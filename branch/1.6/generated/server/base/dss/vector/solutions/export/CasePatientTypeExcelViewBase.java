package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = -268200457)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CasePatientTypeExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CasePatientTypeExcelViewBase extends dss.vector.solutions.export.AggregatedCaseExcelView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.CasePatientTypeExcelView";
  public static java.lang.String PATIENTTYPE = "patientType";
  private static final long serialVersionUID = -268200457;
  
  public CasePatientTypeExcelViewBase()
  {
    super();
  }
  
  public String getPatientType()
  {
    return getValue(PATIENTTYPE);
  }
  
  public void validatePatientType()
  {
    this.validateAttribute(PATIENTTYPE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getPatientTypeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.CasePatientTypeExcelView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(PATIENTTYPE);
  }
  
  public void setPatientType(String value)
  {
    if(value == null)
    {
      setValue(PATIENTTYPE, "");
    }
    else
    {
      setValue(PATIENTTYPE, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CasePatientTypeExcelView get(String id)
  {
    return (CasePatientTypeExcelView) com.runwaysdk.business.View.get(id);
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
