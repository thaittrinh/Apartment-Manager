package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	private static final long serialVersionUID = 913277483974447771L;

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
	
	private String note;
}
