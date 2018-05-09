package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

public class CredentialsInfo implements Reloadable
{

  private String username;

  private String digestAuthHash;

  private String basicAuthHash;

  private String basicAuthSalt;

  public CredentialsInfo(String username, String digestAuthHash, String basicAuthHash, String basicAuthSalt)
  {
    this.username = username;
    this.digestAuthHash = digestAuthHash;
    this.basicAuthHash = basicAuthHash;
    this.basicAuthSalt = basicAuthSalt;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getDigestAuthHash()
  {
    return digestAuthHash;
  }

  public void setDigestAuthHash(String digestAuthHash)
  {
    this.digestAuthHash = digestAuthHash;
  }

  public String getBasicAuthHash()
  {
    return basicAuthHash;
  }

  public void setBasicAuthHash(String basicAuthHash)
  {
    this.basicAuthHash = basicAuthHash;
  }

  public String getBasicAuthSalt()
  {
    return basicAuthSalt;
  }

  public void setBasicAuthSalt(String basicAuthSalt)
  {
    this.basicAuthSalt = basicAuthSalt;
  }
}
