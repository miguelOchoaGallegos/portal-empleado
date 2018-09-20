package pe.com.tech.portal.empleado.resource;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import pe.com.tech.portal.empleado.bean.UsuarioBean;
import pe.com.tech.portal.empleado.error.HandlerException;
import pe.com.tech.portal.empleado.service.UsuarioService;

@RequestMapping("/api/usuario")
@RestController
@Api(tags = "Usuario", description = "Realiza las tareas con respecto al usuario")
public class UsuarioResource {
	
	@Autowired 
	UsuarioService usuarioService;
	
	@ApiOperation(value = "Genera nueva clave aletoria para usuario", notes = "${usuarioresource.actualizarclave.notes}", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping(path = "/actualizarClave/")
	public ResponseEntity<?> actualizarClave(@Valid @RequestBody UsuarioBean request, BindingResult result) {
		try {
			if(result.hasErrors()) {
				throw new HandlerException(result.getAllErrors().get(0).getDefaultMessage());	
			}			
			Map<String, Object> response = usuarioService.updatePassword(request.getNewPassword());			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "Genera nueva clave aletoria para usuario", notes = "${usuarioresource.generarclavedinamica.notes}", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(path = "/generarClaveDinamica")
	public ResponseEntity<?> generarClaveDinamica() {
		try {
			Map<String, Object> response = usuarioService.updatePassword(null);			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException("Ocurrió un error al generar la clave dinámica.");
		}
	}
}
