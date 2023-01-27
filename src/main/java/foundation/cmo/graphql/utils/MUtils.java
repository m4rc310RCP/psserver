package foundation.cmo.graphql.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MUtils {
	public static void main(String[] args) {

		String dt = "dd/MM/yyyy HH:mm:ss";

		int hash = 7;
		for (int i = 0; i < dt.length(); i++) {
			hash = hash * 31 + dt.charAt(i);
		}

		hash = Math.abs(hash);

		System.out.println(hash);

		try {
			
		String str = "test string";
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(str.getBytes(), 0, str.length());
		System.out.println("MD5: " + new BigInteger(1, messageDigest.digest()).toString(64));
		} catch (Exception e) {
		}

	}
}
