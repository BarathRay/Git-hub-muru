package com.intellect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.intellect.modal.ResponseWrapper;

public class UserCoreService {


	@Autowired
	private ResourceBundleMessageSource messageSource;


	public ResponseWrapper handleResponse(String status,String id,String[] arr)
	{
		ResponseWrapper response=new 
				ResponseWrapper(status, id, arr==null?new String[]{}:arr);
		return response;	
	}

	public ResponseWrapper handleResponse(String status,String id,BindingResult bindingResult)
	{
		String[] arr=null;
		if(bindingResult.getErrorCount()>0)
		{
			arr=new String[bindingResult.getErrorCount()];
		}
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		int i=0;
		for (ObjectError objerr : allErrors) {
			if (objerr instanceof FieldError){
				String message = "";
				try {
					message = messageSource.getMessage(objerr, null);
				} catch (NoSuchMessageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arr[i]=message;
				i++;
			}
		}	

		ResponseWrapper response=new 
				ResponseWrapper(status, id, arr);
		return response;	
	}



}
