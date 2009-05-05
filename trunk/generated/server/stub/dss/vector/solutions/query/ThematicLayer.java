package dss.vector.solutions.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;

public class ThematicLayer extends ThematicLayerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158194588L;

  public static final Pattern THEMATIC_VARIABLE_PATTERN = Pattern.compile("^(.*?)\\[(\\w+)\\]$");

  public static final int TYPE_GROUP = 1;

  public static final int VARIABLE_GROUP  = 2;

  public ThematicLayer()
  {
    super();
  }

  /**
   * Locks this Layer and all of its categories.
   */
  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    OIterator<? extends AbstractCategory> iter = this.getAllDefinesCategory();
    try
    {
      while(iter.hasNext())
      {
        iter.next().lock();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Unlocks this Layer and all of its categories.
   */
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    OIterator<? extends AbstractCategory> iter = this.getAllDefinesCategory();
    try
    {
      while(iter.hasNext())
      {
        iter.next().unlock();
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Updates a ThematicLayer with the given information.
   *
   * @param layerId
   * @param thematicVariable
   * @param categories
   * @return
   */
  @Transaction
  public static ThematicLayer updateThematicVariable(String layerId, String thematicVariable, AbstractCategory[] categories)
  {
    ThematicLayer layer = ThematicLayer.get(layerId);

    // remove all prior Categories
    OIterator<? extends AbstractCategory> oldCategories = layer.getAllDefinesCategory();
    try
    {
      while(oldCategories.hasNext())
      {
        oldCategories.next().delete();
      }
    }
    finally
    {
      oldCategories.close();
    }

    layer.setThematicVariable(thematicVariable);
    layer.apply();

    for(AbstractCategory category : categories)
    {
      category.apply();
      layer.addDefinesCategory(category).apply();
    }

    return layer;
  }

  /**
   * Returns a Matcher object used to access the entity alias and variable
   * name for a thematic variable. This method returns null if no thematic
   * variable exists or if the pattern doesn't match.
   *
   * @return
   */
  public Matcher matchOnThematicVariable()
  {
    Matcher matcher = null;
    String thematicVariable = this.getThematicVariable();
    if(thematicVariable != null)
    {
      Matcher m = THEMATIC_VARIABLE_PATTERN.matcher(thematicVariable);
      if(m.matches())
      {
        matcher = m;
      }
    }

    return matcher;
  }

}
