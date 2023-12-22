package fun.isite.service.core.system.mapper;

import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.core.system.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户和角色关联 Mapper 接口
 * </p>
 *
 * @author Enigma
 * @since 2023-12-18
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Delete("delete from user_role where user_id = #{userId}")
    int deleteByUserId(String userId);

    @Select("select role_id from user_role where user_id = #{userId}")
    List<String> queryRoleIdsByUserId(String userId);

    List<SystemMenu> queryMenusByUserId(String userId);

    List<String> queryRoleKeyByUserId(String userId);

    List<String> queryPermissionKeyByUserId(String userId);
}
