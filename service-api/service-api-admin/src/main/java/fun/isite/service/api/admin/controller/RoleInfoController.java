package fun.isite.service.api.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.isite.service.core.system.service.IRoleInfoService;
import fun.isite.service.core.system.entity.RoleInfo;
import fun.isite.service.common.db.dto.SplitPageDTO;
import fun.isite.service.common.bean.http.RestResponse;
import fun.isite.service.common.db.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import fun.isite.service.core.basic.controller.BaseController;

import java.util.List;

/**
 * 角色信息 控制层
 *
 * @author Enigma
 */
@RestController
@RequestMapping("/system/role")
@Tag(name = "角色信息", description = "角色信息相关接口")
public class RoleInfoController extends BaseController {

    @Autowired
    private IRoleInfoService service;

    @GetMapping
    @Operation(summary = "角色信息列表")
    public RestResponse<PageVO<RoleInfo>> list(SplitPageDTO page, RoleInfo params) {
        return RestResponse.ok(this.service.basicPage(page, RoleInfo::getRoleScope, (w) -> {
            w.like(params.getRoleName() != null, RoleInfo::getRoleName, params.getRoleName());
            w.like(params.getRoleKey() != null, RoleInfo::getRoleKey, params.getRoleKey());
            w.eq(params.getStatus() != null, RoleInfo::getStatus, params.getStatus());
        }));
    }

    @GetMapping("/all")
    @Operation(summary = "角色信息列表")
    public RestResponse<List<RoleInfo>> listAll() {
        return RestResponse.ok(this.service.list(new LambdaQueryWrapper<RoleInfo>().eq(RoleInfo::getStatus, true)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取指定ID角色信息的详情")
    public RestResponse<RoleInfo> detail(@PathVariable String id) {
        return RestResponse.ok(this.service.queryDetail(id));
    }

    @GetMapping("/{id}/menus")
    @Operation(summary = "获取指定角色ID的菜单ID列表")
    public RestResponse<List<String>> queryRoleMenuIds(@PathVariable String id) {
        return RestResponse.ok(this.service.queryRoleMenuIds(id));
    }

    @PostMapping
    @Operation(summary = "创建角色信息")
    public RestResponse<RoleInfo> create(@RequestBody @Validated RoleInfo req) {
        return RestResponse.ok(this.service.saveRole(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新指定ID的角色信息")
    public RestResponse<RoleInfo> update(@PathVariable String id, @RequestBody @Validated RoleInfo req) {
        return RestResponse.ok(this.service.updateRole(req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除指定ID的角色信息")
    public RestResponse<Integer> remove(@PathVariable String id) {

        return RestResponse.ok(this.service.deleteByRoleId(id));
    }

    @DeleteMapping
    @Operation(summary = "批量删除指定ID的角色信息")
    public RestResponse<Void> remove(@RequestParam("id-list") List<String> idList) {
        this.service.remove(idList);
        return RestResponse.ok();
    }

}
