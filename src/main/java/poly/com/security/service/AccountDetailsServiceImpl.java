package poly.com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.com.entity.Account;
import poly.com.repository.AccountRepository;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accountRepository.findByUsername(username)
											.orElseThrow(()->  new UsernameNotFoundException("\"User Not Found with username: \" + username"));
		
		return AccountDetailsImpl.build(account);
	}

}
