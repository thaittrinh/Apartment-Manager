package poly.com.security;
/*
package poly.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import poly.com.security.jwt.AuthEntryPointJwt;
import poly.com.security.jwt.AuthTokenFilter;
import poly.com.security.service.AccountDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(//cung cấp bảo mật AOP trên các phương thức gồm 3 cái ở dưới
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountDetailsServiceImpl accountDetailsService;

	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/assets/**" ).permitAll()
			.antMatchers("/trang-chu/**").permitAll()
			.antMatchers("/api/auth/signin").permitAll()
			.antMatchers("/api/auth/signup").permitAll()
			.antMatchers("/ui/**").permitAll()
			.antMatchers("/api/**").permitAll()
			//.antMatchers("/api/test/all/**").permitAll()
		   // .antMatchers("/api/test/user").hasAnyRole("USER")
			//.antMatchers("/api/test/admin").hasAnyRole("ADMIN")
			//.antMatchers("/api/test/mod").hasAnyRole("MODERATOR")
			.anyRequest().authenticated();
		//lọc trước
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
*/