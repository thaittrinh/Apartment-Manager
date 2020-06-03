package poly.com.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

	@NotNull
	private int id;
	
	@NotBlank
	@Size(min = 5, max = 20)
	private String username;
	
	@NotBlank
	@Size(min = 6, max = 50)
	private String password;
	
	private Set<String> role;
	
}
