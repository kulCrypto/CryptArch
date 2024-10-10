package com.kldp.second.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Crypto {
	private void processFile(boolean encrypt,File inFile,byte[] inputKey,File outFile)throws Exception
	{
		
		Key key=new SecretKeySpec(inputKey,"AES");
		//Get Cipher instance
		Cipher cipher=Cipher.getInstance("AES");
		
		if(encrypt)
			cipher.init(Cipher.ENCRYPT_MODE, key);
		else
			cipher.init(Cipher.DECRYPT_MODE, key);
		//Read input file
		FileInputStream fileInputStream=new FileInputStream(inFile);
		byte[] inputBytes=new byte[(int)inFile.length()];
		fileInputStream.read(inputBytes);
		
		//process byte  array from input file
		byte[] outputBytes=cipher.doFinal(inputBytes);
		
		//write processed byte array to output file
		FileOutputStream fileOutputStream=new FileOutputStream(outFile);
		fileOutputStream.write(outputBytes);
		
		//close file streams
		fileInputStream.close();
		fileOutputStream.close();
	}
	
	public void encrypt(File inFile,byte[] inputKey,File outFile) throws Exception
	{processFile(true,inFile,inputKey,outFile);}
	
	public void decrypt(File inFile,byte[] inputKey,File outFile) throws Exception
	{processFile(false,inFile,inputKey,outFile);}
}
