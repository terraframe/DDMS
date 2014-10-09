package dss.vector.solutions.manager.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties
{

  private Properties props;

  public DatabaseProperties(String location) throws IOException
  {
    FileInputStream stream = new FileInputStream(location);

    try
    {
      this.props = new Properties();
      this.props.load(stream);
    }
    finally
    {
      stream.close();
    }
  }

  public String getPort()
  {
    return this.props.getProperty("port");
  }
  
  public String getUserName()
  {
    return this.props.getProperty("user");
  }
  
  public String getPassword()
  {
    return this.props.getProperty("password");
  }
  
  public String getDatabaseName()
  {
    return this.props.getProperty("databaseName");
  }
  
  public String getServerName()
  {
    return this.props.getProperty("serverName");
  }
}
