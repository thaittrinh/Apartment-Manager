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
@Table(name = "PriceManagements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceManagement implements Serializable {
	
	private static final long serialVersionUID = -235170374690314904L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 

	@NotNull
	private Double price;
	
	@NotNull
	@Column(unique =  true)
	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
	private Date date;

	@ManyToOne
	@JoinColumn(name = "id_employee", referencedColumnName = "id", nullable = false)
	private Employee employee;

	private String note;
}
