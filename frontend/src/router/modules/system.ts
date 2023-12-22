import {PageRoute} from "@/typings/route";
import {basicLayout} from "@/router/common";

const router: PageRoute =
    {
        name: 'system',
        path: '/system',
        redirect: '/system/user',
        meta: {
            title: '系统管理',
            icon: 'tdesign:system-setting',
            keepAlive: true,
        },
        component: () => basicLayout(),
        children: [
            {
                name: 'system_user',
                path: '/system/user',
                meta: {
                    title: '用户管理',
                    icon: 'line-md:person-search-twotone',
                    keepAlive: true,
                },
                component: () => import('@/views/system/user/index.vue'),
            },
            {
                name: 'system_menu',
                path: '/system/menu',
                meta: {
                    title: '菜单管理',
                    icon: 'line-md:list-3-filled',
                    keepAlive: true,
                },
                component: () => import('@/views/system/menu/index.vue'),
            },
            {
                name: 'system_role',
                path: '/system/role',
                meta: {
                    title: '角色管理',
                    icon: 'mingcute:safety-certificate-fill',
                    keepAlive: true,
                },
                component: () => import('@/views/system/role/index.vue'),
            }
        ]
    }

export default router;