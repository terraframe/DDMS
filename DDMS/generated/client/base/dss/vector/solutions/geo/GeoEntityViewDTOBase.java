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

@com.runwaysdk.business.ClassSignature(hash = -518359112)
public abstract class GeoEntityViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoEntityView";
  private static final long serialVersionUID = -518359112;
  
  protected GeoEntityViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVATED = "activated";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String GEOENTITYID = "geoEntityId";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  public static java.lang.String MOSUBTYPE = "moSubType";
  public static java.lang.String POLITICAL = "political";
  public static java.lang.String POPULATIONALLOWED = "populationAllowed";
  public static java.lang.String SPRAYTARGETALLOWED = "sprayTargetAllowed";
  public static java.lang.String TYPEDISPLAYLABEL = "typeDisplayLabel";
  public static java.lang.String URBAN = "urban";
  public Boolean getActivated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ACTIVATED));
  }
  
  public void setActivated(Boolean value)
  {
    if(value == null)
    {
      setValue(ACTIVATED, "");
    }
    else
    {
      setValue(ACTIVATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isActivatedWritable()
  {
    return isWritable(ACTIVATED);
  }
  
  public boolean isActivatedReadable()
  {
    return isReadable(ACTIVATED);
  }
  
  public boolean isActivatedModified()
  {
    return isModified(ACTIVATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getActivatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ACTIVATED).getAttributeMdDTO();
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public String getEntityType()
  {
    return getValue(ENTITYTYPE);
  }
  
  public void setEntityType(String value)
  {
    if(value == null)
    {
      setValue(ENTITYTYPE, "");
    }
    else
    {
      setValue(ENTITYTYPE, value);
    }
  }
  
  public boolean isEntityTypeWritable()
  {
    return isWritable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeReadable()
  {
    return isReadable(ENTITYTYPE);
  }
  
  public boolean isEntityTypeModified()
  {
    return isModified(ENTITYTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYTYPE).getAttributeMdDTO();
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITYID);
  }
  
  public void setGeoEntityId(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITYID, "");
    }
    else
    {
      setValue(GEOENTITYID, value);
    }
  }
  
  public boolean isGeoEntityIdWritable()
  {
    return isWritable(GEOENTITYID);
  }
  
  public boolean isGeoEntityIdReadable()
  {
    return isReadable(GEOENTITYID);
  }
  
  public boolean isGeoEntityIdModified()
  {
    return isModified(GEOENTITYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITYID).getAttributeMdDTO();
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
    }
  }
  
  public boolean isGeoIdWritable()
  {
    return isWritable(GEOID);
  }
  
  public boolean isGeoIdReadable()
  {
    return isReadable(GEOID);
  }
  
  public boolean isGeoIdModified()
  {
    return isModified(GEOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOID).getAttributeMdDTO();
  }
  
  public String getMoSubType()
  {
    return getValue(MOSUBTYPE);
  }
  
  public void setMoSubType(String value)
  {
    if(value == null)
    {
      setValue(MOSUBTYPE, "");
    }
    else
    {
      setValue(MOSUBTYPE, value);
    }
  }
  
  public boolean isMoSubTypeWritable()
  {
    return isWritable(MOSUBTYPE);
  }
  
  public boolean isMoSubTypeReadable()
  {
    return isReadable(MOSUBTYPE);
  }
  
  public boolean isMoSubTypeModified()
  {
    return isModified(MOSUBTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMoSubTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MOSUBTYPE).getAttributeMdDTO();
  }
  
  public Boolean getPolitical()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POLITICAL));
  }
  
  public void setPolitical(Boolean value)
  {
    if(value == null)
    {
      setValue(POLITICAL, "");
    }
    else
    {
      setValue(POLITICAL, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPoliticalWritable()
  {
    return isWritable(POLITICAL);
  }
  
  public boolean isPoliticalReadable()
  {
    return isReadable(POLITICAL);
  }
  
  public boolean isPoliticalModified()
  {
    return isModified(POLITICAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPoliticalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(POLITICAL).getAttributeMdDTO();
  }
  
  public Boolean getPopulationAllowed()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POPULATIONALLOWED));
  }
  
  public void setPopulationAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(POPULATIONALLOWED, "");
    }
    else
    {
      setValue(POPULATIONALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPopulationAllowedWritable()
  {
    return isWritable(POPULATIONALLOWED);
  }
  
  public boolean isPopulationAllowedReadable()
  {
    return isReadable(POPULATIONALLOWED);
  }
  
  public boolean isPopulationAllowedModified()
  {
    return isModified(POPULATIONALLOWED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getPopulationAllowedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(POPULATIONALLOWED).getAttributeMdDTO();
  }
  
  public Boolean getSprayTargetAllowed()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SPRAYTARGETALLOWED));
  }
  
  public void setSprayTargetAllowed(Boolean value)
  {
    if(value == null)
    {
      setValue(SPRAYTARGETALLOWED, "");
    }
    else
    {
      setValue(SPRAYTARGETALLOWED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSprayTargetAllowedWritable()
  {
    return isWritable(SPRAYTARGETALLOWED);
  }
  
  public boolean isSprayTargetAllowedReadable()
  {
    return isReadable(SPRAYTARGETALLOWED);
  }
  
  public boolean isSprayTargetAllowedModified()
  {
    return isModified(SPRAYTARGETALLOWED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getSprayTargetAllowedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SPRAYTARGETALLOWED).getAttributeMdDTO();
  }
  
  public String getTypeDisplayLabel()
  {
    return getValue(TYPEDISPLAYLABEL);
  }
  
  public void setTypeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(TYPEDISPLAYLABEL, "");
    }
    else
    {
      setValue(TYPEDISPLAYLABEL, value);
    }
  }
  
  public boolean isTypeDisplayLabelWritable()
  {
    return isWritable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelReadable()
  {
    return isReadable(TYPEDISPLAYLABEL);
  }
  
  public boolean isTypeDisplayLabelModified()
  {
    return isModified(TYPEDISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTypeDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TYPEDISPLAYLABEL).getAttributeMdDTO();
  }
  
  public Boolean getUrban()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(URBAN));
  }
  
  public void setUrban(Boolean value)
  {
    if(value == null)
    {
      setValue(URBAN, "");
    }
    else
    {
      setValue(URBAN, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isUrbanWritable()
  {
    return isWritable(URBAN);
  }
  
  public boolean isUrbanReadable()
  {
    return isReadable(URBAN);
  }
  
  public boolean isUrbanModified()
  {
    return isModified(URBAN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getUrbanMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(URBAN).getAttributeMdDTO();
  }
  
  public static GeoEntityViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (GeoEntityViewDTO) dto;
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
