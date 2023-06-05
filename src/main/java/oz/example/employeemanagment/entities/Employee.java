package oz.example.employeemanagment.entities;



import jakarta.persistence.*;
import lombok.*;

@Table(name = "employees")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "id_number", nullable = false)
    private int idNumber;

    @Column(name = "salary", nullable = false)
    private double salary;



}
