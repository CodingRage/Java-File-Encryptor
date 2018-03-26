package de.web.program.encryptor;

public class JFES {
	
	//Java File Encryption Standard
	
	public static String encrypt(String msg) {
    		char[] chars = msg.toCharArray();
		String r = "";
		for (char c : chars) {
     			switch(c) {
       			//Hidden for Security
     			}
    		}
		return r;
	}
  
  	public static String decrypt(String msg) {
    		String r = "";
		char[] chars = msg.toCharArray();
		String key = "";
		for (char c : chars) {
			key = key + c;
			if (key.length() == 4) {
				switch(key) {
          			//Hidden for Security
        			}
      			}
    		}
 	}
}
