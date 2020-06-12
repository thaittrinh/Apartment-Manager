package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PricesGarbage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceGarbage implements Serializable{
  
	private static final long serialVersionUID = -9162073713048927765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private Double price;

	@NotNull
	@Column(unique = true)	
	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
	private Date date;

	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id", nullable = false)
	private Employee employee;

	private String note;

}
