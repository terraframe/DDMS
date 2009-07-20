package dss.vector.solutions.general;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.mo.ActiveIngredient;

public class Insecticide extends InsecticideBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1237396178247L;

  public Insecticide()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getUnits().size() > 0 && this.getActiveIngredient() != null)
    {
      String unit = this.getUnits().get(0).getEnumName();
      String ingredient = this.getActiveIngredient().getId();

      return ingredient + " - " + this.getAmount() + " " + unit;
    }
    
    return this.getId();
  }
  
  public static Insecticide get(String activeIngredient, String unit, Double amount)
  {
    ActiveIngredient ingredient = ActiveIngredient.validateByDisplayLabel(activeIngredient);
    Unit u = getUnitByLabel(unit);

    InsecticideQuery insecticideQuery = new InsecticideQuery(new QueryFactory());
    insecticideQuery.WHERE(insecticideQuery.getActiveIngredient().EQ(ingredient));
    insecticideQuery.WHERE(insecticideQuery.getUnits().containsExactly(u));
    insecticideQuery.WHERE(insecticideQuery.getAmount().EQ(amount));
    
    OIterator<? extends Insecticide> iterator = insecticideQuery.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      return null;
    }
    finally
    {
      iterator.close();
    }
  }
  
  public static Unit getUnitByLabel(String label)
  {
    for (Unit e : Unit.values())
    {
      if (e.getDisplayLabel().equals(label))
      {
        return e;
      }
    }
    return null;
  }
}
