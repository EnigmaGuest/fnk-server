import {request} from "@/service/http";


export const queryAllSystemMenu = async (params?:any) => {
    return await request.get<any>("system/menu",{params})
}


// 根据rootID查询菜单
export const querySystemMenuByRootId = async (rootId: string) => {
    return await request.get<any>(`system/menu/root/${rootId}`)
}

// 保存或者更新
export const saveOrUpdateSystemMenu = async (data:any,isUpdate:boolean) => {
    if(isUpdate){
        return await request.put<any>(`system/menu/${data.id}`, data)
    }else{
        return await request.post<any>(`system/menu`, data)
    }
}
// 删除菜单
export const deleteSystemMenu = async (id:string) => {
    return await request.del<any>(`system/menu/${id}`)
}
// 查询角色
export const querySystemRole = async (params?:any) => {
    return await request.get<any>("system/role",{params})
}
// 查询所有角色
export const queryAllSystemRole = async () => {
    return await request.get<any>("system/role/all")
}
// 查询角色菜单列表
export const querySystemRoleMenuList = async (roleId:string) => {
    return await request.get<any>(`system/role/${roleId}/menus`)
}
// 保存或者更新角色
export const saveOrUpdateSystemRole = async (data:any,isUpdate:boolean) => {
    if(isUpdate){
        return await request.put<any>(`system/role/${data.id}`, data)
    }else{
        return await request.post<any>(`system/role`, data)
    }
}


// 删除角色
export const deleteSystemRole = async (id:string) => {
    return await request.del<any>(`system/role/${id}`)
}
// 查询用户
export const querySystemUser = async (params?:any) => {
    return await request.get<any>("system/admin/user",{params})
}
// 保存或者更新用户
export const saveOrUpdateSystemUser = async (data:any,isUpdate:boolean) => {
    if(isUpdate){
        return await request.put<any>(`system/admin/user/${data.id}`, data)
    }else{
        return await request.post<any>(`system/admin/user`, data)
    }
}
// 获取指定ID系统用户的角色ID列表
export const querySystemUserRoleList = async (userId:string) => {
    return await request.get<any>(`system/admin/user/${userId}/roles`)
}
// 删除用户
export const deleteSystemUser = async (id:string) => {
    return await request.del<any>(`system/admin/user/${id}`)
}
