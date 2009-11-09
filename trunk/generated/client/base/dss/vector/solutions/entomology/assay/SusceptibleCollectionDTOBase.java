package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 303148745)
public abstract class SusceptibleCollectionDTOBase extends com.terraframe.mojo.business.InformationDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.SusceptibleCollection";
  private static final long serialVersionUID = 303148745;
  
  public SusceptibleCollectionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
}
