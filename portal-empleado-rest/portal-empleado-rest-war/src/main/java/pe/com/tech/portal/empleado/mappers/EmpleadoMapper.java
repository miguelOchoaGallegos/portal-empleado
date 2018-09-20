package pe.com.tech.portal.empleado.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import pe.com.tech.portal.empleado.domain.CuentaBancariaEmpleado;
import pe.com.tech.portal.empleado.domain.DatosPersonalesEmpleado;
import pe.com.tech.portal.empleado.domain.DependientesEmpleado;
import pe.com.tech.portal.empleado.domain.SegurosEmpleado;

@Repository
@Mapper
public interface EmpleadoMapper {
	public DatosPersonalesEmpleado getDatosPersonalesByUser( @Param("idUser") Long idUser);
	public CuentaBancariaEmpleado getDatosCuentaBancariaByUser( @Param("idUser") Long idUser);
	public List<DependientesEmpleado> getDatosDependientesByUser( @Param("idUser") Long idUser);
	public SegurosEmpleado getDatosSegurosByUser( @Param("idUser") Long idUser);
}
