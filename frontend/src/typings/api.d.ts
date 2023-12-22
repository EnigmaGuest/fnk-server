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
    orderSort: number;
    url: string;
    icon: string;
    visible: boolean;
    permission: string;
    type: number;
    isBlank: boolean;
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


