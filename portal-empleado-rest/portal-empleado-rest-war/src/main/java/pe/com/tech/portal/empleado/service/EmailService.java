package pe.com.tech.portal.empleado.service;

import java.io.IOException;

import javax.mail.MessagingException;

import pe.com.tech.portal.empleado.mail.Mail;

public interface EmailService {
	public void sendSimpleMessage(Mail mail) throws MessagingException, IOException;	
}
