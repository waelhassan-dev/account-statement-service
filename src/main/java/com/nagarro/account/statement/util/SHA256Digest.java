package com.nagarro.account.statement.util;

import com.nagarro.account.statement.exception.BadHashingException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHA256Digest {

	public static String digest(String clearText) {
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(clearText.getBytes(StandardCharsets.UTF_8));
	 
	        byte byteData[] = md.digest();
	 	        
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        return sb.toString();
		} catch(NoSuchAlgorithmException e){
			log.error("exception occurred while trying to digest text " + e.getMessage());
			throw new BadHashingException(e.getMessage());
		}
	}
}
