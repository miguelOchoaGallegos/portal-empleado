package pe.com.tech.portal.empleado.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pe.com.tech.portal.empleado.security.auth.ajax.LoginRequest;
import pe.com.tech.portal.empleado.swagger.LoginResponse;
import pe.com.tech.portal.empleado.swagger.SwaggerManualApiPlugin;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static pe.com.tech.portal.empleado.constants.Perfiles.DEV;
import static pe.com.tech.portal.empleado.constants.Perfiles.TEST;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Profile({DEV, TEST})
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pe.com.tech.portal.empleado.resource"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .additionalModels(new TypeResolver().resolve(LoginRequest.class),
                        new TypeResolver().resolve(LoginResponse.class))
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                //Tag correspondiente al path de Authenticacion 
                .tags(new Tag("Login", "Autenticación del Aplicativo portal empleado", 1));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Servicios : API REST - Portal empleados")
                .description("Documentación de los servicios destinados al portal empleados")
                .version("1.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .contact(new Contact("Miguel Ochoa Gallegos", "www.tech.com.pe", "miguel.ochoa.gallegos@gmail.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "Header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex("/anyPath.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

    @Bean
    public ApiListingScannerPlugin listingScanner() {
        return new SwaggerManualApiPlugin();
    }
}
