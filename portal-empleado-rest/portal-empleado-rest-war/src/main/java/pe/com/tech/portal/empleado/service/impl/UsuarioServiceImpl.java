package pe.com.tech.portal.empleado.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import pe.com.tech.portal.empleado.config.MessageProperties;
import pe.com.tech.portal.empleado.constants.ConstanteGenerica;
import pe.com.tech.portal.empleado.dao.UsuarioDAO;
import pe.com.tech.portal.empleado.domain.Usuario;
import pe.com.tech.portal.empleado.mail.Mail;
import pe.com.tech.portal.empleado.service.EmailService;
import pe.com.tech.portal.empleado.service.UsuarioService;
import pe.com.tech.portal.empleado.util.PasswordGenerator;
import pe.com.tech.portal.empleado.util.Util;


@Service
public class UsuarioServiceImpl implements UsuarioService {		 
	
	@Autowired
	PasswordGenerator passwordGenerator;	
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired
	MessageProperties messageProperties;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
    private HttpServletRequest request;
	
	@Override
	public Map<String, Object> updatePassword(String newPassword) {
		// TODO Auto-generated method stub
		Map<String, Object> response = new HashMap<String, Object>();		
		Map<String, Object> params = new HashMap<String, Object>();
		try {			
			
			Usuario userCurrent = usuarioDAO.getUserByLogin(Util.getUserNameCurrent(request));
			newPassword = newPassword==null ? PasswordGenerator.getPassword(ConstanteGenerica.UpdatePassword.MAXLENGTHDEFAULT) : newPassword;
			
			params.put(ConstanteGenerica.UpdatePassword.NEWPASSWORD, Util.decodePasswordBcrypt(newPassword));
			params.put(ConstanteGenerica.UpdatePassword.USER_CURRENT, userCurrent.getIdUser());			
			
			usuarioDAO.updatePassword(params);			
			
			response.put(ConstanteGenerica.ResponseService.RESPONSE , Boolean.TRUE.toString() );
			response.put(ConstanteGenerica.ResponseService.MESSAGE , messageProperties.getCambioClave() + newPassword);
			//SendMailUpdatePassword(userCurrent,newPassword);
			
		} catch (Exception e) {
			// TODO: handle exception
			response.put(ConstanteGenerica.ResponseService.RESPONSE , Boolean.FALSE.toString() );
			response.put(ConstanteGenerica.ResponseService.MESSAGE , e.getMessage());
		}
		
		
		return response;
	}
	
	
	@Async
	private void SendMailUpdatePassword(Usuario user, String newPassword) throws Exception{
		Mail mail = new Mail();				
		
		mail.setTemplate("MailUpdatePassword");
		mail.setFrom("miguel.ochoa.gallegos@gmail.com");
        mail.setTo(user.getCorreo());
        mail.setSubject("Actualización de clave de acceso");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", user.getNombres());
        model.put("newPassword", newPassword);        
        mail.setModel(model);
		
		emailService.sendSimpleMessage(mail);
	}


	

}
