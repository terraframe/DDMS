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

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.ChildOption;

public class IndividualPremiseVisitMethod extends IndividualPremiseVisitMethodBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -1901234686;

  public IndividualPremiseVisitMethod(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public IndividualPremiseVisitMethod(IndividualPremiseVisit parent, Term child)
  {
    this(parent.getId(), child.getId());
  }

  public IndividualPremiseVisitMethodView getView()
  {
    IndividualPremiseVisitMethodView view = new IndividualPremiseVisitMethodView();

    view.populateView(this);

    return view;
  }

  @Override
  public void apply()
  {
    validateUsed();

    super.apply();
  }

  @Override
  public void validateUsed()
  {
    if (this.getUsed() != null && this.getUsed())
    {
      IndividualPremiseVisit parent = this.getParent();
      
      if (parent.getVisited() == null || !parent.getVisited())
      {
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, USED);
        p.setInputAttribute(IndividualPremiseVisit.getVisitedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) IndividualPremiseVisit.getVisitedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }

      if (parent.getTreated() == null || !parent.getTreated())
      {
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, USED);
        p.setInputAttribute(IndividualPremiseVisit.getTreatedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue( ( (MdAttributeBooleanDAOIF) IndividualPremiseVisit.getTreatedMd() ).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }
}
