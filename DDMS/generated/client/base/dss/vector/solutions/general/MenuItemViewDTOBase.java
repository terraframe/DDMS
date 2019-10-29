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

@com.runwaysdk.business.ClassSignature(hash = 125345263)
public abstract class MenuItemViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MenuItemView";
  private static final long serialVersionUID = 125345263;
  
  protected MenuItemViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ID = "id";
  public static java.lang.String MENUITEMID = "menuItemId";
  public static java.lang.String TERMDISPLAY = "termDisplay";
  public static java.lang.String URLDISPLAY = "urlDisplay";
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
  
  public String getMenuItemId()
  {
    return getValue(MENUITEMID);
  }
  
  public void setMenuItemId(String value)
  {
    if(value == null)
    {
      setValue(MENUITEMID, "");
    }
    else
    {
      setValue(MENUITEMID, value);
    }
  }
  
  public boolean isMenuItemIdWritable()
  {
    return isWritable(MENUITEMID);
  }
  
  public boolean isMenuItemIdReadable()
  {
    return isReadable(MENUITEMID);
  }
  
  public boolean isMenuItemIdModified()
  {
    return isModified(MENUITEMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMenuItemIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MENUITEMID).getAttributeMdDTO();
  }
  
  public String getTermDisplay()
  {
    return getValue(TERMDISPLAY);
  }
  
  public void setTermDisplay(String value)
  {
    if(value == null)
    {
      setValue(TERMDISPLAY, "");
    }
    else
    {
      setValue(TERMDISPLAY, value);
    }
  }
  
  public boolean isTermDisplayWritable()
  {
    return isWritable(TERMDISPLAY);
  }
  
  public boolean isTermDisplayReadable()
  {
    return isReadable(TERMDISPLAY);
  }
  
  public boolean isTermDisplayModified()
  {
    return isModified(TERMDISPLAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getTermDisplayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(TERMDISPLAY).getAttributeMdDTO();
  }
  
  public String getUrlDisplay()
  {
    return getValue(URLDISPLAY);
  }
  
  public void setUrlDisplay(String value)
  {
    if(value == null)
    {
      setValue(URLDISPLAY, "");
    }
    else
    {
      setValue(URLDISPLAY, value);
    }
  }
  
  public boolean isUrlDisplayWritable()
  {
    return isWritable(URLDISPLAY);
  }
  
  public boolean isUrlDisplayReadable()
  {
    return isReadable(URLDISPLAY);
  }
  
  public boolean isUrlDisplayModified()
  {
    return isModified(URLDISPLAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getUrlDisplayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(URLDISPLAY).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.MenuItemViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.MenuItemViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.general.MenuItemViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.general.MenuItemViewQueryDTO getViewsForDisease(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.MenuItemViewDTO.CLASS, "getViewsForDisease", _declaredTypes);
    return (dss.vector.solutions.general.MenuItemViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MenuItemViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MenuItemViewDTO) dto;
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
