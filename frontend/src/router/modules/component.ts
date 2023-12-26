
import {PageRoute} from "@/typings/route";

const page: PageRoute = {
    name: 'component',
    path: '/component',
    redirect: '/component/icon',
    meta: {
        title: '组件',
        icon: 'line-md:coffee-twotone',
        order: 3,
        keepAlive: false,
    },
    type: 'basic',
    children: [
        {
            name: 'component_icon',
            path: '/component/icon',
            type: 'self',
            meta: {
                title: '图标',
                icon: 'line-md:emoji-smile-wink',
                keepAlive: false,
            },
            component: () => import('@/views/component/icon/index.vue'),
        },
        {
            name: 'component_form',
            path: '/component/form',
            type: 'self',
            meta: {
                title: '表单',
                icon: 'line-md:emoji-smile-wink',
                keepAlive: false,
            },
            component: () => import('@/views/component/form/index.vue'),
        },
        {
            name: 'component_table',
            path: '/component/table',
            type: 'self',
            meta: {
                title: '表格',
                icon: 'line-md:emoji-smile-wink',
                keepAlive: false,
            },
            component: () => import('@/views/component/table/index.vue'),
        }
    ]
}

export default page;