package fun.isite.service.core.system.service;

import fun.isite.service.core.system.entity.RoleInfo;
import fun.isite.service.common.db.service.IBaseService;

import java.util.List;

/**
* 角色信息 服务接口层
*
* @author Enigma
* @since 2023-12-18
*/
public interface IRoleInfoService extends IBaseService<RoleInfo> {


    RoleInfo saveRole(RoleInfo roleInfo);

    RoleInfo updateRole(RoleInfo roleInfo);


    /**
     * 删除角色菜单关联
     * @param roleId 菜单id
     * @return 删除条数
     */
    int deleteByRoleId(String roleId);

    /**
     * 查询详情
     * @param roleId 角色id
     * @return 角色信息
     */
    RoleInfo queryDetail(String roleId);

    /**
     * 查询角色菜单
     * @param roleId 角色id
     * @return 菜单id集合
     */
    List<String> queryRoleMenuIds(String roleId);

}
