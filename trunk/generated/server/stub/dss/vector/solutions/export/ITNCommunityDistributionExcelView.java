package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.District;
import dss.vector.solutions.geo.generated.SettlementSubdivision;
import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.util.HierarchyBuilder;

public class ITNCommunityDistributionExcelView extends ITNCommunityDistributionExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256836182017L;
  
  public ITNCommunityDistributionExcelView()
  {
    super();
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
    view.setBatchNumber(this.getBatchNumber());
    view.setEntryType(this.getEntryType());
    view.setHouseholdName(this.getHouseholdName());
    view.setHouseholdSurname(this.getHouseholdSurname());
    view.setHouseholdAddress(this.getHouseholdAddress().getGeoId());
    view.setResidents(this.getResidents());
    view.setDistributionLocation(this.getDistributionLocation().getGeoId());
    view.setSold(this.getSold());
    view.setCurrencyReceived(this.getCurrencyReceived());
    view.setRetrieved(this.getRetrieved());
    view.setNumberRetrieved(this.getNumberRetrieved());
    view.setPretreated(this.getPretreated());
    view.apply();
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
    exporter.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener(DISTRIBUTIONLOCATION));
    importer.addListener(createExcelGeoListener(HOUSEHOLDADDRESS));
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(String attribute)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SettlementSubdivision.CLASS));
    return new DynamicGeoColumnListener(CLASS, attribute, builder);
  }
}
