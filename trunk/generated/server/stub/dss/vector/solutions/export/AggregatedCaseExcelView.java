package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.UnknownAgeGroupException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQuery;
import dss.vector.solutions.surveillance.AggregatedCaseSearchView;
import dss.vector.solutions.surveillance.AggregatedCaseView;
import dss.vector.solutions.surveillance.PeriodType;
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
      acv.setCases(this.getCases());
      acv.setPositiveCases(this.getPositiveCases());
      acv.setNegativeCases(this.getNegativeCases());
      acv.setDeaths(this.getDeaths());
    }
    return this.acv;
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(DISPLAYLABEL);
    list.add(CASES);
    list.add(POSITIVECASES);
    list.add(NEGATIVECASES);
    list.add(DEATHS);
    return list;
  }

  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
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

  public static PeriodType getPeriodTypeByLabel(String label)
  {
    if (label == null || label.equals(""))
    {
      return null;
    }

    for (PeriodType e : PeriodType.values())
    {
      if (e.getDisplayLabel().equalsIgnoreCase(label) ||
          e.getEnumName().equalsIgnoreCase(label))
      {
        return e;
      }
    }
    String message = "[" + label + "] is not a valid display label for [" + PeriodType.CLASS + "]";
    throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(PeriodType.CLASS));
  }
}
