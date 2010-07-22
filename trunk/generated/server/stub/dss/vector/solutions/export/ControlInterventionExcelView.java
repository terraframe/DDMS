package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.intervention.monitor.ControlInterventionViewQuery;
import dss.vector.solutions.util.HierarchyBuilder;

public class ControlInterventionExcelView extends ControlInterventionExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1186841377;
  private ControlInterventionView civ;
  
  public ControlInterventionExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    getControlPoint();
    civ.apply();
  }
  
  protected ControlInterventionView getControlPoint()
  {
    if (this.civ==null)
    {
      this.civ = new ControlInterventionView();
      
      this.civ.setGeoEntity(this.getGeoEntity());
      this.civ.setStartDate(this.getStartDate());
      this.civ.setEndDate(this.getEndDate());
      this.civ.setComments(this.getComments());
      
//      ControlInterventionViewQuery search = ControlInterventionView.search(this.civ, null, false, 10, 1);
//      OIterator<? extends ControlInterventionView> iterator = search.getIterator();
//      if (iterator.hasNext())
//      {
//        this.civ = iterator.next();
//      }
      this.civ = ControlInterventionView.getIntervention(this.civ);
    }
    return this.civ;
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(STARTDATE);
    list.add(ENDDATE);
    list.add(COMMENTS);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }
  
  public static void setupImportListener(ImportContext context, String... params)
  {
    context.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    HierarchyBuilder builder = new HierarchyBuilder();
    for (GeoHierarchy hierarchy : GeoHierarchy.getAllUrban())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
