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
    if (id != null && id.length() > 0)
    {
      InsecticideBrand concrete = InsecticideBrand.get(id);

      if (concrete != null)
      {
        InsecticideBrandLabel label = new InsecticideBrandLabel();
        label.setConcreteId(concrete.getId());
        label.setProductName(concrete.getProductName().getTermDisplayLabel().getValue());
        label.setActiveIngredient(concrete.getActiveIngredient().getTermDisplayLabel().getValue());
        label.setConcentrationQuantifier(concrete.getConcentrationQuantifier());
        label.setConcentrationQualifier(concrete.getConcentrationQualifier().get(0).getDisplayLabel());

        return label;
      }
    }

    return null;
  }

}
