package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = -1162192299)
public abstract class PersonWithDelegatesViewDTOBase extends dss.vector.solutions.PersonViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.PersonWithDelegatesView";
  private static final long serialVersionUID = -1162192299;
  
  protected PersonWithDelegatesViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String SPRAYLEADERDELEGATE = "sprayLeaderDelegate";
  public static java.lang.String SPRAYOPERATORDELEGATE = "sprayOperatorDelegate";
  public static java.lang.String STOCKSTAFFDELEGATE = "stockStaffDelegate";
  public static java.lang.String SUPERVISORDELEGATE = "supervisorDelegate";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIptRecipientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IPTRECIPIENTDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getItnRecipientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ITNRECIPIENTDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPatientDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENTDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayLeaderDTO getSprayLeaderDelegate()
  {
    if(getValue(SPRAYLEADERDELEGATE) == null || getValue(SPRAYLEADERDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayLeaderDTO.get(getRequest(), getValue(SPRAYLEADERDELEGATE));
    }
  }
  
  public void setSprayLeaderDelegate(dss.vector.solutions.irs.SprayLeaderDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYLEADERDELEGATE, "");
    }
    else
    {
      setValue(SPRAYLEADERDELEGATE, value.getId());
    }
  }
  
  public boolean isSprayLeaderDelegateWritable()
  {
    return isWritable(SPRAYLEADERDELEGATE);
  }
  
  public boolean isSprayLeaderDelegateReadable()
  {
    return isReadable(SPRAYLEADERDELEGATE);
  }
  
  public boolean isSprayLeaderDelegateModified()
  {
    return isModified(SPRAYLEADERDELEGATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayLeaderDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYLEADERDELEGATE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayOperatorDTO getSprayOperatorDelegate()
  {
    if(getValue(SPRAYOPERATORDELEGATE) == null || getValue(SPRAYOPERATORDELEGATE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperatorDTO.get(getRequest(), getValue(SPRAYOPERATORDELEGATE));
    }
  }
  
  public void setSprayOperatorDelegate(dss.vector.solutions.irs.SprayOperatorDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATORDELEGATE, "");
    }
    else
    {
      setValue(SPRAYOPERATORDELEGATE, value.getId());
    }
  }
  
  public boolean isSprayOperatorDelegateWritable()
  {
    return isWritable(SPRAYOPERATORDELEGATE);
  }
  
  public boolean isSprayOperatorDelegateReadable()
  {
    return isReadable(SPRAYOPERATORDELEGATE);
  }
  
  public boolean isSprayOperatorDelegateModified()
  {
    return isModified(SPRAYOPERATORDELEGATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayOperatorDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYOPERATORDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getStockStaffDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(STOCKSTAFFDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSupervisorDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISORDELEGATE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getUserDelegateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USERDELEGATE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.PersonWithDelegatesViewQueryDTO getPage(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.PersonWithDelegatesViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.PersonWithDelegatesViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonWithDelegatesViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
