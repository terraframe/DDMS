package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -322655229)
public abstract class MDSSUserDTOBase extends com.runwaysdk.system.UsersDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.MDSSUser";
  private static final long serialVersionUID = -322655229;
  
  protected MDSSUserDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MDSSUserDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOROOT = "geoRoot";
  public static java.lang.String PERSON = "person";
  public static java.lang.String ROOTGEOENTITY = "rootGeoEntity";
  public String getGeoRoot()
  {
    return getValue(GEOROOT);
  }
  
  public void setGeoRoot(String value)
  {
    if(value == null)
    {
      setValue(GEOROOT, "");
    }
    else
    {
      setValue(GEOROOT, value);
    }
  }
  
  public boolean isGeoRootWritable()
  {
    return isWritable(GEOROOT);
  }
  
  public boolean isGeoRootReadable()
  {
    return isReadable(GEOROOT);
  }
  
  public boolean isGeoRootModified()
  {
    return isModified(GEOROOT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoRootMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOROOT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.PersonDTO getPerson()
  {
    if(getValue(PERSON) == null || getValue(PERSON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.PersonDTO.get(getRequest(), getValue(PERSON));
    }
  }
  
  public String getPersonId()
  {
    return getValue(PERSON);
  }
  
  public void setPerson(dss.vector.solutions.PersonDTO value)
  {
    if(value == null)
    {
      setValue(PERSON, "");
    }
    else
    {
      setValue(PERSON, value.getId());
    }
  }
  
  public boolean isPersonWritable()
  {
    return isWritable(PERSON);
  }
  
  public boolean isPersonReadable()
  {
    return isReadable(PERSON);
  }
  
  public boolean isPersonModified()
  {
    return isModified(PERSON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPersonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERSON).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getRootGeoEntity()
  {
    if(getValue(ROOTGEOENTITY) == null || getValue(ROOTGEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(ROOTGEOENTITY));
    }
  }
  
  public String getRootGeoEntityId()
  {
    return getValue(ROOTGEOENTITY);
  }
  
  public void setRootGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(ROOTGEOENTITY, "");
    }
    else
    {
      setValue(ROOTGEOENTITY, value.getId());
    }
  }
  
  public boolean isRootGeoEntityWritable()
  {
    return isWritable(ROOTGEOENTITY);
  }
  
  public boolean isRootGeoEntityReadable()
  {
    return isReadable(ROOTGEOENTITY);
  }
  
  public boolean isRootGeoEntityModified()
  {
    return isModified(ROOTGEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getRootGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOTGEOENTITY).getAttributeMdDTO();
  }
  
  public static final java.lang.Boolean canDeleteAll(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "canDeleteAll", _declaredTypes);
    return (java.lang.Boolean) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void changeDisease(java.lang.String diseaseName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{diseaseName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "changeDisease", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void changeDisease(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String diseaseName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{id, diseaseName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "changeDisease", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final void changeRootGeoEntity(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoEntityId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoEntityId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "changeRootGeoEntity", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.lang.String getDiseaseName()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "getDiseaseName", _declaredTypes);
    return (java.lang.String) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.lang.String getDiseaseName(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "getDiseaseName", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateRoles(java.lang.String[] assign, java.lang.String[] revoke)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{assign, revoke};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "updateRoles", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateRoles(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String[] assign, java.lang.String[] revoke)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, assign, revoke};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "updateRoles", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllPersistedQueries()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllPersistedQueries(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedQueriesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedQueriesRelationships(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public dss.vector.solutions.query.PersistsSearchDTO addPersistedQueries(dss.vector.solutions.query.SavedSearchDTO child)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.PersistsSearchDTO addPersistedQueries(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedSearchDTO child)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public void removePersistedQueries(dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removePersistedQueries(com.runwaysdk.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllPersistedQueries()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static void removeAllPersistedQueries(com.runwaysdk.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.MDSSUserDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.MDSSUserDTO) dto;
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
  
  public static dss.vector.solutions.MDSSUserQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.MDSSUserQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.MDSSUserDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.MDSSUserDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.MDSSUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.MDSSUserDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.MDSSUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
