package pe.com.tech.portal.empleado.swagger;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import pe.com.tech.portal.empleado.security.auth.ajax.LoginRequest;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

public class SwaggerManualApiPlugin implements ApiListingScannerPlugin {
    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return new ArrayList<>(
                Collections.singletonList(
                        new ApiDescription(
                                "/api/auth/login",
                                "Generar JWT Token",
                                Collections.singletonList(
                                        new OperationBuilder(
                                                new CachingOperationNameGenerator())
                                                .authorizations(new ArrayList())
                                                .codegenMethodNameStem("basicAuth0001")
                                                .method(HttpMethod.POST)
                                                .notes("Endpoint responsable de la Autenticación")
                                                .parameters(
                                                        Arrays.asList(
                                                                new ParameterBuilder()
                                                                        .description("X-Requested-With")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("X-Requested-With")
                                                                        .defaultValue("XMLHttpRequest")
                                                                        .parameterType("header")
                                                                        .parameterAccess("access")
                                                                        .hidden(true)
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string"))
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("Content-Type")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("Content-Type")
                                                                        .defaultValue("application/json")
                                                                        .parameterType("header")
                                                                        .parameterAccess("access")
                                                                        .hidden(true)
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string"))
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("Cache-Control")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("Cache-Control")
                                                                        .defaultValue("no-cache")
                                                                        .parameterType("header")
                                                                        .parameterAccess("access")
                                                                        .hidden(true)
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string"))
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("Credenciales")
                                                                        .type(new TypeResolver().resolve(LoginRequest.class))
                                                                        .name("Credenciales")
                                                                        .parameterType("body")
                                                                        .parameterAccess("access")
                                                                        .modelRef(new ModelRef("LoginRequest"))
                                                                        .required(true)
                                                                        .build()))
                                                .tags(new HashSet<>(Collections.singletonList("Login")))
                                                .responseMessages(getDefaultResponseMessages())
                                                .build()),
                                false)));
    }

    private Set<ResponseMessage> getDefaultResponseMessages() {
        Set<ResponseMessage> defaultResponseMessages = Sets.newHashSet();
        defaultResponseMessages.add(new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), new ModelRef("LoginResponse"), new HashMap<>(), new ArrayList<>()));
        defaultResponseMessages.add(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), null, new HashMap<>(), new ArrayList<>()));
        defaultResponseMessages.add(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, new HashMap<>(), new ArrayList<>()));
        defaultResponseMessages.add(new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null, new HashMap<>(), new ArrayList<>()));
        defaultResponseMessages.add(new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null, new HashMap<>(), new ArrayList<>()));
        return defaultResponseMessages;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
