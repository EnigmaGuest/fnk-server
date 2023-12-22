package fun.isite.service.core.system.impl;

import fun.isite.service.common.db.impl.BaseService;
import fun.isite.service.core.system.entity.RoleMenu;
import fun.isite.service.core.system.mapper.RoleMenuMapper;
import fun.isite.service.core.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 角色和菜单关联 服务实现层
*
* @author Enigma
* @since 2023-12-18
*/
@Service
public class RoleMenuService extends BaseService<RoleMenuMapper, RoleMenu> implements IRoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByMenuId(String menuId) {
        return this.baseMapper.deleteByMenuId(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByRoleId(String roleId) {
        return this.baseMapper.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleMenu(String roleId, List<String> menuIds) {
        return this.saveBatch(menuIds.stream().map(menuId -> new RoleMenu(roleId, menuId)).toList());
    }

    @Override
    public List<String> queryMenuIdByRoleId(String roleId) {
        return this.baseMapper.queryMenuIdByRoleId(roleId);
    }

    @Override
    public List<String> queryPermissionKeyByRoleId(String roleId) {
        return this.baseMapper.queryPermissionKeyByRoleId(roleId);
    }

    @Override
    public List<String> queryPermissionKeyByRoleKey(String roleKey) {
        List<String> permissionKeyList = this.baseMapper.queryPermissionKeyByRoleKey(roleKey);
        return permissionKeyList.stream().filter(e-> !"".equals(e)).toList();
    }
}
