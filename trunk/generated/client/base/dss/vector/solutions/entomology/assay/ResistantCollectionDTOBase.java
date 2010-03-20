package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1882759585)
public abstract class ResistantCollectionDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.ResistantCollection";
  private static final long serialVersionUID = -1882759585;
  
  public ResistantCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
}
