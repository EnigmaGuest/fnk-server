package fun.isite.service.core.system.impl;

import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.core.system.entity.UserRole;
import fun.isite.service.core.system.mapper.UserRoleMapper;
import fun.isite.service.core.system.service.IUserRoleService;
import fun.isite.service.common.db.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户和角色关联 服务实现层
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Service
public class UserRoleService extends BaseService<UserRoleMapper, UserRole> implements IUserRoleService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserRole(String userId, List<String> roleIds) {
        return this.saveBatch(roleIds.stream().map(roleId -> new UserRole(userId, roleId)).toList());
    }

    @Override
    public int deleteByUserId(String userId) {
        return this.baseMapper.deleteByUserId(userId);
    }

    @Override
    public List<String> queryRoleIdsByUserId(String userId) {
        return this.baseMapper.queryRoleIdsByUserId(userId);
    }

    @Override
    public List<SystemMenu> queryMenusByUserId(String userId) {
        return this.baseMapper.queryMenusByUserId(userId);
    }

    @Override
    public List<String> queryRoleKey(String userId) {
        return this.baseMapper.queryRoleKeyByUserId(userId);
    }


    @Override
    public List<String> queryPermissionKeyByUserId(String userId) {
        List<String> permissionKeyList = this.baseMapper.queryPermissionKeyByUserId(userId);
        // 去重
        return permissionKeyList.stream().distinct().toList();
    }
}
