package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -642088784)
public abstract class EntityNotChildOfMainWarningDTOBase extends com.runwaysdk.business.WarningDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.EntityNotChildOfMainWarning";
  private static final long serialVersionUID = -642088784;
  
  public EntityNotChildOfMainWarningDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITIES = "entities";
  public static java.lang.String ID = "id";
  public String getEntities()
  {
    return getValue(ENTITIES);
  }
  
  public void setEntities(String value)
  {
    if(value == null)
    {
      setValue(ENTITIES, "");
    }
    else
    {
      setValue(ENTITIES, value);
    }
  }
  
  public boolean isEntitiesWritable()
  {
    return isWritable(ENTITIES);
  }
  
  public boolean isEntitiesReadable()
  {
    return isReadable(ENTITIES);
  }
  
  public boolean isEntitiesModified()
  {
    return isModified(ENTITIES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getEntitiesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(ENTITIES).getAttributeMdDTO();
  }
  
}
