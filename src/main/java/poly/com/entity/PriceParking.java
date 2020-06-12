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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PriceParking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceParking implements Serializable {

	private static final long serialVersionUID = 2182751926313749043L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Double price;

	@NotNull
	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
	private Date date;

	@NotNull(message = "Employee is not null")
	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id")
	private Employee employee;
	
	@NotNull(message = "Type is not null")
	@ManyToOne
	@JoinColumn(name = "id_type", referencedColumnName = "id")
	private TypeVehicel typeVehicel;
	
	private String note;
}
