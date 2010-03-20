package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.util.HierarchyBuilder;

public class PopulationDataExcelView extends PopulationDataExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256164504563L;
  
  public PopulationDataExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    PopulationData populationData = new PopulationData();
    
    populationData.setGeoEntity(this.getGeoEntity());
    populationData.setYearOfData(this.getYearOfData());
    populationData.setPopulation(this.getPopulation());
    Double rate = this.getGrowthRate();
    if (rate != null)
    {
      populationData.setGrowthRate(rate / 100); // Ticket #822 calls for division by 100 to get a %
    }
    
    populationData.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(YEAROFDATA);
    list.add(POPULATION);
    list.add(GROWTHRATE);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String...params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    OIterator<? extends GeoHierarchy> iterator = PopulationData.getValidGeoHierarchies().getIterator();
    while (iterator.hasNext())
    {
      builder.add(iterator.next());
    }
    iterator.close();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
}
