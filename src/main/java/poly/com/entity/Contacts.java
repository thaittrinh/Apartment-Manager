package poly.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contacts {
	
	// < --------------------------------------------- Class Entity Contacts ----------------------------------------------->
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(length = 12 )
@NotNull
private int phone;

@Column(length = 50)
private String email;

@NotNull
private String address;

private String bank;

@Column(length = 50)
private int accountNumber;

}
