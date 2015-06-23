package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1923468739)
public abstract class PupalCollectionExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.PupalCollectionExcelView";
  private static final long serialVersionUID = 1923468739;
  
  protected PupalCollectionExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONTAINERID = "containerId";
  public static java.lang.String CONTAINERLENGTH = "containerLength";
  public static java.lang.String CONTAINERTYPE = "containerType";
  public static java.lang.String DIAMETER = "diameter";
  public static java.lang.String DRAWDOWNFREQUENCY = "drawdownFrequency";
  public static java.lang.String DRAWDOWNPERCENT = "drawdownPercent";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String FILLFREQUENCY = "fillFrequency";
  public static java.lang.String FILLMETHOD = "fillMethod";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String HEIGHT = "height";
  public static java.lang.String ID = "id";
  public static java.lang.String LID = "lid";
  public static java.lang.String NOTES = "notes";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String OPENINGDIAMETER = "openingDiameter";
  public static java.lang.String OPENINGLENGTH = "openingLength";
  public static java.lang.String OPENINGWIDTH = "openingWidth";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String ROOF = "roof";
  public static java.lang.String SHADING = "shading";
  public static java.lang.String SHAPE = "shape";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String WIDTH = "width";
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
  
  public String getContainerId()
  {
    return getValue(CONTAINERID);
  }
  
  public void setContainerId(String value)
  {
    if(value == null)
    {
      setValue(CONTAINERID, "");
    }
    else
    {
      setValue(CONTAINERID, value);
    }
  }
  
  public boolean isContainerIdWritable()
  {
    return isWritable(CONTAINERID);
  }
  
  public boolean isContainerIdReadable()
  {
    return isReadable(CONTAINERID);
  }
  
  public boolean isContainerIdModified()
  {
    return isModified(CONTAINERID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getContainerIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTAINERID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getContainerLength()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(CONTAINERLENGTH));
  }
  
  public void setContainerLength(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(CONTAINERLENGTH, "");
    }
    else
    {
      setValue(CONTAINERLENGTH, value.toString());
    }
  }
  
  public boolean isContainerLengthWritable()
  {
    return isWritable(CONTAINERLENGTH);
  }
  
  public boolean isContainerLengthReadable()
  {
    return isReadable(CONTAINERLENGTH);
  }
  
  public boolean isContainerLengthModified()
  {
    return isModified(CONTAINERLENGTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getContainerLengthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONTAINERLENGTH).getAttributeMdDTO();
  }
  
  public String getContainerType()
  {
    return getValue(CONTAINERTYPE);
  }
  
  public void setContainerType(String value)
  {
    if(value == null)
    {
      setValue(CONTAINERTYPE, "");
    }
    else
    {
      setValue(CONTAINERTYPE, value);
    }
  }
  
  public boolean isContainerTypeWritable()
  {
    return isWritable(CONTAINERTYPE);
  }
  
  public boolean isContainerTypeReadable()
  {
    return isReadable(CONTAINERTYPE);
  }
  
  public boolean isContainerTypeModified()
  {
    return isModified(CONTAINERTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getContainerTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONTAINERTYPE).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getDiameter()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(DIAMETER));
  }
  
  public void setDiameter(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(DIAMETER, "");
    }
    else
    {
      setValue(DIAMETER, value.toString());
    }
  }
  
  public boolean isDiameterWritable()
  {
    return isWritable(DIAMETER);
  }
  
  public boolean isDiameterReadable()
  {
    return isReadable(DIAMETER);
  }
  
  public boolean isDiameterModified()
  {
    return isModified(DIAMETER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getDiameterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(DIAMETER).getAttributeMdDTO();
  }
  
  public String getDrawdownFrequency()
  {
    return getValue(DRAWDOWNFREQUENCY);
  }
  
  public void setDrawdownFrequency(String value)
  {
    if(value == null)
    {
      setValue(DRAWDOWNFREQUENCY, "");
    }
    else
    {
      setValue(DRAWDOWNFREQUENCY, value);
    }
  }
  
  public boolean isDrawdownFrequencyWritable()
  {
    return isWritable(DRAWDOWNFREQUENCY);
  }
  
  public boolean isDrawdownFrequencyReadable()
  {
    return isReadable(DRAWDOWNFREQUENCY);
  }
  
  public boolean isDrawdownFrequencyModified()
  {
    return isModified(DRAWDOWNFREQUENCY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getDrawdownFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(DRAWDOWNFREQUENCY).getAttributeMdDTO();
  }
  
  public Integer getDrawdownPercent()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(DRAWDOWNPERCENT));
  }
  
  public void setDrawdownPercent(Integer value)
  {
    if(value == null)
    {
      setValue(DRAWDOWNPERCENT, "");
    }
    else
    {
      setValue(DRAWDOWNPERCENT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isDrawdownPercentWritable()
  {
    return isWritable(DRAWDOWNPERCENT);
  }
  
  public boolean isDrawdownPercentReadable()
  {
    return isReadable(DRAWDOWNPERCENT);
  }
  
  public boolean isDrawdownPercentModified()
  {
    return isModified(DRAWDOWNPERCENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getDrawdownPercentMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(DRAWDOWNPERCENT).getAttributeMdDTO();
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
  
  public String getFillFrequency()
  {
    return getValue(FILLFREQUENCY);
  }
  
  public void setFillFrequency(String value)
  {
    if(value == null)
    {
      setValue(FILLFREQUENCY, "");
    }
    else
    {
      setValue(FILLFREQUENCY, value);
    }
  }
  
  public boolean isFillFrequencyWritable()
  {
    return isWritable(FILLFREQUENCY);
  }
  
  public boolean isFillFrequencyReadable()
  {
    return isReadable(FILLFREQUENCY);
  }
  
  public boolean isFillFrequencyModified()
  {
    return isModified(FILLFREQUENCY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFillFrequencyMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILLFREQUENCY).getAttributeMdDTO();
  }
  
  public String getFillMethod()
  {
    return getValue(FILLMETHOD);
  }
  
  public void setFillMethod(String value)
  {
    if(value == null)
    {
      setValue(FILLMETHOD, "");
    }
    else
    {
      setValue(FILLMETHOD, value);
    }
  }
  
  public boolean isFillMethodWritable()
  {
    return isWritable(FILLMETHOD);
  }
  
  public boolean isFillMethodReadable()
  {
    return isReadable(FILLMETHOD);
  }
  
  public boolean isFillMethodModified()
  {
    return isModified(FILLMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFillMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILLMETHOD).getAttributeMdDTO();
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
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
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
  
  public java.math.BigDecimal getHeight()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(HEIGHT));
  }
  
  public void setHeight(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(HEIGHT, "");
    }
    else
    {
      setValue(HEIGHT, value.toString());
    }
  }
  
  public boolean isHeightWritable()
  {
    return isWritable(HEIGHT);
  }
  
  public boolean isHeightReadable()
  {
    return isReadable(HEIGHT);
  }
  
  public boolean isHeightModified()
  {
    return isModified(HEIGHT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getHeightMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(HEIGHT).getAttributeMdDTO();
  }
  
  public String getLid()
  {
    return getValue(LID);
  }
  
  public void setLid(String value)
  {
    if(value == null)
    {
      setValue(LID, "");
    }
    else
    {
      setValue(LID, value);
    }
  }
  
  public boolean isLidWritable()
  {
    return isWritable(LID);
  }
  
  public boolean isLidReadable()
  {
    return isReadable(LID);
  }
  
  public boolean isLidModified()
  {
    return isModified(LID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLidMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LID).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getOpeningDiameter()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGDIAMETER));
  }
  
  public void setOpeningDiameter(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGDIAMETER, "");
    }
    else
    {
      setValue(OPENINGDIAMETER, value.toString());
    }
  }
  
  public boolean isOpeningDiameterWritable()
  {
    return isWritable(OPENINGDIAMETER);
  }
  
  public boolean isOpeningDiameterReadable()
  {
    return isReadable(OPENINGDIAMETER);
  }
  
  public boolean isOpeningDiameterModified()
  {
    return isModified(OPENINGDIAMETER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningDiameterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGDIAMETER).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpeningLength()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGLENGTH));
  }
  
  public void setOpeningLength(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGLENGTH, "");
    }
    else
    {
      setValue(OPENINGLENGTH, value.toString());
    }
  }
  
  public boolean isOpeningLengthWritable()
  {
    return isWritable(OPENINGLENGTH);
  }
  
  public boolean isOpeningLengthReadable()
  {
    return isReadable(OPENINGLENGTH);
  }
  
  public boolean isOpeningLengthModified()
  {
    return isModified(OPENINGLENGTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningLengthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGLENGTH).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getOpeningWidth()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(OPENINGWIDTH));
  }
  
  public void setOpeningWidth(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(OPENINGWIDTH, "");
    }
    else
    {
      setValue(OPENINGWIDTH, value.toString());
    }
  }
  
  public boolean isOpeningWidthWritable()
  {
    return isWritable(OPENINGWIDTH);
  }
  
  public boolean isOpeningWidthReadable()
  {
    return isReadable(OPENINGWIDTH);
  }
  
  public boolean isOpeningWidthModified()
  {
    return isModified(OPENINGWIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getOpeningWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(OPENINGWIDTH).getAttributeMdDTO();
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
  
  public String getRoof()
  {
    return getValue(ROOF);
  }
  
  public void setRoof(String value)
  {
    if(value == null)
    {
      setValue(ROOF, "");
    }
    else
    {
      setValue(ROOF, value);
    }
  }
  
  public boolean isRoofWritable()
  {
    return isWritable(ROOF);
  }
  
  public boolean isRoofReadable()
  {
    return isReadable(ROOF);
  }
  
  public boolean isRoofModified()
  {
    return isModified(ROOF);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRoofMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ROOF).getAttributeMdDTO();
  }
  
  public String getShading()
  {
    return getValue(SHADING);
  }
  
  public void setShading(String value)
  {
    if(value == null)
    {
      setValue(SHADING, "");
    }
    else
    {
      setValue(SHADING, value);
    }
  }
  
  public boolean isShadingWritable()
  {
    return isWritable(SHADING);
  }
  
  public boolean isShadingReadable()
  {
    return isReadable(SHADING);
  }
  
  public boolean isShadingModified()
  {
    return isModified(SHADING);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getShadingMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SHADING).getAttributeMdDTO();
  }
  
  public String getShape()
  {
    return getValue(SHAPE);
  }
  
  public void setShape(String value)
  {
    if(value == null)
    {
      setValue(SHAPE, "");
    }
    else
    {
      setValue(SHAPE, value);
    }
  }
  
  public boolean isShapeWritable()
  {
    return isWritable(SHAPE);
  }
  
  public boolean isShapeReadable()
  {
    return isReadable(SHAPE);
  }
  
  public boolean isShapeModified()
  {
    return isModified(SHAPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getShapeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SHAPE).getAttributeMdDTO();
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
  
  public java.math.BigDecimal getWidth()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(WIDTH));
  }
  
  public void setWidth(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(WIDTH, "");
    }
    else
    {
      setValue(WIDTH, value.toString());
    }
  }
  
  public boolean isWidthWritable()
  {
    return isWritable(WIDTH);
  }
  
  public boolean isWidthReadable()
  {
    return isReadable(WIDTH);
  }
  
  public boolean isWidthModified()
  {
    return isModified(WIDTH);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getWidthMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(WIDTH).getAttributeMdDTO();
  }
  
  public static PupalCollectionExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (PupalCollectionExcelViewDTO) dto;
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
