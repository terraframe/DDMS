package mdss.entomology.assay;


public class AssayInsecticide extends AssayInsecticideBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543766956L;
  
  public AssayInsecticide()
  {
    super();
  }
  
  public AssayInsecticide(com.terraframe.mojo.business.MutableWithStructs entity, String structName)
  {
    super(entity, structName);
  }
  
  
  public void validate()
  {
    validateGenericName();
  }
    
  @Override
  public void apply()
  {
    validate();
    super.apply();
  }
}
