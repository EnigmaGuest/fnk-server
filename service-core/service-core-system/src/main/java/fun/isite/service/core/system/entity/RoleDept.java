package fun.isite.service.core.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.isite.service.common.db.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 角色关联部门
*
* @author Enigma
* @since 2023-12-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_dept")
@Schema(name = "RoleDept", description = "角色关联部门")
public class RoleDept extends BaseEntity<RoleDept> {

    /**
    * 角色ID
    */
    @Schema(description="角色ID")
    private String roleId;

    /**
    * 部门ID
    */
    @Schema(description="部门ID")
    private String deptId;


}
