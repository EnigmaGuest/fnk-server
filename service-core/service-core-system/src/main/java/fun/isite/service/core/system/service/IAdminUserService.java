package fun.isite.service.core.system.service;

import fun.isite.service.common.db.service.IBaseService;
import fun.isite.service.core.basic.vo.TokenVO;
import fun.isite.service.core.system.dto.LoginAdminDTO;
import fun.isite.service.core.system.entity.AdminUser;
import fun.isite.service.core.system.vo.AdminUserVO;

import java.util.List;

/**
* 系统用户 服务接口层
*
* @author Enigma
* @since 2023-12-18
*/
public interface IAdminUserService extends IBaseService<AdminUser> {

    void initAdminUser();

    /**
     * 保存用户
     * @param adminUser
     * @return
     */
    AdminUser saveAdminUser(AdminUser adminUser);

    /**
     * 更新用户
     * @param adminUser
     * @return
     */

    AdminUser updateAdminUser(AdminUser adminUser);

    /**
     * 查询用户详情
     * @param userId 用户id
     * @return 用户详情
     */
    AdminUser queryDetail(String userId);

    /**
     * 登录
     *
     * @param loginAdminDTO 登录信息
     * @return token
     */
    TokenVO login(LoginAdminDTO loginAdminDTO);

    // 退出登录
    void logout(String userId);

    /**
     * 手机号码是否存在
     *
     * @param phone  手机号码
     * @param userId 排除的用户id
     * @return true 存在
     */
    Boolean isPhoneExist(String phone, String userId);


    AdminUserVO getCurrentAdminInfo(String s);

    /**
     * 查询用户角色ID列表
     * @param userId
     * @return
     */
    List<String> queryUserRoleIds(String userId);
}
