package com.runwaysdk.constants;

import com.runwaysdk.dataaccess.database.Oracle;

/**
 * Contains static properties for operation of the core with Oracle. Login info
 * is specified in database.properties. Typical default values for logging in to
 * Oracle are:
 * 
 * <pre>
 * port=1521
 * databaseName=orcl
 * user=runway
 * password=runway
 * rootuser=system
 * rootpassword=system
 * rootdatabase=orcl
 * </pre>
 * 
 * @author Justin
 */
public class OracleProperties implements VendorProperties
{
  public static final String NAME         = "Oracle";

  public static final String VENDOR_CLASS = "com.runwaysdk.constants.Oracle";

  public OracleProperties()
  {
  }

  public String getDatabaseClass()
  {
    return Oracle.class.getName();
  }

  public boolean isError(String code)
  {
    return false;
  }

  public boolean isSeriousError(String code)
  {
    return false;
  }

  public String getName()
  {
    return NAME;
  }
}
