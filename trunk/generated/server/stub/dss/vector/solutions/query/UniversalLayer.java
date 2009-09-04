package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;

public class UniversalLayer extends UniversalLayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241144021057L;

  public UniversalLayer()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

  /**
   * Creates a new layer with default styles.
   *
   * @param savedSearchId
   * @param layerClass
   * @return The created Layer
   */
  @Transaction
  public static Layer createLayer(String savedSearchId, String layerClass)
  {
    GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(layerClass);
    MdBusiness md = geoH.getGeoEntityClass();
    String viewName = md.getTypeName().toLowerCase() + QueryConstants.VIEW_NAME_SUFFIX;;

    UniversalLayer layer = new UniversalLayer();
    layer.setGeoHierarchy(geoH);
    layer.setViewName(viewName);

    // geo style
    MdAttributeGeometry attrGeoMd = geoH.getGeometry();
    GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(attrGeoMd);
    layer.setGeometryStyle(geoStyle);

    // text style
    TextStyle textStyle = new TextStyle();
    textStyle.apply();
    layer.setTextStyle(textStyle);

    layer.apply();

    SavedSearch search = SavedSearch.get(savedSearchId);
    search.addDefinesLayers(layer).apply();

    return layer;
  }

  public LayerView getAsView()
  {
    LayerView view = super.getAsView();
    
    view.setIsThematic(false);
    view.setUniversalType(this.getGeoHierarchy().getGeoEntityClass().definesType());
    
    return view;
  }  
  
}
