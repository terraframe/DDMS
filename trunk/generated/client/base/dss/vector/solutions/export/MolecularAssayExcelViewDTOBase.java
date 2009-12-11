package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = 416450237)
public abstract class MolecularAssayExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MolecularAssayExcelView";
  private static final long serialVersionUID = 416450237;
  
  protected MolecularAssayExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSAYMETHOD = "assayMethod";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERRR = "numberRR";
  public static java.lang.String NUMBERRS = "numberRS";
  public static java.lang.String NUMBERSS = "numberSS";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String TARGET = "target";
  public String getAssayMethod()
  {
    return getValue(ASSAYMETHOD);
  }
  
  public void setAssayMethod(String value)
  {
    if(value == null)
    {
      setValue(ASSAYMETHOD, "");
    }
    else
    {
      setValue(ASSAYMETHOD, value);
    }
  }
  
  public boolean isAssayMethodWritable()
  {
    return isWritable(ASSAYMETHOD);
  }
  
  public boolean isAssayMethodReadable()
  {
    return isReadable(ASSAYMETHOD);
  }
  
  public boolean isAssayMethodModified()
  {
    return isModified(ASSAYMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getAssayMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ASSAYMETHOD).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGenerationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getIdentMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOFEMALE).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMosquitoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MOSQUITOID).getAttributeMdDTO();
  }
  
  public Integer getNumberRR()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRR));
  }
  
  public void setNumberRR(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRR, "");
    }
    else
    {
      setValue(NUMBERRR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRRWritable()
  {
    return isWritable(NUMBERRR);
  }
  
  public boolean isNumberRRReadable()
  {
    return isReadable(NUMBERRR);
  }
  
  public boolean isNumberRRModified()
  {
    return isModified(NUMBERRR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberRRMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRR).getAttributeMdDTO();
  }
  
  public Integer getNumberRS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRS));
  }
  
  public void setNumberRS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRS, "");
    }
    else
    {
      setValue(NUMBERRS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberRSWritable()
  {
    return isWritable(NUMBERRS);
  }
  
  public boolean isNumberRSReadable()
  {
    return isReadable(NUMBERRS);
  }
  
  public boolean isNumberRSModified()
  {
    return isModified(NUMBERRS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberRSMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRS).getAttributeMdDTO();
  }
  
  public Integer getNumberSS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSS));
  }
  
  public void setNumberSS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSS, "");
    }
    else
    {
      setValue(NUMBERSS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberSSWritable()
  {
    return isWritable(NUMBERSS);
  }
  
  public boolean isNumberSSReadable()
  {
    return isReadable(NUMBERSS);
  }
  
  public boolean isNumberSSModified()
  {
    return isModified(NUMBERSS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberSSMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSS).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSpeciesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIES).getAttributeMdDTO();
  }
  
  public String getTarget()
  {
    return getValue(TARGET);
  }
  
  public void setTarget(String value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value);
    }
  }
  
  public boolean isTargetWritable()
  {
    return isWritable(TARGET);
  }
  
  public boolean isTargetReadable()
  {
    return isReadable(TARGET);
  }
  
  public boolean isTargetModified()
  {
    return isModified(TARGET);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTargetMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
  }
  
  public static MolecularAssayExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (MolecularAssayExcelViewDTO) dto;
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
