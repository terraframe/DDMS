package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 1318483448)
public abstract class InsecticideBrandLabelDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandLabel";
  private static final long serialVersionUID = 1318483448;
  
  protected InsecticideBrandLabelDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String PRODUCTNAME = "productName";
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
  
  public String getConcentrationQualifier()
  {
    return getValue(CONCENTRATIONQUALIFIER);
  }
  
  public void setConcentrationQualifier(String value)
  {
    if(value == null)
    {
      setValue(CONCENTRATIONQUALIFIER, "");
    }
    else
    {
      setValue(CONCENTRATIONQUALIFIER, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcentrationQualifierMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCENTRATIONQUALIFIER).getAttributeMdDTO();
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
  
  public String getProductName()
  {
    return getValue(PRODUCTNAME);
  }
  
  public void setProductName(String value)
  {
    if(value == null)
    {
      setValue(PRODUCTNAME, "");
    }
    else
    {
      setValue(PRODUCTNAME, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getProductNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PRODUCTNAME).getAttributeMdDTO();
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
