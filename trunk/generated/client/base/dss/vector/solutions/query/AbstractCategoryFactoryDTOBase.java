package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 439119526)
public abstract class AbstractCategoryFactoryDTOBase extends com.terraframe.mojo.business.UtilDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.AbstractCategoryFactory";
  private static final long serialVersionUID = 439119526;
  
  protected AbstractCategoryFactoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
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
