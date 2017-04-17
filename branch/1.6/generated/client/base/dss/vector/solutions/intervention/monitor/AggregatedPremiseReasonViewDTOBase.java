package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 1454626717)
public abstract class AggregatedPremiseReasonViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView";
  private static final long serialVersionUID = 1454626717;
  
  protected AggregatedPremiseReasonViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  public static java.lang.String VISIT = "visit";
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
  
  public dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO getVisit()
  {
    if(getValue(VISIT) == null || getValue(VISIT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO.get(getRequest(), getValue(VISIT));
    }
  }
  
  public String getVisitId()
  {
    return getValue(VISIT);
  }
  
  public void setVisit(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitDTO value)
  {
    if(value == null)
    {
      setValue(VISIT, "");
    }
    else
    {
      setValue(VISIT, value.getId());
    }
  }
  
  public boolean isVisitWritable()
  {
    return isWritable(VISIT);
  }
  
  public boolean isVisitReadable()
  {
    return isReadable(VISIT);
  }
  
  public boolean isVisitModified()
  {
    return isModified(VISIT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getVisitMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(VISIT).getAttributeMdDTO();
  }
  
  public static AggregatedPremiseReasonViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedPremiseReasonViewDTO) dto;
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
