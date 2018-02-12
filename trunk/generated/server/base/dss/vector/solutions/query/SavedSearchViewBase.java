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

@com.runwaysdk.business.ClassSignature(hash = -565544880)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SavedSearchView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SavedSearchViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.SavedSearchView";
  public static java.lang.String ADDITIVESELECTABLES = "additiveSelectables";
  public static java.lang.String CONFIG = "config";
  public static java.lang.String DELETESELECTABLES = "deleteSelectables";
  public static java.lang.String ID = "id";
  public static java.lang.String ISMATERIALIZED = "isMaterialized";
  public static java.lang.String KALEIDOSCOPES = "kaleidoscopes";
  public static java.lang.String QUERYNAME = "queryName";
  public static java.lang.String QUERYTYPE = "queryType";
  public static java.lang.String QUERYXML = "queryXml";
  public static java.lang.String SAVEDQUERYID = "savedQueryId";
  private static final long serialVersionUID = -565544880;
  
  public SavedSearchViewBase()
  {
    super();
  }
  
  public String getAdditiveSelectables()
  {
    return getValue(ADDITIVESELECTABLES);
  }
  
  public void validateAdditiveSelectables()
  {
    this.validateAttribute(ADDITIVESELECTABLES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getAdditiveSelectablesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(ADDITIVESELECTABLES);
  }
  
  public void setAdditiveSelectables(String value)
  {
    if(value == null)
    {
      setValue(ADDITIVESELECTABLES, "");
    }
    else
    {
      setValue(ADDITIVESELECTABLES, value);
    }
  }
  
  public String getConfig()
  {
    return getValue(CONFIG);
  }
  
  public void validateConfig()
  {
    this.validateAttribute(CONFIG);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getConfigMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(CONFIG);
  }
  
  public void setConfig(String value)
  {
    if(value == null)
    {
      setValue(CONFIG, "");
    }
    else
    {
      setValue(CONFIG, value);
    }
  }
  
  public String getDeleteSelectables()
  {
    return getValue(DELETESELECTABLES);
  }
  
  public void validateDeleteSelectables()
  {
    this.validateAttribute(DELETESELECTABLES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getDeleteSelectablesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(DELETESELECTABLES);
  }
  
  public void setDeleteSelectables(String value)
  {
    if(value == null)
    {
      setValue(DELETESELECTABLES, "");
    }
    else
    {
      setValue(DELETESELECTABLES, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getIsMaterialized()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISMATERIALIZED));
  }
  
  public void validateIsMaterialized()
  {
    this.validateAttribute(ISMATERIALIZED);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getIsMaterializedMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(ISMATERIALIZED);
  }
  
  public void setIsMaterialized(Boolean value)
  {
    if(value == null)
    {
      setValue(ISMATERIALIZED, "");
    }
    else
    {
      setValue(ISMATERIALIZED, java.lang.Boolean.toString(value));
    }
  }
  
  public String getKaleidoscopes()
  {
    return getValue(KALEIDOSCOPES);
  }
  
  public void validateKaleidoscopes()
  {
    this.validateAttribute(KALEIDOSCOPES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeTextDAOIF getKaleidoscopesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeTextDAOIF)mdClassIF.definesAttribute(KALEIDOSCOPES);
  }
  
  public void setKaleidoscopes(String value)
  {
    if(value == null)
    {
      setValue(KALEIDOSCOPES, "");
    }
    else
    {
      setValue(KALEIDOSCOPES, value);
    }
  }
  
  public String getQueryName()
  {
    return getValue(QUERYNAME);
  }
  
  public void validateQueryName()
  {
    this.validateAttribute(QUERYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getQueryNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(QUERYNAME);
  }
  
  public void setQueryName(String value)
  {
    if(value == null)
    {
      setValue(QUERYNAME, "");
    }
    else
    {
      setValue(QUERYNAME, value);
    }
  }
  
  public String getQueryType()
  {
    return getValue(QUERYTYPE);
  }
  
  public void validateQueryType()
  {
    this.validateAttribute(QUERYTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getQueryTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(QUERYTYPE);
  }
  
  public void setQueryType(String value)
  {
    if(value == null)
    {
      setValue(QUERYTYPE, "");
    }
    else
    {
      setValue(QUERYTYPE, value);
    }
  }
  
  public String getQueryXml()
  {
    return getValue(QUERYXML);
  }
  
  public void validateQueryXml()
  {
    this.validateAttribute(QUERYXML);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getQueryXmlMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(QUERYXML);
  }
  
  public void setQueryXml(String value)
  {
    if(value == null)
    {
      setValue(QUERYXML, "");
    }
    else
    {
      setValue(QUERYXML, value);
    }
  }
  
  public String getSavedQueryId()
  {
    return getValue(SAVEDQUERYID);
  }
  
  public void validateSavedQueryId()
  {
    this.validateAttribute(SAVEDQUERYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSavedQueryIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.SavedSearchView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SAVEDQUERYID);
  }
  
  public void setSavedQueryId(String value)
  {
    if(value == null)
    {
      setValue(SAVEDQUERYID, "");
    }
    else
    {
      setValue(SAVEDQUERYID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SavedSearchView get(String id)
  {
    return (SavedSearchView) com.runwaysdk.business.View.get(id);
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
