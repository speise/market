package org.market.domain.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHAMD5 implements SHA {

	private static final Logger log = LoggerFactory.getLogger(SHAMD5.class);

	private MessageDigest md;

	public SHAMD5() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public String getSecureString(String value) {
		return binaryToString(md.digest(value.getBytes()));
	}

	private String binaryToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
