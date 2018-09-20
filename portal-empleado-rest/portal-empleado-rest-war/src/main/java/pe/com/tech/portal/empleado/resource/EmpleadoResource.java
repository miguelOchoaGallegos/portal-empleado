package pe.com.tech.portal.empleado.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import pe.com.tech.portal.empleado.domain.CuentaBancariaEmpleado;
import pe.com.tech.portal.empleado.domain.DatosPersonalesEmpleado;
import pe.com.tech.portal.empleado.domain.DependientesEmpleado;
import pe.com.tech.portal.empleado.domain.SegurosEmpleado;
import pe.com.tech.portal.empleado.error.HandlerException;
import pe.com.tech.portal.empleado.service.EmpleadoService;


@RequestMapping("/api/empleado")
@RestController
@Api(tags = "Empleado", description = "Realiza las tareas con respecto al usuario")
public class EmpleadoResource {
	
	@Autowired 
	EmpleadoService empleadoService;
	
	@ApiOperation(value = "obtiene datos personales del empleado", notes = "${empleadoresource.obtenerdatospersonales.notes}", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(path = "/obtenerDatosPersonales")
	public ResponseEntity<?> obtenerDatosPersonales() {
		try {
			DatosPersonalesEmpleado response = empleadoService.getDatosPersonalesByUser();			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "obtiene datos de la cuenta bancaria del empleado", notes = "${empleadoresource.obtenerdatoscuentabancaria.notes}", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(path = "/obtenerDatosCuentaBancaria")
	public ResponseEntity<?> obtenerDatosCuentaBancaria() {
		try {
			CuentaBancariaEmpleado response = empleadoService.getDatosCuentaBancariaByUser();			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "obtiene datos de los dependendientes del empleado", notes = "${empleadoresource.obtenerdatosdependientes.notes}", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(path = "/obtenerDatosDependientes")
	public ResponseEntity<?> obtenerDatosDependientes() {
		try {
			List<DependientesEmpleado> response = empleadoService.getDatosDependientesByUser();			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException(e.getMessage());
		}
	}
	
	@ApiOperation(value = "obtiene datos del seguro del empleado", notes = "${empleadoresource.obtenerdatosseguros.notes}", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping(path = "/obtenerDatosSeguros")
	public ResponseEntity<?> obtenerDatosSeguros() {
		try {
			SegurosEmpleado response = empleadoService.getDatosSegurosByUser();			
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerException(e.getMessage());
		}
	}
	
	
}
