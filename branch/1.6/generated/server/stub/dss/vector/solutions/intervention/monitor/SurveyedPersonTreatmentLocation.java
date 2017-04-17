package dss.vector.solutions.intervention.monitor;

public class SurveyedPersonTreatmentLocation extends SurveyedPersonTreatmentLocationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 241024383;
  
  public SurveyedPersonTreatmentLocation(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public SurveyedPersonTreatmentLocation(dss.vector.solutions.intervention.monitor.SurveyedPerson parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
}
