package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1870696866)
public abstract class NonRangeCategoryDTOBase extends dss.vector.solutions.query.AbstractCategoryDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.NonRangeCategory";
  private static final long serialVersionUID = -1870696866;
  
  protected NonRangeCategoryDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected NonRangeCategoryDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EXACTVALUESTR = "exactValueStr";
  public String getExactValueStr()
  {
    return getValue(EXACTVALUESTR);
  }
  
  public void setExactValueStr(String value)
  {
    if(value == null)
    {
      setValue(EXACTVALUESTR, "");
    }
    else
    {
      setValue(EXACTVALUESTR, value);
    }
  }
  
  public boolean isExactValueStrWritable()
  {
    return isWritable(EXACTVALUESTR);
  }
  
  public boolean isExactValueStrReadable()
  {
    return isReadable(EXACTVALUESTR);
  }
  
  public boolean isExactValueStrModified()
  {
    return isModified(EXACTVALUESTR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getExactValueStrMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EXACTVALUESTR).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.NonRangeCategoryDTO) dto;
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
  
  public static dss.vector.solutions.query.NonRangeCategoryQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.NonRangeCategoryQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.query.NonRangeCategoryDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.NonRangeCategoryDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.NonRangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.query.NonRangeCategoryDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.NonRangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
