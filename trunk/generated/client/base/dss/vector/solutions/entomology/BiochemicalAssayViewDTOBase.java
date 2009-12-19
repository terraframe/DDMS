package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -438734270)
public abstract class BiochemicalAssayViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.BiochemicalAssayView";
  private static final long serialVersionUID = -438734270;
  
  protected BiochemicalAssayViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERELEVATED = "numberElevated";
  public static java.lang.String NUMBERTESTED = "numberTested";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public dss.vector.solutions.ontology.TermDTO getAssay()
  {
    if(getValue(ASSAY) == null || getValue(ASSAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ASSAY));
    }
  }
  
  public void setAssay(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ASSAY, "");
    }
    else
    {
      setValue(ASSAY, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAssayMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ASSAY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.MosquitoCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollectionDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
    }
  }
  
  public boolean isCollectionWritable()
  {
    return isWritable(COLLECTION);
  }
  
  public boolean isCollectionReadable()
  {
    return isReadable(COLLECTION);
  }
  
  public boolean isCollectionModified()
  {
    return isModified(COLLECTION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCollectionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTION).getAttributeMdDTO();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getGeneration()
  {
    if(getValue(GENERATION) == null || getValue(GENERATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(GENERATION));
    }
  }
  
  public void setGeneration(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGenerationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getIdentMethod()
  {
    if(getValue(IDENTMETHOD) == null || getValue(IDENTMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(IDENTMETHOD));
    }
  }
  
  public void setIdentMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIdentMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
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
  
  public Integer getNumberElevated()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERELEVATED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberElevatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERELEVATED).getAttributeMdDTO();
  }
  
  public Integer getNumberTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERTESTED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberTestedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERTESTED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSpecies()
  {
    if(getValue(SPECIES) == null || getValue(SPECIES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SPECIES));
    }
  }
  
  public void setSpecies(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value.getId());
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSpeciesMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPECIES).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.BiochemicalAssayView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.BiochemicalAssayViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.BiochemicalAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.BiochemicalAssayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.BiochemicalAssayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static BiochemicalAssayViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (BiochemicalAssayViewDTO) dto;
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
