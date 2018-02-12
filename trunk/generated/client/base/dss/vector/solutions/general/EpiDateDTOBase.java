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
package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 703010425)
public abstract class EpiDateDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiDate";
  private static final long serialVersionUID = 703010425;
  
  protected EpiDateDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  
  public Integer getEpiYear()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EPIYEAR));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEpiYearMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EPIYEAR).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO> getPeriodType()
  {
    return (java.util.List<dss.vector.solutions.surveillance.PeriodTypeDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.surveillance.PeriodTypeDTO.CLASS, getEnumNames(PERIODTYPE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getPeriodTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
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
  
  public static final dss.vector.solutions.general.EpiDateDTO getInstanceByDate(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.util.Date endDate)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.util.Date"};
    Object[] _parameters = new Object[]{startDate, endDate};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getInstanceByDate", _declaredTypes);
    return (dss.vector.solutions.general.EpiDateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.EpiDateDTO getInstanceByPeriod(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.surveillance.PeriodTypeDTO periodType, java.lang.Integer period, java.lang.Integer epiYear)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.surveillance.PeriodType", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{periodType, period, epiYear};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getInstanceByPeriod", _declaredTypes);
    return (dss.vector.solutions.general.EpiDateDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.Integer getNumberOfEpiWeeks()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getNumberOfEpiWeeks", _declaredTypes);
    return (java.lang.Integer) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.Integer getNumberOfEpiWeeks(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "getNumberOfEpiWeeks", _declaredTypes);
    return (java.lang.Integer) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToEpiWeek(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToEpiWeek", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToEpiYear(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToEpiYear", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToMonth(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToMonth", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToQuarter(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToQuarter", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToSeason(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToSeason", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.util.Date snapToYear(com.runwaysdk.constants.ClientRequestIF clientRequest, java.util.Date startDate, java.lang.Boolean snapToFirstDay)
  {
    String[] _declaredTypes = new String[]{"java.util.Date", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{startDate, snapToFirstDay};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.EpiDateDTO.CLASS, "snapToYear", _declaredTypes);
    return (java.util.Date) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static EpiDateDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
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
