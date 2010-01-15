package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1222724992)
public abstract class LayerOmittedDuplicateDataInformationDTOBase extends com.terraframe.mojo.business.InformationDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.LayerOmittedDuplicateDataInformation";
  private static final long serialVersionUID = -1222724992;
  
  public LayerOmittedDuplicateDataInformationDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LAYERNAME = "layerName";
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public boolean isLayerNameWritable()
  {
    return isWritable(LAYERNAME);
  }
  
  public boolean isLayerNameReadable()
  {
    return isReadable(LAYERNAME);
  }
  
  public boolean isLayerNameModified()
  {
    return isModified(LAYERNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getLayerNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERNAME).getAttributeMdDTO();
  }
  
}
