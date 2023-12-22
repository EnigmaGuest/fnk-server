package fun.isite.service.core.basic.enums;

import fun.isite.service.core.basic.interf.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Enigma
 */
@AllArgsConstructor
@Getter
public enum GenderType implements IBaseEnum<String> {

    // 性别 男 女
    MAN("0"),
    WOMAN("1"),
    OTHER("2");

    private final String value;
}
