package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken  implements Serializable {
  
	private static final long serialVersionUID = 417468717936783546L;

	private static final int EXPIRATION = 60 * 24;
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  
    private String token;
  
    private Date expiryDate;
    
    @OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_employee")
    private Employee employee;
    
}