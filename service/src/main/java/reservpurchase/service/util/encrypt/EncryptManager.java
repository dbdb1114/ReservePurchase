package reservpurchase.service.util.encrypt;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptManager {

    private static String alg;
    private static String key; // 32byte
    private static String iv; // 16byte
    protected static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    protected EncryptManager(Environment env) {
        alg = env.getProperty("encrypt.alg");
        key = env.getProperty("encrypt.key");
        iv = env.getProperty("encrypt.iv");
    }

    public String infoEncode(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e){
            e.printStackTrace();
            return handleEncodeException(e);
        }
    }

    public String infoDecode(String encoded) {
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            byte[] decodedBytes = Base64.getDecoder().decode(encoded);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        } catch (Exception e){
            e.printStackTrace();
            return handleEncodeException(e);
        }

    }


    private String handleEncodeException(Exception e) {
        System.out.println(e.getMessage());
        return "fail";
    }
}
