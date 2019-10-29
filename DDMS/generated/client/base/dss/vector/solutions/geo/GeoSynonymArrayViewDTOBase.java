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
package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = 1337816020)
public abstract class GeoSynonymArrayViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoSynonymArrayView";
  private static final long serialVersionUID = 1337816020;
  
  protected GeoSynonymArrayViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GEOENTITYNAME = "geoEntityName";
  public static java.lang.String GEOTYPEDISPLAYLABEL = "geoTypeDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMIDS = "synonymIds";
  public static java.lang.String SYNONYMNAMES = "synonymNames";
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
  
  public String getGeoEntityName()
  {
    return getValue(GEOENTITYNAME);
  }
  
  public void setGeoEntityName(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITYNAME, "");
    }
    else
    {
      setValue(GEOENTITYNAME, value);
    }
  }
  
  public boolean isGeoEntityNameWritable()
  {
    return isWritable(GEOENTITYNAME);
  }
  
  public boolean isGeoEntityNameReadable()
  {
    return isReadable(GEOENTITYNAME);
  }
  
  public boolean isGeoEntityNameModified()
  {
    return isModified(GEOENTITYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITYNAME).getAttributeMdDTO();
  }
  
  public String getGeoTypeDisplayLabel()
  {
    return getValue(GEOTYPEDISPLAYLABEL);
  }
  
  public void setGeoTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(GEOTYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(GEOTYPEDISPLAYLABEL, value);
    }
  }
  
  public boolean isGeoTypeDisplayLabelWritable()
  {
    return isWritable(GEOTYPEDISPLAYLABEL);
  }
  
  public boolean isGeoTypeDisplayLabelReadable()
  {
    return isReadable(GEOTYPEDISPLAYLABEL);
  }
  
  public boolean isGeoTypeDisplayLabelModified()
  {
    return isModified(GEOTYPEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoTypeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOTYPEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getSynonymIds()
  {
    return getValue(SYNONYMIDS);
  }
  
  public void setSynonymIds(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMIDS, "");
    }
    else
    {
      setValue(SYNONYMIDS, value);
    }
  }
  
  public boolean isSynonymIdsWritable()
  {
    return isWritable(SYNONYMIDS);
  }
  
  public boolean isSynonymIdsReadable()
  {
    return isReadable(SYNONYMIDS);
  }
  
  public boolean isSynonymIdsModified()
  {
    return isModified(SYNONYMIDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymIdsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMIDS).getAttributeMdDTO();
  }
  
  public String getSynonymNames()
  {
    return getValue(SYNONYMNAMES);
  }
  
  public void setSynonymNames(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAMES, "");
    }
    else
    {
      setValue(SYNONYMNAMES, value);
    }
  }
  
  public boolean isSynonymNamesWritable()
  {
    return isWritable(SYNONYMNAMES);
  }
  
  public boolean isSynonymNamesReadable()
  {
    return isReadable(SYNONYMNAMES);
  }
  
  public boolean isSynonymNamesModified()
  {
    return isModified(SYNONYMNAMES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymNamesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMNAMES).getAttributeMdDTO();
  }
  
  public final void applyWithSynonyms(dss.vector.solutions.geo.GeoSynonymViewDTO[] synonyms)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.geo.GeoSynonymView;"};
    Object[] _parameters = new Object[]{synonyms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "applyWithSynonyms", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyWithSynonyms(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.geo.GeoSynonymViewDTO[] synonyms)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.geo.GeoSynonymView;"};
    Object[] _parameters = new Object[]{id, synonyms};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "applyWithSynonyms", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoSynonymArrayViewDTO getGeoSynonym(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoEntityId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoEntityId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "getGeoSynonym", _declaredTypes);
    return (dss.vector.solutions.geo.GeoSynonymArrayViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoSynonymArrayViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.geo.GeoSynonymArrayViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void lock()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "lock", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "lock", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.geo.GeoSynonymArrayViewQueryDTO searchByView(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.geo.GeoSynonymArrayViewDTO view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.geo.GeoSynonymArrayView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{view, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.geo.GeoSynonymArrayViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void unlock()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "unlock", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.geo.GeoSynonymArrayViewDTO.CLASS, "unlock", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static GeoSynonymArrayViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (GeoSynonymArrayViewDTO) dto;
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
