package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Person;
import dss.vector.solutions.surveillance.GridComparator;

public class ITNDistribution extends ITNDistributionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1253142897109L;
  
  public ITNDistribution()
  {
    super();
  }
  
  @Override
  @Transaction
  public void applyAll(ITNDistributionTargetGroup[] targetGroups)
  {
    if (this.isNew())
    {
      for (int i = 0; i < targetGroups.length; i++)
      {
        targetGroups[i] = targetGroups[i].clone(this);
      }
    }
    
    this.apply();
    
    for (ITNDistributionTargetGroup dtg : targetGroups)
    {
      dtg.apply();
    }
  }
  
  @Override
  public ITNDistributionTargetGroup[] getDistributionTargetGroups()
  {
    Set<ITNDistributionTargetGroup> set = new TreeSet<ITNDistributionTargetGroup>(new GridComparator());

    for (TargetGroupGrid d : TargetGroupGrid.getAll())
    {
      set.add(new ITNDistributionTargetGroup(this.getId(), d.getId()));
    }

    if (!this.isNew())
    {
      for (ITNDistributionTargetGroup d : this.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actual
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNDistributionTargetGroup[set.size()]);
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
