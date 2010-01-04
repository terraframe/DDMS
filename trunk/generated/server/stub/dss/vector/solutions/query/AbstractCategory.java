package dss.vector.solutions.query;

import java.util.List;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;
import com.terraframe.mojo.dataaccess.transaction.Transaction;

public abstract class AbstractCategory extends AbstractCategoryBase
  implements com.terraframe.mojo.generation.loader.Reloadable
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
    
    this.preValidate();
    
    Layer layer = Layer.get(layerId);
    this.checkBoundsForAll(layer.getAllHasCategory().getAll());
    
    this.apply();
    
    if(isNew)
    {
      layer.addHasCategory(this).apply();
    }
  }
  
  /**
   * Ensures any validation is performed ahead of time such that
   * the category can be compared to other categories. This should be
   * called prior to checkBounds().
   */
  @AbortIfProblem
  protected abstract void preValidate();
  
  /**
   * Checks this categories bounds against those of the given category.
   * 
   * @throws OverlapBoundsException if the two category bounds overlap.
   * @param category
   */
  protected abstract void checkBounds(AbstractCategory category);
  
  @AbortIfProblem
  private void checkBoundsForAll(List<? extends AbstractCategory> list)
  {
    for(AbstractCategory category : list)
    {
      if(!this.equals(category))
      {
        this.checkBounds(category);
      }
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
  
  protected void throwsOverlapException(String str1, String str2, String str3, String str4)
  {
    OverlapBoundsException ex = new OverlapBoundsException();
    ex.setRangeOne(str1+" , "+str2);
    ex.setRangeTwo(str3+" , "+str4);
    throw ex;
  }
  
  protected void throwsOverlapException(String str1, String str2)
  {
    OverlapBoundsException ex = new OverlapBoundsException();
    ex.setRangeOne(str1);
    ex.setRangeTwo(str2);
    throw ex;
  }

  protected void throwsOverlapException(String str1, String str2, String str3)
  {
    OverlapBoundsException ex = new OverlapBoundsException();
    ex.setRangeOne(str1);
    ex.setRangeTwo(str2+" , "+str3);
    throw ex;
  }
  
  /**
   * Throws an exception for the overlapping ranges.
   * 
   * @param range1
   * @param range2
   */
  protected void throwsOverlapException(NumberRange range1, NumberRange range2)
  {
    OverlapBoundsException ex = new OverlapBoundsException();
    Number min1 = range1.getMinimumNumber();
    Number max1 = range1.getMaximumNumber();
    ex.setRangeOne(min1.equals(max1) ? min1.toString() : min1.toString()+", "+max1.toString());

    Number min2 = range2.getMinimumNumber();
    Number max2 = range2.getMaximumNumber();
    ex.setRangeTwo(min2.equals(max2) ? min2.toString() : min2.toString()+", "+max2.toString());
    
    throw ex; 
  }

}
