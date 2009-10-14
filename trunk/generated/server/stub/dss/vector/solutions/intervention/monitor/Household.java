package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class Household extends HouseholdBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641282092L;

  public Household()
  {
    super();
  }
    
  @Override
  protected String buildKey()
  {
    if(this.getHouseholdName() != null)
    {
      return this.getHouseholdName();
    }
    
    return this.getId();
  }
  
  @Override
  public void validateLastSprayed()
  {
    if(this.getLastSprayed() != null && this.getLastSprayed() > 12)
    {
      String msg = "Not able to have a last spray date greater than 12";
      SprayProblem p = new SprayProblem(msg);      
      p.setMonths(this.getLastSprayed());
      p.setNotification(this, LASTSPRAYED);
      p.apply();
      p.throwIt();
    }
  }
  
  @Override
  public void validateNets()
  {
    if(this.getNets() != null && this.getNets() != this.getNetSum())
    {
      String msg = "The sum of all individual nets does not sum up to the total number of nets";
      NetSumProblem p = new NetSumProblem(msg);      
      p.setNotification(this, NETS);
      p.apply();
      p.throwIt();      
    }
  }
  
  
  @Override
  public void validateNetsUsed()
  {
    if(this.getNets() != null && this.getNetsUsed() != null)
    {
      if(this.getNets() < this.getNetsUsed())
      {
        String msg = "Nets used is not allowed to be greater than total nets";
        NetQuantityProblem p = new NetQuantityProblem(msg);
        p.setNetCount(this.getNetsUsed());
        p.setQuantity(this.getNets());
        p.setNotification(this, NETSUSED);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateSleptUnderNets()
  {
    if(this.getNets() != null && this.getSleptUnderNets() != null)
    {
      if(this.getNets() == 0 && this.getSleptUnderNets() > 0)
      {
        String msg = "Slept under nets cannot be set when the total number of nets is 0";
        
        SleptUnderNetsProblem p = new SleptUnderNetsProblem(msg);
        p.setNotification(this, SLEPTUNDERNETS);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateWindowType()
  {
    if(this.getHasWindows() != null && !this.getHasWindows())
    {
      if(this.getWindowType() != null)
      {
        String msg = "A window type is not allowed to be set when has windows is false";
        WindowTypeProblem p = new WindowTypeProblem(msg);
        p.setNotification(this, WINDOWTYPE);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  private int getNetSum()
  {
    int sum = 0;
    
    HouseholdNet[] nets = this.getHouseholdNets();

    for(HouseholdNet net : nets)
    {
      sum += net.getAmount();
    }
    
    return sum;
  }

  @Override
  @Transaction
  public void apply()
  {
    validateLastSprayed();
    validateNetsUsed();
    validateSleptUnderNets();
    validateWindowType();

    boolean first = !this.isAppliedToDB() && this.isNew();

    super.apply();

    if (first)
    {
      SurveyHousehold surveyHousehold = this.addSurveyPoints(this.getSurveyPoint());
      surveyHousehold.apply();
    }
  }
  
  @Override
  @Transaction
  public void delete()
  {
    //First delete all of the Surveyed People of this household
    List<Person> list = new LinkedList<Person>();
    OIterator<? extends Person> it = this.getAllPersons();
    
    try
    {      
      while(it.hasNext())
      {
        list.add(it.next());
      }      
    }
    finally
    {
      it.close();
    }
    
    for(Person person : list)
    {
      person.delete();
    }
    
    super.delete();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    for(HouseholdNet net : this.getAllNetsRel())
    {
      net.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    for(HouseholdNet net : this.getAllNetsRel())
    {
      net.unlock();
    }
  }
  
  @Override
  public HouseholdNet[] getHouseholdNets()
  {
    Set<HouseholdNet> set = new TreeSet<HouseholdNet>(new GridComparator());

    for (Term d : Term.getRootChildren(Household.getHasWindowsMd()))
    {
      set.add(new HouseholdNet(this.getId(), d.getId()));
    }

    if (!this.isNew() || this.isAppliedToDB())
    {
      for (HouseholdNet net : this.getAllNetsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set.  Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if(set.contains(net))
        {
          set.remove(net);
          set.add(net);
        }
      }
    }

    return set.toArray(new HouseholdNet[set.size()]);
  }

  @Transaction
  public void applyAll(HouseholdNet[] nets)
  {
    boolean newCase = this.isNew();

    this.apply();

    // If this is a new household then all of the house hold
    // nets to need be clone with the proper parent id
    // because their existing parent id does not exist in the
    // system
    if (newCase)
    {
      for (int i = 0; i < nets.length; i++)
      {
        nets[i] = nets[i].clone(this);
      }
    }

    for(HouseholdNet net : nets)
    {
      net.apply();
    }
    
    //  Validate the sum of all the nets
    this.validateNets();
  }
}
