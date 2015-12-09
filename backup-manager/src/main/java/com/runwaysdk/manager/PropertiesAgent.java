package com.runwaysdk.manager;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import sun.security.action.GetPropertyAction;

import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.dataaccess.io.RestoreAgent;
import com.runwaysdk.util.FileIO;

public class PropertiesAgent implements RestoreAgent
{
  public static String ROOT_DIRECTORY = "C:/MDSS";

  public static String DEFAULT_TOMCAT = ROOT_DIRECTORY + "/tomcat/";

  public static String DEFAULT_MANAGER = ROOT_DIRECTORY + "/manager/";

  private String appName;

  public PropertiesAgent(String appName)
  {
    this.appName = appName;
  }

  @Override
  public void preRestore()
  {
  }

  @Override
  public void postRestore()
  {
    this.updateRMI();
  }

  /**
   * This class ensures that an RMI port is not in use by another DDMS
   * application and is available at the time of installation. Any changes to
   * this code should also be made in the MDSS project in PostInstallSetup.java
   * 
   * @throws IOException
   */
  private void updateRMI()
  {
    int port = 1099;
    String managerDirectory = DEFAULT_MANAGER;
    String lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));
    File applications = new File(managerDirectory + "manager-1.0.0/classes/applications.txt");
    String appTxt;
    
    try
    {
      appTxt = FileIO.readString(applications);
    } catch (IOException e)
    {
      throw new FileWriteException("Couldn't read application list file", applications, e);
    }
    String[] apps = appTxt.split(lineSeparator);
    int appNumber;
    boolean foundApp = false;
    for (appNumber = 0; appNumber < apps.length; appNumber++)
    {
      String app = apps[appNumber];
      if (app.equals(appName))
      {
        foundApp = true;
        break;
      }
    }

    List<Integer> usedPorts = null;
    usedPorts = getDDMSPorts();

    if (foundApp)
    {
      port -= appNumber;

      while (usedPorts.contains(port) || !isPortAvailable(port))
      {
        port--;
      }

      // And give common.properties a unique rmi.port
      try
      {
        editWebappProperty("rmi.port", Integer.toString(port), "common.properties");
      } catch (IOException e)
      {
        throw new FileWriteException("Couldn't write commons.properties file for " + appName, null, e);
      }
    }
    else
    {
      throw new FileReadException("The application ["+appName+"] was not found in the application list. Please contact technical support.", applications);
    }

  }

  public List<Integer> getDDMSPorts()
  {
    ArrayList<Integer> ports = new ArrayList<Integer>();

    File webappDir = new File(DEFAULT_TOMCAT + "webapps");
    FileFilter fileFilter = new FileFilter()
    {
      public boolean accept(File file)
      {
        if (!file.getName().equals(appName) && file.isDirectory())
          return true;
        return false;
      }
    };

    for (File webapp : webappDir.listFiles(fileFilter))
    {
      String commonProp = webapp.getPath() + "\\WEB-INF\\classes\\common.properties";
      Properties prop = new Properties();
      if (new File(commonProp).exists())
      {
        try
        {
          prop.load(new FileInputStream(commonProp));
        } catch (FileNotFoundException e)
        {
          throw new FileReadException("There exists no commons.properties file for " + appName, null, e);
        } catch (IOException e)
        {
          throw new FileReadException("Couldn't read commons.properties file for " + appName, null, e);
        }
        ports.add(Integer.parseInt(prop.getProperty("rmi.port")));
      }
    }

    return ports;
  }

  public static boolean isPortAvailable(int port)
  {
    ServerSocket ss = null;
    DatagramSocket ds = null;
    try
    {
      ss = new ServerSocket(port);
      ss.setReuseAddress(true);
      ds = new DatagramSocket(port);
      ds.setReuseAddress(true);
      return true;
    } catch (IOException e)
    {
    } finally
    {
      if (ds != null)
      {
        ds.close();
      }

      if (ss != null)
      {
        try
        {
          ss.close();
        } catch (IOException e)
        {
          /* should not be thrown */
        }
      }
    }

    return false;
  }

  public void editWebappProperty(String key, String newValue, String bundle) throws IOException
  {
    File appRoot = new File(DEFAULT_TOMCAT + "webapps/" + appName);
    File classes = new File(appRoot, "WEB-INF/classes");
    editProperty(key, newValue, new File(classes, bundle));
  }

  private void editProperty(String key, String newValue, File file) throws IOException
  {
    String data = new String();
    for (String line : FileIO.readLines(file))
    {
      if (!line.startsWith(key + '='))
      {
        data += line;
      } else
      {
        data += key + '=' + newValue;
      }
      data += "\n";
    }
    FileIO.write(file, data);
  }

}
