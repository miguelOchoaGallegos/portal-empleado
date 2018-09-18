package pe.com.tech.portal.empleado.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

	public static String NUMEROS = "0123456789";	 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
	
	public static String getPassword(String key, int length) {
		String pswd = ""; 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		} 
		return pswd;
	}
	
	
}
