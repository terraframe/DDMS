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

@com.runwaysdk.business.ClassSignature(hash = -213598797)
public abstract class SprayTeamViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeamView";
  private static final long serialVersionUID = -213598797;
  
  protected SprayTeamViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSIGNEDOPERATORS = "assignedOperators";
  public static java.lang.String AVAILABLEOPERATORS = "availableOperators";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CURRENTOPERATORS = "currentOperators";
  public static java.lang.String ID = "id";
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
  public static java.lang.String TEAMLEADER = "teamLeader";
  public String getAssignedOperators()
  {
    return getValue(ASSIGNEDOPERATORS);
  }
  
  public void setAssignedOperators(String value)
  {
    if(value == null)
    {
      setValue(ASSIGNEDOPERATORS, "");
    }
    else
    {
      setValue(ASSIGNEDOPERATORS, value);
    }
  }
  
  public boolean isAssignedOperatorsWritable()
  {
    return isWritable(ASSIGNEDOPERATORS);
  }
  
  public boolean isAssignedOperatorsReadable()
  {
    return isReadable(ASSIGNEDOPERATORS);
  }
  
  public boolean isAssignedOperatorsModified()
  {
    return isModified(ASSIGNEDOPERATORS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAssignedOperatorsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ASSIGNEDOPERATORS).getAttributeMdDTO();
  }
  
  public String getAvailableOperators()
  {
    return getValue(AVAILABLEOPERATORS);
  }
  
  public void setAvailableOperators(String value)
  {
    if(value == null)
    {
      setValue(AVAILABLEOPERATORS, "");
    }
    else
    {
      setValue(AVAILABLEOPERATORS, value);
    }
  }
  
  public boolean isAvailableOperatorsWritable()
  {
    return isWritable(AVAILABLEOPERATORS);
  }
  
  public boolean isAvailableOperatorsReadable()
  {
    return isReadable(AVAILABLEOPERATORS);
  }
  
  public boolean isAvailableOperatorsModified()
  {
    return isModified(AVAILABLEOPERATORS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAvailableOperatorsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AVAILABLEOPERATORS).getAttributeMdDTO();
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
  
  public String getCurrentOperators()
  {
    return getValue(CURRENTOPERATORS);
  }
  
  public void setCurrentOperators(String value)
  {
    if(value == null)
    {
      setValue(CURRENTOPERATORS, "");
    }
    else
    {
      setValue(CURRENTOPERATORS, value);
    }
  }
  
  public boolean isCurrentOperatorsWritable()
  {
    return isWritable(CURRENTOPERATORS);
  }
  
  public boolean isCurrentOperatorsReadable()
  {
    return isReadable(CURRENTOPERATORS);
  }
  
  public boolean isCurrentOperatorsModified()
  {
    return isModified(CURRENTOPERATORS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCurrentOperatorsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CURRENTOPERATORS).getAttributeMdDTO();
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
  
  public String getTeamLeader()
  {
    return getValue(TEAMLEADER);
  }
  
  public void setTeamLeader(String value)
  {
    if(value == null)
    {
      setValue(TEAMLEADER, "");
    }
    else
    {
      setValue(TEAMLEADER, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTeamLeaderMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TEAMLEADER).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.SprayTeamViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.SprayTeamViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.irs.SprayTeamViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SprayTeamViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SprayTeamViewDTO) dto;
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
