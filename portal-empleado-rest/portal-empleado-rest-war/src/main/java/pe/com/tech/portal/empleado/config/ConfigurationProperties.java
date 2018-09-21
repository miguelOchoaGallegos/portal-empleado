package pe.com.tech.portal.empleado.config;

import static pe.com.tech.portal.empleado.constants.Perfiles.TEST;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:///tech/app/portal-empleado/rest/properties/config-${spring.profiles.active}.properties"})
@Profile({TEST})
public class ConfigurationProperties {
    
    @Value("${jdbc.jndi}")
    private String stringConneccion;
    
	public String getStringConneccion() {
		return stringConneccion;
	}

	public void setStringConneccion(String stringConneccion) {
		this.stringConneccion = stringConneccion;
	}    
    
}
