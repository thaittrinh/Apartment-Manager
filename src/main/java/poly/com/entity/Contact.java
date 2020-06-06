package poly.com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	@Column(length = 50 )
	@NotNull
	private String nameApartment;
	
	@Column(length = 12 )
	@NotNull
	private Integer phone;
	
	@Column(length = 100)
	private String email;
	
	@NotNull
	@Column(length = 100)
	private String address;
	
	@Column(length = 100)
	private String bank;
	
	@Column(length = 50)
	private Integer accountNumber;

}
