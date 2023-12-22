package fun.isite.service.core.system.mapper;

import fun.isite.service.core.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联 Mapper 接口
 * </p>
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from role_menu where menu_id = #{menuId}")
    int deleteByMenuId(String menuId);


    @Delete("delete from role_menu where role_id = #{roleId}")
    int deleteByRoleId(String roleId);

    @Select("select menu_id from role_menu where role_id = #{roleId}")
    List<String> queryMenuIdByRoleId(String roleId);

    /**
     * 查询角色所有权限key
     * @param roleId 角色id
     * @return 权限key
     */
    List<String> queryPermissionKeyByRoleId(String roleId);

    List<String> queryPermissionKeyByRoleKey(String roleKey);
}
