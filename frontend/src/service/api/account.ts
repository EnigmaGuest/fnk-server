import {request} from "@/service/http";


/**
 * 登录管理员
 * @param data
 */
export const loginAdmin = async (data) => {
    return await request.post<TokenVO>('/account/login', data)
}

/**
 * 获取当前登录用户
 */
export const getAdminInfo = async () => {
    return await request.get<AdminUserVO>("account/admin")
}