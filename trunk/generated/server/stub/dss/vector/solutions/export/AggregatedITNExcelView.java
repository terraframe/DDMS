package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.ITNData;
import dss.vector.solutions.intervention.monitor.ITNDataView;
import dss.vector.solutions.intervention.monitor.ITNNet;
import dss.vector.solutions.intervention.monitor.ITNService;
import dss.vector.solutions.intervention.monitor.ITNTargetGroup;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedITNExcelView extends AggregatedITNExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1914412224;
  
  private List<Term>        services;
  private List<Integer>     serviceAmounts;
  private List<Term>        targetGroups;
  private List<Integer>     targetGroupAmounts;
  private List<Term>        itnTypes;
  private List<Integer>     itnTypeAmounts;
  
  public AggregatedITNExcelView()
  {
    super();
    services = new LinkedList<Term>();
    serviceAmounts = new LinkedList<Integer>();
    targetGroups = new LinkedList<Term>();
    targetGroupAmounts = new LinkedList<Integer>();
    itnTypes = new LinkedList<Term>();
    itnTypeAmounts = new LinkedList<Integer>();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoEntity geoEntity = getGeoEntity();

    PeriodType periodType = ExcelEnums.getPeriodType(this.getPeriodType());
    
    ITNDataView data = ITNData.searchByGeoEntityAndEpiDate(geoEntity, periodType, this.getPeriod(), this.getPeriodYear());

    // Lock the instance if it exists
    if(data.hasConcrete())
    {
      data = ITNData.lockView(data.getConcreteId());
    }    
    
    data.setBatchNumber(this.getBatchNumber());
    data.setReceivedForTargetGroups(this.getReceivedForTargetGroups());
    data.setReceivedForCommunityResponse(this.getReceivedForCommunityResponse());
    data.setNumberDistributed(this.getNumberDistributed());
    data.setNumberSold(this.getNumberSold());
    data.setCurrencyReceived(this.getCurrencyReceived());
    
    ITNService[] serviceArray = new ITNService[services.size()];
    for (int i = 0; i < serviceArray.length; i++)
    {
      if (i < serviceAmounts.size())
      {
        serviceArray[i] = new ITNService(data.getConcreteId(), services.get(i).getId());
        serviceArray[i].setAmount((serviceAmounts.get(i)));
      }
    }
    
    ITNTargetGroup[] targetGroupArray = new ITNTargetGroup[targetGroups.size()];
    for (int i = 0; i < targetGroupArray.length; i++)
    {
      if (i < targetGroupAmounts.size())
      {
        targetGroupArray[i] = new ITNTargetGroup(data.getConcreteId(), targetGroups.get(i).getId());
        targetGroupArray[i].setAmount((targetGroupAmounts.get(i)));
      }
    }
    
    ITNNet[] itnTypeArray = new ITNNet[itnTypes.size()];
    for (int i = 0; i < itnTypeArray.length; i++)
    {
      if (i < itnTypeAmounts.size())
      {
        itnTypeArray[i] = new ITNNet(data.getConcreteId(), itnTypes.get(i).getId());
        itnTypeArray[i].setAmount((itnTypeAmounts.get(i)));
      }
    }
    
    data.applyAll(itnTypeArray, targetGroupArray, serviceArray);
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(PERIODTYPE);
    list.add(PERIOD);
    list.add(PERIODYEAR);
    list.add(BATCHNUMBER);
    list.add(RECEIVEDFORTARGETGROUPS);
    list.add(RECEIVEDFORCOMMUNITYRESPONSE);
    list.add(NUMBERDISTRIBUTED);
    list.add(NUMBERSOLD);
    list.add(CURRENCYRECEIVED);
    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(new AggregatedITNListener());
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
    exporter.addListener(new AggregatedITNListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
  public void addService(Term grid, Integer amount)
  {
    services.add(grid);
    serviceAmounts.add(amount);
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
