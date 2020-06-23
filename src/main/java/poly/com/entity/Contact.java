package poly.com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact implements Serializable {
	
	private static final long serialVersionUID = -3736044155231009975L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100 )
	@NotNull(message = "Name is not null")
	private String name;
	
	@Column(length = 11 )
	@NotNull
	@Pattern(regexp = "[0-9]{9,11}", message = "Phone numbers from 9 to 11 digits long")
	private String phone;
	
	@NotNull(message = "Email is not null")
	@Column(length = 100)
	private String email;
	
	@NotNull(message = "Address is not null")
	@Column(length = 100)
	private String address;
	
	@Column(length = 100)
	@NotNull(message = "Bank is not null")
	private String bank;
	
	@Column(length = 13)
	@NotNull
	@Pattern(regexp = "[0-9]{8,13}", message = "Account number from 8 to 13 digits long")
	private String accountNumber;

}
