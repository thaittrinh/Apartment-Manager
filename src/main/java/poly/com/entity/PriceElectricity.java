package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "PricesElectricity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceElectricity implements Serializable {
	
 static final long serialVersionUID = -1327517206306173846L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Min(value = 1, message = "Limit must be greater than 1" )
	private Integer limits;

	@NotNull
	@Min(value = 0, message = "Price must be greater than 0" )
	private Double price;

	@NotNull(message = "Date is not null")
	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@NotNull(message = "Employee is not null")
	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id")
	private Employee employee;
	
	@Size( max = 255, message = "The note length is less than or equal to 50 characters")
	private String note;
}
