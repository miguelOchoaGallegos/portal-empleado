package pe.com.tech.portal.empleado.service;

import java.util.Map;

import pe.com.tech.portal.empleado.security.auth.JwtAuthenticationToken;

public interface UsuarioService {
	Map<String, Object> updatePassword(String newPassword);	
}
