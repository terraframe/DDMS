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
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1976729967)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AttributeGeoHierarchy.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class AttributeGeoHierarchyBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.AttributeGeoHierarchy";
  public static java.lang.String ATTRIBUTEDISPLAYLABEL = "attributeDisplayLabel";
  public static java.lang.String GEOHIERARCHYDISPLAYLABEL = "geoHierarchyDisplayLabel";
  public static java.lang.String GEOHIERARCHYID = "geoHierarchyId";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  private static final long serialVersionUID = -1976729967;
  
  public AttributeGeoHierarchyBase()
  {
    super();
  }
  
  public String getAttributeDisplayLabel()
  {
    return getValue(ATTRIBUTEDISPLAYLABEL);
  }
  
  public void validateAttributeDisplayLabel()
  {
    this.validateAttribute(ATTRIBUTEDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getAttributeDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.AttributeGeoHierarchy.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ATTRIBUTEDISPLAYLABEL);
  }
  
  public void setAttributeDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTEDISPLAYLABEL, "");
    }
    else
    {
      setValue(ATTRIBUTEDISPLAYLABEL, value);
    }
  }
  
  public String getGeoHierarchyDisplayLabel()
  {
    return getValue(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public void validateGeoHierarchyDisplayLabel()
  {
    this.validateAttribute(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoHierarchyDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.AttributeGeoHierarchy.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOHIERARCHYDISPLAYLABEL);
  }
  
  public void setGeoHierarchyDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHYDISPLAYLABEL, "");
    }
    else
    {
      setValue(GEOHIERARCHYDISPLAYLABEL, value);
    }
  }
  
  public String getGeoHierarchyId()
  {
    return getValue(GEOHIERARCHYID);
  }
  
  public void validateGeoHierarchyId()
  {
    this.validateAttribute(GEOHIERARCHYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoHierarchyIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.AttributeGeoHierarchy.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOHIERARCHYID);
  }
  
  public void setGeoHierarchyId(String value)
  {
    if(value == null)
    {
      setValue(GEOHIERARCHYID, "");
    }
    else
    {
      setValue(GEOHIERARCHYID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.AttributeGeoHierarchy.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTEID);
  }
  
  public void validateMdAttributeId()
  {
    this.validateAttribute(MDATTRIBUTEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getMdAttributeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.AttributeGeoHierarchy.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(MDATTRIBUTEID);
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
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AttributeGeoHierarchy get(String id)
  {
    return (AttributeGeoHierarchy) com.runwaysdk.business.View.get(id);
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
