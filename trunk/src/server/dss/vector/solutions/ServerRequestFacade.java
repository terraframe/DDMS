package dss.vector.solutions;

import java.io.InputStream;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.WebFileInfo;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.vault.WebFileDAO;

import dss.vector.solutions.query.RequestFacadeIF;

public class ServerRequestFacade implements RequestFacadeIF, Reloadable
{
  @Override
  public String newFile(String path, String filename, String extension, InputStream stream)
  {
    Business entity = BusinessFacade.newBusiness(WebFileInfo.CLASS);
    WebFileDAO file = (WebFileDAO) BusinessFacade.getEntityDAO(entity);

    file.setExtension(extension);
    file.setFileName(filename);
    file.setFilePath(path);

    entity.apply();
    file.putFile(stream);

    return entity.getId();
  }

  @Override
  public void delete(String id)
  {
    try
    {
      BusinessFacade.getEntity(id).delete();
    }
    catch (DataNotFoundException e)
    {
      // Nothing to delete
    }
  }

}
