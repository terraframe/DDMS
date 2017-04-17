package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -450469372)
public abstract class CaseDiagnosisTypeAmountViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView";
  private static final long serialVersionUID = -450469372;
  
  protected CaseDiagnosisTypeAmountViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String DIAGNOSIS = "diagnosis";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
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
  
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO getDiagnosis()
  {
    if(getValue(DIAGNOSIS) == null || getValue(DIAGNOSIS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO.get(getRequest(), getValue(DIAGNOSIS));
    }
  }
  
  public String getDiagnosisId()
  {
    return getValue(DIAGNOSIS);
  }
  
  public void setDiagnosis(dss.vector.solutions.surveillance.CaseDiagnosisTypeDTO value)
  {
    if(value == null)
    {
      setValue(DIAGNOSIS, "");
    }
    else
    {
      setValue(DIAGNOSIS, value.getId());
    }
  }
  
  public boolean isDiagnosisWritable()
  {
    return isWritable(DIAGNOSIS);
  }
  
  public boolean isDiagnosisReadable()
  {
    return isReadable(DIAGNOSIS);
  }
  
  public boolean isDiagnosisModified()
  {
    return isModified(DIAGNOSIS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getDiagnosisMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DIAGNOSIS).getAttributeMdDTO();
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
  
  public static CaseDiagnosisTypeAmountViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CaseDiagnosisTypeAmountViewDTO) dto;
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
