package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -93714755)
public abstract class TermSynonymViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermSynonymView";
  private static final long serialVersionUID = -93714755;
  
  protected TermSynonymViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public static java.lang.String TERMSYNONYMID = "termSynonymId";
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  public boolean isSynonymNameWritable()
  {
    return isWritable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameReadable()
  {
    return isReadable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameModified()
  {
    return isModified(SYNONYMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMNAME).getAttributeMdDTO();
  }
  
  public String getTermSynonymId()
  {
    return getValue(TERMSYNONYMID);
  }
  
  public void setTermSynonymId(String value)
  {
    if(value == null)
    {
      setValue(TERMSYNONYMID, "");
    }
    else
    {
      setValue(TERMSYNONYMID, value);
    }
  }
  
  public boolean isTermSynonymIdWritable()
  {
    return isWritable(TERMSYNONYMID);
  }
  
  public boolean isTermSynonymIdReadable()
  {
    return isReadable(TERMSYNONYMID);
  }
  
  public boolean isTermSynonymIdModified()
  {
    return isModified(TERMSYNONYMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermSynonymIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMSYNONYMID).getAttributeMdDTO();
  }
  
  public static TermSynonymViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TermSynonymViewDTO) dto;
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
