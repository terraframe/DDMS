package dss.vector.solutions.query;

//import com.runwaysdk.util.FileIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;

public class MapImage extends MapImageBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 515829519;
  
  public static final String IMAGE_PREFIX  = "img$";
  
  public MapImage()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    super.apply();
  }
  
  @Override
  public void delete()
  {
    // TODO Auto-generated method stub
    super.delete();
  }
  
  @Override
  @Transaction
  public String removeMapImage(String mapImageId, String mapId)
  {
    MapImage image = MapImage.get(mapImageId);
    String imageFileName = image.getImageName();
    List<String> childImageNameArray = new ArrayList<String>();
    

    // delete the instance of MapImage from the default map so it isnt copied back to the saved map
    // this works because image names are unique for the saved map and default map 
    DefaultSavedMap defaultMap = SavedMap.getSessionDefaultMap();
    List<? extends MapImage> defaultMapChildImages = defaultMap.getAllHasImage().getAll();
    for( MapImage defaultMapChildImage : defaultMapChildImages){
      if(defaultMapChildImage.getImageName().equals(imageFileName)){
        defaultMapChildImage.delete();
      }
    }
    
    
    // get all SavedMaps 
    SavedMap[] parentMaps;
    SavedMapQuery maps = SavedMap.getAllSavedMaps();
    OIterator<? extends SavedMap> iterator = maps.getIterator();
    try{
      List<? extends SavedMap> all = iterator.getAll();
      parentMaps = all.toArray(new SavedMap[all.size()]);
    }
    finally{
      iterator.close();
    }
    
    // build a list of saved image names that are children of a saved map and NOT the default map
    for(SavedMap parentMap : parentMaps){
      String parentMapId = parentMap.getId();
      String parentType = parentMap.getType();

      if(!parentType.equals("dss.vector.solutions.query.DefaultSavedMap") && !parentMapId.equals(mapId)){
        List<? extends MapImage> childImages = parentMap.getAllHasImage().getAll();
        for (MapImage childImage : childImages){
          String childImageName = childImage.getImageName();
          childImageNameArray.add(childImageName);
        }
      }
    }
    
    // Delete the image file from the file system if it is not being referenced by another map
    if(!childImageNameArray.contains(imageFileName)){
      String deploy = DeployProperties.getDeployPath();
      if (!deploy.endsWith("/"))
      {
        deploy += "/";
      }
      String imageDir = deploy + QueryConstants.MAP_IMAGES_DIR;
      String fullImagePath = imageDir + imageFileName;
      
      File file = new File(fullImagePath);
      if (file.exists())
      {
        try
        {
          file.delete();
        }
        catch (Exception e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    
    // Delete the instance of MapImage from the database
    image.delete();

    return mapImageId;
  }
  
}
