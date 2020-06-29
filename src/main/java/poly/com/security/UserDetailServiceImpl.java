package poly.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poly.com.entity.Employee;
import poly.com.repository.EmployeeRepository;
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    /* ------------------------------------- Class EmployeeDetailService -------------------------------------*/
    @Autowired
    EmployeeRepository employeeRepository;

    /* ------------------------------------------------*/


    /*------------- Load employee by username------------------------ */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username).orElse(null);
        if (employee == null)
            throw new UsernameNotFoundException(username);
        
        return new UserDetailsImpl(employee);

    }

}