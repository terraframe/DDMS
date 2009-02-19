package org.obofoundry;

public abstract class TypedefDTOBase extends org.obofoundry.ComponentDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "org.obofoundry.Typedef";
  private static final long serialVersionUID = 1229530369087L;
  
  protected TypedefDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TypedefDTOBase(com.terraframe.mojo.transport.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ISANTISYMMETRIC = "isAntiSymmetric";
  public static java.lang.String MDRELATIONSHIP = "mdRelationship";
  public static java.lang.String INVERSEOF = "inverseof";
  public static java.lang.String ISPARENT = "isParent";
  public static java.lang.String ISREFLEXIVE = "isReflexive";
  public static java.lang.String ISCHILD = "isChild";
  public static java.lang.String ISTRANSITIVE = "isTransitive";
  public Boolean getIsAntiSymmetric()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISANTISYMMETRIC));
  }
  
  public void setIsAntiSymmetric(Boolean value)
  {
    if(value == null)
    {
      setValue(ISANTISYMMETRIC, "");
    }
    else
    {
      setValue(ISANTISYMMETRIC, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsAntiSymmetricWritable()
  {
    return isWritable(ISANTISYMMETRIC);
  }
  
  public boolean isIsAntiSymmetricReadable()
  {
    return isReadable(ISANTISYMMETRIC);
  }
  
  public boolean isIsAntiSymmetricModified()
  {
    return isModified(ISANTISYMMETRIC);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsAntiSymmetricMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isAntiSymmetric").getAttributeMdDTO();
  }
  
  public com.terraframe.mojo.system.metadata.MdRelationshipDTO getMdRelationship()
  {
    return com.terraframe.mojo.system.metadata.MdRelationshipDTO.get(getRequest(), getValue(MDRELATIONSHIP));
  }
  
  public void setMdRelationship(com.terraframe.mojo.system.metadata.MdRelationshipDTO value)
  {
    setValue(MDRELATIONSHIP, value.getId());
  }
  
  public boolean isMdRelationshipWritable()
  {
    return isWritable(MDRELATIONSHIP);
  }
  
  public boolean isMdRelationshipReadable()
  {
    return isReadable(MDRELATIONSHIP);
  }
  
  public boolean isMdRelationshipModified()
  {
    return isModified(MDRELATIONSHIP);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMdRelationshipMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("mdRelationship").getAttributeMdDTO();
  }
  
  public org.obofoundry.TypedefDTO getInverseof()
  {
    return org.obofoundry.TypedefDTO.get(getRequest(), getValue(INVERSEOF));
  }
  
  public void setInverseof(org.obofoundry.TypedefDTO value)
  {
    setValue(INVERSEOF, value.getId());
  }
  
  public boolean isInverseofWritable()
  {
    return isWritable(INVERSEOF);
  }
  
  public boolean isInverseofReadable()
  {
    return isReadable(INVERSEOF);
  }
  
  public boolean isInverseofModified()
  {
    return isModified(INVERSEOF);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getInverseofMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("inverseof").getAttributeMdDTO();
  }
  
  public Boolean getIsParent()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISPARENT));
  }
  
  public void setIsParent(Boolean value)
  {
    if(value == null)
    {
      setValue(ISPARENT, "");
    }
    else
    {
      setValue(ISPARENT, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsParentWritable()
  {
    return isWritable(ISPARENT);
  }
  
  public boolean isIsParentReadable()
  {
    return isReadable(ISPARENT);
  }
  
  public boolean isIsParentModified()
  {
    return isModified(ISPARENT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsParentMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isParent").getAttributeMdDTO();
  }
  
  public Boolean getIsReflexive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISREFLEXIVE));
  }
  
  public void setIsReflexive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISREFLEXIVE, "");
    }
    else
    {
      setValue(ISREFLEXIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsReflexiveWritable()
  {
    return isWritable(ISREFLEXIVE);
  }
  
  public boolean isIsReflexiveReadable()
  {
    return isReadable(ISREFLEXIVE);
  }
  
  public boolean isIsReflexiveModified()
  {
    return isModified(ISREFLEXIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsReflexiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isReflexive").getAttributeMdDTO();
  }
  
  public Boolean getIsChild()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISCHILD));
  }
  
  public void setIsChild(Boolean value)
  {
    if(value == null)
    {
      setValue(ISCHILD, "");
    }
    else
    {
      setValue(ISCHILD, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsChildWritable()
  {
    return isWritable(ISCHILD);
  }
  
  public boolean isIsChildReadable()
  {
    return isReadable(ISCHILD);
  }
  
  public boolean isIsChildModified()
  {
    return isModified(ISCHILD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsChildMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isChild").getAttributeMdDTO();
  }
  
  public Boolean getIsTransitive()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISTRANSITIVE));
  }
  
  public void setIsTransitive(Boolean value)
  {
    if(value == null)
    {
      setValue(ISTRANSITIVE, "");
    }
    else
    {
      setValue(ISTRANSITIVE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsTransitiveWritable()
  {
    return isWritable(ISTRANSITIVE);
  }
  
  public boolean isIsTransitiveReadable()
  {
    return isReadable(ISTRANSITIVE);
  }
  
  public boolean isIsTransitiveModified()
  {
    return isModified(ISTRANSITIVE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsTransitiveMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isTransitive").getAttributeMdDTO();
  }
  
  public static TypedefDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.transport.EntityDTO dto = (com.terraframe.mojo.transport.EntityDTO)clientRequest.get(id);
    
    return (TypedefDTO) dto;
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
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static org.obofoundry.TypedefDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Typedef", "lock", _declaredTypes);
    return (org.obofoundry.TypedefDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static org.obofoundry.TypedefDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.transport.MethodMetaData _metadata = new com.terraframe.mojo.transport.MethodMetaData("org.obofoundry.Typedef", "unlock", _declaredTypes);
    return (org.obofoundry.TypedefDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
