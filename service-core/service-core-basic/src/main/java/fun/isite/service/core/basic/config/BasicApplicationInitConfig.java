package fun.isite.service.core.basic.config;

import fun.isite.service.core.basic.consts.Scan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 基础通用注册
 * 包括事务开启，缓存开启，扫描配置
 * @author Enigma
 */
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@ComponentScan(Scan.COMPONENTS_SCAN)
@MapperScan(Scan.MAPPER_SCAN)
@Import({
        CorsConfig.class,
        RedisConfig.class,
})
@EnableWebMvc
@Order(1)
public class BasicApplicationInitConfig {
}
