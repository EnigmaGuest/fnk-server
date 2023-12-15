package fun.isite.service.api.admin.controller;

import fun.isite.service.common.bean.http.RestResponse;
import fun.isite.service.common.tools.utils.RedisUtils;
import fun.isite.service.core.basic.annotation.AnonymousApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Enigma
 */

@RestController
@RequestMapping("/redis")
@AnonymousApi
@Tag(name = "RedisController", description = "Redis测试")
public class RedisController {

    @GetMapping("/test")
    @Operation(summary = "测试",description = "测试返回数据")
    public RestResponse<Object> test() {

        RedisUtils.getINSTANCE().set("test", "test");

        return RestResponse.ok(new Date());
    }
}
