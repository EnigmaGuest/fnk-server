package fun.isite.service.core.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.isite.service.common.db.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 角色和菜单关联
*
* @author Enigma
* @since 2023-12-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_menu")
@Schema(name = "RoleMenu", description = "角色和菜单关联")
public class RoleMenu extends BaseEntity<RoleMenu> {

    /**
    * 角色id
    */
    @Schema(description="角色id")
    private String roleId;

    /**
    * 菜单ID
    */
    @Schema(description="菜单ID")
    private String menuId;


}
