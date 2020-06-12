package poly.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Apartment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apartment implements Serializable {
    @Id
    @Column(length = 10)
    private String id;

    @NotNull
    @Column(length = 6)
    private String password;

    @NotNull
    private Integer acreage;

    @NotNull
    @Column(length = 50)
    private String location;

    @Column(length = 50)
    private String note;

    @NotNull
    @OneToMany
    @JoinColumn(name = "id_own" , referencedColumnName = "id")
    private OwnApartment ownApartment;

}
