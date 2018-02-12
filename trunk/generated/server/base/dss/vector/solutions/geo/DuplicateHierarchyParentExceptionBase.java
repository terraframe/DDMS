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

@com.runwaysdk.business.ClassSignature(hash = -1228847344)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateHierarchyParentException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateHierarchyParentExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.DuplicateHierarchyParentException";
  public static java.lang.String CHILDDISPLAYLABEL = "childDisplayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTDISPLAYLABEL = "parentDisplayLabel";
  private static final long serialVersionUID = -1228847344;
  
  public DuplicateHierarchyParentExceptionBase()
  {
    super();
  }
  
  public DuplicateHierarchyParentExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateHierarchyParentExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateHierarchyParentExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getChildDisplayLabel()
  {
    return getValue(CHILDDISPLAYLABEL);
  }
  
  public void validateChildDisplayLabel()
  {
    this.validateAttribute(CHILDDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getChildDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateHierarchyParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CHILDDISPLAYLABEL);
  }
  
  public void setChildDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(CHILDDISPLAYLABEL, "");
    }
    else
    {
      setValue(CHILDDISPLAYLABEL, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateHierarchyParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getParentDisplayLabel()
  {
    return getValue(PARENTDISPLAYLABEL);
  }
  
  public void validateParentDisplayLabel()
  {
    this.validateAttribute(PARENTDISPLAYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getParentDisplayLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.geo.DuplicateHierarchyParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PARENTDISPLAYLABEL);
  }
  
  public void setParentDisplayLabel(String value)
  {
    if(value == null)
    {
      setValue(PARENTDISPLAYLABEL, "");
    }
    else
    {
      setValue(PARENTDISPLAYLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{childDisplayLabel}", this.getChildDisplayLabel());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{parentDisplayLabel}", this.getParentDisplayLabel());
    return message;
  }
  
}
