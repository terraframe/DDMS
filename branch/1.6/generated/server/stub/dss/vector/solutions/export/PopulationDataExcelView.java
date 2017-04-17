package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.PopulationDataQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
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
    GeoEntity geo = this.getGeoEntity();
    Integer year = this.getYearOfData();
    
    PopulationDataQuery query = new PopulationDataQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geo));
    query.WHERE(query.getYearOfData().EQ(year));
    OIterator<? extends PopulationData> iterator = query.getIterator();
    
    if (iterator.hasNext())
    {
      populationData = iterator.next();
      populationData.lock();
    }
    else
    {
      populationData.setGeoEntity(geo);
      populationData.setYearOfData(year);
    }
    
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
    exporter.addListener(createExcelGeoListener(null));
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }
  
  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    OIterator<? extends GeoHierarchy> iterator = PopulationData.getValidGeoHierarchies().getIterator();
    while (iterator.hasNext())
    {
      builder.add(iterator.next());
    }
    iterator.close();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
  
}
