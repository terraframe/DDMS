package mdss.entomology;

public abstract class UninterestingSpecieGroupDTOBase extends mdss.entomology.TrueSpecieEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.UninterestingSpecieGroup";
  private static final long serialVersionUID = 1234294597524L;
  
  protected UninterestingSpecieGroupDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected UninterestingSpecieGroupDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String QUANITY = "quanity";
  public Integer getQuanity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANITY));
  }
  
  public void setQuanity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANITY, "");
    }
    else
    {
      setValue(QUANITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuanityWritable()
  {
    return isWritable(QUANITY);
  }
  
  public boolean isQuanityReadable()
  {
    return isReadable(QUANITY);
  }
  
  public boolean isQuanityModified()
  {
    return isModified(QUANITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuanityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quanity").getAttributeMdDTO();
  }
  
  public static mdss.entomology.UninterestingSpecieGroupDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (mdss.entomology.UninterestingSpecieGroupDTO) dto;
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
  
  public static mdss.entomology.UninterestingSpecieGroupQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (mdss.entomology.UninterestingSpecieGroupQueryDTO) clientRequest.getAllInstances("mdss.entomology.UninterestingSpecieGroup", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static mdss.entomology.UninterestingSpecieGroupDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.UninterestingSpecieGroupDTO.CLASS, "lock", _declaredTypes);
    return (mdss.entomology.UninterestingSpecieGroupDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static mdss.entomology.UninterestingSpecieGroupDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.UninterestingSpecieGroupDTO.CLASS, "unlock", _declaredTypes);
    return (mdss.entomology.UninterestingSpecieGroupDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
