package poly.com.security.jwt;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import poly.com.security.service.AccountDetailsImpl;

/**
 * This class has 3 funtions:sử lý jwt 
 * - tạo 1 jwt,
 * - getusername từ 1 chuỗi jwt,
 * - xác nhận 1 jwt
 */
@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret; /// chuỗi này là bí mật thay đổi trong file application.properties
	
	@Value("${bezkoder.app.jwtExpirationMs}")// set timeout cho chuỗi token
	private int jwtExpirationMs;

	//generate a JWT from username, date, expiration, secret
	public String generateJwtToken(Authentication authentication) {
		AccountDetailsImpl accountPrincipal  = (AccountDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((accountPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	//get username from JWT
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	//validate a JWT xác nhận 1 jwt
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
}
