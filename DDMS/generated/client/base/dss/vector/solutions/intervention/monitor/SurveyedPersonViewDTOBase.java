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

@com.runwaysdk.business.ClassSignature(hash = 581102609)
public abstract class SurveyedPersonViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.SurveyedPersonView";
  private static final long serialVersionUID = 581102609;
  
  protected SurveyedPersonViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String ANAEMIATREATMENT = "anaemiaTreatment";
  public static java.lang.String BLOODSLIDEDETAIL = "bloodslideDetail";
  public static java.lang.String BLOODSLIDEREASON = "bloodslideReason";
  public static java.lang.String BLOODSLIDERESULT = "bloodslideResult";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYLOCATIONS = "displayLocations";
  public static java.lang.String DISPLAYTREATMENTS = "displayTreatments";
  public static java.lang.String DOB = "dob";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HEADOFHOUSEHOLD = "headOfHousehold";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String IMMUNECOMPROMISED = "immuneCompromised";
  public static java.lang.String IRON = "iron";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIACONFORMATIONTECHNIQUE = "malariaConformationTechnique";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PERFORMEDBLOODSLIDE = "performedBloodslide";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String RDTDETAIL = "rdtDetail";
  public static java.lang.String RDTRESULT = "rdtResult";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public Integer getAge()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
  }
  
  public void setAge(Integer value)
  {
    if(value == null)
    {
      setValue(AGE, "");
    }
    else
    {
      setValue(AGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAgeWritable()
  {
    return isWritable(AGE);
  }
  
  public boolean isAgeReadable()
  {
    return isReadable(AGE);
  }
  
  public boolean isAgeModified()
  {
    return isModified(AGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.ontology.TermDTO getDisplayLocations()
  {
    if(getValue(DISPLAYLOCATIONS) == null || getValue(DISPLAYLOCATIONS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYLOCATIONS));
    }
  }
  
  public String getDisplayLocationsId()
  {
    return getValue(DISPLAYLOCATIONS);
  }
  
  public void setDisplayLocations(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYLOCATIONS, "");
    }
    else
    {
      setValue(DISPLAYLOCATIONS, value.getId());
    }
  }
  
  public boolean isDisplayLocationsWritable()
  {
    return isWritable(DISPLAYLOCATIONS);
  }
  
  public boolean isDisplayLocationsReadable()
  {
    return isReadable(DISPLAYLOCATIONS);
  }
  
  public boolean isDisplayLocationsModified()
  {
    return isModified(DISPLAYLOCATIONS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayLocationsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYLOCATIONS).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDisplayTreatments()
  {
    if(getValue(DISPLAYTREATMENTS) == null || getValue(DISPLAYTREATMENTS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DISPLAYTREATMENTS));
    }
  }
  
  public String getDisplayTreatmentsId()
  {
    return getValue(DISPLAYTREATMENTS);
  }
  
  public void setDisplayTreatments(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DISPLAYTREATMENTS, "");
    }
    else
    {
      setValue(DISPLAYTREATMENTS, value.getId());
    }
  }
  
  public boolean isDisplayTreatmentsWritable()
  {
    return isWritable(DISPLAYTREATMENTS);
  }
  
  public boolean isDisplayTreatmentsReadable()
  {
    return isReadable(DISPLAYTREATMENTS);
  }
  
  public boolean isDisplayTreatmentsModified()
  {
    return isModified(DISPLAYTREATMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDisplayTreatmentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISPLAYTREATMENTS).getAttributeMdDTO();
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
  
  public final void applyAll(dss.vector.solutions.ontology.TermDTO[] locations, dss.vector.solutions.ontology.TermDTO[] treatments)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.ontology.Term;", "[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{locations, treatments};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.ontology.TermDTO[] locations, dss.vector.solutions.ontology.TermDTO[] treatments)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.ontology.Term;", "[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{id, locations, treatments};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.TermDTO[] getLocations()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "getLocations", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getLocations(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "getLocations", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.TermDTO[] getTreatments()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getTreatments(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO.CLASS, "getTreatments", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SurveyedPersonViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SurveyedPersonViewDTO) dto;
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
