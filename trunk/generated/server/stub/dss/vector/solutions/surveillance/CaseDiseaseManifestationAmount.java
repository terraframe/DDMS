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

public class CaseDiseaseManifestationAmount extends CaseDiseaseManifestationAmountBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -26748056;
  
  public CaseDiseaseManifestationAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseDiseaseManifestationAmount(dss.vector.solutions.surveillance.CaseDiseaseManifestation parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public CaseDiseaseManifestationAmountView getView()
  {
    CaseDiseaseManifestationAmountView view = new CaseDiseaseManifestationAmountView();

    view.populateView(this);

    return view;
  }

}
