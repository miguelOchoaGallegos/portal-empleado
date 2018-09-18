package pe.com.tech.portal.empleado.security.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.com.tech.portal.empleado.security.auth.jwt.extractor.TokenExtractor;
import pe.com.tech.portal.empleado.security.config.JwtSettings;
import pe.com.tech.portal.empleado.security.config.WebSecurityConfig;
import pe.com.tech.portal.empleado.security.exceptions.InvalidJwtToken;
import pe.com.tech.portal.empleado.security.model.UserContext;
import pe.com.tech.portal.empleado.security.model.token.JwtToken;
import pe.com.tech.portal.empleado.security.model.token.JwtTokenFactory;
import pe.com.tech.portal.empleado.security.model.token.RawAccessJwtToken;
import pe.com.tech.portal.empleado.security.model.token.RefreshToken;


@RestController
public class RefreshTokenEndpoint {
	@Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;        
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;    
    
    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {    	
    	
    	String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());        

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        UserContext userContext = UserContext.create(refreshToken.getSubject(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
