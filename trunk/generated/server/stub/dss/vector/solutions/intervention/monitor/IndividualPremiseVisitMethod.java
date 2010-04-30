package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;

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
}
