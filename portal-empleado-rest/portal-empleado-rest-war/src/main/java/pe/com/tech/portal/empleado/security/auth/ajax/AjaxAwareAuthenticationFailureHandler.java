package pe.com.tech.portal.empleado.security.auth.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import pe.com.tech.portal.empleado.enumerado.ErrorCode;
import pe.com.tech.portal.empleado.error.ErrorResponse;
import pe.com.tech.portal.empleado.security.exceptions.AuthMethodNotSupportedException;
import pe.com.tech.portal.empleado.security.exceptions.JwtExpiredTokenException;

@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper mapper;
	
	@Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());		
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		if (e instanceof BadCredentialsException) {
			mapper.writeValue(response.getOutputStream(), ErrorResponse.of("Usuario o clave inválidos", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));			
			
		} else if (e instanceof JwtExpiredTokenException) {
			mapper.writeValue(response.getOutputStream(), ErrorResponse.of("Token ha expirado", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof AuthMethodNotSupportedException) {
		    mapper.writeValue(response.getOutputStream(), ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}else {
			mapper.writeValue(response.getOutputStream(), ErrorResponse.of("Fallo autentificación. ".concat(e.getMessage().toString()), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));	
		}		
	}
}
