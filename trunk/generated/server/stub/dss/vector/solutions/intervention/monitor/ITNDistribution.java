package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Person;

public class ITNDistribution extends ITNDistributionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253142897109L;
  
  public ITNDistribution()
  {
    super();
  }
  
  public static ITNDistributionView getView(String id)
  {
    return ITNDistribution.get(id).getView();
  }

  public ITNDistributionView getView()
  {
    ITNDistributionView view = new ITNDistributionView();
    view.populateView(this);

    return view;
  }

  @Override
  public ITNDistributionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public ITNDistributionView lockView()
  {
    this.lock();

    return this.getView();
  }

    
  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    for (ITNDistributionTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }
  }
  
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    
    for (ITNDistributionTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }
  }
  
  @Override
  protected String buildKey()
  {
    ITNRecipient recip = this.getRecipient();
    if(recip != null && this.getDistributionDate() != null && this.getFacility() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      Person person = recip.getPerson();
      
      return person.getFirstName() + "." + person.getLastName() + "." + this.getFacility().getGeoId() + "." + format.format(this.getDistributionDate());
    }
    return this.getId();
  }
}
