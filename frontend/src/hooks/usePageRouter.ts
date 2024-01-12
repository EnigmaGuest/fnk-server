import {router as globalRouter} from "@/router"
import {RouteLocationRaw, useRouter} from "vue-router";

export function usePageRouter(isSetup = true) {
    const router = isSetup ? useRouter() : globalRouter;
    const route = globalRouter.currentRoute;

    function routerPush(to: RouteLocationRaw, newTab = false) {
        if (newTab) {
            const routerData = router.resolve(to);
            window.open(routerData.href, '_blank');
            return Promise.resolve();
        }
        return router.push(to);
    }

    /**
     * 返回上一级路由
     */
    function routerBack() {
        router.go(-1);
    }

    /**
     * 跳转首页
     * @param newTab
     */
    function toHome(newTab = false) {
        const {VITE_ROUTE_HOME_PATH} = import.meta.env
         routerPush({path: VITE_ROUTE_HOME_PATH}, newTab);
    }

    /**
     * 跳转登录页面
     * @param redirectUrl - 重定向地址(登录成功后跳转的地址)
     */
    function toLogin(redirectUrl?: string){
        const redirect = redirectUrl || route.value.fullPath;
        routerPush({path: "/login", query: {redirect}});
    }

    /**
     * 登录成功后跳转重定向的地址
     */
    function toLoginRedirect(){
        const {query} = route.value;
        if (query?.redirect) {
            routerPush(query.redirect as string);
        } else {
            toHome();
        }
    }
    return {
        routerPush,
        routerBack,
        toHome,
        toLogin,
        toLoginRedirect
    }
}