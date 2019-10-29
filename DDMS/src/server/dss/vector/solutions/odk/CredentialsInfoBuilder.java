package dss.vector.solutions.odk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.runwaysdk.generation.loader.Reloadable;

public class CredentialsInfoBuilder implements Reloadable
{
  public static final String  realmString = "DDMS mobile data capture ODK Aggregate";

  private static final Random random      = new Random();

  // must match that in Spring Security core.codec.Hex
  private static char[]       hexDigits   = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  private static String genHash(MessageDigest md, String fullSourceString, int len) throws NoSuchAlgorithmException
  {
    byte[] asBytes;
    try
    {
      asBytes = fullSourceString.getBytes("UTF-8");
    }
    catch (UnsupportedEncodingException e)
    {
      throw new NoSuchAlgorithmException("Cannot get UTF-8 encoding " + e.getMessage());
    }
    md.update(asBytes);

    byte[] messageDigest = md.digest();
    StringBuilder b = new StringBuilder();
    int i = len;
    while ( ( 2 * messageDigest.length ) < i)
    {
      b.append("0");
      --i;
    }

    for (int j = 0; j < messageDigest.length; ++j)
    {
      byte v = messageDigest[j];
      int hi = ( v & 0xF0 ) >> 4;
      int lo = ( v & 0x0F );
      b.append(hexDigits[hi]);
      b.append(hexDigits[lo]);
    }
    return b.toString();
  }

  public static CredentialsInfo build(String username, String rawPassword) throws NoSuchAlgorithmException
  {
    // compute the digest auth...
    String fullDigestAuth = username + ":" + realmString + ":" + rawPassword;
    MessageDigest md = MessageDigest.getInstance("MD5");
    String digestAuthHash = genHash(md, fullDigestAuth, 32);

    String basicAuthSalt = null;
    String basicAuthHash = null;

    // compute the basic auth hash...
    basicAuthSalt = Integer.toString(random.nextInt(), Character.MAX_RADIX);
    // we only have 8 characters to hold it...
    if (basicAuthSalt != null)
    {
      int len = basicAuthSalt.length();
      int start, end;
      if (len > 8)
      {
        start = 1;
        end = start + 8;
      }
      else
      {
        start = 0;
        end = len;
      }
      basicAuthSalt = basicAuthSalt.substring(start, end);
    }

    // RealmSecurityInfo would need to be augmented if additional
    // encrypted format parameters are allowed.
    // Must match SpringSecurity BasicPasswordEncoder for constructing salted
    // string.
    String fullBasicAuth = rawPassword + "{" + basicAuthSalt + "}";

    // basicAuthHash = DigestUtils.shaHex(fullBasicAuth);
    MessageDigest sha1d = MessageDigest.getInstance("SHA-1");
    basicAuthHash = genHash(sha1d, fullBasicAuth, 40);

    return new CredentialsInfo(username, digestAuthHash, basicAuthHash, basicAuthSalt);
  }
}
