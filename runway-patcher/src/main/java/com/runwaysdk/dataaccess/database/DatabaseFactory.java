package com.runwaysdk.dataaccess.database;

import java.io.File;

import com.runwaysdk.constants.DatabaseProperties;

public class DatabaseFactory
{
  public AbstractDatabase createDatabase(DatabaseProperties properties)
  {
    try
    {
      Class<AbstractDatabase> clazz = properties.getDatabaseClass();
      return clazz.getConstructor(DatabaseProperties.class).newInstance(properties);
    }
    catch (Exception e)
    {
      throw new DatabaseException(e);
    }
  }

  public AbstractDatabase createDatabase(File properties)
  {
    try
    {
      return this.createDatabase(new DatabaseProperties(properties));
    }
    catch (Exception e)
    {
      throw new DatabaseException(e);
    }
  }
}
