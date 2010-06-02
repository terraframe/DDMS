package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.session.Session;

import dss.vector.solutions.general.Disease;

public class IndividualPremiseVisit extends IndividualPremiseVisitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 609276442;
  
  public IndividualPremiseVisit()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    this.validateTreated();
    this.validateReasonsForNotTreated();
    
    if(this.isNew() && !this.isAppliedToDB())
    {
      this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }

  public IndividualPremiseVisitView getView()
  {
    IndividualPremiseVisitView view = new IndividualPremiseVisitView();
    
    view.populateView(this);
    
    return view;
  }
  
  @Override
  public void validateTreated()
  {
    if (this.getTreated() != null && this.getTreated())
    {
      if (this.getVisited() == null || !this.getVisited())
      {
        String msg = "A premise cannot be treated when it is not visited";

        NotApplicableProblem p = new NotApplicableProblem(msg);
        p.setNotification(this, TREATED);
        p.setInputAttribute(getVisitedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(((MdAttributeBooleanDAOIF) getVisitedMd()).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateReasonsForNotTreated()
  {
    if (this.getReasonsForNotTreated() != null)
    {
      if (this.getVisited() == null || !this.getVisited())
      {
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, REASONSFORNOTTREATED);
        p.setInputAttribute(getVisitedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(((MdAttributeBooleanDAOIF) getVisitedMd()).getNegativeDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }

      if (this.getTreated() == null || this.getTreated())
      {
        NotApplicableProblem p = new NotApplicableProblem();
        p.setNotification(this, REASONSFORNOTTREATED);
        p.setInputAttribute(getTreatedMd().getDisplayLabel(Session.getCurrentLocale()));
        p.setInputValue(((MdAttributeBooleanDAOIF) getVisitedMd()).getPositiveDisplayLabel(Session.getCurrentLocale()));
        p.apply();
        p.throwIt();
      }
    }
  }
  
}
