package secondProject.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfHiring;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Nullable
    private LocalDate dateOfFiring;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_photos",
            joinColumns = {
                    @JoinColumn(name = "employee_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "photo_id")
            }
    )
    private Photo photo;

    public Employee(String name, String surname, String email, LocalDate dateOfBirth, LocalDate dateOfHiring, String password, String roles, Photo photo) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHiring = dateOfHiring;
        this.photo = photo;
    }


    public Employee(String name, String surname, String email, LocalDate dateOfBirth, LocalDate dateOfHiring, String password, String roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHiring = dateOfHiring;
        photo = new Photo();
    }


    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }

    public LocalDate getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(LocalDate dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    @Nullable
    public LocalDate getDateOfFiring() {
        return dateOfFiring;
    }

    public void setDateOfFiring(@Nullable LocalDate dateOfFiring) {
        this.dateOfFiring = dateOfFiring;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!Objects.equals(id, employee.id)) return false;
        if (!Objects.equals(name, employee.name)) return false;
        if (!Objects.equals(surname, employee.surname)) return false;
        if (!Objects.equals(email, employee.email)) return false;
        if (!Objects.equals(dateOfBirth, employee.dateOfBirth))
            return false;
        if (!Objects.equals(age, employee.age)) return false;
        if (!Objects.equals(dateOfHiring, employee.dateOfHiring))
            return false;
        if (!Objects.equals(dateOfFiring, employee.dateOfFiring))
            return false;
        return Objects.equals(name, employee.name);
    }

}
