package com.fastcampus.ch3;

import org.springframework.lang.NonNull;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);  // clazz가 User 또는 그 자손인지 확인
    }

    @Override
    public void validate(Object target, @NonNull Errors errors) {
        System.out.println("UserValidator.validate() is called");

        User user = (User) target;
        String id = user.getId();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if (id == null || id.length() < 5 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[]{"5", "12"}, null);
        }
    }
}