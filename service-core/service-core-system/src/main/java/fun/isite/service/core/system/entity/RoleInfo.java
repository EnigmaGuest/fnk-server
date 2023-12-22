package fun.isite.service.core.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.isite.service.common.db.entity.BaseEntity;
import fun.isite.service.common.db.handler.StringListTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* 角色信息
*
* @author Enigma
* @since 2023-12-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "role_info",autoResultMap = true)
@Schema(name = "RoleInfo", description = "角色信息")
public class RoleInfo extends BaseEntity<RoleInfo> {

    /**
    * 名称
    */
    @Schema(description="名称")
    private String roleName;

    /**
    * 角色key
    */
    @Schema(description="角色key")
    private String roleKey;

    /**
    * 显示排序
    */
    @Schema(description="显示排序")
    private Integer orderSort;

    /**
    * 角色数据范围
     * 仅用户数据传递
    */
    @Schema(description="角色数据范围")
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> roleScope;

    /**
    * 状态
    */
    @Schema(description="状态")
    private Boolean status;


}
