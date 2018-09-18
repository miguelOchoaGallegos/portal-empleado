package pe.com.tech.portal.empleado.interfaces;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

import pe.com.tech.portal.empleado.constants.ConstanteGenerica;
import pe.com.tech.portal.empleado.validator.NewPasswordValidator;

@Documented
@Constraint(validatedBy = NewPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NewPasswordConstraint {
	String message() default "La clave ingresada no cumple con los requisitos minimos de seguridad: al menos 1 digito, al menos 1 caracter mayuscula, que la clave tenga 6 caracter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
