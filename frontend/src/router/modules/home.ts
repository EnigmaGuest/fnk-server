import {PageRoute} from "@/typings/route";
import {basicLayout} from "@/router/common";

const router: PageRoute =
    {
        name: 'home',
        path: '/home',
        meta: {
            title: '首页',
            icon: 'line-md:home-md-twotone-alt',
            affix: true,
            keepAlive: true,
        },
        isSingle: true,
        type: 'basic',
        component: () => import('@/views/home/index.vue')
    }

export default router;