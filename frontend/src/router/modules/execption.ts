
import {PageRoute} from "@/typings/route";

const router: PageRoute = {
    name: 'exception',
    path: '/exception',
    redirect: '/exception/404',
    meta: {
        title: '异常页',
        icon: 'line-md:alert-circle',
        order: 4
    },
    type: 'basic',
    component: () => import('@/layouts/index.vue'),
    children: [
        {
            name: 'exception_404',
            path: '/exception/404',
            type: 'self',
            meta: {
                title: '404',
                icon: 'line-md:alert-circle',
            },
            component: () => import('@/views/exception/404/index.vue'),
        },
        {
            name: 'exception_403',
            path: '/exception/403',
            type: 'self',
            meta: {
                title: '403',
                icon: 'line-md:alert-circle',
            },
            component: () => import('@/views/exception/403/index.vue'),
        },
        {
            name: 'exception_500',
            path: '/exception/500',
            type: 'self',
            meta: {
                title: '500',
                icon: 'line-md:alert-circle',
            },
            component: () => import('@/views/exception/500/index.vue'),
        }
    ]
}
export default router;