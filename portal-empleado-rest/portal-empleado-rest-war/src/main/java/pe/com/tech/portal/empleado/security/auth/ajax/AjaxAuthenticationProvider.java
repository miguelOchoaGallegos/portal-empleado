package pe.com.tech.portal.empleado.security.auth.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import pe.com.tech.portal.empleado.bean.ResponseLoginBean;
import pe.com.tech.portal.empleado.error.HandlerException;
import pe.com.tech.portal.empleado.security.model.UserContext;
import pe.com.tech.portal.empleado.service.AutenticacionService;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AutenticacionService autenticacionService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        Assert.notNull(authentication, "No se envio la información necesaria.");
        UsernamePasswordAuthenticationToken token;
        final Map<String, Object> details = new HashMap<>();
        ResponseLoginBean responseAuten = new ResponseLoginBean();        
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        // validar si existe usuario / validar la clave del usuario       
        responseAuten = autenticacionService.autenticarUsuario(username, password);
        if (responseAuten.getRpta().equals(Boolean.FALSE.toString())) {
        	token = new UsernamePasswordAuthenticationToken(null, null, null);
        	details.put("error", responseAuten.getMensaje());
        	token.setDetails(details);
        	return token;
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        UserContext userContext = UserContext.create(responseAuten.getData().getLogin(), authorities);

        details.put("idusuario", responseAuten.getData().getIdUser());
        details.put("usuario", responseAuten.getData().getLogin() );
        details.put("nombreUsuario", responseAuten.getData().getNombres().concat(" ").concat(responseAuten.getData().getApellidos()));
        details.put("correo", responseAuten.getData().getCorreo());

        token = new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());        
        token.setDetails(details);
        return token;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
