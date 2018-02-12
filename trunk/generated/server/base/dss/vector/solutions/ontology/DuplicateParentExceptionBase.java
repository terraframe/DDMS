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

@com.runwaysdk.business.ClassSignature(hash = -1721214367)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateParentException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateParentExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.DuplicateParentException";
  public static java.lang.String CHILDTERM = "childTerm";
  public static java.lang.String ID = "id";
  public static java.lang.String PARENTTERM = "parentTerm";
  private static final long serialVersionUID = -1721214367;
  
  public DuplicateParentExceptionBase()
  {
    super();
  }
  
  public DuplicateParentExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateParentExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateParentExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getChildTerm()
  {
    return getValue(CHILDTERM);
  }
  
  public void validateChildTerm()
  {
    this.validateAttribute(CHILDTERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getChildTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CHILDTERM);
  }
  
  public void setChildTerm(String value)
  {
    if(value == null)
    {
      setValue(CHILDTERM, "");
    }
    else
    {
      setValue(CHILDTERM, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getParentTerm()
  {
    return getValue(PARENTTERM);
  }
  
  public void validateParentTerm()
  {
    this.validateAttribute(PARENTTERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getParentTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.DuplicateParentException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PARENTTERM);
  }
  
  public void setParentTerm(String value)
  {
    if(value == null)
    {
      setValue(PARENTTERM, "");
    }
    else
    {
      setValue(PARENTTERM, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{childTerm}", this.getChildTerm());
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{parentTerm}", this.getParentTerm());
    return message;
  }
  
}
