import {defineStore} from "pinia";
import {routeModuleList, router} from "@/router";
import {useAuthStore} from "@/store";
import {PageRoute} from "@/typings/route";
import {
    dynamicGenerateRoutes,
    dynamicGenerateMenus,
    getCacheRoutes,
    groupDynamicMenu, staticPageRouteGenerateRoutes,
    renderIcon
} from "@/utils";
import {System} from "@/typings/system";
import {nextTick, toRaw, unref} from "vue";

interface RouteState {

    /**
     * 权限路由模式:
     * - static - 前端声明的静态
     * - dynamic - 后端返回的动态
     */
    routeMode: ImportMetaEnv['VITE_AUTH_ROUTE_MODE'];

    /** 是否初始化了权限路由 */
    isInitRoute: boolean;

    /** 路由首页name(前端静态路由时生效，后端动态路由该值会被后端返回的值覆盖) */
    routeHomeName: string;
    /** 菜单 */
    menus: System.GlobalMenu[];
    /** 缓存的路由名称 */
    cacheRoutes: string[];
    /** 动态路由 */
    dynamicRoutes: PageRoute[];
    /** 页面刷新的标识  用来刷新页面 true 才能显示*/
    reloadFlag: boolean;
}

export const useRouteStore = defineStore({
    id: 'app-route',
    state: (): RouteState => ({
        routeMode: import.meta.env.VITE_AUTH_ROUTE_MODE,
        isInitRoute: false,
        routeHomeName: import.meta.env.VITE_ROUTE_HOME_PATH,
        menus: [],
        cacheRoutes: [],
        dynamicRoutes: [],
        reloadFlag: true
    }),
    actions: {
        /** 重置路由的store */
        resetRouteStore() {
            // this.resetRoutes();
            this.$reset();
        },
        /** 重置路由数据，保留固定路由 */
        resetRoutes() {
            // 模块路由
            this.dynamicRoutes?.forEach((route: any) => {
                router.removeRoute(route.name);
            });
        },
        handleRoute(routes: PageRoute[]) {
            routes.forEach((route: any) => {
                router.addRoute(route)
            })
        },
        // 初始化动态路由
        async initDynamicRoute() {
            const ua = useAuthStore()
            if (ua.isLogin){
                // 获取动态路由
                const menus =[]
                Object.assign(menus,ua.getMenus)
                // 处理菜单
                if (!menus.length){
                    console.warn("没有菜单")
                    return
                }
                const menuItems:IMenus[] = groupDynamicMenu(menus)
                // 生成路由
                if (menuItems.length){
                    this.dynamicRoutes = dynamicGenerateRoutes(menuItems)
                    this.menus = dynamicGenerateMenus(this.dynamicRoutes)
                }
            }

        },
        // 初始化静态路由
        async initStaticRoute() {
            const ua = useAuthStore()
            this.dynamicRoutes = staticPageRouteGenerateRoutes(routeModuleList,ua.getAuthRouterName)
            // 添加菜单
            this.menus = dynamicGenerateMenus(this.dynamicRoutes)
        },
        /**
         * 初始化路由
         */
        async initRoute() {
            if (this.routeMode === 'static') {
                await this.initStaticRoute()
            } else {
                await this.initDynamicRoute()
            }
            // 添加路由
            this.handleRoute(this.dynamicRoutes)
            this.isInitRoute = true;
            // 缓存路由名称
            this.cacheRoutes = getCacheRoutes(this.dynamicRoutes)
        },
        /**
         * 刷新页面
         */
        async reloadPage() {
            this.reloadFlag = false
            await nextTick(() => {
                this.reloadFlag = true
            })
        }
    }
})
