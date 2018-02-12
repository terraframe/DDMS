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

@com.runwaysdk.business.ClassSignature(hash = -1224227500)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeoSynonymArrayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeoSynonymArrayViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.GeoSynonymArrayView";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GEOENTITYNAME = "geoEntityName";
  public static java.lang.String GEOTYPEDISPLAYLABEL = "geoTypeDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMIDS = "synonymIds";
  public static java.lang.String SYNONYMNAMES = "synonymNames";
  private static final long serialVersionUID = -1224227500;
  
  public GeoSynonymArrayViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntity getGeoEntity()
  {
    if (getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntity.get(getValue(GEOENTITY));
    }
  }
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
  }
  
  public void validateGeoEntity()
  {
    this.validateAttribute(GEOENTITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getGeoEntityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(GEOENTITY);
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntity value)
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
  
  public String getGeoEntityName()
  {
    return getValue(GEOENTITYNAME);
  }
  
  public void validateGeoEntityName()
  {
    this.validateAttribute(GEOENTITYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoEntityNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOENTITYNAME);
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
  
  public String getGeoTypeDisplayLabel()
  {
    return getValue(GEOTYPEDISPLAYLABEL);
  }
  
  public void validateGeoTypeDisplayLabel()
  {
    this.validateAttribute(GEOTYPEDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoTypeDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOTYPEDISPLAYLABEL);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getSynonymIds()
  {
    return getValue(SYNONYMIDS);
  }
  
  public void validateSynonymIds()
  {
    this.validateAttribute(SYNONYMIDS);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSynonymIdsMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SYNONYMIDS);
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
  
  public String getSynonymNames()
  {
    return getValue(SYNONYMNAMES);
  }
  
  public void validateSynonymNames()
  {
    this.validateAttribute(SYNONYMNAMES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSynonymNamesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.GeoSynonymArrayView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SYNONYMNAMES);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeoSynonymArrayView get(String id)
  {
    return (GeoSynonymArrayView) com.runwaysdk.business.View.get(id);
  }
  
  public void applyWithSynonyms(dss.vector.solutions.geo.GeoSynonymView[] synonyms)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyWithSynonyms(java.lang.String id, dss.vector.solutions.geo.GeoSynonymView[] synonyms)
  {
    GeoSynonymArrayView _instance = GeoSynonymArrayView.get(id);
    _instance.applyWithSynonyms(synonyms);
  }
  
  public static dss.vector.solutions.geo.GeoSynonymArrayView getGeoSynonym(java.lang.String geoEntityId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static dss.vector.solutions.geo.GeoSynonymArrayViewQuery getMostRecent()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void lock()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void lock(java.lang.String id)
  {
    GeoSynonymArrayView _instance = GeoSynonymArrayView.get(id);
    _instance.lock();
  }
  
  public static dss.vector.solutions.geo.GeoSynonymArrayViewQuery searchByView(dss.vector.solutions.geo.GeoSynonymArrayView view, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void unlock()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.geo.GeoSynonymArrayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void unlock(java.lang.String id)
  {
    GeoSynonymArrayView _instance = GeoSynonymArrayView.get(id);
    _instance.unlock();
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
