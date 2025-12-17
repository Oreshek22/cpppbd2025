package app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vet")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vet_id")
    private Integer id;

    @Column(name = "fcs")
    private String fcs;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    // В БД колонка называется "experiens" (с опечаткой) — важно повторить точно
    @Column(name = "experiens")
    private Integer experience;

    @Column(name = "ward_class")
    private String wardClass;

    public Vet() {}

    public Integer getId() {
        return id;
    }

    public String getWardClass() {
        return wardClass;
    }

    public void setWardClass(String wardClass) {
        this.wardClass = wardClass;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFcs() {
        return fcs;
    }

    public void setFcs(String fcs) {
        this.fcs = fcs;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Vet{" +
                "id=" + id +
                ", fcs='" + fcs + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", experience=" + experience +
                ", wardClass='" + wardClass + '\'' +
                '}';
    }


    // дальше: геттеры/сеттеры (или Lombok, если вы его используете)
}
