package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

public class ODKAttributeRelevancyComposite extends ODKAttributeRelevancy implements Reloadable
{
  private ODKAttributeRelevancy parent;
  
  private ODKAttributeConditionOperation operation;
  
  private ODKAttributeRelevancy child;
  
  public ODKAttributeRelevancyComposite(ODKAttributeRelevancy parent, ODKAttributeConditionOperation operation, ODKAttributeRelevancy child)
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
