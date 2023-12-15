package fun.isite.service.core.basic.annotation;

import fun.isite.service.core.basic.enums.Version;

import java.lang.annotation.*;

/**
 * @author Enigma
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiVersion(Version.V2)
public @interface ApiV2 {
}
