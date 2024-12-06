package ro.betfair.springboot.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "employees_cards")
public class EmployeeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    private Employee cardHolder;
    private Date issueDate;
}
