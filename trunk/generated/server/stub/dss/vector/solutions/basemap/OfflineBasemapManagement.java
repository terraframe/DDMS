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
package dss.vector.solutions.basemap;

import java.util.HashMap;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geoserver.LocalBasemapBuilder;

public class OfflineBasemapManagement extends OfflineBasemapManagementBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1424086879;
  
  public OfflineBasemapManagement()
  {
    super();
  }
  
  public static java.lang.String getFlatFilesOnDisk()
  {
    HashMap<String, Boolean> flatFilesOnDisk = LocalBasemapBuilder.getBasemapFilesUploadStatus();
    
    JSONObject json = new JSONObject(flatFilesOnDisk);
    
    return json.toString();
  }
  
  @Override
  public void setFileName(String value) {
	super.setFileName(value);
  }
  
  @Override
  public void setFilePath(String value) {
	super.setFilePath(value);
  }
  
  @Override
  public void setQuedForUpload(Boolean value) {
	super.setQuedForUpload(value);
  }
  
  @Override
  public void setUploadSuccessful(Boolean value) {
	super.setUploadSuccessful(value);
  }
  
  @Transaction
  public static java.lang.Boolean importBasemapFiles(java.lang.String[] files)
  {
	boolean allSuccessful = LocalBasemapBuilder.importBasemapFiles(files);
    
	return allSuccessful;
  }
}
