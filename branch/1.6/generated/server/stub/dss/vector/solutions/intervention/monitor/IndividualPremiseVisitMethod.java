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
