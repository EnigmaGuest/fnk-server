package fun.isite.service.api.admin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import fun.isite.service.common.bean.http.RestResponse;
import fun.isite.service.common.db.dto.SplitPageDTO;
import fun.isite.service.common.db.vo.PageVO;
import fun.isite.service.core.basic.controller.BaseController;
import fun.isite.service.core.system.entity.AdminUser;
import fun.isite.service.core.system.service.IAdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 系统用户 控制层
*
* @author Enigma
*/
@RestController
@RequestMapping("/system/admin/user")
@Tag(name = "系统用户", description = "系统用户相关接口")
@AllArgsConstructor
public class AdminUserController extends BaseController {

    private IAdminUserService service;

    @GetMapping
    @Operation(summary = "系统用户列表")
    @SaCheckPermission("system:user:view")
    public RestResponse<PageVO<AdminUser>> list(SplitPageDTO page,AdminUser params) {
        return RestResponse.ok(this.service.basicPage(page, AdminUser::getCreateTime, (w) -> {
            w.like(params.getPhone()!=null, AdminUser::getPhone, params.getPhone());
            w.like(params.getUsername()!=null, AdminUser::getUsername, params.getUsername());
            w.like(params.getAvatar()!=null, AdminUser::getAvatar, params.getAvatar());
            w.like(params.getSex()!=null, AdminUser::getSex, params.getSex());
            w.like(params.getLoginIp()!=null, AdminUser::getLoginIp, params.getLoginIp());
            w.eq(params.getStatus()!=null, AdminUser::getStatus, params.getStatus());
        }));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取指定ID系统用户的详情")
    public RestResponse<AdminUser> detail(@PathVariable String id) {
        return RestResponse.ok(this.service.detail(id));
    }

    @GetMapping("/{id}/roles")
    @Operation(summary = "获取指定ID系统用户的角色ID列表")
    public RestResponse<List<String>> queryUserRoleIds(@PathVariable String id) {
        return RestResponse.ok(this.service.queryUserRoleIds(id));
    }

    @PostMapping
    @Operation(summary = "创建系统用户")
//    @SaCheckPermission("system:user:add")
    public RestResponse<AdminUser> create(@RequestBody @Validated AdminUser req) {
        return RestResponse.ok(this.service.saveAdminUser(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新指定ID的系统用户")
//    @SaCheckPermission("system:user:edit")
    public RestResponse<AdminUser> update(@PathVariable String id, @RequestBody @Validated AdminUser req) {
        return RestResponse.ok(this.service.updateAdminUser(req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除指定ID的系统用户")
//    @SaCheckPermission("system:user:remove")
    public RestResponse<Void> remove(@PathVariable String id) {
        this.service.removeSingle(id);
        return RestResponse.ok();
    }

    @DeleteMapping
    @Operation(summary = "批量删除指定ID的系统用户")
//    @SaCheckPermission("system:user:remove")
    public RestResponse<Void> remove(@RequestParam("id-list") List<String> idList) {
        this.service.remove(idList);
        return RestResponse.ok();
    }

}
