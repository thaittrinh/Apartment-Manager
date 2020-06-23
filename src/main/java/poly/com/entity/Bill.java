package poly.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	@Id
	@Column(name = "id")	
    private Integer id;
	
	@NotNull(message = "Electricity number is not null") 
	private Integer electricityNumber;
	
	@NotNull(message = "Electricity price is not null") 
	private Double electricityPrice;
	
	@NotNull(message = "Water number is not null") 
	private Integer waterNumber;
	
	@NotNull(message = "Water price is not null") 
	private Integer waterPrice;
	
	@NotNull(message = "Bicycle number is not null") 
	private Integer bicycleNumber;
	
	@NotNull(message = "Bicycle price is not null") 
	private Integer bicyclePrice;
	
	@NotNull(message = "Motocycle number is not null") 
	private Integer motocycleNumber;
	
	@NotNull(message = "Motocycle price is not null") 
	private Integer motocyclePrice;
	
	@NotNull(message = "Car number is not null") 
	private Integer carNumber;
	
	@NotNull(message = "Car price is not null") 
	private Integer carPrice;
	
	@NotNull(message = "Management fee is not null") 
	private Integer managementPrice;
	
	@NotNull(message = "Total price is not null") 
	private Integer totalPrice;

	@NotNull
	private Boolean paid;
	
	@OneToOne()
	@MapsId()
	private ApartmentIndex apartmentIndex;

	
}
