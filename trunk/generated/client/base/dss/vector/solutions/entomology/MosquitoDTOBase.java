package dss.vector.solutions.entomology;

@com.terraframe.mojo.business.ClassSignature(hash = 1679468752)
public abstract class MosquitoDTOBase extends dss.vector.solutions.entomology.TrueSpecieEntityDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.Mosquito";
  private static final long serialVersionUID = 1679468752;
  
  protected MosquitoDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String SEX = "sex";
  public static java.lang.String TESTDATE = "testDate";
  public dss.vector.solutions.ontology.TermDTO getGeneration()
  {
    if(getValue(GENERATION) == null || getValue(GENERATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(GENERATION));
    }
  }
  
  public void setGeneration(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value.getId());
    }
  }
  
  public boolean isGenerationWritable()
  {
    return isWritable(GENERATION);
  }
  
  public boolean isGenerationReadable()
  {
    return isReadable(GENERATION);
  }
  
  public boolean isGenerationModified()
  {
    return isModified(GENERATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGenerationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsofemaleWritable()
  {
    return isWritable(ISOFEMALE);
  }
  
  public boolean isIsofemaleReadable()
  {
    return isReadable(ISOFEMALE);
  }
  
  public boolean isIsofemaleModified()
  {
    return isModified(ISOFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOFEMALE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getSex()
  {
    if(getValue(SEX) == null || getValue(SEX).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(SEX));
    }
  }
  
  public void setSex(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value.getId());
    }
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isTestDateWritable()
  {
    return isWritable(TESTDATE);
  }
  
  public boolean isTestDateReadable()
  {
    return isReadable(TESTDATE);
  }
  
  public boolean isTestDateModified()
  {
    return isModified(TESTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TESTDATE).getAttributeMdDTO();
  }
  
  public static final java.io.InputStream exportQueryToCSV(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "exportQueryToCSV", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "exportQueryToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.AssayTestResultDTO[] getTestResults()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "getTestResults", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AssayTestResultDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.AssayTestResultDTO[] getTestResults(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "getTestResults", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AssayTestResultDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoViewDTO getView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "getView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MosquitoViewDTO lockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoViewDTO lockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "lockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO queryEntomology(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{queryXML, config, sortBy, ascending, pageNumber, pageSize};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "queryEntomology", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MosquitoViewDTO unlockView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoViewDTO unlockView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "unlockView", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO xmlToValueQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String xml, java.lang.String[] selectedUniversals, java.lang.Boolean includeGeometry)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{xml, selectedUniversals, includeGeometry};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "xmlToValueQuery", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllBiochemicalResults()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO> getAllBiochemicalResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO> getAllBiochemicalResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO addBiochemicalResults(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO addBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public void removeBiochemicalResults(dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllBiochemicalResults()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  public static void removeAllBiochemicalResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.entomology.assay.BiochemicalTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllInfectivityResults()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO> getAllInfectivityResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO> getAllInfectivityResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.InfectivityTestResultDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.InfectivityTestResultDTO addInfectivityResults(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.InfectivityTestResultDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.InfectivityTestResultDTO addInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.InfectivityTestResultDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public void removeInfectivityResults(dss.vector.solutions.entomology.assay.InfectivityTestResultDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllInfectivityResults()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  public static void removeAllInfectivityResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.entomology.assay.InfectivityTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetSiteResults()
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) getRequest().getChildren(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.ontology.TermDTO> getAllTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.ontology.TermDTO>) clientRequestIF.getChildren(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO> getAllTargetSiteResultsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO>) getRequest().getChildRelationships(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO> getAllTargetSiteResultsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO>) clientRequestIF.getChildRelationships(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO addTargetSiteResults(dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO) getRequest().addChild(this.getId(), child.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO addTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.ontology.TermDTO child)
  {
    return (dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO) clientRequestIF.addChild(id, child.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public void removeTargetSiteResults(dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO relationship)
  {
    getRequest().deleteChild(relationship.getId());
  }
  
  public static void removeTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO relationship)
  {
    clientRequestIF.deleteChild(relationship.getId());
  }
  
  public void removeAllTargetSiteResults()
  {
    getRequest().deleteChildren(this.getId(), dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  public static void removeAllTargetSiteResults(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteChildren(id, dss.vector.solutions.entomology.assay.TargetSiteTestResultDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO> getAllCollections()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO>) getRequest().getParents(this.getId(), dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO> getAllCollections(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO>) clientRequestIF.getParents(id, dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<? extends dss.vector.solutions.entomology.CollectionMosquitoDTO> getAllCollectionsRelationships()
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.CollectionMosquitoDTO>) getRequest().getParentRelationships(this.getId(), dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public static java.util.List<? extends dss.vector.solutions.entomology.CollectionMosquitoDTO> getAllCollectionsRelationships(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    return (java.util.List<? extends dss.vector.solutions.entomology.CollectionMosquitoDTO>) clientRequestIF.getParentRelationships(id, dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  public dss.vector.solutions.entomology.CollectionMosquitoDTO addCollections(dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO parent)
  {
    return (dss.vector.solutions.entomology.CollectionMosquitoDTO) getRequest().addParent(parent.getId(), this.getId(), dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.CollectionMosquitoDTO addCollections(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id, dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO parent)
  {
    return (dss.vector.solutions.entomology.CollectionMosquitoDTO) clientRequestIF.addParent(parent.getId(), id, dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  public void removeCollections(dss.vector.solutions.entomology.CollectionMosquitoDTO relationship)
  {
    getRequest().deleteParent(relationship.getId());
  }
  
  public static void removeCollections(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, dss.vector.solutions.entomology.CollectionMosquitoDTO relationship)
  {
    clientRequestIF.deleteParent(relationship.getId());
  }
  
  public void removeAllCollections()
  {
    getRequest().deleteParents(this.getId(), dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  public static void removeAllCollections(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, String id)
  {
    clientRequestIF.deleteParents(id, dss.vector.solutions.entomology.CollectionMosquitoDTO.CLASS);
  }
  
  public static dss.vector.solutions.entomology.MosquitoDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.MosquitoDTO) dto;
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
  
  public static dss.vector.solutions.entomology.MosquitoQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.MosquitoQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.Mosquito", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.MosquitoDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
