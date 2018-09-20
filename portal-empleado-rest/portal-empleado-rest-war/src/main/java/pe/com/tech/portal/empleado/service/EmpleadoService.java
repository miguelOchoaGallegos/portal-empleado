package pe.com.tech.portal.empleado.service;

import java.util.List;

import pe.com.tech.portal.empleado.domain.CuentaBancariaEmpleado;
import pe.com.tech.portal.empleado.domain.DatosPersonalesEmpleado;
import pe.com.tech.portal.empleado.domain.DependientesEmpleado;
import pe.com.tech.portal.empleado.domain.SegurosEmpleado;

public interface EmpleadoService {
	public DatosPersonalesEmpleado getDatosPersonalesByUser();
	public CuentaBancariaEmpleado getDatosCuentaBancariaByUser();
	public List<DependientesEmpleado> getDatosDependientesByUser();
	public SegurosEmpleado getDatosSegurosByUser();
}
