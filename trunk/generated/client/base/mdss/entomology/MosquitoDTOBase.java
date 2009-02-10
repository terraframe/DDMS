package mdss.entomology;

public abstract class MosquitoDTOBase extends mdss.entomology.TrueSpecieEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "mdss.entomology.Mosquito";
  private static final long serialVersionUID = 1234294598434L;
  
  protected MosquitoDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String SEX = "sex";
  public static java.lang.String TESTDATE = "testDate";
  @SuppressWarnings("unchecked")
  public java.util.List<mdss.entomology.GenerationDTO> getGeneration()
  {
    return (java.util.List<mdss.entomology.GenerationDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "mdss.entomology.Generation", getEnumNames(GENERATION));
  }
  
  public java.util.List<String> getGenerationEnumNames()
  {
    return getEnumNames(GENERATION);
  }
  
  public void addGeneration(mdss.entomology.GenerationDTO enumDTO)
  {
    addEnumItem(GENERATION, enumDTO.toString());
  }
  
  public void removeGeneration(mdss.entomology.GenerationDTO enumDTO)
  {
    removeEnumItem(GENERATION, enumDTO.toString());
  }
  
  public void clearGeneration()
  {
    clearEnum(GENERATION);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getGenerationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("generation").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isofemale").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<mdss.general.SexDTO> getSex()
  {
    return (java.util.List<mdss.general.SexDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "mdss.general.Sex", getEnumNames(SEX));
  }
  
  public java.util.List<String> getSexEnumNames()
  {
    return getEnumNames(SEX);
  }
  
  public void addSex(mdss.general.SexDTO enumDTO)
  {
    addEnumItem(SEX, enumDTO.toString());
  }
  
  public void removeSex(mdss.general.SexDTO enumDTO)
  {
    removeEnumItem(SEX, enumDTO.toString());
  }
  
  public void clearSex()
  {
    clearEnum(SEX);
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("sex").getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isTestDateWritable()
  {
    return isWritable(TESTDATE);
  }
  
  public boolean isTestDateReadable()
  {
    return isReadable(TESTDATE);
  }
  
  public boolean isTestDateModified()
  {
    return isModified(TESTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO("testDate").getAttributeMdDTO();
  }
  
  public static mdss.entomology.MosquitoDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (mdss.entomology.MosquitoDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static mdss.entomology.MosquitoQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (mdss.entomology.MosquitoQueryDTO) clientRequest.getAllInstances("mdss.entomology.Mosquito", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static mdss.entomology.MosquitoDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.MosquitoDTO.CLASS, "lock", _declaredTypes);
    return (mdss.entomology.MosquitoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static mdss.entomology.MosquitoDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(mdss.entomology.MosquitoDTO.CLASS, "unlock", _declaredTypes);
    return (mdss.entomology.MosquitoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
