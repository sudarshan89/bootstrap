package org.nthdimenzion.security.application.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 16/8/13
 * Time: 9:46 PM
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncryptionUtil {

    public static String encrypt(final IEncryportDecryptor iEncryportDecryptor,String plainText){
        String encryptedText = iEncryportDecryptor.encryptToBase64(iEncryportDecryptor.encrypt(plainText));
        return encryptedText;
    }

    public static String decrypt(final IEncryportDecryptor iEncryportDecryptor,String ecryptedText){
        final String planText = iEncryportDecryptor.decrypt(iEncryportDecryptor.decryptToBase64(ecryptedText));
        return planText;
    }

}
