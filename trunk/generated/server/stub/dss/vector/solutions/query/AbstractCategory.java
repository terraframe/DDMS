package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

public abstract class AbstractCategory extends AbstractCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158096964L;

  public AbstractCategory()
  {
    super();
  }
  
  @Override
  @Transaction
  public void delete()
  {
    super.delete();

    this.getStyles().delete();
  }
  
  @Override
  @Transaction
  public void applyWithStyles(Styles styles, String layerId)
  {
    styles.apply();
    
    boolean isNew = this.isNew();
    
    if(isNew)
    {
      this.setStyles(styles);
    }
    
    this.apply();
    
    if(isNew)
    {
      Layer layer = Layer.get(layerId);
      
      layer.addHasCategory(this).apply();
    }
  }
  
  
  @Override
  @Transaction
  public void lock()
  {
    this.getStyles().lock();
    super.lock();
  }
  
  @Override
  @Transaction
  public void unlock()
  {
    this.getStyles().unlock();
    super.unlock();
  }

  @Override
  protected String buildKey()
  {
    //TODO: Naifeh needs to define this key
    return this.getId();
  }

}
