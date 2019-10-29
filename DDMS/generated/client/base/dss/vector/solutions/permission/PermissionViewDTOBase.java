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

@com.runwaysdk.business.ClassSignature(hash = 2054093197)
public abstract class PermissionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.permission.PermissionView";
  private static final long serialVersionUID = 2054093197;
  
  protected PermissionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String URLID = "urlId";
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.permission.PermissionOptionDTO> getPermission()
  {
    return (java.util.List<dss.vector.solutions.permission.PermissionOptionDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.permission.PermissionOptionDTO.CLASS, getEnumNames(PERMISSION));
  }
  
  public java.util.List<String> getPermissionEnumNames()
  {
    return getEnumNames(PERMISSION);
  }
  
  public void addPermission(dss.vector.solutions.permission.PermissionOptionDTO enumDTO)
  {
    addEnumItem(PERMISSION, enumDTO.toString());
  }
  
  public void removePermission(dss.vector.solutions.permission.PermissionOptionDTO enumDTO)
  {
    removeEnumItem(PERMISSION, enumDTO.toString());
  }
  
  public void clearPermission()
  {
    clearEnum(PERMISSION);
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
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getPermissionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(PERMISSION).getAttributeMdDTO();
  }
  
  public String getUrlId()
  {
    return getValue(URLID);
  }
  
  public void setUrlId(String value)
  {
    if(value == null)
    {
      setValue(URLID, "");
    }
    else
    {
      setValue(URLID, value);
    }
  }
  
  public boolean isUrlIdWritable()
  {
    return isWritable(URLID);
  }
  
  public boolean isUrlIdReadable()
  {
    return isReadable(URLID);
  }
  
  public boolean isUrlIdModified()
  {
    return isModified(URLID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUrlIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(URLID).getAttributeMdDTO();
  }
  
  public static PermissionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PermissionViewDTO) dto;
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
