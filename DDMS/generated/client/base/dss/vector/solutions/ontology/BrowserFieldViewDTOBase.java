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
package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -232861837)
public abstract class BrowserFieldViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserFieldView";
  private static final long serialVersionUID = -232861837;
  
  protected BrowserFieldViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String BROWSERFIELDID = "browserFieldId";
  public static java.lang.String DEFAULTVALUE = "defaultValue";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public static java.lang.String MDATTRIBUTELABEL = "mdAttributeLabel";
  public static java.lang.String MDCLASSID = "mdClassId";
  public static java.lang.String MDCLASSLABEL = "mdClassLabel";
  public String getBrowserFieldId()
  {
    return getValue(BROWSERFIELDID);
  }
  
  public void setBrowserFieldId(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELDID, "");
    }
    else
    {
      setValue(BROWSERFIELDID, value);
    }
  }
  
  public boolean isBrowserFieldIdWritable()
  {
    return isWritable(BROWSERFIELDID);
  }
  
  public boolean isBrowserFieldIdReadable()
  {
    return isReadable(BROWSERFIELDID);
  }
  
  public boolean isBrowserFieldIdModified()
  {
    return isModified(BROWSERFIELDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrowserFieldIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BROWSERFIELDID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getDefaultValue()
  {
    if(getValue(DEFAULTVALUE) == null || getValue(DEFAULTVALUE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(DEFAULTVALUE));
    }
  }
  
  public String getDefaultValueId()
  {
    return getValue(DEFAULTVALUE);
  }
  
  public void setDefaultValue(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTVALUE, "");
    }
    else
    {
      setValue(DEFAULTVALUE, value.getId());
    }
  }
  
  public boolean isDefaultValueWritable()
  {
    return isWritable(DEFAULTVALUE);
  }
  
  public boolean isDefaultValueReadable()
  {
    return isReadable(DEFAULTVALUE);
  }
  
  public boolean isDefaultValueModified()
  {
    return isModified(DEFAULTVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDefaultValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTVALUE).getAttributeMdDTO();
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTEID);
  }
  
  public void setMdAttributeId(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTEID, "");
    }
    else
    {
      setValue(MDATTRIBUTEID, value);
    }
  }
  
  public boolean isMdAttributeIdWritable()
  {
    return isWritable(MDATTRIBUTEID);
  }
  
  public boolean isMdAttributeIdReadable()
  {
    return isReadable(MDATTRIBUTEID);
  }
  
  public boolean isMdAttributeIdModified()
  {
    return isModified(MDATTRIBUTEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdAttributeIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDATTRIBUTEID).getAttributeMdDTO();
  }
  
  public String getMdAttributeLabel()
  {
    return getValue(MDATTRIBUTELABEL);
  }
  
  public void setMdAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTELABEL, "");
    }
    else
    {
      setValue(MDATTRIBUTELABEL, value);
    }
  }
  
  public boolean isMdAttributeLabelWritable()
  {
    return isWritable(MDATTRIBUTELABEL);
  }
  
  public boolean isMdAttributeLabelReadable()
  {
    return isReadable(MDATTRIBUTELABEL);
  }
  
  public boolean isMdAttributeLabelModified()
  {
    return isModified(MDATTRIBUTELABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdAttributeLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDATTRIBUTELABEL).getAttributeMdDTO();
  }
  
  public String getMdClassId()
  {
    return getValue(MDCLASSID);
  }
  
  public void setMdClassId(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSID, "");
    }
    else
    {
      setValue(MDCLASSID, value);
    }
  }
  
  public boolean isMdClassIdWritable()
  {
    return isWritable(MDCLASSID);
  }
  
  public boolean isMdClassIdReadable()
  {
    return isReadable(MDCLASSID);
  }
  
  public boolean isMdClassIdModified()
  {
    return isModified(MDCLASSID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdClassIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDCLASSID).getAttributeMdDTO();
  }
  
  public String getMdClassLabel()
  {
    return getValue(MDCLASSLABEL);
  }
  
  public void setMdClassLabel(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSLABEL, "");
    }
    else
    {
      setValue(MDCLASSLABEL, value);
    }
  }
  
  public boolean isMdClassLabelWritable()
  {
    return isWritable(MDCLASSLABEL);
  }
  
  public boolean isMdClassLabelReadable()
  {
    return isReadable(MDCLASSLABEL);
  }
  
  public boolean isMdClassLabelModified()
  {
    return isModified(MDCLASSLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdClassLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDCLASSLABEL).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.ontology.BrowserRootViewDTO[] getRoots()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.BrowserFieldViewDTO.CLASS, "getRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.BrowserRootViewDTO[] getRoots(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ontology.BrowserFieldViewDTO.CLASS, "getRoots", _declaredTypes);
    return (dss.vector.solutions.ontology.BrowserRootViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static BrowserFieldViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (BrowserFieldViewDTO) dto;
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
