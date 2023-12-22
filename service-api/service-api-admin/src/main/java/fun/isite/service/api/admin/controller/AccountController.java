package fun.isite.service.api.admin.controller;

import fun.isite.service.common.bean.http.RestResponse;
import fun.isite.service.core.basic.annotation.AnonymousApi;
import fun.isite.service.core.basic.controller.BaseController;
import fun.isite.service.core.basic.vo.TokenVO;
import fun.isite.service.core.system.dto.LoginAdminDTO;
import fun.isite.service.core.system.service.IAdminUserService;
import fun.isite.service.core.system.vo.AdminUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Enigma
 */

@RestController
@RequestMapping("/account")
@Tag(name = "认证接口")
@AllArgsConstructor
@AnonymousApi
public class AccountController extends BaseController {

    private IAdminUserService adminUserService;


    @PostMapping("/login")
    @Operation(summary = "登录")
    public RestResponse<TokenVO> create(@RequestBody @Validated LoginAdminDTO dto) {
        return RestResponse.ok(adminUserService.login(dto));
    }

    // 获取当前登录用户
     @GetMapping("/admin")
    @Operation(summary = "获取当前登录用户")
    public RestResponse<AdminUserVO> get() {
        return RestResponse.ok(adminUserService.getCurrentAdminInfo(this.authId()));
    }


}
