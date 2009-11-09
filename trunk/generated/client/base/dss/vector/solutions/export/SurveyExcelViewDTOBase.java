package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 1102926849)
public abstract class SurveyExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.SurveyExcelView";
  private static final long serialVersionUID = 1102926849;
  
  protected SurveyExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ANAEMIATREATMENT = "anaemiaTreatment";
  public static java.lang.String BLOODSLIDE = "bloodslide";
  public static java.lang.String DOB = "dob";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String FEVERTREATEMENT = "feverTreatement";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HASWINDOWS = "hasWindows";
  public static java.lang.String HOUSEHOLDNAME = "householdName";
  public static java.lang.String ID = "id";
  public static java.lang.String IRON = "iron";
  public static java.lang.String LASTSPRAYED = "lastSprayed";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIATREATMENT = "malariaTreatment";
  public static java.lang.String MALARIAEPOSITIVE = "malariaePositive";
  public static java.lang.String MIXEDPOSITIVE = "mixedPositive";
  public static java.lang.String NEGATIVE = "negative";
  public static java.lang.String NETS = "nets";
  public static java.lang.String NETSUSED = "netsUsed";
  public static java.lang.String NOTVALID = "notValid";
  public static java.lang.String OVALEPOSITIVE = "ovalePositive";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PEOPLE = "people";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PFPOSITIVE = "pfPositive";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String ROOFINFO = "roofInfo";
  public static java.lang.String ROOMS = "rooms";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public static java.lang.String SLEPTUNDERNETS = "sleptUnderNets";
  public static java.lang.String SURVEYDATE = "surveyDate";
  public static java.lang.String URBAN = "urban";
  public static java.lang.String VIVAXPOSITIVE = "vivaxPositive";
  public static java.lang.String WALLINFO = "wallInfo";
  public static java.lang.String WINDOWTYPE = "windowType";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAnaemiaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ANAEMIATREATMENT).getAttributeMdDTO();
  }
  
  public String getBloodslide()
  {
    return getValue(BLOODSLIDE);
  }
  
  public void setBloodslide(String value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDE, "");
    }
    else
    {
      setValue(BLOODSLIDE, value);
    }
  }
  
  public boolean isBloodslideWritable()
  {
    return isWritable(BLOODSLIDE);
  }
  
  public boolean isBloodslideReadable()
  {
    return isReadable(BLOODSLIDE);
  }
  
  public boolean isBloodslideModified()
  {
    return isModified(BLOODSLIDE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBloodslideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BLOODSLIDE).getAttributeMdDTO();
  }
  
  public java.util.Date getDob()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DOB));
  }
  
  public void setDob(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DOB, "");
    }
    else
    {
      setValue(DOB, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getDobMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DOB).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFeverMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FEVER).getAttributeMdDTO();
  }
  
  public String getFeverTreatement()
  {
    return getValue(FEVERTREATEMENT);
  }
  
  public void setFeverTreatement(String value)
  {
    if(value == null)
    {
      setValue(FEVERTREATEMENT, "");
    }
    else
    {
      setValue(FEVERTREATEMENT, value);
    }
  }
  
  public boolean isFeverTreatementWritable()
  {
    return isWritable(FEVERTREATEMENT);
  }
  
  public boolean isFeverTreatementReadable()
  {
    return isReadable(FEVERTREATEMENT);
  }
  
  public boolean isFeverTreatementModified()
  {
    return isModified(FEVERTREATEMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFeverTreatementMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FEVERTREATEMENT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getHaemoglobin()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(HAEMOGLOBIN));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getHaemoglobinMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HAEMOGLOBIN).getAttributeMdDTO();
  }
  
  public Boolean getHaemoglobinMeasured()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HAEMOGLOBINMEASURED));
  }
  
  public void setHaemoglobinMeasured(Boolean value)
  {
    if(value == null)
    {
      setValue(HAEMOGLOBINMEASURED, "");
    }
    else
    {
      setValue(HAEMOGLOBINMEASURED, java.lang.Boolean.toString(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getHaemoglobinMeasuredMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HAEMOGLOBINMEASURED).getAttributeMdDTO();
  }
  
  public Boolean getHasWindows()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HASWINDOWS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getHasWindowsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(HASWINDOWS).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getHouseholdNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HOUSEHOLDNAME).getAttributeMdDTO();
  }
  
  public Boolean getIron()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(IRON));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIronMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(IRON).getAttributeMdDTO();
  }
  
  public Integer getLastSprayed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTSPRAYED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getLastSprayedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LASTSPRAYED).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMalariaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MALARIA).getAttributeMdDTO();
  }
  
  public String getMalariaTreatment()
  {
    return getValue(MALARIATREATMENT);
  }
  
  public void setMalariaTreatment(String value)
  {
    if(value == null)
    {
      setValue(MALARIATREATMENT, "");
    }
    else
    {
      setValue(MALARIATREATMENT, value);
    }
  }
  
  public boolean isMalariaTreatmentWritable()
  {
    return isWritable(MALARIATREATMENT);
  }
  
  public boolean isMalariaTreatmentReadable()
  {
    return isReadable(MALARIATREATMENT);
  }
  
  public boolean isMalariaTreatmentModified()
  {
    return isModified(MALARIATREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMalariaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MALARIATREATMENT).getAttributeMdDTO();
  }
  
  public Boolean getMalariaePositive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(MALARIAEPOSITIVE));
  }
  
  public void setMalariaePositive(Boolean value)
  {
    if(value == null)
    {
      setValue(MALARIAEPOSITIVE, "");
    }
    else
    {
      setValue(MALARIAEPOSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isMalariaePositiveWritable()
  {
    return isWritable(MALARIAEPOSITIVE);
  }
  
  public boolean isMalariaePositiveReadable()
  {
    return isReadable(MALARIAEPOSITIVE);
  }
  
  public boolean isMalariaePositiveModified()
  {
    return isModified(MALARIAEPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getMalariaePositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(MALARIAEPOSITIVE).getAttributeMdDTO();
  }
  
  public Boolean getMixedPositive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(MIXEDPOSITIVE));
  }
  
  public void setMixedPositive(Boolean value)
  {
    if(value == null)
    {
      setValue(MIXEDPOSITIVE, "");
    }
    else
    {
      setValue(MIXEDPOSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isMixedPositiveWritable()
  {
    return isWritable(MIXEDPOSITIVE);
  }
  
  public boolean isMixedPositiveReadable()
  {
    return isReadable(MIXEDPOSITIVE);
  }
  
  public boolean isMixedPositiveModified()
  {
    return isModified(MIXEDPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getMixedPositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(MIXEDPOSITIVE).getAttributeMdDTO();
  }
  
  public Boolean getNegative()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NEGATIVE));
  }
  
  public void setNegative(Boolean value)
  {
    if(value == null)
    {
      setValue(NEGATIVE, "");
    }
    else
    {
      setValue(NEGATIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNegativeWritable()
  {
    return isWritable(NEGATIVE);
  }
  
  public boolean isNegativeReadable()
  {
    return isReadable(NEGATIVE);
  }
  
  public boolean isNegativeModified()
  {
    return isModified(NEGATIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNegativeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NEGATIVE).getAttributeMdDTO();
  }
  
  public Integer getNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETS).getAttributeMdDTO();
  }
  
  public Integer getNetsUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETSUSED));
  }
  
  public void setNetsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(NETSUSED, "");
    }
    else
    {
      setValue(NETSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNetsUsedWritable()
  {
    return isWritable(NETSUSED);
  }
  
  public boolean isNetsUsedReadable()
  {
    return isReadable(NETSUSED);
  }
  
  public boolean isNetsUsedModified()
  {
    return isModified(NETSUSED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNetsUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETSUSED).getAttributeMdDTO();
  }
  
  public Boolean getNotValid()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NOTVALID));
  }
  
  public void setNotValid(Boolean value)
  {
    if(value == null)
    {
      setValue(NOTVALID, "");
    }
    else
    {
      setValue(NOTVALID, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNotValidWritable()
  {
    return isWritable(NOTVALID);
  }
  
  public boolean isNotValidReadable()
  {
    return isReadable(NOTVALID);
  }
  
  public boolean isNotValidModified()
  {
    return isModified(NOTVALID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getNotValidMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NOTVALID).getAttributeMdDTO();
  }
  
  public Boolean getOvalePositive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(OVALEPOSITIVE));
  }
  
  public void setOvalePositive(Boolean value)
  {
    if(value == null)
    {
      setValue(OVALEPOSITIVE, "");
    }
    else
    {
      setValue(OVALEPOSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isOvalePositiveWritable()
  {
    return isWritable(OVALEPOSITIVE);
  }
  
  public boolean isOvalePositiveReadable()
  {
    return isReadable(OVALEPOSITIVE);
  }
  
  public boolean isOvalePositiveModified()
  {
    return isModified(OVALEPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getOvalePositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(OVALEPOSITIVE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPaymentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PAYMENT).getAttributeMdDTO();
  }
  
  public Integer getPeople()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PEOPLE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeopleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PEOPLE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPerformedRDTMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERFORMEDRDT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPersonIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONID).getAttributeMdDTO();
  }
  
  public Boolean getPfPositive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PFPOSITIVE));
  }
  
  public void setPfPositive(Boolean value)
  {
    if(value == null)
    {
      setValue(PFPOSITIVE, "");
    }
    else
    {
      setValue(PFPOSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPfPositiveWritable()
  {
    return isWritable(PFPOSITIVE);
  }
  
  public boolean isPfPositiveReadable()
  {
    return isReadable(PFPOSITIVE);
  }
  
  public boolean isPfPositiveModified()
  {
    return isModified(PFPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPfPositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PFPOSITIVE).getAttributeMdDTO();
  }
  
  public Boolean getPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PREGNANT));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PREGNANT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRdtTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RDTTREATMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getRoof()
  {
    if(getValue(ROOF) == null || getValue(ROOF).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ROOF));
    }
  }
  
  public void setRoof(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ROOF, "");
    }
    else
    {
      setValue(ROOF, value.getId());
    }
  }
  
  public boolean isRoofWritable()
  {
    return isWritable(ROOF);
  }
  
  public boolean isRoofReadable()
  {
    return isReadable(ROOF);
  }
  
  public boolean isRoofModified()
  {
    return isModified(ROOF);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRoofMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOF).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRoofInfoMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOFINFO).getAttributeMdDTO();
  }
  
  public Integer getRooms()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(ROOMS));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRoomsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(ROOMS).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public Boolean getSleptUnderNet()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SLEPTUNDERNET));
  }
  
  public void setSleptUnderNet(Boolean value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNET, "");
    }
    else
    {
      setValue(SLEPTUNDERNET, java.lang.Boolean.toString(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSleptUnderNetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SLEPTUNDERNET).getAttributeMdDTO();
  }
  
  public Integer getSleptUnderNets()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SLEPTUNDERNETS));
  }
  
  public void setSleptUnderNets(Integer value)
  {
    if(value == null)
    {
      setValue(SLEPTUNDERNETS, "");
    }
    else
    {
      setValue(SLEPTUNDERNETS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSleptUnderNetsWritable()
  {
    return isWritable(SLEPTUNDERNETS);
  }
  
  public boolean isSleptUnderNetsReadable()
  {
    return isReadable(SLEPTUNDERNETS);
  }
  
  public boolean isSleptUnderNetsModified()
  {
    return isModified(SLEPTUNDERNETS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSleptUnderNetsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SLEPTUNDERNETS).getAttributeMdDTO();
  }
  
  public java.util.Date getSurveyDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SURVEYDATE));
  }
  
  public void setSurveyDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SURVEYDATE, "");
    }
    else
    {
      setValue(SURVEYDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getSurveyDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SURVEYDATE).getAttributeMdDTO();
  }
  
  public Boolean getUrban()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getUrbanMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(URBAN).getAttributeMdDTO();
  }
  
  public Boolean getVivaxPositive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIVAXPOSITIVE));
  }
  
  public void setVivaxPositive(Boolean value)
  {
    if(value == null)
    {
      setValue(VIVAXPOSITIVE, "");
    }
    else
    {
      setValue(VIVAXPOSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isVivaxPositiveWritable()
  {
    return isWritable(VIVAXPOSITIVE);
  }
  
  public boolean isVivaxPositiveReadable()
  {
    return isReadable(VIVAXPOSITIVE);
  }
  
  public boolean isVivaxPositiveModified()
  {
    return isModified(VIVAXPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getVivaxPositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VIVAXPOSITIVE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getWallInfoMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WALLINFO).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getWindowTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WINDOWTYPE).getAttributeMdDTO();
  }
  
  public static SurveyExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
