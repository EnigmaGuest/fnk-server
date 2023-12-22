package fun.isite.service.core.system.vo;

import fun.isite.service.core.basic.enums.GenderType;
import fun.isite.service.core.system.entity.AdminUser;
import fun.isite.service.core.system.entity.SystemMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author Enigma
 */
@Data
@Schema(description = "管理员信息")
public class AdminUserVO {


    private String id;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;


    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String username;

    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;

    /**
     * 用户性别
     */
    @Schema(description = "用户性别")
    private GenderType sex;


    // 权限信息

    private List<String> roles;

    // 权限菜单
    private List<SystemMenu> menus;
    public static AdminUserVO build(AdminUser adminUser) {
        AdminUserVO adminUserVO = new AdminUserVO();
        BeanUtils.copyProperties(adminUser, adminUserVO);
        return adminUserVO;
    }
}
