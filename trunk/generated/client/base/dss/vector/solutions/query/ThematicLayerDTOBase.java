package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -763658792)
public abstract class ThematicLayerDTOBase extends dss.vector.solutions.query.LayerDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.ThematicLayer";
  private static final long serialVersionUID = -763658792;
  
  protected ThematicLayerDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ThematicLayerDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String THEMATICVARIABLE = "thematicVariable";
  public static java.lang.String VIEWCREATED = "viewCreated";
  public dss.vector.solutions.query.ThematicVariableDTO getThematicVariable()
  {
    if(getValue(THEMATICVARIABLE) == null || getValue(THEMATICVARIABLE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.ThematicVariableDTO.get(getRequest(), getValue(THEMATICVARIABLE));
    }
  }
  
  public void setThematicVariable(dss.vector.solutions.query.ThematicVariableDTO value)
  {
    if(value == null)
    {
      setValue(THEMATICVARIABLE, "");
    }
    else
    {
      setValue(THEMATICVARIABLE, value.getId());
    }
  }
  
  public boolean isThematicVariableWritable()
  {
    return isWritable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableReadable()
  {
    return isReadable(THEMATICVARIABLE);
  }
  
  public boolean isThematicVariableModified()
  {
    return isModified(THEMATICVARIABLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getThematicVariableMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(THEMATICVARIABLE).getAttributeMdDTO();
  }
  
  public Boolean getViewCreated()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VIEWCREATED));
  }
  
  public void setViewCreated(Boolean value)
  {
    if(value == null)
    {
      setValue(VIEWCREATED, "");
    }
    else
    {
      setValue(VIEWCREATED, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isViewCreatedWritable()
  {
    return isWritable(VIEWCREATED);
  }
  
  public boolean isViewCreatedReadable()
  {
    return isReadable(VIEWCREATED);
  }
  
  public boolean isViewCreatedModified()
  {
    return isModified(VIEWCREATED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getViewCreatedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VIEWCREATED).getAttributeMdDTO();
  }
  
  public final void changeLayerType(java.lang.String thematicLayerType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{thematicLayerType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.ThematicLayerDTO.CLASS, "changeLayerType", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void changeLayerType(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String thematicLayerType)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, thematicLayerType};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.ThematicLayerDTO.CLASS, "changeLayerType", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.query.ThematicLayerDTO updateThematicVariable(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String layerId, dss.vector.solutions.query.ThematicVariableDTO thematicVariable, dss.vector.solutions.query.AbstractCategoryDTO[] categories)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.query.ThematicVariable", "[Ldss.vector.solutions.query.AbstractCategory;"};
    Object[] _parameters = new Object[]{layerId, thematicVariable, categories};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.ThematicLayerDTO.CLASS, "updateThematicVariable", _declaredTypes);
    return (dss.vector.solutions.query.ThematicLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllDefinesCategory()
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO> getAllDefinesCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.AbstractCategoryDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.DefinesCategoriesDTO> getAllDefinesCategoryRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesCategoriesDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.DefinesCategoriesDTO> getAllDefinesCategoryRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.DefinesCategoriesDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  public dss.vector.solutions.query.DefinesCategoriesDTO addDefinesCategory(dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.DefinesCategoriesDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.DefinesCategoriesDTO addDefinesCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.AbstractCategoryDTO child)
  {
    return (dss.vector.solutions.query.DefinesCategoriesDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  public void removeDefinesCategory(dss.vector.solutions.query.DefinesCategoriesDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeDefinesCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.DefinesCategoriesDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllDefinesCategory()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  public static void removeAllDefinesCategory(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.DefinesCategoriesDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.ThematicLayerDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.ThematicLayerDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.query.ThematicLayerQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.ThematicLayerQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.ThematicLayer", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.ThematicLayerDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.ThematicLayerDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.ThematicLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.ThematicLayerDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.ThematicLayerDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.ThematicLayerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
