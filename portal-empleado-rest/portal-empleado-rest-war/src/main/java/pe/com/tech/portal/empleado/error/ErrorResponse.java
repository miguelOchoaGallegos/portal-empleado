package pe.com.tech.portal.empleado.error;

import java.util.Date;
import org.springframework.http.HttpStatus;

import pe.com.tech.portal.empleado.enumerado.ErrorCode;

public class ErrorResponse {
	 // HTTP Response Status Code
    private final HttpStatus status;
    // General Error message
    private final String mensaje;
    // Error code
    private final ErrorCode codError;
    private final Date timestamp;
    
	public Integer getStatus() {
		return status.value();
	}
	public String getMensaje() {
		return mensaje;
	}
	public ErrorCode getCodError() {
		return codError;
	}
	public Date getTimestamp() {
		return timestamp;
	}
    
	public static ErrorResponse of(final String mensaje, final ErrorCode codError, HttpStatus status) {
	  return new ErrorResponse(mensaje, codError, status);
	}    

    protected ErrorResponse(final String mensaje, final ErrorCode codError, HttpStatus status) {
        this.mensaje = mensaje;
        this.codError = codError;
        this.status = status;
        this.timestamp = new java.util.Date();
    }
    
}
