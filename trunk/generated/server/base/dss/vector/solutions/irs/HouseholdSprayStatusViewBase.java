package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 1388609058)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdSprayStatusView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class HouseholdSprayStatusViewBase extends dss.vector.solutions.irs.SprayStatusView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.HouseholdSprayStatusView";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String STRUCTUREID = "structureId";
  private static final long serialVersionUID = 1388609058;
  
  public HouseholdSprayStatusViewBase()
  {
    super();
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void validateHouseholdId()
  {
    this.validateAttribute(HOUSEHOLDID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHouseholdIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.HouseholdSprayStatusView.CLASS);
    return mdClassIF.definesAttribute(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void validateStructureId()
  {
    this.validateAttribute(STRUCTUREID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getStructureIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.HouseholdSprayStatusView.CLASS);
    return mdClassIF.definesAttribute(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static HouseholdSprayStatusView get(String id)
  {
    return (HouseholdSprayStatusView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.HouseholdSprayStatusView[] applyAll(dss.vector.solutions.irs.HouseholdSprayStatusView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.HouseholdSprayStatusView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.HouseholdSprayStatusView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    HouseholdSprayStatusView _instance = HouseholdSprayStatusView.get(id);
    _instance.deleteConcrete();
  }
  
  public static java.lang.String[] getGeneratedIds()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.HouseholdSprayStatusView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
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
