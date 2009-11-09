package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = -1420695089)
public abstract class MorphologicalSpecieGroupViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MorphologicalSpecieGroupView";
  private static final long serialVersionUID = -1420695089;
  
  protected MorphologicalSpecieGroupViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String GROUPID = "groupId";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String QUANTITYFEMALE = "quantityFemale";
  public static java.lang.String QUANTITYMALE = "quantityMale";
  public static java.lang.String SPECIE = "specie";
  public dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public void setCollection(dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO value)
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
  
  public String getGroupId()
  {
    return getValue(GROUPID);
  }
  
  public void setGroupId(String value)
  {
    if(value == null)
    {
      setValue(GROUPID, "");
    }
    else
    {
      setValue(GROUPID, value);
    }
  }
  
  public boolean isGroupIdWritable()
  {
    return isWritable(GROUPID);
  }
  
  public boolean isGroupIdReadable()
  {
    return isReadable(GROUPID);
  }
  
  public boolean isGroupIdModified()
  {
    return isModified(GROUPID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGroupIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GROUPID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getIdentificationMethod()
  {
    if(getValue(IDENTIFICATIONMETHOD) == null || getValue(IDENTIFICATIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(IDENTIFICATIONMETHOD));
    }
  }
  
  public void setIdentificationMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value.getId());
    }
  }
  
  public boolean isIdentificationMethodWritable()
  {
    return isWritable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodReadable()
  {
    return isReadable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodModified()
  {
    return isModified(IDENTIFICATIONMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIdentificationMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(IDENTIFICATIONMETHOD).getAttributeMdDTO();
  }
  
  public Integer getQuantity()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityWritable()
  {
    return isWritable(QUANTITY);
  }
  
  public boolean isQuantityReadable()
  {
    return isReadable(QUANTITY);
  }
  
  public boolean isQuantityModified()
  {
    return isModified(QUANTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
  }
  
  public Integer getQuantityFemale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYFEMALE));
  }
  
  public void setQuantityFemale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYFEMALE, "");
    }
    else
    {
      setValue(QUANTITYFEMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityFemaleWritable()
  {
    return isWritable(QUANTITYFEMALE);
  }
  
  public boolean isQuantityFemaleReadable()
  {
    return isReadable(QUANTITYFEMALE);
  }
  
  public boolean isQuantityFemaleModified()
  {
    return isModified(QUANTITYFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityFemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYFEMALE).getAttributeMdDTO();
  }
  
  public Integer getQuantityMale()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYMALE));
  }
  
  public void setQuantityMale(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYMALE, "");
    }
    else
    {
      setValue(QUANTITYMALE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityMaleWritable()
  {
    return isWritable(QUANTITYMALE);
  }
  
  public boolean isQuantityMaleReadable()
  {
    return isReadable(QUANTITYMALE);
  }
  
  public boolean isQuantityMaleModified()
  {
    return isModified(QUANTITYMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getQuantityMaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYMALE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSpecie()
  {
    if(getValue(SPECIE) == null || getValue(SPECIE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SPECIE));
    }
  }
  
  public void setSpecie(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SPECIE, "");
    }
    else
    {
      setValue(SPECIE, value.getId());
    }
  }
  
  public boolean isSpecieWritable()
  {
    return isWritable(SPECIE);
  }
  
  public boolean isSpecieReadable()
  {
    return isReadable(SPECIE);
  }
  
  public boolean isSpecieModified()
  {
    return isModified(SPECIE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSpecieMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPECIE).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO[] saveAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO[] array)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.MorphologicalSpecieGroupView;"};
    Object[] _parameters = new Object[]{array};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO.CLASS, "saveAll", _declaredTypes);
    return (dss.vector.solutions.entomology.MorphologicalSpecieGroupViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MorphologicalSpecieGroupViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (MorphologicalSpecieGroupViewDTO) dto;
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
