package com.intellect.modal;

import java.io.Serializable;

public class ResponseWrapper implements Serializable {
	
	public static final String SUCCESS="Request Success";
	public static final String FAIL="Error Occurred";
	public static final String UNKNOWN="Unknown error";
	public static final String SERVICE_UNAVAILABLE="Service Unavailable error";
	public static final String INVALID_INPUT="Enter Valid Input";
	public static final String VALIDATION="Validation Info";
	
	private String resMsg;
	private String userId;
	private String[] valErrors;
	
	
	
	
	public ResponseWrapper(String resMsg, String userId, String[] valErrors) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
		this.valErrors = valErrors;
	}

	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getValErrors() {
		return valErrors;
	}
	public void setValErrors(String[] valErrors) {
		this.valErrors = valErrors;
	}
	
	
	
	
	
	

}
