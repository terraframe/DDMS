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

@com.runwaysdk.business.ClassSignature(hash = -994379831)
public abstract class SprayTeamDTOBase extends dss.vector.solutions.irs.TargeterDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeam";
  private static final long serialVersionUID = -994379831;
  
  protected SprayTeamDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SprayTeamDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
  public dss.vector.solutions.general.DiseaseDTO getDisease()
  {
    if(getValue(DISEASE) == null || getValue(DISEASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.general.DiseaseDTO.get(getRequest(), getValue(DISEASE));
    }
  }
  
  public String getDiseaseId()
  {
    return getValue(DISEASE);
  }
  
  public void setDisease(dss.vector.solutions.general.DiseaseDTO value)
  {
    if(value == null)
    {
      setValue(DISEASE, "");
    }
    else
    {
      setValue(DISEASE, value.getId());
    }
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.SprayZoneDTO getSprayZone()
  {
    if(getValue(SPRAYZONE) == null || getValue(SPRAYZONE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.SprayZoneDTO.get(getRequest(), getValue(SPRAYZONE));
    }
  }
  
  public String getSprayZoneId()
  {
    return getValue(SPRAYZONE);
  }
  
  public void setSprayZone(dss.vector.solutions.geo.generated.SprayZoneDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYZONE, "");
    }
    else
    {
      setValue(SPRAYZONE, value.getId());
    }
  }
  
  public boolean isSprayZoneWritable()
  {
    return isWritable(SPRAYZONE);
  }
  
  public boolean isSprayZoneReadable()
  {
    return isReadable(SPRAYZONE);
  }
  
  public boolean isSprayZoneModified()
  {
    return isModified(SPRAYZONE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSprayZoneMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYZONE).getAttributeMdDTO();
  }
  
  public String getTeamId()
  {
    return getValue(TEAMID);
  }
  
  public void setTeamId(String value)
  {
    if(value == null)
    {
      setValue(TEAMID, "");
    }
    else
    {
      setValue(TEAMID, value);
    }
  }
  
  public boolean isTeamIdWritable()
  {
    return isWritable(TEAMID);
  }
  
  public boolean isTeamIdReadable()
  {
    return isReadable(TEAMID);
  }
  
  public boolean isTeamIdModified()
  {
    return isModified(TEAMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTeamIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMID).getAttributeMdDTO();
  }
  
  public final void create(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{geoId, leaderId, operatorIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "create", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void create(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, geoId, leaderId, operatorIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "create", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void edit(java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{geoId, leaderId, operatorIds, removedIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "edit", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void edit(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String geoId, java.lang.String leaderId, java.lang.String[] operatorIds, java.lang.String[] removedIds)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, geoId, leaderId, operatorIds, removedIds};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "edit", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.SprayTeamDTO[] findByLocation(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "findByLocation", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamMemberViewDTO[] getOperatorViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getOperatorViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamMemberViewDTO[] getOperatorViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getOperatorViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamMemberViewDTO[] getTeamMemberViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMemberViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamMemberViewDTO[] getTeamMemberViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMemberViews", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.irs.TeamMemberDTO[] getTeamMembers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMembers", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TeamMemberDTO[] getTeamMembers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "getTeamMembers", _declaredTypes);
    return (dss.vector.solutions.irs.TeamMemberDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO> getAllSprayTeamMembers()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO> getAllSprayTeamMembers(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamMembersRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamMembersRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.InTeamDTO addSprayTeamMembers(dss.vector.solutions.irs.TeamMemberDTO child)
  {
    return (dss.vector.solutions.irs.InTeamDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InTeamDTO addSprayTeamMembers(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.TeamMemberDTO child)
  {
    return (dss.vector.solutions.irs.InTeamDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public void removeSprayTeamMembers(dss.vector.solutions.irs.InTeamDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeSprayTeamMembers(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.InTeamDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllSprayTeamMembers()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static void removeAllSprayTeamMembers(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO> getAllTeamLeader()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO> getAllTeamLeader(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.TeamMemberDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllTeamLeaderRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO> getAllTeamLeaderRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.LeadTeamDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.LeadTeamDTO addTeamLeader(dss.vector.solutions.irs.TeamMemberDTO child)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.LeadTeamDTO addTeamLeader(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.TeamMemberDTO child)
  {
    return (dss.vector.solutions.irs.LeadTeamDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public void removeTeamLeader(dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTeamLeader(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.LeadTeamDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTeamLeader()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static void removeAllTeamLeader(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.irs.LeadTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.SprayTeamDTO) dto;
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
  
  public static dss.vector.solutions.irs.SprayTeamQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.SprayTeamQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.irs.SprayTeamDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.SprayTeamDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
