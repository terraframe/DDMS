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

@com.runwaysdk.business.ClassSignature(hash = 1720226931)
public abstract class UnknownGeoEntityDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.UnknownGeoEntity";
  private static final long serialVersionUID = 1720226931;
  
  protected UnknownGeoEntityDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ENTITYNAME = "entityName";
  public static java.lang.String ENTITYTYPE = "entityType";
  public static java.lang.String ID = "id";
  public static java.lang.String KNOWNHIERARCHY = "knownHierarchy";
  public static java.lang.String SIBLINGS = "siblings";
  public static java.lang.String SYNONYMS = "synonyms";
  public String getEntityName()
  {
    return getValue(ENTITYNAME);
  }
  
  public void setEntityName(String value)
  {
    if(value == null)
    {
      setValue(ENTITYNAME, "");
    }
    else
    {
      setValue(ENTITYNAME, value);
    }
  }
  
  public boolean isEntityNameWritable()
  {
    return isWritable(ENTITYNAME);
  }
  
  public boolean isEntityNameReadable()
  {
    return isReadable(ENTITYNAME);
  }
  
  public boolean isEntityNameModified()
  {
    return isModified(ENTITYNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYNAME).getAttributeMdDTO();
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
  
  public String getKnownHierarchy()
  {
    return getValue(KNOWNHIERARCHY);
  }
  
  public void setKnownHierarchy(String value)
  {
    if(value == null)
    {
      setValue(KNOWNHIERARCHY, "");
    }
    else
    {
      setValue(KNOWNHIERARCHY, value);
    }
  }
  
  public boolean isKnownHierarchyWritable()
  {
    return isWritable(KNOWNHIERARCHY);
  }
  
  public boolean isKnownHierarchyReadable()
  {
    return isReadable(KNOWNHIERARCHY);
  }
  
  public boolean isKnownHierarchyModified()
  {
    return isModified(KNOWNHIERARCHY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getKnownHierarchyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(KNOWNHIERARCHY).getAttributeMdDTO();
  }
  
  public String getSiblings()
  {
    return getValue(SIBLINGS);
  }
  
  public void setSiblings(String value)
  {
    if(value == null)
    {
      setValue(SIBLINGS, "");
    }
    else
    {
      setValue(SIBLINGS, value);
    }
  }
  
  public boolean isSiblingsWritable()
  {
    return isWritable(SIBLINGS);
  }
  
  public boolean isSiblingsReadable()
  {
    return isReadable(SIBLINGS);
  }
  
  public boolean isSiblingsModified()
  {
    return isModified(SIBLINGS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSiblingsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SIBLINGS).getAttributeMdDTO();
  }
  
  public String getSynonyms()
  {
    return getValue(SYNONYMS);
  }
  
  public void setSynonyms(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMS, "");
    }
    else
    {
      setValue(SYNONYMS, value);
    }
  }
  
  public boolean isSynonymsWritable()
  {
    return isWritable(SYNONYMS);
  }
  
  public boolean isSynonymsReadable()
  {
    return isReadable(SYNONYMS);
  }
  
  public boolean isSynonymsModified()
  {
    return isModified(SYNONYMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSynonymsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SYNONYMS).getAttributeMdDTO();
  }
  
  public static UnknownGeoEntityDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (UnknownGeoEntityDTO) dto;
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
