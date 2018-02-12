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

import com.runwaysdk.AttributeNotification;
import com.runwaysdk.business.Entity;
import com.runwaysdk.business.View;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

public abstract class NotificationProblem extends NotificationProblemBase implements AttributeNotification,  Reloadable
{
  private static final long serialVersionUID = 1238204417262L;

  public NotificationProblem()
  {
    super();
  }

  public NotificationProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }

  public void setNotification(Entity entity, String attributeName)
  {
    this.setComponentId(entity.getId());
    this.setAttributeName(attributeName);
    this.setAttributeDisplayLabel(entity.getMdAttributeDAO(attributeName).getDisplayLabel(Session.getCurrentLocale()));
    this.setDefiningType(entity.getType());
    this.setDefiningTypeDisplayLabel(entity.getClassDisplayLabel());
  }

  public void setNotification(View view, String attributeName)
  {
    this.setComponentId(view.getId());
    this.setAttributeName(attributeName);
    this.setAttributeDisplayLabel(view.getMdAttributeDAO(attributeName).getDisplayLabel(Session.getCurrentLocale()));
    this.setDefiningType(view.getType());
    this.setDefiningTypeDisplayLabel(view.getClassDisplayLabel());
  }

}
