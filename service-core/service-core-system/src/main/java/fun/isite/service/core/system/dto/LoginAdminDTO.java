package fun.isite.service.core.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * @author Enigma
 */
@Schema(description = "登录信息")
public record LoginAdminDTO(@NotBlank(message = "手机号码不能为空")@Schema(description = "手机号码") String phone,
                            @NotBlank(message = "密码不能为空")@Schema(description = "登录密码")  String password,
                            String ip) {

}
