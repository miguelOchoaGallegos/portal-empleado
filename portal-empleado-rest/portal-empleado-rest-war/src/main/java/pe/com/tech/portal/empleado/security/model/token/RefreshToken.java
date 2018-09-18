package pe.com.tech.portal.empleado.security.model.token;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;

import pe.com.tech.portal.empleado.security.model.Scopes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {

	private Jws<Claims> claims;
	
	private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }
	
	/**
     * Creates and validates Refresh token 
     * 
     * @param token
     * @param signingKey
     * 
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     * 
     * @return
     */
    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> scopes = claims.getBody().get("scopes", List.class);
        if (scopes == null || scopes.isEmpty() 
                || !scopes.stream().filter(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }
	
	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}
	public Jws<Claims> getClaims() {
        return claims;
    }
    
    public String getJti() {
        return claims.getBody().getId();
    }
    
    public String getSubject() {
        return claims.getBody().getSubject();
    }

}
