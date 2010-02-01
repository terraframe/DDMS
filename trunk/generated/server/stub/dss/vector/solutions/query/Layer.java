package dss.vector.solutions.query;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.query.CategorySorter;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectableChar;
import com.terraframe.mojo.query.ValueQuery;
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
  
  @Override
  public int hashCode()
  {
    return this.getId().hashCode();
  }
  
  @Override
  public QueryInfo calculateQueryInfo()
  {
    QueryInfo info = new QueryInfo();

    // Create the DB view for this layer
    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(new Layer[]{this}, true);
    
    // Query on that view
    QueryFactory f = new QueryFactory();
    ValueQuery wrapper = new ValueQuery(f);
    wrapper.FROM(this.getViewName(), "queryInfo");
    
    String thematicVar = this.getThematicUserAlias();
    List<Selectable> selectables = new LinkedList<Selectable>();
    if(thematicVar != null && thematicVar.length() > 0)
    {
      info.setHasThematicVariable(true);
      
      ValueQuery vq = layersVQ.get(this);
      
      Selectable selectable = vq.getSelectableRef(thematicVar);
      
      if(selectable instanceof SelectableChar)
      {
        selectables.add(wrapper.aSQLAggregateCharacter("min_data", "MIN("+QueryConstants.THEMATIC_DATA_COLUMN+")"));
        selectables.add(wrapper.aSQLAggregateCharacter("max_data", "MAX("+QueryConstants.THEMATIC_DATA_COLUMN+")"));
      }
      else
      {
        info.setIsThematicVariable(true);
        
        selectables.add(wrapper.aSQLAggregateDouble("min_data", "MIN("+QueryConstants.THEMATIC_DATA_COLUMN+")"));
        selectables.add(wrapper.aSQLAggregateDouble("max_data", "MAX("+QueryConstants.THEMATIC_DATA_COLUMN+")"));
      }
    }
    
    selectables.add(wrapper.aSQLAggregateLong("totalResults", "COUNT(*)"));
    
    wrapper.SELECT(selectables.toArray(new Selectable[selectables.size()]));
    
    OIterator<? extends ValueObject> iter = wrapper.getIterator();
    try
    {
      ValueObject row = iter.next();
      
      if(info.hasThematicVariable())
      {
        String min = row.getValue("min_data");
        String max = row.getValue("max_data");
        
        info.setMinimum(min);
        info.setMaximum(max);
      }
      
      Integer total = Integer.valueOf(row.getValue("totalResults"));
      info.setTotalResults(total);

      return info;
    }
    finally
    {
      iter.close();
    }
  }
  
  @Override
  @Transaction
  public AbstractCategory[] generateCategories(CategoryGen categoryGen)
  {
    String type = categoryGen.getFactoryType();
    AbstractCategoryFactory factory;
    try
    {
      factory = (AbstractCategoryFactory) LoaderDecorator.load(type).newInstance();
    }
    catch (InstantiationException e)
    {
      throw new ProgrammingErrorException(e);
    }
    catch (IllegalAccessException e)
    {
      throw new ProgrammingErrorException(e);
    }
    
    // Delete all prior categories because they may no longer apply.
    for(AbstractCategory category : this.getAllHasCategory().getAll())
    {
      category.delete();
    }
    
    List<AbstractCategory> categories = factory.create(this, categoryGen);
    CategorySorter.sort(categories);
    
    // Save the new generated categories
    String layerId = categoryGen.getLayerId();
    Layer layer = Layer.get(layerId);
    for(AbstractCategory cat : categories)
    {
      cat.applyWithLayer(layer, true);
    }
    
    return categories.toArray(new AbstractCategory[categories.size()]);
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
    
    // If the legend title is empty, just set it to the layer name.
    String legendTitle = this.getLegendTitle();
    String layerName = this.getLayerName();
    if((legendTitle == null || legendTitle.length() == 0) && (layerName != null && layerName.length() > 0))
    {
      this.setLegendTitle(layerName);
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