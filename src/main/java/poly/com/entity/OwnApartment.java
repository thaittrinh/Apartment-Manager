package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "OwnApartments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnApartment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(length = 70)
	private String fullname;

	@NotNull
	private Boolean gender;

	@NotNull
	private Date birthday;

	@NotNull
	@Column(length = 20)
	private String nationality;

	@NotNull
	private String birthplace;
	
	@NotNull
	private String job;

	@NotNull
	@Column(length = 12)
	private Integer phone;

	@Column(length = 50)
	private String email;
	
	@NotNull
	private String address;

	private String image;

	@NotNull
	@Column(length = 12)
	private Integer identitycard;

}
