package fun.isite.service.core.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import fun.isite.service.common.db.entity.BaseEntity;
import fun.isite.service.core.system.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
* 系统菜单
*
* @author Enigma
* @since 2023-12-18
*/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_menu")
@Schema(name = "SystemMenu", description = "系统菜单")
public class SystemMenu extends BaseEntity<SystemMenu> {

    /**
    * 上级ID
    */
    @Schema(description="上级ID")
    private String rootId;

    /**
    * 菜单名称
    */
    @Schema(description="菜单名称")
    private String name;

    /**
    * 显示顺序
    */
    @Schema(description="显示顺序")
    private Integer orderSort;

    /**
    * 请求URL
    */
    @Schema(description="请求URL")
    private String url;

    /**
    * 菜单icon
    */
    @Schema(description="菜单icon")
    private String icon;

    /**
    * 是否显示
    */
    @Schema(description="是否显示")
    private Boolean visible;

    /**
    * 权限标识
    */
    @Schema(description="权限标识")
    private String permission;

    /**
    * 是否新开页面
    */
    @Schema(description="是否新开页面")
    private Boolean isBlank;

    /**
    * 菜单类型（table目录 menu菜单 button按钮）
    */
    @Schema(description="菜单类型（table目录 menu菜单 button按钮）")
    private MenuTypeEnum type;

    /**
    * 备注
    */
    @Schema(description="备注")
    private String remark;


}
