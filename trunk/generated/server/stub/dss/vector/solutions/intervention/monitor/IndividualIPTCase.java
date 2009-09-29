package dss.vector.solutions.intervention.monitor;


public class IndividualIPTCase extends IndividualIPTCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254012841931L;
  
  public IndividualIPTCase()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  public static IndividualIPTCaseView getView(String id)
  {
    return IndividualIPTCase.get(id).getView();
  }

  public IndividualIPTCaseView getView()
  {
    IndividualIPTCaseView view = new IndividualIPTCaseView();
    view.populateView(this);

    return view;
  }

  @Override
  public IndividualIPTCaseView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public IndividualIPTCaseView lockView()
  {
    this.lock();

    return this.getView();
  }
}
