package pe.com.tech.portal.empleado.service;

import pe.com.tech.portal.empleado.bean.ResponseLoginBean;

public interface AutenticacionService  {
	public ResponseLoginBean autenticarUsuario(String usuario, String clave);
}
