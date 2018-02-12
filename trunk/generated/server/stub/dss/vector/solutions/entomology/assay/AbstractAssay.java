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
package dss.vector.solutions.entomology.assay;

import java.util.Date;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;

public abstract class AbstractAssay extends AbstractAssayBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543768433L;

  public AbstractAssay()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  public void apply()
  {
    validateTestDate();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }

  @Override
  public void validateTestDate()
  {
    if (this.getTestDate() != null)
    {
      super.validateTestDate();

      Date current = new Date();

      if (current.before(this.getTestDate()))
      {
        String msg = "It is impossible to have a test date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getTestDate());
        p.setCurrentDate(current);
        p.setNotification(this, TESTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }
}
