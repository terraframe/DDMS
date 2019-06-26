package com.runwaysdk.util;

import java.util.Locale;

public interface Converter
{
  public String format(Object object, Locale locale);
  
  public Object parse(String value, Locale locale);
}
