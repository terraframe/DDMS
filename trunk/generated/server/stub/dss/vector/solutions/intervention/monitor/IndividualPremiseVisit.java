package dss.vector.solutions.intervention.monitor;

public class IndividualPremiseVisit extends IndividualPremiseVisitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 609276442;
  
  public IndividualPremiseVisit()
  {
    super();
  }

  public IndividualPremiseVisitView getView()
  {
    IndividualPremiseVisitView view = new IndividualPremiseVisitView();
    
    view.populateView(this);
    
    return view;
  }
  
}
