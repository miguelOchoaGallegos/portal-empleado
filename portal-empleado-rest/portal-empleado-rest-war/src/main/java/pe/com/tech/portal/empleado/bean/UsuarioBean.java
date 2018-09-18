package pe.com.tech.portal.empleado.bean;

import pe.com.tech.portal.empleado.interfaces.NewPasswordConstraint;

public class UsuarioBean {

	@NewPasswordConstraint
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
