package dss.vector.solutions.odk;

@com.runwaysdk.business.ClassSignature(hash = -1496200101)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ODKUser.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class ODKUserQueryDTO extends dss.vector.solutions.MDSSUserQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1496200101;

  protected ODKUserQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends dss.vector.solutions.odk.ODKUserDTO> getResultSet()
{
  return (java.util.List<? extends dss.vector.solutions.odk.ODKUserDTO>)super.getResultSet();
}
}
