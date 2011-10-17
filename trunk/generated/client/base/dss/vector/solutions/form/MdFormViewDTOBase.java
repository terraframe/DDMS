package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 389389918)
public abstract class MdFormViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.form.MdFormView";
  private static final long serialVersionUID = 389389918;
  
  protected MdFormViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FORMNAME = "formName";
  public static java.lang.String ID = "id";
  public static java.lang.String MDFORMID = "mdFormId";
  public String getFormName()
  {
    return getValue(FORMNAME);
  }
  
  public void setFormName(String value)
  {
    if(value == null)
    {
      setValue(FORMNAME, "");
    }
    else
    {
      setValue(FORMNAME, value);
    }
  }
  
  public boolean isFormNameWritable()
  {
    return isWritable(FORMNAME);
  }
  
  public boolean isFormNameReadable()
  {
    return isReadable(FORMNAME);
  }
  
  public boolean isFormNameModified()
  {
    return isModified(FORMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFormNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FORMNAME).getAttributeMdDTO();
  }
  
  public String getMdFormId()
  {
    return getValue(MDFORMID);
  }
  
  public void setMdFormId(String value)
  {
    if(value == null)
    {
      setValue(MDFORMID, "");
    }
    else
    {
      setValue(MDFORMID, value);
    }
  }
  
  public boolean isMdFormIdWritable()
  {
    return isWritable(MDFORMID);
  }
  
  public boolean isMdFormIdReadable()
  {
    return isReadable(MDFORMID);
  }
  
  public boolean isMdFormIdModified()
  {
    return isModified(MDFORMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMdFormIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MDFORMID).getAttributeMdDTO();
  }
  
  public static MdFormViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MdFormViewDTO) dto;
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