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
package dss.vector.solutions.query;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.ClientRequestFacade;

public class LayerDTO extends LayerDTOBase implements com.runwaysdk.generation.loader.Reloadable, LayerIF
{
  private static final long serialVersionUID = 1240900979001L;

  public LayerDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
   * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
   * 
   * @param businessDTO
   *          The BusinessDTO to duplicate
   * @param clientRequest
   *          The clientRequest this DTO should use to communicate with the server.
   */
  protected LayerDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  public boolean hasThematicVariable()
  {
    String alias = this.getThematicUserAlias();
    return alias != null && alias.length() != 0;
  }

  /**
   * This accessor lowercases the column alias to match actual column alias which is lowercased by the Query API prior to building the SQL.
   */
  public String getThematicColumnAlias()
  {
    return super.getThematicColumnAlias().toLowerCase();
  }

  @Override
  public boolean isPoint()
  {
    return this.getRenderAs().get(0).equals(AllRenderTypesDTO.POINT);
  }

  @Override
  public RequestFacadeIF getRequestFacade()
  {
    return new ClientRequestFacade(this.getRequest());
  }

  @Override
  public List<AbstractCategoryIF> getAllCategories()
  {
    return new LinkedList<AbstractCategoryIF>(this.getAllHasCategory());
  }

  @Override
  public void updateFile(String fileId)
  {
    this.updateSLDFile(fileId);
  }

}
