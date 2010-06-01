package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 604175184)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ResistancePropertyDisplayLabel.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ResistancePropertyDisplayLabelBase extends com.runwaysdk.business.LocalStruct implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ResistancePropertyDisplayLabel";
  public static java.lang.String DENGUE_DEFAULTLOCALE = "dENGUE_defaultLocale";
  public static java.lang.String DEFAULTLOCALE = "defaultLocale";
  public static java.lang.String ID = "id";
  public static java.lang.String KEYNAME = "keyName";
  public static java.lang.String MALARIA_DEFAULTLOCALE = "mALARIA_defaultLocale";
  public static java.lang.String SITEMASTER = "siteMaster";
  private static final long serialVersionUID = 604175184;
  
  public ResistancePropertyDisplayLabelBase()
  {
    super();
  }
  
  public ResistancePropertyDisplayLabelBase(com.runwaysdk.business.MutableWithStructs component, String structName)
  {
    super(component, structName);
  }
  
  public static ResistancePropertyDisplayLabel get(String id)
  {
    return (ResistancePropertyDisplayLabel) com.runwaysdk.business.Struct.get(id);
  }
  
  public static ResistancePropertyDisplayLabel getByKey(String key)
  {
    return (ResistancePropertyDisplayLabel) com.runwaysdk.business.Struct.get(CLASS, key);
  }
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getKeyName()
  {
    return getValue(KEYNAME);
  }
  
  public void validateKeyName()
  {
    this.validateAttribute(KEYNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getKeyNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.CLASS);
    return mdClassIF.definesAttribute(KEYNAME);
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
  
  public String getSiteMaster()
  {
    return getValue(SITEMASTER);
  }
  
  public void validateSiteMaster()
  {
    this.validateAttribute(SITEMASTER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSiteMasterMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.ResistancePropertyDisplayLabel.CLASS);
    return mdClassIF.definesAttribute(SITEMASTER);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ResistancePropertyDisplayLabelQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ResistancePropertyDisplayLabelQuery query = new ResistancePropertyDisplayLabelQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
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
