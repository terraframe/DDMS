package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = 44128089)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SchedulerUtil.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SchedulerUtilBase extends com.runwaysdk.business.Util implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.SchedulerUtil";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 44128089;
  
  public SchedulerUtilBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.report.SchedulerUtil.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SchedulerUtil get(String id)
  {
    return (SchedulerUtil) com.runwaysdk.business.Util.get(id);
  }
  
  public static com.runwaysdk.system.scheduler.JobHistoryViewQuery getJobHistories(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.SchedulerUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.system.scheduler.ExecutableJobQuery instanceQuery(java.lang.String[] filterTypes, java.lang.String sortAttr, java.lang.Boolean isDescending, java.lang.Integer pageSize, java.lang.Integer pageNum)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.SchedulerUtil.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.runwaysdk.query.ValueQuery searchByValueQuery(java.lang.String value)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.report.SchedulerUtil.java";
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
