package dss.vector.solutions.odk;

public class ODKAttributeConditionComposite extends ODKAttributeCondition
{
  private ODKAttributeCondition parent;
  
  private ODKAttributeConditionOperation operation;
  
  private ODKAttributeCondition child;
  
  public ODKAttributeConditionComposite(ODKAttributeCondition parent, ODKAttributeConditionOperation operation, ODKAttributeCondition child)
  {
    this.parent = parent;
    this.operation = operation;
    this.child = child;
  }

  @Override
  public String getBindRelevant()
  {
    return parent.getBindRelevant() + " " + operation.getOdkRepresentation() + " " + child.getBindRelevant();
  }
}
