package dss.vector.solutions.query;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.geo.GeoHierarchy;

public abstract class SavedSearch extends SavedSearchBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158161320L;

  public SavedSearch()
  {
    super();
  }


  /**
   * Populates and applies this SavedSearch object with the given information in the SavedSearchView.
   *
   * @param view
   * @param savedQuery
   */
  protected void populate(SavedSearchView view)
  {
    String name = view.getQueryName();
    String xml = view.getQueryXml();
    String thematicLayerClass = view.getThematicLayer();

    GeoHierarchy thematicGeoH = GeoHierarchy.getGeoHierarchyFromType(thematicLayerClass);
    MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

    ThematicLayer thematicLayer = new ThematicLayer();
    thematicLayer.setGeoHierarchy(thematicGeoH);

    // Create the view name, which has to be unique as unique as possible
    // without going over the maximum view name size.
    String viewName = "view_"+thematicLayer.getId().substring(0, 16);
    thematicLayer.setViewName(viewName);

    // Geo Style
    GeometryStyle geoStyle = GeometryStyle.convertAttributeGeometryToStyle(geoAttr);
    thematicLayer.setGeometryStyle(geoStyle);

    // text style
    TextStyle textStyle = new TextStyle();
    textStyle.apply();
    thematicLayer.setTextStyle(textStyle);
    thematicLayer.apply();

    this.setQueryName(name);
    this.setQueryXml(xml);
    this.setThematicLayer(thematicLayer);
    this.setQueryViewName(viewName);
    this.apply();

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    mdssUser.addPersistedQueries(this).apply();
  }

}
