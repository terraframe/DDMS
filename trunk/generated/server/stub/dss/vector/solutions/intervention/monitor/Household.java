package dss.vector.solutions.intervention.monitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

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
      SprayProblem p = new SprayProblem();
      p.setMonths(this.getLastSprayed());
      p.apply();
      p.throwIt();
    }
  }

  @Override
  @Transaction
  public void apply()
  {
    validateLastSprayed();

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

    stack.push(Net.getRoot());

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
  }
}
