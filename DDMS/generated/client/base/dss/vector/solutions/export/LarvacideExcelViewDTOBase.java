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

@com.runwaysdk.business.ClassSignature(hash = 679936500)
public abstract class LarvacideExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.LarvacideExcelView";
  private static final long serialVersionUID = 679936500;
  
  protected LarvacideExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COMPLETIONDATE = "completionDate";
  public static java.lang.String CONTROLMETHOD = "controlMethod";
  public static java.lang.String GEODESCRIPTION = "geoDescription";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NATUREOFCONTROL = "natureOfControl";
  public static java.lang.String PERSONCOUNT = "personCount";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String SUBSTANCE = "substance";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADERID = "teamLeaderId";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String UNIT = "unit";
  public static java.lang.String UNITSUSED = "unitsUsed";
  public java.util.Date getCompletionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COMPLETIONDATE));
  }
  
  public void setCompletionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COMPLETIONDATE, "");
    }
    else
    {
      setValue(COMPLETIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCompletionDateWritable()
  {
    return isWritable(COMPLETIONDATE);
  }
  
  public boolean isCompletionDateReadable()
  {
    return isReadable(COMPLETIONDATE);
  }
  
  public boolean isCompletionDateModified()
  {
    return isModified(COMPLETIONDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCompletionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COMPLETIONDATE).getAttributeMdDTO();
  }
  
  public String getControlMethod()
  {
    return getValue(CONTROLMETHOD);
  }
  
  public void setControlMethod(String value)
  {
    if(value == null)
    {
      setValue(CONTROLMETHOD, "");
    }
    else
    {
      setValue(CONTROLMETHOD, value);
    }
  }
  
  public boolean isControlMethodWritable()
  {
    return isWritable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodReadable()
  {
    return isReadable(CONTROLMETHOD);
  }
  
  public boolean isControlMethodModified()
  {
    return isModified(CONTROLMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getControlMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTROLMETHOD).getAttributeMdDTO();
  }
  
  public String getGeoDescription()
  {
    return getValue(GEODESCRIPTION);
  }
  
  public void setGeoDescription(String value)
  {
    if(value == null)
    {
      setValue(GEODESCRIPTION, "");
    }
    else
    {
      setValue(GEODESCRIPTION, value);
    }
  }
  
  public boolean isGeoDescriptionWritable()
  {
    return isWritable(GEODESCRIPTION);
  }
  
  public boolean isGeoDescriptionReadable()
  {
    return isReadable(GEODESCRIPTION);
  }
  
  public boolean isGeoDescriptionModified()
  {
    return isModified(GEODESCRIPTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getGeoDescriptionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(GEODESCRIPTION).getAttributeMdDTO();
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
  
  public Boolean getNatureOfControl()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(NATUREOFCONTROL));
  }
  
  public void setNatureOfControl(Boolean value)
  {
    if(value == null)
    {
      setValue(NATUREOFCONTROL, "");
    }
    else
    {
      setValue(NATUREOFCONTROL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isNatureOfControlWritable()
  {
    return isWritable(NATUREOFCONTROL);
  }
  
  public boolean isNatureOfControlReadable()
  {
    return isReadable(NATUREOFCONTROL);
  }
  
  public boolean isNatureOfControlModified()
  {
    return isModified(NATUREOFCONTROL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getNatureOfControlMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(NATUREOFCONTROL).getAttributeMdDTO();
  }
  
  public Integer getPersonCount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERSONCOUNT));
  }
  
  public void setPersonCount(Integer value)
  {
    if(value == null)
    {
      setValue(PERSONCOUNT, "");
    }
    else
    {
      setValue(PERSONCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPersonCountWritable()
  {
    return isWritable(PERSONCOUNT);
  }
  
  public boolean isPersonCountReadable()
  {
    return isReadable(PERSONCOUNT);
  }
  
  public boolean isPersonCountModified()
  {
    return isModified(PERSONCOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPersonCountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERSONCOUNT).getAttributeMdDTO();
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
  
  public String getSubstance()
  {
    return getValue(SUBSTANCE);
  }
  
  public void setSubstance(String value)
  {
    if(value == null)
    {
      setValue(SUBSTANCE, "");
    }
    else
    {
      setValue(SUBSTANCE, value);
    }
  }
  
  public boolean isSubstanceWritable()
  {
    return isWritable(SUBSTANCE);
  }
  
  public boolean isSubstanceReadable()
  {
    return isReadable(SUBSTANCE);
  }
  
  public boolean isSubstanceModified()
  {
    return isModified(SUBSTANCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSubstanceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBSTANCE).getAttributeMdDTO();
  }
  
  public String getTarget()
  {
    return getValue(TARGET);
  }
  
  public void setTarget(String value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value);
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public String getTeamLeaderId()
  {
    return getValue(TEAMLEADERID);
  }
  
  public void setTeamLeaderId(String value)
  {
    if(value == null)
    {
      setValue(TEAMLEADERID, "");
    }
    else
    {
      setValue(TEAMLEADERID, value);
    }
  }
  
  public boolean isTeamLeaderIdWritable()
  {
    return isWritable(TEAMLEADERID);
  }
  
  public boolean isTeamLeaderIdReadable()
  {
    return isReadable(TEAMLEADERID);
  }
  
  public boolean isTeamLeaderIdModified()
  {
    return isModified(TEAMLEADERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTeamLeaderIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMLEADERID).getAttributeMdDTO();
  }
  
  public Integer getTreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTreatedWritable()
  {
    return isWritable(TREATED);
  }
  
  public boolean isTreatedReadable()
  {
    return isReadable(TREATED);
  }
  
  public boolean isTreatedModified()
  {
    return isModified(TREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TREATED).getAttributeMdDTO();
  }
  
  public String getUnit()
  {
    return getValue(UNIT);
  }
  
  public void setUnit(String value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value);
    }
  }
  
  public boolean isUnitWritable()
  {
    return isWritable(UNIT);
  }
  
  public boolean isUnitReadable()
  {
    return isReadable(UNIT);
  }
  
  public boolean isUnitModified()
  {
    return isModified(UNIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUnitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIT).getAttributeMdDTO();
  }
  
  public Integer getUnitsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSUSED));
  }
  
  public void setUnitsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSUSED, "");
    }
    else
    {
      setValue(UNITSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsUsedWritable()
  {
    return isWritable(UNITSUSED);
  }
  
  public boolean isUnitsUsedReadable()
  {
    return isReadable(UNITSUSED);
  }
  
  public boolean isUnitsUsedModified()
  {
    return isModified(UNITSUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnitsUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSUSED).getAttributeMdDTO();
  }
  
  public static LarvacideExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (LarvacideExcelViewDTO) dto;
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
