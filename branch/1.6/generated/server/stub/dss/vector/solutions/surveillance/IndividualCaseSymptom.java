package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.IndividualInstance;

public class IndividualCaseSymptom extends IndividualCaseSymptomBase implements ChildOption, Reloadable
{
  private static final long serialVersionUID = 1257959971971L;
  
  public IndividualCaseSymptom(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public IndividualCaseSymptom(dss.vector.solutions.intervention.monitor.IndividualInstance parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }

  public IndividualCaseSymptom clone(IndividualInstance parent)
  {
    IndividualCaseSymptom clone = new IndividualCaseSymptom(parent, this.getChild());
    clone.setHasSymptom(this.getHasSymptom());

    return clone;
  }
  
}
