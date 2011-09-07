package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.excel.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNCommunityNet;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class ITNCommunityExcelView extends ITNCommunityExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256836182017L;

  private List<Term>        targetGroups;
  private List<Integer>     targetGroupAmounts;
  private List<Term>        itnTypes;
  private List<Integer>     itnTypeAmounts;
  
  public ITNCommunityExcelView()
  {
    super();
    targetGroups = new LinkedList<Term>();
    targetGroupAmounts = new LinkedList<Integer>();
    itnTypes = new LinkedList<Term>();
    itnTypeAmounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ITNCommunityDistributionView view = new ITNCommunityDistributionView();
    view.setStartDate(this.getStartDate());
    view.setEndDate(this.getEndDate());
    view.setAgentFirstName(this.getAgentFirstName());
    view.setAgentSurname(this.getAgentSurname());
    view.setItnsReceived(this.getItnsReceived());
    view.setHasBatchNumber(this.getBatchNumber().length()>0);
    view.setBatchNumber(this.getBatchNumber());
    view.setEntryType(this.getEntryType());
    view.setHouseholdName(this.getHouseholdName());
    view.setHouseholdSurname(this.getHouseholdSurname());

    GeoEntity address = this.getHouseholdAddress();
    if (address!=null)
    {
      view.setHouseholdAddress(address.getGeoId());
    }
    
    view.setResidents(this.getResidents());
    
    GeoEntity location = this.getDistributionLocation();
    if (location!=null)
    {
      view.setDistributionLocation(location.getGeoId());
    }
    
    view.setSold(this.getSold());
    view.setCurrencyReceived(this.getCurrencyReceived());
    view.setRetrieved(this.getRetrieved());
    view.setNumberRetrieved(this.getNumberRetrieved());
    view.setPretreated(this.getPretreated());
    
    ITNCommunityTargetGroup[] targetGroupArray = new ITNCommunityTargetGroup[targetGroups.size()];
    for (int i = 0; i < targetGroupArray.length; i++)
    {
      if (i < targetGroupAmounts.size())
      {
        targetGroupArray[i] = new ITNCommunityTargetGroup(view.getConcreteId(), targetGroups.get(i).getId());
        targetGroupArray[i].setAmount((targetGroupAmounts.get(i)));
      }
    }
    
    ITNCommunityNet[] itnTypeArray = new ITNCommunityNet[itnTypes.size()];
    for (int i = 0; i < itnTypeArray.length; i++)
    {
      if (i < itnTypeAmounts.size())
      {
        itnTypeArray[i] = new ITNCommunityNet(view.getConcreteId(), itnTypes.get(i).getId());
        itnTypeArray[i].setAmount((itnTypeAmounts.get(i)));
      }
    }
    
    view.applyAll(itnTypeArray, targetGroupArray);
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(AGENTFIRSTNAME);
    list.add(AGENTSURNAME);
    list.add(ITNSRECEIVED);
    list.add(BATCHNUMBER);
    list.add(ENTRYTYPE);
    list.add(HOUSEHOLDNAME);
    list.add(HOUSEHOLDSURNAME);
    list.add(RESIDENTS);
    list.add(SOLD);
    list.add(CURRENCYRECEIVED);
    list.add(RETRIEVED);
    list.add(NUMBERRETRIEVED);
    list.add(PRETREATED);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
    exporter.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
    exporter.addListener(new ITNCommunityDistributionListener());
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new ITNCommunityDistributionListener());
    context.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
    context.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(String attribute)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, attribute, builder);
  }

  public void addTargetGroup(Term grid, Integer amount)
  {
    targetGroups.add(grid);
    targetGroupAmounts.add(amount);
  }

  public void addITNType(Term grid, Integer amount)
  {
    itnTypes.add(grid);
    itnTypeAmounts.add(amount);
  }
}
