package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2015325539)
public abstract class ActorSprayDTOBase extends dss.vector.solutions.irs.AbstractSprayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ActorSpray";
  private static final long serialVersionUID = 2015325539;
  
  protected ActorSprayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ActorSprayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TEAMSPRAYWEEK = "teamSprayWeek";
  public static java.lang.String USED = "used";
  public Integer getReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedWritable()
  {
    return isWritable(RECEIVED);
  }
  
  public boolean isReceivedReadable()
  {
    return isReadable(RECEIVED);
  }
  
  public boolean isReceivedModified()
  {
    return isModified(RECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVED).getAttributeMdDTO();
  }
  
  public Integer getRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefillsWritable()
  {
    return isWritable(REFILLS);
  }
  
  public boolean isRefillsReadable()
  {
    return isReadable(REFILLS);
  }
  
  public boolean isRefillsModified()
  {
    return isModified(REFILLS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRefillsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFILLS).getAttributeMdDTO();
  }
  
  public Integer getReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReturnedWritable()
  {
    return isWritable(RETURNED);
  }
  
  public boolean isReturnedReadable()
  {
    return isReadable(RETURNED);
  }
  
  public boolean isReturnedModified()
  {
    return isModified(RETURNED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReturnedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RETURNED).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.SprayOperatorDTO getTeamLeader()
  {
    if(getValue(TEAMLEADER) == null || getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperatorDTO.get(getRequest(), getValue(TEAMLEADER));
    }
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.SprayOperatorDTO value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value.getId());
    }
  }
  
  public boolean isTeamLeaderWritable()
  {
    return isWritable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderReadable()
  {
    return isReadable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderModified()
  {
    return isModified(TEAMLEADER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public Integer getTeamSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEAMSPRAYWEEK));
  }
  
  public void setTeamSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(TEAMSPRAYWEEK, "");
    }
    else
    {
      setValue(TEAMSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTeamSprayWeekWritable()
  {
    return isWritable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekReadable()
  {
    return isReadable(TEAMSPRAYWEEK);
  }
  
  public boolean isTeamSprayWeekModified()
  {
    return isModified(TEAMSPRAYWEEK);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTeamSprayWeekMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TEAMSPRAYWEEK).getAttributeMdDTO();
  }
  
  public Integer getUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedWritable()
  {
    return isWritable(USED);
  }
  
  public boolean isUsedReadable()
  {
    return isReadable(USED);
  }
  
  public boolean isUsedModified()
  {
    return isModified(USED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USED).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.irs.ActorSprayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.ActorSprayDTO) dto;
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
  
  public static dss.vector.solutions.irs.ActorSprayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.ActorSprayQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.ActorSpray", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.ActorSprayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ActorSprayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.ActorSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.ActorSprayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.ActorSprayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.ActorSprayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
