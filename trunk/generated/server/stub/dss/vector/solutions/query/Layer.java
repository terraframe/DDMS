package dss.vector.solutions.query;

import java.io.File;
import java.io.IOException;

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.util.FileIO;

public abstract class Layer extends LayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240900978895L;

  public Layer()
  {
    super();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Note that a geometry style may not exist until
    // a thematic layer type (geo entity type) has been defined.
    GeometryStyle geoStyle = this.getGeometryStyle();
    if(geoStyle != null)
    {
      this.getGeometryStyle().lock();
    }

    this.getTextStyle().lock();
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Note that a geometry style may not exist until
    // a thematic layer type (geo entity type) has been defined.
    GeometryStyle geoStyle = this.getGeometryStyle();
    if(geoStyle != null)
    {
      this.getGeometryStyle().unlock();
    }

    this.getTextStyle().unlock();
  }

  @Override
  @Transaction
  public void delete()
  {
    String webId = this.getSldFile();
    if(webId != null && webId.trim().length() > 0)
    {
      WebFile webFile = WebFile.get(webId);
      String path = webFile.getFilePath();
      String fileName = webFile.getFileName();
      String extension = webFile.getFileExtension();

      webFile.delete();
      
      // remove the SLD artifact
      String rootPath = LocalProperties.getWebDirectory();
      
      String filepath = rootPath + path + fileName + "." + extension;
      File file = new File(filepath);
      if(file.exists())
      {
        try
        {
          FileIO.deleteFile(file);
        }
        catch (IOException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    
    
    super.delete();
  }
  
  /**
   * Updates the given layer and its associated styles.
   *
   * @param geometryStyle
   * @param textStyle
   * @param layerId
   * @return
   */
  @Transaction
  public static Layer updateLayer(GeometryStyle geometryStyle, TextStyle textStyle, String layerId)
  {
    Layer layer = Layer.get(layerId);

    geometryStyle.apply();
    textStyle.apply();

    layer.unlock();
    return layer;
  }
  
  public LayerView getAsView()
  {
    LayerView view = new LayerView();
    
    view.setLayerId(this.getId());
    
    return view;
  }

}