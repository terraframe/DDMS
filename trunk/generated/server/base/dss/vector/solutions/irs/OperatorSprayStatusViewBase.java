package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1122809881)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to OperatorSprayStatusView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class OperatorSprayStatusViewBase extends dss.vector.solutions.irs.ActorSprayStatusView implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorSprayStatusView";
  public static java.lang.String OPERATORLABEL = "operatorLabel";
  public static java.lang.String OPERATORSPRAYWEEK = "operatorSprayWeek";
  public static java.lang.String SPRAYOPERATOR = "sprayOperator";
  private static final long serialVersionUID = -1122809881;
  
  public OperatorSprayStatusViewBase()
  {
    super();
  }
  
  public String getOperatorLabel()
  {
    return getValue(OPERATORLABEL);
  }
  
  public void validateOperatorLabel()
  {
    this.validateAttribute(OPERATORLABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getOperatorLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorSprayStatusView.CLASS);
    return mdClassIF.definesAttribute(OPERATORLABEL);
  }
  
  public void setOperatorLabel(String value)
  {
    if(value == null)
    {
      setValue(OPERATORLABEL, "");
    }
    else
    {
      setValue(OPERATORLABEL, value);
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorSprayStatusView.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.OperatorSprayStatusView.CLASS);
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
  
  public static OperatorSprayStatusView get(String id)
  {
    return (OperatorSprayStatusView) com.terraframe.mojo.business.View.get(id);
  }
  
  public static dss.vector.solutions.irs.OperatorSprayStatusView[] applyAll(dss.vector.solutions.irs.OperatorSprayStatusView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorSprayStatusView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.irs.OperatorSprayStatusView.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    OperatorSprayStatusView _instance = OperatorSprayStatusView.get(id);
    _instance.deleteConcrete();
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
