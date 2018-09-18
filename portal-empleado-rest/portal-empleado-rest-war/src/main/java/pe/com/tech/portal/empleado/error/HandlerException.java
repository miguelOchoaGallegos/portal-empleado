package pe.com.tech.portal.empleado.error;

public class HandlerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public HandlerException(String errorDescription) {
        super(errorDescription);
    }

    public HandlerException(String mensaje, Throwable e) {
        super(mensaje, e);
    }
}
