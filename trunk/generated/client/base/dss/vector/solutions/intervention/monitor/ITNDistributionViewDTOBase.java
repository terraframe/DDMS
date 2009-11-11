package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = -497711849)
public abstract class ITNDistributionViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDistributionView";
  private static final long serialVersionUID = -497711849;
  
  protected ITNDistributionViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISTRIBUTIONDATE = "distributionDate";
  public static java.lang.String DISTRIBUTORNAME = "distributorName";
  public static java.lang.String DISTRIBUTORSURNAME = "distributorSurname";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String NET = "net";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String RECIPIENT = "recipient";
  public static java.lang.String SERVICE = "service";
  public static java.lang.String TARGETGROUPS = "targetGroups";
  public String getBatchNumber()
  {
    return getValue(BATCHNUMBER);
  }
  
  public void setBatchNumber(String value)
  {
    if(value == null)
    {
      setValue(BATCHNUMBER, "");
    }
    else
    {
      setValue(BATCHNUMBER, value);
    }
  }
  
  public boolean isBatchNumberWritable()
  {
    return isWritable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberReadable()
  {
    return isReadable(BATCHNUMBER);
  }
  
  public boolean isBatchNumberModified()
  {
    return isModified(BATCHNUMBER);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public Double getCurrencyReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(Double value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, java.lang.Double.toString(value));
    }
  }
  
  public boolean isCurrencyReceivedWritable()
  {
    return isWritable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedReadable()
  {
    return isReadable(CURRENCYRECEIVED);
  }
  
  public boolean isCurrencyReceivedModified()
  {
    return isModified(CURRENCYRECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
  }
  
  public java.util.Date getDistributionDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DISTRIBUTIONDATE));
  }
  
  public void setDistributionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONDATE, "");
    }
    else
    {
      setValue(DISTRIBUTIONDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDistributionDateWritable()
  {
    return isWritable(DISTRIBUTIONDATE);
  }
  
  public boolean isDistributionDateReadable()
  {
    return isReadable(DISTRIBUTIONDATE);
  }
  
  public boolean isDistributionDateModified()
  {
    return isModified(DISTRIBUTIONDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDistributionDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DISTRIBUTIONDATE).getAttributeMdDTO();
  }
  
  public String getDistributorName()
  {
    return getValue(DISTRIBUTORNAME);
  }
  
  public void setDistributorName(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORNAME, value);
    }
  }
  
  public boolean isDistributorNameWritable()
  {
    return isWritable(DISTRIBUTORNAME);
  }
  
  public boolean isDistributorNameReadable()
  {
    return isReadable(DISTRIBUTORNAME);
  }
  
  public boolean isDistributorNameModified()
  {
    return isModified(DISTRIBUTORNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDistributorNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORNAME).getAttributeMdDTO();
  }
  
  public String getDistributorSurname()
  {
    return getValue(DISTRIBUTORSURNAME);
  }
  
  public void setDistributorSurname(String value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTORSURNAME, "");
    }
    else
    {
      setValue(DISTRIBUTORSURNAME, value);
    }
  }
  
  public boolean isDistributorSurnameWritable()
  {
    return isWritable(DISTRIBUTORSURNAME);
  }
  
  public boolean isDistributorSurnameReadable()
  {
    return isReadable(DISTRIBUTORSURNAME);
  }
  
  public boolean isDistributorSurnameModified()
  {
    return isModified(DISTRIBUTORSURNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getDistributorSurnameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORSURNAME).getAttributeMdDTO();
  }
  
  public String getFacility()
  {
    return getValue(FACILITY);
  }
  
  public void setFacility(String value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value);
    }
  }
  
  public boolean isFacilityWritable()
  {
    return isWritable(FACILITY);
  }
  
  public boolean isFacilityReadable()
  {
    return isReadable(FACILITY);
  }
  
  public boolean isFacilityModified()
  {
    return isModified(FACILITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getNet()
  {
    if(getValue(NET) == null || getValue(NET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(NET));
    }
  }
  
  public void setNet(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(NET, "");
    }
    else
    {
      setValue(NET, value.getId());
    }
  }
  
  public boolean isNetWritable()
  {
    return isWritable(NET);
  }
  
  public boolean isNetReadable()
  {
    return isReadable(NET);
  }
  
  public boolean isNetModified()
  {
    return isModified(NET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NET).getAttributeMdDTO();
  }
  
  public Integer getNumberSold()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
  }
  
  public void setNumberSold(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSOLD, "");
    }
    else
    {
      setValue(NUMBERSOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberSoldWritable()
  {
    return isWritable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldReadable()
  {
    return isReadable(NUMBERSOLD);
  }
  
  public boolean isNumberSoldModified()
  {
    return isModified(NUMBERSOLD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberSoldMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSOLD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNRecipientDTO getRecipient()
  {
    if(getValue(RECIPIENT) == null || getValue(RECIPIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNRecipientDTO.get(getRequest(), getValue(RECIPIENT));
    }
  }
  
  public void setRecipient(dss.vector.solutions.intervention.monitor.ITNRecipientDTO value)
  {
    if(value == null)
    {
      setValue(RECIPIENT, "");
    }
    else
    {
      setValue(RECIPIENT, value.getId());
    }
  }
  
  public boolean isRecipientWritable()
  {
    return isWritable(RECIPIENT);
  }
  
  public boolean isRecipientReadable()
  {
    return isReadable(RECIPIENT);
  }
  
  public boolean isRecipientModified()
  {
    return isModified(RECIPIENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRecipientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RECIPIENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getService()
  {
    if(getValue(SERVICE) == null || getValue(SERVICE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SERVICE));
    }
  }
  
  public void setService(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SERVICE, "");
    }
    else
    {
      setValue(SERVICE, value.getId());
    }
  }
  
  public boolean isServiceWritable()
  {
    return isWritable(SERVICE);
  }
  
  public boolean isServiceReadable()
  {
    return isReadable(SERVICE);
  }
  
  public boolean isServiceModified()
  {
    return isModified(SERVICE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getServiceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SERVICE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTargetGroups()
  {
    if(getValue(TARGETGROUPS) == null || getValue(TARGETGROUPS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TARGETGROUPS));
    }
  }
  
  public void setTargetGroups(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TARGETGROUPS, "");
    }
    else
    {
      setValue(TARGETGROUPS, value.getId());
    }
  }
  
  public boolean isTargetGroupsWritable()
  {
    return isWritable(TARGETGROUPS);
  }
  
  public boolean isTargetGroupsReadable()
  {
    return isReadable(TARGETGROUPS);
  }
  
  public boolean isTargetGroupsModified()
  {
    return isModified(TARGETGROUPS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getTargetGroupsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TARGETGROUPS).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;"};
    Object[] _parameters = new Object[]{targetGroups};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;"};
    Object[] _parameters = new Object[]{id, targetGroups};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] getDistributionTargetGroups()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getDistributionTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] getDistributionTargetGroups(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getDistributionTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNDistributionViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNDistributionViewDTO) dto;
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
