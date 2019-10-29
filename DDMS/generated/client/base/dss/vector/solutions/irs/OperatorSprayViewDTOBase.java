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
package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1958747619)
public abstract class OperatorSprayViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorSprayView";
  private static final long serialVersionUID = -1958747619;
  
  protected OperatorSprayViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSIGNEDOPERATOR = "assignedOperator";
  public static java.lang.String BRAND = "brand";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String FLOATINGOPERATOR = "floatingOperator";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NOZZLESUSED = "nozzlesUsed";
  public static java.lang.String PUMPSUSED = "pumpsUsed";
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  public static java.lang.String SUPERVISOR = "supervisor";
  public static java.lang.String SURFACETYPE = "surfaceType";
  public static java.lang.String TARGET = "target";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public static java.lang.String TEAMOPERATOR = "teamOperator";
  public static java.lang.String USED = "used";
  public String getAssignedOperator()
  {
    return getValue(ASSIGNEDOPERATOR);
  }
  
  public void setAssignedOperator(String value)
  {
    if(value == null)
    {
      setValue(ASSIGNEDOPERATOR, "");
    }
    else
    {
      setValue(ASSIGNEDOPERATOR, value);
    }
  }
  
  public boolean isAssignedOperatorWritable()
  {
    return isWritable(ASSIGNEDOPERATOR);
  }
  
  public boolean isAssignedOperatorReadable()
  {
    return isReadable(ASSIGNEDOPERATOR);
  }
  
  public boolean isAssignedOperatorModified()
  {
    return isModified(ASSIGNEDOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAssignedOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ASSIGNEDOPERATOR).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.InsecticideBrandDTO getBrand()
  {
    if(getValue(BRAND) == null || getValue(BRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(BRAND));
    }
  }
  
  public String getBrandId()
  {
    return getValue(BRAND);
  }
  
  public void setBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(BRAND, "");
    }
    else
    {
      setValue(BRAND, value.getId());
    }
  }
  
  public boolean isBrandWritable()
  {
    return isWritable(BRAND);
  }
  
  public boolean isBrandReadable()
  {
    return isReadable(BRAND);
  }
  
  public boolean isBrandModified()
  {
    return isModified(BRAND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(BRAND).getAttributeMdDTO();
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
  
  public String getFloatingOperator()
  {
    return getValue(FLOATINGOPERATOR);
  }
  
  public void setFloatingOperator(String value)
  {
    if(value == null)
    {
      setValue(FLOATINGOPERATOR, "");
    }
    else
    {
      setValue(FLOATINGOPERATOR, value);
    }
  }
  
  public boolean isFloatingOperatorWritable()
  {
    return isWritable(FLOATINGOPERATOR);
  }
  
  public boolean isFloatingOperatorReadable()
  {
    return isReadable(FLOATINGOPERATOR);
  }
  
  public boolean isFloatingOperatorModified()
  {
    return isModified(FLOATINGOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFloatingOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FLOATINGOPERATOR).getAttributeMdDTO();
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
  
  public Integer getNozzlesUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NOZZLESUSED));
  }
  
  public void setNozzlesUsed(Integer value)
  {
    if(value == null)
    {
      setValue(NOZZLESUSED, "");
    }
    else
    {
      setValue(NOZZLESUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNozzlesUsedWritable()
  {
    return isWritable(NOZZLESUSED);
  }
  
  public boolean isNozzlesUsedReadable()
  {
    return isReadable(NOZZLESUSED);
  }
  
  public boolean isNozzlesUsedModified()
  {
    return isModified(NOZZLESUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNozzlesUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NOZZLESUSED).getAttributeMdDTO();
  }
  
  public Integer getPumpsUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUMPSUSED));
  }
  
  public void setPumpsUsed(Integer value)
  {
    if(value == null)
    {
      setValue(PUMPSUSED, "");
    }
    else
    {
      setValue(PUMPSUSED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPumpsUsedWritable()
  {
    return isWritable(PUMPSUSED);
  }
  
  public boolean isPumpsUsedReadable()
  {
    return isReadable(PUMPSUSED);
  }
  
  public boolean isPumpsUsedModified()
  {
    return isModified(PUMPSUSED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPumpsUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUMPSUSED).getAttributeMdDTO();
  }
  
  public Integer getReceived()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedWritable()
  {
    return isWritable(RECEIVED);
  }
  
  public boolean isReceivedReadable()
  {
    return isReadable(RECEIVED);
  }
  
  public boolean isReceivedModified()
  {
    return isModified(RECEIVED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReceivedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVED).getAttributeMdDTO();
  }
  
  public Integer getRefills()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefillsWritable()
  {
    return isWritable(REFILLS);
  }
  
  public boolean isRefillsReadable()
  {
    return isReadable(REFILLS);
  }
  
  public boolean isRefillsModified()
  {
    return isModified(REFILLS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getRefillsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFILLS).getAttributeMdDTO();
  }
  
  public Integer getReturned()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReturnedWritable()
  {
    return isWritable(RETURNED);
  }
  
  public boolean isReturnedReadable()
  {
    return isReadable(RETURNED);
  }
  
  public boolean isReturnedModified()
  {
    return isModified(RETURNED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getReturnedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RETURNED).getAttributeMdDTO();
  }
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isSprayDateWritable()
  {
    return isWritable(SPRAYDATE);
  }
  
  public boolean isSprayDateReadable()
  {
    return isReadable(SPRAYDATE);
  }
  
  public boolean isSprayDateModified()
  {
    return isModified(SPRAYDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getSprayDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(SPRAYDATE).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.SprayMethodDTO> getSprayMethod()
  {
    return (java.util.List<dss.vector.solutions.irs.SprayMethodDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.SprayMethodDTO.CLASS, getEnumNames(SPRAYMETHOD));
  }
  
  public java.util.List<String> getSprayMethodEnumNames()
  {
    return getEnumNames(SPRAYMETHOD);
  }
  
  public void addSprayMethod(dss.vector.solutions.irs.SprayMethodDTO enumDTO)
  {
    addEnumItem(SPRAYMETHOD, enumDTO.toString());
  }
  
  public void removeSprayMethod(dss.vector.solutions.irs.SprayMethodDTO enumDTO)
  {
    removeEnumItem(SPRAYMETHOD, enumDTO.toString());
  }
  
  public void clearSprayMethod()
  {
    clearEnum(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodWritable()
  {
    return isWritable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodReadable()
  {
    return isReadable(SPRAYMETHOD);
  }
  
  public boolean isSprayMethodModified()
  {
    return isModified(SPRAYMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getSprayMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(SPRAYMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getSprayOperator()
  {
    if(getValue(SPRAYOPERATOR) == null || getValue(SPRAYOPERATOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(SPRAYOPERATOR));
    }
  }
  
  public String getSprayOperatorId()
  {
    return getValue(SPRAYOPERATOR);
  }
  
  public void setSprayOperator(dss.vector.solutions.irs.TeamMemberDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATOR, "");
    }
    else
    {
      setValue(SPRAYOPERATOR, value.getId());
    }
  }
  
  public boolean isSprayOperatorWritable()
  {
    return isWritable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorReadable()
  {
    return isReadable(SPRAYOPERATOR);
  }
  
  public boolean isSprayOperatorModified()
  {
    return isModified(SPRAYOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYOPERATOR).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayTeamDTO getSprayTeam()
  {
    if(getValue(SPRAYTEAM) == null || getValue(SPRAYTEAM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayTeamDTO.get(getRequest(), getValue(SPRAYTEAM));
    }
  }
  
  public String getSprayTeamId()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void setSprayTeam(dss.vector.solutions.irs.SprayTeamDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value.getId());
    }
  }
  
  public boolean isSprayTeamWritable()
  {
    return isWritable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamReadable()
  {
    return isReadable(SPRAYTEAM);
  }
  
  public boolean isSprayTeamModified()
  {
    return isModified(SPRAYTEAM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayTeamMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYTEAM).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SupervisorDTO getSupervisor()
  {
    if(getValue(SUPERVISOR) == null || getValue(SUPERVISOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SupervisorDTO.get(getRequest(), getValue(SUPERVISOR));
    }
  }
  
  public String getSupervisorId()
  {
    return getValue(SUPERVISOR);
  }
  
  public void setSupervisor(dss.vector.solutions.irs.SupervisorDTO value)
  {
    if(value == null)
    {
      setValue(SUPERVISOR, "");
    }
    else
    {
      setValue(SUPERVISOR, value.getId());
    }
  }
  
  public boolean isSupervisorWritable()
  {
    return isWritable(SUPERVISOR);
  }
  
  public boolean isSupervisorReadable()
  {
    return isReadable(SUPERVISOR);
  }
  
  public boolean isSupervisorModified()
  {
    return isModified(SUPERVISOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSupervisorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SUPERVISOR).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSurfaceType()
  {
    if(getValue(SURFACETYPE) == null || getValue(SURFACETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SURFACETYPE));
    }
  }
  
  public String getSurfaceTypeId()
  {
    return getValue(SURFACETYPE);
  }
  
  public void setSurfaceType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SURFACETYPE, "");
    }
    else
    {
      setValue(SURFACETYPE, value.getId());
    }
  }
  
  public boolean isSurfaceTypeWritable()
  {
    return isWritable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeReadable()
  {
    return isReadable(SURFACETYPE);
  }
  
  public boolean isSurfaceTypeModified()
  {
    return isModified(SURFACETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSurfaceTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SURFACETYPE).getAttributeMdDTO();
  }
  
  public Integer getTarget()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TARGET));
  }
  
  public void setTarget(Integer value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, java.lang.Integer.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.TeamMemberDTO getTeamLeader()
  {
    if(getValue(TEAMLEADER) == null || getValue(TEAMLEADER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.TeamMemberDTO.get(getRequest(), getValue(TEAMLEADER));
    }
  }
  
  public String getTeamLeaderId()
  {
    return getValue(TEAMLEADER);
  }
  
  public void setTeamLeader(dss.vector.solutions.irs.TeamMemberDTO value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value.getId());
    }
  }
  
  public boolean isTeamLeaderWritable()
  {
    return isWritable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderReadable()
  {
    return isReadable(TEAMLEADER);
  }
  
  public boolean isTeamLeaderModified()
  {
    return isModified(TEAMLEADER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTeamLeaderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public String getTeamOperator()
  {
    return getValue(TEAMOPERATOR);
  }
  
  public void setTeamOperator(String value)
  {
    if(value == null)
    {
      setValue(TEAMOPERATOR, "");
    }
    else
    {
      setValue(TEAMOPERATOR, value);
    }
  }
  
  public boolean isTeamOperatorWritable()
  {
    return isWritable(TEAMOPERATOR);
  }
  
  public boolean isTeamOperatorReadable()
  {
    return isReadable(TEAMOPERATOR);
  }
  
  public boolean isTeamOperatorModified()
  {
    return isModified(TEAMOPERATOR);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTeamOperatorMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMOPERATOR).getAttributeMdDTO();
  }
  
  public Integer getUsed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedWritable()
  {
    return isWritable(USED);
  }
  
  public boolean isUsedReadable()
  {
    return isReadable(USED);
  }
  
  public boolean isUsedModified()
  {
    return isModified(USED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUsedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USED).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] getStatus()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[] getStatus(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "getStatus", _declaredTypes);
    return (dss.vector.solutions.irs.HouseholdSprayStatusViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayViewDTO searchBySprayData(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, java.util.Date sprayDate, dss.vector.solutions.irs.SprayMethodDTO sprayMethod, dss.vector.solutions.irs.InsecticideBrandDTO brand, java.lang.String operatorId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.util.Date", "dss.vector.solutions.irs.SprayMethod", "dss.vector.solutions.irs.InsecticideBrand", "java.lang.String"};
    Object[] _parameters = new Object[]{geoId, sprayDate, sprayMethod, brand, operatorId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.OperatorSprayViewDTO.CLASS, "searchBySprayData", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorSprayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static OperatorSprayViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorSprayViewDTO) dto;
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
