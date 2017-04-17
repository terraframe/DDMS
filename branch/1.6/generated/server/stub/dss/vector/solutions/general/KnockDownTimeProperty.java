package dss.vector.solutions.general;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.KnockDownTimePropertyProblem;

public class KnockDownTimeProperty extends KnockDownTimePropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237411050776L;

  public KnockDownTimeProperty()
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
    if (this.getInsecticide() != null)
    {
      return this.getInsecticide().getKey();
    }

    return this.getId();
  }

  @Override
  public void validateLowerTime()
  {
    if (this.getLowerTime() != null && this.getUpperTime() != null && ! ( this.getLowerTime() < this.getUpperTime() ))
    {
      String msg = "Lower time must be less then upper time for Knockdown Time Properties";

      KnockDownTimePropertyProblem p = new KnockDownTimePropertyProblem(msg);
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

  public static KnockDownTimeProperty searchByInsecticide(Insecticide insecticide)
  {
    KnockDownTimePropertyQuery query = new KnockDownTimePropertyQuery(new QueryFactory());

    query.WHERE(query.getInsecticide().EQ(insecticide));
    OIterator<? extends KnockDownTimeProperty> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
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
