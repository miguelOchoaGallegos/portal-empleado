package pe.com.tech.portal.empleado.enumerado;

public enum EnumWeb {
	XML_HTTP_REQUEST("XMLHttpRequest"),	
	X_REQUESTED_WITH("X-Requested-With"),
	CONTENT_TYPE("Content-type"),
	CONTENT_TYPE_JSON("application/json");
	
    private final String value;

    private EnumWeb(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
