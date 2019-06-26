package com.runwaysdk.constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.runwaysdk.dataaccess.database.AbstractDatabase;

/**
 * Acesses properties from two files: the agnostic database.properties, and the
 * vendor specified in databse.properties.
 * 
 * @author Eric
 */
public class DatabaseProperties
{
  private ResourceBundle   bundle;

  private VendorProperties vendorProperties;

  public DatabaseProperties(ResourceBundle bundle)
  {
    this.bundle = bundle;
    this.vendorProperties = new VendorPropertiesFactory().getVendorProperties(bundle.getString("databaseVendor"));
  }

  public DatabaseProperties(File file) throws FileNotFoundException, IOException
  {
    this(new PropertyResourceBundle(new FileReader(file)));
  }

  /**
   * @return The bin directory of the database
   */
  public String getDatabaseBinDirectory()
  {
    String databaseBinDirectory = bundle.getString("databaseBinDirectory");
    databaseBinDirectory = databaseBinDirectory.replace("/", File.separator);
    databaseBinDirectory = databaseBinDirectory.replace("\\", File.separator);

    return databaseBinDirectory;
  }

  /**
   * @return The bin directory of the database
   */
  public String getDataDumpExecutable()
  {
    return bundle.getString("dataDumpExecutable");
  }

  /**
   * @return The bin directory of the database
   */
  public String getDataImportExecutable()
  {
    return bundle.getString("dataImportExecutable");
  }

  /**
   * @return The fully qualified name of the JDBC driver
   */
  public boolean getConnectionPooling()
  {
    return Boolean.parseBoolean(bundle.getString("db.connection.pooling"));
  }

  /**
   * @return The name of the database the core is running on
   */
  public String getDatabaseName()
  {
    return bundle.getString("databaseName");
  }

  /**
   * @return The database the core is running on
   */
  @SuppressWarnings("unchecked")
  public Class<AbstractDatabase> getDatabaseClass()
  {
    String className = vendorProperties.getDatabaseClass();

    try
    {
      return (Class<AbstractDatabase>) Class.forName(className);
    }
    catch (ClassNotFoundException e)
    {
      String msg = "Unable to find a database class corresponding to the vendor [" + vendorProperties.getName() + "]";
      throw new UnknownVendorException(msg, e);
    }
  }

  /**
   * @return The number of database connections to initialize the pool with
   */
  public int getInitialConnections()
  {
    return Integer.parseInt(bundle.getString("db.connection.initial"));
  }

  /**
   * Returns the JNDI Data Source
   * 
   * @return
   */
  public String getJNDIDataSource()
  {
    return bundle.getString("jndiDataSource");
  }

  /**
   * @return The maximum number of database connections
   */
  public int getMaxConnections()
  {
    return Integer.parseInt(bundle.getString("db.connection.max"));
  }

  /**
   * @return The password for the runway's database user
   */
  public String getPassword()
  {
    return bundle.getString("password");
  }

  /**
   * @return The port the database is listening on
   */
  public int getPort()
  {
    return Integer.parseInt(bundle.getString("port"));
  }

  /**
   * @return The location (IP Address/URL/Network location) of the database
   */
  public String getServerName()
  {
    return bundle.getString("serverName");
  }

  /**
   * @return The runway's database user name
   */
  public String getUser()
  {
    return bundle.getString("user");
  }

  /**
   * @param code
   *          An error code
   * @return true if the String is a recognized error code for the current
   *         database
   */
  public boolean isError(String code)
  {
    return vendorProperties.isError(code);
  }

  /**
   * @param code
   *          An error code
   * @return true if the String is a serious error code for the current database
   */
  public boolean isSeriousError(String code)
  {
    return vendorProperties.isSeriousError(code);
  }

  public String getVendorName()
  {
    return vendorProperties.getName();
  }
}
