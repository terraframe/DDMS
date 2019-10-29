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
package dss.vector.solutions.permission;

@com.runwaysdk.business.ClassSignature(hash = 908279638)
public abstract class UniversalPermissionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.permission.UniversalPermissionView";
  private static final long serialVersionUID = 908279638;
  
  protected UniversalPermissionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LABEL = "label";
  public static java.lang.String PERMISSION = "permission";
  public static java.lang.String UNIVERSALID = "universalId";
  public String getLabel()
  {
    return getValue(LABEL);
  }
  
  public void setLabel(String value)
  {
    if(value == null)
    {
      setValue(LABEL, "");
    }
    else
    {
      setValue(LABEL, value);
    }
  }
  
  public boolean isLabelWritable()
  {
    return isWritable(LABEL);
  }
  
  public boolean isLabelReadable()
  {
    return isReadable(LABEL);
  }
  
  public boolean isLabelModified()
  {
    return isModified(LABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LABEL).getAttributeMdDTO();
  }
  
  public Boolean getPermission()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PERMISSION));
  }
  
  public void setPermission(Boolean value)
  {
    if(value == null)
    {
      setValue(PERMISSION, "");
    }
    else
    {
      setValue(PERMISSION, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPermissionWritable()
  {
    return isWritable(PERMISSION);
  }
  
  public boolean isPermissionReadable()
  {
    return isReadable(PERMISSION);
  }
  
  public boolean isPermissionModified()
  {
    return isModified(PERMISSION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPermissionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(PERMISSION).getAttributeMdDTO();
  }
  
  public String getUniversalId()
  {
    return getValue(UNIVERSALID);
  }
  
  public void setUniversalId(String value)
  {
    if(value == null)
    {
      setValue(UNIVERSALID, "");
    }
    else
    {
      setValue(UNIVERSALID, value);
    }
  }
  
  public boolean isUniversalIdWritable()
  {
    return isWritable(UNIVERSALID);
  }
  
  public boolean isUniversalIdReadable()
  {
    return isReadable(UNIVERSALID);
  }
  
  public boolean isUniversalIdModified()
  {
    return isModified(UNIVERSALID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniversalIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIVERSALID).getAttributeMdDTO();
  }
  
  public static UniversalPermissionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (UniversalPermissionViewDTO) dto;
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
