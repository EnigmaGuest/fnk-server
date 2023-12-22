package fun.isite.service.core.system.service;

import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.common.db.service.IBaseService;

import java.util.List;

/**
* 系统菜单 服务接口层
*
* @author Enigma
* @since 2023-12-18
*/
public interface ISystemMenuService extends IBaseService<SystemMenu> {


    List<SystemMenu> listByRootId(String rootId);


    /**
     * 删除菜单
     * @param menuId
     * @return 处理多少相关数据
     */
    int deleteMenu(String menuId);


}
