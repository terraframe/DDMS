package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -864640352)
public abstract class BiochemicalAssayExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.BiochemicalAssayExcelView";
  private static final long serialVersionUID = -864640352;
  
  protected BiochemicalAssayExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERELEVATED = "numberElevated";
  public static java.lang.String NUMBERTESTED = "numberTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
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
  
  public String getGeneration()
  {
    return getValue(GENERATION);
  }
  
  public void setGeneration(String value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value);
    }
  }
  
  public boolean isGenerationWritable()
  {
    return isWritable(GENERATION);
  }
  
  public boolean isGenerationReadable()
  {
    return isReadable(GENERATION);
  }
  
  public boolean isGenerationModified()
  {
    return isModified(GENERATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGenerationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
  }
  
  public String getIdentMethod()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void setIdentMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value);
    }
  }
  
  public boolean isIdentMethodWritable()
  {
    return isWritable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodReadable()
  {
    return isReadable(IDENTMETHOD);
  }
  
  public boolean isIdentMethodModified()
  {
    return isModified(IDENTMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsofemaleWritable()
  {
    return isWritable(ISOFEMALE);
  }
  
  public boolean isIsofemaleReadable()
  {
    return isReadable(ISOFEMALE);
  }
  
  public boolean isIsofemaleModified()
  {
    return isModified(ISOFEMALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOFEMALE).getAttributeMdDTO();
  }
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void setMosquitoId(String value)
  {
    if(value == null)
    {
      setValue(MOSQUITOID, "");
    }
    else
    {
      setValue(MOSQUITOID, value);
    }
  }
  
  public boolean isMosquitoIdWritable()
  {
    return isWritable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdReadable()
  {
    return isReadable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdModified()
  {
    return isModified(MOSQUITOID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMosquitoIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MOSQUITOID).getAttributeMdDTO();
  }
  
  public Integer getNumberElevated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERELEVATED));
  }
  
  public void setNumberElevated(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERELEVATED, "");
    }
    else
    {
      setValue(NUMBERELEVATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberElevatedWritable()
  {
    return isWritable(NUMBERELEVATED);
  }
  
  public boolean isNumberElevatedReadable()
  {
    return isReadable(NUMBERELEVATED);
  }
  
  public boolean isNumberElevatedModified()
  {
    return isModified(NUMBERELEVATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberElevatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERELEVATED).getAttributeMdDTO();
  }
  
  public Integer getNumberTested()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERTESTED));
  }
  
  public void setNumberTested(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERTESTED, "");
    }
    else
    {
      setValue(NUMBERTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberTestedWritable()
  {
    return isWritable(NUMBERTESTED);
  }
  
  public boolean isNumberTestedReadable()
  {
    return isReadable(NUMBERTESTED);
  }
  
  public boolean isNumberTestedModified()
  {
    return isModified(NUMBERTESTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberTestedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERTESTED).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public static BiochemicalAssayExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (BiochemicalAssayExcelViewDTO) dto;
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
