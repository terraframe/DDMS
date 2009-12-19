package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1804537857)
public abstract class ZoneSprayDTOBase extends dss.vector.solutions.irs.AbstractSprayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ZoneSpray";
  private static final long serialVersionUID = -1804537857;
  
  protected ZoneSprayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ZoneSprayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SPRAYWEEK = "sprayWeek";
  public static java.lang.String SUPERVISORNAME = "supervisorName";
  public static java.lang.String SUPERVISORSURNAME = "supervisorSurname";
  public static java.lang.String TARGET = "target";
  public Integer getSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYWEEK));
  }
  
  public void setSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYWEEK, "");
    }
    else
    {
      setValue(SPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayWeekWritable()
  {
    return isWritable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekReadable()
  {
    return isReadable(SPRAYWEEK);
  }
  
  public boolean isSprayWeekModified()
  {
    return isModified(SPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYWEEK).getAttributeMdDTO();
  }
  
  public String getSupervisorName()
  {
    return getValue(SUPERVISORNAME);
  }
  
  public void setSupervisorName(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORNAME, "");
    }
    else
    {
      setValue(SUPERVISORNAME, value);
    }
  }
  
  public boolean isSupervisorNameWritable()
  {
    return isWritable(SUPERVISORNAME);
  }
  
  public boolean isSupervisorNameReadable()
  {
    return isReadable(SUPERVISORNAME);
  }
  
  public boolean isSupervisorNameModified()
  {
    return isModified(SUPERVISORNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSupervisorNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUPERVISORNAME).getAttributeMdDTO();
  }
  
  public String getSupervisorSurname()
  {
    return getValue(SUPERVISORSURNAME);
  }
  
  public void setSupervisorSurname(String value)
  {
    if(value == null)
    {
      setValue(SUPERVISORSURNAME, "");
    }
    else
    {
      setValue(SUPERVISORSURNAME, value);
    }
  }
  
  public boolean isSupervisorSurnameWritable()
  {
    return isWritable(SUPERVISORSURNAME);
  }
  
  public boolean isSupervisorSurnameReadable()
  {
    return isReadable(SUPERVISORSURNAME);
  }
  
  public boolean isSupervisorSurnameModified()
  {
    return isModified(SUPERVISORSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSupervisorSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUPERVISORSURNAME).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.ZoneSprayDTO) dto;
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
  
  public static dss.vector.solutions.irs.ZoneSprayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.ZoneSprayQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.ZoneSpray", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.ZoneSprayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
