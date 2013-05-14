package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1494154597)
public abstract class SubCollectionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.SubCollectionView";
  private static final long serialVersionUID = -1494154597;
  
  protected SubCollectionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String EGGS = "eggs";
  public static java.lang.String FEMALESFED = "femalesFed";
  public static java.lang.String FEMALESGRAVID = "femalesGravid";
  public static java.lang.String FEMALESHALFGRAVID = "femalesHalfGravid";
  public static java.lang.String FEMALESTOTAL = "femalesTotal";
  public static java.lang.String FEMALESUNFED = "femalesUnfed";
  public static java.lang.String FEMALESUNKNOWN = "femalesUnknown";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String LARVAE = "larvae";
  public static java.lang.String MALE = "male";
  public static java.lang.String PUPAE = "pupae";
  public static java.lang.String SUBCOLLECTIONID = "subCollectionId";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TOTAL = "total";
  public static java.lang.String UNKNOWNS = "unknowns";
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
  
  public Integer getEggs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EGGS));
  }
  
  public void setEggs(Integer value)
  {
    if(value == null)
    {
      setValue(EGGS, "");
    }
    else
    {
      setValue(EGGS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isEggsWritable()
  {
    return isWritable(EGGS);
  }
  
  public boolean isEggsReadable()
  {
    return isReadable(EGGS);
  }
  
  public boolean isEggsModified()
  {
    return isModified(EGGS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getEggsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EGGS).getAttributeMdDTO();
  }
  
  public Integer getFemalesFed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESFED));
  }
  
  public void setFemalesFed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESFED, "");
    }
    else
    {
      setValue(FEMALESFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesFedWritable()
  {
    return isWritable(FEMALESFED);
  }
  
  public boolean isFemalesFedReadable()
  {
    return isReadable(FEMALESFED);
  }
  
  public boolean isFemalesFedModified()
  {
    return isModified(FEMALESFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesFedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESGRAVID));
  }
  
  public void setFemalesGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESGRAVID, "");
    }
    else
    {
      setValue(FEMALESGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesGravidWritable()
  {
    return isWritable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidReadable()
  {
    return isReadable(FEMALESGRAVID);
  }
  
  public boolean isFemalesGravidModified()
  {
    return isModified(FEMALESGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesHalfGravid()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESHALFGRAVID));
  }
  
  public void setFemalesHalfGravid(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESHALFGRAVID, "");
    }
    else
    {
      setValue(FEMALESHALFGRAVID, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesHalfGravidWritable()
  {
    return isWritable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidReadable()
  {
    return isReadable(FEMALESHALFGRAVID);
  }
  
  public boolean isFemalesHalfGravidModified()
  {
    return isModified(FEMALESHALFGRAVID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesHalfGravidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESHALFGRAVID).getAttributeMdDTO();
  }
  
  public Integer getFemalesTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESTOTAL));
  }
  
  public void setFemalesTotal(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESTOTAL, "");
    }
    else
    {
      setValue(FEMALESTOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesTotalWritable()
  {
    return isWritable(FEMALESTOTAL);
  }
  
  public boolean isFemalesTotalReadable()
  {
    return isReadable(FEMALESTOTAL);
  }
  
  public boolean isFemalesTotalModified()
  {
    return isModified(FEMALESTOTAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesTotalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESTOTAL).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnfed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNFED));
  }
  
  public void setFemalesUnfed(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNFED, "");
    }
    else
    {
      setValue(FEMALESUNFED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnfedWritable()
  {
    return isWritable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedReadable()
  {
    return isReadable(FEMALESUNFED);
  }
  
  public boolean isFemalesUnfedModified()
  {
    return isModified(FEMALESUNFED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnfedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNFED).getAttributeMdDTO();
  }
  
  public Integer getFemalesUnknown()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FEMALESUNKNOWN));
  }
  
  public void setFemalesUnknown(Integer value)
  {
    if(value == null)
    {
      setValue(FEMALESUNKNOWN, "");
    }
    else
    {
      setValue(FEMALESUNKNOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFemalesUnknownWritable()
  {
    return isWritable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownReadable()
  {
    return isReadable(FEMALESUNKNOWN);
  }
  
  public boolean isFemalesUnknownModified()
  {
    return isModified(FEMALESUNKNOWN);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getFemalesUnknownMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FEMALESUNKNOWN).getAttributeMdDTO();
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
  
  public Integer getLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LARVAE));
  }
  
  public void setLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(LARVAE, "");
    }
    else
    {
      setValue(LARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLarvaeWritable()
  {
    return isWritable(LARVAE);
  }
  
  public boolean isLarvaeReadable()
  {
    return isReadable(LARVAE);
  }
  
  public boolean isLarvaeModified()
  {
    return isModified(LARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LARVAE).getAttributeMdDTO();
  }
  
  public Integer getMale()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MALE));
  }
  
  public void setMale(Integer value)
  {
    if(value == null)
    {
      setValue(MALE, "");
    }
    else
    {
      setValue(MALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMaleWritable()
  {
    return isWritable(MALE);
  }
  
  public boolean isMaleReadable()
  {
    return isReadable(MALE);
  }
  
  public boolean isMaleModified()
  {
    return isModified(MALE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getMaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(MALE).getAttributeMdDTO();
  }
  
  public Integer getPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PUPAE));
  }
  
  public void setPupae(Integer value)
  {
    if(value == null)
    {
      setValue(PUPAE, "");
    }
    else
    {
      setValue(PUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPupaeWritable()
  {
    return isWritable(PUPAE);
  }
  
  public boolean isPupaeReadable()
  {
    return isReadable(PUPAE);
  }
  
  public boolean isPupaeModified()
  {
    return isModified(PUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PUPAE).getAttributeMdDTO();
  }
  
  public String getSubCollectionId()
  {
    return getValue(SUBCOLLECTIONID);
  }
  
  public void setSubCollectionId(String value)
  {
    if(value == null)
    {
      setValue(SUBCOLLECTIONID, "");
    }
    else
    {
      setValue(SUBCOLLECTIONID, value);
    }
  }
  
  public boolean isSubCollectionIdWritable()
  {
    return isWritable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdReadable()
  {
    return isReadable(SUBCOLLECTIONID);
  }
  
  public boolean isSubCollectionIdModified()
  {
    return isModified(SUBCOLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSubCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SUBCOLLECTIONID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTaxon()
  {
    if(getValue(TAXON) == null || getValue(TAXON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TAXON));
    }
  }
  
  public String getTaxonId()
  {
    return getValue(TAXON);
  }
  
  public void setTaxon(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value.getId());
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public Integer getTotal()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTAL));
  }
  
  public void setTotal(Integer value)
  {
    if(value == null)
    {
      setValue(TOTAL, "");
    }
    else
    {
      setValue(TOTAL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalWritable()
  {
    return isWritable(TOTAL);
  }
  
  public boolean isTotalReadable()
  {
    return isReadable(TOTAL);
  }
  
  public boolean isTotalModified()
  {
    return isModified(TOTAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTotalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTAL).getAttributeMdDTO();
  }
  
  public Integer getUnknowns()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNKNOWNS));
  }
  
  public void setUnknowns(Integer value)
  {
    if(value == null)
    {
      setValue(UNKNOWNS, "");
    }
    else
    {
      setValue(UNKNOWNS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnknownsWritable()
  {
    return isWritable(UNKNOWNS);
  }
  
  public boolean isUnknownsReadable()
  {
    return isReadable(UNKNOWNS);
  }
  
  public boolean isUnknownsModified()
  {
    return isModified(UNKNOWNS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnknownsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNKNOWNS).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.SubCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SubCollectionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (SubCollectionViewDTO) dto;
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
