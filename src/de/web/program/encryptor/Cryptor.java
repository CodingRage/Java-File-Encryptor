package de.web.program.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.security.Key;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptor {
	private static final String ALGORITHM = "AES";
    	private static final String TRANSFORMATION = "AES";
    
    	public static void writeToFile(String filename, String msg) throws Exception {
    		PrintWriter writer = new PrintWriter(Main.path + filename, "UTF-8");
    		writer.println(msg);
    		writer.close();
    	}
    
    	public static void writeToFile(File file, String msg) throws Exception {
    		PrintWriter writer = new PrintWriter(file.getPath(), "UTF-8");
    		writer.println(msg);
    		writer.close();
   	}
    
    	public static String[] readLinesFromFile(String filename) throws Exception {
    		File file = new File(Main.path, filename);
    		FileReader fr = new FileReader(file);
    		BufferedReader br = new BufferedReader(fr);
    		String lines = "";
    		String line = "";
    		while ((line = br.readLine()) != null) {
    			lines = lines + line + ",";
    		}
    		String[] returned = lines.split(Pattern.quote(","));
    		fr.close();
    		br.close();
    		return returned;
    	}
    
    	public static String[] readLinesFromFile(File file) throws Exception {
    		FileReader fr = new FileReader(file);
    		BufferedReader br = new BufferedReader(fr);
    		String lines = "";
    		String line = "";
    		while ((line = br.readLine()) != null) {
    			lines = lines + line + ",";
    		}
    		String[] returned = lines.split(Pattern.quote(","));
    		fr.close();
    		br.close();
    		return returned;
   	}
    
    	public static void encrypt(String key, File inputFile, File outputFile) throws Exception {
        //Hidden for Security
    	}
 
    	public static void decrypt(String key, File inputFile, File outputFile) throws Exception {
        //Hidden for Security
    	}
}
