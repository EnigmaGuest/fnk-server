package fun.isite.service.api.admin;

import fun.isite.service.core.basic.config.BasicApplicationInitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Enigma
 */
@SpringBootApplication
@Import({BasicApplicationInitConfig.class})
public class AdminApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApiApplication.class, args);
    }
}
