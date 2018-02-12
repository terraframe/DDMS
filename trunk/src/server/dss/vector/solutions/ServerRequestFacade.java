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
