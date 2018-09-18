package pe.com.tech.portal.empleado.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import pe.com.tech.portal.empleado.domain.Usuario;

@Repository
@Mapper
public interface UsuarioMapper {
	public Usuario getUserByLogin( @Param("inLogin") String login);
	public Usuario getUserById(@Param("inIdUser") Long idUser);
	public void updatePassword(Map<String, Object> params);
}
