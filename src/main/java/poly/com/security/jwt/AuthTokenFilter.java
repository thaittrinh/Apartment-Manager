
package poly.com.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import poly.com.security.service.AccountDetailsServiceImpl;

	/*
	 * Class define a filter that executes once per request. 
	 */
	  
public class AuthTokenFilter extends OncePerRequestFilter{

	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AccountDetailsServiceImpl accountDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = parseJwt(request);
			
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) { // xác nhận 1 jwt
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				
				//Tìm user theo username and nạp user cho UserDetails
				UserDetails userDetails = accountDetailsService.loadUserByUsername(username);
				
				//from username, get UserDetails to create an Authentication object
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {	
			logger.error("Cannot set user authentication: {}", e);
		}
		//doFilter bạn đang chuyển yêu cầu / phản hồi cho bộ lọc tiếp theo trong chuỗi bộ lọc của bạn
		filterChain.doFilter(request, response);
	}
	
	//Return JWT from the Authorization header and remove Bearer prefix
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");// get header kèm chuỗi token có tên là Authorization

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			//cắt bỏ "Bearer"
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}


}

