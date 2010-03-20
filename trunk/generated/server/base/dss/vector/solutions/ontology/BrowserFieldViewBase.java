package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -386630615)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to BrowserFieldView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class BrowserFieldViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.BrowserFieldView";
  public static java.lang.String BROWSERFIELDID = "browserFieldId";
  public static java.lang.String DEFAULTVALUE = "defaultValue";
  public static java.lang.String ID = "id";
  public static java.lang.String MDATTRIBUTEID = "mdAttributeId";
  public static java.lang.String MDATTRIBUTELABEL = "mdAttributeLabel";
  public static java.lang.String MDCLASSID = "mdClassId";
  public static java.lang.String MDCLASSLABEL = "mdClassLabel";
  private static final long serialVersionUID = -386630615;
  
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getBrowserFieldIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDefaultValueMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdAttributeIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdAttributeLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdClassIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getMdClassLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.ontology.BrowserFieldView.CLASS);
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
    return (BrowserFieldView) com.runwaysdk.business.View.get(id);
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
