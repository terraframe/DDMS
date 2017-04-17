package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.UnknownAgeGroupException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQuery;
import dss.vector.solutions.surveillance.AggregatedCaseSearchView;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.util.HierarchyBuilder;

public class AggregatedCaseExcelView extends AggregatedCaseExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246505362370L;
  private AggregatedCaseView acv;

  public AggregatedCaseExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    getAggregatedCaseView();
  }

  protected AggregatedCaseView getAggregatedCaseView()
  {
    if (this.acv==null)
    {
      AggregatedAgeGroupQuery query = new AggregatedAgeGroupQuery(new QueryFactory());
      query.WHERE(query.getDisplayLabel().EQ(this.getDisplayLabel()));
      query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
      OIterator<? extends AggregatedAgeGroup> iterator = query.getIterator();
      
      if (!iterator.hasNext())
      {
        throw new UnknownAgeGroupException();
      }
      
      AggregatedAgeGroup ageGroup = iterator.next();
      iterator.close();
      acv = AggregatedCaseSearchView.searchByDates(this.getGeoEntity(), this.getStartDate(), this.getEndDate(), ageGroup);
    }
    return this.acv;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(DISPLAYLABEL);
    return list;
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(createExcelGeoListener(importer));
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener(null));
  }

  private static DynamicGeoColumnListener createExcelGeoListener(ExcelImportManager importer)
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllPoliticals())
    {
      builder.add(hierarchy);
    }
    builder.add(GeoHierarchy.getGeoHierarchyFromType(HealthFacility.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
}
