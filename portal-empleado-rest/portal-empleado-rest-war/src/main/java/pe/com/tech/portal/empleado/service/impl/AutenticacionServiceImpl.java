package pe.com.tech.portal.empleado.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tech.portal.empleado.bean.ResponseLoginBean;
import pe.com.tech.portal.empleado.dao.UsuarioDAO;
import pe.com.tech.portal.empleado.domain.Usuario;
import pe.com.tech.portal.empleado.service.AutenticacionService;
import pe.com.tech.portal.empleado.util.Util;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Override
	public ResponseLoginBean autenticarUsuario(String usuario, String clave) {
		// TODO Auto-generated method stub
		ResponseLoginBean response = new ResponseLoginBean();		
		// verify exists username		
		Usuario user = usuarioDAO.getUserByLogin(usuario);
		if(user==null) {
			response.setRpta(Boolean.FALSE.toString());
			response.setMensaje("No se encontró usuario registrado, favor revisar sus credenciales.");
			return response;
		}
		// verify actived
		if(user.getActivo().intValue()==0) {
			response.setRpta(Boolean.FALSE.toString());
			response.setMensaje("Usuario se encuentra inactivo, favor revisar sus credenciales.");
			return response;
		}
		// verifyu pass
		if(Util.verifyPasswordBcrypt(clave, user.getClave())) {
			response.setRpta(Boolean.TRUE.toString());		
			response.setData(user);
			return response;	
		}else {
			response.setRpta(Boolean.FALSE.toString());
			response.setMensaje("Clave inválida, favor revisar sus credenciales.");
			return response;
		}
	}

}
