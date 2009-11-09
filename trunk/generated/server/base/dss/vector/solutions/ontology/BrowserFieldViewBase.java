package dss.vector.solutions.ontology;

@com.terraframe.mojo.business.ClassSignature(hash = -156489678)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BrowserFieldView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class BrowserFieldViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserFieldView";
  public static java.lang.String BROWSERFIELDID = "browserFieldId";
  public static java.lang.String DEFAULTVALUE = "defaultValue";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public static java.lang.String MDATTRIBUTELABEL = "mdAttributeLabel";
  public static java.lang.String MDCLASSID = "mdClassId";
  public static java.lang.String MDCLASSLABEL = "mdClassLabel";
  private static final long serialVersionUID = -156489678;
  
  public BrowserFieldViewBase()
  {
    super();
  }
  
  public String getBrowserFieldId()
  {
    return getValue(BROWSERFIELDID);
  }
  
  public void validateBrowserFieldId()
  {
    this.validateAttribute(BROWSERFIELDID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getBrowserFieldIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
    return mdClassIF.definesAttribute(BROWSERFIELDID);
  }
  
  public void setBrowserFieldId(String value)
  {
    if(value == null)
    {
      setValue(BROWSERFIELDID, "");
    }
    else
    {
      setValue(BROWSERFIELDID, value);
    }
  }
  
  public dss.vector.solutions.ontology.Term getDefaultValue()
  {
    if (getValue(DEFAULTVALUE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DEFAULTVALUE));
    }
  }
  
  public void validateDefaultValue()
  {
    this.validateAttribute(DEFAULTVALUE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getDefaultValueMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
    return mdClassIF.definesAttribute(DEFAULTVALUE);
  }
  
  public void setDefaultValue(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DEFAULTVALUE, "");
    }
    else
    {
      setValue(DEFAULTVALUE, value.getId());
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public String getMdAttributeLabel()
  {
    return getValue(MDATTRIBUTELABEL);
  }
  
  public void validateMdAttributeLabel()
  {
    this.validateAttribute(MDATTRIBUTELABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMdAttributeLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
    return mdClassIF.definesAttribute(MDATTRIBUTELABEL);
  }
  
  public void setMdAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(MDATTRIBUTELABEL, "");
    }
    else
    {
      setValue(MDATTRIBUTELABEL, value);
    }
  }
  
  public String getMdClassId()
  {
    return getValue(MDCLASSID);
  }
  
  public void validateMdClassId()
  {
    this.validateAttribute(MDCLASSID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMdClassIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
    return mdClassIF.definesAttribute(MDCLASSID);
  }
  
  public void setMdClassId(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSID, "");
    }
    else
    {
      setValue(MDCLASSID, value);
    }
  }
  
  public String getMdClassLabel()
  {
    return getValue(MDCLASSLABEL);
  }
  
  public void validateMdClassLabel()
  {
    this.validateAttribute(MDCLASSLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMdClassLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
    return mdClassIF.definesAttribute(MDCLASSLABEL);
  }
  
  public void setMdClassLabel(String value)
  {
    if(value == null)
    {
      setValue(MDCLASSLABEL, "");
    }
    else
    {
      setValue(MDCLASSLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static BrowserFieldView get(String id)
  {
    return (BrowserFieldView) com.terraframe.mojo.business.View.get(id);
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
