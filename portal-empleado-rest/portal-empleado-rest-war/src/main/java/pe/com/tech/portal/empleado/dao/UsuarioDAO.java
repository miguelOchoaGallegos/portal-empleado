package pe.com.tech.portal.empleado.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tech.portal.empleado.domain.Usuario;
import pe.com.tech.portal.empleado.mappers.UsuarioMapper;

@Service
public class UsuarioDAO {

	@Autowired
	UsuarioMapper usuarioMapper;
	
	public Usuario getUserByLogin(String login) {
		return usuarioMapper.getUserByLogin(login);
	}
	
	public Usuario getUserById(Long idUser) {
		return usuarioMapper.getUserById(idUser);
	}
	
	public void updatePassword(Map<String, Object> params){
		usuarioMapper.updatePassword(params);
	}
}
