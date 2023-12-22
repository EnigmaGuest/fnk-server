package fun.isite.service.core.system.service;

import fun.isite.service.core.system.entity.RoleMenu;
import fun.isite.service.common.db.service.IBaseService;

import java.util.List;

/**
* 角色和菜单关联 服务接口层
*
* @author Enigma
* @since 2023-12-18
*/
public interface IRoleMenuService extends IBaseService<RoleMenu> {


    int deleteByMenuId(String menuId);

    int deleteByRoleId(String roleId);

    boolean saveRoleMenu(String roleId, List<String> menuIds);

    /**
     * 根据角色id查询角色所有菜单id
     * @param roleId
     * @return
     */
    List<String> queryMenuIdByRoleId(String roleId);

    /**
     * 根据角色id查询角色所有权限key
     * @param roleId
     * @return
     */
    List<String> queryPermissionKeyByRoleId(String roleId);

    /**
     * 根据角色key查询角色所有权限key
     * @param roleKey
     * @return
     */
    List<String> queryPermissionKeyByRoleKey(String roleKey);

}
