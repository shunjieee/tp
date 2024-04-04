package seedu.address.storage;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class for encryption and decryption using AES.
 */
public class FixedAesUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String hexKey = "b6ea406741f7cfa3856d109d4337a4e69ede08a580ca4c7079ba33379c77cad5";
    private static final String hexIv = "6f36c2be10a88e7cf928abc5b5b2a279";

    /**
     * Converts a hex string to a byte array.
     *
     * @param s The hex string to be converted.
     * @return The byte array.
     */
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Encrypts the given plain text using AES.
     *
     * @param input The plain text to be encrypted.
     * @return The encrypted text.
     * @throws Exception If an error occurs during encryption.
     */
    public static String encrypt(String input) throws Exception {
        SecretKeySpec key = new SecretKeySpec(hexStringToByteArray(hexKey), "AES");
        IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(hexIv));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * Decrypts the given cipher text.
     *
     * @param cipherText The cipher text to be decrypted.
     * @return The decrypted plain text.
     * @throws Exception If an error occurs during decryption.
     */
    public static String decrypt(String cipherText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(hexStringToByteArray(hexKey), "AES");
        IvParameterSpec iv = new IvParameterSpec(hexStringToByteArray(hexIv));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

}
