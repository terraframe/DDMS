package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1352432330)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to RefreshViewJob.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class RefreshViewJobQueryDTO extends com.runwaysdk.system.scheduler.ExecutableJobQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -1352432330;

  protected RefreshViewJobQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends dss.vector.solutions.query.RefreshViewJobDTO> getResultSet()
{
  return (java.util.List<? extends dss.vector.solutions.query.RefreshViewJobDTO>)super.getResultSet();
}
}
