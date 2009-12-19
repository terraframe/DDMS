package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -764210054)
public abstract class MapUtilDTOBase extends com.terraframe.mojo.business.UtilDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.MapUtil";
  private static final long serialVersionUID = -764210054;
  
  protected MapUtilDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static MapUtilDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.UtilDTO dto = (com.terraframe.mojo.business.UtilDTO)clientRequest.get(id);
    
    return (MapUtilDTO) dto;
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
