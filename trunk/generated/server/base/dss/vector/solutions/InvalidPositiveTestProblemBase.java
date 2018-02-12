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
package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -67408187)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidPositiveTestProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class InvalidPositiveTestProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.InvalidPositiveTestProblem";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String AMOUNTPOSITIVE = "amountPositive";
  private static final long serialVersionUID = -67408187;
  
  public InvalidPositiveTestProblemBase()
  {
    super();
  }
  
  public InvalidPositiveTestProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getAmount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void validateAmount()
  {
    this.validateAttribute(AMOUNT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getAmountMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.InvalidPositiveTestProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(AMOUNT);
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getAmountPositive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNTPOSITIVE));
  }
  
  public void validateAmountPositive()
  {
    this.validateAttribute(AMOUNTPOSITIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getAmountPositiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.InvalidPositiveTestProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(AMOUNTPOSITIVE);
  }
  
  public void setAmountPositive(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNTPOSITIVE, "");
    }
    else
    {
      setValue(AMOUNTPOSITIVE, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{amount}", this.getAmount());
    message = replace(message, "{amountPositive}", this.getAmountPositive());
    return message;
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
