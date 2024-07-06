package org.webproject.mainsystem.model.validator.workExp;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WorkExperienceValidator.class)
public @interface ValidWorkExp {
    String message() default "Invalid workExperience";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
