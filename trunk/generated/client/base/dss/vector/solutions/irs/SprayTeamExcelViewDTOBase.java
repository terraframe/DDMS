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

@com.runwaysdk.business.ClassSignature(hash = 1472226673)
public abstract class SprayTeamExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayTeamExcelView";
  private static final long serialVersionUID = 1472226673;
  
  protected SprayTeamExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String SPRAYZONE = "sprayZone";
  public static java.lang.String TEAMID = "teamId";
  public static java.lang.String TEAMLEADERID = "teamLeaderId";
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public boolean isOperatorIdWritable()
  {
    return isWritable(OPERATORID);
  }
  
  public boolean isOperatorIdReadable()
  {
    return isReadable(OPERATORID);
  }
  
  public boolean isOperatorIdModified()
  {
    return isModified(OPERATORID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
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
  
  public static SprayTeamExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SprayTeamExcelViewDTO) dto;
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
