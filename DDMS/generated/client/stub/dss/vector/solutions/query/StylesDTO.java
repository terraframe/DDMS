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

import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;

public class StylesDTO extends StylesDTOBase implements com.runwaysdk.generation.loader.Reloadable, StylesIF
{
  private static final long serialVersionUID = -213714992;

  public StylesDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  protected StylesDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  @Override
  public boolean isEnumerationAttribute(String styleName)
  {
    return ( this.getAttributeMd(styleName) instanceof AttributeEnumerationMdDTO );
  }

  @Override
  public StylesIF createMergedStyle()
  {
    return new StylesDTO(this.getRequest());
  }

  @Override
  public String getFontStylesName()
  {
    return this.getFontStyles().get(0).name().toLowerCase();
  }

  @Override
  public String getPointMarkerName()
  {
    WellKnownNamesDTO wknDTO = this.getPointMarker().get(0);
    String wkn = wknDTO.name().toLowerCase();

    return wkn;
  }
}
