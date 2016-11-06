package com.mcnc.mbanking.auth.dto;

import com.mcnc.mbanking.auth.secure.annotation.CipherData;

public class ApiResponseMessage2 {
	
	@CipherData
	private String encryptText;

	public String getEncryptText() {
		return encryptText;
	}

	public void setEncryptText(String encryptText) {
		this.encryptText = encryptText;
	}
}
