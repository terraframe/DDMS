package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1632736400)
public abstract class AbstractCategoryFactoryDTOBase extends com.terraframe.mojo.business.UtilDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.AbstractCategoryFactory";
  private static final long serialVersionUID = -1632736400;
  
  protected AbstractCategoryFactoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static final java.lang.String getAllRequiredAttributes(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.AbstractCategoryFactoryDTO.CLASS, "getAllRequiredAttributes", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO getSubclassInfo(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.AbstractCategoryFactoryDTO.CLASS, "getSubclassInfo", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AbstractCategoryFactoryDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.UtilDTO dto = (com.terraframe.mojo.business.UtilDTO)clientRequest.get(id);
    
    return (AbstractCategoryFactoryDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
