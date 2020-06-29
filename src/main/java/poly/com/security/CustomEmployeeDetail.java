package poly.com.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import poly.com.entity.Employee;
import poly.com.entity.Role;

import java.util.Collection;
import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
public class CustomEmployeeDetail implements UserDetails {
    /* -------------------------------------- CustomEmployeeDetail ------------------------------------ */
    Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    public String getFullName() {
        return employee.getFullName();
    }

    public boolean isGender() {
        return employee.isGender();
    }

    public Date getBirthday() {
        return employee.getBirthday();
    }

    public String getIdentityCard() {
        return employee.getIdentitycard();
    }

    public String getPhone() {
        return employee.getPhone();
    }

    public String getAddress() {
        return employee.getAddress();
    }

    public String getEmail() {
        return employee.getEmail();
    }

    public String getImage() {
        return employee.getImage();
    }

    public Set<Role> getRole() { return employee.getRoles(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
