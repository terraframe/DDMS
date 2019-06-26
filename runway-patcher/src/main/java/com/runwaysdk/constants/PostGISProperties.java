package com.runwaysdk.constants;

import com.runwaysdk.dataaccess.database.PostGIS;

public class PostGISProperties extends PostgreSQLProperties implements VendorProperties
{
  public static final String NAME         = "Postgis";

  public static final String VENDOR_CLASS = "com.runwaysdk.gis.constants.PostGIS";

  @Override
  public String getDatabaseClass()
  {
    return PostGIS.class.getName();
  }

  @Override
  public String getName()
  {
    return NAME;
  }
}
