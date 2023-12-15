package fun.isite.service.common.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Enigma
 */
@Component
public class MybatisPlusAutoField implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createdAt = metaObject.getValue("createdAt");
        if(createdAt == null){
            this.setFieldValByName("createdAt", new Date(), metaObject);
        }
        this.setFieldValByName("updatedAt", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedAt", new Date(), metaObject);
    }

}
