package com.runwaysdk.constants;

public interface VendorProperties
{
  public boolean isError(String code);

  public boolean isSeriousError(String code);

  public String getDatabaseClass();

  public String getName();
}
