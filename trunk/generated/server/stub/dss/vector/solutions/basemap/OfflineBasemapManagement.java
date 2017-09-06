package dss.vector.solutions.basemap;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geoserver.LocalBasemapBuilder;

public class OfflineBasemapManagement extends OfflineBasemapManagementBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1424086879;
  
  public OfflineBasemapManagement()
  {
    super();
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
