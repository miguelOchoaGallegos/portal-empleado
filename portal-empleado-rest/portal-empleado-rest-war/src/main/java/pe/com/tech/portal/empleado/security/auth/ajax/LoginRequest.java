package pe.com.tech.portal.empleado.security.auth.ajax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginRequest {

    @ApiModelProperty("${login.credenciales.usuario}")
    private String usuario;
    @ApiModelProperty("${login.credenciales.clave}")
    private String clave;

    @JsonCreator
    public LoginRequest(@JsonProperty("usuario") String usuario, @JsonProperty("clave") String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }


}
