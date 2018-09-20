package pe.com.tech.portal.empleado.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tech.portal.empleado.dao.EmpleadoDAO;
import pe.com.tech.portal.empleado.dao.UsuarioDAO;
import pe.com.tech.portal.empleado.domain.CuentaBancariaEmpleado;
import pe.com.tech.portal.empleado.domain.DatosPersonalesEmpleado;
import pe.com.tech.portal.empleado.domain.DependientesEmpleado;
import pe.com.tech.portal.empleado.domain.SegurosEmpleado;
import pe.com.tech.portal.empleado.domain.Usuario;
import pe.com.tech.portal.empleado.service.EmpleadoService;
import pe.com.tech.portal.empleado.util.Util;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoDAO empleadoDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
    private HttpServletRequest request;
	
	@Override
	public DatosPersonalesEmpleado getDatosPersonalesByUser() {
		// TODO Auto-generated method stub		
		Usuario userCurrent = usuarioDAO.getUserByLogin(Util.getUserNameCurrent(request));
		return empleadoDAO.getDatosPersonalesByUser(userCurrent.getIdUser());
	}

	@Override
	public CuentaBancariaEmpleado getDatosCuentaBancariaByUser() {
		// TODO Auto-generated method stub
		Usuario userCurrent = usuarioDAO.getUserByLogin(Util.getUserNameCurrent(request));
		return empleadoDAO.getDatosCuentaBancariaByUser(userCurrent.getIdUser());
	}

	@Override
	public List<DependientesEmpleado> getDatosDependientesByUser() {
		// TODO Auto-generated method stub
		Usuario userCurrent = usuarioDAO.getUserByLogin(Util.getUserNameCurrent(request));
		return empleadoDAO.getDatosDependientesByUser(userCurrent.getIdUser());
	}

	@Override
	public SegurosEmpleado getDatosSegurosByUser() {
		// TODO Auto-generated method stub
		Usuario userCurrent = usuarioDAO.getUserByLogin(Util.getUserNameCurrent(request));
		return empleadoDAO.getDatosSegurosByUser(userCurrent.getIdUser());
	}

}
