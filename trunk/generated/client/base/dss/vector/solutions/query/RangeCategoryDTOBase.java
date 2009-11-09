package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 1577049395)
public abstract class RangeCategoryDTOBase extends dss.vector.solutions.query.AbstractCategoryDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.RangeCategory";
  private static final long serialVersionUID = 1577049395;
  
  protected RangeCategoryDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected RangeCategoryDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String LOWERBOUND = "lowerBound";
  public static java.lang.String UPPERBOUND = "upperBound";
  public Double getLowerBound()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(LOWERBOUND));
  }
  
  public void setLowerBound(Double value)
  {
    if(value == null)
    {
      setValue(LOWERBOUND, "");
    }
    else
    {
      setValue(LOWERBOUND, java.lang.Double.toString(value));
    }
  }
  
  public boolean isLowerBoundWritable()
  {
    return isWritable(LOWERBOUND);
  }
  
  public boolean isLowerBoundReadable()
  {
    return isReadable(LOWERBOUND);
  }
  
  public boolean isLowerBoundModified()
  {
    return isModified(LOWERBOUND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getLowerBoundMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(LOWERBOUND).getAttributeMdDTO();
  }
  
  public Double getUpperBound()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(UPPERBOUND));
  }
  
  public void setUpperBound(Double value)
  {
    if(value == null)
    {
      setValue(UPPERBOUND, "");
    }
    else
    {
      setValue(UPPERBOUND, java.lang.Double.toString(value));
    }
  }
  
  public boolean isUpperBoundWritable()
  {
    return isWritable(UPPERBOUND);
  }
  
  public boolean isUpperBoundReadable()
  {
    return isReadable(UPPERBOUND);
  }
  
  public boolean isUpperBoundModified()
  {
    return isModified(UPPERBOUND);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getUpperBoundMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(UPPERBOUND).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.RangeCategoryDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.RangeCategoryDTO) dto;
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
  
  public static dss.vector.solutions.query.RangeCategoryQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.RangeCategoryQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.RangeCategory", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.RangeCategoryDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.RangeCategoryDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.RangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.RangeCategoryDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.RangeCategoryDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.RangeCategoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
