package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -1106695957)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ReportModuleView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ReportModuleViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.ReportModuleView";
  public static java.lang.String ID = "id";
  public static java.lang.String MODULENAME = "moduleName";
  private static final long serialVersionUID = -1106695957;
  
  public ReportModuleViewBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ReportModuleView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getModuleName()
  {
    return getValue(MODULENAME);
  }
  
  public void validateModuleName()
  {
    this.validateAttribute(MODULENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getModuleNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.ReportModuleView.CLASS);
    return mdClassIF.definesAttribute(MODULENAME);
  }
  
  public void setModuleName(String value)
  {
    if(value == null)
    {
      setValue(MODULENAME, "");
    }
    else
    {
      setValue(MODULENAME, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ReportModuleView get(String id)
  {
    return (ReportModuleView) com.runwaysdk.business.View.get(id);
  }
  
  public void buildDatabaseViews()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void buildDatabaseViews(java.lang.String id)
  {
    ReportModuleView _instance = ReportModuleView.get(id);
    _instance.buildDatabaseViews();
  }
  
  public static dss.vector.solutions.general.ReportModuleView[] getModules()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.Integer getProgress(java.lang.String requestId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.ReportModuleView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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