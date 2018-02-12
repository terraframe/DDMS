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
package dss.vector.solutions.irs;

import dss.vector.solutions.geo.generated.GeoEntity;

public abstract class InterventionPlanningView extends InterventionPlanningViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254346304596L;

  public InterventionPlanningView()
  {
    super();
  }

  public void setEntityLabel(GeoEntity entity)
  {
    this.setEntityLabel(entity.getLabel());
  }

  protected boolean validateCalculation()
  {
    if (this.getTargets() == null || this.getTargets() == 0)
    {
//      String msg = "Targets have not been populated";
//
//      RequiredTargetsProblem p = new RequiredTargetsProblem(msg);
//      p.setNotification(this, TARGETS);
//      p.setEntityLabel(this.getEntityLabel());
//      p.apply();
//      p.throwIt();
//      
      return false;
    }
    
    return true;
  }
}
