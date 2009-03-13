package csu.mrc.ivcc.mdss.entomology;

public abstract class MorphologicalSpecieGroupViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView";
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
  public static java.lang.String SPECIE = "specie";
  public csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public void setCollection(csu.mrc.ivcc.mdss.entomology.ConcreteMosquitoCollectionDTO value)
  {
    setValue(COLLECTION, value.getId());
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("collection").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("groupId").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO getIdentificationMethod()
  {
    if(getValue(IDENTIFICATIONMETHOD) == null || getValue(IDENTIFICATIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.get(getRequest(), getValue(IDENTIFICATIONMETHOD));
    }
  }
  
  public void setIdentificationMethod(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO value)
  {
    setValue(IDENTIFICATIONMETHOD, value.getId());
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("identificationMethod").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("quantity").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.SpecieDTO getSpecie()
  {
    if(getValue(SPECIE) == null || getValue(SPECIE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return csu.mrc.ivcc.mdss.mo.SpecieDTO.get(getRequest(), getValue(SPECIE));
    }
  }
  
  public void setSpecie(csu.mrc.ivcc.mdss.mo.SpecieDTO value)
  {
    setValue(SPECIE, value.getId());
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("specie").getAttributeMdDTO();
  }
  
  public static final csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewDTO[] saveAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewDTO[] array)
  {
    String[] _declaredTypes = new String[]{"[Lcsu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView;"};
    Object[] _parameters = new Object[]{array};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewDTO.CLASS, "saveAll", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
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
