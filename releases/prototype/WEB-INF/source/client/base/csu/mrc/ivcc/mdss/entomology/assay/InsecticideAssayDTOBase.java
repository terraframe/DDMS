package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class InsecticideAssayDTOBase extends csu.mrc.ivcc.mdss.entomology.assay.AdultAssayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236612275896L;
  
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssay";
  protected InsecticideAssayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected InsecticideAssayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String GENERICNAME = "genericName";
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String UNITS = "units";
  public Integer getAmount()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAmountWritable()
  {
    return isWritable(AMOUNT);
  }
  
  public boolean isAmountReadable()
  {
    return isReadable(AMOUNT);
  }
  
  public boolean isAmountModified()
  {
    return isModified(AMOUNT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getAmountMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("amount").getAttributeMdDTO();
  }
  
  public String getGenericName()
  {
    return getValue(GENERICNAME);
  }
  
  public void setGenericName(String value)
  {
    if(value == null)
    {
      setValue(GENERICNAME, "");
    }
    else
    {
      setValue(GENERICNAME, value);
    }
  }
  
  public boolean isGenericNameWritable()
  {
    return isWritable(GENERICNAME);
  }
  
  public boolean isGenericNameReadable()
  {
    return isReadable(GENERICNAME);
  }
  
  public boolean isGenericNameModified()
  {
    return isModified(GENERICNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGenericNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("genericName").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideDTO getInsecticide()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideDTO.get(getRequest(), getValue(INSECTICIDE));
  }
  
  public void setInsecticide(csu.mrc.ivcc.mdss.mo.InsecticideDTO value)
  {
    setValue(INSECTICIDE, value.getId());
  }
  
  public boolean isInsecticideWritable()
  {
    return isWritable(INSECTICIDE);
  }
  
  public boolean isInsecticideReadable()
  {
    return isReadable(INSECTICIDE);
  }
  
  public boolean isInsecticideModified()
  {
    return isModified(INSECTICIDE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getInsecticideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("insecticide").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.entomology.assay.UnitDTO> getUnits()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.entomology.assay.UnitDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "csu.mrc.ivcc.mdss.entomology.assay.Unit", getEnumNames(UNITS));
  }
  
  public java.util.List<String> getUnitsEnumNames()
  {
    return getEnumNames(UNITS);
  }
  
  public void addUnits(csu.mrc.ivcc.mdss.entomology.assay.UnitDTO enumDTO)
  {
    addEnumItem(UNITS, enumDTO.toString());
  }
  
  public void removeUnits(csu.mrc.ivcc.mdss.entomology.assay.UnitDTO enumDTO)
  {
    removeEnumItem(UNITS, enumDTO.toString());
  }
  
  public void clearUnits()
  {
    clearEnum(UNITS);
  }
  
  public boolean isUnitsWritable()
  {
    return isWritable(UNITS);
  }
  
  public boolean isUnitsReadable()
  {
    return isReadable(UNITS);
  }
  
  public boolean isUnitsModified()
  {
    return isModified(UNITS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getUnitsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("units").getAttributeMdDTO();
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO) dto;
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
  
  public static csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayQueryDTO) clientRequest.getAllInstances("csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssay", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.CLASS, "lock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO.CLASS, "unlock", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
