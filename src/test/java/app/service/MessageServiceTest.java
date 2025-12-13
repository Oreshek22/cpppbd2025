package app.service;

import app.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    @Test
    void switchesLanguage() {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        var ms = ctx.getBean(MessageService.class);

        ms.setLang("en");
        assertTrue(ms.msg("app.exit").toLowerCase().contains("exit"));

        ms.setLang("ru");
        assertTrue(ms.msg("app.exit").contains("Выход"));

        ctx.close();
    }
}
