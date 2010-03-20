package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -2011049045)
public abstract class SusceptibleCollectionDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.SusceptibleCollection";
  private static final long serialVersionUID = -2011049045;
  
  public SusceptibleCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
}
