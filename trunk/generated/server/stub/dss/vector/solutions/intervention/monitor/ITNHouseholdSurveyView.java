package dss.vector.solutions.intervention.monitor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.FeverResponse;
import dss.vector.solutions.intervention.TimeInterval;
import dss.vector.solutions.surveillance.GridComparator;
import dss.vector.solutions.surveillance.OptionComparator;

public class ITNHouseholdSurveyView extends ITNHouseholdSurveyViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970233264L;
  
  public ITNHouseholdSurveyView()
  {
    super();
  }
  
  public void populateView(ITNHouseholdSurvey concrete)
  {    
    this.setConcreteId(concrete.getId());
    
    this.setStartDate(concrete.getStartDate());
    this.setEndDate(concrete.getEndDate());
    this.setAgentFirstName(concrete.getAgentFirstName());
    this.setAgentSurname(concrete.getAgentSurname());
    this.setQuestionnaireNumber(concrete.getQuestionnaireNumber());
    this.setResidents(concrete.getResidents());
    this.setPregnantResidents(concrete.getPregnantResidents());
    this.setChildResidents(concrete.getChildResidents());
    this.setItns(concrete.getItns());
    this.setDamagedItns(concrete.getDamagedItns());
    this.setHangingItns(concrete.getHangingItns());
    this.setOtherItns(concrete.getOtherItns());
    this.setMonthReceived(concrete.getMonthReceived());
    this.setYearReceived(concrete.getYearReceived());
    this.setUsedItns(concrete.getUsedItns());
    this.setUsedEveryNight(concrete.getUsedEveryNight());
    this.setNetsObtained(concrete.getNetsObtained());
    this.setFreeProvider(concrete.getFreeProvider());
    this.setBoughtProvider(concrete.getBoughtProvider());
    this.setKnowWashFrequency(concrete.getWashFrequency() != null);
    this.setWashFrequency(concrete.getWashFrequency());
    this.setRetreated(concrete.getRetreated());
    this.setRetreatmentPeriod(concrete.getRetreatmentPeriod());
    
    this.clearWashed();
    this.clearWashInterval();
    
    for(FeverResponse washed : concrete.getWashed())
    {
      this.addWashed(washed);
    }
    
    for(TimeInterval interval : concrete.getWashInterval())
    {
      this.addWashInterval(interval);
    }
    
    GeoEntity location = concrete.getSurveyLocation();
    
    if (location != null)
    {
      this.setSurveyLocation(location.getGeoId());
    }    
  }

  private void populateConcrete(ITNHouseholdSurvey concrete)
  {
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setAgentFirstName(this.getAgentFirstName());
    concrete.setAgentSurname(this.getAgentSurname());
    concrete.setQuestionnaireNumber(this.getQuestionnaireNumber());
    concrete.setResidents(this.getResidents());
    concrete.setPregnantResidents(this.getPregnantResidents());
    concrete.setChildResidents(this.getChildResidents());
    concrete.setItns(this.getItns());
    concrete.setDamagedItns(this.getDamagedItns());
    concrete.setHangingItns(this.getHangingItns());
    concrete.setOtherItns(this.getOtherItns());
    concrete.setMonthReceived(this.getMonthReceived());
    concrete.setYearReceived(this.getYearReceived());
    concrete.setUsedItns(this.getUsedItns());
    concrete.setUsedEveryNight(this.getUsedEveryNight());
    concrete.setNetsObtained(this.getNetsObtained());
    concrete.setFreeProvider(this.getFreeProvider());
    concrete.setBoughtProvider(this.getBoughtProvider());
    concrete.setWashFrequency(this.getWashFrequency());
    concrete.setRetreated(this.getRetreated());
    concrete.setRetreatmentPeriod(this.getRetreatmentPeriod());
    
    concrete.clearWashed();
    concrete.clearWashInterval();
    
    for(FeverResponse washed : this.getWashed())
    {
      concrete.addWashed(washed);
    }
    
    for(TimeInterval interval : this.getWashInterval())
    {
      concrete.addWashInterval(interval);
    }
    
    String location = this.getSurveyLocation();
    
    if (location != null && !location.equals(""))
    {
      concrete.setSurveyLocation(GeoEntity.searchByGeoId(location));
    }    
    else
    {
      concrete.setSurveyLocation(null);
    }
  }

  private void buildAttributeMap(ITNHouseholdSurvey concrete)
  {
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.STARTDATE, this, ITNHouseholdSurveyView.STARTDATE);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.ENDDATE, this, ITNHouseholdSurveyView.ENDDATE);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.SURVEYLOCATION, this, ITNHouseholdSurveyView.SURVEYLOCATION);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.AGENTFIRSTNAME, this, ITNHouseholdSurveyView.AGENTFIRSTNAME);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.AGENTSURNAME, this, ITNHouseholdSurveyView.AGENTSURNAME);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.QUESTIONNAIRENUMBER, this, ITNHouseholdSurveyView.QUESTIONNAIRENUMBER);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.RESIDENTS, this, ITNHouseholdSurveyView.RESIDENTS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.PREGNANTRESIDENTS, this, ITNHouseholdSurveyView.PREGNANTRESIDENTS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.CHILDRESIDENTS, this, ITNHouseholdSurveyView.CHILDRESIDENTS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.ITNS, this, ITNHouseholdSurveyView.ITNS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.DAMAGEDITNS, this, ITNHouseholdSurveyView.DAMAGEDITNS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.HANGINGITNS, this, ITNHouseholdSurveyView.HANGINGITNS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.OTHERITNS, this, ITNHouseholdSurveyView.OTHERITNS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.MONTHRECEIVED, this, ITNHouseholdSurveyView.MONTHRECEIVED);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.YEARRECEIVED, this, ITNHouseholdSurveyView.YEARRECEIVED);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.USEDITNS, this, ITNHouseholdSurveyView.USEDITNS);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.USEDEVERYNIGHT, this, ITNHouseholdSurveyView.USEDEVERYNIGHT);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.NETSOBTAINED, this, ITNHouseholdSurveyView.NETSOBTAINED);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.FREEPROVIDER, this, ITNHouseholdSurveyView.FREEPROVIDER);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.BOUGHTPROVIDER, this, ITNHouseholdSurveyView.BOUGHTPROVIDER);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.WASHED, this, ITNHouseholdSurveyView.WASHED);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.WASHFREQUENCY, this, ITNHouseholdSurveyView.WASHFREQUENCY);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.WASHINTERVAL, this, ITNHouseholdSurveyView.WASHINTERVAL);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.RETREATED, this, ITNHouseholdSurveyView.RETREATED);
    new AttributeNotificationMap(concrete, ITNHouseholdSurvey.RETREATMENTPERIOD, this, ITNHouseholdSurveyView.RETREATMENTPERIOD);
  }

  @Override
  public void apply()
  {
    ITNHouseholdSurvey concrete = new ITNHouseholdSurvey();

    if (this.hasConcrete())
    {
      concrete = ITNHouseholdSurvey.get(this.getConcreteId());
    }

    // Build the attribute map between ITNHouseholdSurvey and
    // ITNHouseholdSurveyView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ITNHouseholdSurvey.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  @Transaction
  public void applyAll(ITNHouseholdSurveyNet[] nets, ITNHouseholdSurveyTargetGroup[] targetGroups, ITNHouseholdSurveyNonUseReason[] reasons)
  {
    boolean newCase = !this.hasConcrete();

    this.apply();

    if (newCase)
    {
      this.updateParentIds(nets, targetGroups, reasons);
    }

    for (ITNHouseholdSurveyNet net : nets)
    {
      net.apply();
    }

    for (ITNHouseholdSurveyTargetGroup group : targetGroups)
    {
      group.apply();
    }
    
    for(ITNHouseholdSurveyNonUseReason reason : reasons)
    {
      reason.apply();
    }
  }

  @Transaction
  private void updateParentIds(ITNHouseholdSurveyNet[] nets, ITNHouseholdSurveyTargetGroup[] targetGroups, ITNHouseholdSurveyNonUseReason[] reasons)
  {
    for (int i = 0; i < nets.length; i++)
    {
      nets[i] = nets[i].clone(this);
    }

    for (int i = 0; i < targetGroups.length; i++)
    {
      targetGroups[i] = targetGroups[i].clone(this);
    }

    for (int i = 0; i < reasons.length; i++)
    {
      reasons[i] = reasons[i].clone(this);
    }
  }

  @Override
  public ITNHouseholdSurveyNet[] getITNHouseholdSurveyNets()
  {
    // A list of all this household nets plus nets that it doesn't have
    Map<String, ITNHouseholdSurveyNet> nets = loadNets();
    List<ITNHouseholdSurveyNet> list = new LinkedList<ITNHouseholdSurveyNet>();
    Stack<Net> stack = new Stack<Net>();

    for (Net root : Net.getRoots())
    {
      stack.push(root);
    }

    while (!stack.empty())
    {
      // Create a stack to provide sorting on the nets
      // Sort by the net name
      Set<Net> set = new TreeSet<Net>(new OptionComparator());

      Net net = stack.pop();

      set.addAll(net.getAllChildNets().getAll());

      for (Net child : set)
      {
        stack.push(child);
      }

      list.add(nets.get(net.getId()));
    }

    return list.toArray(new ITNHouseholdSurveyNet[list.size()]);
  }

  private Map<String, ITNHouseholdSurveyNet> loadNets()
  {
    Map<String, ITNHouseholdSurveyNet> map = new HashMap<String, ITNHouseholdSurveyNet>();

    if (this.hasConcrete())
    {
      ITNHouseholdSurvey concrete = ITNHouseholdSurvey.get(this.getConcreteId());

      for (ITNHouseholdSurveyNet householdNet : concrete.getAllNetsRel())
      {
        map.put(householdNet.getChildId(), householdNet);
      }
    }

    for (Net net : Net.getAll())
    {
      if (!map.containsKey(net.getId()))
      {
        map.put(net.getId(), new ITNHouseholdSurveyNet(this.getConcreteId(), net.getId()));
      }
    }

    return map;
  }

  @Override
  public ITNHouseholdSurveyTargetGroup[] getITNHouseholdSurveyTargetGroups()
  {
    Set<ITNHouseholdSurveyTargetGroup> set = new TreeSet<ITNHouseholdSurveyTargetGroup>(new GridComparator());

    for (TargetGroupGrid d : TargetGroupGrid.getAll())
    {
      set.add(new ITNHouseholdSurveyTargetGroup(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNHouseholdSurvey concrete = ITNHouseholdSurvey.get(this.getConcreteId());

      for (ITNHouseholdSurveyTargetGroup d : concrete.getAllTargetGroupsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNHouseholdSurveyTargetGroup[set.size()]);
  }
  
  @Override
  public ITNHouseholdSurveyNonUseReason[] getITNHouseholdSurveyNonUseReason()
  {
    Set<ITNHouseholdSurveyNonUseReason> set = new TreeSet<ITNHouseholdSurveyNonUseReason>(new GridComparator());

    for (NonUseReasonGrid d : NonUseReasonGrid.getAll())
    {
      set.add(new ITNHouseholdSurveyNonUseReason(this.getConcreteId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      ITNHouseholdSurvey concrete = ITNHouseholdSurvey.get(this.getConcreteId());

      for (ITNHouseholdSurveyNonUseReason d : concrete.getAllNonUseReasonsRel())
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    return set.toArray(new ITNHouseholdSurveyNonUseReason[set.size()]);
  }

  public static ITNHouseholdSurveyViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    ITNHouseholdSurveyViewQuery query = new ITNHouseholdSurveyViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = STARTDATE;
    }

    SelectablePrimitive selectable = (SelectablePrimitive) query.getComponentQuery().getSelectable(sortAttribute);

    if (isAscending)
    {
      query.ORDER_BY_ASC(selectable);
    }
    else
    {
      query.ORDER_BY_DESC(selectable);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
  
}
