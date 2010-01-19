package dss.vector.solutions.query;

import java.io.File;
import java.io.IOException;

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.WebFile;
import com.terraframe.mojo.util.FileIO;

public class Layer extends LayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240900978895L;

  public static final String GEO_VIEW_PREFIX = "geo$";
  
  public Layer()
  {
    super();
  }
  
  /**
   * Applies this Layer along with its Styles.
   */
  @Override
  @Transaction
  public void applyWithStyles(Styles styles, String savedMapId)
  {
    styles.apply();
    
    boolean isNew = this.isNew();
    
    if(isNew)
    {
      this.setDefaultStyles(styles);
    }
    
    this.apply();
    
    if(isNew)
    {
      SavedMap map = SavedMap.get(savedMapId);

      QueryFactory f = new QueryFactory();
      HasLayersQuery hQ = new HasLayersQuery(f);
      LayerQuery lQ = new LayerQuery(f);
      
      lQ.WHERE(hQ.parentId().EQ(savedMapId));
      lQ.AND(lQ.map(hQ));
      
      lQ.WHERE(lQ.getLayerName().EQ(this.getLayerName()));
      
      if(lQ.getCount() > 0)
      {
        String error = "The layer name ["+this.getLayerName()+"] already " +
        		"exists for the map ["+map.getMapName()+"].";
        throw new NonUniqueLayerNameException(error);
      }
      
      int count = (int) map.getLayerCount();
      
      HasLayers rel = this.addMap(map);
      rel.setLayerPosition((int)count);
      
      rel.apply();
    }
  }
  
  @Override
  public void apply()
  {
    if(this.isNew())
    {
      String name = GEO_VIEW_PREFIX + System.currentTimeMillis();
      this.setViewName(name);
    }
    
    super.apply();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  

  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    this.getDefaultStyles().lock();
    
    OIterator<? extends AbstractCategory> iter = this.getAllHasCategory();
    try
    {
      while (iter.hasNext())
      {
        iter.next().lock();
      }
    }
    finally
    {
      iter.close();
    }    
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    this.getDefaultStyles().unlock();
    
    OIterator<? extends AbstractCategory> iter = this.getAllHasCategory();
    try
    {
      while (iter.hasNext())
      {
        iter.next().unlock();
      }
    }
    finally
    {
      iter.close();
    }
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
    
    Styles styles = this.getDefaultStyles();
    
    super.delete();
    
    styles.delete();
  }
  
  public String toString()
  {
    String name = this.getLayerName();
    return name != null ? name : this.getId();
  }
  
}