package fun.isite.service.core.system.mapper;

import fun.isite.service.core.system.entity.SystemMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Mapper
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    @Delete("delete from system_menu where id = #{id}")
    int deleteMenu(String id);
}
