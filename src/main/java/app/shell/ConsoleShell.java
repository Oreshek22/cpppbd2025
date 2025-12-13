package app.shell;

import app.service.AnimalService;
import app.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleShell {

    private final AnimalService animals;
    private final MessageService i18n;

    public ConsoleShell(AnimalService animals, MessageService i18n) {
        this.animals = animals;
        this.i18n = i18n;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(i18n.msg("app.prompt"));
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            if (line.equals("exit")) {
                System.out.println(i18n.msg("app.exit"));
                return;
            }

            if (line.startsWith("lang ")) {
                String lang = line.substring(5).trim();
                i18n.setLang(lang);
                System.out.println(i18n.msg("lang.current", i18n.getLang()));
                continue;
            }

            if (line.equals("find-all")) {
                System.out.println(i18n.msg("animals.header"));
                animals.findAll().forEach(a -> System.out.println(format(a)));
                continue;
            }

            if (line.startsWith("find ")) {
                String arg = line.substring(5).trim();
                int id;
                try { id = Integer.parseInt(arg); }
                catch (Exception e) {
                    System.out.println(i18n.msg("app.unknown"));
                    continue;
                }

                var opt = animals.findById(id);
                if (opt.isEmpty()) System.out.println(i18n.msg("animal.notfound"));
                else {
                    System.out.println(i18n.msg("animal.one"));
                    System.out.println(format(opt.get()));
                }
                continue;
            }

            System.out.println(i18n.msg("app.unknown"));
        }
    }

    private String format(app.model.Animal a) {
        return i18n.msg("animal.id") + ": " + a.id() + ", " +
                i18n.msg("animal.gender") + ": " + a.gender() + ", " +
                i18n.msg("animal.price") + ": " + a.price();
    }
}
