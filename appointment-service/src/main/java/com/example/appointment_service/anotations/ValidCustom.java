package com.example.appointment_service.anotations;

import com.example.appointment_service.utils.CustomValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD,
        ElementType.METHOD,
        ElementType.ANNOTATION_TYPE,
        ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomValidator.class)
public @interface ValidCustom {
    String message() default "{jakarta.validation.constraints.NotBlank.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
