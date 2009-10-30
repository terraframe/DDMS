package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;

import dss.vector.solutions.PersonView;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Ward;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

public class PopulationDataExcelView extends PopulationDataExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    populationData.setGrowthRate(this.getGrowthRate());
    
    populationData.apply();
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
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
  
}
