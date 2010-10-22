package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -654210824)
public abstract class PotentiallyResistantCollectionDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.PotentiallyResistantCollection";
  private static final long serialVersionUID = -654210824;
  
  public PotentiallyResistantCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
}
