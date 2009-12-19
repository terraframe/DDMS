package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 563666263)
public abstract class ZoneSprayViewDTOBase extends dss.vector.solutions.irs.AbstractSprayViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ZoneSprayView";
  private static final long serialVersionUID = 563666263;
  
  protected ZoneSprayViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
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
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatus()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamSprayStatusViewDTO[] getStatus(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.TeamSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.ZoneSprayViewDTO searchBySprayData(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethodDTO sprayMethod, dss.vector.solutions.irs.InsecticideBrandDTO brand)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "dss.vector.solutions.irs.SprayMethod", "dss.vector.solutions.irs.InsecticideBrand"};
    Object[] _parameters = new Object[]{geoId, sprayDate, sprayMethod, brand};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ZoneSprayViewDTO.CLASS, "searchBySprayData", _declaredTypes);
    return (dss.vector.solutions.irs.ZoneSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ZoneSprayViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ZoneSprayViewDTO) dto;
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
