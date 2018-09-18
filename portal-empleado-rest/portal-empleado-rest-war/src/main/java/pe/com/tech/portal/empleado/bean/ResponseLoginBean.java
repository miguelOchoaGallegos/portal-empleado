package pe.com.tech.portal.empleado.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import pe.com.tech.portal.empleado.domain.Usuario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseLoginBean {

	private String rpta;
	private String mensaje;
	private Usuario data;

	public ResponseLoginBean() {
	}

	public ResponseLoginBean(String rpta, String mensaje) {
		this.rpta = rpta;
		this.mensaje = mensaje;
	}

	public String getRpta() {
		return rpta;
	}
	public void setRpta(String rpta) {
		this.rpta = rpta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Usuario getData() {
		return data;
	}
	public void setData(Usuario data) {
		this.data = data;
	}
	
}
