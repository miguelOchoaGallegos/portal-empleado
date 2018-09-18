package pe.com.tech.portal.empleado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pe.com.tech.portal.empleado.aspect.Logging;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)//it's for watching inside AjaxAuthenticationProvider
public class AspectConfig {
    @Bean
    public Logging loggingAspect() {
        return new Logging();
    }
}