package dss.vector.solutions.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.session.Request;

/**
 * This is a generic, reusable patching class. The primary advantage is that we can pull as much logic out of the NSIS scripts as possible, which makes the code more reusable and testable. 
 * The fromPatchVersion / toPatchVersion variables are the $PatchVersion variable in NSIS, which signifies the SVN version of the DDMS trunk.
 * 
 * @author rrowlands
 */
public class ApplicationPatcher
{
  private static Logger logger = LoggerFactory.getLogger(ApplicationPatcher.class);
  
  private Integer fromPatchVersion;
  
  private Integer toPatchVersion;
  
  private Boolean doIt;
  
  public ApplicationPatcher(Integer fromPatchVersion, Integer toPatchVersion, Boolean doIt)
  {
    this.fromPatchVersion = fromPatchVersion;
    this.toPatchVersion = toPatchVersion;
    this.doIt = doIt;
  }
  
  @Request
  public void run()
  {
    if (this.doIt)
    {
      this.doIt();
    }
    else
    {
      this.undoIt();
    }
  }
  
  private void doIt()
  {
    ArrayList<DDMSPatchIF> patches = PatchRegistry.getPatchesInRange(this.fromPatchVersion, this.toPatchVersion);
    
    for (DDMSPatchIF patch : patches)
    {
      logger.info("Running 'doIt' of patch [" + patch.getClass().getName() + "].");
      
      patch.doIt();
    }
  }
  
  private void undoIt()
  {
    ArrayList<DDMSPatchIF> patches = PatchRegistry.getPatchesInRange(this.fromPatchVersion, this.toPatchVersion);
    
    for (DDMSPatchIF patch : patches)
    {
      logger.info("Running 'undoIt' of patch [" + patch.getClass().getName() + "].");
      
      patch.undoIt();
    }
  }
  
  public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
  {
    Options options = new Options();
    options.addOption(new Option("f", "fromPatchVersion", true, "From patch version. Saved in the windows registry as 'PatchVersion' and is the SVN version number of the trunk."));
    options.addOption(new Option("t", "toPatchVersion", true, "To patch version. Saved in the windows registry as 'PatchVersion' and is the SVN version number of the trunk."));
    options.addOption(new Option("d", "doIt", true, "Flag denoting whether this is doIt or undoIt."));
    
    try
    {
      CommandLineParser parser = new PosixParser();
      CommandLine cmd = parser.parse(options, args);

      Integer fromPatchVersion = Integer.parseInt(cmd.getOptionValue("f").trim());
      Integer toPatchVersion = Integer.parseInt(cmd.getOptionValue("t").trim());
      
      Boolean doIt = true;
      if (cmd.hasOption("d"))
      {
        doIt = Boolean.parseBoolean(cmd.getOptionValue("d"));
      }

      Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.migration.ApplicationPatcher");
      clazz.getMethod("start", new Class<?>[] { Integer.class, Integer.class, Boolean.class }).invoke(null, new Object[] { fromPatchVersion, toPatchVersion, doIt });
    }
    catch (ParseException e)
    {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("setup", options);

      e.printStackTrace();
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  public static void start(Integer fromPatchVersion, Integer toPatchVersion, Boolean doIt)
  {
    new ApplicationPatcher(fromPatchVersion, toPatchVersion, doIt).run();
  }
}
