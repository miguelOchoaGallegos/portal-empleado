package pe.com.tech.portal.empleado.security.auth.jwt.verify;

import org.springframework.stereotype.Component;

@Component
public class BloomFilterTokenVerifier implements TokenVerifier{

	@Override
	public boolean verify(String jti) {
		// TODO Auto-generated method stub
		 return true;
	}

}
