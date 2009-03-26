package dss.vector.solutions.general;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class KnockDownTimeProperty extends KnockDownTimePropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237411050776L;
  
  public KnockDownTimeProperty()
  {
    super();
  }
    
  public static KnockDownTimeProperty searchByInsecticide(Insecticide insecticide)
  {
    KnockDownTimePropertyQuery query = new KnockDownTimePropertyQuery(new QueryFactory());
    
    query.WHERE(query.getInsecticide().EQ(insecticide));
    OIterator<? extends KnockDownTimeProperty> iterator = query.getIterator();
    
    try
    {
      if(iterator.hasNext())
      {
        return iterator.next();
      }
      
      UndefinedKnockDownPropertyException e = new UndefinedKnockDownPropertyException();
      e.setInsecticide(insecticide);
      e.apply();
      
      throw e;
    }
    finally
    {
      iterator.close();
    }
  }
}