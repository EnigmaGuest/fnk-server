/**
 * 登录返回的数据
 */
interface TokenVO {
    tokenValue: string;
    tokenName: string;
    isLogin: boolean;
    loginId: string;
    loginType: string;
    tokenTimeout: number;
    sessionTimeout: number;
    tokenSessionTimeout: number;
    tokenActivityTimeout: number;
    loginDevice: string;
    tag: string;
}

interface IMenus {
    id: string;
    rootId: string;
    name: string;
    routeKey: string;
    orderSort: number;
    path: string;
    icon: string;
    localIcon: string;
    visible: boolean;
    permission: string;
    type: string;
    isIframe: boolean;
    children: IMenus[];
}
interface AdminUserVO{
    id: string;
    phone: string;
    username: string;
    sex: string;
    avatar: string;
    menus: IMenus[]
    [x: string]: any;
}


