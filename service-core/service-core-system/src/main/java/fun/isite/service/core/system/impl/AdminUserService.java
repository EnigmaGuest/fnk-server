package fun.isite.service.core.system.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.isite.service.common.db.impl.BaseService;
import fun.isite.service.common.tools.lang.AssertUtils;
import fun.isite.service.common.tools.utils.SaltUtils;
import fun.isite.service.core.basic.enums.GenderType;
import fun.isite.service.core.basic.vo.TokenVO;
import fun.isite.service.core.system.cache.RoleCache;
import fun.isite.service.core.system.dto.LoginAdminDTO;
import fun.isite.service.core.system.entity.AdminUser;
import fun.isite.service.core.system.mapper.AdminUserMapper;
import fun.isite.service.core.system.service.IAdminUserService;
import fun.isite.service.core.system.service.IUserRoleService;
import fun.isite.service.core.system.vo.AdminUserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户 服务实现层
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Service
@AllArgsConstructor
public class AdminUserService extends BaseService<AdminUserMapper, AdminUser> implements IAdminUserService {


    private IUserRoleService userRoleService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initAdminUser() {
        long count = this.count();
        AssertUtils.isTrue(count >= 1, "系统用户已经初始化");
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("admin");
        adminUser.setSalt(SaltUtils.getSalt(4));
        adminUser.setPhone("18888888888");
        adminUser.setPassword("123456");
        adminUser.setSex(GenderType.MAN);
        adminUser.setStatus(true);
        adminUser.setPassword(encryptPassword(adminUser.getPassword(), adminUser.getSalt()));
        this.save(adminUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminUser saveAdminUser(AdminUser adminUser) {
        AssertUtils.isTrue(isPhoneExist(adminUser.getPhone(), null), "手机号码已存在！");
        adminUser.setSalt(SaltUtils.getSalt(4));
        adminUser.setPassword(encryptPassword(adminUser.getPassword(), adminUser.getSalt()));
        AssertUtils.isFalse(this.save(adminUser), "保存用户失败！");
        userRoleService.saveUserRole(adminUser.getId(), adminUser.getRoleIdList());
        return adminUser;
    }

    @Override
    public AdminUser updateAdminUser(AdminUser adminUser) {
        AssertUtils.isTrue(isPhoneExist(adminUser.getPhone(), adminUser.getId()), "手机号码已存在！");
        if (adminUser.getPassword() != null) {
            adminUser.setSalt(SaltUtils.getSalt(4));
            adminUser.setPassword(encryptPassword(adminUser.getPassword(), adminUser.getSalt()));
        }
        AssertUtils.isFalse(this.updateById(adminUser), "更新用户失败！");
        userRoleService.deleteByUserId(adminUser.getId());
        userRoleService.saveUserRole(adminUser.getId(), adminUser.getRoleIdList());
        // 强制退出被修改的用户使其重新登录
        logout(adminUser.getId());
        return adminUser;
    }

    @Override
    public AdminUser queryDetail(String userId) {
        AdminUser adminUser = this.getById(userId);
        if (adminUser != null) {
            adminUser.setRoleIdList(userRoleService.queryRoleIdsByUserId(userId));
            return adminUser;
        }
        return null;
    }

    @Override
    public TokenVO login(LoginAdminDTO loginAdminDTO) {
        AdminUser adminUser = this.getFirst(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getPhone, loginAdminDTO.phone()));
        AssertUtils.isNull(adminUser, "用户信息不存在！");
        AssertUtils.isFalse(adminUser.getStatus(), "用户已被禁用！");
        String encryptPassword = encryptPassword(loginAdminDTO.password(), adminUser.getSalt());
        AssertUtils.isFalse(adminUser.getPassword().equals(encryptPassword), "密码错误！");
        if (loginAdminDTO.ip() != null) {
            adminUser.setLoginIp(loginAdminDTO.ip());
            adminUser.updateById();
        }
        StpUtil.login(adminUser.getId());
        return TokenVO.generateFromSaToken(StpUtil.getTokenInfo());
    }

    @Override
    public void logout(String userId) {
        StpUtil.logout(userId);
        RoleCache.resetUserRoleCache(userId);
    }

    @Override
    public Boolean isPhoneExist(String phone, String userId) {

        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getPhone, phone);
        if (userId != null) {
            wrapper.ne(AdminUser::getId, userId);
        }
        return this.count(wrapper) > 1;
    }

    @Override
    public AdminUserVO getCurrentAdminInfo(String Id) {
        AdminUser adminUser = this.getById(Id);
        AssertUtils.isNull(adminUser, "用户信息不存在！");
        AdminUserVO adminUserVO = AdminUserVO.build(adminUser);
        adminUserVO.setMenus(userRoleService.queryMenusByUserId(adminUser.getId()));
        return adminUserVO;
    }

    @Override
    public List<String> queryUserRoleIds(String userId) {
        return userRoleService.queryRoleIdsByUserId(userId);
    }


    // 加密密码
    private String encryptPassword(String password, String salt) {
        return SecureUtil.md5(SecureUtil.md5(password) + salt);
    }
}
