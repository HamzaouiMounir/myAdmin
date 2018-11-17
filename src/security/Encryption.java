package security;

import java.security.MessageDigest;
import org.jasypt.util.text.BasicTextEncryptor;

public class Encryption {
	private String input;
	static MessageDigest md;
	static BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

	public Encryption() {

	}

	public static String encrypt(String input) {
		textEncryptor.setPassword("myAdmin");
		return textEncryptor.encrypt(input);
	}

	public static String decrypt(String input) {
		textEncryptor.setPassword("myAdmin");
		return textEncryptor.decrypt(input);

	}

}
