package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -383238468)
public abstract class MosquitoCollectionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionView";
  private static final long serialVersionUID = -383238468;
  
  protected MosquitoCollectionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ABUNDANCE = "abundance";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public static java.lang.String COLLECTIONMETHODLABEL = "collectionMethodLabel";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String GEOENTITYLABEL = "geoEntityLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public Boolean getAbundance()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ABUNDANCE));
  }
  
  public void setAbundance(Boolean value)
  {
    if(value == null)
    {
      setValue(ABUNDANCE, "");
    }
    else
    {
      setValue(ABUNDANCE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAbundanceWritable()
  {
    return isWritable(ABUNDANCE);
  }
  
  public boolean isAbundanceReadable()
  {
    return isReadable(ABUNDANCE);
  }
  
  public boolean isAbundanceModified()
  {
    return isModified(ABUNDANCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getAbundanceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ABUNDANCE).getAttributeMdDTO();
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCollectionDateWritable()
  {
    return isWritable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateReadable()
  {
    return isReadable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateModified()
  {
    return isModified(COLLECTIONDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCollectionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COLLECTIONDATE).getAttributeMdDTO();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionMethod()
  {
    if(getValue(COLLECTIONMETHOD) == null || getValue(COLLECTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONMETHOD));
    }
  }
  
  public void setCollectionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value.getId());
    }
  }
  
  public boolean isCollectionMethodWritable()
  {
    return isWritable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodReadable()
  {
    return isReadable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodModified()
  {
    return isModified(COLLECTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public String getCollectionMethodLabel()
  {
    return getValue(COLLECTIONMETHODLABEL);
  }
  
  public void setCollectionMethodLabel(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHODLABEL, "");
    }
    else
    {
      setValue(COLLECTIONMETHODLABEL, value);
    }
  }
  
  public boolean isCollectionMethodLabelWritable()
  {
    return isWritable(COLLECTIONMETHODLABEL);
  }
  
  public boolean isCollectionMethodLabelReadable()
  {
    return isReadable(COLLECTIONMETHODLABEL);
  }
  
  public boolean isCollectionMethodLabelModified()
  {
    return isModified(COLLECTIONMETHODLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionMethodLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONMETHODLABEL).getAttributeMdDTO();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public String getGeoEntityLabel()
  {
    return getValue(GEOENTITYLABEL);
  }
  
  public void setGeoEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(GEOENTITYLABEL, "");
    }
    else
    {
      setValue(GEOENTITYLABEL, value);
    }
  }
  
  public boolean isGeoEntityLabelWritable()
  {
    return isWritable(GEOENTITYLABEL);
  }
  
  public boolean isGeoEntityLabelReadable()
  {
    return isReadable(GEOENTITYLABEL);
  }
  
  public boolean isGeoEntityLabelModified()
  {
    return isModified(GEOENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGeoEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GEOENTITYLABEL).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.LifeStageDTO> getLifeStage()
  {
    return (java.util.List<dss.vector.solutions.entomology.LifeStageDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.entomology.LifeStageDTO.CLASS, getEnumNames(LIFESTAGE));
  }
  
  public java.util.List<String> getLifeStageEnumNames()
  {
    return getEnumNames(LIFESTAGE);
  }
  
  public void addLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    addEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void removeLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    removeEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void clearLifeStage()
  {
    clearEnum(LIFESTAGE);
  }
  
  public boolean isLifeStageWritable()
  {
    return isWritable(LIFESTAGE);
  }
  
  public boolean isLifeStageReadable()
  {
    return isReadable(LIFESTAGE);
  }
  
  public boolean isLifeStageModified()
  {
    return isModified(LIFESTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLifeStageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO[] applyAll(dss.vector.solutions.entomology.SubCollectionViewDTO[] subCollections)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.SubCollectionView;"};
    Object[] _parameters = new Object[]{subCollections};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.entomology.SubCollectionViewDTO[] subCollections)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.entomology.SubCollectionView;"};
    Object[] _parameters = new Object[]{id, subCollections};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO getAdultDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getAdultDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO getAdultDoseAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getAdultDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] getBiochemicalAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getBiochemicalAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.BiochemicalAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] getBiochemicalAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getBiochemicalAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.BiochemicalAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.MosquitoCollectionViewDTO collection)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.MosquitoCollectionView"};
    Object[] _parameters = new Object[]{collection};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.InfectionAssayViewDTO[] getInfectionAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.InfectionAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.InfectionAssayViewDTO[] getInfectionAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.InfectionAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO getKnockDownAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getKnockDownAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO getKnockDownAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getKnockDownAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO getLarvaeDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getLarvaeDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO getLarvaeDoseAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getLarvaeDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MolecularAssayViewDTO[] getMolecularAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMolecularAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.MolecularAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MolecularAssayViewDTO[] getMolecularAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMolecularAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.MolecularAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[] getPooledInfectionAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getPooledInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[] getPooledInfectionAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getPooledInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO[] getSubCollections()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getSubCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO[] getSubCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getSubCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getViewByCollectionId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String collectionId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{collectionId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getViewByCollectionId", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO searchCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "searchCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO searchCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO collection, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.SearchMosquitoCollectionView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{collection, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "searchCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MosquitoCollectionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoCollectionViewDTO) dto;
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
