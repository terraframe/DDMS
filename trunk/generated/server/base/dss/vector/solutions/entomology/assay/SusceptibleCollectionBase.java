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
package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -201625747)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SusceptibleCollection.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SusceptibleCollectionBase extends com.runwaysdk.business.Information implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.SusceptibleCollection";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -201625747;
  
  public SusceptibleCollectionBase()
  {
    super();
  }
  
  public String getActiveIngredient()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void validateActiveIngredient()
  {
    this.validateAttribute(ACTIVEINGREDIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.SusceptibleCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(String value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value);
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void validateCollectionId()
  {
    this.validateAttribute(COLLECTIONID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.SusceptibleCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.SusceptibleCollection.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{activeIngredient}", this.getActiveIngredient());
    message = replace(message, "{collectionId}", this.getCollectionId());
    message = replace(message, "{id}", this.getId());
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
