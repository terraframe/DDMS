package dss.vector.solutions.general;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.LethalTimePropertyProblem;

public class LethalTimeProperty extends LethalTimePropertyBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237411043508L;

  public LethalTimeProperty()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
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

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
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
