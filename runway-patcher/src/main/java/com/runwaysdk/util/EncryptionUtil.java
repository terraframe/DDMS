package com.runwaysdk.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.security.provider.Sun;

/**
 * Computes a message digest for a value.
 */
public class EncryptionUtil
{

  /**
   * Digests a value and returns the hash value.
   * 
   * @param message
   * @param digestType
   * @return
   * @throws NoSuchAlgorithmException
   */
  public static String digestMethod(String message, String digestType) throws NoSuchAlgorithmException
  {
    String hash = null;

    if (message != null)
    {
      MessageDigest digest = MessageDigest.getInstance(digestType, new Sun());

      digest.update(message.getBytes());
      
      hash = Base64.encodeToString(digest.digest(), false);
    }
    
    return hash;
  }
}
