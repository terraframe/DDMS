package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -300818476)
public abstract class ExactCategoryFactoryDTOBase extends dss.vector.solutions.query.AbstractCategoryFactoryDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.ExactCategoryFactory";
  private static final long serialVersionUID = -300818476;
  
  protected ExactCategoryFactoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ExactCategoryFactoryDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.UtilDTO dto = (com.terraframe.mojo.business.UtilDTO)clientRequest.get(id);
    
    return (ExactCategoryFactoryDTO) dto;
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
