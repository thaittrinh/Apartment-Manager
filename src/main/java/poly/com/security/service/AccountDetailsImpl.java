package poly.com.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import poly.com.entity.Account;

public class AccountDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -7679110777808967378L;

    private int id;
	
	private String username;
	
	@JsonIgnore 
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public AccountDetailsImpl(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	
	public static AccountDetailsImpl build(Account account) {
		/*
		 * you can notice that we convert Set<Role> into List<GrantedAuthority>. 
		 * It is important to work with Spring Security and Authentication object later.
		 */
		List<GrantedAuthority> authorities = account.getRoles().stream()
												.map(role -> new SimpleGrantedAuthority(role.getName().name()))
												.collect(Collectors.toList());
		
		return new AccountDetailsImpl(account.getId(),
				                   account.getUsername(),						  
				                   account.getPassword(),
								   authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}
	
	public long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AccountDetailsImpl acc = (AccountDetailsImpl) o;
		return Objects.equals(id, acc.id);
	}
	
}
