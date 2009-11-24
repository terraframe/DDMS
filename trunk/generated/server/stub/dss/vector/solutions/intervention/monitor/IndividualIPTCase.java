package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.Person;


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
  
  @Override
  public void apply()
  {
    super.apply();
    
    // As per ticket #890 we must update the patients residential information
    if(this.getResidentialLocation() != null)
    {
      Person person = this.getPatient().getPerson();
      
      person.lock();
      person.setResidentialGeoEntity(this.getResidentialLocation());
      person.apply();
    }
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
