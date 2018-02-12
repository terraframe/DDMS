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

import com.runwaysdk.AttributeNotificationDTO;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

public abstract class NotificationProblemDTO extends NotificationProblemDTOBase implements AttributeNotificationDTO, Reloadable
{
  private static final long serialVersionUID = 1238204417274L;

  public NotificationProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }

  public NotificationProblemDTO(com.runwaysdk.constants.ClientRequestIF clientRequest,
      java.util.Locale locale)
  {
    super(clientRequest, locale);
  }

  public void setNotification(MutableDTO mutable, AttributeMdDTO attribute)
  {
    this.setComponentId(mutable.getId());
    this.setAttributeName(attribute.getName());
    this.setAttributeDisplayLabel(attribute.getDisplayLabel());
    this.setDefiningType(mutable.getType());
    this.setDefiningTypeDisplayLabel(mutable.getMd().getDisplayLabel());
  }
}
