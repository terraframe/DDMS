package dss.vector.solutions.general;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.entomology.assay.Unit;
import dss.vector.solutions.ontology.Term;

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
    if (this.getUnits() != null && this.getActiveIngredient() != null)
    {
      String unit = this.getUnits().getKey();
      String ingredient = this.getActiveIngredient().getKey();

      return ingredient + " - " + this.getAmount() + " " + unit;
    }
    
    return this.getId();
  }
  
  public static Insecticide get(String activeIngredient, String unit, Double amount)
  {
    Term ingredient = Term.validateByDisplayLabel(activeIngredient, Insecticide.getActiveIngredientMd());
    Term u = Term.validateByDisplayLabel(unit, Insecticide.getUnitsMd());
    
    if(u == null)
    {
      String msg = "Units is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getUnitsMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }
    
    if(amount == null)
    {
      String msg = "Amount is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getAmountMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }
    
    if(ingredient == null)
    {
      String msg = "Active ingredient is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(Insecticide.getActiveIngredientMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }

    InsecticideQuery insecticideQuery = new InsecticideQuery(new QueryFactory());
    insecticideQuery.WHERE(insecticideQuery.getActiveIngredient().EQ(ingredient));
    insecticideQuery.WHERE(insecticideQuery.getUnits().EQ(u));
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
