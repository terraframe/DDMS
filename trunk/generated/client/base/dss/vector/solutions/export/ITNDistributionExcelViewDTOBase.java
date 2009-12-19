package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 2121049586)
public abstract class ITNDistributionExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ITNDistributionExcelView";
  private static final long serialVersionUID = 2121049586;
  
  protected ITNDistributionExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISTRIBUTIONDATE = "distributionDate";
  public static java.lang.String DISTRIBUTORNAME = "distributorName";
  public static java.lang.String DISTRIBUTORSURNAME = "distributorSurname";
  public static java.lang.String FACILITY = "facility";
  public static java.lang.String ID = "id";
  public static java.lang.String NET = "net";
  public static java.lang.String NUMBERSOLD = "numberSold";
  public static java.lang.String RECIPIENTDOB = "recipientDOB";
  public static java.lang.String RECIPIENTFIRSTNAME = "recipientFirstName";
  public static java.lang.String RECIPIENTLASTNAME = "recipientLastName";
  public static java.lang.String SERVICE = "service";
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
  
  public dss.vector.solutions.geo.generated.HealthFacilityDTO getFacility()
  {
    if(getValue(FACILITY) == null || getValue(FACILITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.HealthFacilityDTO.get(getRequest(), getValue(FACILITY));
    }
  }
  
  public void setFacility(dss.vector.solutions.geo.generated.HealthFacilityDTO value)
  {
    if(value == null)
    {
      setValue(FACILITY, "");
    }
    else
    {
      setValue(FACILITY, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFacilityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FACILITY).getAttributeMdDTO();
  }
  
  public String getNet()
  {
    return getValue(NET);
  }
  
  public void setNet(String value)
  {
    if(value == null)
    {
      setValue(NET, "");
    }
    else
    {
      setValue(NET, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NET).getAttributeMdDTO();
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
  
  public java.util.Date getRecipientDOB()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(RECIPIENTDOB));
  }
  
  public void setRecipientDOB(java.util.Date value)
  {
    if(value == null)
    {
      setValue(RECIPIENTDOB, "");
    }
    else
    {
      setValue(RECIPIENTDOB, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isRecipientDOBWritable()
  {
    return isWritable(RECIPIENTDOB);
  }
  
  public boolean isRecipientDOBReadable()
  {
    return isReadable(RECIPIENTDOB);
  }
  
  public boolean isRecipientDOBModified()
  {
    return isModified(RECIPIENTDOB);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getRecipientDOBMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(RECIPIENTDOB).getAttributeMdDTO();
  }
  
  public String getRecipientFirstName()
  {
    return getValue(RECIPIENTFIRSTNAME);
  }
  
  public void setRecipientFirstName(String value)
  {
    if(value == null)
    {
      setValue(RECIPIENTFIRSTNAME, "");
    }
    else
    {
      setValue(RECIPIENTFIRSTNAME, value);
    }
  }
  
  public boolean isRecipientFirstNameWritable()
  {
    return isWritable(RECIPIENTFIRSTNAME);
  }
  
  public boolean isRecipientFirstNameReadable()
  {
    return isReadable(RECIPIENTFIRSTNAME);
  }
  
  public boolean isRecipientFirstNameModified()
  {
    return isModified(RECIPIENTFIRSTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRecipientFirstNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RECIPIENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getRecipientLastName()
  {
    return getValue(RECIPIENTLASTNAME);
  }
  
  public void setRecipientLastName(String value)
  {
    if(value == null)
    {
      setValue(RECIPIENTLASTNAME, "");
    }
    else
    {
      setValue(RECIPIENTLASTNAME, value);
    }
  }
  
  public boolean isRecipientLastNameWritable()
  {
    return isWritable(RECIPIENTLASTNAME);
  }
  
  public boolean isRecipientLastNameReadable()
  {
    return isReadable(RECIPIENTLASTNAME);
  }
  
  public boolean isRecipientLastNameModified()
  {
    return isModified(RECIPIENTLASTNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRecipientLastNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RECIPIENTLASTNAME).getAttributeMdDTO();
  }
  
  public String getService()
  {
    return getValue(SERVICE);
  }
  
  public void setService(String value)
  {
    if(value == null)
    {
      setValue(SERVICE, "");
    }
    else
    {
      setValue(SERVICE, value);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getServiceMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SERVICE).getAttributeMdDTO();
  }
  
  public static ITNDistributionExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ITNDistributionExcelViewDTO) dto;
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
