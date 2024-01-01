package fun.isite.service.api.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fun.isite.service.common.bean.http.RestResponse;
import fun.isite.service.core.basic.controller.BaseController;
import fun.isite.service.core.system.entity.SystemMenu;
import fun.isite.service.core.system.service.ISystemMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单 控制层
 *
 * @author Enigma
 */
@RestController
@RequestMapping("/system/menu")
@Tag(name = "系统菜单", description = "系统菜单相关接口")
@Slf4j
public class SystemMenuController extends BaseController {

    @Autowired
    private ISystemMenuService service;

    @GetMapping
    @Operation(summary = "系统菜单列表")
    public RestResponse<List<SystemMenu>> list(SystemMenu params) {
        log.info("params: {}", params);
        LambdaQueryWrapper<SystemMenu> wrapper  = new LambdaQueryWrapper<>();
        wrapper.like(params.getName()!=null, SystemMenu::getName, params.getName());
        wrapper.eq(params.getPermission()!=null, SystemMenu::getPermission, params.getPermission());
        wrapper.orderByAsc(SystemMenu::getOrderSort);
        return RestResponse.ok(this.service.list(wrapper));
    }

    // 根据rootID查询
    @GetMapping("/root/{rootId}")
    @Operation(summary = "根据rootID查询菜单")
    public RestResponse<List<SystemMenu>> listByRootId(@PathVariable String rootId) {
        return RestResponse.ok(this.service.listByRootId(rootId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取指定ID系统菜单的详情")
    public RestResponse<SystemMenu> detail(@PathVariable String id) {
        return RestResponse.ok(this.service.detail(id));
    }

    @PostMapping
    @Operation(summary = "创建系统菜单")
//    @SaCheckPermission("system:menu:add")
    public RestResponse<SystemMenu> create(@RequestBody @Validated SystemMenu req) {
        return RestResponse.ok(this.service.create(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新指定ID的系统菜单")
//    @SaCheckPermission("system:menu:edit")
    public RestResponse<SystemMenu> update(@PathVariable String id, @RequestBody @Validated SystemMenu req) {
        return RestResponse.ok(this.service.update(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除指定ID的系统菜单")
//    @SaCheckPermission("system:menu:remove")
    public RestResponse<Void> remove(@PathVariable String id) {
        this.service.removeSingle(id);
        return RestResponse.ok();
    }

    @DeleteMapping
    @Operation(summary = "批量删除指定ID的系统菜单")
//    @SaCheckPermission("system:menu:remove")
    public RestResponse<Void> remove(@RequestParam("id-list") List<String> idList) {
        this.service.remove(idList);
        return RestResponse.ok();
    }

}
