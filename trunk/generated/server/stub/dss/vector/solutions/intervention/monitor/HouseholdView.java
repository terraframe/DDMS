package dss.vector.solutions.intervention.monitor;

import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.GridComparator;

public class HouseholdView extends HouseholdViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255641605608L;
  
  public HouseholdView()
  {
    super();
  }
  
  public void populateView(Household concrete)
  {    
    this.setConcreteId(concrete.getId());

    this.setHasWindows(concrete.getHasWindows());
    this.setHouseholdName(concrete.getHouseholdName());
    this.setLastSprayed(concrete.getLastSprayed());
    this.setNets(concrete.getNets());
    this.setNetsUsed(concrete.getNetsUsed());
    this.setPeople(concrete.getPeople());
    this.setRoof(concrete.getRoof());
    this.setRoofInfo(concrete.getRoofInfo());
    this.setRooms(concrete.getRooms());
    this.setSleptUnderNets(concrete.getSleptUnderNets());
    this.setSurveyPoint(concrete.getSurveyPoint());
    this.setUrban(concrete.getUrban());
    this.setWall(concrete.getWall());
    this.setWallInfo(concrete.getWallInfo());
    this.setWindowType(concrete.getWindowType());
  }

  private void populateConcrete(Household concrete)
  {
    concrete.setHasWindows(this.getHasWindows());
    concrete.setHouseholdName(this.getHouseholdName());
    concrete.setLastSprayed(this.getLastSprayed());
    concrete.setNets(this.getNets());
    concrete.setNetsUsed(this.getNetsUsed());
    concrete.setPeople(this.getPeople());
    concrete.setRoof(this.getRoof());
    concrete.setRoofInfo(this.getRoofInfo());
    concrete.setRooms(this.getRooms());
    concrete.setSleptUnderNets(this.getSleptUnderNets());
    concrete.setSurveyPoint(this.getSurveyPoint());
    concrete.setUrban(this.getUrban());
    concrete.setWall(this.getWall());
    concrete.setWallInfo(this.getWallInfo());
    concrete.setWindowType(this.getWindowType());
  }

  private void buildAttributeMap(Household concrete)
  {    
    new AttributeNotificationMap(concrete, Household.HASWINDOWS, this, HouseholdView.HASWINDOWS);
    new AttributeNotificationMap(concrete, Household.HOUSEHOLDNAME, this, HouseholdView.HOUSEHOLDNAME);
    new AttributeNotificationMap(concrete, Household.LASTSPRAYED, this, HouseholdView.LASTSPRAYED);
    new AttributeNotificationMap(concrete, Household.NETS, this, HouseholdView.NETS);
    new AttributeNotificationMap(concrete, Household.NETSUSED, this, HouseholdView.NETSUSED);
    new AttributeNotificationMap(concrete, Household.PEOPLE, this, HouseholdView.PEOPLE);
    new AttributeNotificationMap(concrete, Household.ROOF, this, HouseholdView.ROOF);
    new AttributeNotificationMap(concrete, Household.ROOFINFO, this, HouseholdView.ROOFINFO);
    new AttributeNotificationMap(concrete, Household.ROOMS, this, HouseholdView.ROOMS);
    new AttributeNotificationMap(concrete, Household.SLEPTUNDERNETS, this, HouseholdView.SLEPTUNDERNETS);
    new AttributeNotificationMap(concrete, Household.SURVEYPOINT, this, HouseholdView.SURVEYPOINT);
    new AttributeNotificationMap(concrete, Household.URBAN, this, HouseholdView.URBAN);
    new AttributeNotificationMap(concrete, Household.WALL, this, HouseholdView.WALL);
    new AttributeNotificationMap(concrete, Household.WALLINFO, this, HouseholdView.WALLINFO);
    new AttributeNotificationMap(concrete, Household.WINDOWTYPE, this, HouseholdView.WINDOWTYPE);
  }

  @Override
  public void apply()
  {
    Household concrete = new Household();

    if (this.hasConcrete())
    {
      concrete = Household.get(this.getConcreteId());
    }

    // Build the attribute map between Household and
    // HouseholdView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    String householdId = this.getConcreteId();

    if (this.hasConcrete())
    {
      
      Household.get(householdId).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  @Override
  public HouseholdNet[] getHouseholdNets()
  {
    Set<HouseholdNet> set = new TreeSet<HouseholdNet>(new GridComparator());

    for (Term d : Term.getRootChildren(HouseholdView.getDisplayNetsMd()))
    {
      set.add(new HouseholdNet(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      Household concrete = Household.get(this.getConcreteId());

      for (HouseholdNet net : concrete.getAllNetsRel())
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
    boolean newCase = !this.hasConcrete();

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
