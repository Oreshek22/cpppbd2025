package app.shell;

import app.model.Animal;
import app.service.AnimalService;
import app.service.MessageService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ZooCommands {

    private final AnimalService animals;
    private final MessageService i18n;

    public ZooCommands(AnimalService animals, MessageService i18n) {
        this.animals = animals;
        this.i18n = i18n;
    }

    @ShellMethod(key = "find-all", value = "Show all animals")
    public String findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append(i18n.msg("animals.header")).append("\n");
        for (Animal a : animals.findAll()) {
            sb.append(format(a)).append("\n");
        }
        return sb.toString();
    }

    @ShellMethod(key = "find", value = "Find animal by id")
    public String find(@ShellOption int id) {
        var opt = animals.findById(id);
        if (opt.isEmpty()) {
            return i18n.msg("animal.notfound");
        }
        return i18n.msg("animal.one") + "\n" + format(opt.get());
    }

    @ShellMethod(key = "lang", value = "Switch language: lang en | lang ru")
    public String lang(@ShellOption String code) {
        i18n.setLang(code);
        return i18n.msg("lang.current", i18n.getLang());
    }



    private String format(Animal a) {
        String gender = i18n.msg("gender." + a.gender());

        return i18n.msg("animal.id") + ": " + a.id() + ", " +
                i18n.msg("animal.gender") + ": " + gender + ", " +
                i18n.msg("animal.price") + ": " + a.price();
    }


}

