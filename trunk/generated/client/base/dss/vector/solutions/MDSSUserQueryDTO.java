package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 177488547)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MDSSUser.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class MDSSUserQueryDTO extends com.runwaysdk.system.UsersQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 177488547;

  protected MDSSUserQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends dss.vector.solutions.MDSSUserDTO> getResultSet()
{
  return (java.util.List<? extends dss.vector.solutions.MDSSUserDTO>)super.getResultSet();
}
}
