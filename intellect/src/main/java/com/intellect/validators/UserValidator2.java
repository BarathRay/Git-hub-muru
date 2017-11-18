package com.intellect.validators;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intellect.modal.User;

public class UserValidator2 implements Validator {

	private Pattern pattern;  
	private Matcher matcher;  

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  


	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		User objin=(User) obj;
		
		if (objin.getId() == null || objin.getId() == "") {  
			
			errors.rejectValue("id", "user.id",  
					"Enter valid ID");  
		}
		
			if (objin.getBirthDate() == null) {  
				
				errors.rejectValue("email", "user.dob",  
						"Enter valid DOB");  
			}
			else
			{
				if (!objin.getBirthDate().before(new Date())) {
					errors.rejectValue("email", "user.dobf",  
							"Enter valid DOB");  
				}
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pinCode","user.pincode");
		
		
	}

}
