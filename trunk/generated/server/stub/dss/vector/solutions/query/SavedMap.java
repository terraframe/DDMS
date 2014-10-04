package dss.vector.solutions.query;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;







//import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeEnumerationDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.database.DatabaseException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.util.FileIO;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.UserSettings;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.util.ShapefileExporter;

public class SavedMap extends SavedMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252823927;

  public SavedMap()
  {
    super();
  }

  @Override
  public String toString()
  {
    return this.getMapName();
  }
  
  @Override
  public void delete()
  {
    List<? extends MapImage> savedImages = this.getAllHasImage().getAll();
    for(MapImage image : savedImages){
      image.removeMapImage(image.getId(), this.getId());
    }
    super.delete();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && !this.isAppliedToDB())
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public LayerViewQuery getAllLayers()
  {
    QueryFactory f = new QueryFactory();
    LayerViewQuery q = new LayerViewQuery(f, this.getId());

    return q;
  }

  /**
   * Refreshes the map data, including rerunning the queries and creating DB
   * views.
   */
  @Override
  @Authenticate
  public String refreshMap(String currentMapId)
  {
    JSONObject mapData;
    JSONArray layersJSON;
    JSONArray savedImagesJSON;
    JSONArray savedTextJSON;

    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(getOrderedLayers(), false);
    String sessionId = Session.getCurrentSession().getId();
    MapUtil.reload(sessionId, layersVQ);
    String appName = CommonProperties.getDeployAppName();
    try
    {
      String geoserverPath = MapUtil.getGeoServerRemoteURL();
      String geoserverLocalPath = MapUtil.getGeoServerLocalURL();
      
      // this is a hack until username, password, and sld path are put in 
      // properties files.
      String sldPath = geoserverLocalPath.replace("geoserver", appName)+"/";
      
      mapData = new JSONObject();
      layersJSON = new JSONArray();
      savedImagesJSON = new JSONArray();
      savedTextJSON = new JSONArray();

      mapData.put("geoserverURL", geoserverPath);
      mapData.put("sldURL", sldPath);
      mapData.put("layers", layersJSON);
      mapData.put("savedImages", savedImagesJSON);
      mapData.put("savedTextElements", savedTextJSON);
    }
    catch (JSONException e)
    {
      String error = "Could not create the mapping data.";
      throw new ProgrammingErrorException(error, e);
    }

    // Create the JSON for the layers that will be passed to OpenLayers
    List<Layer> bboxLayers = new LinkedList<Layer>();
    int count = 0;
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    while (iter.hasNext())
    {
      Layer layer = iter.next();

      String namespacedView = appName + ":" + layer.getViewName();
      String sldFile = MapUtil.formatSLD(layer);

      JSONObject layerJSON = new JSONObject();
      try
      {
        layerJSON.put("view", namespacedView);
        layerJSON.put("sld", sldFile);
        layerJSON.put("opacity", layer.getOpacity());
        layerJSON.put("id", layer.getId());
        
        // Always add the base layer to the bounding box
        if (count == 0 || layer.getAddToBBox())
        {
          bboxLayers.add(layer);
        }

        // Add the legend if enabled
        if (layer.getEnableLegend())
        {
          JSONObject legend = new JSONObject();
          legend.put("title", layer.getLegendTitle());
          legend.put("showLegendBorder", layer.getShowLegendBorder());
          legend.put("fontTitleFamily", layer.getLegendTitleFontFamily());
          legend.put("fontTitleFill", layer.getLegendTitleFontFill());
          legend.put("fontTitleSize", layer.getLegendTitleFontSize());
          legend.put("fontTitleStyle", layer.getLegendTitleFontStyles().get(0).name().toLowerCase());
          legend.put("fontFamily", layer.getLegendFontFamily());
          legend.put("fontFill", layer.getLegendFontFill());
          legend.put("fontSize", layer.getLegendFontSize());
          legend.put("fontStyle", layer.getLegendFontStyles().get(0).name().toLowerCase());
          legend.put("legendXPosition", layer.getLegendXPosition());
          legend.put("legendYPosition", layer.getLegendYPosition());

          MdAttributeDAO md = (MdAttributeDAO) MdAttributeDAO.get(layer.getValue(Layer.LEGENDCOLOR));
          String colorAttribute = md.definesAttribute();

          if (layer.getCreateRawLegend())
          {
            legend.put("createRawLegend", true);
            legend.put("color", layer.getDefaultStyles().getValue(colorAttribute));
          }
          else
          {
            JSONArray categories = new JSONArray();
            legend.put("categories", categories);

            List<? extends AbstractCategory> cats = layer.getAllHasCategory().getAll();
            CategorySorter.sort(cats);

            for (AbstractCategory cat : cats)
            {
              JSONObject category = new JSONObject();

              if (cat instanceof RangeCategory)
              {
                category.put("lower", ( (RangeCategory) cat ).getLowerBoundStr());
                category.put("upper", ( (RangeCategory) cat ).getUpperBoundStr());
              }
              else
              {
                category.put("exact", ( (NonRangeCategory) cat ).getExactValueStr());
              }
              category.put("color", cat.getStyles().getValue(colorAttribute));
              categories.put(category);
            }
          }
          layerJSON.put("legend", legend);
        }
        else
        {
          layerJSON.put("legend", JSONObject.NULL);
        }
        layersJSON.put(layerJSON);
        count++;
      }
      catch (JSONException e)
      {
        String error = "Could not produce the information for the layer [" + layer.getLayerName() + "]";
        throw new ProgrammingErrorException(error, e);
      }
    }
    
    SavedMap currentMap = SavedMap.get(currentMapId);

    // restrict the map bounds to the applicable layers
    // unless a saved map has a saved bounding box
    try
    {
      String mapCenter = currentMap.getMapCenter();
      if(mapCenter != ""){
        JSONObject mapCenterObj = new JSONObject(mapCenter);
        JSONArray mapBounds = new JSONArray();
        mapBounds.put(mapCenterObj.getString("left"));
        mapBounds.put(mapCenterObj.getString("bottom"));
        mapBounds.put(mapCenterObj.getString("right"));
        mapBounds.put(mapCenterObj.getString("top"));
        mapData.put("bbox", mapBounds);
      }
      else{
        mapData.put("bbox", MapUtil.getThematicBBox(bboxLayers));
      }
    }
    catch (JSONException e)
    {
      String error = "Could not produce the bounding box.";
      throw new ProgrammingErrorException(error, e);
    }
    

    List<? extends MapImage> mapImages = currentMap.getAllHasImage().getAll();
    
    // Build json for all instances of image related to this map
    for(MapImage image : mapImages){
      try{
        JSONObject imageInstanceJSONObj = new JSONObject();
        imageInstanceJSONObj.put("filePath", image.getImageFilePath());
        imageInstanceJSONObj.put("fileName", image.getImageName());
        imageInstanceJSONObj.put("imageXPosition", image.getImageXPosition());
        imageInstanceJSONObj.put("imageYPosition", image.getImageYPosition());
        imageInstanceJSONObj.put("imageId", image.getCustomImageId());
        savedImagesJSON.put(imageInstanceJSONObj);
      }
      catch (JSONException e){
        String error = "Could collect map image info.";
        throw new ProgrammingErrorException(error, e);
      }
    }
    
    
    List<? extends TextElement> textElements = currentMap.getAllHasTextElement().getAll();
    
    // Build json for all instances of image related to this map
    for(TextElement text : textElements){
      try{
        JSONObject textInstanceJSONObj = new JSONObject();
        textInstanceJSONObj.put("textValue", text.getTextValue());
        textInstanceJSONObj.put("fontColor", text.getFontColor());
        textInstanceJSONObj.put("fontFamily", text.getFontFamily());
        textInstanceJSONObj.put("fontSize", text.getFontSize());
        textInstanceJSONObj.put("fontStyle", text.getFontStyle());
        textInstanceJSONObj.put("textXPosition", text.getTextXPosition());
        textInstanceJSONObj.put("textYPosition", text.getTextYPosition());
        textInstanceJSONObj.put("textId", text.getCustomTextElementId());
        savedTextJSON.put(textInstanceJSONObj);
      }
      catch (JSONException e){
        String error = "Could collect text element info.";
        throw new ProgrammingErrorException(error, e);
      }
    }
    
    return mapData.toString();
  };

  /**
   * Returns the layers of this map in order of position on map, starting with
   * the base layer at index 0.
   * 
   * @return
   */
  private Layer[] getOrderedLayers()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery hasQ = new HasLayersQuery(f);

    ValueQuery vq = new ValueQuery(f);
    vq.SELECT(hasQ.childId("child_id"), hasQ.getLayerPosition("layerPosition"));
    vq.WHERE(hasQ.parentId().EQ(this.getId()));
    vq.ORDER_BY_ASC(hasQ.getLayerPosition());

    Map<String, Integer> positions = new HashMap<String, Integer>();
    for (ValueObject o : vq.getIterator().getAll())
    {
      positions.put(o.getValue("child_id"), Integer.valueOf(o.getValue("layerPosition")));
    }

    Layer[] ordered = new Layer[positions.size()];
    for (Layer layer : this.getAllLayer().getAll())
    {
      ordered[positions.get(layer.getId())] = layer;
    }

    return ordered;
  }
  
  /**
   * Returns the instances of MapImage for this map 
   * 
   * @return
   */
  public MapImage[] getMapImages()
  {
    
    QueryFactory f = new QueryFactory();
    MapImageQuery q = new MapImageQuery(f);
    OIterator<? extends MapImage> iterator = q.getIterator();

    try{
      List<? extends MapImage> all = iterator.getAll();
      MapImage[] array = all.toArray(new MapImage[all.size()]);
      
      return array;
    }
    finally{
      iterator.close();
    }

  }
  

  @Override
  public InputStream exportShapefile()
  {
    List<? extends Layer> layers = this.getAllLayer().getAll();
    if (layers.size() == 0)
    {
      String error = "The map [] does not have any layers to export.";
      throw new NoLayersInExportException(error);
    }

    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(getOrderedLayers(), false);
    Iterator<Layer> iter = layersVQ.keySet().iterator();
    List<Layer> layersInMap = new LinkedList<Layer>();
    while (iter.hasNext())
    {
      layersInMap.add(iter.next());
    }

    ShapefileExporter exporter = new ShapefileExporter();

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    exporter.export(layersInMap, output);

    InputStream input = new ByteArrayInputStream(output.toByteArray());

    return input;
  }

  private void copyStyles(Styles source, Styles dest)
  {
    for (MdAttributeDAOIF mdAttr : source.getMdClass().definesAttributes())
    {
      if (!mdAttr.isSystem() && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
      {
        String name = mdAttr.definesAttribute();

        if (mdAttr instanceof MdAttributeEnumerationDAOIF)
        {
          String value = source.getEnumValues(name).get(0).getId();
          dest.addEnumItem(name, value);
        }
        else
        {
          String value = source.getValue(name);
          dest.setValue(name, value);
        }
      }
    }
  }

  /**
   * Creates a SavedMap and clones the given layers to be applied as layers on
   * the created SavedMap.
   */
  @Override
  @Transaction
  @Authenticate
  public LayerViewQuery createFromExisting(String existingMapId)
  {
    SavedMap existingMap = SavedMap.get(existingMapId);
    
    boolean returnExisting = true;
    if (this.isNew())
    {
      this.apply();
    }
    else
    {
      returnExisting = false;
      for (Layer layer : this.getAllLayer().getAll())
      {
        layer.delete();
      }
      
      for (MapImage image : this.getAllHasImage().getAll())
      {
        image.delete();
      }
      
      for (TextElement text : this.getAllHasTextElement().getAll())
      {
        text.delete();
      }
    }
    

    // copy the layers from the existing map
    for (LayerView existingLayerView : existingMap.getAllLayers().getIterator().getAll())
    {
      Layer existingLayer = Layer.get(existingLayerView.getLayerId());
      Layer layer = new Layer();

      for (MdAttributeDAOIF mdAttr : layer.getMdClass().definesAttributes())
      {
        if (!mdAttr.isSystem() && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
        {
          String name = mdAttr.definesAttribute();

          if (mdAttr instanceof MdAttributeEnumerationDAOIF)
          {
            String value = existingLayer.getEnumValues(name).get(0).getId();
            layer.addEnumItem(name, value);
          }
          else if (!name.equals(Layer.SLDFILE) && !name.equals(Layer.DEFAULTSTYLES))
          {
            String value = existingLayer.getValue(name);
            layer.setValue(name, value);
          }
        }
      }

      // copy the default layer styles
      Styles existingStyles = existingLayer.getDefaultStyles();
      Styles styles = new Styles();
      copyStyles(existingStyles, styles);
      styles.apply();

      layer.setDefaultStyles(styles);
      layer.apply();

      // copy all the categories on the layer
      for (AbstractCategory category : existingLayer.getAllHasCategory().getAll())
      {
        AbstractCategory copy;
        try
        {
          copy = (AbstractCategory) LoaderDecorator.load(category.getType()).newInstance();
        }
        catch (Throwable t)
        {
          throw new ProgrammingErrorException(t);
        }

        for (MdAttributeDAOIF mdAttr : copy.getMdClass().definesAttributes())
        {
          String name = mdAttr.definesAttribute();
          if (!mdAttr.isSystem() && !name.equals(AbstractCategory.STYLES)  && !mdAttr.definesAttribute().equals(Styles.KEYNAME))
          {
            copy.setValue(name, category.getValue(name));
          }
        }

        Styles cStyles = new Styles();
        copyStyles(category.getStyles(), cStyles);
        cStyles.apply();

        copy.setStyles(cStyles);
        copy.apply();

        layer.addHasCategory(copy).apply();
      }

      // copy the relationship between the SavedMap and Layer
      HasLayers rel = this.addLayer(layer);
      rel.setLayerPosition(existingLayerView.getLayerPosition());
      rel.apply();
    }
    
    
    // Copy instances of mapImage
    List<? extends MapImage> mapImages = existingMap.getAllHasImage().getAll();
    for (MapImage image : mapImages)
    {
      MapImage newImage = new MapImage();
      newImage.setImageName(image.getImageName());
      newImage.setImageFilePath(image.getImageFilePath());
      newImage.setImageXPosition(image.getImageXPosition());
      newImage.setImageYPosition(image.getImageYPosition());
      newImage.setCustomImageId(image.getCustomImageId());
      newImage.apply();

      HasImage newHasImage = this.addHasImage(newImage);
      newHasImage.apply();
    }
    
    
    // Copy instances of TextElement
    List<? extends TextElement> textElements = existingMap.getAllHasTextElement().getAll();
    for (TextElement textElement : textElements)
    {
      TextElement newTextElement = new TextElement();
      newTextElement.setTextValue(textElement.getTextValue());
      newTextElement.setFontColor(textElement.getFontColor());
      newTextElement.setFontFamily(textElement.getFontFamily());
      newTextElement.setFontSize(textElement.getFontSize());
      newTextElement.setFontStyle(textElement.getFontStyle());
      newTextElement.setCustomTextElementId(textElement.getCustomTextElementId());
      newTextElement.setTextXPosition(textElement.getTextXPosition());
      newTextElement.setTextYPosition(textElement.getTextYPosition());
      newTextElement.apply();

      HasTextElement newHasText = this.addHasTextElement(newTextElement);
      newHasText.apply();
    }
    

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    return defaultMap.getAllLayers();
  }

  
  /**
   * Removes the given layer from this SavedMap and reorders the other layers
   * accordingly.
   */
  @Override
  @Transaction
  public void deleteLayerFromMap(String layerId)
  {
    Layer layer = Layer.get(layerId);
    layer.delete();

    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();

    try
    {
      int count = 0;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Moves the given layer to the desired position. All other layers are
   * repositioned accordingly.
   * 
   */
  @Override
  @Transaction
  public void moveLayerOnMap(String layerId, Integer layerPosition)
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);
    q.WHERE(q.parentId().EQ(this.getId()));

    q.ORDER_BY_ASC(q.getLayerPosition());

    OIterator<? extends HasLayers> iter = q.getIterator();
    List<HasLayers> rels = new LinkedList<HasLayers>();

    try
    {
      int oldIndex = 0;
      boolean found = false;
      while (iter.hasNext())
      {
        HasLayers rel = iter.next();
        if (rel.getChildId().equals(layerId))
        {
          found = true;
        }
        else if (!found)
        {
          oldIndex++;
        }

        rels.add(rel);
      }

      // swap the moved layer position
      rels.add(layerPosition, rels.remove(oldIndex));

      // now reset all layer indexes
      int count = 0;
      for (HasLayers rel : rels)
      {
        rel.appLock();
        rel.setLayerPosition(count++);
        rel.apply();
      }
    }
    finally
    {
      iter.close();
    }
  }

  public long getLayerCount()
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery q = new HasLayersQuery(f);

    q.WHERE(q.parentId().EQ(this.getId()));

    return q.getCount();
  }

  /**
   * Returns all non-default maps from all users. This way users can view maps
   * that others have created.
   * 
   * @return
   */
  public static SavedMapQuery getAllSavedMaps()
  {
    QueryFactory f = new QueryFactory();
    SavedMapQuery q = new SavedMapQuery(f);

    q.WHERE(q.getType().NE(DefaultSavedMap.CLASS));
    q.AND(q.getDisease().EQ(Disease.getCurrent()));

    return q;
  }

  /**
   * Loads the default map.
   * 
   * @param map
   *          FIXME the map parameter may not be needed
   * @return
   */
  @Transaction
  @Authenticate
  public static SavedMap loadDefaultMap(SavedMap map)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    if (defaultMap != null)
    {
      defaultMap.delete();
    }

    defaultMap = new DefaultSavedMap();
    defaultMap.setDisease(Disease.getCurrent());
    defaultMap.apply();

    settings.appLock();
    settings.setDefaultMap(defaultMap);
    settings.apply();

    return defaultMap;
  }

  public static void cleanOldViews(long olderThan)
  {
    for (String viewName : Database.getViewsByPrefix(Layer.GEO_VIEW_PREFIX))
    {
      String next = viewName;
      try
      {
        long parseLong = Long.parseLong(next.substring(Layer.GEO_VIEW_PREFIX.length()));
        if (parseLong < olderThan)
        {
          try
          {
            MapUtil.deleteMapView(viewName);
          }
          catch (DatabaseException e)
          {
            // This is okay
          }
        }
      }
      catch (NumberFormatException e)
      {
        // This can happen if there's a view that matches the prefix but doesn't
        // have the timestamp. Just ignore it.
      }
    }

//    // Also clean up any old map images (the directory will be recreated when an
//    // image is uploaded)
//
//    String deploy = DeployProperties.getDeployPath();
//    if (!deploy.endsWith("/"))
//    {
//      deploy += "/";
//    }
//    String imageDir = deploy + QueryConstants.MAP_IMAGES_DIR;
//    File dir = new File(imageDir);
//    if (dir.exists())
//    {
//      try
//      {
//        FileIO.deleteDirectory(dir);
//      }
//      catch (IOException e)
//      {
//        throw new ProgrammingErrorException(e);
//      }
//    }
  }

  /**
   * Cleans up all views older than an hour.
   */
  public static void cleanOldViews()
  {
    long anHourAgo = System.currentTimeMillis() - ( 60 * 60 * 1000 );
    cleanOldViews(anHourAgo);
  }
  
  @Override
  @Transaction
  public void updateNorthArrow(Integer northArrowXPosition, Integer northArrowYPosition, Boolean northArrowActive)
  {
    if(northArrowActive){
      this.appLock();
      this.setNorthArrowXPosition(northArrowXPosition);
      this.setNorthArrowYPosition(northArrowYPosition);
      this.setNorthArrowActive(true);
      this.apply();
    }
    else{
      this.appLock();
      this.setNorthArrowXPosition(0);
      this.setNorthArrowYPosition(0);
      this.setNorthArrowActive(false);
      this.apply();
    }
  }
  
  @Override
  @Transaction
  public void updateScaleBar(Integer scaleBarXPosition, Integer scaleBarYPosition, Boolean scaleBarActive)
  {
    if(scaleBarActive){
      this.appLock();
      this.setScaleBarXPosition(scaleBarXPosition);
      this.setScaleBarYPosition(scaleBarYPosition);
      this.setScaleBarActive(true);
      this.apply();
    }
    else{
      this.appLock();
      this.setScaleBarXPosition(0);
      this.setScaleBarYPosition(0);
      this.setScaleBarActive(false);
      this.apply();
    }
  }
  
  @Override
  public void updateLegendLocations(String legendLocations)
  {
    try{
      JSONObject legendsObj = new JSONObject(legendLocations);
      JSONArray legendsArr = legendsObj.getJSONArray("legends");
      
      for(int i=0; i<legendsArr.length(); i++){
        
        JSONObject legendObj = legendsArr.getJSONObject(i);
        String legendId = legendObj.getString("legendId");
        String layerId = legendId.replace("legend_", "");
        Integer legendTopPos = Math.round(Float.parseFloat(legendObj.getString("top").replace("px", "")));
        Integer legendLeftPos = Math.round(Float.parseFloat(legendObj.getString("left").replace("px", "")));
        
        Layer layer = Layer.get(layerId);
        
        layer.appLock();
        layer.setLegendXPosition(legendLeftPos);
        layer.setLegendYPosition(legendTopPos);
        layer.apply();
      }
    }
    catch(JSONException e){
      throw new ProgrammingErrorException(e);
    }
  }
  
  @Override
  public void updateImageLocations(String imageLocations)
  {
    try{
      JSONObject imagesObj = new JSONObject(imageLocations);
      JSONArray imagesArr = imagesObj.getJSONArray("images");
      
      for(int i=0; i<imagesArr.length(); i++){
        
        JSONObject imageObj = imagesArr.getJSONObject(i);
        String imageId = imageObj.getString("imageId");
        Integer imageTopPos = Math.round(Float.parseFloat(imageObj.getString("top").replace("px", "")));
        Integer imageLeftPos = Math.round(Float.parseFloat(imageObj.getString("left").replace("px", "")));
        
        String mdImageId = getImageByCustomImageId(imageId);
        
        MapImage image = MapImage.get(mdImageId);
        
        image.appLock();
        image.setImageXPosition(imageLeftPos);
        image.setImageYPosition(imageTopPos);
        image.apply();
      }
    }
    catch(JSONException e){
      throw new ProgrammingErrorException(e);
    }
  }
  
  /**
   * Persists the MapImage to the database
   * 
   * @savedMapId 
   * @imageName
   * @imagePath
   */
  @Override
  @Transaction
  public String addMapImage(String savedMapId, String imageName, String imagePath)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    
    String newImageCustomId = "image_" + IDGenerator.nextID();
    
    MapImage newImage = new MapImage();
    newImage.setImageName(imageName);
    newImage.setImageFilePath(imagePath);
    newImage.setCustomImageId(newImageCustomId);
    newImage.apply();

    // we only need to add the relationship to the default map because this relationship 
    // will later be passed to the saved map
    HasImage defaultHasImage = defaultMap.addHasImage(newImage);
    defaultHasImage.apply();
    
    return newImageCustomId;
  }
  
  
  @Override
  public String getImageByCustomImageId(String customImageId)
  {
    String mdImageId = "";
    List<? extends MapImage> childImages = this.getAllHasImage().getAll();
    for (MapImage childImage : childImages)
    {
      String currCustomImageid = childImage.getCustomImageId();
      if(currCustomImageid.equals(customImageId)){
        mdImageId = childImage.getId();
      }
    }
    
    return mdImageId;
  }
  
  @Override
  public String removeMapImage(String customImageId)
  {
    String imageId = getImageByCustomImageId(customImageId);
    if(imageId != ""){
      MapImage image = MapImage.get(imageId);
      image.removeMapImage(imageId, this.getId());
    }

    return customImageId; 
  }
  
  @Override
  public void updateMapState(Integer zoomLevel, String mapCenter)
  {
    this.appLock();
    this.setZoomLevel(zoomLevel);
    this.setMapCenter(mapCenter);
    this.apply();
  }
  
  
  /**
   * Persists the TextElement to the database
   * 
   * @savedMapId 
   * @textValue
   * @fontColor
   * @fontFamily
   * @fontSize
   * @fontStyle
   * @customTextElementId
   */
  @Override
  @Transaction
  public String addTextElement(String savedMapId, String textValue, String fontColor, String fontFamily,
      Integer fontSize, String fontStyle, String customTextElementId)
  {
    
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedMap defaultMap = settings.getDefaultMap();
    
    TextElement newTextElem = new TextElement();
    newTextElem.setTextValue(textValue);
    newTextElem.setFontColor(fontColor);
    newTextElem.setFontFamily(fontFamily);
    newTextElem.setFontSize(fontSize);  
    newTextElem.setFontStyle(fontStyle);
    newTextElem.setCustomTextElementId(customTextElementId);
    
    newTextElem.apply();

    // we only need to add the relationship to the default map because this relationship 
    // will later be passed to the saved map
    HasTextElement defaultHasText = defaultMap.addHasTextElement(newTextElem);
    defaultHasText.apply();
    
    return customTextElementId;  
  }
  
  
  @Override
  @Transaction
  public void updateTextElements(String textElements)
  {
    try{
      JSONObject textObjs = new JSONObject(textElements);
      JSONArray textArr = textObjs.getJSONArray("textElements");
      
      for(int i=0; i<textArr.length(); i++){
        
        JSONObject textObj = textArr.getJSONObject(i);
        String textId = textObj.getString("textId");
        Integer textTopPos = Math.round(Float.parseFloat(textObj.getString("top").replace("px", "")));
        Integer textLeftPos = Math.round(Float.parseFloat(textObj.getString("left").replace("px", "")));
        
        String mdTextId = getTextByCustomTextElementId(textId);
        
        if(mdTextId != ""){
          TextElement text = TextElement.get(mdTextId);
          
          text.appLock();
          text.setTextXPosition(textLeftPos);
          text.setTextYPosition(textTopPos);
          text.apply();
        }
      }
    }
    catch(JSONException e){
      throw new ProgrammingErrorException(e);
    }
  }
  
  @Override
  public String getTextByCustomTextElementId(String customTextElementId)
  {
    String mdTextId = "";
    List<? extends TextElement> childTextElements = this.getAllHasTextElement().getAll();
    for (TextElement childTextElement : childTextElements)
    {
      String currCustomTextId = childTextElement.getCustomTextElementId();
      if(currCustomTextId.equals(customTextElementId)){
        mdTextId = childTextElement.getId();
      }
    }
    
    return mdTextId;
  }

}
