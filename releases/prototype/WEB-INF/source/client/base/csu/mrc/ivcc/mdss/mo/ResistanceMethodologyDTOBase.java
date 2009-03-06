package csu.mrc.ivcc.mdss.mo;

public abstract class ResistanceMethodologyDTOBase extends csu.mrc.ivcc.mdss.mo.AbstractTermDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236360387174L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.mo.ResistanceMethodology";
  protected ResistanceMethodologyDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ResistanceMethodologyDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static final csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.CLASS, "getAll", _declaredTypes);
    return (csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static csu.mrc.ivcc.mdss.mo.ResistanceMethodologyQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.mo.ResistanceMethodologyQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.mo.ResistanceMethodology", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.mo.ResistanceMethodologyDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
