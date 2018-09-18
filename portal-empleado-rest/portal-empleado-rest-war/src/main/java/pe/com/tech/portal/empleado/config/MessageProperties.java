package pe.com.tech.portal.empleado.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:///tech/app/portal-empleado/rest/properties/messages.properties"})
public class MessageProperties {

	@Value("${cambio.clave.success}")
    private String cambioClave;

	public String getCambioClave() {
		return cambioClave;
	}

	public void setCambioClave(String cambioClave) {
		this.cambioClave = cambioClave;
	}
	
	
	
}
