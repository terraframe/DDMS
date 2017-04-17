package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 38745188)
public abstract class InsecticideBrandViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandView";
  private static final long serialVersionUID = 38745188;
  
  protected InsecticideBrandViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String CONCENTRATIONQUALIFIER = "concentrationQualifier";
  public static java.lang.String CONCENTRATIONQUANTIFIER = "concentrationQuantifier";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICDEID = "insecticdeId";
  public static java.lang.String INSECTICIDEUSE = "insecticideUse";
  public static java.lang.String PRODUCTNAME = "productName";
  public static java.lang.String UNITQUALIFIER = "unitQualifier";
  public static java.lang.String UNITQUANTIFIER = "unitQuantifier";
  public static java.lang.String UNITSPERAPPLICATION = "unitsPerApplication";
  public static java.lang.String USEDETAIL = "useDetail";
  public dss.vector.solutions.ontology.TermDTO getActiveIngredient()
  {
    if(getValue(ACTIVEINGREDIENT) == null || getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(ACTIVEINGREDIENT));
    }
  }
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value.getId());
    }
  }
  
  public boolean isActiveIngredientWritable()
  {
    return isWritable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientReadable()
  {
    return isReadable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientModified()
  {
    return isModified(ACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO> getConcentrationQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO.CLASS, getEnumNames(CONCENTRATIONQUALIFIER));
  }
  
  public java.util.List<String> getConcentrationQualifierEnumNames()
  {
    return getEnumNames(CONCENTRATIONQUALIFIER);
  }
  
  public void addConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO enumDTO)
  {
    addEnumItem(CONCENTRATIONQUALIFIER, enumDTO.toString());
  }
  
  public void removeConcentrationQualifier(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO enumDTO)
  {
    removeEnumItem(CONCENTRATIONQUALIFIER, enumDTO.toString());
  }
  
  public void clearConcentrationQualifier()
  {
    clearEnum(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierWritable()
  {
    return isWritable(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierReadable()
  {
    return isReadable(CONCENTRATIONQUALIFIER);
  }
  
  public boolean isConcentrationQualifierModified()
  {
    return isModified(CONCENTRATIONQUALIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getConcentrationQualifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(CONCENTRATIONQUALIFIER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getConcentrationQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CONCENTRATIONQUANTIFIER));
  }
  
  public void setConcentrationQuantifier(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CONCENTRATIONQUANTIFIER, "");
    }
    else
    {
      setValue(CONCENTRATIONQUANTIFIER, value.toString());
    }
  }
  
  public boolean isConcentrationQuantifierWritable()
  {
    return isWritable(CONCENTRATIONQUANTIFIER);
  }
  
  public boolean isConcentrationQuantifierReadable()
  {
    return isReadable(CONCENTRATIONQUANTIFIER);
  }
  
  public boolean isConcentrationQuantifierModified()
  {
    return isModified(CONCENTRATIONQUANTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getConcentrationQuantifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONCENTRATIONQUANTIFIER).getAttributeMdDTO();
  }
  
  public Boolean getEnabled()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
  }
  
  public void setEnabled(Boolean value)
  {
    if(value == null)
    {
      setValue(ENABLED, "");
    }
    else
    {
      setValue(ENABLED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isEnabledWritable()
  {
    return isWritable(ENABLED);
  }
  
  public boolean isEnabledReadable()
  {
    return isReadable(ENABLED);
  }
  
  public boolean isEnabledModified()
  {
    return isModified(ENABLED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getEnabledMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLED).getAttributeMdDTO();
  }
  
  public String getInsecticdeId()
  {
    return getValue(INSECTICDEID);
  }
  
  public void setInsecticdeId(String value)
  {
    if(value == null)
    {
      setValue(INSECTICDEID, "");
    }
    else
    {
      setValue(INSECTICDEID, value);
    }
  }
  
  public boolean isInsecticdeIdWritable()
  {
    return isWritable(INSECTICDEID);
  }
  
  public boolean isInsecticdeIdReadable()
  {
    return isReadable(INSECTICDEID);
  }
  
  public boolean isInsecticdeIdModified()
  {
    return isModified(INSECTICDEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getInsecticdeIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICDEID).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUseDTO> getInsecticideUse()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandUseDTO.CLASS, getEnumNames(INSECTICIDEUSE));
  }
  
  public java.util.List<String> getInsecticideUseEnumNames()
  {
    return getEnumNames(INSECTICIDEUSE);
  }
  
  public void addInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUseDTO enumDTO)
  {
    addEnumItem(INSECTICIDEUSE, enumDTO.toString());
  }
  
  public void removeInsecticideUse(dss.vector.solutions.irs.InsecticideBrandUseDTO enumDTO)
  {
    removeEnumItem(INSECTICIDEUSE, enumDTO.toString());
  }
  
  public void clearInsecticideUse()
  {
    clearEnum(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseWritable()
  {
    return isWritable(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseReadable()
  {
    return isReadable(INSECTICIDEUSE);
  }
  
  public boolean isInsecticideUseModified()
  {
    return isModified(INSECTICIDEUSE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getInsecticideUseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(INSECTICIDEUSE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getProductName()
  {
    if(getValue(PRODUCTNAME) == null || getValue(PRODUCTNAME).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PRODUCTNAME));
    }
  }
  
  public String getProductNameId()
  {
    return getValue(PRODUCTNAME);
  }
  
  public void setProductName(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PRODUCTNAME, "");
    }
    else
    {
      setValue(PRODUCTNAME, value.getId());
    }
  }
  
  public boolean isProductNameWritable()
  {
    return isWritable(PRODUCTNAME);
  }
  
  public boolean isProductNameReadable()
  {
    return isReadable(PRODUCTNAME);
  }
  
  public boolean isProductNameModified()
  {
    return isModified(PRODUCTNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getProductNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PRODUCTNAME).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO> getUnitQualifier()
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO.CLASS, getEnumNames(UNITQUALIFIER));
  }
  
  public java.util.List<String> getUnitQualifierEnumNames()
  {
    return getEnumNames(UNITQUALIFIER);
  }
  
  public void addUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO enumDTO)
  {
    addEnumItem(UNITQUALIFIER, enumDTO.toString());
  }
  
  public void removeUnitQualifier(dss.vector.solutions.irs.InsecticideBrandUnitQualifierDTO enumDTO)
  {
    removeEnumItem(UNITQUALIFIER, enumDTO.toString());
  }
  
  public void clearUnitQualifier()
  {
    clearEnum(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierWritable()
  {
    return isWritable(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierReadable()
  {
    return isReadable(UNITQUALIFIER);
  }
  
  public boolean isUnitQualifierModified()
  {
    return isModified(UNITQUALIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getUnitQualifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(UNITQUALIFIER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getUnitQuantifier()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(UNITQUANTIFIER));
  }
  
  public void setUnitQuantifier(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(UNITQUANTIFIER, "");
    }
    else
    {
      setValue(UNITQUANTIFIER, value.toString());
    }
  }
  
  public boolean isUnitQuantifierWritable()
  {
    return isWritable(UNITQUANTIFIER);
  }
  
  public boolean isUnitQuantifierReadable()
  {
    return isReadable(UNITQUANTIFIER);
  }
  
  public boolean isUnitQuantifierModified()
  {
    return isModified(UNITQUANTIFIER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getUnitQuantifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(UNITQUANTIFIER).getAttributeMdDTO();
  }
  
  public Integer getUnitsPerApplication()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERAPPLICATION));
  }
  
  public void setUnitsPerApplication(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSPERAPPLICATION, "");
    }
    else
    {
      setValue(UNITSPERAPPLICATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsPerApplicationWritable()
  {
    return isWritable(UNITSPERAPPLICATION);
  }
  
  public boolean isUnitsPerApplicationReadable()
  {
    return isReadable(UNITSPERAPPLICATION);
  }
  
  public boolean isUnitsPerApplicationModified()
  {
    return isModified(UNITSPERAPPLICATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getUnitsPerApplicationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSPERAPPLICATION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUseDetail()
  {
    if(getValue(USEDETAIL) == null || getValue(USEDETAIL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(USEDETAIL));
    }
  }
  
  public String getUseDetailId()
  {
    return getValue(USEDETAIL);
  }
  
  public void setUseDetail(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(USEDETAIL, "");
    }
    else
    {
      setValue(USEDETAIL, value.getId());
    }
  }
  
  public boolean isUseDetailWritable()
  {
    return isWritable(USEDETAIL);
  }
  
  public boolean isUseDetailReadable()
  {
    return isReadable(USEDETAIL);
  }
  
  public boolean isUseDetailModified()
  {
    return isModified(USEDETAIL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUseDetailMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(USEDETAIL).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideBrandViewDTO[] insecticides)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideBrandView;"};
    Object[] _parameters = new Object[]{insecticides};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getAll(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getAllActive(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getAllActive", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getControlInterventionInsecticideBrands(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getControlInterventionInsecticideBrands", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getEfficacyAssayInsecticideBrands(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getEfficacyAssayInsecticideBrands", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getIRSInsecticideBrands(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getIRSInsecticideBrands", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getNozzleInsecticideBrands(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getNozzleInsecticideBrands", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideBrandViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (InsecticideBrandViewDTO) dto;
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
