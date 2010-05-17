package dss.vector.solutions.irs;

public class InsecticideBrandLabel extends InsecticideBrandLabelBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1906755034;
  
  public InsecticideBrandLabel()
  {
    super();
  }
  
  public static InsecticideBrandLabel getLabel(String id)
  {
    InsecticideBrand concrete = InsecticideBrand.get(id);
    
    if(concrete != null)
    {
      InsecticideBrandLabel label = new InsecticideBrandLabel();
      label.setConcreteId(concrete.getId());
      label.setBrandName(concrete.getBrandName());
      label.setActiveIngredient(concrete.getActiveIngredient().getTermDisplayLabel().getValue());
      label.setConcentration(concrete.getAmount());
      
      return label;
    }
    
    return null;
  }

  
  
}
