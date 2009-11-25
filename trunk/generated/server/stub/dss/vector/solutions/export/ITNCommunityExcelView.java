package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.SettlementSubdivision;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
import dss.vector.solutions.util.HierarchyBuilder;

public class ITNCommunityExcelView extends ITNCommunityExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -788115041;
  
  public ITNCommunityExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    ITNCommunityDistribution concrete = new ITNCommunityDistribution();
    
    concrete.setStartDate(this.getStartDate());
    concrete.setEndDate(this.getEndDate());
    concrete.setAgentFirstName(this.getAgentFirstName());
    concrete.setAgentSurname(this.getAgentSurname());
    concrete.setItnsReceived(this.getItnsReceived());
    concrete.setBatchNumber(this.getBatchNumber());
    concrete.setEntryType(this.getEntryType());
    concrete.setSold(this.getSold());
    concrete.setCurrencyReceived(this.getCurrencyReceived());
    concrete.setRetrieved(this.getRetrieved());
    concrete.setNumberRetrieved(this.getNumberRetrieved());
    concrete.setPretreated(this.getPretreated());
    concrete.setHouseholdName(this.getHouseholdName());
    concrete.setHouseholdSurname(this.getHouseholdSurname());
    concrete.setResidents(this.getResidents());
    
    concrete.apply();
    
    // Add grids: new ITNCommunityNet[0], new ITNCommunityTargetGroup[0]
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
    exporter.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
    importer.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(String attribute)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SettlementSubdivision.CLASS));
    return new DynamicGeoColumnListener(CLASS, attribute, builder);
  }
}
