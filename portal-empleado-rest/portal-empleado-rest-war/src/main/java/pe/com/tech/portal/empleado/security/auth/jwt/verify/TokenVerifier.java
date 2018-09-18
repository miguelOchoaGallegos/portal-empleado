package pe.com.tech.portal.empleado.security.auth.jwt.verify;

public interface TokenVerifier {
	 public boolean verify(String jti);
}
