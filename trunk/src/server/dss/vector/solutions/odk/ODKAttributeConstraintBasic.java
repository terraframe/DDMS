package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class ODKAttributeConstraintBasic extends ODKAttributeConstraint implements Reloadable
{
  private ODKAttribute definingAttr;
  
  private ODKAttribute comparitiveAttr;
  
  private ODKAttributeConditionOperation operation;
  
  private ODKConditionComparative comparative;
  
  /**
   * 
   * @param definingAttr The attribute who contains this condition.
   * @param comparitiveAttr The attribute to use for condition logic.
   * @param operation
   * @param value
   * @param odkForm
   */
  public ODKAttributeConstraintBasic(ODKAttribute definingAttr, ODKAttribute comparitiveAttr, ODKAttributeConditionOperation operation, ODKConditionComparative comparative, String localizedMsg)
  {
    super(localizedMsg);
    
    this.definingAttr = definingAttr;
    this.comparitiveAttr = comparitiveAttr;
    this.operation = operation;
    this.comparative = comparative;
  }
  
  public ODKAttributeConstraintBasic(ODKAttribute comparitiveAttr, ODKAttributeConditionOperation operation, ODKConditionComparative comparative, String localizedMsg)
  {
    this(comparitiveAttr, comparitiveAttr, operation, comparative, localizedMsg);
  }
  
  @Override
  public String getBindConstraint()
  {
    String attr = ".";
    if (this.definingAttr != this.comparitiveAttr)
    {
      attr = this.comparitiveAttr.getInstancePath();
    }
    
    if (this.comparative.asObject() instanceof ODKAttribute)
    {
      // If the ODK attribute is null, then we don't enforce the constraint
      return "((string-length(string(" + this.comparative.toString() + ")) = 0) or (" + attr + " " + operation.getOdkRepresentation() + " " + this.comparative.toString() + "))";
    }
    
    return attr + " " + operation.getOdkRepresentation() + " " + this.comparative.toString();
  }
}
