
package poly.com.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Phương pháp này sẽ được kích hoạt bất cứ lúc nào 
 * Người dùng không được xác thực yêu cầu tài nguyên HTTP được bảo mật 
 * và một xác thựcException được ném ra 
 * Lỗi 401 
 * "status": 401,
    "error": "Unauthorized",
    "message": "Error: Unauthorized",
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
}
