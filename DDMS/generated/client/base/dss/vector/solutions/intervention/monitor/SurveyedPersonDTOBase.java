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
package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 423631456)
public abstract class SurveyedPersonDTOBase extends com.runwaysdk.business.BusinessDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.SurveyedPerson";
  private static final long serialVersionUID = 423631456;
  
  protected SurveyedPersonDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SurveyedPersonDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ANAEMIATREATMENT = "anaemiaTreatment";
  public static java.lang.String BLOODSLIDEDETAIL = "bloodslideDetail";
  public static java.lang.String BLOODSLIDEREASON = "bloodslideReason";
  public static java.lang.String BLOODSLIDERESULT = "bloodslideResult";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String DOB = "dob";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HEADOFHOUSEHOLD = "headOfHousehold";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String IMMUNECOMPROMISED = "immuneCompromised";
  public static java.lang.String IRON = "iron";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIACONFORMATIONTECHNIQUE = "malariaConformationTechnique";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PERFORMEDBLOODSLIDE = "performedBloodslide";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String RDTDETAIL = "rdtDetail";
  public static java.lang.String RDTRESULT = "rdtResult";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String TYPE = "type";
  public dss.vector.solutions.ontology.TermDTO getAnaemiaTreatment()
  {
    if(getValue(ANAEMIATREATMENT) == null || getValue(ANAEMIATREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ANAEMIATREATMENT));
    }
  }
  
  public String getAnaemiaTreatmentId()
  {
    return getValue(ANAEMIATREATMENT);
  }
  
  public void setAnaemiaTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ANAEMIATREATMENT, "");
    }
    else
    {
      setValue(ANAEMIATREATMENT, value.getId());
    }
  }
  
  public boolean isAnaemiaTreatmentWritable()
  {
    return isWritable(ANAEMIATREATMENT);
  }
  
  public boolean isAnaemiaTreatmentReadable()
  {
    return isReadable(ANAEMIATREATMENT);
  }
  
  public boolean isAnaemiaTreatmentModified()
  {
    return isModified(ANAEMIATREATMENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAnaemiaTreatmentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ANAEMIATREATMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getBloodslideDetail()
  {
    if(getValue(BLOODSLIDEDETAIL) == null || getValue(BLOODSLIDEDETAIL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BLOODSLIDEDETAIL));
    }
  }
  
  public String getBloodslideDetailId()
  {
    return getValue(BLOODSLIDEDETAIL);
  }
  
  public void setBloodslideDetail(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDEDETAIL, "");
    }
    else
    {
      setValue(BLOODSLIDEDETAIL, value.getId());
    }
  }
  
  public boolean isBloodslideDetailWritable()
  {
    return isWritable(BLOODSLIDEDETAIL);
  }
  
  public boolean isBloodslideDetailReadable()
  {
    return isReadable(BLOODSLIDEDETAIL);
  }
  
  public boolean isBloodslideDetailModified()
  {
    return isModified(BLOODSLIDEDETAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBloodslideDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BLOODSLIDEDETAIL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getBloodslideReason()
  {
    if(getValue(BLOODSLIDEREASON) == null || getValue(BLOODSLIDEREASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BLOODSLIDEREASON));
    }
  }
  
  public String getBloodslideReasonId()
  {
    return getValue(BLOODSLIDEREASON);
  }
  
  public void setBloodslideReason(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDEREASON, "");
    }
    else
    {
      setValue(BLOODSLIDEREASON, value.getId());
    }
  }
  
  public boolean isBloodslideReasonWritable()
  {
    return isWritable(BLOODSLIDEREASON);
  }
  
  public boolean isBloodslideReasonReadable()
  {
    return isReadable(BLOODSLIDEREASON);
  }
  
  public boolean isBloodslideReasonModified()
  {
    return isModified(BLOODSLIDEREASON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBloodslideReasonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BLOODSLIDEREASON).getAttributeMdDTO();
  }
  
  public Boolean getBloodslideResult()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(BLOODSLIDERESULT));
  }
  
  public void setBloodslideResult(Boolean value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDERESULT, "");
    }
    else
    {
      setValue(BLOODSLIDERESULT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isBloodslideResultWritable()
  {
    return isWritable(BLOODSLIDERESULT);
  }
  
  public boolean isBloodslideResultReadable()
  {
    return isReadable(BLOODSLIDERESULT);
  }
  
  public boolean isBloodslideResultModified()
  {
    return isModified(BLOODSLIDERESULT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getBloodslideResultMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(BLOODSLIDERESULT).getAttributeMdDTO();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public boolean isCreateDateWritable()
  {
    return isWritable(CREATEDATE);
  }
  
  public boolean isCreateDateReadable()
  {
    return isReadable(CREATEDATE);
  }
  
  public boolean isCreateDateModified()
  {
    return isModified(CREATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getCreateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(CREATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getCreatedBy()
  {
    if(getValue(CREATEDBY) == null || getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public boolean isCreatedByWritable()
  {
    return isWritable(CREATEDBY);
  }
  
  public boolean isCreatedByReadable()
  {
    return isReadable(CREATEDBY);
  }
  
  public boolean isCreatedByModified()
  {
    return isModified(CREATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCreatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CREATEDBY).getAttributeMdDTO();
  }
  
  public java.util.Date getDob()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DOB));
  }
  
  public void setDob(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DOB, "");
    }
    else
    {
      setValue(DOB, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDobWritable()
  {
    return isWritable(DOB);
  }
  
  public boolean isDobReadable()
  {
    return isReadable(DOB);
  }
  
  public boolean isDobModified()
  {
    return isModified(DOB);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDobMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DOB).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.metadata.MdDomainDTO getEntityDomain()
  {
    if(getValue(ENTITYDOMAIN) == null || getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomainDTO.get(getRequest(), getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomainDTO value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public boolean isEntityDomainWritable()
  {
    return isWritable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainReadable()
  {
    return isReadable(ENTITYDOMAIN);
  }
  
  public boolean isEntityDomainModified()
  {
    return isModified(ENTITYDOMAIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getEntityDomainMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ENTITYDOMAIN).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getFever()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.ResponseDTO.CLASS, getEnumNames(FEVER));
  }
  
  public java.util.List<String> getFeverEnumNames()
  {
    return getEnumNames(FEVER);
  }
  
  public void addFever(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(FEVER, enumDTO.toString());
  }
  
  public void removeFever(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(FEVER, enumDTO.toString());
  }
  
  public void clearFever()
  {
    clearEnum(FEVER);
  }
  
  public boolean isFeverWritable()
  {
    return isWritable(FEVER);
  }
  
  public boolean isFeverReadable()
  {
    return isReadable(FEVER);
  }
  
  public boolean isFeverModified()
  {
    return isModified(FEVER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getFeverMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(FEVER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getHaemoglobin()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(HAEMOGLOBIN));
  }
  
  public void setHaemoglobin(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(HAEMOGLOBIN, "");
    }
    else
    {
      setValue(HAEMOGLOBIN, value.toString());
    }
  }
  
  public boolean isHaemoglobinWritable()
  {
    return isWritable(HAEMOGLOBIN);
  }
  
  public boolean isHaemoglobinReadable()
  {
    return isReadable(HAEMOGLOBIN);
  }
  
  public boolean isHaemoglobinModified()
  {
    return isModified(HAEMOGLOBIN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getHaemoglobinMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HAEMOGLOBIN).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.RefusedResponseDTO> getHaemoglobinMeasured()
  {
    return (java.util.List<dss.vector.solutions.RefusedResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.RefusedResponseDTO.CLASS, getEnumNames(HAEMOGLOBINMEASURED));
  }
  
  public java.util.List<String> getHaemoglobinMeasuredEnumNames()
  {
    return getEnumNames(HAEMOGLOBINMEASURED);
  }
  
  public void addHaemoglobinMeasured(dss.vector.solutions.RefusedResponseDTO enumDTO)
  {
    addEnumItem(HAEMOGLOBINMEASURED, enumDTO.toString());
  }
  
  public void removeHaemoglobinMeasured(dss.vector.solutions.RefusedResponseDTO enumDTO)
  {
    removeEnumItem(HAEMOGLOBINMEASURED, enumDTO.toString());
  }
  
  public void clearHaemoglobinMeasured()
  {
    clearEnum(HAEMOGLOBINMEASURED);
  }
  
  public boolean isHaemoglobinMeasuredWritable()
  {
    return isWritable(HAEMOGLOBINMEASURED);
  }
  
  public boolean isHaemoglobinMeasuredReadable()
  {
    return isReadable(HAEMOGLOBINMEASURED);
  }
  
  public boolean isHaemoglobinMeasuredModified()
  {
    return isModified(HAEMOGLOBINMEASURED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getHaemoglobinMeasuredMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(HAEMOGLOBINMEASURED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getHeadOfHousehold()
  {
    if(getValue(HEADOFHOUSEHOLD) == null || getValue(HEADOFHOUSEHOLD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(HEADOFHOUSEHOLD));
    }
  }
  
  public String getHeadOfHouseholdId()
  {
    return getValue(HEADOFHOUSEHOLD);
  }
  
  public void setHeadOfHousehold(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(HEADOFHOUSEHOLD, "");
    }
    else
    {
      setValue(HEADOFHOUSEHOLD, value.getId());
    }
  }
  
  public boolean isHeadOfHouseholdWritable()
  {
    return isWritable(HEADOFHOUSEHOLD);
  }
  
  public boolean isHeadOfHouseholdReadable()
  {
    return isReadable(HEADOFHOUSEHOLD);
  }
  
  public boolean isHeadOfHouseholdModified()
  {
    return isModified(HEADOFHOUSEHOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getHeadOfHouseholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HEADOFHOUSEHOLD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdDTO getHousehold()
  {
    if(getValue(HOUSEHOLD) == null || getValue(HOUSEHOLD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.HouseholdDTO.get(getRequest(), getValue(HOUSEHOLD));
    }
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLD);
  }
  
  public void setHousehold(dss.vector.solutions.intervention.monitor.HouseholdDTO value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLD, "");
    }
    else
    {
      setValue(HOUSEHOLD, value.getId());
    }
  }
  
  public boolean isHouseholdWritable()
  {
    return isWritable(HOUSEHOLD);
  }
  
  public boolean isHouseholdReadable()
  {
    return isReadable(HOUSEHOLD);
  }
  
  public boolean isHouseholdModified()
  {
    return isModified(HOUSEHOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getHouseholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getImmuneCompromised()
  {
    if(getValue(IMMUNECOMPROMISED) == null || getValue(IMMUNECOMPROMISED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(IMMUNECOMPROMISED));
    }
  }
  
  public String getImmuneCompromisedId()
  {
    return getValue(IMMUNECOMPROMISED);
  }
  
  public void setImmuneCompromised(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(IMMUNECOMPROMISED, "");
    }
    else
    {
      setValue(IMMUNECOMPROMISED, value.getId());
    }
  }
  
  public boolean isImmuneCompromisedWritable()
  {
    return isWritable(IMMUNECOMPROMISED);
  }
  
  public boolean isImmuneCompromisedReadable()
  {
    return isReadable(IMMUNECOMPROMISED);
  }
  
  public boolean isImmuneCompromisedModified()
  {
    return isModified(IMMUNECOMPROMISED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getImmuneCompromisedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IMMUNECOMPROMISED).getAttributeMdDTO();
  }
  
  public Boolean getIron()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(IRON));
  }
  
  public void setIron(Boolean value)
  {
    if(value == null)
    {
      setValue(IRON, "");
    }
    else
    {
      setValue(IRON, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIronWritable()
  {
    return isWritable(IRON);
  }
  
  public boolean isIronReadable()
  {
    return isReadable(IRON);
  }
  
  public boolean isIronModified()
  {
    return isModified(IRON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIronMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(IRON).getAttributeMdDTO();
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public boolean isKeyNameWritable()
  {
    return isWritable(KEYNAME);
  }
  
  public boolean isKeyNameReadable()
  {
    return isReadable(KEYNAME);
  }
  
  public boolean isKeyNameModified()
  {
    return isModified(KEYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getKeyNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(KEYNAME).getAttributeMdDTO();
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public boolean isLastUpdateDateWritable()
  {
    return isWritable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateReadable()
  {
    return isReadable(LASTUPDATEDATE);
  }
  
  public boolean isLastUpdateDateModified()
  {
    return isModified(LASTUPDATEDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO getLastUpdateDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateTimeMdDTO) getAttributeDTO(LASTUPDATEDATE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLastUpdatedBy()
  {
    if(getValue(LASTUPDATEDBY) == null || getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByWritable()
  {
    return isWritable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByReadable()
  {
    return isReadable(LASTUPDATEDBY);
  }
  
  public boolean isLastUpdatedByModified()
  {
    return isModified(LASTUPDATEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLastUpdatedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LASTUPDATEDBY).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.SingleActorDTO getLockedBy()
  {
    if(getValue(LOCKEDBY) == null || getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActorDTO.get(getRequest(), getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public boolean isLockedByWritable()
  {
    return isWritable(LOCKEDBY);
  }
  
  public boolean isLockedByReadable()
  {
    return isReadable(LOCKEDBY);
  }
  
  public boolean isLockedByModified()
  {
    return isModified(LOCKEDBY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getLockedByMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(LOCKEDBY).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.ResponseDTO> getMalaria()
  {
    return (java.util.List<dss.vector.solutions.ResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.ResponseDTO.CLASS, getEnumNames(MALARIA));
  }
  
  public java.util.List<String> getMalariaEnumNames()
  {
    return getEnumNames(MALARIA);
  }
  
  public void addMalaria(dss.vector.solutions.ResponseDTO enumDTO)
  {
    addEnumItem(MALARIA, enumDTO.toString());
  }
  
  public void removeMalaria(dss.vector.solutions.ResponseDTO enumDTO)
  {
    removeEnumItem(MALARIA, enumDTO.toString());
  }
  
  public void clearMalaria()
  {
    clearEnum(MALARIA);
  }
  
  public boolean isMalariaWritable()
  {
    return isWritable(MALARIA);
  }
  
  public boolean isMalariaReadable()
  {
    return isReadable(MALARIA);
  }
  
  public boolean isMalariaModified()
  {
    return isModified(MALARIA);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getMalariaMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(MALARIA).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getMalariaConformationTechnique()
  {
    if(getValue(MALARIACONFORMATIONTECHNIQUE) == null || getValue(MALARIACONFORMATIONTECHNIQUE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIACONFORMATIONTECHNIQUE));
    }
  }
  
  public String getMalariaConformationTechniqueId()
  {
    return getValue(MALARIACONFORMATIONTECHNIQUE);
  }
  
  public void setMalariaConformationTechnique(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIACONFORMATIONTECHNIQUE, "");
    }
    else
    {
      setValue(MALARIACONFORMATIONTECHNIQUE, value.getId());
    }
  }
  
  public boolean isMalariaConformationTechniqueWritable()
  {
    return isWritable(MALARIACONFORMATIONTECHNIQUE);
  }
  
  public boolean isMalariaConformationTechniqueReadable()
  {
    return isReadable(MALARIACONFORMATIONTECHNIQUE);
  }
  
  public boolean isMalariaConformationTechniqueModified()
  {
    return isModified(MALARIACONFORMATIONTECHNIQUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getMalariaConformationTechniqueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIACONFORMATIONTECHNIQUE).getAttributeMdDTO();
  }
  
  public com.runwaysdk.system.ActorDTO getOwner()
  {
    if(getValue(OWNER) == null || getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.ActorDTO.get(getRequest(), getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.ActorDTO value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public boolean isOwnerWritable()
  {
    return isWritable(OWNER);
  }
  
  public boolean isOwnerReadable()
  {
    return isReadable(OWNER);
  }
  
  public boolean isOwnerModified()
  {
    return isModified(OWNER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getOwnerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(OWNER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPayment()
  {
    if(getValue(PAYMENT) == null || getValue(PAYMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PAYMENT));
    }
  }
  
  public String getPaymentId()
  {
    return getValue(PAYMENT);
  }
  
  public void setPayment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PAYMENT, "");
    }
    else
    {
      setValue(PAYMENT, value.getId());
    }
  }
  
  public boolean isPaymentWritable()
  {
    return isWritable(PAYMENT);
  }
  
  public boolean isPaymentReadable()
  {
    return isReadable(PAYMENT);
  }
  
  public boolean isPaymentModified()
  {
    return isModified(PAYMENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPaymentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PAYMENT).getAttributeMdDTO();
  }
  
  public Boolean getPerformedBloodslide()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PERFORMEDBLOODSLIDE));
  }
  
  public void setPerformedBloodslide(Boolean value)
  {
    if(value == null)
    {
      setValue(PERFORMEDBLOODSLIDE, "");
    }
    else
    {
      setValue(PERFORMEDBLOODSLIDE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPerformedBloodslideWritable()
  {
    return isWritable(PERFORMEDBLOODSLIDE);
  }
  
  public boolean isPerformedBloodslideReadable()
  {
    return isReadable(PERFORMEDBLOODSLIDE);
  }
  
  public boolean isPerformedBloodslideModified()
  {
    return isModified(PERFORMEDBLOODSLIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPerformedBloodslideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PERFORMEDBLOODSLIDE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.RefusedResponseDTO> getPerformedRDT()
  {
    return (java.util.List<dss.vector.solutions.RefusedResponseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.RefusedResponseDTO.CLASS, getEnumNames(PERFORMEDRDT));
  }
  
  public java.util.List<String> getPerformedRDTEnumNames()
  {
    return getEnumNames(PERFORMEDRDT);
  }
  
  public void addPerformedRDT(dss.vector.solutions.RefusedResponseDTO enumDTO)
  {
    addEnumItem(PERFORMEDRDT, enumDTO.toString());
  }
  
  public void removePerformedRDT(dss.vector.solutions.RefusedResponseDTO enumDTO)
  {
    removeEnumItem(PERFORMEDRDT, enumDTO.toString());
  }
  
  public void clearPerformedRDT()
  {
    clearEnum(PERFORMEDRDT);
  }
  
  public boolean isPerformedRDTWritable()
  {
    return isWritable(PERFORMEDRDT);
  }
  
  public boolean isPerformedRDTReadable()
  {
    return isReadable(PERFORMEDRDT);
  }
  
  public boolean isPerformedRDTModified()
  {
    return isModified(PERFORMEDRDT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getPerformedRDTMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERFORMEDRDT).getAttributeMdDTO();
  }
  
  public String getPersonId()
  {
    return getValue(PERSONID);
  }
  
  public void setPersonId(String value)
  {
    if(value == null)
    {
      setValue(PERSONID, "");
    }
    else
    {
      setValue(PERSONID, value);
    }
  }
  
  public boolean isPersonIdWritable()
  {
    return isWritable(PERSONID);
  }
  
  public boolean isPersonIdReadable()
  {
    return isReadable(PERSONID);
  }
  
  public boolean isPersonIdModified()
  {
    return isModified(PERSONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPersonIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
  }
  
  public Boolean getPregnant()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
  }
  
  public void setPregnant(Boolean value)
  {
    if(value == null)
    {
      setValue(PREGNANT, "");
    }
    else
    {
      setValue(PREGNANT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPregnantWritable()
  {
    return isWritable(PREGNANT);
  }
  
  public boolean isPregnantReadable()
  {
    return isReadable(PREGNANT);
  }
  
  public boolean isPregnantModified()
  {
    return isModified(PREGNANT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPregnantMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PREGNANT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRdtDetail()
  {
    if(getValue(RDTDETAIL) == null || getValue(RDTDETAIL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(RDTDETAIL));
    }
  }
  
  public String getRdtDetailId()
  {
    return getValue(RDTDETAIL);
  }
  
  public void setRdtDetail(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(RDTDETAIL, "");
    }
    else
    {
      setValue(RDTDETAIL, value.getId());
    }
  }
  
  public boolean isRdtDetailWritable()
  {
    return isWritable(RDTDETAIL);
  }
  
  public boolean isRdtDetailReadable()
  {
    return isReadable(RDTDETAIL);
  }
  
  public boolean isRdtDetailModified()
  {
    return isModified(RDTDETAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRdtDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RDTDETAIL).getAttributeMdDTO();
  }
  
  public Boolean getRdtResult()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RDTRESULT));
  }
  
  public void setRdtResult(Boolean value)
  {
    if(value == null)
    {
      setValue(RDTRESULT, "");
    }
    else
    {
      setValue(RDTRESULT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRdtResultWritable()
  {
    return isWritable(RDTRESULT);
  }
  
  public boolean isRdtResultReadable()
  {
    return isReadable(RDTRESULT);
  }
  
  public boolean isRdtResultModified()
  {
    return isModified(RDTRESULT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getRdtResultMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RDTRESULT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRdtTreatment()
  {
    if(getValue(RDTTREATMENT) == null || getValue(RDTTREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(RDTTREATMENT));
    }
  }
  
  public String getRdtTreatmentId()
  {
    return getValue(RDTTREATMENT);
  }
  
  public void setRdtTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(RDTTREATMENT, "");
    }
    else
    {
      setValue(RDTTREATMENT, value.getId());
    }
  }
  
  public boolean isRdtTreatmentWritable()
  {
    return isWritable(RDTTREATMENT);
  }
  
  public boolean isRdtTreatmentReadable()
  {
    return isReadable(RDTTREATMENT);
  }
  
  public boolean isRdtTreatmentModified()
  {
    return isModified(RDTTREATMENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRdtTreatmentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RDTTREATMENT).getAttributeMdDTO();
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public boolean isSeqWritable()
  {
    return isWritable(SEQ);
  }
  
  public boolean isSeqReadable()
  {
    return isReadable(SEQ);
  }
  
  public boolean isSeqModified()
  {
    return isModified(SEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SEQ).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public String getSexId()
  {
    return getValue(SEX);
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public boolean isSiteMasterWritable()
  {
    return isWritable(SITEMASTER);
  }
  
  public boolean isSiteMasterReadable()
  {
    return isReadable(SITEMASTER);
  }
  
  public boolean isSiteMasterModified()
  {
    return isModified(SITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SITEMASTER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ITNInstanceDTO getSleptUnderNet()
  {
    if(getValue(SLEPTUNDERNET) == null || getValue(SLEPTUNDERNET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ITNInstanceDTO.get(getRequest(), getValue(SLEPTUNDERNET));
    }
  }
  
  public String getSleptUnderNetId()
  {
    return getValue(SLEPTUNDERNET);
  }
  
  public void setSleptUnderNet(dss.vector.solutions.intervention.monitor.ITNInstanceDTO value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, value.getId());
    }
  }
  
  public boolean isSleptUnderNetWritable()
  {
    return isWritable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetReadable()
  {
    return isReadable(SLEPTUNDERNET);
  }
  
  public boolean isSleptUnderNetModified()
  {
    return isModified(SLEPTUNDERNET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSleptUnderNetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO getView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO getView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO lockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO unlockView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllLocations()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllLocationsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO> getAllLocationsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addLocations(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO addLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public void removeLocations(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllLocations()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  public static void removeAllLocations(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTreatments()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllTreatmentsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO> getAllTreatmentsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addTreatments(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO addTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public void removeTreatments(dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTreatments()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  public static void removeAllTreatments(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO> getAllHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdDTO>) clientRequestIF.getParents(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO> getAllHouseholdsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO> getAllHouseholdsRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO addHouseholds(dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO addHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.intervention.monitor.HouseholdDTO parent)
  {
    return (dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public void removeHouseholds(dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllHouseholds()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public static void removeAllHouseholds(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.intervention.monitor.HouseholdSurveyedPersonDTO.CLASS);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonDTO) dto;
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
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.intervention.monitor.SurveyedPersonDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.SurveyedPersonDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
