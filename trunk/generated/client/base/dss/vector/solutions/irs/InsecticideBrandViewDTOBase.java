package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -1577919943)
public abstract class InsecticideBrandViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandView";
  private static final long serialVersionUID = -1577919943;
  
  protected InsecticideBrandViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public Integer getAmount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAmountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AMOUNT).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBrandNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BRANDNAME).getAttributeMdDTO();
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
  
  public Integer getSachetsPerRefill()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SACHETSPERREFILL));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getSachetsPerRefillMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SACHETSPERREFILL).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getWeight()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(WEIGHT));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WEIGHT).getAttributeMdDTO();
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
