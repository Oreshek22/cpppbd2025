package app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animals")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "price")
    private Double price;

    @Column(name = "feeding_id")
    private Integer feedingId;

    @Column(name = "date_of_inspection")
    private LocalDate dateOfInspection;

    public AnimalEntity() {
    }

    // --- getters/setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getTypeId() { return typeId; }
    public void setTypeId(Integer typeId) { this.typeId = typeId; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getFeedingId() { return feedingId; }
    public void setFeedingId(Integer feedingId) { this.feedingId = feedingId; }

    public LocalDate getDateOfInspection() { return dateOfInspection; }
    public void setDateOfInspection(LocalDate dateOfInspection) { this.dateOfInspection = dateOfInspection; }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", price=" + price +
                ", feedingId=" + feedingId +
                ", dateOfInspection=" + dateOfInspection +
                '}';
    }
}
