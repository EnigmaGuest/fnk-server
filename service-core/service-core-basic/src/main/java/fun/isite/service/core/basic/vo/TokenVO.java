package fun.isite.service.core.basic.vo;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author Enigma
 */
@Data
@Schema(name = "授权信息")
public class TokenVO implements Serializable {
    /**
     * token名称
     */
    @Schema(name = "token名称")
    public String tokenName;

    /**
     * token值
     */
    @Schema(name = "token值")
    public String tokenValue;

    /**
     * 此token是否已经登录
     */
    @Schema(name = "此token是否已经登录")
    public Boolean isLogin;

    /**
     * 此token对应的LoginId，未登录时为null
     */
    @Schema(name = "此token对应的LoginId，未登录时为null")
    public Object loginId;

    /**
     * 账号类型
     */
    @Schema(name = "账号类型")
    public String loginType;

    /**
     * token剩余有效期 (单位: 秒)
     */
    @Schema(name = "token剩余有效期 (单位: 秒)")
    public long tokenTimeout;

    /**
     * User-Session剩余有效时间 (单位: 秒)
     */
    @Schema(name = "User-Session剩余有效时间 (单位: 秒)")
    public long sessionTimeout;

    /**
     * Token-Session剩余有效时间 (单位: 秒)
     */
    @Schema(name = "Token-Session剩余有效时间 (单位: 秒)")
    public long tokenSessionTimeout;

    /**
     * token剩余无操作有效时间 (单位: 秒)
     */
    @Schema(name = "token剩余无操作有效时间 (单位: 秒)")
    public long tokenActivityTimeout;

    /**
     * 登录设备标识
     */
    @Schema(name = "登录设备标识")
    public String loginDevice;

    /**
     * 自定义数据
     */
    @Schema(name = "自定义数据")
    public String tag;




    public static TokenVO generateFromSaToken(SaTokenInfo info) {
        TokenVO vo = new TokenVO();
        BeanUtils.copyProperties(info, vo);
        return vo;
    }
}
