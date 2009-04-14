package dss.vector.solutions.entomology.assay;

public abstract class ResistantCollectionDTOBase extends com.terraframe.mojo.business.InformationDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.ResistantCollection";
  public ResistantCollectionDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
}
