package dss.vector.solutions.intervention.monitor;

@com.terraframe.mojo.business.ClassSignature(hash = 311531194)
public abstract class PersonViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.PersonView";
  private static final long serialVersionUID = 311531194;
  
  protected PersonViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGE = "age";
  public static java.lang.String ANAEMIATREATMENT = "anaemiaTreatment";
  public static java.lang.String BLOODSLIDE = "bloodslide";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DOB = "dob";
  public static java.lang.String FEVER = "fever";
  public static java.lang.String FEVERTREATMENT = "feverTreatment";
  public static java.lang.String HAEMOGLOBIN = "haemoglobin";
  public static java.lang.String HAEMOGLOBINMEASURED = "haemoglobinMeasured";
  public static java.lang.String HOUSEHOLD = "household";
  public static java.lang.String ID = "id";
  public static java.lang.String IRON = "iron";
  public static java.lang.String MALARIA = "malaria";
  public static java.lang.String MALARIATREATMENT = "malariaTreatment";
  public static java.lang.String PAYMENT = "payment";
  public static java.lang.String PERFORMEDRDT = "performedRDT";
  public static java.lang.String PERSONID = "personId";
  public static java.lang.String PREGNANT = "pregnant";
  public static java.lang.String RDTRESULT = "rDTResult";
  public static java.lang.String RDTTREATMENT = "rdtTreatment";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SLEPTUNDERNET = "sleptUnderNet";
  public Integer getAge()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AGE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getAgeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AGE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAnaemiaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ANAEMIATREATMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getBloodslide()
  {
    if(getValue(BLOODSLIDE) == null || getValue(BLOODSLIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(BLOODSLIDE));
    }
  }
  
  public void setBloodslide(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(BLOODSLIDE, "");
    }
    else
    {
      setValue(BLOODSLIDE, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBloodslideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BLOODSLIDE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getFever()
  {
    if(getValue(FEVER) == null || getValue(FEVER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FEVER));
    }
  }
  
  public void setFever(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FEVER, "");
    }
    else
    {
      setValue(FEVER, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFeverMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FEVER).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getFeverTreatment()
  {
    if(getValue(FEVERTREATMENT) == null || getValue(FEVERTREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(FEVERTREATMENT));
    }
  }
  
  public void setFeverTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(FEVERTREATMENT, "");
    }
    else
    {
      setValue(FEVERTREATMENT, value.getId());
    }
  }
  
  public boolean isFeverTreatmentWritable()
  {
    return isWritable(FEVERTREATMENT);
  }
  
  public boolean isFeverTreatmentReadable()
  {
    return isReadable(FEVERTREATMENT);
  }
  
  public boolean isFeverTreatmentModified()
  {
    return isModified(FEVERTREATMENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getFeverTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(FEVERTREATMENT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getHouseholdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(HOUSEHOLD).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getMalaria()
  {
    if(getValue(MALARIA) == null || getValue(MALARIA).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIA));
    }
  }
  
  public void setMalaria(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIA, "");
    }
    else
    {
      setValue(MALARIA, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMalariaMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIA).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getMalariaTreatment()
  {
    if(getValue(MALARIATREATMENT) == null || getValue(MALARIATREATMENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(MALARIATREATMENT));
    }
  }
  
  public void setMalariaTreatment(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(MALARIATREATMENT, "");
    }
    else
    {
      setValue(MALARIATREATMENT, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMalariaTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(MALARIATREATMENT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPaymentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PAYMENT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPerformedRDT()
  {
    if(getValue(PERFORMEDRDT) == null || getValue(PERFORMEDRDT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PERFORMEDRDT));
    }
  }
  
  public void setPerformedRDT(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PERFORMEDRDT, "");
    }
    else
    {
      setValue(PERFORMEDRDT, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPerformedRDTMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERFORMEDRDT).getAttributeMdDTO();
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
  
  public Integer getRDTResult()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RDTRESULT));
  }
  
  public void setRDTResult(Integer value)
  {
    if(value == null)
    {
      setValue(RDTRESULT, "");
    }
    else
    {
      setValue(RDTRESULT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRDTResultWritable()
  {
    return isWritable(RDTRESULT);
  }
  
  public boolean isRDTResultReadable()
  {
    return isReadable(RDTRESULT);
  }
  
  public boolean isRDTResultModified()
  {
    return isModified(RDTRESULT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRDTResultMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RDTRESULT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRdtTreatmentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(RDTTREATMENT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public final void applyAll(dss.vector.solutions.ontology.TermDTO[] results)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{results};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.ontology.TermDTO[] results)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.ontology.Term;"};
    Object[] _parameters = new Object[]{id, results};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.TermDTO[] getRDTResults()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "getRDTResults", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.TermDTO[] getRDTResults(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.intervention.monitor.PersonViewDTO.CLASS, "getRDTResults", _declaredTypes);
    return (dss.vector.solutions.ontology.TermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static PersonViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (PersonViewDTO) dto;
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
