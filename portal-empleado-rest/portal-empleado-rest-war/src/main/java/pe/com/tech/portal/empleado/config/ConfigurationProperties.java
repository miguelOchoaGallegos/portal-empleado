package pe.com.tech.portal.empleado.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:///tech/app/portal-empleado/rest/properties/config-${spring.profiles.active}.properties"})
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
