package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Residents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident implements Serializable {

	private static final long serialVersionUID = -2874673172145085128L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "fullname can  not  be null ")
    @Column(length = 50)
    private String fullname;

    @NotNull(message = "gender can not be  null ")
    private Boolean gender;

    @NotNull(message = "birthday can not be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") //MM/dd/yyyy
    private Date birthday;

    @NotNull(message = "nationality can not bư null")
    @Column(length = 20)
    private String nationality;

    @NotNull(message = " hometown can not be null ")
    private String hometown;

    @NotNull(message = "job can not be null")
    private String job;


    @Column(length = 11 )
    @Pattern(regexp = "[0-9]{9,11}", message = "Phone numbers from 6 to 11 digits long")
    private String phone;

    @Column(length = 50)
    private String email;


    @Column(length = 12)
    @Pattern(regexp = "[0-9]{9,12}", message = "Identitycard from 9 to 12 digits long")
    private String identitycard;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_apartment" , referencedColumnName = "id")
    private Apartment apartment;
}
