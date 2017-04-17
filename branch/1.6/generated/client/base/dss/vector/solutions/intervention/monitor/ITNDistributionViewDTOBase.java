package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1603836096)
public abstract class ITNDistributionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ITNDistributionView";
  private static final long serialVersionUID = 1603836096;
  
  protected ITNDistributionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String PERSON = "person";
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBatchNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BATCHNUMBER).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public Double getCurrencyReceived()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getCurrencyReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CURRENCYRECEIVED).getAttributeMdDTO();
  }
  
  public java.util.Date getDistributionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DISTRIBUTIONDATE));
  }
  
  public void setDistributionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONDATE, "");
    }
    else
    {
      setValue(DISTRIBUTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDistributionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DISTRIBUTIONDATE).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDistributorNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORNAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDistributorSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISTRIBUTORSURNAME).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFacilityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
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
  
  public String getNetId()
  {
    return getValue(NET);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getNetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NET).getAttributeMdDTO();
  }
  
  public Integer getNumberSold()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSOLD));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberSoldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSOLD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PersonDTO getPerson()
  {
    if(getValue(PERSON) == null || getValue(PERSON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PersonDTO.get(getRequest(), getValue(PERSON));
    }
  }
  
  public String getPersonId()
  {
    return getValue(PERSON);
  }
  
  public void setPerson(dss.vector.solutions.PersonDTO value)
  {
    if(value == null)
    {
      setValue(PERSON, "");
    }
    else
    {
      setValue(PERSON, value.getId());
    }
  }
  
  public boolean isPersonWritable()
  {
    return isWritable(PERSON);
  }
  
  public boolean isPersonReadable()
  {
    return isReadable(PERSON);
  }
  
  public boolean isPersonModified()
  {
    return isModified(PERSON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPersonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERSON).getAttributeMdDTO();
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
  
  public String getServiceId()
  {
    return getValue(SERVICE);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getServiceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SERVICE).getAttributeMdDTO();
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
  
  public String getTargetGroupsId()
  {
    return getValue(TARGETGROUPS);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTargetGroupsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TARGETGROUPS).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;"};
    Object[] _parameters = new Object[]{targetGroups};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] targetGroups)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.ITNDistributionTargetGroup;"};
    Object[] _parameters = new Object[]{id, targetGroups};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] getDistributionTargetGroups()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getDistributionTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[] getDistributionTargetGroups(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getDistributionTargetGroups", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionTargetGroupDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.PersonViewDTO getRecipientView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getRecipientView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.PersonViewDTO getRecipientView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "getRecipientView", _declaredTypes);
    return (dss.vector.solutions.PersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ITNDistributionViewQueryDTO searchHistory(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO view)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.intervention.monitor.ITNDistributionView"};
    Object[] _parameters = new Object[]{view};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ITNDistributionViewDTO.CLASS, "searchHistory", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ITNDistributionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ITNDistributionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
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
