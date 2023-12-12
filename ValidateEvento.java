package projuris.evento;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import projuris.mvc.ValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidateEvento implements ConstraintValidator<EventoFieldMandatory, Object> {
  private String mainField;
  private String[] dependentFields;

  @Override
  public void initialize(EventoFieldMandatory constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    mainField = constraintAnnotation.mainField();
    dependentFields = constraintAnnotation.dependentFields();
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(obj);

    Object mainFieldValue = beanWrapper.getPropertyValue(mainField);

    if (mainFieldValue == null || mainFieldValue.toString().isEmpty()) {
      return true;
    }

    for (String dependentField : dependentFields) {
      Object dependentFieldValue = beanWrapper.getPropertyValue(dependentField);
      if (dependentFieldValue == null || dependentFieldValue.toString().isEmpty()) {
        return false;
      }
    }
    return true;
  }
}
