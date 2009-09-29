package com.terraframe.mojo;

import java.text.ParseException;
import java.util.Date;

import com.terraframe.mojo.dataaccess.io.Versioning;
import com.terraframe.mojo.session.StartSession;

public class RevertVersion
{

  /**
   * @param args
   */
  @StartSession
  public static void main(String[] args)
  {
    Date version = new Date(Long.parseLong(args[2]));
    try
    {
      new Versioning(args[0], args[1]).changeVersion(version);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
  }

}
