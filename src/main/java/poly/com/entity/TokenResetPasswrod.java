package poly.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResetPasswrod implements Serializable {

    private static final long serialVersionUID = 417468717936783546L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private Date expiryDate;

    @OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_employee")
    private Employee employee;

    public TokenResetPasswrod(Employee employee) {
        this.employee = employee;
        expiryDate = new Date();
        token = UUID.randomUUID().toString();
    }
}