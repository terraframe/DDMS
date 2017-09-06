package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -1856915395)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TermSynonymView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TermSynonymViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermSynonymView";
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public static java.lang.String TERMSYNONYMID = "termSynonymId";
  private static final long serialVersionUID = -1856915395;
  
  public TermSynonymViewBase()
  {
    super();
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermSynonymView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void validateSynonymName()
  {
    this.validateAttribute(SYNONYMNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getSynonymNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermSynonymView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  public String getTermSynonymId()
  {
    return getValue(TERMSYNONYMID);
  }
  
  public void validateTermSynonymId()
  {
    this.validateAttribute(TERMSYNONYMID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getTermSynonymIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermSynonymView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(TERMSYNONYMID);
  }
  
  public void setTermSynonymId(String value)
  {
    if(value == null)
    {
      setValue(TERMSYNONYMID, "");
    }
    else
    {
      setValue(TERMSYNONYMID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TermSynonymView get(String id)
  {
    return (TermSynonymView) com.runwaysdk.business.View.get(id);
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
