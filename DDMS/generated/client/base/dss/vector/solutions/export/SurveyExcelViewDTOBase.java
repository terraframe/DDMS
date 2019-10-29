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

@com.runwaysdk.business.ClassSignature(hash = 643037542)
public abstract class SurveyExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.SurveyExcelView";
  private static final long serialVersionUID = 643037542;
  
  protected SurveyExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String DAMAGED = "damaged";
  public static java.lang.String DOB = "dob";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HANGING = "hanging";
  public static java.lang.String HASBEENSPRAYED = "hasBeenSprayed";
  public static java.lang.String HASWINDOWS = "hasWindows";
  public static java.lang.String HEADOFHOUSEHOLD = "headOfHousehold";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String ID = "id";
  public static java.lang.String IMMUNECOMPROMISED = "immuneCompromised";
  public static java.lang.String IRON = "iron";
  public static java.lang.String LASTSPRAYED = "lastSprayed";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIACONFORMATIONTECHNIQUE = "malariaConformationTechnique";
  public static java.lang.String MONTHRECEIVED = "monthReceived";
  public static java.lang.String MONTHRETREATED = "monthRetreated";
  public static java.lang.String NETBRAND = "netBrand";
  public static java.lang.String NETID = "netId";
  public static java.lang.String NETS = "nets";
  public static java.lang.String NOTUSEDFORSLEEPING = "notUsedForSleeping";
  public static java.lang.String OBTAINED = "obtained";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PERFORMEDBLOODSLIDE = "performedBloodslide";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String PRICE = "price";
  public static java.lang.String PURPOSE = "purpose";
  public static java.lang.String PURPOSECOMMENTS = "purposeComments";
  public static java.lang.String RDTDETAIL = "rdtDetail";
  public static java.lang.String RDTRESULT = "rdtResult";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String RETREATED = "retreated";
  public static java.lang.String ROOFINFO = "roofInfo";
  public static java.lang.String ROOFSURFACE = "roofSurface";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String SLEPTUNDERNETID = "sleptUnderNetId";
  public static java.lang.String SURVEYDATE = "surveyDate";
  public static java.lang.String URBAN = "urban";
  public static java.lang.String WALLINFO = "wallInfo";
  public static java.lang.String WALLSURFACE = "wallSurface";
  public static java.lang.String WASHFREQUENCY = "washFrequency";
  public static java.lang.String WASHPERIOD = "washPeriod";
  public static java.lang.String WASHED = "washed";
  public static java.lang.String WINDOWTYPE = "windowType";
  public static java.lang.String YEARRECEIVED = "yearReceived";
  public static java.lang.String YEARRETREATED = "yearRetreated";
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
  
  public String getAnaemiaTreatment()
  {
    return getValue(ANAEMIATREATMENT);
  }
  
  public void setAnaemiaTreatment(String value)
  {
    if(value == null)
    {
      setValue(ANAEMIATREATMENT, "");
    }
    else
    {
      setValue(ANAEMIATREATMENT, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAnaemiaTreatmentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ANAEMIATREATMENT).getAttributeMdDTO();
  }
  
  public String getBloodslideDetail()
  {
    return getValue(BLOODSLIDEDETAIL);
  }
  
  public void setBloodslideDetail(String value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDEDETAIL, "");
    }
    else
    {
      setValue(BLOODSLIDEDETAIL, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBloodslideDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BLOODSLIDEDETAIL).getAttributeMdDTO();
  }
  
  public String getBloodslideReason()
  {
    return getValue(BLOODSLIDEREASON);
  }
  
  public void setBloodslideReason(String value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDEREASON, "");
    }
    else
    {
      setValue(BLOODSLIDEREASON, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBloodslideReasonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BLOODSLIDEREASON).getAttributeMdDTO();
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
  
  public String getDamaged()
  {
    return getValue(DAMAGED);
  }
  
  public void setDamaged(String value)
  {
    if(value == null)
    {
      setValue(DAMAGED, "");
    }
    else
    {
      setValue(DAMAGED, value);
    }
  }
  
  public boolean isDamagedWritable()
  {
    return isWritable(DAMAGED);
  }
  
  public boolean isDamagedReadable()
  {
    return isReadable(DAMAGED);
  }
  
  public boolean isDamagedModified()
  {
    return isModified(DAMAGED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDamagedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DAMAGED).getAttributeMdDTO();
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
  
  public String getFever()
  {
    return getValue(FEVER);
  }
  
  public void setFever(String value)
  {
    if(value == null)
    {
      setValue(FEVER, "");
    }
    else
    {
      setValue(FEVER, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFeverMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FEVER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
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
  
  public String getHaemoglobinMeasured()
  {
    return getValue(HAEMOGLOBINMEASURED);
  }
  
  public void setHaemoglobinMeasured(String value)
  {
    if(value == null)
    {
      setValue(HAEMOGLOBINMEASURED, "");
    }
    else
    {
      setValue(HAEMOGLOBINMEASURED, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHaemoglobinMeasuredMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HAEMOGLOBINMEASURED).getAttributeMdDTO();
  }
  
  public String getHanging()
  {
    return getValue(HANGING);
  }
  
  public void setHanging(String value)
  {
    if(value == null)
    {
      setValue(HANGING, "");
    }
    else
    {
      setValue(HANGING, value);
    }
  }
  
  public boolean isHangingWritable()
  {
    return isWritable(HANGING);
  }
  
  public boolean isHangingReadable()
  {
    return isReadable(HANGING);
  }
  
  public boolean isHangingModified()
  {
    return isModified(HANGING);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHangingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HANGING).getAttributeMdDTO();
  }
  
  public String getHasBeenSprayed()
  {
    return getValue(HASBEENSPRAYED);
  }
  
  public void setHasBeenSprayed(String value)
  {
    if(value == null)
    {
      setValue(HASBEENSPRAYED, "");
    }
    else
    {
      setValue(HASBEENSPRAYED, value);
    }
  }
  
  public boolean isHasBeenSprayedWritable()
  {
    return isWritable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedReadable()
  {
    return isReadable(HASBEENSPRAYED);
  }
  
  public boolean isHasBeenSprayedModified()
  {
    return isModified(HASBEENSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHasBeenSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HASBEENSPRAYED).getAttributeMdDTO();
  }
  
  public Boolean getHasWindows()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASWINDOWS));
  }
  
  public void setHasWindows(Boolean value)
  {
    if(value == null)
    {
      setValue(HASWINDOWS, "");
    }
    else
    {
      setValue(HASWINDOWS, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHasWindowsWritable()
  {
    return isWritable(HASWINDOWS);
  }
  
  public boolean isHasWindowsReadable()
  {
    return isReadable(HASWINDOWS);
  }
  
  public boolean isHasWindowsModified()
  {
    return isModified(HASWINDOWS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getHasWindowsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASWINDOWS).getAttributeMdDTO();
  }
  
  public String getHeadOfHousehold()
  {
    return getValue(HEADOFHOUSEHOLD);
  }
  
  public void setHeadOfHousehold(String value)
  {
    if(value == null)
    {
      setValue(HEADOFHOUSEHOLD, "");
    }
    else
    {
      setValue(HEADOFHOUSEHOLD, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHeadOfHouseholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HEADOFHOUSEHOLD).getAttributeMdDTO();
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
  
  public String getImmuneCompromised()
  {
    return getValue(IMMUNECOMPROMISED);
  }
  
  public void setImmuneCompromised(String value)
  {
    if(value == null)
    {
      setValue(IMMUNECOMPROMISED, "");
    }
    else
    {
      setValue(IMMUNECOMPROMISED, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getImmuneCompromisedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IMMUNECOMPROMISED).getAttributeMdDTO();
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
  
  public Integer getLastSprayed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTSPRAYED));
  }
  
  public void setLastSprayed(Integer value)
  {
    if(value == null)
    {
      setValue(LASTSPRAYED, "");
    }
    else
    {
      setValue(LASTSPRAYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLastSprayedWritable()
  {
    return isWritable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedReadable()
  {
    return isReadable(LASTSPRAYED);
  }
  
  public boolean isLastSprayedModified()
  {
    return isModified(LASTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLastSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LASTSPRAYED).getAttributeMdDTO();
  }
  
  public String getMalaria()
  {
    return getValue(MALARIA);
  }
  
  public void setMalaria(String value)
  {
    if(value == null)
    {
      setValue(MALARIA, "");
    }
    else
    {
      setValue(MALARIA, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMalariaMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MALARIA).getAttributeMdDTO();
  }
  
  public String getMalariaConformationTechnique()
  {
    return getValue(MALARIACONFORMATIONTECHNIQUE);
  }
  
  public void setMalariaConformationTechnique(String value)
  {
    if(value == null)
    {
      setValue(MALARIACONFORMATIONTECHNIQUE, "");
    }
    else
    {
      setValue(MALARIACONFORMATIONTECHNIQUE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMalariaConformationTechniqueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MALARIACONFORMATIONTECHNIQUE).getAttributeMdDTO();
  }
  
  public String getMonthReceived()
  {
    return getValue(MONTHRECEIVED);
  }
  
  public void setMonthReceived(String value)
  {
    if(value == null)
    {
      setValue(MONTHRECEIVED, "");
    }
    else
    {
      setValue(MONTHRECEIVED, value);
    }
  }
  
  public boolean isMonthReceivedWritable()
  {
    return isWritable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedReadable()
  {
    return isReadable(MONTHRECEIVED);
  }
  
  public boolean isMonthReceivedModified()
  {
    return isModified(MONTHRECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMonthReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MONTHRECEIVED).getAttributeMdDTO();
  }
  
  public String getMonthRetreated()
  {
    return getValue(MONTHRETREATED);
  }
  
  public void setMonthRetreated(String value)
  {
    if(value == null)
    {
      setValue(MONTHRETREATED, "");
    }
    else
    {
      setValue(MONTHRETREATED, value);
    }
  }
  
  public boolean isMonthRetreatedWritable()
  {
    return isWritable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedReadable()
  {
    return isReadable(MONTHRETREATED);
  }
  
  public boolean isMonthRetreatedModified()
  {
    return isModified(MONTHRETREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMonthRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MONTHRETREATED).getAttributeMdDTO();
  }
  
  public String getNetBrand()
  {
    return getValue(NETBRAND);
  }
  
  public void setNetBrand(String value)
  {
    if(value == null)
    {
      setValue(NETBRAND, "");
    }
    else
    {
      setValue(NETBRAND, value);
    }
  }
  
  public boolean isNetBrandWritable()
  {
    return isWritable(NETBRAND);
  }
  
  public boolean isNetBrandReadable()
  {
    return isReadable(NETBRAND);
  }
  
  public boolean isNetBrandModified()
  {
    return isModified(NETBRAND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNetBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NETBRAND).getAttributeMdDTO();
  }
  
  public String getNetId()
  {
    return getValue(NETID);
  }
  
  public void setNetId(String value)
  {
    if(value == null)
    {
      setValue(NETID, "");
    }
    else
    {
      setValue(NETID, value);
    }
  }
  
  public boolean isNetIdWritable()
  {
    return isWritable(NETID);
  }
  
  public boolean isNetIdReadable()
  {
    return isReadable(NETID);
  }
  
  public boolean isNetIdModified()
  {
    return isModified(NETID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getNetIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(NETID).getAttributeMdDTO();
  }
  
  public Integer getNets()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETS));
  }
  
  public void setNets(Integer value)
  {
    if(value == null)
    {
      setValue(NETS, "");
    }
    else
    {
      setValue(NETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNetsWritable()
  {
    return isWritable(NETS);
  }
  
  public boolean isNetsReadable()
  {
    return isReadable(NETS);
  }
  
  public boolean isNetsModified()
  {
    return isModified(NETS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNetsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETS).getAttributeMdDTO();
  }
  
  public Boolean getNotUsedForSleeping()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTUSEDFORSLEEPING));
  }
  
  public void setNotUsedForSleeping(Boolean value)
  {
    if(value == null)
    {
      setValue(NOTUSEDFORSLEEPING, "");
    }
    else
    {
      setValue(NOTUSEDFORSLEEPING, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNotUsedForSleepingWritable()
  {
    return isWritable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingReadable()
  {
    return isReadable(NOTUSEDFORSLEEPING);
  }
  
  public boolean isNotUsedForSleepingModified()
  {
    return isModified(NOTUSEDFORSLEEPING);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getNotUsedForSleepingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTUSEDFORSLEEPING).getAttributeMdDTO();
  }
  
  public String getObtained()
  {
    return getValue(OBTAINED);
  }
  
  public void setObtained(String value)
  {
    if(value == null)
    {
      setValue(OBTAINED, "");
    }
    else
    {
      setValue(OBTAINED, value);
    }
  }
  
  public boolean isObtainedWritable()
  {
    return isWritable(OBTAINED);
  }
  
  public boolean isObtainedReadable()
  {
    return isReadable(OBTAINED);
  }
  
  public boolean isObtainedModified()
  {
    return isModified(OBTAINED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getObtainedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OBTAINED).getAttributeMdDTO();
  }
  
  public String getPayment()
  {
    return getValue(PAYMENT);
  }
  
  public void setPayment(String value)
  {
    if(value == null)
    {
      setValue(PAYMENT, "");
    }
    else
    {
      setValue(PAYMENT, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPaymentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PAYMENT).getAttributeMdDTO();
  }
  
  public Integer getPeople()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
  }
  
  public void setPeople(Integer value)
  {
    if(value == null)
    {
      setValue(PEOPLE, "");
    }
    else
    {
      setValue(PEOPLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeopleWritable()
  {
    return isWritable(PEOPLE);
  }
  
  public boolean isPeopleReadable()
  {
    return isReadable(PEOPLE);
  }
  
  public boolean isPeopleModified()
  {
    return isModified(PEOPLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
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
  
  public String getPerformedRDT()
  {
    return getValue(PERFORMEDRDT);
  }
  
  public void setPerformedRDT(String value)
  {
    if(value == null)
    {
      setValue(PERFORMEDRDT, "");
    }
    else
    {
      setValue(PERFORMEDRDT, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPerformedRDTMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERFORMEDRDT).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getPrice()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PRICE));
  }
  
  public void setPrice(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PRICE, "");
    }
    else
    {
      setValue(PRICE, value.toString());
    }
  }
  
  public boolean isPriceWritable()
  {
    return isWritable(PRICE);
  }
  
  public boolean isPriceReadable()
  {
    return isReadable(PRICE);
  }
  
  public boolean isPriceModified()
  {
    return isModified(PRICE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPriceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PRICE).getAttributeMdDTO();
  }
  
  public String getPurpose()
  {
    return getValue(PURPOSE);
  }
  
  public void setPurpose(String value)
  {
    if(value == null)
    {
      setValue(PURPOSE, "");
    }
    else
    {
      setValue(PURPOSE, value);
    }
  }
  
  public boolean isPurposeWritable()
  {
    return isWritable(PURPOSE);
  }
  
  public boolean isPurposeReadable()
  {
    return isReadable(PURPOSE);
  }
  
  public boolean isPurposeModified()
  {
    return isModified(PURPOSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPurposeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PURPOSE).getAttributeMdDTO();
  }
  
  public String getPurposeComments()
  {
    return getValue(PURPOSECOMMENTS);
  }
  
  public void setPurposeComments(String value)
  {
    if(value == null)
    {
      setValue(PURPOSECOMMENTS, "");
    }
    else
    {
      setValue(PURPOSECOMMENTS, value);
    }
  }
  
  public boolean isPurposeCommentsWritable()
  {
    return isWritable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsReadable()
  {
    return isReadable(PURPOSECOMMENTS);
  }
  
  public boolean isPurposeCommentsModified()
  {
    return isModified(PURPOSECOMMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getPurposeCommentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(PURPOSECOMMENTS).getAttributeMdDTO();
  }
  
  public String getRdtDetail()
  {
    return getValue(RDTDETAIL);
  }
  
  public void setRdtDetail(String value)
  {
    if(value == null)
    {
      setValue(RDTDETAIL, "");
    }
    else
    {
      setValue(RDTDETAIL, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRdtDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RDTDETAIL).getAttributeMdDTO();
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
  
  public String getRdtTreatment()
  {
    return getValue(RDTTREATMENT);
  }
  
  public void setRdtTreatment(String value)
  {
    if(value == null)
    {
      setValue(RDTTREATMENT, "");
    }
    else
    {
      setValue(RDTTREATMENT, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRdtTreatmentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RDTTREATMENT).getAttributeMdDTO();
  }
  
  public Boolean getRetreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(RETREATED));
  }
  
  public void setRetreated(Boolean value)
  {
    if(value == null)
    {
      setValue(RETREATED, "");
    }
    else
    {
      setValue(RETREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isRetreatedWritable()
  {
    return isWritable(RETREATED);
  }
  
  public boolean isRetreatedReadable()
  {
    return isReadable(RETREATED);
  }
  
  public boolean isRetreatedModified()
  {
    return isModified(RETREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(RETREATED).getAttributeMdDTO();
  }
  
  public String getRoofInfo()
  {
    return getValue(ROOFINFO);
  }
  
  public void setRoofInfo(String value)
  {
    if(value == null)
    {
      setValue(ROOFINFO, "");
    }
    else
    {
      setValue(ROOFINFO, value);
    }
  }
  
  public boolean isRoofInfoWritable()
  {
    return isWritable(ROOFINFO);
  }
  
  public boolean isRoofInfoReadable()
  {
    return isReadable(ROOFINFO);
  }
  
  public boolean isRoofInfoModified()
  {
    return isModified(ROOFINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoofInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOFINFO).getAttributeMdDTO();
  }
  
  public String getRoofSurface()
  {
    return getValue(ROOFSURFACE);
  }
  
  public void setRoofSurface(String value)
  {
    if(value == null)
    {
      setValue(ROOFSURFACE, "");
    }
    else
    {
      setValue(ROOFSURFACE, value);
    }
  }
  
  public boolean isRoofSurfaceWritable()
  {
    return isWritable(ROOFSURFACE);
  }
  
  public boolean isRoofSurfaceReadable()
  {
    return isReadable(ROOFSURFACE);
  }
  
  public boolean isRoofSurfaceModified()
  {
    return isModified(ROOFSURFACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoofSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOFSURFACE).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
  }
  
  public void setRooms(Integer value)
  {
    if(value == null)
    {
      setValue(ROOMS, "");
    }
    else
    {
      setValue(ROOMS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRoomsWritable()
  {
    return isWritable(ROOMS);
  }
  
  public boolean isRoomsReadable()
  {
    return isReadable(ROOMS);
  }
  
  public boolean isRoomsModified()
  {
    return isModified(ROOMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public Long getSleptUnderNet()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
  }
  
  public void setSleptUnderNet(Long value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, java.lang.Long.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSleptUnderNetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public String getSleptUnderNetId()
  {
    return getValue(SLEPTUNDERNETID);
  }
  
  public void setSleptUnderNetId(String value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNETID, "");
    }
    else
    {
      setValue(SLEPTUNDERNETID, value);
    }
  }
  
  public boolean isSleptUnderNetIdWritable()
  {
    return isWritable(SLEPTUNDERNETID);
  }
  
  public boolean isSleptUnderNetIdReadable()
  {
    return isReadable(SLEPTUNDERNETID);
  }
  
  public boolean isSleptUnderNetIdModified()
  {
    return isModified(SLEPTUNDERNETID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSleptUnderNetIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SLEPTUNDERNETID).getAttributeMdDTO();
  }
  
  public java.util.Date getSurveyDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SURVEYDATE));
  }
  
  public void setSurveyDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SURVEYDATE, "");
    }
    else
    {
      setValue(SURVEYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSurveyDateWritable()
  {
    return isWritable(SURVEYDATE);
  }
  
  public boolean isSurveyDateReadable()
  {
    return isReadable(SURVEYDATE);
  }
  
  public boolean isSurveyDateModified()
  {
    return isModified(SURVEYDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSurveyDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SURVEYDATE).getAttributeMdDTO();
  }
  
  public Boolean getUrban()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
  }
  
  public void setUrban(Boolean value)
  {
    if(value == null)
    {
      setValue(URBAN, "");
    }
    else
    {
      setValue(URBAN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUrbanWritable()
  {
    return isWritable(URBAN);
  }
  
  public boolean isUrbanReadable()
  {
    return isReadable(URBAN);
  }
  
  public boolean isUrbanModified()
  {
    return isModified(URBAN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getUrbanMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(URBAN).getAttributeMdDTO();
  }
  
  public String getWallInfo()
  {
    return getValue(WALLINFO);
  }
  
  public void setWallInfo(String value)
  {
    if(value == null)
    {
      setValue(WALLINFO, "");
    }
    else
    {
      setValue(WALLINFO, value);
    }
  }
  
  public boolean isWallInfoWritable()
  {
    return isWritable(WALLINFO);
  }
  
  public boolean isWallInfoReadable()
  {
    return isReadable(WALLINFO);
  }
  
  public boolean isWallInfoModified()
  {
    return isModified(WALLINFO);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWallInfoMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLINFO).getAttributeMdDTO();
  }
  
  public String getWallSurface()
  {
    return getValue(WALLSURFACE);
  }
  
  public void setWallSurface(String value)
  {
    if(value == null)
    {
      setValue(WALLSURFACE, "");
    }
    else
    {
      setValue(WALLSURFACE, value);
    }
  }
  
  public boolean isWallSurfaceWritable()
  {
    return isWritable(WALLSURFACE);
  }
  
  public boolean isWallSurfaceReadable()
  {
    return isReadable(WALLSURFACE);
  }
  
  public boolean isWallSurfaceModified()
  {
    return isModified(WALLSURFACE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWallSurfaceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLSURFACE).getAttributeMdDTO();
  }
  
  public Integer getWashFrequency()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(WASHFREQUENCY));
  }
  
  public void setWashFrequency(Integer value)
  {
    if(value == null)
    {
      setValue(WASHFREQUENCY, "");
    }
    else
    {
      setValue(WASHFREQUENCY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isWashFrequencyWritable()
  {
    return isWritable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyReadable()
  {
    return isReadable(WASHFREQUENCY);
  }
  
  public boolean isWashFrequencyModified()
  {
    return isModified(WASHFREQUENCY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getWashFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(WASHFREQUENCY).getAttributeMdDTO();
  }
  
  public String getWashPeriod()
  {
    return getValue(WASHPERIOD);
  }
  
  public void setWashPeriod(String value)
  {
    if(value == null)
    {
      setValue(WASHPERIOD, "");
    }
    else
    {
      setValue(WASHPERIOD, value);
    }
  }
  
  public boolean isWashPeriodWritable()
  {
    return isWritable(WASHPERIOD);
  }
  
  public boolean isWashPeriodReadable()
  {
    return isReadable(WASHPERIOD);
  }
  
  public boolean isWashPeriodModified()
  {
    return isModified(WASHPERIOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWashPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WASHPERIOD).getAttributeMdDTO();
  }
  
  public String getWashed()
  {
    return getValue(WASHED);
  }
  
  public void setWashed(String value)
  {
    if(value == null)
    {
      setValue(WASHED, "");
    }
    else
    {
      setValue(WASHED, value);
    }
  }
  
  public boolean isWashedWritable()
  {
    return isWritable(WASHED);
  }
  
  public boolean isWashedReadable()
  {
    return isReadable(WASHED);
  }
  
  public boolean isWashedModified()
  {
    return isModified(WASHED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWashedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WASHED).getAttributeMdDTO();
  }
  
  public String getWindowType()
  {
    return getValue(WINDOWTYPE);
  }
  
  public void setWindowType(String value)
  {
    if(value == null)
    {
      setValue(WINDOWTYPE, "");
    }
    else
    {
      setValue(WINDOWTYPE, value);
    }
  }
  
  public boolean isWindowTypeWritable()
  {
    return isWritable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeReadable()
  {
    return isReadable(WINDOWTYPE);
  }
  
  public boolean isWindowTypeModified()
  {
    return isModified(WINDOWTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWindowTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WINDOWTYPE).getAttributeMdDTO();
  }
  
  public Integer getYearReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRECEIVED));
  }
  
  public void setYearReceived(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRECEIVED, "");
    }
    else
    {
      setValue(YEARRECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearReceivedWritable()
  {
    return isWritable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedReadable()
  {
    return isReadable(YEARRECEIVED);
  }
  
  public boolean isYearReceivedModified()
  {
    return isModified(YEARRECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRECEIVED).getAttributeMdDTO();
  }
  
  public Integer getYearRetreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(YEARRETREATED));
  }
  
  public void setYearRetreated(Integer value)
  {
    if(value == null)
    {
      setValue(YEARRETREATED, "");
    }
    else
    {
      setValue(YEARRETREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isYearRetreatedWritable()
  {
    return isWritable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedReadable()
  {
    return isReadable(YEARRETREATED);
  }
  
  public boolean isYearRetreatedModified()
  {
    return isModified(YEARRETREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getYearRetreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(YEARRETREATED).getAttributeMdDTO();
  }
  
  public static SurveyExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SurveyExcelViewDTO) dto;
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
