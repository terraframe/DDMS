package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.xml.sax.SAXException;

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
  public static int    SUCCESS         = 0;

  /**
   * Exit status for the failure case.
   */
  public static int    FAILURE         = -1;

  public static String DEFAULT_TOMCAT  = "C:/MDSS/tomcat6/";

  public static String DEFAULT_MANAGER = "C:/MDSS/manager/";

  private File         appRoot;

  private File         classes;

  private String       appName;

  private String       dbName;

  private String       installationNumber;

  private Boolean      isMaster;

  private String       managerDirectory;

  private String       tomcatDirectory;

  public PostInstallSetup(String appName, String installationNumber, Boolean isMaster)
  {
    this(appName, installationNumber, isMaster, DEFAULT_TOMCAT, DEFAULT_MANAGER);
  }

  public PostInstallSetup(String appName, String installationNumber, Boolean isMaster, String tomcatDirectory, String managerDirectory)
  {
    this.appName = appName;
    this.dbName = appName.toLowerCase();
    this.installationNumber = installationNumber;
    this.isMaster = isMaster;
    this.tomcatDirectory = tomcatDirectory;
    this.managerDirectory = managerDirectory;

    this.appRoot = new File(tomcatDirectory + "webapps/" + appName);
    this.classes = new File(appRoot, "WEB-INF/classes");
  }

  public void go() throws IOException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerException
  {
    this.updateProperties();

    // Update css and jsps
    this.updateCSS();

    this.updateJSPs();

    // Add this app to the list in applications.txt
    String lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));

    File applications = new File(this.managerDirectory + "manager-1.0.0/classes/applications.txt");
    String appTxt = FileIO.readString(applications);
    appTxt += lineSeparator + appName;
    appTxt = appTxt.trim();

    int appCount = appTxt.split("\\s+").length;

    FileIO.write(applications, appTxt);
    // And give common.properties a unique rmi.port
    editWebappProperty("rmi.port", Integer.toString(1099 - appCount), "common.properties");

    // Update tomcat RAM
    File startup = new File(tomcatDirectory + "/bin/startup.bat");
    int totalMemory = Math.min(2048, 512 * appCount);
    readAndReplace(startup, "-Xmx\\d*M", "-Xmx" + totalMemory + "M");

    // Update Geoserver's catalog.xml
    CatalogBuilder builder = new CatalogBuilder(tomcatDirectory + "/webapps/geoserver/data/catalog.xml");
    builder.addApplication(appName, dbName);
    builder.write();
  }

  private void updateJSPs() throws IOException
  {
    String template = "/DDMS/";
    String replacer = "/" + appName + "/";

    readAndReplace(new File(appRoot, "login.jsp"), template, replacer);
    readAndReplace(new File(appRoot, "test/viewComponentOldWay.jsp"), template, replacer);
  }

  public void updateCSS() throws IOException
  {
    String template = "/\\w+/imgs/";
    String replacer = "/" + appName + "/imgs/";

    readAndReplace(new File(appRoot, "css/style.css"), template, replacer);
  }

  private void updateProperties() throws IOException
  {
    String domain = installationNumber + ".mdss.ivcc.com";

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

  public static void main(String[] args)
  {
    Option appNameOption = new Option("a", true, "Name of the app (required)");
    appNameOption.setRequired(true);

    Option installationNumberOption = new Option("n", true, "Installation number (required)");
    installationNumberOption.setRequired(true);

    Option isMasterOption = new Option("i", true, "Flag indicating if the install type is a master or dependent (required)");
    isMasterOption.setRequired(true);

    Options options = new Options();
    options.addOption(appNameOption);
    options.addOption(installationNumberOption);
    options.addOption(isMasterOption);
    options.addOption(new Option("c", "css-only", false, "Setup will only update the css files"));
    options.addOption(new Option("t", true, "Tomcat directory"));
    options.addOption(new Option("m", true, "Manager directory"));

    try
    {
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      String appName = cmd.getOptionValue("a").trim();
      String installationNumber = cmd.getOptionValue("n").trim();
      Boolean isMaster = Boolean.parseBoolean(cmd.getOptionValue("i").trim());
      String tomcatDirectory = cmd.getOptionValue("t", DEFAULT_TOMCAT);
      String managerDirectory = cmd.getOptionValue("m", DEFAULT_MANAGER);

      PostInstallSetup setup = new PostInstallSetup(appName, installationNumber, isMaster, tomcatDirectory, managerDirectory);

      if (cmd.hasOption("c"))
      {
        setup.updateCSS();
      }
      else
      {
        setup.go();
      }
    }
    catch (ParseException exp)
    {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("setup", options);

      throw new RuntimeException(exp);
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
}
