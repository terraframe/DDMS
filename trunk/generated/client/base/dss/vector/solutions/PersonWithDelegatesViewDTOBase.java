package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -122909123)
public abstract class PersonWithDelegatesViewDTOBase extends dss.vector.solutions.PersonViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PersonWithDelegatesView";
  private static final long serialVersionUID = -122909123;
  
  protected PersonWithDelegatesViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String IPTRECIPIENTDELEGATE = "iptRecipientDelegate";
  public static java.lang.String ITNRECIPIENTDELEGATE = "itnRecipientDelegate";
  public static java.lang.String PATIENTDELEGATE = "patientDelegate";
  public static java.lang.String PHYSICIANDELEGATE = "physicianDelegate";
  public static java.lang.String STOCKSTAFFDELEGATE = "stockStaffDelegate";
  public static java.lang.String SUPERVISORDELEGATE = "supervisorDelegate";
  public static java.lang.String TEAMMEMBERDELEGATE = "teamMemberDelegate";
  public static java.lang.String USERDELEGATE = "userDelegate";
  public dss.vector.solutions.intervention.monitor.IPTRecipientDTO getIptRecipientDelegate()
  {
    if(getValue(IPTRECIPIENTDELEGATE) == null || getValue(IPTRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.IPTRecipientDTO.get(getRequest(), getValue(IPTRECIPIENTDELEGATE));
    }
  }
  
  public String getIptRecipientDelegateId()
  {
    return getValue(IPTRECIPIENTDELEGATE);
  }
  
  public void setIptRecipientDelegate(dss.vector.solutions.intervention.monitor.IPTRecipientDTO value)
  {
    if(value == null)
    {
      setValue(IPTRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(IPTRECIPIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isIptRecipientDelegateWritable()
  {
    return isWritable(IPTRECIPIENTDELEGATE);
  }
  
  public boolean isIptRecipientDelegateReadable()
  {
    return isReadable(IPTRECIPIENTDELEGATE);
  }
  
  public boolean isIptRecipientDelegateModified()
  {
    return isModified(IPTRECIPIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIptRecipientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTRECIPIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNRecipientDTO getItnRecipientDelegate()
  {
    if(getValue(ITNRECIPIENTDELEGATE) == null || getValue(ITNRECIPIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipientDTO.get(getRequest(), getValue(ITNRECIPIENTDELEGATE));
    }
  }
  
  public String getItnRecipientDelegateId()
  {
    return getValue(ITNRECIPIENTDELEGATE);
  }
  
  public void setItnRecipientDelegate(dss.vector.solutions.intervention.monitor.ITNRecipientDTO value)
  {
    if(value == null)
    {
      setValue(ITNRECIPIENTDELEGATE, "");
    }
    else
    {
      setValue(ITNRECIPIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isItnRecipientDelegateWritable()
  {
    return isWritable(ITNRECIPIENTDELEGATE);
  }
  
  public boolean isItnRecipientDelegateReadable()
  {
    return isReadable(ITNRECIPIENTDELEGATE);
  }
  
  public boolean isItnRecipientDelegateModified()
  {
    return isModified(ITNRECIPIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getItnRecipientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITNRECIPIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PatientDTO getPatientDelegate()
  {
    if(getValue(PATIENTDELEGATE) == null || getValue(PATIENTDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PatientDTO.get(getRequest(), getValue(PATIENTDELEGATE));
    }
  }
  
  public String getPatientDelegateId()
  {
    return getValue(PATIENTDELEGATE);
  }
  
  public void setPatientDelegate(dss.vector.solutions.PatientDTO value)
  {
    if(value == null)
    {
      setValue(PATIENTDELEGATE, "");
    }
    else
    {
      setValue(PATIENTDELEGATE, value.getId());
    }
  }
  
  public boolean isPatientDelegateWritable()
  {
    return isWritable(PATIENTDELEGATE);
  }
  
  public boolean isPatientDelegateReadable()
  {
    return isReadable(PATIENTDELEGATE);
  }
  
  public boolean isPatientDelegateModified()
  {
    return isModified(PATIENTDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PhysicianDTO getPhysicianDelegate()
  {
    if(getValue(PHYSICIANDELEGATE) == null || getValue(PHYSICIANDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PhysicianDTO.get(getRequest(), getValue(PHYSICIANDELEGATE));
    }
  }
  
  public String getPhysicianDelegateId()
  {
    return getValue(PHYSICIANDELEGATE);
  }
  
  public void setPhysicianDelegate(dss.vector.solutions.PhysicianDTO value)
  {
    if(value == null)
    {
      setValue(PHYSICIANDELEGATE, "");
    }
    else
    {
      setValue(PHYSICIANDELEGATE, value.getId());
    }
  }
  
  public boolean isPhysicianDelegateWritable()
  {
    return isWritable(PHYSICIANDELEGATE);
  }
  
  public boolean isPhysicianDelegateReadable()
  {
    return isReadable(PHYSICIANDELEGATE);
  }
  
  public boolean isPhysicianDelegateModified()
  {
    return isModified(PHYSICIANDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPhysicianDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PHYSICIANDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.stock.StockStaffDTO getStockStaffDelegate()
  {
    if(getValue(STOCKSTAFFDELEGATE) == null || getValue(STOCKSTAFFDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.stock.StockStaffDTO.get(getRequest(), getValue(STOCKSTAFFDELEGATE));
    }
  }
  
  public String getStockStaffDelegateId()
  {
    return getValue(STOCKSTAFFDELEGATE);
  }
  
  public void setStockStaffDelegate(dss.vector.solutions.stock.StockStaffDTO value)
  {
    if(value == null)
    {
      setValue(STOCKSTAFFDELEGATE, "");
    }
    else
    {
      setValue(STOCKSTAFFDELEGATE, value.getId());
    }
  }
  
  public boolean isStockStaffDelegateWritable()
  {
    return isWritable(STOCKSTAFFDELEGATE);
  }
  
  public boolean isStockStaffDelegateReadable()
  {
    return isReadable(STOCKSTAFFDELEGATE);
  }
  
  public boolean isStockStaffDelegateModified()
  {
    return isModified(STOCKSTAFFDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getStockStaffDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKSTAFFDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SupervisorDTO getSupervisorDelegate()
  {
    if(getValue(SUPERVISORDELEGATE) == null || getValue(SUPERVISORDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SupervisorDTO.get(getRequest(), getValue(SUPERVISORDELEGATE));
    }
  }
  
  public String getSupervisorDelegateId()
  {
    return getValue(SUPERVISORDELEGATE);
  }
  
  public void setSupervisorDelegate(dss.vector.solutions.irs.SupervisorDTO value)
  {
    if(value == null)
    {
      setValue(SUPERVISORDELEGATE, "");
    }
    else
    {
      setValue(SUPERVISORDELEGATE, value.getId());
    }
  }
  
  public boolean isSupervisorDelegateWritable()
  {
    return isWritable(SUPERVISORDELEGATE);
  }
  
  public boolean isSupervisorDelegateReadable()
  {
    return isReadable(SUPERVISORDELEGATE);
  }
  
  public boolean isSupervisorDelegateModified()
  {
    return isModified(SUPERVISORDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSupervisorDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISORDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getTeamMemberDelegate()
  {
    if(getValue(TEAMMEMBERDELEGATE) == null || getValue(TEAMMEMBERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(TEAMMEMBERDELEGATE));
    }
  }
  
  public String getTeamMemberDelegateId()
  {
    return getValue(TEAMMEMBERDELEGATE);
  }
  
  public void setTeamMemberDelegate(dss.vector.solutions.irs.TeamMemberDTO value)
  {
    if(value == null)
    {
      setValue(TEAMMEMBERDELEGATE, "");
    }
    else
    {
      setValue(TEAMMEMBERDELEGATE, value.getId());
    }
  }
  
  public boolean isTeamMemberDelegateWritable()
  {
    return isWritable(TEAMMEMBERDELEGATE);
  }
  
  public boolean isTeamMemberDelegateReadable()
  {
    return isReadable(TEAMMEMBERDELEGATE);
  }
  
  public boolean isTeamMemberDelegateModified()
  {
    return isModified(TEAMMEMBERDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTeamMemberDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMMEMBERDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.MDSSUserDTO getUserDelegate()
  {
    if(getValue(USERDELEGATE) == null || getValue(USERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.MDSSUserDTO.get(getRequest(), getValue(USERDELEGATE));
    }
  }
  
  public String getUserDelegateId()
  {
    return getValue(USERDELEGATE);
  }
  
  public void setUserDelegate(dss.vector.solutions.MDSSUserDTO value)
  {
    if(value == null)
    {
      setValue(USERDELEGATE, "");
    }
    else
    {
      setValue(USERDELEGATE, value.getId());
    }
  }
  
  public boolean isUserDelegateWritable()
  {
    return isWritable(USERDELEGATE);
  }
  
  public boolean isUserDelegateReadable()
  {
    return isReadable(USERDELEGATE);
  }
  
  public boolean isUserDelegateModified()
  {
    return isModified(USERDELEGATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUserDelegateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USERDELEGATE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.PersonWithDelegatesViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonWithDelegatesViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PersonWithDelegatesViewDTO) dto;
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
