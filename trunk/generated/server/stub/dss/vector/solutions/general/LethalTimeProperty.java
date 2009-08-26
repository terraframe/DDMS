package dss.vector.solutions.general;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.LethalTimePropertyProblem;

public class LethalTimeProperty extends LethalTimePropertyBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237411043508L;

  public LethalTimeProperty()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getInsecticide() != null)
    {      
      return this.getInsecticide().getKey();
    }
    
    return this.getId();
  }

  @Override
  public void validateLowerTime()
  {
    if (this.getLowerTime() != null && this.getUpperTime() != null
        && !(this.getLowerTime() < this.getUpperTime()))
    {
      String msg = "Lower time must be less then upper time for Lethal Time Properties";
      
      LethalTimePropertyProblem p = new LethalTimePropertyProblem(msg);
      p.setNotification(this, LOWERTIME);
      p.apply();
      p.throwIt();
    }
  }

  @Override
  public void apply()
  {
    validateLowerTime();

    super.apply();
  }

  public static LethalTimeProperty searchByInsecticide(Insecticide insecticide)
  {
    LethalTimePropertyQuery query = new LethalTimePropertyQuery(new QueryFactory());

    query.WHERE(query.getInsecticide().EQ(insecticide));
    OIterator<? extends LethalTimeProperty> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
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
