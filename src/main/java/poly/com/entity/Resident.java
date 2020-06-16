package poly.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Date;


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


    @NotNull(message = " hometown can not be null ")
    private String hometown;

    private String job;

    @Column(length = 11 )
    private String phone;

    @Column(length = 50)
    private String email;


    @Column(length = 12)
    private String identitycard;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_apartment" , referencedColumnName = "id")
    private Apartment apartment;
}
