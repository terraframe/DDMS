package dss.vector.solutions.util;

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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.io.FileWriteException;
import com.runwaysdk.util.FileIO;

/**
 * Called at the end of NSIS installation. Edits the user-supplied app name into
 * all appropriate files
 * 
 * @author Eric Grunzke
 */
public class PostInstallSetup implements com.runwaysdk.generation.loader.Reloadable
{
  /**
   * Max perm size in MB which should be given to tomcat.
   */
  private static final int MAX_PERM_SIZE    = 256;

  /**
   * Amount of memory in MB to allocate to tomcat per app.
   */
  private static final int MEMORY_PER_APP   = 768;

  /**
   * Maximum amount of memory in MB to give to tomcat for all apps.
   */
  private static int       MAX_TOTAL_MEMORY = 1350;
  
  public static String     ROOT_DIRECTORY = "C:/MDSS";

  public static String     DEFAULT_TOMCAT  = ROOT_DIRECTORY+"/tomcat6/";

  public static String     DEFAULT_MANAGER = ROOT_DIRECTORY+"/manager/";

  public static String     DEFAULT_BIRT = ROOT_DIRECTORY+"/birt/";
  
  private final static Logger logger = Logger.getLogger(PostInstallSetup.class.getName());

  private File             appRoot;

  private File             classes;

  private String           appName;

  private String           dbName;

  private String           installationNumber;

  private Boolean          isMaster;

  private String           managerDirectory;

  private String           tomcatDirectory;

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
    logger.info("Adding "+appName+" to applications.txt");
    String lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));

    File applications = new File(this.managerDirectory + "manager-1.0.0/classes/applications.txt");
    String appTxt = FileIO.readString(applications);
    appTxt += lineSeparator + appName;
    appTxt = appTxt.trim();

    int appCount = appTxt.split("\\s+").length;

    FileIO.write(applications, appTxt);
    // And give common.properties a unique rmi.port
    logger.info("Setting a unique rmi port in common.properties");
    //int port = P
    this.updateRMI(appCount);

    // Update tomcat RAM, each app needs at least 768M inorder to compile the
    // system
    File startup = new File(tomcatDirectory + "/bin/startup.bat");
    int totalMemory = Math.min(MAX_TOTAL_MEMORY, MEMORY_PER_APP * appCount);
    logger.info("Updating Tomcat RAM in startup.bat to use "+totalMemory+"M");
    readAndReplace(startup, "-Xmx\\d*M", "-Xmx" + totalMemory + "M");
  }
  
  /**
   * This class ensures that an RMI port is not in use by another DDMS application
   * and is available at the time of installation. Any changes to this code should
   * also be made in the backup-manager project in PropertiesAgent.java
   * @throws IOException
   */
  private void updateRMI(int appCount)
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
    
    for (appCount = 0; appCount < apps.length; appCount++)
    {
      String app = apps[appCount];
      if (app.equals(appName))
      {
        break;
      }
    }
    
    List<Integer> usedPorts = null;
    usedPorts = getDDMSPorts();
    
    port -= appCount;

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

  private void updateJSPs() throws IOException
  {
    logger.info("Modifying login.jsp to use "+appName+" path");
    String template = "/DDMS/";
    String replacer = "/" + appName + "/";

    readAndReplace(new File(appRoot, "login.jsp"), template, replacer);
    readAndReplace(new File(appRoot, "test/viewComponentOldWay.jsp"), template, replacer);
  }

  public void patch() throws IOException
  {
    this.updateCSS();
    this.updateMaxPermSize();
    this.updateBIRTJavaPath();
  }

  public void updateCSS() throws IOException
  {
    logger.info("Updating style.css paths to images to use "+appName+"/imgs");
    String template = "/\\w+/imgs/";
    String replacer = "/" + appName + "/imgs/";

    readAndReplace(new File(appRoot, "css/style.css"), template, replacer);
    readAndReplace(new File(appRoot, "css/style-rtl.css"), template, replacer);
  }

  private void updateMaxPermSize() throws IOException
  {
    logger.info("Setting MAX_PERM_SIZE to "+MAX_PERM_SIZE+"M");
    File startup = new File(PostInstallSetup.DEFAULT_TOMCAT + "/bin/startup.bat");
    this.readAndReplace(startup, "-XX:MaxPermSize=\\d*M", "-XX:MaxPermSize=" + MAX_PERM_SIZE + "M");
  }
  
  /**
   * The executable birt.bat originally pointed to JAVA_HOME to set the
   * Java path, but that is incorrect because BIRT pays no attention to JAVA_HOME.
   * Instead set a new PATH value with DDMS's Java bin path.
   * @throws IOException 
   */
  private void updateBIRTJavaPath() throws IOException
  {
    logger.info("Updating birt.bat to use PATH instead of JAVA_HOME.");
    File file = new File(PostInstallSetup.DEFAULT_BIRT+"birt.bat");
    this.readAndReplace(file, "JAVA_HOME=(.*)", "PATH=$1\\\\bin;%PATH%");
  }

  private void updateProperties() throws IOException
  {
    logger.info("Updating property files to use specified AppName ["+appName+"]");
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
    options.addOption(new Option("p", "patch", false, "Post patch setup"));
    options.addOption(new Option("t", true, "Tomcat directory"));
    options.addOption(new Option("m", true, "Manager directory"));

    try
    {
      // Set up logging
      FileHandler logFile = new FileHandler(ROOT_DIRECTORY+"/PostInstallSetup.log");
      SimpleFormatter formatter = new SimpleFormatter();
      logFile.setFormatter(formatter);
      logger.addHandler(logFile);
      logger.setLevel(Level.INFO);
    
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      String appName = cmd.getOptionValue("a").trim();
      String installationNumber = cmd.getOptionValue("n").trim();
      Boolean isMaster = Boolean.parseBoolean(cmd.getOptionValue("i").trim());
      String tomcatDirectory = cmd.getOptionValue("t", DEFAULT_TOMCAT);
      String managerDirectory = cmd.getOptionValue("m", DEFAULT_MANAGER);

      PostInstallSetup setup = new PostInstallSetup(appName, installationNumber, isMaster, tomcatDirectory, managerDirectory);

      if (cmd.hasOption("p"))
      {
        setup.patch();
      }
      else if (cmd.hasOption("c"))
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
      
      logger.severe(exp.getLocalizedMessage());
      throw new RuntimeException(exp);
    }
    catch (RuntimeException e)
    {
      logger.severe(e.getLocalizedMessage());
      throw e;
    }
    catch (Exception e)
    {
      logger.severe(e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }
}
