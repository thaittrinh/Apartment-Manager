package poly.com.security.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 8 ,max = 24, message = "Password from 8 to 24 characters!")
    private String password;

    @NotNull
    @Size(min = 8 ,max = 24, message = "Password from 8 to 24 characters!")
    private String newpassword;
}
