package dss.vector.solutions.report;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.birt.report.model.api.IResourceLocator;
import org.eclipse.birt.report.model.api.ModuleHandle;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * This class is used by the BIRTEngine to resolve resource files from user uploaded vault zip files.
 * 
 * @author rrowlands
 */
public class BIRTVaultResourceResolver implements Reloadable, IResourceLocator
{
  private Map<String, String> resourceCache;
  
  public BIRTVaultResourceResolver()
  {
    resourceCache = new HashMap<String, String>();
  }
  
  @Override
  public URL findResource(ModuleHandle rptdesign, String filePath, int arg2)
  {
    return resolve(filePath);
  }

  @Override
  public URL findResource(ModuleHandle rptdesign, String filePath, int arg2, Map arg3)
  {
    return resolve(filePath);
  }
  
  private URL resolve(String filePath)
  {
    // When making PDF's for some reason BIRT will give a URL back to us that we've already resolved. So, lets check if what we've been given already exists.
    try
    {
      if (new File(new URL(filePath).toURI()).exists())
      {
        return new URL(filePath);
      }
    }
    catch (Exception e)
    {
      
    }
    
    File resourcesF = ReportResourceManager.getInstance().getBIRTResources();
    
    try
    {
      return new File(resourcesF.getAbsolutePath() + "/" + filePath).toURI().toURL();
    }
    catch (MalformedURLException e)
    {
      throw new ReportResourcesException(e);
    }
  }
}
