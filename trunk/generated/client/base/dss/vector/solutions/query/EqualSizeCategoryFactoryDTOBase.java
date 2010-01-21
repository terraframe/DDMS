package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -113681494)
public abstract class EqualSizeCategoryFactoryDTOBase extends dss.vector.solutions.query.AbstractCategoryFactoryDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.EqualSizeCategoryFactory";
  private static final long serialVersionUID = -113681494;
  
  protected EqualSizeCategoryFactoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EqualSizeCategoryFactoryDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.UtilDTO dto = (com.terraframe.mojo.business.UtilDTO)clientRequest.get(id);
    
    return (EqualSizeCategoryFactoryDTO) dto;
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
