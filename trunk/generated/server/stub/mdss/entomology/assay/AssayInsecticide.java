package mdss.entomology.assay;

import mdss.entomology.Insecticide;

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
//    validateInsecticide();
    
    validateGenericName();
  }
  
  @Override
  public void validateGenericName()
  {
    super.validateGenericName();
    
//    if(!Insecticide.OTHER.equals(this.getInsecticide().get(0)) &&
//        !this.getGenericName().equals(""))
//    {
//      String msg = "Insectices cannont have generic name unless they fall under the other catagory";
//      throw new RuntimeException(msg);
//    }
  }
  
  @Override
  public void apply()
  {
    validate();
    super.apply();
  }
}
