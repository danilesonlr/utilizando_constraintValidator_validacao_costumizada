package projuris.evento;

import javax.persistence.Entity;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidateEvento.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventoFieldMandatory {
  String message() default "error.validation";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  String mainField();
  String [] dependentFields();
}
