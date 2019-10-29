/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
  
  public URL resolve(String filePath)
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
