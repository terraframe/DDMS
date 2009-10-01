package com.terraframe.mojo;

import java.text.ParseException;

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
    try
    {
      new Versioning(args[0], args[1]).changeMostRecent();
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
  }

}
