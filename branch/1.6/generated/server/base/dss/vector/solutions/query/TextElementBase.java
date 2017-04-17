package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = 1159376111)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TextElement.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TextElementBase extends com.runwaysdk.business.Business implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.TextElement";
  public static java.lang.String CREATEDATE = "createDate";
  public static java.lang.String CREATEDBY = "createdBy";
  public static java.lang.String CUSTOMTEXTELEMENTID = "customTextElementId";
  public static java.lang.String ENTITYDOMAIN = "entityDomain";
  public static java.lang.String FONTCOLOR = "fontColor";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLE = "fontStyle";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String LASTUPDATEDATE = "lastUpdateDate";
  public static java.lang.String LASTUPDATEDBY = "lastUpdatedBy";
  public static java.lang.String LOCKEDBY = "lockedBy";
  public static java.lang.String OWNER = "owner";
  public static java.lang.String SEQ = "seq";
  public static java.lang.String SITEMASTER = "siteMaster";
  public static java.lang.String TEXTVALUE = "textValue";
  public static java.lang.String TEXTXPOSITION = "textXPosition";
  public static java.lang.String TEXTYPOSITION = "textYPosition";
  public static java.lang.String TYPE = "type";
  private static final long serialVersionUID = 1159376111;
  
  public TextElementBase()
  {
    super();
  }
  
  public java.util.Date getCreateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(CREATEDATE));
  }
  
  public void validateCreateDate()
  {
    this.validateAttribute(CREATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getCreateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(CREATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getCreatedBy()
  {
    if (getValue(CREATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(CREATEDBY));
    }
  }
  
  public String getCreatedById()
  {
    return getValue(CREATEDBY);
  }
  
  public void validateCreatedBy()
  {
    this.validateAttribute(CREATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getCreatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CREATEDBY);
  }
  
  public String getCustomTextElementId()
  {
    return getValue(CUSTOMTEXTELEMENTID);
  }
  
  public void validateCustomTextElementId()
  {
    this.validateAttribute(CUSTOMTEXTELEMENTID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getCustomTextElementIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CUSTOMTEXTELEMENTID);
  }
  
  public void setCustomTextElementId(String value)
  {
    if(value == null)
    {
      setValue(CUSTOMTEXTELEMENTID, "");
    }
    else
    {
      setValue(CUSTOMTEXTELEMENTID, value);
    }
  }
  
  public com.runwaysdk.system.metadata.MdDomain getEntityDomain()
  {
    if (getValue(ENTITYDOMAIN).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.metadata.MdDomain.get(getValue(ENTITYDOMAIN));
    }
  }
  
  public String getEntityDomainId()
  {
    return getValue(ENTITYDOMAIN);
  }
  
  public void validateEntityDomain()
  {
    this.validateAttribute(ENTITYDOMAIN);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getEntityDomainMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(ENTITYDOMAIN);
  }
  
  public void setEntityDomain(com.runwaysdk.system.metadata.MdDomain value)
  {
    if(value == null)
    {
      setValue(ENTITYDOMAIN, "");
    }
    else
    {
      setValue(ENTITYDOMAIN, value.getId());
    }
  }
  
  public String getFontColor()
  {
    return getValue(FONTCOLOR);
  }
  
  public void validateFontColor()
  {
    this.validateAttribute(FONTCOLOR);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFontColorMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FONTCOLOR);
  }
  
  public void setFontColor(String value)
  {
    if(value == null)
    {
      setValue(FONTCOLOR, "");
    }
    else
    {
      setValue(FONTCOLOR, value);
    }
  }
  
  public String getFontFamily()
  {
    return getValue(FONTFAMILY);
  }
  
  public void validateFontFamily()
  {
    this.validateAttribute(FONTFAMILY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFontFamilyMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FONTFAMILY);
  }
  
  public void setFontFamily(String value)
  {
    if(value == null)
    {
      setValue(FONTFAMILY, "");
    }
    else
    {
      setValue(FONTFAMILY, value);
    }
  }
  
  public Integer getFontSize()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FONTSIZE));
  }
  
  public void validateFontSize()
  {
    this.validateAttribute(FONTSIZE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getFontSizeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(FONTSIZE);
  }
  
  public void setFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(FONTSIZE, "");
    }
    else
    {
      setValue(FONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public String getFontStyle()
  {
    return getValue(FONTSTYLE);
  }
  
  public void validateFontStyle()
  {
    this.validateAttribute(FONTSTYLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFontStyleMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FONTSTYLE);
  }
  
  public void setFontStyle(String value)
  {
    if(value == null)
    {
      setValue(FONTSTYLE, "");
    }
    else
    {
      setValue(FONTSTYLE, value);
    }
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(KEYNAME);
  }
  
  public void setKeyName(String value)
  {
    if(value == null)
    {
      setValue(KEYNAME, "");
    }
    else
    {
      setValue(KEYNAME, value);
    }
  }
  
  public java.util.Date getLastUpdateDate()
  {
    return com.runwaysdk.constants.MdAttributeDateTimeUtil.getTypeSafeValue(getValue(LASTUPDATEDATE));
  }
  
  public void validateLastUpdateDate()
  {
    this.validateAttribute(LASTUPDATEDATE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF getLastUpdateDateMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF)mdClassIF.definesAttribute(LASTUPDATEDATE);
  }
  
  public com.runwaysdk.system.SingleActor getLastUpdatedBy()
  {
    if (getValue(LASTUPDATEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LASTUPDATEDBY));
    }
  }
  
  public String getLastUpdatedById()
  {
    return getValue(LASTUPDATEDBY);
  }
  
  public void validateLastUpdatedBy()
  {
    this.validateAttribute(LASTUPDATEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLastUpdatedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LASTUPDATEDBY);
  }
  
  public com.runwaysdk.system.SingleActor getLockedBy()
  {
    if (getValue(LOCKEDBY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.SingleActor.get(getValue(LOCKEDBY));
    }
  }
  
  public String getLockedById()
  {
    return getValue(LOCKEDBY);
  }
  
  public void validateLockedBy()
  {
    this.validateAttribute(LOCKEDBY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getLockedByMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(LOCKEDBY);
  }
  
  public com.runwaysdk.system.Actor getOwner()
  {
    if (getValue(OWNER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.Actor.get(getValue(OWNER));
    }
  }
  
  public String getOwnerId()
  {
    return getValue(OWNER);
  }
  
  public void validateOwner()
  {
    this.validateAttribute(OWNER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getOwnerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(OWNER);
  }
  
  public void setOwner(com.runwaysdk.system.Actor value)
  {
    if(value == null)
    {
      setValue(OWNER, "");
    }
    else
    {
      setValue(OWNER, value.getId());
    }
  }
  
  public Long getSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(SEQ));
  }
  
  public void validateSeq()
  {
    this.validateAttribute(SEQ);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeLongDAOIF getSeqMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeLongDAOIF)mdClassIF.definesAttribute(SEQ);
  }
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SITEMASTER);
  }
  
  public String getTextValue()
  {
    return getValue(TEXTVALUE);
  }
  
  public void validateTextValue()
  {
    this.validateAttribute(TEXTVALUE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTextValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TEXTVALUE);
  }
  
  public void setTextValue(String value)
  {
    if(value == null)
    {
      setValue(TEXTVALUE, "");
    }
    else
    {
      setValue(TEXTVALUE, value);
    }
  }
  
  public Integer getTextXPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTXPOSITION));
  }
  
  public void validateTextXPosition()
  {
    this.validateAttribute(TEXTXPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getTextXPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(TEXTXPOSITION);
  }
  
  public void setTextXPosition(Integer value)
  {
    if(value == null)
    {
      setValue(TEXTXPOSITION, "");
    }
    else
    {
      setValue(TEXTXPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getTextYPosition()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TEXTYPOSITION));
  }
  
  public void validateTextYPosition()
  {
    this.validateAttribute(TEXTYPOSITION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF getTextYPositionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF)mdClassIF.definesAttribute(TEXTYPOSITION);
  }
  
  public void setTextYPosition(Integer value)
  {
    if(value == null)
    {
      setValue(TEXTYPOSITION, "");
    }
    else
    {
      setValue(TEXTYPOSITION, java.lang.Integer.toString(value));
    }
  }
  
  public String getType()
  {
    return getValue(TYPE);
  }
  
  public void validateType()
  {
    this.validateAttribute(TYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.query.TextElement.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TYPE);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TextElementQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    TextElementQuery query = new TextElementQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public dss.vector.solutions.query.HasTextElement addContainingMap(dss.vector.solutions.query.SavedMap savedMap)
  {
    return (dss.vector.solutions.query.HasTextElement) addParent(savedMap, dss.vector.solutions.query.HasTextElement.CLASS);
  }
  
  public void removeContainingMap(dss.vector.solutions.query.SavedMap savedMap)
  {
    removeAllParents(savedMap, dss.vector.solutions.query.HasTextElement.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.SavedMap> getAllContainingMap()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.SavedMap>) getParents(dss.vector.solutions.query.HasTextElement.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.HasTextElement> getAllContainingMapRel()
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.HasTextElement>) getParentRelationships(dss.vector.solutions.query.HasTextElement.CLASS);
  }
  
  @SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.HasTextElement> getContainingMapRel(dss.vector.solutions.query.SavedMap savedMap)
  {
    return (com.runwaysdk.query.OIterator<? extends dss.vector.solutions.query.HasTextElement>) getRelationshipsWithParent(savedMap, dss.vector.solutions.query.HasTextElement.CLASS);
  }
  
  public static TextElement get(String id)
  {
    return (TextElement) com.runwaysdk.business.Business.get(id);
  }
  
  public static TextElement getByKey(String key)
  {
    return (TextElement) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public java.lang.String removeTextElement(java.lang.String textElementId, java.lang.String mapId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.query.TextElement.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final java.lang.String removeTextElement(java.lang.String id, java.lang.String textElementId, java.lang.String mapId)
  {
    TextElement _instance = TextElement.get(id);
    return _instance.removeTextElement(textElementId, mapId);
  }
  
  public static TextElement lock(java.lang.String id)
  {
    TextElement _instance = TextElement.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static TextElement unlock(java.lang.String id)
  {
    TextElement _instance = TextElement.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
