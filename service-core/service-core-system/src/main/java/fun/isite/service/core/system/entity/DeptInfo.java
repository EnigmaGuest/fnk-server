package fun.isite.service.core.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.isite.service.common.db.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 部门信息
*
* @author Enigma
* @since 2023-12-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("dept_info")
@Schema(name = "DeptInfo", description = "部门信息")
public class DeptInfo extends BaseEntity<DeptInfo> {

    /**
    * 上级ID
    */
    @Schema(description="上级ID")
    private String rootId;

    /**
    * 所有的上级ID集合列表
    */
    @Schema(description="所有的上级ID集合列表")
    private String allRootId;

    /**
    * 部门名称
    */
    @Schema(description="部门名称")
    private String name;

    /**
    * 排序
    */
    @Schema(description="排序")
    private Integer orderSort;

    /**
    * 管理用户名称
    */
    @Schema(description="管理用户名称")
    private String leader;

    /**
    * 手机号码
    */
    @Schema(description="手机号码")
    private String phone;

    /**
    * 邮箱
    */
    @Schema(description="邮箱")
    private String email;

    /**
    * 状态
    */
    @Schema(description="状态")
    private Boolean status;


}
