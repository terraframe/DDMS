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
package dss.vector.solutions.intervention.monitor;

public class ITNCommunityDistributionViewDTO extends ITNCommunityDistributionViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1252612685889L;
  
  public ITNCommunityDistributionViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  
  /**
   * Bean form of isDisplayNetsReadable used in EL for jsps
   * 
   * @return this.isDisplayNetsReadable()
   */
  public boolean getIsDisplayNetsReadable()
  {
    return this.isDisplayNetsReadable();
  }

  /**
   * Bean form of isDisplayTargetGroupsReadable used in EL for jsps
   * 
   * @return this.isDisplayTargetGroupsReadable()
   */
  public boolean getIsDisplayTargetGroupsReadable()
  {
    return this.isDisplayTargetGroupsReadable();
  }
}
