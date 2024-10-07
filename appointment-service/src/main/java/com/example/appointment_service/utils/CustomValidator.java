package com.example.appointment_service.utils;

import com.example.appointment_service.anotations.ValidCustom;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<ValidCustom,String> {

    @Override
    public void initialize(ValidCustom constraintAnnotation) {
        // Gerekirse başlangıçta yapılacak işlemler
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Geçerli bir değerin nasıl kontrol edileceğini tanımlayın
       if (value == null || value.isEmpty()) {
            return false; // Değer boşsa geçersiz kabul edilir
        }
       return true;
    }

}
