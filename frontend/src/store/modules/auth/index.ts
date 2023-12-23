import {defineStore} from "pinia";
import {SecureStorage} from "@/store/plugins";
import {useRouteStore} from "@/store/modules/route";
import {getAdminInfo} from "@/service/api/account";
import {useTabsStore} from "@/store";


interface AuthState {
    /** 用户信息 */
    userInfo: AdminUserVO;
    /** 用户token */
    token: string;
}



export const useAuthStore = defineStore({
    id: 'auth-store',
    state: (): AuthState => ({
        userInfo: null,
        token: ''
    }),
    getters: {
        /** 是否登录 */
        isLogin(state) {
            return Boolean(state?.token);
        },
        getAuthRouterName(state) {
            return state.userInfo?.menus?.map((item: any) => item.routeKey)
        },
        getMenus(state) {
            return state.userInfo?.menus
        }
    },
    actions: {
        // 重置auth状态
        resetAuthStore() {
            this.$reset();
        },
        async loginByToken(token: string) {
            this.token = token
            // 查询用户信息
            const {data} = await getAdminInfo()
            this.userInfo = data
            const ut = useRouteStore()
            // 加载路由
            await ut.initRoute()
            return data != null
        },
        /**
         * 退出登录
         */
        loginOut() {
            this.$reset();
            useRouteStore().resetRouteStore()
            useTabsStore().$reset()
        }
    },
    persist: {
        // 持久化存储 使用加密的存储方式
        storage: SecureStorage
    }
})