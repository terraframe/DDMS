package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 2103162253)
public abstract class NonRangeCategoryDTOBase extends dss.vector.solutions.query.AbstractCategoryDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.NonRangeCategory";
  private static final long serialVersionUID = 2103162253;
  
  protected NonRangeCategoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected NonRangeCategoryDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EXACTVALUE = "exactValue";
  public Double getExactValue()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(EXACTVALUE));
  }
  
  public void setExactValue(Double value)
  {
    if(value == null)
    {
      setValue(EXACTVALUE, "");
    }
    else
    {
      setValue(EXACTVALUE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isExactValueWritable()
  {
    return isWritable(EXACTVALUE);
  }
  
  public boolean isExactValueReadable()
  {
    return isReadable(EXACTVALUE);
  }
  
  public boolean isExactValueModified()
  {
    return isModified(EXACTVALUE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getExactValueMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(EXACTVALUE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.query.NonRangeCategoryQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.NonRangeCategoryQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.NonRangeCategory", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.NonRangeCategoryDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.NonRangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.NonRangeCategoryDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.NonRangeCategoryDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.NonRangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
