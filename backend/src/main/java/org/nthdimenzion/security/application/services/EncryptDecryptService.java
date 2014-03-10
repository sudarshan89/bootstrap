package org.nthdimenzion.security.application.services;

import org.apache.commons.codec.binary.Base64;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptDecryptService implements IEncryportDecryptor {

	private final TextEncryptor textEncryptor;

	@Autowired
	public EncryptDecryptService(TextEncryptor textEncryptor) {
	this.textEncryptor = textEncryptor;
	}

	@Override
	public String decrypt(String encryptedMessage) {
	return textEncryptor.decrypt(encryptedMessage);
	}

	@Override
	public String encrypt(String message) {
	return textEncryptor.encrypt(message);
	}

	@Override
	public String decryptToBase64(String encryptedMessage) {
	byte[] decodedBytes = Base64.decodeBase64(encryptedMessage.getBytes());
	return new String(decodedBytes);
	}

	@Override
	public String encryptToBase64(String message) {
	byte[] encodedBytes = Base64.encodeBase64(message.getBytes());
	return new String(encodedBytes);
	}
}
