package pe.com.tech.portal.empleado.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pe.com.tech.portal.empleado.constants.ConstanteGenerica;
import pe.com.tech.portal.empleado.interfaces.NewPasswordConstraint;

public class NewPasswordValidator implements ConstraintValidator<NewPasswordConstraint, String> {

	@Override
	public void initialize(NewPasswordConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String newPassTest, ConstraintValidatorContext cxt) {
		// TODO Auto-generated method stub
		 Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		 
		if(newPassTest==null) return false;
		if(newPassTest.trim().length()<=ConstanteGenerica.UpdatePassword.MAXLENGTHDEFAULT) return false;		
		if(!newPassTest.chars().anyMatch(Character::isLetterOrDigit)) return false;		
		if(!newPassTest.chars().anyMatch(Character::isUpperCase)) return false;
		
		Matcher hasSpecial = special.matcher(newPassTest);		
		if(!hasSpecial.find()) return false;
		
		return true;
	}

}
