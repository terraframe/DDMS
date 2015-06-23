package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 1833862588)
public abstract class CaseTreatmentStockViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseTreatmentStockView";
  private static final long serialVersionUID = 1833862588;
  
  protected CaseTreatmentStockViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGGREGATEDCASE = "aggregatedCase";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String OUTOFSTOCK = "outOfStock";
  public static java.lang.String TERM = "term";
  public dss.vector.solutions.surveillance.AggregatedCaseDTO getAggregatedCase()
  {
    if(getValue(AGGREGATEDCASE) == null || getValue(AGGREGATEDCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedCaseDTO.get(getRequest(), getValue(AGGREGATEDCASE));
    }
  }
  
  public String getAggregatedCaseId()
  {
    return getValue(AGGREGATEDCASE);
  }
  
  public void setAggregatedCase(dss.vector.solutions.surveillance.AggregatedCaseDTO value)
  {
    if(value == null)
    {
      setValue(AGGREGATEDCASE, "");
    }
    else
    {
      setValue(AGGREGATEDCASE, value.getId());
    }
  }
  
  public boolean isAggregatedCaseWritable()
  {
    return isWritable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseReadable()
  {
    return isReadable(AGGREGATEDCASE);
  }
  
  public boolean isAggregatedCaseModified()
  {
    return isModified(AGGREGATEDCASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAggregatedCaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGGREGATEDCASE).getAttributeMdDTO();
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
  
  public Boolean getOutOfStock()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(OUTOFSTOCK));
  }
  
  public void setOutOfStock(Boolean value)
  {
    if(value == null)
    {
      setValue(OUTOFSTOCK, "");
    }
    else
    {
      setValue(OUTOFSTOCK, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isOutOfStockWritable()
  {
    return isWritable(OUTOFSTOCK);
  }
  
  public boolean isOutOfStockReadable()
  {
    return isReadable(OUTOFSTOCK);
  }
  
  public boolean isOutOfStockModified()
  {
    return isModified(OUTOFSTOCK);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getOutOfStockMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(OUTOFSTOCK).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getTerm()
  {
    if(getValue(TERM) == null || getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TERM));
    }
  }
  
  public String getTermId()
  {
    return getValue(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  public boolean isTermWritable()
  {
    return isWritable(TERM);
  }
  
  public boolean isTermReadable()
  {
    return isReadable(TERM);
  }
  
  public boolean isTermModified()
  {
    return isModified(TERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TERM).getAttributeMdDTO();
  }
  
  public static CaseTreatmentStockViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CaseTreatmentStockViewDTO) dto;
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
