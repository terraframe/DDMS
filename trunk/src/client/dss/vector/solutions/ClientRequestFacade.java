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

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.query.RequestFacadeIF;

public class ClientRequestFacade implements RequestFacadeIF, Reloadable
{
  private ClientRequestIF request;

  public ClientRequestFacade(ClientRequestIF request)
  {
    this.request = request;
  }

  @Override
  public String newFile(String path, String filename, String extension, InputStream stream)
  {
    BusinessDTO file = this.request.newFile(path, filename, extension, stream);

    return file.getId();
  }

  @Override
  public void delete(String id)
  {
    this.request.delete(id);
  }

}
