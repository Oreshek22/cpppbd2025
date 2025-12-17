package app.shell;

import app.model.Vet;
import app.service.VetService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class VetCommands {

    private final VetService vetService;

    public VetCommands(VetService vetService) {
        this.vetService = vetService;
    }

    @ShellMethod(key = "vet-list", value = "List all vets")
    public String list() {
        StringBuilder sb = new StringBuilder();

        for (Vet vet : vetService.getAll()) {
            sb.append(vet).append("\n");
        }

        return sb.toString();
    }


    @ShellMethod(key = "vet-get", value = "Get vet by id")
    public Vet get(@ShellOption int id) {
        return vetService.getById(id);
    }

    @ShellMethod(key = "vet-delete", value = "Delete vet by id")
    public String delete(@ShellOption int id) {
        vetService.deleteById(id);
        return "Deleted vet id=" + id;
    }

    @ShellMethod(key = "vet-create", value = "Create vet")
    public String create(
            @ShellOption String fcs,
            @ShellOption String phone,
            @ShellOption String email,
            @ShellOption int experience,
            @ShellOption("ward") String wardClass
    ) {
        Vet vet = new Vet();
        vet.setFcs(fcs);
        vet.setPhoneNumber(phone);
        vet.setEmail(email);
        vet.setExperience(experience);
        vet.setWardClass(wardClass);

        Vet saved = vetService.create(vet);
        return "Created vet id=" + saved.getId();
    }
}
