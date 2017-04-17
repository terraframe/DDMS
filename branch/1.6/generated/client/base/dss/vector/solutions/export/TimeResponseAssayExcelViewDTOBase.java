package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -1944053275)
public abstract class TimeResponseAssayExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.TimeResponseAssayExcelView";
  private static final long serialVersionUID = -1944053275;
  
  protected TimeResponseAssayExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
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
  public String getActiveIngredient()
  {
    return getValue(ACTIVEINGREDIENT);
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
  
  public boolean isActiveIngredientWritable()
  {
    return isWritable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientReadable()
  {
    return isReadable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientModified()
  {
    return isModified(ACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public String getAssay()
  {
    return getValue(ASSAY);
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
  
  public boolean isAssayWritable()
  {
    return isWritable(ASSAY);
  }
  
  public boolean isAssayReadable()
  {
    return isReadable(ASSAY);
  }
  
  public boolean isAssayModified()
  {
    return isModified(ASSAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAssayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ASSAY).getAttributeMdDTO();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
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
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
  }
  
  public String getLifeStage()
  {
    return getValue(LIFESTAGE);
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
  
  public boolean isLifeStageWritable()
  {
    return isWritable(LIFESTAGE);
  }
  
  public boolean isLifeStageReadable()
  {
    return isReadable(LIFESTAGE);
  }
  
  public boolean isLifeStageModified()
  {
    return isModified(LIFESTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLifeStageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getReferenceStrainResult()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(REFERENCESTRAINRESULT));
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
  
  public boolean isReferenceStrainResultWritable()
  {
    return isWritable(REFERENCESTRAINRESULT);
  }
  
  public boolean isReferenceStrainResultReadable()
  {
    return isReadable(REFERENCESTRAINRESULT);
  }
  
  public boolean isReferenceStrainResultModified()
  {
    return isModified(REFERENCESTRAINRESULT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getReferenceStrainResultMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(REFERENCESTRAINRESULT).getAttributeMdDTO();
  }
  
  public String getSpecies()
  {
    return getValue(SPECIES);
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
  
  public boolean isSpeciesWritable()
  {
    return isWritable(SPECIES);
  }
  
  public boolean isSpeciesReadable()
  {
    return isReadable(SPECIES);
  }
  
  public boolean isSpeciesModified()
  {
    return isModified(SPECIES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSpeciesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIES).getAttributeMdDTO();
  }
  
  public Boolean getSynergist()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SYNERGIST));
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
  
  public boolean isSynergistWritable()
  {
    return isWritable(SYNERGIST);
  }
  
  public boolean isSynergistReadable()
  {
    return isReadable(SYNERGIST);
  }
  
  public boolean isSynergistModified()
  {
    return isModified(SYNERGIST);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getSynergistMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SYNERGIST).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getTestStrainResult()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(TESTSTRAINRESULT));
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
  
  public boolean isTestStrainResultWritable()
  {
    return isWritable(TESTSTRAINRESULT);
  }
  
  public boolean isTestStrainResultReadable()
  {
    return isReadable(TESTSTRAINRESULT);
  }
  
  public boolean isTestStrainResultModified()
  {
    return isModified(TESTSTRAINRESULT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getTestStrainResultMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(TESTSTRAINRESULT).getAttributeMdDTO();
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
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
  
  public boolean isUniqueAssayIdWritable()
  {
    return isWritable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdReadable()
  {
    return isReadable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdModified()
  {
    return isModified(UNIQUEASSAYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniqueAssayIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIQUEASSAYID).getAttributeMdDTO();
  }
  
  public static TimeResponseAssayExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TimeResponseAssayExcelViewDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
