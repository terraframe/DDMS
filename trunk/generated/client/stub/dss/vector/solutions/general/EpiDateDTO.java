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
package dss.vector.solutions.general;

import com.runwaysdk.constants.ClientRequestIF;

import dss.vector.solutions.surveillance.PeriodTypeMasterDTO;

public class EpiDateDTO extends EpiDateDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1243373494507L;

  public EpiDateDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public String getDisplayLabel(ClientRequestIF clientRequest)
  {
    PeriodTypeMasterDTO item = this.getPeriodType().get(0).item(clientRequest);

    return item.getDisplayLabel() + " " + this.getPeriod() + " - " + this.getEpiYear();
  }

}
