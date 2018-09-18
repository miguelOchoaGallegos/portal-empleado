package pe.com.tech.portal.empleado.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"file:///tech/app/portal-empleado/rest/properties/swagger.properties"})
public class SwaggerProperties {

}
