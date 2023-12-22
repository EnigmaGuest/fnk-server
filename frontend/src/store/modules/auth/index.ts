import {defineStore} from "pinia";
import {SecureStorage} from "@/store/plugins";
import {useRouteStore} from "@/store/modules/route";
import {getAdminInfo} from "@/service/api/account";


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
        }
    },
    actions: {
        // 重置auth状态
        resetAuthStore() {
            this.$reset();
        },
        async loginByToken(token: string) {
            this.token = token
            const {data} = await getAdminInfo()
            this.userInfo = data
            return data != null
            // 查询用户信息
        }
    },
    persist: {
        // 持久化存储 使用加密的存储方式
        storage: SecureStorage
    }
})