/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 493317237)
public abstract class ITNCommunityExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ITNCommunityExcelView";
  private static final long serialVersionUID = 493317237;
  
  protected ITNCommunityExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGENTFIRSTNAME = "agentFirstName";
  public static java.lang.String AGENTSURNAME = "agentSurname";
  public static java.lang.String BATCHNUMBER = "batchNumber";
  public static java.lang.String CURRENCYRECEIVED = "currencyReceived";
  public static java.lang.String DISTRIBUTIONLOCATION = "distributionLocation";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String ENTRYTYPE = "entryType";
  public static java.lang.String HOUSEHOLDADDRESS = "householdAddress";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String HOUSEHOLDSURNAME = "householdSurname";
  public static java.lang.String ID = "id";
  public static java.lang.String ITNSRECEIVED = "itnsReceived";
  public static java.lang.String NUMBERRETRIEVED = "numberRetrieved";
  public static java.lang.String PRETREATED = "pretreated";
  public static java.lang.String RESIDENTS = "residents";
  public static java.lang.String RETRIEVED = "retrieved";
  public static java.lang.String SOLD = "sold";
  public static java.lang.String STARTDATE = "startDate";
  public String getAgentFirstName()
  {
    return getValue(AGENTFIRSTNAME);
  }
  
  public void setAgentFirstName(String value)
  {
    if(value == null)
    {
      setValue(AGENTFIRSTNAME, "");
    }
    else
    {
      setValue(AGENTFIRSTNAME, value);
    }
  }
  
  public boolean isAgentFirstNameWritable()
  {
    return isWritable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameReadable()
  {
    return isReadable(AGENTFIRSTNAME);
  }
  
  public boolean isAgentFirstNameModified()
  {
    return isModified(AGENTFIRSTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAgentFirstNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTFIRSTNAME).getAttributeMdDTO();
  }
  
  public String getAgentSurname()
  {
    return getValue(AGENTSURNAME);
  }
  
  public void setAgentSurname(String value)
  {
    if(value == null)
    {
      setValue(AGENTSURNAME, "");
    }
    else
    {
      setValue(AGENTSURNAME, value);
    }
  }
  
  public boolean isAgentSurnameWritable()
  {
    return isWritable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameReadable()
  {
    return isReadable(AGENTSURNAME);
  }
  
  public boolean isAgentSurnameModified()
  {
    return isModified(AGENTSURNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAgentSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AGENTSURNAME).getAttributeMdDTO();
  }
  
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
  
  public java.math.BigDecimal getCurrencyReceived()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CURRENCYRECEIVED));
  }
  
  public void setCurrencyReceived(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CURRENCYRECEIVED, "");
    }
    else
    {
      setValue(CURRENCYRECEIVED, value.toString());
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
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getDistributionLocation()
  {
    if(getValue(DISTRIBUTIONLOCATION) == null || getValue(DISTRIBUTIONLOCATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(DISTRIBUTIONLOCATION));
    }
  }
  
  public String getDistributionLocationId()
  {
    return getValue(DISTRIBUTIONLOCATION);
  }
  
  public void setDistributionLocation(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(DISTRIBUTIONLOCATION, "");
    }
    else
    {
      setValue(DISTRIBUTIONLOCATION, value.getId());
    }
  }
  
  public boolean isDistributionLocationWritable()
  {
    return isWritable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationReadable()
  {
    return isReadable(DISTRIBUTIONLOCATION);
  }
  
  public boolean isDistributionLocationModified()
  {
    return isModified(DISTRIBUTIONLOCATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDistributionLocationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISTRIBUTIONLOCATION).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public Boolean getEntryType()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENTRYTYPE));
  }
  
  public void setEntryType(Boolean value)
  {
    if(value == null)
    {
      setValue(ENTRYTYPE, "");
    }
    else
    {
      setValue(ENTRYTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEntryTypeWritable()
  {
    return isWritable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeReadable()
  {
    return isReadable(ENTRYTYPE);
  }
  
  public boolean isEntryTypeModified()
  {
    return isModified(ENTRYTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEntryTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENTRYTYPE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getHouseholdAddress()
  {
    if(getValue(HOUSEHOLDADDRESS) == null || getValue(HOUSEHOLDADDRESS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(HOUSEHOLDADDRESS));
    }
  }
  
  public String getHouseholdAddressId()
  {
    return getValue(HOUSEHOLDADDRESS);
  }
  
  public void setHouseholdAddress(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDADDRESS, "");
    }
    else
    {
      setValue(HOUSEHOLDADDRESS, value.getId());
    }
  }
  
  public boolean isHouseholdAddressWritable()
  {
    return isWritable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressReadable()
  {
    return isReadable(HOUSEHOLDADDRESS);
  }
  
  public boolean isHouseholdAddressModified()
  {
    return isModified(HOUSEHOLDADDRESS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getHouseholdAddressMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLDADDRESS).getAttributeMdDTO();
  }
  
  public String getHouseholdName()
  {
    return getValue(HOUSEHOLDNAME);
  }
  
  public void setHouseholdName(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDNAME, value);
    }
  }
  
  public boolean isHouseholdNameWritable()
  {
    return isWritable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameReadable()
  {
    return isReadable(HOUSEHOLDNAME);
  }
  
  public boolean isHouseholdNameModified()
  {
    return isModified(HOUSEHOLDNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
  }
  
  public String getHouseholdSurname()
  {
    return getValue(HOUSEHOLDSURNAME);
  }
  
  public void setHouseholdSurname(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDSURNAME, "");
    }
    else
    {
      setValue(HOUSEHOLDSURNAME, value);
    }
  }
  
  public boolean isHouseholdSurnameWritable()
  {
    return isWritable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameReadable()
  {
    return isReadable(HOUSEHOLDSURNAME);
  }
  
  public boolean isHouseholdSurnameModified()
  {
    return isModified(HOUSEHOLDSURNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHouseholdSurnameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDSURNAME).getAttributeMdDTO();
  }
  
  public Integer getItnsReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ITNSRECEIVED));
  }
  
  public void setItnsReceived(Integer value)
  {
    if(value == null)
    {
      setValue(ITNSRECEIVED, "");
    }
    else
    {
      setValue(ITNSRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isItnsReceivedWritable()
  {
    return isWritable(ITNSRECEIVED);
  }
  
  public boolean isItnsReceivedReadable()
  {
    return isReadable(ITNSRECEIVED);
  }
  
  public boolean isItnsReceivedModified()
  {
    return isModified(ITNSRECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getItnsReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ITNSRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getNumberRetrieved()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRETRIEVED));
  }
  
  public void setNumberRetrieved(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRETRIEVED, "");
    }
    else
    {
      setValue(NUMBERRETRIEVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRetrievedWritable()
  {
    return isWritable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedReadable()
  {
    return isReadable(NUMBERRETRIEVED);
  }
  
  public boolean isNumberRetrievedModified()
  {
    return isModified(NUMBERRETRIEVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberRetrievedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRETRIEVED).getAttributeMdDTO();
  }
  
  public Boolean getPretreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PRETREATED));
  }
  
  public void setPretreated(Boolean value)
  {
    if(value == null)
    {
      setValue(PRETREATED, "");
    }
    else
    {
      setValue(PRETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPretreatedWritable()
  {
    return isWritable(PRETREATED);
  }
  
  public boolean isPretreatedReadable()
  {
    return isReadable(PRETREATED);
  }
  
  public boolean isPretreatedModified()
  {
    return isModified(PRETREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPretreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PRETREATED).getAttributeMdDTO();
  }
  
  public Integer getResidents()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RESIDENTS));
  }
  
  public void setResidents(Integer value)
  {
    if(value == null)
    {
      setValue(RESIDENTS, "");
    }
    else
    {
      setValue(RESIDENTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isResidentsWritable()
  {
    return isWritable(RESIDENTS);
  }
  
  public boolean isResidentsReadable()
  {
    return isReadable(RESIDENTS);
  }
  
  public boolean isResidentsModified()
  {
    return isModified(RESIDENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getResidentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RESIDENTS).getAttributeMdDTO();
  }
  
  public Boolean getRetrieved()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETRIEVED));
  }
  
  public void setRetrieved(Boolean value)
  {
    if(value == null)
    {
      setValue(RETRIEVED, "");
    }
    else
    {
      setValue(RETRIEVED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetrievedWritable()
  {
    return isWritable(RETRIEVED);
  }
  
  public boolean isRetrievedReadable()
  {
    return isReadable(RETRIEVED);
  }
  
  public boolean isRetrievedModified()
  {
    return isModified(RETRIEVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getRetrievedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETRIEVED).getAttributeMdDTO();
  }
  
  public Boolean getSold()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SOLD));
  }
  
  public void setSold(Boolean value)
  {
    if(value == null)
    {
      setValue(SOLD, "");
    }
    else
    {
      setValue(SOLD, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSoldWritable()
  {
    return isWritable(SOLD);
  }
  
  public boolean isSoldReadable()
  {
    return isReadable(SOLD);
  }
  
  public boolean isSoldModified()
  {
    return isModified(SOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getSoldMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SOLD).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public static ITNCommunityExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ITNCommunityExcelViewDTO) dto;
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
