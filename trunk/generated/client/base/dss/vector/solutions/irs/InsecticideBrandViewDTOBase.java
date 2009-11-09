package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1104881882)
public abstract class InsecticideBrandViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandView";
  private static final long serialVersionUID = -1104881882;
  
  protected InsecticideBrandViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String BRANDNAME = "brandName";
  public static java.lang.String ENABLED = "enabled";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICDEID = "insecticdeId";
  public static java.lang.String SACHETSPERREFILL = "sachetsPerRefill";
  public static java.lang.String WEIGHT = "weight";
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getActiveIngredientMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AMOUNT).getAttributeMdDTO();
  }
  
  public String getBrandName()
  {
    return getValue(BRANDNAME);
  }
  
  public void setBrandName(String value)
  {
    if(value == null)
    {
      setValue(BRANDNAME, "");
    }
    else
    {
      setValue(BRANDNAME, value);
    }
  }
  
  public boolean isBrandNameWritable()
  {
    return isWritable(BRANDNAME);
  }
  
  public boolean isBrandNameReadable()
  {
    return isReadable(BRANDNAME);
  }
  
  public boolean isBrandNameModified()
  {
    return isModified(BRANDNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getBrandNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BRANDNAME).getAttributeMdDTO();
  }
  
  public Boolean getEnabled()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ENABLED));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getEnabledMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ENABLED).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getInsecticdeIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INSECTICDEID).getAttributeMdDTO();
  }
  
  public Integer getSachetsPerRefill()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SACHETSPERREFILL));
  }
  
  public void setSachetsPerRefill(Integer value)
  {
    if(value == null)
    {
      setValue(SACHETSPERREFILL, "");
    }
    else
    {
      setValue(SACHETSPERREFILL, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSachetsPerRefillWritable()
  {
    return isWritable(SACHETSPERREFILL);
  }
  
  public boolean isSachetsPerRefillReadable()
  {
    return isReadable(SACHETSPERREFILL);
  }
  
  public boolean isSachetsPerRefillModified()
  {
    return isModified(SACHETSPERREFILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSachetsPerRefillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SACHETSPERREFILL).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getWeight()
  {
    return com.terraframe.mojo.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(WEIGHT));
  }
  
  public void setWeight(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(WEIGHT, "");
    }
    else
    {
      setValue(WEIGHT, value.toString());
    }
  }
  
  public boolean isWeightWritable()
  {
    return isWritable(WEIGHT);
  }
  
  public boolean isWeightReadable()
  {
    return isReadable(WEIGHT);
  }
  
  public boolean isWeightModified()
  {
    return isModified(WEIGHT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getWeightMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] applyAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideBrandViewDTO[] insecticides)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideBrandView;"};
    Object[] _parameters = new Object[]{insecticides};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getAll", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO[] getAllActive(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandViewDTO.CLASS, "getAllActive", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideBrandViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
