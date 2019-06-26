package com.runwaysdk.constants;

public class VendorPropertiesFactory
{
  public VendorProperties getVendorProperties(String vendor)
  {
    if (vendor.equals(PostgreSQLProperties.VENDOR_CLASS))
    {
      return new PostgreSQLProperties();
    }
    if (vendor.equals(PostGISProperties.VENDOR_CLASS))
    {
      return new PostGISProperties();
    }
    else if (vendor.equals(SqlServerProperties.VENDOR_CLASS))
    {
      return new SqlServerProperties();
    }
    else if (vendor.equals(MySQLProperties.VENDOR_CLASS))
    {
      return new MySQLProperties();
    }
    else if (vendor.equals(OracleProperties.VENDOR_CLASS))
    {
      return new OracleProperties();
    }

    throw new UnknownVendorException("Unknown database vendor [" + vendor + "]");
  }

}
