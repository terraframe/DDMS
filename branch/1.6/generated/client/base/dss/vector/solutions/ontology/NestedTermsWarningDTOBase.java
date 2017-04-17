package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = 331218211)
public abstract class NestedTermsWarningDTOBase extends com.runwaysdk.business.WarningDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.NestedTermsWarning";
  private static final long serialVersionUID = 331218211;
  
  public NestedTermsWarningDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String NESTEDTERMS = "nestedTerms";
  public String getNestedTerms()
  {
    return getValue(NESTEDTERMS);
  }
  
  public void setNestedTerms(String value)
  {
    if(value == null)
    {
      setValue(NESTEDTERMS, "");
    }
    else
    {
      setValue(NESTEDTERMS, value);
    }
  }
  
  public boolean isNestedTermsWritable()
  {
    return isWritable(NESTEDTERMS);
  }
  
  public boolean isNestedTermsReadable()
  {
    return isReadable(NESTEDTERMS);
  }
  
  public boolean isNestedTermsModified()
  {
    return isModified(NESTEDTERMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getNestedTermsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(NESTEDTERMS).getAttributeMdDTO();
  }
  
}
