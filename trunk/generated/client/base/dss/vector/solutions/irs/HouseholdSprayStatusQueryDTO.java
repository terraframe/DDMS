package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 503611304)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to HouseholdSprayStatus.java
 *
 * @author Autogenerated by TerraFrame
 */
public class HouseholdSprayStatusQueryDTO extends dss.vector.solutions.irs.SprayStatusQueryDTO
 implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 503611304;

  protected HouseholdSprayStatusQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends dss.vector.solutions.irs.HouseholdSprayStatusDTO> getResultSet()
{
  return (java.util.List<? extends dss.vector.solutions.irs.HouseholdSprayStatusDTO>)super.getResultSet();
}
}
