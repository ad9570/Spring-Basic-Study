package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);	// clazz가 User 혹은 그 자손
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("GlobalValidator.validate() is called");
//		if (target instanceof User) return;	// supports()가 이 역할을 대신 수행
		
		User user = (User)target;
		
		String id = user.getId();
		
//		if(id == null || "".equals(id.trim()))	// trim() : 공백 제거
//			errors.rejectValue("id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");	//위와 같은 의미
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if(id.length() < 5 || id.length() > 12)
			errors.rejectValue("id", "invalidLength", new String[] {"5", "12"}, null);
	}

}
