package pe.com.tech.portal.empleado.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginResponse {
    private Data data;
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    static class Token {
        @ApiModelProperty("${token.accesstoken}")
        private String accessToken;
        @ApiModelProperty("${token.refreshtoken}")
        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    static class Data {
        @ApiModelProperty("${data.usuario}")
        private String usuario;
        @ApiModelProperty("${data.nombreusuario}")
        private String nombreUsuario;       

        public String getUsuario() {
            return usuario;
        }
        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }
        public String getNombreUsuario() {
            return nombreUsuario;
        }
        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }
      
    }
}
