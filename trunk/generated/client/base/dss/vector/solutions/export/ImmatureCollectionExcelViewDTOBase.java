package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -952675893)
public abstract class ImmatureCollectionExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.ImmatureCollectionExcelView";
  private static final long serialVersionUID = -952675893;
  
  protected ImmatureCollectionExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONTAINERTERM = "containerTerm";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NOTES = "notes";
  public static java.lang.String NUMBERCONTAINERS = "numberContainers";
  public static java.lang.String NUMBERDESTROYED = "numberDestroyed";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERIMMATURES = "numberImmatures";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String NUMBERLARVAE = "numberLarvae";
  public static java.lang.String NUMBERLARVAECOLLECTED = "numberLarvaeCollected";
  public static java.lang.String NUMBERPUPAE = "numberPupae";
  public static java.lang.String NUMBERPUPAECOLLECTED = "numberPupaeCollected";
  public static java.lang.String NUMBERWITHIMMATURES = "numberWithImmatures";
  public static java.lang.String NUMBERWITHLARVAE = "numberWithLarvae";
  public static java.lang.String NUMBERWITHLARVICIDE = "numberWithLarvicide";
  public static java.lang.String NUMBERWITHPUPAE = "numberWithPupae";
  public static java.lang.String NUMBERWITHWATER = "numberWithWater";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TAXON = "taxon";
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
  
  public String getContainerTerm()
  {
    return getValue(CONTAINERTERM);
  }
  
  public void setContainerTerm(String value)
  {
    if(value == null)
    {
      setValue(CONTAINERTERM, "");
    }
    else
    {
      setValue(CONTAINERTERM, value);
    }
  }
  
  public boolean isContainerTermWritable()
  {
    return isWritable(CONTAINERTERM);
  }
  
  public boolean isContainerTermReadable()
  {
    return isReadable(CONTAINERTERM);
  }
  
  public boolean isContainerTermModified()
  {
    return isModified(CONTAINERTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getContainerTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTAINERTERM).getAttributeMdDTO();
  }
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
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
  
  public String getNotes()
  {
    return getValue(NOTES);
  }
  
  public void setNotes(String value)
  {
    if(value == null)
    {
      setValue(NOTES, "");
    }
    else
    {
      setValue(NOTES, value);
    }
  }
  
  public boolean isNotesWritable()
  {
    return isWritable(NOTES);
  }
  
  public boolean isNotesReadable()
  {
    return isReadable(NOTES);
  }
  
  public boolean isNotesModified()
  {
    return isModified(NOTES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getNotesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(NOTES).getAttributeMdDTO();
  }
  
  public Integer getNumberContainers()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERCONTAINERS));
  }
  
  public void setNumberContainers(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERCONTAINERS, "");
    }
    else
    {
      setValue(NUMBERCONTAINERS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberContainersWritable()
  {
    return isWritable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersReadable()
  {
    return isReadable(NUMBERCONTAINERS);
  }
  
  public boolean isNumberContainersModified()
  {
    return isModified(NUMBERCONTAINERS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberContainersMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERCONTAINERS).getAttributeMdDTO();
  }
  
  public Integer getNumberDestroyed()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERDESTROYED));
  }
  
  public void setNumberDestroyed(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERDESTROYED, "");
    }
    else
    {
      setValue(NUMBERDESTROYED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberDestroyedWritable()
  {
    return isWritable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedReadable()
  {
    return isReadable(NUMBERDESTROYED);
  }
  
  public boolean isNumberDestroyedModified()
  {
    return isModified(NUMBERDESTROYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberDestroyedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERDESTROYED).getAttributeMdDTO();
  }
  
  public Integer getNumberExamined()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEREXAMINED));
  }
  
  public void setNumberExamined(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEREXAMINED, "");
    }
    else
    {
      setValue(NUMBEREXAMINED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberExaminedWritable()
  {
    return isWritable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedReadable()
  {
    return isReadable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedModified()
  {
    return isModified(NUMBEREXAMINED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberExaminedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEREXAMINED).getAttributeMdDTO();
  }
  
  public Integer getNumberImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERIMMATURES));
  }
  
  public void setNumberImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERIMMATURES, "");
    }
    else
    {
      setValue(NUMBERIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberImmaturesWritable()
  {
    return isWritable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesReadable()
  {
    return isReadable(NUMBERIMMATURES);
  }
  
  public boolean isNumberImmaturesModified()
  {
    return isModified(NUMBERIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberInhabitants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERINHABITANTS));
  }
  
  public void setNumberInhabitants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERINHABITANTS, "");
    }
    else
    {
      setValue(NUMBERINHABITANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberInhabitantsWritable()
  {
    return isWritable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsReadable()
  {
    return isReadable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsModified()
  {
    return isModified(NUMBERINHABITANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberInhabitantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERINHABITANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAE));
  }
  
  public void setNumberLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAE, "");
    }
    else
    {
      setValue(NUMBERLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeWritable()
  {
    return isWritable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeReadable()
  {
    return isReadable(NUMBERLARVAE);
  }
  
  public boolean isNumberLarvaeModified()
  {
    return isModified(NUMBERLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberLarvaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERLARVAECOLLECTED));
  }
  
  public void setNumberLarvaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERLARVAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERLARVAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberLarvaeCollectedWritable()
  {
    return isWritable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedReadable()
  {
    return isReadable(NUMBERLARVAECOLLECTED);
  }
  
  public boolean isNumberLarvaeCollectedModified()
  {
    return isModified(NUMBERLARVAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberLarvaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERLARVAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAE));
  }
  
  public void setNumberPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAE, "");
    }
    else
    {
      setValue(NUMBERPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeWritable()
  {
    return isWritable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeReadable()
  {
    return isReadable(NUMBERPUPAE);
  }
  
  public boolean isNumberPupaeModified()
  {
    return isModified(NUMBERPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAE).getAttributeMdDTO();
  }
  
  public Integer getNumberPupaeCollected()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPUPAECOLLECTED));
  }
  
  public void setNumberPupaeCollected(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPUPAECOLLECTED, "");
    }
    else
    {
      setValue(NUMBERPUPAECOLLECTED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPupaeCollectedWritable()
  {
    return isWritable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedReadable()
  {
    return isReadable(NUMBERPUPAECOLLECTED);
  }
  
  public boolean isNumberPupaeCollectedModified()
  {
    return isModified(NUMBERPUPAECOLLECTED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberPupaeCollectedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPUPAECOLLECTED).getAttributeMdDTO();
  }
  
  public Integer getNumberWithImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHIMMATURES));
  }
  
  public void setNumberWithImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHIMMATURES, "");
    }
    else
    {
      setValue(NUMBERWITHIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithImmaturesWritable()
  {
    return isWritable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesReadable()
  {
    return isReadable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesModified()
  {
    return isModified(NUMBERWITHIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVAE));
  }
  
  public void setNumberWithLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVAE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvaeWritable()
  {
    return isWritable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeReadable()
  {
    return isReadable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeModified()
  {
    return isModified(NUMBERWITHLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvicide()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVICIDE));
  }
  
  public void setNumberWithLarvicide(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVICIDE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVICIDE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvicideWritable()
  {
    return isWritable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideReadable()
  {
    return isReadable(NUMBERWITHLARVICIDE);
  }
  
  public boolean isNumberWithLarvicideModified()
  {
    return isModified(NUMBERWITHLARVICIDE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvicideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVICIDE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHPUPAE));
  }
  
  public void setNumberWithPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHPUPAE, "");
    }
    else
    {
      setValue(NUMBERWITHPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithPupaeWritable()
  {
    return isWritable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeReadable()
  {
    return isReadable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeModified()
  {
    return isModified(NUMBERWITHPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHPUPAE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithWater()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHWATER));
  }
  
  public void setNumberWithWater(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHWATER, "");
    }
    else
    {
      setValue(NUMBERWITHWATER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithWaterWritable()
  {
    return isWritable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterReadable()
  {
    return isReadable(NUMBERWITHWATER);
  }
  
  public boolean isNumberWithWaterModified()
  {
    return isModified(NUMBERWITHWATER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithWaterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHWATER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPremiseSize()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PREMISESIZE));
  }
  
  public void setPremiseSize(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PREMISESIZE, "");
    }
    else
    {
      setValue(PREMISESIZE, value.toString());
    }
  }
  
  public boolean isPremiseSizeWritable()
  {
    return isWritable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeReadable()
  {
    return isReadable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeModified()
  {
    return isModified(PREMISESIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPremiseSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PREMISESIZE).getAttributeMdDTO();
  }
  
  public String getPremiseType()
  {
    return getValue(PREMISETYPE);
  }
  
  public void setPremiseType(String value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value);
    }
  }
  
  public boolean isPremiseTypeWritable()
  {
    return isWritable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeReadable()
  {
    return isReadable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeModified()
  {
    return isModified(PREMISETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPremiseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PREMISETYPE).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public String getTaxon()
  {
    return getValue(TAXON);
  }
  
  public void setTaxon(String value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value);
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public static ImmatureCollectionExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ImmatureCollectionExcelViewDTO) dto;
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
