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
package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -278229374)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateTeamSprayImportException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateTeamSprayImportExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.DuplicateTeamSprayImportException";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDETERM = "insecticideTerm";
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String SPRAYDATE = "sprayDate";
  public static java.lang.String SPRAYMETHOD = "sprayMethod";
  public static java.lang.String SPRAYTEAM = "sprayTeam";
  private static final long serialVersionUID = -278229374;
  
  public DuplicateTeamSprayImportExceptionBase()
  {
    super();
  }
  
  public DuplicateTeamSprayImportExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateTeamSprayImportExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateTeamSprayImportExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getInsecticideTerm()
  {
    return getValue(INSECTICIDETERM);
  }
  
  public void validateInsecticideTerm()
  {
    this.validateAttribute(INSECTICIDETERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getInsecticideTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(INSECTICIDETERM);
  }
  
  public void setInsecticideTerm(String value)
  {
    if(value == null)
    {
      setValue(INSECTICIDETERM, "");
    }
    else
    {
      setValue(INSECTICIDETERM, value);
    }
  }
  
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void validateOperatorId()
  {
    this.validateAttribute(OPERATORID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getOperatorIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public java.util.Date getSprayDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(SPRAYDATE));
  }
  
  public void validateSprayDate()
  {
    this.validateAttribute(SPRAYDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateDAOIF getSprayDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateDAOIF)mdClassIF.definesAttribute(SPRAYDATE);
  }
  
  public void setSprayDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(SPRAYDATE, "");
    }
    else
    {
      setValue(SPRAYDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public String getSprayMethod()
  {
    return getValue(SPRAYMETHOD);
  }
  
  public void validateSprayMethod()
  {
    this.validateAttribute(SPRAYMETHOD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSprayMethodMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SPRAYMETHOD);
  }
  
  public void setSprayMethod(String value)
  {
    if(value == null)
    {
      setValue(SPRAYMETHOD, "");
    }
    else
    {
      setValue(SPRAYMETHOD, value);
    }
  }
  
  public String getSprayTeam()
  {
    return getValue(SPRAYTEAM);
  }
  
  public void validateSprayTeam()
  {
    this.validateAttribute(SPRAYTEAM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSprayTeamMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.DuplicateTeamSprayImportException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SPRAYTEAM);
  }
  
  public void setSprayTeam(String value)
  {
    if(value == null)
    {
      setValue(SPRAYTEAM, "");
    }
    else
    {
      setValue(SPRAYTEAM, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    message = replace(message, "{insecticideTerm}", this.getInsecticideTerm());
    message = replace(message, "{operatorId}", this.getOperatorId());
    message = replace(message, "{sprayDate}", this.getSprayDate());
    message = replace(message, "{sprayMethod}", this.getSprayMethod());
    message = replace(message, "{sprayTeam}", this.getSprayTeam());
    return message;
  }
  
}
