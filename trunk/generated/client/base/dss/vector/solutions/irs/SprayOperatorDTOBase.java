package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1934368454)
public abstract class SprayOperatorDTOBase extends dss.vector.solutions.irs.TargeterDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayOperator";
  private static final long serialVersionUID = 1934368454;
  
  protected SprayOperatorDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected SprayOperatorDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OPERATORID = "operatorId";
  public static java.lang.String PERSON = "person";
  public String getOperatorId()
  {
    return getValue(OPERATORID);
  }
  
  public void setOperatorId(String value)
  {
    if(value == null)
    {
      setValue(OPERATORID, "");
    }
    else
    {
      setValue(OPERATORID, value);
    }
  }
  
  public boolean isOperatorIdWritable()
  {
    return isWritable(OPERATORID);
  }
  
  public boolean isOperatorIdReadable()
  {
    return isReadable(OPERATORID);
  }
  
  public boolean isOperatorIdModified()
  {
    return isModified(OPERATORID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOperatorIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OPERATORID).getAttributeMdDTO();
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
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllSprayTeam()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO> getAllSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.SprayTeamDTO>) clientRequestIF.getParents(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.irs.InTeamDTO> getAllSprayTeamRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.irs.InTeamDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public dss.vector.solutions.irs.InTeamDTO addSprayTeam(dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.InTeamDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.InTeamDTO addSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.irs.SprayTeamDTO parent)
  {
    return (dss.vector.solutions.irs.InTeamDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public void removeSprayTeam(dss.vector.solutions.irs.InTeamDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.irs.InTeamDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllSprayTeam()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static void removeAllSprayTeam(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.irs.InTeamDTO.CLASS);
  }
  
  public static dss.vector.solutions.irs.SprayOperatorDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.irs.SprayOperatorDTO) dto;
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
  
  public static dss.vector.solutions.irs.SprayOperatorQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.irs.SprayOperatorQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.irs.SprayOperator", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.irs.SprayOperatorDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayOperatorDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.irs.SprayOperatorDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.SprayOperatorDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.irs.SprayOperatorDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
