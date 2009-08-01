package dss.vector.solutions.intervention.monitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;

public class Household extends HouseholdBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239641282092L;

  public Household()
  {
    super();
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
      if(this.getWindowType().size() > 0)
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
    
    for(HouseholdNet net : this.getHouseholdNets())
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


  public HouseholdNet[] getHouseholdNets()
  {
    //A list of all this household nets plus nets that it doesn't have
    Map<String, HouseholdNet> nets = loadNets();
    List<HouseholdNet> list = new LinkedList<HouseholdNet>();
    Stack<Net> stack = new Stack<Net>();

    for(Net root : Net.getRoots())
    {
      stack.push(root);
    }

    while(!stack.empty())
    {
      Net net = stack.pop();

      for(Net child : net.getAllChildNets())
      {
        stack.push(child);
      }

      list.add(nets.get(net.getId()));
    }

    return list.toArray(new HouseholdNet[list.size()]);
  }

  private Map<String, HouseholdNet> loadNets()
  {
    Map<String, HouseholdNet> map = new HashMap<String, HouseholdNet>();

    for(HouseholdNet householdNet : this.getAllNetsRel())
    {
      map.put(householdNet.getChildId(), householdNet);
    }

    for(Net net : Net.getAll())
    {
      if(!map.containsKey(net.getId()))
      {
       map.put(net.getId(), new HouseholdNet(this, net));
      }
    }

    return map;
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
      if(!net.getChild().getIsAbstract())
      {
        net.apply();
      }
    }
    
    //  Validate the sum of all the nets
    this.validateNets();
  }
}
