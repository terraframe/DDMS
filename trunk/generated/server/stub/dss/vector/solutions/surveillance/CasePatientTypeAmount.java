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

public class CasePatientTypeAmount extends CasePatientTypeAmountBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 187559946;
  
  public CasePatientTypeAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CasePatientTypeAmount(dss.vector.solutions.surveillance.CasePatientType parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
 
  
  public CasePatientTypeAmountView getView()
  {
    CasePatientTypeAmountView view = new CasePatientTypeAmountView();

    view.populateView(this);

    return view;
  }
}
