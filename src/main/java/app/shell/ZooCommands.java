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
        String gender = i18n.msg("gender." + a.gender()); // <-- переводим

        return i18n.msg("animal.id") + ": " + a.id() + ", " +
                i18n.msg("animal.typeId") + ": " + a.typeId() + ", " +
                i18n.msg("animal.gender") + ": " + gender + ", " +
                i18n.msg("animal.dob") + ": " + a.dateOfBirth() + ", " +
                i18n.msg("animal.price") + ": " + a.price();
    }



    @ShellMethod(key = "add", value = "Add: add --typeId 1 --gender male --dob 2020-01-01 --price 1000")
    public String add(@ShellOption Integer typeId,
                      @ShellOption String gender,
                      @ShellOption String dob,
                      @ShellOption double price) {
        var ok = animals.add(typeId, gender, java.time.LocalDate.parse(dob), price);
        return ok ? "OK" : "FAIL";
    }

    @ShellMethod(key = "edit", value = "Edit: edit --id 1 --typeId 1 --gender female --dob 2020-01-01 --price 2000")
    public String edit(@ShellOption int id,
                       @ShellOption Integer typeId,
                       @ShellOption String gender,
                       @ShellOption String dob,
                       @ShellOption double price) {
        var ok = animals.edit(id, typeId, gender, java.time.LocalDate.parse(dob), price);
        return ok ? "OK" : "NOT FOUND";
    }

    @ShellMethod(key = "delete", value = "Delete: delete --id 1")
    public String delete(@ShellOption int id) {
        var ok = animals.delete(id);
        return ok ? "OK" : "NOT FOUND";
    }



}

