package app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("app")
@PropertySource("classpath:db.properties")
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        var ms = new ResourceBundleMessageSource();
        ms.setBasename("messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
