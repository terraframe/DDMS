package dss.vector.solutions.query;

import org.apache.commons.lang.math.NumberRange;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;


public class RangeCategory extends RangeCategoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158118735L;

  public RangeCategory()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    this.validateLowerBoundStr();
    this.validateUpperBoundStr();
    
    validateBounds();
    
    super.apply();
  }
  
  @AbortIfProblem
  private void validateBounds()
  {
    String lower = this.getLowerBoundStr();
    String upper = this.getUpperBoundStr();
    
    try
    {
      Double lowerD = Double.valueOf(lower);
      Double upperD = Double.valueOf(upper);
//      
//      NumberRange range1 = new NumberRange(lowerD);
//      NumberRange range2 = new NumberRange(upperD);
//      
//      if(!upperD.equals(lowerD)  && range1.overlapsRange(range2))
//      {
//        OverlapBoundsException ex = new OverlapBoundsException();
//
//        Number min1 = range1.getMinimumNumber();
//        Number max1 = range1.getMaximumNumber();
//        ex.setRangeOne(min1.equals(max1) ? min1.toString() : min1.toString()+", "+max1.toString());
//  
//        Number min2 = range2.getMinimumNumber();
//        Number max2 = range2.getMaximumNumber();
//        ex.setRangeTwo(min2.equals(max2) ? min2.toString() : min2.toString()+", "+max2.toString());
//  
//        throw ex; 
//      }
    }
    catch(NumberFormatException e)
    {
      // nothing to do since the bounds are not both numbers
    }
  }

}
