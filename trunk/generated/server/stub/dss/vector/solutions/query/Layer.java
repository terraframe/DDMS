package dss.vector.solutions.query;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.constants.LocalProperties;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.WebFile;
import com.runwaysdk.util.FileIO;

public class Layer extends LayerBase implements com.runwaysdk.generation.loader.Reloadable, LayerIF
{
  private static final long  serialVersionUID = 1240900978895L;

  public static final String GEO_VIEW_PREFIX  = "geo$";

  public Layer()
  {
    super();
  }

  @Override
  public int hashCode()
  {
    return this.getId().hashCode();
  }

  public boolean hasThematicVariable()
  {
    String alias = this.getThematicUserAlias();
    return alias != null && alias.length() != 0;
  }

  @Override
  public QueryInfo calculateQueryInfo()
  {
    QueryInfo info = new QueryInfo();

    // Create the DB view for this layer
    Map<Layer, ValueQuery> layersVQ = MapUtil.createDBViews(new Layer[] { this }, true);

    // Query on that view
    QueryFactory f = new QueryFactory();
    ValueQuery wrapper = new ValueQuery(f);
    wrapper.FROM(this.getViewName(), "queryInfo");

    List<Selectable> selectables = new LinkedList<Selectable>();
    if (this.hasThematicVariable())
    {
      info.setHasThematicVariable(true);

      ValueQuery vq = layersVQ.get(this);

      String userAlias = this.getThematicUserAlias();
      Selectable selectable = vq.getSelectableRef(userAlias);

      // NOTE: the column alias was set in the call to MapUtil.createDBViews
      String columnAlias = this.getThematicColumnAlias();
      if (selectable instanceof SelectableChar)
      {
        selectables.add(wrapper.aSQLAggregateCharacter("min_data", "MIN(" + columnAlias + ")"));
        selectables.add(wrapper.aSQLAggregateCharacter("max_data", "MAX(" + columnAlias + ")"));
      }
      else
      {
        info.setIsThematicVariable(true);

        selectables.add(wrapper.aSQLAggregateDouble("min_data", "MIN(" + columnAlias + ")"));
        selectables.add(wrapper.aSQLAggregateDouble("max_data", "MAX(" + columnAlias + ")"));
      }
    }

    selectables.add(wrapper.aSQLAggregateLong("totalResults", "COUNT(*)"));

    wrapper.SELECT(selectables.toArray(new Selectable[selectables.size()]));

    OIterator<? extends ValueObject> iter = wrapper.getIterator();
    try
    {
      ValueObject row = iter.next();

      if (info.hasThematicVariable())
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
  public AbstractCategory[] generateCategories(CategoryGen categoryGen, Layer currentLayer)
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
    for (AbstractCategory category : this.getAllHasCategory().getAll())
    {
      category.delete();
    }

    List<AbstractCategory> categories = factory.create(currentLayer, categoryGen);
    CategorySorter.sort(categories);

    // Save the new generated categories
    String layerId = categoryGen.getLayerId();
    Layer layer = Layer.get(layerId);
    for (AbstractCategory cat : categories)
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

    if (isNew)
    {
      this.setDefaultStyles(styles);
    }

    // Validate the uniqueness of the layer name. If the layer
    // is new then grab the provided savedMapId because it represents
    // the map that is being used as the temporary SavedMap until the
    // user explicitly saves the map.
    // Otherwise, use the existing map that the layer points to.
    SavedMap map;
    if (isNew)
    {
      map = SavedMap.get(savedMapId);
    }
    else
    {
      map = this.getAllMap().getAll().get(0);
    }
    validateUniqueness(map);

    this.apply();

    if (isNew)
    {
      int count = (int) map.getLayerCount();

      HasLayers rel = this.addMap(map);
      rel.setLayerPosition((int) count);

      rel.apply();
    }
  }

  /**
   * Validates that this Layer's name is unique on the map (but it doesn't have
   * to be unique across the system). This should be called after this Layer has
   * been applied.
   * 
   * @param savedMapId
   */
  public void validateUniqueness(SavedMap map)
  {
    QueryFactory f = new QueryFactory();
    HasLayersQuery hQ = new HasLayersQuery(f);
    LayerQuery lQ = new LayerQuery(f);

    hQ.WHERE(hQ.parentId().EQ(map.getId()));
    lQ.WHERE(lQ.map(hQ));

    lQ.WHERE(lQ.getLayerName().EQ(this.getLayerName()));

    if (!this.isNew())
    {
      lQ.AND(lQ.getId().NE(this.getId()));
    }

    if (lQ.getCount() > 0)
    {
      String error = "The layer name [" + this.getLayerName() + "] already " + "exists for the map [" + map.getMapName() + "].";
      throw new NonUniqueLayerNameException(error);
    }
  }

  @Override
  public void apply()
  {
    if (this.isNew())
    {
      String name = GEO_VIEW_PREFIX + System.currentTimeMillis();
      this.setViewName(name);
    }

    // If the legend title is empty, just set it to the layer name.
    String legendTitle = this.getLegendTitle();
    String layerName = this.getLayerName();

    if ( ( legendTitle == null || legendTitle.length() == 0 ) && layerName != null && layerName.length() > 0)
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
    if (webId != null && webId.trim().length() > 0)
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
      if (file.exists())
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

  @Override
  @Transaction
  @Authenticate
  public void updateSLDFile(String fileId)
  {
    this.lock();

    this.setSldFile(fileId);

    this.apply();
  }

  public String toString()
  {
    String name = this.getLayerName();
    return name != null ? name : this.getId();
  }

}