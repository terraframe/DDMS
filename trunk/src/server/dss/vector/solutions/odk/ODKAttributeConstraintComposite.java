package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

public class ODKAttributeConstraintComposite extends ODKAttributeConstraint implements Reloadable
{
  private ODKAttributeConstraint parent;
  
  private ODKAttributeConditionOperation operation;
  
  private ODKAttributeConstraint child;
  
  public ODKAttributeConstraintComposite(ODKAttributeConstraint parent, ODKAttributeConditionOperation operation, ODKAttributeConstraint child)
  {
    super(null);
    
    this.parent = parent;
    this.operation = operation;
    this.child = child;
  }

  @Override
  public String getBindConstraint()
  {
    return parent.getBindConstraint() + " " + operation.getOdkRepresentation() + " " + child.getBindConstraint();
  }

  @Override
  public String getConstraintMsg()
  {
    return parent.getConstraintMsg() + ". " + child.getConstraintMsg();
  }
}
