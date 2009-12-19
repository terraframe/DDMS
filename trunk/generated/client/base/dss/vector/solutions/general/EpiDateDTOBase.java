package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = 535434521)
public abstract class EpiDateDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiDate";
  private static final long serialVersionUID = 535434521;
  
  protected EpiDateDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String EPIYEAR = "epiYear";
  public static java.lang.String ID = "id";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String STARTDATE = "startDate";
  public java.util.Date getEndDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
  }
  
  public Integer getEpiYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EPIYEAR));
  }
  
  public void setEpiYear(Integer value)
  {
    if(value == null)
    {
      setValue(EPIYEAR, "");
    }
    else
    {
      setValue(EPIYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEpiYearWritable()
  {
    return isWritable(EPIYEAR);
  }
  
  public boolean isEpiYearReadable()
  {
    return isReadable(EPIYEAR);
  }
  
  public boolean isEpiYearModified()
  {
    return isModified(EPIYEAR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getEpiYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EPIYEAR).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.surveillance.PeriodType", getEnumNames(PERIODTYPE));
  }
  
  public java.util.List<String> getPeriodTypeEnumNames()
  {
    return getEnumNames(PERIODTYPE);
  }
  
  public void addPeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    addEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void removePeriodType(dss.vector.solutions.surveillance.PeriodTypeDTO enumDTO)
  {
    removeEnumItem(PERIODTYPE, enumDTO.toString());
  }
  
  public void clearPeriodType()
  {
    clearEnum(PERIODTYPE);
  }
  
  public boolean isPeriodTypeWritable()
  {
    return isWritable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeReadable()
  {
    return isReadable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeModified()
  {
    return isModified(PERIODTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getPeriodTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.EpiDateDTO getInstanceByDate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{startDate, endDate};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getInstanceByDate", _declaredTypes);
    return (dss.vector.solutions.general.EpiDateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.EpiDateDTO getInstanceByPeriod(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.surveillance.PeriodTypeDTO periodType, java.lang.Integer period, java.lang.Integer epiYear)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.surveillance.PeriodType", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{periodType, period, epiYear};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getInstanceByPeriod", _declaredTypes);
    return (dss.vector.solutions.general.EpiDateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.Integer getNumberOfEpiWeeks()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getNumberOfEpiWeeks", _declaredTypes);
    return (java.lang.Integer) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Integer getNumberOfEpiWeeks(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getNumberOfEpiWeeks", _declaredTypes);
    return (java.lang.Integer) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToEpiWeek(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToEpiWeek", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToMonth(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToMonth", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToQuarter(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToQuarter", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToSeason(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToSeason", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToYear(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToYear", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static EpiDateDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (EpiDateDTO) dto;
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
