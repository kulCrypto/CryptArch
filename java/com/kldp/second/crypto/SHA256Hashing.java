package com.kldp.second.crypto;
import java.nio.charset.StandardCharsets;

import org.bouncycastle.crypto.digests.SHA256Digest;
public class SHA256Hashing {

	  public static byte[] hashPassword(String password,int iteration) {
	        SHA256Digest digest = new SHA256Digest();
	        byte[] data = password.getBytes(StandardCharsets.UTF_8);
	        digest.update(data, 0, data.length);
	        byte[] hash = new byte[digest.getDigestSize()];
	        digest.doFinal(hash, 0);
	        iteration--;
	        if(iteration>1)
	        	iterativeHashing(hash,iteration);
	        return hash;
	    }
	  
	  public static byte[] iterativeHashing(byte[] passwordHash,int iteration) {
		  SHA256Digest digest = new SHA256Digest();
	        //byte[] data = password.getBytes(StandardCharsets.UTF_8);
	        digest.update(passwordHash, 0, passwordHash.length);
	        byte[] hash = new byte[digest.getDigestSize()];
	        digest.doFinal(hash, 0);
	        iteration--;
	        if(iteration>1)
	        	iterativeHashing(hash,iteration);
	        return hash;
	  }
}
