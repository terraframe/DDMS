package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = -2082785070)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BrowserRootView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class BrowserRootViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserRootView";
  public static java.lang.String BROWSERROOTID = "browserRootId";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public static java.lang.String SELECTABLE = "selectable";
  public static java.lang.String TERMID = "termId";
  public static java.lang.String TERMNAME = "termName";
  public static java.lang.String TERMONTOLOGYID = "termOntologyId";
  private static final long serialVersionUID = -2082785070;
  
  public BrowserRootViewBase()
  {
    super();
  }
  
  public String getBrowserRootId()
  {
    return getValue(BROWSERROOTID);
  }
  
  public void validateBrowserRootId()
  {
    this.validateAttribute(BROWSERROOTID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBrowserRootIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
    return mdClassIF.definesAttribute(BROWSERROOTID);
  }
  
  public void setBrowserRootId(String value)
  {
    if(value == null)
    {
      setValue(BROWSERROOTID, "");
    }
    else
    {
      setValue(BROWSERROOTID, value);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getMdAttributeId()
  {
    return getValue(MDATTRIBUTEID);
  }
  
  public void validateMdAttributeId()
  {
    this.validateAttribute(MDATTRIBUTEID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMdAttributeIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
    return mdClassIF.definesAttribute(MDATTRIBUTEID);
  }
  
  public void setMdAttributeId(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTEID, "");
    }
    else
    {
      setValue(MDATTRIBUTEID, value);
    }
  }
  
  public Boolean getSelectable()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SELECTABLE));
  }
  
  public void validateSelectable()
  {
    this.validateAttribute(SELECTABLE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSelectableMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermNameMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
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
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTermOntologyIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserRootView.CLASS);
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
  
  public static BrowserRootView get(String id)
  {
    return (BrowserRootView) com.terraframe.mojo.business.View.get(id);
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
