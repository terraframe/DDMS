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

import dss.vector.solutions.surveillance.ChildOption;

public class AggregatedPremiseReason extends AggregatedPremiseReasonBase implements com.runwaysdk.generation.loader.Reloadable, ChildOption
{
  private static final long serialVersionUID = -460001839;
  
  public AggregatedPremiseReason(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public AggregatedPremiseReason(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public AggregatedPremiseReasonView getView()
  {
    AggregatedPremiseReasonView view = new AggregatedPremiseReasonView();
    
    view.populateView(this);
    
    return view;
  }
}

