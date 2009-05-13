package dss.vector.solutions.query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.metadata.MdBusiness;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoServerReloader;


public class MapUtil extends MapUtilBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242080109170L;

  public MapUtil()
  {
    super();
  }

  /**
  *
  *
  * @param universalLayers
  * @param valueQuery
  * @param savedSearch
  * @param thematicLayer
  * @return
  */
 public static String generateLayers(String[] universalLayers, ValueQuery valueQuery, SavedSearch savedSearch, ThematicLayer thematicLayer)
 {
   String sql = valueQuery.getSQL();

   // reload the view for the thematic layer
   String viewName = thematicLayer.getViewName();

   if(thematicLayer.getViewCreated().booleanValue())
   {
     Database.dropView(viewName, sql);
   }

   Database.createView(viewName, sql);

   thematicLayer.appLock();
   thematicLayer.setViewCreated(true);
   thematicLayer.apply();

   String sessionId = Session.getCurrentSession().getId();

   GeoHierarchy thematicGeoH = thematicLayer.getGeoHierarchy();
   MdAttributeGeometry geoAttr = thematicGeoH.getGeometry();

   GeoServerReloader.reload(sessionId, viewName, geoAttr);


   // JSON representation of the layers in the map. This is used
   // in Javascript to generated an OpenLayers call to GeoServer.
   JSONArray layers = new JSONArray();

   try
   {
     String geoserverPath = GeoServerReloader.getGeoServerURL();
     String baseView = QueryConstants.MDSS_NAMESPACE + ":" + viewName.toLowerCase();
     JSONObject baseLayer = new JSONObject();

     String sldFile = formatSLD(thematicLayer);

     baseLayer.put("view", baseView);
     baseLayer.put("sld", sldFile);
     baseLayer.put("geoserverURL", geoserverPath);
     layers.put(baseLayer);
   }
   catch (JSONException e)
   {
     String error = "Unable to define the base (thematic) layer.";
     throw new ProgrammingErrorException(error, e);
   }

   // create views (if needed) for all other layers
   for (String layerId : universalLayers)
   {
     // TODO return null if count(*) == 0 and don't add
     // to layers (do inside createViewTable())
     UniversalLayer layer = UniversalLayer.get(layerId);
     GeoHierarchy geoH = layer.getGeoHierarchy();

     boolean includeLayer = geoH.createViewTable(sessionId);
     if (includeLayer)
     {
       String layerView = layer.getViewName();
       String namespacedView = QueryConstants.MDSS_NAMESPACE + ":" + layerView;

       JSONObject layerObj = new JSONObject();

       try
       {
         String sldFile = formatSLD(layer);


         layerObj.put("view", namespacedView);
         layerObj.put("sld", sldFile);
       }
       catch (JSONException e)
       {
         MdBusiness md = geoH.getGeoEntityClass();
         String error = "Unable to define the layer for [" + md.getDisplayLabel().getValue() + "].";
         throw new ProgrammingErrorException(error, e);
       }

       layers.put(layerObj);
     }
   }

   return layers.toString();
 }

 /**
  * Formats the SLD file (pathing and filename concatenation) for the given layer.
  *
  * @param layer
  * @return
  */
 private static String formatSLD(Layer layer)
 {
   WebFile file = WebFile.get(layer.getSldFile());
   String fullWebDir = LocalProperties.getWebDirectory();

   if(fullWebDir.endsWith("/"))
   {
     fullWebDir = fullWebDir.substring(0, fullWebDir.length()-1);
   }
   String webDir = fullWebDir.substring(fullWebDir.lastIndexOf("/"));
   if(webDir.startsWith("/"))
   {
     webDir = webDir.substring(1);
   }
   String filePath = webDir+ "/" + file.getFilePath() + file.getFileName() + "." + file.getFileExtension();

   // Generated a random query string to force GeoServer to not cache the SLD
   String random = String.valueOf(Math.random());
   random = random.substring(random.length()-6);

   return filePath+ "?a="+random;
 }


}
