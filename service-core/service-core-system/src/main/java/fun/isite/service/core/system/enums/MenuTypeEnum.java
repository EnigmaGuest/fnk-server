package fun.isite.service.core.system.enums;

import fun.isite.service.core.basic.interf.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Enigma
 */
@AllArgsConstructor
@Getter
public enum MenuTypeEnum implements IBaseEnum<String> {
    // （table目录 menu菜单 button按钮）
    TABLE("TABLE", "目录"),
    MENU("MENU", "菜单"),
    BUTTON("BUTTON", "按钮");
    public final String value;
    public final String desc;

}
