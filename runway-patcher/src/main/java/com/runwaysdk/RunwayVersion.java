package com.runwaysdk;

public class RunwayVersion implements Comparable<RunwayVersion>
{
  static final Integer FIX_VERSION       = 0;

  static final Integer MINOR_VERSION     = 29;

  static final Integer MAJOR_VERSION     = 1;

  static final String  VERSION_DELIMETER = ".";

  private Integer      majorVersion;

  private Integer      minorVersion;

  private Integer      fixVersion;

  public RunwayVersion(Integer majorVersion, Integer minorVersion, Integer fixVersion)
  {
    this.majorVersion = majorVersion;
    this.minorVersion = minorVersion;
    this.fixVersion = fixVersion;
  }

  public RunwayVersion(String version)
  {
    this.majorVersion = 0;
    this.minorVersion = 0;
    this.fixVersion = 0;

    try
    {
      String[] versions = version.split("\\.");

      if (versions.length > 0)
      {
        this.majorVersion = Integer.parseInt(versions[0]);
      }

      if (versions.length > 1)
      {
        this.minorVersion = Integer.parseInt(versions[1]);
      }

      if (versions.length > 2)
      {
        this.fixVersion = Integer.parseInt(versions[2]);
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException("Invalid format of a runway version [" + version + "]");
    }
  }

  @Override
  public String toString()
  {
    return this.majorVersion + VERSION_DELIMETER + this.minorVersion + VERSION_DELIMETER + this.fixVersion;
  }

  public static RunwayVersion getCurrentVersion()
  {
    return new RunwayVersion(MAJOR_VERSION, MINOR_VERSION, FIX_VERSION);
  }

  public Integer getMajorVersion()
  {
    return this.majorVersion;
  }

  public Integer getMinorVersion()
  {
    return this.minorVersion;
  }

  public Integer getFixVersion()
  {
    return this.fixVersion;
  }

  /**
   * Method to determine if one RunwayVersion is greater than another
   * RunwayVersion. However, this method does not take into account the fix
   * version number when determining which version is greater. The algorithm for
   * determing which version is higher is majorVersion then minorVersion.
   * 
   * @param version
   * @return
   */
  public boolean isGreater(RunwayVersion version)
  {
    if (this.majorVersion > version.getMajorVersion())
    {
      return true;
    }

    if (this.minorVersion > version.getMinorVersion())
    {
      return true;
    }

    return false;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof RunwayVersion)
    {
      RunwayVersion version = (RunwayVersion) obj;

      return ( majorVersion.equals(version.getMajorVersion()) && minorVersion.equals(version.getMinorVersion()) && fixVersion.equals(version.getFixVersion()) );
    }

    return false;
  }

  public int compareTo(RunwayVersion version)
  {
    if (version.getMajorVersion().equals(this.majorVersion))
    {
      if (version.getMinorVersion().equals(this.minorVersion))
      {
        return version.getFixVersion().compareTo(this.fixVersion);
      }

      return version.getMinorVersion().compareTo(this.minorVersion);
    }

    return version.getMajorVersion().compareTo(this.majorVersion);
  }
}
