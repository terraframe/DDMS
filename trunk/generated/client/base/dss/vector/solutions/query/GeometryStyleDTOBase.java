package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 168217379)
public abstract class GeometryStyleDTOBase extends dss.vector.solutions.query.StyleRuleDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.GeometryStyle";
  private static final long serialVersionUID = 168217379;
  
  protected GeometryStyleDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected GeometryStyleDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String STROKE = "stroke";
  public static java.lang.String STROKEWIDTH = "strokeWidth";
  public String getStroke()
  {
    return getValue(STROKE);
  }
  
  public void setStroke(String value)
  {
    if(value == null)
    {
      setValue(STROKE, "");
    }
    else
    {
      setValue(STROKE, value);
    }
  }
  
  public boolean isStrokeWritable()
  {
    return isWritable(STROKE);
  }
  
  public boolean isStrokeReadable()
  {
    return isReadable(STROKE);
  }
  
  public boolean isStrokeModified()
  {
    return isModified(STROKE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getStrokeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(STROKE).getAttributeMdDTO();
  }
  
  public Integer getStrokeWidth()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(STROKEWIDTH));
  }
  
  public void setStrokeWidth(Integer value)
  {
    if(value == null)
    {
      setValue(STROKEWIDTH, "");
    }
    else
    {
      setValue(STROKEWIDTH, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isStrokeWidthWritable()
  {
    return isWritable(STROKEWIDTH);
  }
  
  public boolean isStrokeWidthReadable()
  {
    return isReadable(STROKEWIDTH);
  }
  
  public boolean isStrokeWidthModified()
  {
    return isModified(STROKEWIDTH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getStrokeWidthMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(STROKEWIDTH).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.GeometryStyleDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.GeometryStyleDTO) dto;
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
  
  public static dss.vector.solutions.query.GeometryStyleQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.GeometryStyleQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.GeometryStyle", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.GeometryStyleDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.GeometryStyleDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.GeometryStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.GeometryStyleDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.GeometryStyleDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.GeometryStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
