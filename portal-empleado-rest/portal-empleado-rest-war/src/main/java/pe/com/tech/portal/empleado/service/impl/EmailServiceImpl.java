package pe.com.tech.portal.empleado.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import pe.com.tech.portal.empleado.mail.Mail;
import pe.com.tech.portal.empleado.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender emailSender;    
	
	@Autowired
    private SpringTemplateEngine templateEngine;
    
	@Override
	public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        helper.addAttachment("logo.png", new ClassPathResource("logo-tech.png"));
        
        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process(mail.getTemplate(), context);
        
        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);


	}

}
