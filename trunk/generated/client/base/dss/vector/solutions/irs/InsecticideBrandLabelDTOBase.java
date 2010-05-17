package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1676370670)
public abstract class InsecticideBrandLabelDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandLabel";
  private static final long serialVersionUID = 1676370670;
  
  protected InsecticideBrandLabelDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String BRANDNAME = "brandName";
  public static java.lang.String CONCENTRATION = "concentration";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public String getActiveIngredient()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(String value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
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
  
  public Integer getConcentration()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(CONCENTRATION));
  }
  
  public void setConcentration(Integer value)
  {
    if(value == null)
    {
      setValue(CONCENTRATION, "");
    }
    else
    {
      setValue(CONCENTRATION, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isConcentrationWritable()
  {
    return isWritable(CONCENTRATION);
  }
  
  public boolean isConcentrationReadable()
  {
    return isReadable(CONCENTRATION);
  }
  
  public boolean isConcentrationModified()
  {
    return isModified(CONCENTRATION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getConcentrationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(CONCENTRATION).getAttributeMdDTO();
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
  
  public static final dss.vector.solutions.irs.InsecticideBrandLabelDTO getLabel(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideBrandLabelDTO.CLASS, "getLabel", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandLabelDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideBrandLabelDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (InsecticideBrandLabelDTO) dto;
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
