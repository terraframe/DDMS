package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 2080243509)
public abstract class MenuItemViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MenuItemView";
  private static final long serialVersionUID = 2080243509;
  
  protected MenuItemViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DISEASE = "disease";
  public static java.lang.String ID = "id";
  public static java.lang.String MENUITEMID = "menuItemId";
  public static java.lang.String TERMDISPLAY = "termDisplay";
  public static java.lang.String URLDISPLAY = "urlDisplay";
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.general.DiseaseDTO> getDisease()
  {
    return (java.util.List<dss.vector.solutions.general.DiseaseDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.general.DiseaseDTO.CLASS, getEnumNames(DISEASE));
  }
  
  public java.util.List<String> getDiseaseEnumNames()
  {
    return getEnumNames(DISEASE);
  }
  
  public void addDisease(dss.vector.solutions.general.DiseaseDTO enumDTO)
  {
    addEnumItem(DISEASE, enumDTO.toString());
  }
  
  public void removeDisease(dss.vector.solutions.general.DiseaseDTO enumDTO)
  {
    removeEnumItem(DISEASE, enumDTO.toString());
  }
  
  public void clearDisease()
  {
    clearEnum(DISEASE);
  }
  
  public boolean isDiseaseWritable()
  {
    return isWritable(DISEASE);
  }
  
  public boolean isDiseaseReadable()
  {
    return isReadable(DISEASE);
  }
  
  public boolean isDiseaseModified()
  {
    return isModified(DISEASE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getDiseaseMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(DISEASE).getAttributeMdDTO();
  }
  
  public String getMenuItemId()
  {
    return getValue(MENUITEMID);
  }
  
  public void setMenuItemId(String value)
  {
    if(value == null)
    {
      setValue(MENUITEMID, "");
    }
    else
    {
      setValue(MENUITEMID, value);
    }
  }
  
  public boolean isMenuItemIdWritable()
  {
    return isWritable(MENUITEMID);
  }
  
  public boolean isMenuItemIdReadable()
  {
    return isReadable(MENUITEMID);
  }
  
  public boolean isMenuItemIdModified()
  {
    return isModified(MENUITEMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMenuItemIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MENUITEMID).getAttributeMdDTO();
  }
  
  public String getTermDisplay()
  {
    return getValue(TERMDISPLAY);
  }
  
  public void setTermDisplay(String value)
  {
    if(value == null)
    {
      setValue(TERMDISPLAY, "");
    }
    else
    {
      setValue(TERMDISPLAY, value);
    }
  }
  
  public boolean isTermDisplayWritable()
  {
    return isWritable(TERMDISPLAY);
  }
  
  public boolean isTermDisplayReadable()
  {
    return isReadable(TERMDISPLAY);
  }
  
  public boolean isTermDisplayModified()
  {
    return isModified(TERMDISPLAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getTermDisplayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(TERMDISPLAY).getAttributeMdDTO();
  }
  
  public String getUrlDisplay()
  {
    return getValue(URLDISPLAY);
  }
  
  public void setUrlDisplay(String value)
  {
    if(value == null)
    {
      setValue(URLDISPLAY, "");
    }
    else
    {
      setValue(URLDISPLAY, value);
    }
  }
  
  public boolean isUrlDisplayWritable()
  {
    return isWritable(URLDISPLAY);
  }
  
  public boolean isUrlDisplayReadable()
  {
    return isReadable(URLDISPLAY);
  }
  
  public boolean isUrlDisplayModified()
  {
    return isModified(URLDISPLAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getUrlDisplayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(URLDISPLAY).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.general.MenuItemViewQueryDTO getViewsForDisease(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.general.MenuItemViewDTO.CLASS, "getViewsForDisease", _declaredTypes);
    return (dss.vector.solutions.general.MenuItemViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MenuItemViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MenuItemViewDTO) dto;
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
