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

@com.runwaysdk.business.ClassSignature(hash = 626779541)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to LarvaeDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class LarvaeDiscriminatingDoseAssayBase extends dss.vector.solutions.entomology.assay.LarvaeAssay implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String LT50 = "lt50";
  public static java.lang.String LT95 = "lt95";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  private static final long serialVersionUID = 626779541;
  
  public LarvaeDiscriminatingDoseAssayBase()
  {
    super();
  }
  
  public Float getControlTestMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
  }
  
  public void validateControlTestMortality()
  {
    this.validateAttribute(CONTROLTESTMORTALITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeFloatDAOIF getControlTestMortalityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeFloatDAOIF)mdClassIF.definesAttribute(CONTROLTESTMORTALITY);
  }
  
  public void setControlTestMortality(Float value)
  {
    if(value == null)
    {
      setValue(CONTROLTESTMORTALITY, "");
    }
    else
    {
      setValue(CONTROLTESTMORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public Integer getHoldingTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
  }
  
  public void validateHoldingTime()
  {
    this.validateAttribute(HOLDINGTIME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getHoldingTimeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(HOLDINGTIME);
  }
  
  public void setHoldingTime(Integer value)
  {
    if(value == null)
    {
      setValue(HOLDINGTIME, "");
    }
    else
    {
      setValue(HOLDINGTIME, java.lang.Integer.toString(value));
    }
  }
  
  public Double getLt50()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(LT50));
  }
  
  public void validateLt50()
  {
    this.validateAttribute(LT50);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getLt50Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(LT50);
  }
  
  public void setLt50(Double value)
  {
    if(value == null)
    {
      setValue(LT50, "");
    }
    else
    {
      setValue(LT50, java.lang.Double.toString(value));
    }
  }
  
  public Double getLt95()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(LT95));
  }
  
  public void validateLt95()
  {
    this.validateAttribute(LT95);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF getLt95Md()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF)mdClassIF.definesAttribute(LT95);
  }
  
  public void setLt95(Double value)
  {
    if(value == null)
    {
      setValue(LT95, "");
    }
    else
    {
      setValue(LT95, java.lang.Double.toString(value));
    }
  }
  
  public Float getMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
  }
  
  public void validateMortality()
  {
    this.validateAttribute(MORTALITY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeFloatDAOIF getMortalityMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeFloatDAOIF)mdClassIF.definesAttribute(MORTALITY);
  }
  
  public void setMortality(Float value)
  {
    if(value == null)
    {
      setValue(MORTALITY, "");
    }
    else
    {
      setValue(MORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public Integer getQuantityDead()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void validateQuantityDead()
  {
    this.validateAttribute(QUANTITYDEAD);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getQuantityDeadMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(QUANTITYDEAD);
  }
  
  public void setQuantityDead(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYDEAD, "");
    }
    else
    {
      setValue(QUANTITYDEAD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityLive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
  }
  
  public void validateQuantityLive()
  {
    this.validateAttribute(QUANTITYLIVE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getQuantityLiveMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(QUANTITYLIVE);
  }
  
  public void setQuantityLive(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYLIVE, "");
    }
    else
    {
      setValue(QUANTITYLIVE, java.lang.Integer.toString(value));
    }
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void validateUniqueAssayId()
  {
    this.validateAttribute(UNIQUEASSAYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getUniqueAssayIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static LarvaeDiscriminatingDoseAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    LarvaeDiscriminatingDoseAssayQuery query = new LarvaeDiscriminatingDoseAssayQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static LarvaeDiscriminatingDoseAssay get(String id)
  {
    return (LarvaeDiscriminatingDoseAssay) com.runwaysdk.business.Business.get(id);
  }
  
  public static LarvaeDiscriminatingDoseAssay getByKey(String key)
  {
    return (LarvaeDiscriminatingDoseAssay) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static LarvaeDiscriminatingDoseAssay lock(java.lang.String id)
  {
    LarvaeDiscriminatingDoseAssay _instance = LarvaeDiscriminatingDoseAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static LarvaeDiscriminatingDoseAssay unlock(java.lang.String id)
  {
    LarvaeDiscriminatingDoseAssay _instance = LarvaeDiscriminatingDoseAssay.get(id);
    _instance.unlock();
    
    return _instance;
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
