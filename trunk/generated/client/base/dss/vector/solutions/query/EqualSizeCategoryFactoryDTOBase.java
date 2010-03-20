package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -113681494)
public abstract class EqualSizeCategoryFactoryDTOBase extends dss.vector.solutions.query.AbstractCategoryFactoryDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.EqualSizeCategoryFactory";
  private static final long serialVersionUID = -113681494;
  
  protected EqualSizeCategoryFactoryDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static EqualSizeCategoryFactoryDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.UtilDTO dto = (com.runwaysdk.business.UtilDTO)clientRequest.get(id);
    
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
