package dss.vector.solutions.general;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class LethalTimeProperty extends LethalTimePropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237411043508L;
  
  public LethalTimeProperty()
  {
    super();
  }
  
  public static LethalTimeProperty searchByInsecticide(Insecticide insecticide)
  {
    LethalTimePropertyQuery query = new LethalTimePropertyQuery(new QueryFactory());
    
    query.WHERE(query.getInsecticide().EQ(insecticide));
    OIterator<? extends LethalTimeProperty> iterator = query.getIterator();
    
    try
    {
      if(iterator.hasNext())
      {
        return iterator.next();
      }
      
      UndefinedLethalTimePropertyException e = new UndefinedLethalTimePropertyException();
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
