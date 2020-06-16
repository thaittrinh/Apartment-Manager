package poly.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {

    private static final long serialVersionUID = -7051941017444489454L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "licensePlates vehicle can not be null" )
    @Column(length = 20)
    private String licensePlates;

    @NotNull(message = " color vehicle can not be null" )
    @Column(length = 20)
    private String color;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_resident" , referencedColumnName = "id")
    private Resident resident;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_typevehicle" , referencedColumnName = "id")
    private TypeVehicel typeVehicle;

 
}
