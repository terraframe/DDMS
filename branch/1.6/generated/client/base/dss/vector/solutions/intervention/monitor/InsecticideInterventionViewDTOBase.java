package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 166255556)
public abstract class InsecticideInterventionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.InsecticideInterventionView";
  private static final long serialVersionUID = 166255556;
  
  protected InsecticideInterventionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String INSECTICIDE = "insecticide";
  public static java.lang.String INTERVENTION = "intervention";
  public static java.lang.String INTERVENTIONMETHOD = "interventionMethod";
  public static java.lang.String QUANTITY = "quantity";
  public static java.lang.String UNIT = "unit";
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
  
  public dss.vector.solutions.irs.InsecticideBrandDTO getInsecticide()
  {
    if(getValue(INSECTICIDE) == null || getValue(INSECTICIDE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(INSECTICIDE));
    }
  }
  
  public String getInsecticideId()
  {
    return getValue(INSECTICIDE);
  }
  
  public void setInsecticide(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDE, "");
    }
    else
    {
      setValue(INSECTICIDE, value.getId());
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ControlInterventionDTO getIntervention()
  {
    if(getValue(INTERVENTION) == null || getValue(INTERVENTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ControlInterventionDTO.get(getRequest(), getValue(INTERVENTION));
    }
  }
  
  public String getInterventionId()
  {
    return getValue(INTERVENTION);
  }
  
  public void setIntervention(dss.vector.solutions.intervention.monitor.ControlInterventionDTO value)
  {
    if(value == null)
    {
      setValue(INTERVENTION, "");
    }
    else
    {
      setValue(INTERVENTION, value.getId());
    }
  }
  
  public boolean isInterventionWritable()
  {
    return isWritable(INTERVENTION);
  }
  
  public boolean isInterventionReadable()
  {
    return isReadable(INTERVENTION);
  }
  
  public boolean isInterventionModified()
  {
    return isModified(INTERVENTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInterventionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INTERVENTION).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getInterventionMethod()
  {
    if(getValue(INTERVENTIONMETHOD) == null || getValue(INTERVENTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(INTERVENTIONMETHOD));
    }
  }
  
  public String getInterventionMethodId()
  {
    return getValue(INTERVENTIONMETHOD);
  }
  
  public void setInterventionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(INTERVENTIONMETHOD, "");
    }
    else
    {
      setValue(INTERVENTIONMETHOD, value.getId());
    }
  }
  
  public boolean isInterventionMethodWritable()
  {
    return isWritable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodReadable()
  {
    return isReadable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodModified()
  {
    return isModified(INTERVENTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInterventionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INTERVENTIONMETHOD).getAttributeMdDTO();
  }
  
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getUnit()
  {
    if(getValue(UNIT) == null || getValue(UNIT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(UNIT));
    }
  }
  
  public String getUnitId()
  {
    return getValue(UNIT);
  }
  
  public void setUnit(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(UNIT, "");
    }
    else
    {
      setValue(UNIT, value.getId());
    }
  }
  
  public boolean isUnitWritable()
  {
    return isWritable(UNIT);
  }
  
  public boolean isUnitReadable()
  {
    return isReadable(UNIT);
  }
  
  public boolean isUnitModified()
  {
    return isModified(UNIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getUnitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(UNIT).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.irs.InsecticideBrandViewDTO getInsecticideView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO.CLASS, "getInsecticideView", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideBrandViewDTO getInsecticideView(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO.CLASS, "getInsecticideView", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideBrandViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideInterventionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (InsecticideInterventionViewDTO) dto;
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
