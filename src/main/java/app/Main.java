package app;

import app.config.AppConfig;
import app.shell.ConsoleShell;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(ConsoleShell.class).run();
        ctx.close();
    }
}
