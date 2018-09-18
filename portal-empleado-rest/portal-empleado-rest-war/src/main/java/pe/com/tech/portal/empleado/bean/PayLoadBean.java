package pe.com.tech.portal.empleado.bean;

public class PayLoadBean {

	private String sub;
	private String[] scopes;
	private String iss;
	private Long iat;
	private Long exp;
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String[] getScopes() {
		return scopes;
	}
	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public Long getIat() {
		return iat;
	}
	public void setIat(Long iat) {
		this.iat = iat;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	
}
