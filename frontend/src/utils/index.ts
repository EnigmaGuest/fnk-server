import {h} from "vue";
import EgIcon from "@/components/common/eg-icon.vue";
import {PageRoute, RouteComponentType} from "@/typings/route";
import {System} from "@/typings/system";
import {RouteComponent} from "vue-router";


// 渲染icon
export const renderIcon = (icon?: string, localIcon?: string) => {
    if (!icon && !localIcon) {
        console.warn('not find icon')
        return null
    }
    // 默认p 内容为icon
    return () => h(EgIcon, {icon, localIcon})
}

// 生成菜单
export const generateMenus = (pages: PageRoute[]) => {
    const menuOptions: System.GlobalMenu[] = []
    pages.forEach((page: PageRoute) => {
        if (page.meta?.title) {
            const globalMenu: System.GlobalMenu = {
                label: page.meta.title,
                key: page.name,
                icon: renderIcon(page.meta.icon, page.meta.localIcon),
                routeName: page.name,
                routePath: page.path,
                meta: page.meta,
                component: generateComponent(page.component),
            }
            // 判断是否只有一个子菜单
            const isRoot = page?.children?.filter((item: PageRoute) => !Boolean(item?.meta?.hide))?.length === 1;
            if (isRoot) {
                // 如果只有一个子菜单，那么就不显示父级菜单 // todo 修改单个
                const rootMenu = page?.children?.filter((item: PageRoute) => !Boolean(item?.meta?.hide))[0]
                globalMenu.key = rootMenu.name
                globalMenu.label = rootMenu.meta?.title
                globalMenu.routeName = rootMenu.name
                globalMenu.routePath = rootMenu.path
                globalMenu.component = generateComponent(rootMenu.component)
                globalMenu.icon = renderIcon(rootMenu.meta?.icon, rootMenu.meta?.localIcon)
            } else {
                // 如果有多个子菜单，那么就显示父级菜单（是过滤隐藏后的）
                globalMenu.children = page?.children?.filter((item: PageRoute) => !Boolean(item?.meta?.hide))?.map((item: PageRoute) => {
                    return {
                        label: item.meta?.title,
                        key: item.name,
                        icon: renderIcon(item.meta?.icon, item.meta?.localIcon),
                        routeName: item.name,
                        routePath: item.path,
                        component: generateComponent(item.component),
                        meta: item.meta,
                    }
                })
            }
            menuOptions.push(globalMenu)
        }
    })
    return menuOptions
}
export const dynamicGenerateMenus = (menus: IMenus[]) => {
    const menuOptions: System.GlobalMenu[] = []
    menus.forEach((menu: IMenus) => {
        if (menu.name) {
            const globalMenu: System.GlobalMenu = {
                label: menu.name,
                key: menu.name,
                icon: renderIcon(menu.icon),
                routeName: menu.name,
                routePath: menu.url,
                meta: {
                    title: menu.name,
                    icon: menu.icon,
                    permission: menu.permission,
                    hide: !menu.visible,
                },
                component: generateComponent(menu.isBlank ? 'blank' : 'basic'),
            }
            // 判断是否只有一个子菜单
            const isRoot = menu?.children?.filter((item: IMenus) => item.visible)?.length === 1;
            if (isRoot) {
                // 如果只有一个子菜单，那么就不显示父级菜单 // todo 修改单个
                const itemMenu = menu?.children?.filter((item: IMenus) => item.visible)[0]
                globalMenu.key = itemMenu.name
                globalMenu.label = itemMenu.name
                globalMenu.routeName = itemMenu.name
                globalMenu.routePath = itemMenu.url
                globalMenu.component = generateComponent(itemMenu.isBlank ? 'blank' : 'basic')
                globalMenu.icon = renderIcon(itemMenu.icon)
            } else {
                // 如果有多个子菜单，那么就显示父级菜单（是过滤隐藏后的）
                globalMenu.children = menu?.children?.filter((item: IMenus) => item.visible)?.map((item: IMenus) => {
                    return {
                        label: item.name,
                        key: item.name,
                        icon: renderIcon(item.icon),
                        routeName: item.name,
                        routePath: item.url,
                        component: generateComponent(item.isBlank ? 'blank' : 'basic'),
                        meta: {
                            title: item.name,
                            icon: item.icon,
                            permission: item.permission,
                            hide: !item.visible,
                        },
                    }
                })
            }
            menuOptions.push(globalMenu)
        }
    })
    return menuOptions
}

//RouteComponent
function generateComponent(component: RouteComponentType) {
    switch (component) {
        case "iframe":
            return () => import('@/layouts/iframe.vue')
        case "basic":
            return () => import('@/layouts/index.vue')
        case "blank":
            return () => import('@/layouts/blank.vue')
        default:
            return component
    }
}

// 生成路由

// 获取缓存的路由
export const getCacheRoutes = (pages: PageRoute[]) => {
    const cacheRoutes: string[] = []
    pages.forEach((page: PageRoute) => {
        if (page.meta?.keepAlive) {
            cacheRoutes.push(page.name)
        }
        if (page?.children?.length) {
           cacheRoutes.push(...getCacheRoutes(page.children))
        }
    })
    return cacheRoutes
}

/**
 * 将菜单项分组
 * @param menuItems
 * @param parentId
 */
export function groupMenuItems(menuItems: any[], parentId: number | string = '0'): IMenus[] {
    const groupedItems: any[] = [];

    const childItems = menuItems.filter(item => item.rootId === parentId);
    childItems.forEach(childItem => {
        const index = menuItems.indexOf(childItem);
        if (index !== -1) {
            menuItems.splice(index, 1); // 从原始数据中移除已使用的数据
        }
        let items = groupMenuItems(menuItems, childItem.id);
        if (items.length > 0) {
            childItem.children = items;
        }
        groupedItems.push(childItem);
    });
    return groupedItems;
}

export * from './color'