package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;

import sun.security.action.GetPropertyAction;

import com.runwaysdk.util.FileIO;

/**
 * Called at the end of NSIS installation. Edits the user-supplied app name into
 * all appropriate files
 * 
 * @author Eric Grunzke
 */
public class PostInstallSetup
{
  /**
   * Exit status for the success case. NSIS considers any non-zero exit status
   * as an error, as such the SUCCESS exit status must be 0.
   */
  public static int SUCCESS = 0;

  /**
   * Exit status for the failure case.
   */
  public static int FAILURE = -1;

  private File      appRoot;

  private File      classes;

  private String    appName;

  private String    dbName;

  private String    installationNumber;

  private Boolean   isMaster;

  /**
   * Exit status of the setup
   */
  private int       exitStatus;

  public PostInstallSetup(String appName, String installationNumber, Boolean isMaster)
  {
    this.appName = appName;
    this.dbName = appName.toLowerCase();
    this.installationNumber = installationNumber;
    this.isMaster = isMaster;
    this.appRoot = new File("C:/MDSS/tomcat6/webapps/" + appName);
    this.classes = new File(appRoot, "WEB-INF/classes");
    this.exitStatus = PostInstallSetup.SUCCESS;
  }

  public void go() throws IOException
  {
    String domain = installationNumber + ".mdss.ivcc.com";
    String lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));

    // Update property files
    editWebappProperty("deploy.appname", appName, "terraframe.properties");
    editWebappProperty("databaseName", dbName, "database.properties");
    if (isMaster)
    {
      editWebappProperty("master", "true", "install.properties");
      editWebappProperty("domain", "0.mdss.ivcc.com", "terraframe.properties");
    }
    else
    {
      editWebappProperty("master", "false", "install.properties");
      editWebappProperty("domain", domain, "terraframe.properties");
    }

    // Update css and jsps
    String template = "/DDMS/";
    String replacer = "/" + appName + "/";
    readAndReplace(new File(appRoot, "login.jsp"), template, replacer);
    readAndReplace(new File(appRoot, "css/style.css"), template, replacer);
    readAndReplace(new File(appRoot, "test/viewComponentOldWay.jsp"), template, replacer);

    // Add this app to the list in applications.txt
    File applications = new File("C:/MDSS/manager/manager-1.0.0/classes/applications.txt");
    String appTxt = FileIO.readString(applications);
    appTxt += lineSeparator + appName;
    appTxt = appTxt.trim();

    int appCount = appTxt.split("\\s+").length;

    try
    {
      FileIO.write(applications, appTxt);
    }
    catch (Exception e)
    {
      this.exitStatus = PostInstallSetup.FAILURE;
    }

    try
    {
      // And give common.properties a unique rmi.port
      editWebappProperty("rmi.port", Integer.toString(1099 - appCount), "common.properties");
    }
    catch (Exception e)
    {
      this.exitStatus = PostInstallSetup.FAILURE;
    }

    try
    {
      // Update tomcat RAM
      File startup = new File("C:/MDSS/tomcat6/bin/startup.bat");
      int totalMemory = Math.min(2048, 512 * appCount);
      readAndReplace(startup, "-Xmx\\d*M", "-Xmx" + totalMemory + "M");
    }
    catch (Exception e)
    {
      this.exitStatus = PostInstallSetup.FAILURE;
    }

    try
    {
      // Update Geoserver's catalog.xml
      CatalogBuilder builder = new CatalogBuilder("C:/MDSS/tomcat6/webapps/geoserver/data/catalog.xml");
      builder.addApplication(appName, dbName);
      builder.write();
    }
    catch (Exception e)
    {
      this.exitStatus = PostInstallSetup.FAILURE;
    }
  }

  public void editWebappProperty(String key, String newValue, String bundle) throws IOException
  {
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
      }
      else
      {
        data += key + '=' + newValue;
      }
      data += "\n";
    }
    FileIO.write(file, data);
  }

  private void readAndReplace(File file, String regex, String replacement) throws IOException
  {
    String data = FileIO.readString(file);
    String replaced = data.replaceAll(regex, replacement);
    FileIO.write(file, replaced);
  }
//
//  private String createDatastoreXML()
//  {
//    String datastore = "<datastore id = \"MDSS_maps_" + appName + "\" enabled = \"true\" namespace = \"" + appName + "\" >\n" + "      <connectionParams >\n" + "        <parameter name = \"port\" value = \"5444\" />\n" + "        <parameter name = \"passwd\" value = \"mdssdeploy\" />\n" + "        <parameter name = \"dbtype\" value = \"postgis\" />\n" + "        <parameter name = \"host\" value = \"localhost\" />\n" + "        <parameter name = \"validate connections\" value = \"false\" />\n"
//        + "        <parameter name = \"max connections\" value = \"10\" />\n" + "        <parameter name = \"database\" value = \"" + dbName + "\" />\n" + "        <parameter name = \"wkb enabled\" value = \"true\" />\n" + "        <parameter name = \"namespace\" value = \"http://" + appName + ".terraframe.com\" />\n" + "        <parameter name = \"schema\" value = \"public\" />\n" + "        <parameter name = \"estimated extent\" value = \"false\" />\n"
//        + "        <parameter name = \"loose bbox\" value = \"true\" />\n" + "        <parameter name = \"user\" value = \"mdssdeploy\" />\n" + "        <parameter name = \"min connections\" value = \"4\" />\n" + "      </connectionParams>\n" + "    </datastore>\n    ";
//    return datastore;
//  }
//
//  private String createNamespaceXML()
//  {
//    return "<namespace prefix = \"" + appName + "\" uri = \"http://" + appName + ".terraframe.com\" />\n    ";
//  }

  public int getExitStatus()
  {
    return exitStatus;
  }

  public static void main(String[] args) throws Exception
  {
    try
    {
      String appName = args[0];
      String installationNumber = args[1];
      Boolean isMaster = Boolean.parseBoolean(args[2]);

      PostInstallSetup setup = new PostInstallSetup(appName, installationNumber, isMaster);
      setup.go();

      System.exit(setup.getExitStatus());
    }
    catch (Exception e)
    {
      System.exit(PostInstallSetup.FAILURE);
    }
  }
}
