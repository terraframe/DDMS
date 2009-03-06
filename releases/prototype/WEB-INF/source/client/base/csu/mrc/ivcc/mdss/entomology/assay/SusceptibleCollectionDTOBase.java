package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class SusceptibleCollectionDTOBase extends com.terraframe.mojo.business.InformationDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.SusceptibleCollection";
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
