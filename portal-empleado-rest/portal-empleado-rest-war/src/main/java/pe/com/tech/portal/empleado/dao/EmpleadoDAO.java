package pe.com.tech.portal.empleado.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tech.portal.empleado.domain.CuentaBancariaEmpleado;
import pe.com.tech.portal.empleado.domain.DatosPersonalesEmpleado;
import pe.com.tech.portal.empleado.domain.DependientesEmpleado;
import pe.com.tech.portal.empleado.domain.SegurosEmpleado;
import pe.com.tech.portal.empleado.mappers.EmpleadoMapper;

@Service
public class EmpleadoDAO {

	@Autowired
	EmpleadoMapper empleadoMapper;
	
	public DatosPersonalesEmpleado getDatosPersonalesByUser(Long idUser){
		return empleadoMapper.getDatosPersonalesByUser(idUser);
	}
	
	public CuentaBancariaEmpleado getDatosCuentaBancariaByUser(Long idUser){
		return empleadoMapper.getDatosCuentaBancariaByUser(idUser);
	}
	
	public List<DependientesEmpleado> getDatosDependientesByUser(Long idUser){
		return empleadoMapper.getDatosDependientesByUser(idUser);
	}
	
	public SegurosEmpleado getDatosSegurosByUser(Long idUser){
		return empleadoMapper.getDatosSegurosByUser(idUser);
	}
	
}
