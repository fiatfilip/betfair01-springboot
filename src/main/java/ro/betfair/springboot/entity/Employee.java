package ro.betfair.springboot.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    @OneToOne
    private EmployeeCard accessCard;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(EmployeeCard accessCard) {
        this.accessCard = accessCard;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
