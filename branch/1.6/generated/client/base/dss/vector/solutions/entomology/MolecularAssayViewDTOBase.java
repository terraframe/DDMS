package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -2141904403)
public abstract class MolecularAssayViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MolecularAssayView";
  private static final long serialVersionUID = -2141904403;
  
  protected MolecularAssayViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSAYMETHOD = "assayMethod";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
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
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  public dss.vector.solutions.ontology.TermDTO getAssayMethod()
  {
    if(getValue(ASSAYMETHOD) == null || getValue(ASSAYMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ASSAYMETHOD));
    }
  }
  
  public String getAssayMethodId()
  {
    return getValue(ASSAYMETHOD);
  }
  
  public void setAssayMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ASSAYMETHOD, "");
    }
    else
    {
      setValue(ASSAYMETHOD, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAssayMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ASSAYMETHOD).getAttributeMdDTO();
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
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTION).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
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
  
  public String getGenerationId()
  {
    return getValue(GENERATION);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGenerationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
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
  
  public String getIdentMethodId()
  {
    return getValue(IDENTMETHOD);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIdentMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IDENTMETHOD).getAttributeMdDTO();
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
  
  public Integer getNumberRR()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRR));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberRRMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRR).getAttributeMdDTO();
  }
  
  public Integer getNumberRS()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRS));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberRSMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERRS).getAttributeMdDTO();
  }
  
  public Integer getNumberSS()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSS));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberSSMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERSS).getAttributeMdDTO();
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
  
  public String getSexId()
  {
    return getValue(SEX);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
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
  
  public String getSpeciesId()
  {
    return getValue(SPECIES);
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getSpeciesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPECIES).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTarget()
  {
    if(getValue(TARGET) == null || getValue(TARGET).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TARGET));
    }
  }
  
  public String getTargetId()
  {
    return getValue(TARGET);
  }
  
  public void setTarget(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value.getId());
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTargetMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TARGET).getAttributeMdDTO();
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
  
  public static final dss.vector.solutions.entomology.MolecularAssayViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.MolecularAssayViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.MolecularAssayView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MolecularAssayViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.MolecularAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MolecularAssayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MolecularAssayViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MolecularAssayViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MolecularAssayViewDTO) dto;
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
