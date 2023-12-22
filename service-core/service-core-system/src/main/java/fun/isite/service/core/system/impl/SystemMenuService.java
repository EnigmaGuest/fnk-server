package fun.isite.service.core.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.isite.service.common.db.impl.BaseService;
import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.core.system.mapper.SystemMenuMapper;
import fun.isite.service.core.system.service.IRoleMenuService;
import fun.isite.service.core.system.service.ISystemMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 系统菜单 服务实现层
*
* @author Enigma
* @since 2023-12-18
*/
@Service
@AllArgsConstructor
public class SystemMenuService extends BaseService<SystemMenuMapper, SystemMenu> implements ISystemMenuService {

    private IRoleMenuService roleMenuService;
    @Override
    public List<SystemMenu> listByRootId(String rootId) {
        return this.list(new LambdaQueryWrapper<SystemMenu>().eq(SystemMenu::getRootId, rootId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteMenu(String menuId) {
        int count = roleMenuService.deleteByMenuId(menuId);
        count += this.baseMapper.deleteMenu(menuId);
        return count;
    }
}
