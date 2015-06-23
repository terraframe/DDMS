package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 251631692)
public abstract class PupalContainerAmountViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.PupalContainerAmountView";
  private static final long serialVersionUID = 251631692;
  
  protected PupalContainerAmountViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String CONTAINER = "container";
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
  
  public dss.vector.solutions.entomology.PupalContainerDTO getContainer()
  {
    if(getValue(CONTAINER) == null || getValue(CONTAINER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.PupalContainerDTO.get(getRequest(), getValue(CONTAINER));
    }
  }
  
  public String getContainerId()
  {
    return getValue(CONTAINER);
  }
  
  public void setContainer(dss.vector.solutions.entomology.PupalContainerDTO value)
  {
    if(value == null)
    {
      setValue(CONTAINER, "");
    }
    else
    {
      setValue(CONTAINER, value.getId());
    }
  }
  
  public boolean isContainerWritable()
  {
    return isWritable(CONTAINER);
  }
  
  public boolean isContainerReadable()
  {
    return isReadable(CONTAINER);
  }
  
  public boolean isContainerModified()
  {
    return isModified(CONTAINER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getContainerMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CONTAINER).getAttributeMdDTO();
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
  
  public static PupalContainerAmountViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PupalContainerAmountViewDTO) dto;
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
