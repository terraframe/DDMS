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
package dss.vector.solutions.synchronization;

@com.runwaysdk.business.ClassSignature(hash = 994847385)
public abstract class SynchronizedTypeViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.SynchronizedTypeView";
  private static final long serialVersionUID = 994847385;
  
  protected SynchronizedTypeViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DESCRIPTION = "description";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String EXPORTED = "exported";
  public static java.lang.String ID = "id";
  public static java.lang.String MDTYPEID = "mdTypeId";
  public static java.lang.String QUALIFIEDTYPE = "qualifiedType";
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
  
  public Boolean getExported()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(EXPORTED));
  }
  
  public void setExported(Boolean value)
  {
    if(value == null)
    {
      setValue(EXPORTED, "");
    }
    else
    {
      setValue(EXPORTED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isExportedWritable()
  {
    return isWritable(EXPORTED);
  }
  
  public boolean isExportedReadable()
  {
    return isReadable(EXPORTED);
  }
  
  public boolean isExportedModified()
  {
    return isModified(EXPORTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getExportedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(EXPORTED).getAttributeMdDTO();
  }
  
  public String getMdTypeId()
  {
    return getValue(MDTYPEID);
  }
  
  public void setMdTypeId(String value)
  {
    if(value == null)
    {
      setValue(MDTYPEID, "");
    }
    else
    {
      setValue(MDTYPEID, value);
    }
  }
  
  public boolean isMdTypeIdWritable()
  {
    return isWritable(MDTYPEID);
  }
  
  public boolean isMdTypeIdReadable()
  {
    return isReadable(MDTYPEID);
  }
  
  public boolean isMdTypeIdModified()
  {
    return isModified(MDTYPEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdTypeIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDTYPEID).getAttributeMdDTO();
  }
  
  public String getQualifiedType()
  {
    return getValue(QUALIFIEDTYPE);
  }
  
  public void setQualifiedType(String value)
  {
    if(value == null)
    {
      setValue(QUALIFIEDTYPE, "");
    }
    else
    {
      setValue(QUALIFIEDTYPE, value);
    }
  }
  
  public boolean isQualifiedTypeWritable()
  {
    return isWritable(QUALIFIEDTYPE);
  }
  
  public boolean isQualifiedTypeReadable()
  {
    return isReadable(QUALIFIEDTYPE);
  }
  
  public boolean isQualifiedTypeModified()
  {
    return isModified(QUALIFIEDTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getQualifiedTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(QUALIFIEDTYPE).getAttributeMdDTO();
  }
  
  public static final void confirmAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.synchronization.SynchronizedTypeView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "confirmAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] getAll(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[] getDependencies(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] rootTypes)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{rootTypes};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getDependencies", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO getQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "getQuery", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO search(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String query)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{query};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.SynchronizedTypeViewDTO.CLASS, "search", _declaredTypes);
    return (dss.vector.solutions.synchronization.SynchronizedTypeViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SynchronizedTypeViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SynchronizedTypeViewDTO) dto;
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
