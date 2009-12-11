package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1265380722)
public abstract class PooledInfectionAssayExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PooledInfectionAssayExcelView";
  private static final long serialVersionUID = -1265380722;
  
  protected PooledInfectionAssayExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String INFECTED = "infected";
  public static java.lang.String MOSQUITOSTESTED = "mosquitosTested";
  public static java.lang.String NUMBERPOSITIVE = "numberPositive";
  public static java.lang.String PARASITE = "parasite";
  public static java.lang.String POOLID = "poolId";
  public static java.lang.String POOLSTESTED = "poolsTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String TESTMETHOD = "testMethod";
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
  
  public Boolean getInfected()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(INFECTED));
  }
  
  public void setInfected(Boolean value)
  {
    if(value == null)
    {
      setValue(INFECTED, "");
    }
    else
    {
      setValue(INFECTED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isInfectedWritable()
  {
    return isWritable(INFECTED);
  }
  
  public boolean isInfectedReadable()
  {
    return isReadable(INFECTED);
  }
  
  public boolean isInfectedModified()
  {
    return isModified(INFECTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getInfectedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(INFECTED).getAttributeMdDTO();
  }
  
  public Integer getMosquitosTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MOSQUITOSTESTED));
  }
  
  public void setMosquitosTested(Integer value)
  {
    if(value == null)
    {
      setValue(MOSQUITOSTESTED, "");
    }
    else
    {
      setValue(MOSQUITOSTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMosquitosTestedWritable()
  {
    return isWritable(MOSQUITOSTESTED);
  }
  
  public boolean isMosquitosTestedReadable()
  {
    return isReadable(MOSQUITOSTESTED);
  }
  
  public boolean isMosquitosTestedModified()
  {
    return isModified(MOSQUITOSTESTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMosquitosTestedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MOSQUITOSTESTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPositive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPOSITIVE));
  }
  
  public void setNumberPositive(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPOSITIVE, "");
    }
    else
    {
      setValue(NUMBERPOSITIVE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPositiveWritable()
  {
    return isWritable(NUMBERPOSITIVE);
  }
  
  public boolean isNumberPositiveReadable()
  {
    return isReadable(NUMBERPOSITIVE);
  }
  
  public boolean isNumberPositiveModified()
  {
    return isModified(NUMBERPOSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPositiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPOSITIVE).getAttributeMdDTO();
  }
  
  public String getParasite()
  {
    return getValue(PARASITE);
  }
  
  public void setParasite(String value)
  {
    if(value == null)
    {
      setValue(PARASITE, "");
    }
    else
    {
      setValue(PARASITE, value);
    }
  }
  
  public boolean isParasiteWritable()
  {
    return isWritable(PARASITE);
  }
  
  public boolean isParasiteReadable()
  {
    return isReadable(PARASITE);
  }
  
  public boolean isParasiteModified()
  {
    return isModified(PARASITE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getParasiteMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PARASITE).getAttributeMdDTO();
  }
  
  public String getPoolId()
  {
    return getValue(POOLID);
  }
  
  public void setPoolId(String value)
  {
    if(value == null)
    {
      setValue(POOLID, "");
    }
    else
    {
      setValue(POOLID, value);
    }
  }
  
  public boolean isPoolIdWritable()
  {
    return isWritable(POOLID);
  }
  
  public boolean isPoolIdReadable()
  {
    return isReadable(POOLID);
  }
  
  public boolean isPoolIdModified()
  {
    return isModified(POOLID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPoolIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POOLID).getAttributeMdDTO();
  }
  
  public Integer getPoolsTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(POOLSTESTED));
  }
  
  public void setPoolsTested(Integer value)
  {
    if(value == null)
    {
      setValue(POOLSTESTED, "");
    }
    else
    {
      setValue(POOLSTESTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPoolsTestedWritable()
  {
    return isWritable(POOLSTESTED);
  }
  
  public boolean isPoolsTestedReadable()
  {
    return isReadable(POOLSTESTED);
  }
  
  public boolean isPoolsTestedModified()
  {
    return isModified(POOLSTESTED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPoolsTestedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(POOLSTESTED).getAttributeMdDTO();
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
  
  public String getTestMethod()
  {
    return getValue(TESTMETHOD);
  }
  
  public void setTestMethod(String value)
  {
    if(value == null)
    {
      setValue(TESTMETHOD, "");
    }
    else
    {
      setValue(TESTMETHOD, value);
    }
  }
  
  public boolean isTestMethodWritable()
  {
    return isWritable(TESTMETHOD);
  }
  
  public boolean isTestMethodReadable()
  {
    return isReadable(TESTMETHOD);
  }
  
  public boolean isTestMethodModified()
  {
    return isModified(TESTMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTestMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TESTMETHOD).getAttributeMdDTO();
  }
  
  public static PooledInfectionAssayExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (PooledInfectionAssayExcelViewDTO) dto;
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
