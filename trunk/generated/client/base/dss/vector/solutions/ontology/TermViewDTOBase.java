package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = 1458092803)
public abstract class TermViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermView";
  private static final long serialVersionUID = 1458092803;
  
  protected TermViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SELECTABLE = "selectable";
  public static java.lang.String TERMID = "termId";
  public static java.lang.String TERMNAME = "termName";
  public static java.lang.String TERMONTOLOGYID = "termOntologyId";
  public Boolean getSelectable()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SELECTABLE));
  }
  
  public void setSelectable(Boolean value)
  {
    if(value == null)
    {
      setValue(SELECTABLE, "");
    }
    else
    {
      setValue(SELECTABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSelectableWritable()
  {
    return isWritable(SELECTABLE);
  }
  
  public boolean isSelectableReadable()
  {
    return isReadable(SELECTABLE);
  }
  
  public boolean isSelectableModified()
  {
    return isModified(SELECTABLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSelectableMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SELECTABLE).getAttributeMdDTO();
  }
  
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public boolean isTermIdWritable()
  {
    return isWritable(TERMID);
  }
  
  public boolean isTermIdReadable()
  {
    return isReadable(TERMID);
  }
  
  public boolean isTermIdModified()
  {
    return isModified(TERMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTermIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMID).getAttributeMdDTO();
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public boolean isTermNameWritable()
  {
    return isWritable(TERMNAME);
  }
  
  public boolean isTermNameReadable()
  {
    return isReadable(TERMNAME);
  }
  
  public boolean isTermNameModified()
  {
    return isModified(TERMNAME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTermNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMNAME).getAttributeMdDTO();
  }
  
  public String getTermOntologyId()
  {
    return getValue(TERMONTOLOGYID);
  }
  
  public void setTermOntologyId(String value)
  {
    if(value == null)
    {
      setValue(TERMONTOLOGYID, "");
    }
    else
    {
      setValue(TERMONTOLOGYID, value);
    }
  }
  
  public boolean isTermOntologyIdWritable()
  {
    return isWritable(TERMONTOLOGYID);
  }
  
  public boolean isTermOntologyIdReadable()
  {
    return isReadable(TERMONTOLOGYID);
  }
  
  public boolean isTermOntologyIdModified()
  {
    return isModified(TERMONTOLOGYID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getTermOntologyIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMONTOLOGYID).getAttributeMdDTO();
  }
  
  public static TermViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TermViewDTO) dto;
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
