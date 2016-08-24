package dss.vector.solutions.geo;

import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.export.ControlInterventionExcelView;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.util.HierarchyBuilder;

public class GeoSynonymArrayExcelView extends GeoSynonymArrayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 151005506;
  
  public GeoSynonymArrayExcelView()
  {
    super();
  }
  
  @Override
  @Transaction
  public void apply()
  {
    GeoSynonymArrayView gs = new GeoSynonymArrayView();
    gs.setGeoEntity(getGeoEntity());
    
    String[] names = this.getSynonymNames().split(",");
    GeoSynonymView[] synonymViews = new GeoSynonymView[names.length];
    for (int i = 0; i < names.length ; ++i)
    {
      GeoSynonymView view = new GeoSynonymView();
      view.setSynonymName(names[i]);
      synonymViews[i] = view;
    }
    
    gs.applyWithSynonyms(synonymViews);
  }
  
  public static List<String> customAttributeOrder()
  {
    List<String> list = ControlInterventionExcelView.customAttributeOrder();
    list.add(GEOENTITY);
    list.add(SYNONYMNAMES);
    return list;
  }
  
  public static void setupExportListener(ExcelExporter exporter, String... params)
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
    for (GeoHierarchy hierarchy : GeoHierarchy.getAll())
    {
      builder.add(hierarchy);
    }
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder, importer);
  }
  
}
