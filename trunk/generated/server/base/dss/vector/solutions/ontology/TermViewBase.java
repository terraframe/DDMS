package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -214107679)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to TermView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class TermViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermView";
  public static java.lang.String ID = "id";
  public static java.lang.String SELECTABLE = "selectable";
  public static java.lang.String TERMID = "termId";
  public static java.lang.String TERMNAME = "termName";
  public static java.lang.String TERMONTOLOGYID = "termOntologyId";
  private static final long serialVersionUID = -214107679;
  
  public TermViewBase()
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public Boolean getSelectable()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SELECTABLE));
  }
  
  public void validateSelectable()
  {
    this.validateAttribute(SELECTABLE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSelectableMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermView.CLASS);
    return mdClassIF.definesAttribute(SELECTABLE);
  }
  
  public void setSelectable(Boolean value)
  {
    if(value == null)
    {
      setValue(SELECTABLE, "");
    }
    else
    {
      setValue(SELECTABLE, java.lang.Boolean.toString(value));
    }
  }
  
  public String getTermId()
  {
    return getValue(TERMID);
  }
  
  public void validateTermId()
  {
    this.validateAttribute(TERMID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermView.CLASS);
    return mdClassIF.definesAttribute(TERMID);
  }
  
  public void setTermId(String value)
  {
    if(value == null)
    {
      setValue(TERMID, "");
    }
    else
    {
      setValue(TERMID, value);
    }
  }
  
  public String getTermName()
  {
    return getValue(TERMNAME);
  }
  
  public void validateTermName()
  {
    this.validateAttribute(TERMNAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermView.CLASS);
    return mdClassIF.definesAttribute(TERMNAME);
  }
  
  public void setTermName(String value)
  {
    if(value == null)
    {
      setValue(TERMNAME, "");
    }
    else
    {
      setValue(TERMNAME, value);
    }
  }
  
  public String getTermOntologyId()
  {
    return getValue(TERMONTOLOGYID);
  }
  
  public void validateTermOntologyId()
  {
    this.validateAttribute(TERMONTOLOGYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getTermOntologyIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.TermView.CLASS);
    return mdClassIF.definesAttribute(TERMONTOLOGYID);
  }
  
  public void setTermOntologyId(String value)
  {
    if(value == null)
    {
      setValue(TERMONTOLOGYID, "");
    }
    else
    {
      setValue(TERMONTOLOGYID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static TermView get(String id)
  {
    return (TermView) com.runwaysdk.business.View.get(id);
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
