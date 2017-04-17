package dss.vector.solutions.intervention.monitor;

public class SurveyedPersonTreatment extends SurveyedPersonTreatmentBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1256678924;
  
  public SurveyedPersonTreatment(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public SurveyedPersonTreatment(dss.vector.solutions.intervention.monitor.SurveyedPerson parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
