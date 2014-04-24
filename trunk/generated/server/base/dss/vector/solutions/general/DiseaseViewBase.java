package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = 1389982926)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DiseaseView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DiseaseViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.DiseaseView";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISEASENAME = "diseaseName";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1389982926;
  
  public DiseaseViewBase()
  {
    super();
  }
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.DiseaseView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public String getDiseaseName()
  {
    return getValue(DISEASENAME);
  }
  
  public void validateDiseaseName()
  {
    this.validateAttribute(DISEASENAME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getDiseaseNameMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.DiseaseView.CLASS);
    return mdClassIF.definesAttribute(DISEASENAME);
  }
  
  public void setDiseaseName(String value)
  {
    if(value == null)
    {
      setValue(DISEASENAME, "");
    }
    else
    {
      setValue(DISEASENAME, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.general.DiseaseView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DiseaseView get(String id)
  {
    return (DiseaseView) com.runwaysdk.business.View.get(id);
  }
  
  public void applyConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.DiseaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void applyConcrete(java.lang.String id)
  {
    DiseaseView _instance = DiseaseView.get(id);
    _instance.applyConcrete();
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.DiseaseView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    DiseaseView _instance = DiseaseView.get(id);
    _instance.deleteConcrete();
  }
  
  public static dss.vector.solutions.general.DiseaseViewQuery getPage(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.general.DiseaseView.java";
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