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
package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = -1361951334)
public abstract class MdFieldTypeDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.MdFieldType";
  private static final long serialVersionUID = -1361951334;
  
  protected MdFieldTypeDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String MDFIELDID = "mdFieldId";
  public static java.lang.String MDFIELDTYPE = "mdFieldType";
  public String getDescription()
  {
    return getValue(DESCRIPTION);
  }
  
  public void setDescription(String value)
  {
    if(value == null)
    {
      setValue(DESCRIPTION, "");
    }
    else
    {
      setValue(DESCRIPTION, value);
    }
  }
  
  public boolean isDescriptionWritable()
  {
    return isWritable(DESCRIPTION);
  }
  
  public boolean isDescriptionReadable()
  {
    return isReadable(DESCRIPTION);
  }
  
  public boolean isDescriptionModified()
  {
    return isModified(DESCRIPTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getDescriptionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(DESCRIPTION).getAttributeMdDTO();
  }
  
  public String getDisplayLabel()
  {
    return getValue(DISPLAYLABEL);
  }
  
  public void setDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(DISPLAYLABEL, "");
    }
    else
    {
      setValue(DISPLAYLABEL, value);
    }
  }
  
  public boolean isDisplayLabelWritable()
  {
    return isWritable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelReadable()
  {
    return isReadable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelModified()
  {
    return isModified(DISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getMdFieldId()
  {
    return getValue(MDFIELDID);
  }
  
  public void setMdFieldId(String value)
  {
    if(value == null)
    {
      setValue(MDFIELDID, "");
    }
    else
    {
      setValue(MDFIELDID, value);
    }
  }
  
  public boolean isMdFieldIdWritable()
  {
    return isWritable(MDFIELDID);
  }
  
  public boolean isMdFieldIdReadable()
  {
    return isReadable(MDFIELDID);
  }
  
  public boolean isMdFieldIdModified()
  {
    return isModified(MDFIELDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFieldIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFIELDID).getAttributeMdDTO();
  }
  
  public String getMdFieldType()
  {
    return getValue(MDFIELDTYPE);
  }
  
  public void setMdFieldType(String value)
  {
    if(value == null)
    {
      setValue(MDFIELDTYPE, "");
    }
    else
    {
      setValue(MDFIELDTYPE, value);
    }
  }
  
  public boolean isMdFieldTypeWritable()
  {
    return isWritable(MDFIELDTYPE);
  }
  
  public boolean isMdFieldTypeReadable()
  {
    return isReadable(MDFIELDTYPE);
  }
  
  public boolean isMdFieldTypeModified()
  {
    return isModified(MDFIELDTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFieldTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFIELDTYPE).getAttributeMdDTO();
  }
  
  public static MdFieldTypeDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MdFieldTypeDTO) dto;
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
