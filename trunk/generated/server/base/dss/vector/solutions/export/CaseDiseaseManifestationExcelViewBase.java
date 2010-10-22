package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 2118370501)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiseaseManifestationExcelView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CaseDiseaseManifestationExcelViewBase extends dss.vector.solutions.export.AggregatedCaseExcelView implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.CaseDiseaseManifestationExcelView";
  public static java.lang.String DISEASEMANIFESTATION = "diseaseManifestation";
  private static final long serialVersionUID = 2118370501;
  
  public CaseDiseaseManifestationExcelViewBase()
  {
    super();
  }
  
  public String getDiseaseManifestation()
  {
    return getValue(DISEASEMANIFESTATION);
  }
  
  public void validateDiseaseManifestation()
  {
    this.validateAttribute(DISEASEMANIFESTATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiseaseManifestationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.CaseDiseaseManifestationExcelView.CLASS);
    return mdClassIF.definesAttribute(DISEASEMANIFESTATION);
  }
  
  public void setDiseaseManifestation(String value)
  {
    if(value == null)
    {
      setValue(DISEASEMANIFESTATION, "");
    }
    else
    {
      setValue(DISEASEMANIFESTATION, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CaseDiseaseManifestationExcelView get(String id)
  {
    return (CaseDiseaseManifestationExcelView) com.runwaysdk.business.View.get(id);
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
