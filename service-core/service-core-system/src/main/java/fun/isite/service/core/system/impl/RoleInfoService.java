package fun.isite.service.core.system.impl;

import fun.isite.service.common.db.impl.BaseService;
import fun.isite.service.common.tools.lang.AssertUtils;
import fun.isite.service.core.system.cache.RoleCache;
import fun.isite.service.core.system.entity.RoleInfo;
import fun.isite.service.core.system.mapper.RoleInfoMapper;
import fun.isite.service.core.system.service.IRoleInfoService;
import fun.isite.service.core.system.service.IRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色信息 服务实现层
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Service
@AllArgsConstructor
public class RoleInfoService extends BaseService<RoleInfoMapper, RoleInfo> implements IRoleInfoService {

    private IRoleMenuService roleMenuService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleInfo saveRole(RoleInfo roleInfo) {
        AssertUtils.isFalse(this.save(roleInfo), "保存角色信息失败");
        AssertUtils.isFalse(roleMenuService.saveRoleMenu(roleInfo.getId(), roleInfo.getRoleScope()), "保存角色菜单关联失败");
        return roleInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleInfo updateRole(RoleInfo roleInfo) {
        AssertUtils.isFalse(this.updateById(roleInfo), "更新角色信息失败");
        roleMenuService.deleteByRoleId(roleInfo.getId());
        AssertUtils.isFalse(roleMenuService.saveRoleMenu(roleInfo.getId(), roleInfo.getRoleScope()), "保存角色菜单关联失败");
        // 更新缓存
        RoleCache.setRolePermissionKey(roleInfo.getRoleKey(),roleMenuService.queryPermissionKeyByRoleKey(roleInfo.getRoleKey()));
        // 删除SaToken缓存
        RoleCache.deleteSaTokenRoleCache(roleInfo.getRoleKey());
        return roleInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByRoleId(String roleId) {
        RoleInfo roleInfo =  this.getById(roleId);
        RoleCache.resetRoleCache(roleInfo.getRoleKey());
        RoleCache.deleteSaTokenRoleCache(roleInfo.getRoleKey());
        int count = roleMenuService.deleteByRoleId(roleId);
        return count + this.baseMapper.deleteById(roleId);
    }

    @Override
    public RoleInfo queryDetail(String roleId) {
        RoleInfo roleInfo = this.getById(roleId);
        if (roleInfo != null) {
            roleInfo.setRoleScope(roleMenuService.queryMenuIdByRoleId(roleId));
            return roleInfo;
        }
        return null;
    }

    @Override
    public List<String> queryRoleMenuIds(String roleId) {
        return roleMenuService.queryMenuIdByRoleId(roleId);
    }
}
