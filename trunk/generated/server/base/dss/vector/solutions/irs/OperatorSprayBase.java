package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1553177560)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorSpray.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class OperatorSprayBase extends dss.vector.solutions.irs.ActorSpray implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorSpray";
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  private static final long serialVersionUID = -1553177560;
  
  public OperatorSprayBase()
  {
    super();
  }
  
  public Integer getOperatorSprayWeek()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORSPRAYWEEK));
  }
  
  public void validateOperatorSprayWeek()
  {
    this.validateAttribute(OPERATORSPRAYWEEK);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorSprayWeekMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorSpray.CLASS);
    return mdClassIF.definesAttribute(OPERATORSPRAYWEEK);
  }
  
  public void setOperatorSprayWeek(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORSPRAYWEEK, "");
    }
    else
    {
      setValue(OPERATORSPRAYWEEK, java.lang.Integer.toString(value));
    }
  }
  
  public dss.vector.solutions.irs.SprayOperator getSprayOperator()
  {
    if (getValue(SPRAYOPERATOR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayOperator.get(getValue(SPRAYOPERATOR));
    }
  }
  
  public void validateSprayOperator()
  {
    this.validateAttribute(SPRAYOPERATOR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSprayOperatorMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorSpray.CLASS);
    return mdClassIF.definesAttribute(SPRAYOPERATOR);
  }
  
  public void setSprayOperator(dss.vector.solutions.irs.SprayOperator value)
  {
    if(value == null)
    {
      setValue(SPRAYOPERATOR, "");
    }
    else
    {
      setValue(SPRAYOPERATOR, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static OperatorSprayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static OperatorSpray get(String id)
  {
    return (OperatorSpray) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static OperatorSpray getByKey(String key)
  {
    return (OperatorSpray) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static dss.vector.solutions.irs.OperatorSprayView getView(java.lang.String id)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorSpray.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public dss.vector.solutions.irs.OperatorSprayView lockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorSpray.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayView lockView(java.lang.String id)
  {
    OperatorSpray _instance = OperatorSpray.get(id);
    return _instance.lockView();
  }
  
  public dss.vector.solutions.irs.OperatorSprayView unlockView()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorSpray.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.irs.OperatorSprayView unlockView(java.lang.String id)
  {
    OperatorSpray _instance = OperatorSpray.get(id);
    return _instance.unlockView();
  }
  
  public static OperatorSpray lock(java.lang.String id)
  {
    OperatorSpray _instance = OperatorSpray.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static OperatorSpray unlock(java.lang.String id)
  {
    OperatorSpray _instance = OperatorSpray.get(id);
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
