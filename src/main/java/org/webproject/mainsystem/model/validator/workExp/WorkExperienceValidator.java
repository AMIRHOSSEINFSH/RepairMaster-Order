package org.webproject.mainsystem.model.validator.workExp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.webproject.mainsystem.model.enumData.WorkExperience;

public class WorkExperienceValidator implements ConstraintValidator<ValidWorkExp,Integer> {


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return true;
        return WorkExperience.getWorkExperienceTypeById(value) != null;
    }
}
