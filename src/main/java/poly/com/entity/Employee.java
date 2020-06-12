package poly.com.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4548470369384115251L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @NotNull
    @Column(length = 50)
	private String fullName;
	
    @NotNull
    private boolean gender;
    
    @NotNull 
  	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
    private Date birthday;
    
    @Column(length = 8)
    private String indentityCard;
    
    @NotNull
    private String phone;
    
    @NotNull
    private String address;
    
    private String email;
    
    private String image;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
    
}

