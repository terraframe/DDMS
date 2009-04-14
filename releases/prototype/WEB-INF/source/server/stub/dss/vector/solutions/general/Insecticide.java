package dss.vector.solutions.general;

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

}
