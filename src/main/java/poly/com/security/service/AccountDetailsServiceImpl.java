
package poly.com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.com.entity.Employee;
import poly.com.repository.EmployeeRepository;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee account = employeeRepository.findByUsername(username)
											.orElseThrow(()->  new UsernameNotFoundException("\"User Not Found with username: \" + username"));
		
		return AccountDetailsImpl.build(account);
	}

}
