package fun.isite.service.core.system.service;

import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.core.system.entity.UserRole;
import fun.isite.service.common.db.service.IBaseService;

import java.util.List;

/**
* 用户和角色关联 服务接口层
*
* @author Enigma
* @since 2023-12-18
*/
public interface IUserRoleService extends IBaseService<UserRole> {


    /**
     * 保存用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRole(String userId, List<String> roleIds);

    int deleteByUserId(String userId);

    /**
     * 查询用户角色ID
     * @param userId 用户id
     * @return 用户角色id
     */
    List<String> queryRoleIdsByUserId(String userId);

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<SystemMenu> queryMenusByUserId(String userId);

    /**
     * 查询用户角色key
     * @param userId
     * @return
     */
    List<String> queryRoleKey(String userId);



    /**
     * 查询用户权限key
     * @param userId
     * @return
     */
    List<String> queryPermissionKeyByUserId(String userId);
}
