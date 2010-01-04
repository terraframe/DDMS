package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = 789113529)
public abstract class PeriodTypeMasterDTOBase extends com.terraframe.mojo.system.EnumerationMasterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.PeriodTypeMaster";
  private static final long serialVersionUID = 789113529;
  
  protected PeriodTypeMasterDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected PeriodTypeMasterDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String MAXIMUMPERIOD = "maximumPeriod";
  public Integer getMaximumPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MAXIMUMPERIOD));
  }
  
  public void setMaximumPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(MAXIMUMPERIOD, "");
    }
    else
    {
      setValue(MAXIMUMPERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaximumPeriodWritable()
  {
    return isWritable(MAXIMUMPERIOD);
  }
  
  public boolean isMaximumPeriodReadable()
  {
    return isReadable(MAXIMUMPERIOD);
  }
  
  public boolean isMaximumPeriodModified()
  {
    return isModified(MAXIMUMPERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMaximumPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MAXIMUMPERIOD).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.surveillance.PeriodTypeMasterDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.surveillance.PeriodTypeMasterDTO) dto;
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
  
  public static dss.vector.solutions.surveillance.PeriodTypeMasterQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.surveillance.PeriodTypeMasterQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.surveillance.PeriodTypeMaster", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.surveillance.PeriodTypeMasterDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.PeriodTypeMasterDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.surveillance.PeriodTypeMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.surveillance.PeriodTypeMasterDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.PeriodTypeMasterDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.surveillance.PeriodTypeMasterDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
