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
package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.IndividualInstance;

public class IndividualCaseSymptom extends IndividualCaseSymptomBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1257959971971L;
  
  public IndividualCaseSymptom(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public IndividualCaseSymptom(dss.vector.solutions.intervention.monitor.IndividualInstance parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }

  public IndividualCaseSymptom clone(IndividualInstance parent)
  {
    IndividualCaseSymptom clone = new IndividualCaseSymptom(parent, this.getChild());
    clone.setHasSymptom(this.getHasSymptom());

    return clone;
  }
  
}
