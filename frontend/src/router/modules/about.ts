import {PageRoute} from "@/typings/route";

const router: PageRoute =
    {
        name: 'about',
        path: '/about',
        meta: {
            title: '关于',
            icon: 'line-md:lightbulb-twotone',
            order:10,
            affix: false,
            keepAlive: true,
        },
        isSingle: true,
        type: 'basic',
        component: () => import('@/views/about/index.vue')
    }

export default router;