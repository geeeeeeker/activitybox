package com.uxiangtech.activitybox.common;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class BlowfishUtils {

  private static final String SECRET_KEY_ENGINE = "";

  private static final String SECRET_KEY_CONSOLE = "";

  private static final String ALGORITHM = "Blowfish";

  public static String encryptToBase64(byte[] data, String key) throws Exception {
    byte[] encryptedData = encrypt(data, key);
    return Base64.getEncoder().encodeToString(encryptedData);
  }

  /**
   * 使用Blowfish算法加密数据
   *
   * @param data 待加密的数据
   * @param key  加密密钥
   * @return 加密后的字节数组
   * @throws Exception 如果加密过程中出现错误
   */
  public static byte[] encrypt(byte[] data, String key) throws Exception {
    Key secretKey = generateKey(key);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    return cipher.doFinal(data);
  }

  /**
   * 使用Blowfish算法解密数据
   *
   * @param encryptedData 已加密的数据
   * @param key           解密密钥
   * @return 解密后的字节数组
   * @throws Exception 如果解密过程中出现错误
   */
  public static byte[] decrypt(byte[] encryptedData, String key) throws Exception {
    Key secretKey = generateKey(key);
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    return cipher.doFinal(encryptedData);
  }

  /**
   * 生成Blowfish密钥
   *
   * @param keyStr 密钥字符串
   * @return 生成的密钥对象
   * @throws Exception 如果密钥生成过程中出现错误
   */
  private static Key generateKey(String keyStr) throws Exception {
    byte[] keyBytes = new byte[8];
    byte[] temp = keyStr.getBytes("UTF-8");

    System.arraycopy(temp, 0, keyBytes, 0, keyBytes.length);

    SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
    return secretKey;
  }
}
