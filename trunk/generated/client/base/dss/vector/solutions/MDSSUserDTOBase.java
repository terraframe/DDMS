package dss.vector.solutions;

@com.terraframe.mojo.business.ClassSignature(hash = 117801890)
public abstract class MDSSUserDTOBase extends com.terraframe.mojo.system.UsersDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.MDSSUser";
  private static final long serialVersionUID = 117801890;
  
  protected MDSSUserDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MDSSUserDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String DEFAULTMAP = "defaultMap";
  public static java.lang.String DEFAULTSEARCH = "defaultSearch";
  public static java.lang.String GEOROOT = "geoRoot";
  public static java.lang.String PERSON = "person";
  public static java.lang.String ROOTGEOENTITY = "rootGeoEntity";
  public dss.vector.solutions.query.DefaultSavedMapDTO getDefaultMap()
  {
    if(getValue(DEFAULTMAP) == null || getValue(DEFAULTMAP).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.DefaultSavedMapDTO.get(getRequest(), getValue(DEFAULTMAP));
    }
  }
  
  public void setDefaultMap(dss.vector.solutions.query.DefaultSavedMapDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTMAP, "");
    }
    else
    {
      setValue(DEFAULTMAP, value.getId());
    }
  }
  
  public boolean isDefaultMapWritable()
  {
    return isWritable(DEFAULTMAP);
  }
  
  public boolean isDefaultMapReadable()
  {
    return isReadable(DEFAULTMAP);
  }
  
  public boolean isDefaultMapModified()
  {
    return isModified(DEFAULTMAP);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDefaultMapMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTMAP).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.query.SavedSearchDTO getDefaultSearch()
  {
    if(getValue(DEFAULTSEARCH) == null || getValue(DEFAULTSEARCH).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.query.SavedSearchDTO.get(getRequest(), getValue(DEFAULTSEARCH));
    }
  }
  
  public void setDefaultSearch(dss.vector.solutions.query.SavedSearchDTO value)
  {
    if(value == null)
    {
      setValue(DEFAULTSEARCH, "");
    }
    else
    {
      setValue(DEFAULTSEARCH, value.getId());
    }
  }
  
  public boolean isDefaultSearchWritable()
  {
    return isWritable(DEFAULTSEARCH);
  }
  
  public boolean isDefaultSearchReadable()
  {
    return isReadable(DEFAULTSEARCH);
  }
  
  public boolean isDefaultSearchModified()
  {
    return isModified(DEFAULTSEARCH);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getDefaultSearchMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(DEFAULTSEARCH).getAttributeMdDTO();
  }
  
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getGeoRootMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOROOT).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPersonMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PERSON).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getRootGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ROOTGEOENTITY).getAttributeMdDTO();
  }
  
  public static final void changeRootGeoEntity(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoEntityId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{geoEntityId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "changeRootGeoEntity", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void updateRoles(java.lang.String[] assign, java.lang.String[] revoke)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{assign, revoke};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "updateRoles", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void updateRoles(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String[] assign, java.lang.String[] revoke)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "[Ljava.lang.String;"};
    Object[] _parameters = new Object[]{id, assign, revoke};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "updateRoles", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllPersistedQueries()
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO> getAllPersistedQueries(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.SavedSearchDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedQueriesRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO> getAllPersistedQueriesRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.query.PersistsSearchDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public dss.vector.solutions.query.PersistsSearchDTO addPersistedQueries(dss.vector.solutions.query.SavedSearchDTO child)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.query.PersistsSearchDTO addPersistedQueries(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.query.SavedSearchDTO child)
  {
    return (dss.vector.solutions.query.PersistsSearchDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public void removePersistedQueries(dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removePersistedQueries(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.query.PersistsSearchDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllPersistedQueries()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static void removeAllPersistedQueries(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.query.PersistsSearchDTO.CLASS);
  }
  
  public static dss.vector.solutions.MDSSUserDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
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
  
  public static dss.vector.solutions.MDSSUserQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.MDSSUserQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.MDSSUser", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.MDSSUserDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.MDSSUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.MDSSUserDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.MDSSUserDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.MDSSUserDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
