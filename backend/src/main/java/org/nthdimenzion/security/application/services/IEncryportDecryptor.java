package org.nthdimenzion.security.application.services;

public interface IEncryportDecryptor {

	String encrypt(String message);

	String decrypt(String encryptedMessage);
	
	String encryptToBase64(String message);
	
	String decryptToBase64(String encryptedMessage);
}
