package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1583249509)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TimeResponseAssayExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TimeResponseAssayExcelViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.TimeResponseAssayExcelView";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String ID = "id";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String REFERENCESTRAINRESULT = "referenceStrainResult";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String SYNERGIST = "synergist";
  public static java.lang.String TESTSTRAINRESULT = "testStrainResult";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  private static final long serialVersionUID = 1583249509;
  
  public TimeResponseAssayExcelViewBase()
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ACTIVEINGREDIENT);
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
  
  public String getAssay()
  {
    return getValue(ASSAY);
  }
  
  public void validateAssay()
  {
    this.validateAttribute(ASSAY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getAssayMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ASSAY);
  }
  
  public void setAssay(String value)
  {
    if(value == null)
    {
      setValue(ASSAY, "");
    }
    else
    {
      setValue(ASSAY, value);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONID);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getLifeStage()
  {
    return getValue(LIFESTAGE);
  }
  
  public void validateLifeStage()
  {
    this.validateAttribute(LIFESTAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLifeStageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(LIFESTAGE);
  }
  
  public void setLifeStage(String value)
  {
    if(value == null)
    {
      setValue(LIFESTAGE, "");
    }
    else
    {
      setValue(LIFESTAGE, value);
    }
  }
  
  public java.math.BigDecimal getReferenceStrainResult()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(REFERENCESTRAINRESULT));
  }
  
  public void validateReferenceStrainResult()
  {
    this.validateAttribute(REFERENCESTRAINRESULT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getReferenceStrainResultMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(REFERENCESTRAINRESULT);
  }
  
  public void setReferenceStrainResult(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(REFERENCESTRAINRESULT, "");
    }
    else
    {
      setValue(REFERENCESTRAINRESULT, value.toString());
    }
  }
  
  public String getSpecies()
  {
    return getValue(SPECIES);
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSpeciesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(String value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value);
    }
  }
  
  public Boolean getSynergist()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SYNERGIST));
  }
  
  public void validateSynergist()
  {
    this.validateAttribute(SYNERGIST);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSynergistMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SYNERGIST);
  }
  
  public void setSynergist(Boolean value)
  {
    if(value == null)
    {
      setValue(SYNERGIST, "");
    }
    else
    {
      setValue(SYNERGIST, java.lang.Boolean.toString(value));
    }
  }
  
  public java.math.BigDecimal getTestStrainResult()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(TESTSTRAINRESULT));
  }
  
  public void validateTestStrainResult()
  {
    this.validateAttribute(TESTSTRAINRESULT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTestStrainResultMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(TESTSTRAINRESULT);
  }
  
  public void setTestStrainResult(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(TESTSTRAINRESULT, "");
    }
    else
    {
      setValue(TESTSTRAINRESULT, value.toString());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUniqueAssayIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.TimeResponseAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(UNIQUEASSAYID);
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
  
  public static TimeResponseAssayExcelView get(String id)
  {
    return (TimeResponseAssayExcelView) com.runwaysdk.business.View.get(id);
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
