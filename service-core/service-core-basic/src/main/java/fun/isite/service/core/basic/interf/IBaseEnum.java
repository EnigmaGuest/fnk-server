package fun.isite.service.core.basic.interf;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fun.isite.service.core.basic.serializer.RequestEnumJsonDeserialize;

import java.io.Serializable;

/**
 * @author Enigma
 */
@JsonDeserialize(using = RequestEnumJsonDeserialize.class)
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {
}
