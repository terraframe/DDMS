package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 8587476)
public abstract class CasePatientTypeAmountViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CasePatientTypeAmountView";
  private static final long serialVersionUID = 8587476;
  
  protected CasePatientTypeAmountViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ID = "id";
  public static java.lang.String PATIENT = "patient";
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
  
  public dss.vector.solutions.surveillance.CasePatientTypeDTO getPatient()
  {
    if(getValue(PATIENT) == null || getValue(PATIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.CasePatientTypeDTO.get(getRequest(), getValue(PATIENT));
    }
  }
  
  public String getPatientId()
  {
    return getValue(PATIENT);
  }
  
  public void setPatient(dss.vector.solutions.surveillance.CasePatientTypeDTO value)
  {
    if(value == null)
    {
      setValue(PATIENT, "");
    }
    else
    {
      setValue(PATIENT, value.getId());
    }
  }
  
  public boolean isPatientWritable()
  {
    return isWritable(PATIENT);
  }
  
  public boolean isPatientReadable()
  {
    return isReadable(PATIENT);
  }
  
  public boolean isPatientModified()
  {
    return isModified(PATIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPatientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PATIENT).getAttributeMdDTO();
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
  
  public static CasePatientTypeAmountViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (CasePatientTypeAmountViewDTO) dto;
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
