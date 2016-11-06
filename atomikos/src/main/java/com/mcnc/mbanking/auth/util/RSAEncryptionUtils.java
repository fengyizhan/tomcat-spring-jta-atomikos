package com.mcnc.mbanking.auth.util;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public abstract class RSAEncryptionUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(RSAEncryptionUtils.class);

	public static final String DEFAULT_TRANSFORMATION 	= "RSA/ECB/PKCS1Padding";
	
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder deEncoder = Base64.getDecoder();
	
	/**
	 * Encrypt text using {@link PrivateKey}
	 * @param original
	 * @param key
	 * @return Encrypted text
	 */
	public static String encrypt(String original, PrivateKey key) {
		Assert.notNull(original, "original text cannot be null");
		
		byte[] encrypt = encrypt(original.getBytes(StandardCharsets.UTF_8), key);
		
		if(encrypt != null) {
			return encoder.encodeToString(encrypt);
		}
		else {
			return null;
		}
		
	}
	
	
	/**
	 * Encrypt byte array using {@link PrivateKey}
	 * @param original
	 * @param key
	 * @return Encrypted byte array
	 */
	public static byte[] encrypt(byte[] original, PrivateKey key) {
		byte[] encrypted = null;

		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypted = cipher.doFinal(original);

		} catch (Exception e) {
			logger.error("Cannot encrype data", e);
		}

		return encrypted;
	}
	
	
	/**
	 * Decrypt text using {@link PrivateKey}
	 * @param encrypted
	 * @param key {@link PrivateKey}
	 * @return plain text
	 */
	public static String decrypt(String encrypted, PrivateKey key) {
		Assert.notNull(encrypted, "original text cannot be null");

		String result = null;
		
		try {
			byte[] decrypt = decrypt(deEncoder.decode(encrypted), key);
			if(decrypt != null) {
				result = new String(decrypt, StandardCharsets.UTF_8);
			}
		} catch (Exception e) {
			logger.error("Base64 cannot decode data", e);
		}
		
		return result;
	}

	/**
	 * Decrypt byte array using {@link PrivateKey}
	 * @param encrypted
	 * @param key
	 * @return plain byte array
	 */
	public static byte[] decrypt(byte[] encrypted, PrivateKey key) {
		byte[] decrypted = null;
		
		try {
			Cipher cipher = Cipher.getInstance(DEFAULT_TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypted = cipher.doFinal(encrypted);
		} catch (Exception e) {
			logger.error("Cannot decrypt data", e);
		}
		
		return decrypted;
	}
}
