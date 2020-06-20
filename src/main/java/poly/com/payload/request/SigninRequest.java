package poly.com.payload.request;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

	@NotNull
	private int id;
	
	@NotBlank
	@Size(max = 50)
	private String fullName;

    private boolean gender;

	@Temporal(TemporalType.DATE)	 
  	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

	@NotBlank
	@Pattern(regexp = "[0-9]{9,12}", message = "Identitycard from 9 to 12 digits long")
    private String indentitycard;

	@NotBlank
	@Pattern(regexp = "[0-9]{9,11}", message = "Phone numbers from 6 to 11 digits long")
    private String phone;
    
	@NotBlank
    private String address;
    
    private String email;

    private String image;
	
	@NotBlank
	@Size(min = 5, max = 20)
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 50)
	private String password;
	
	private Set<String> role;
	
}
