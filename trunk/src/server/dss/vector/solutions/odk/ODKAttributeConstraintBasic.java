package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class ODKAttributeConstraintBasic extends ODKAttributeConstraint implements Reloadable
{
  private ODKAttribute definingAttr;
  
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
  public ODKAttributeConstraintBasic(ODKAttribute definingAttr, ODKAttributeConditionOperation operation, ODKConditionComparative comparative, String localizedMsg)
  {
    super(localizedMsg);
    
    this.definingAttr = definingAttr;
    this.operation = operation;
    this.comparative = comparative;
  }
  
  @Override
  public String getBindConstraint()
  {
    if (this.comparative.asObject() instanceof ODKAttribute) // && (definingAttr.getODKType().equals("date") || definingAttr.getODKType().equals("decimal") || definingAttr.getODKType().equals("int")))
    {
      // If the ODK attribute is null, then we don't enforce the constraint
      return "((string-length(string(" + this.comparative.toString() + ")) = 0) or (. " + operation.getOdkRepresentation() + " " + this.comparative.toString() + "))";
    }
    
    return ". " + operation.getOdkRepresentation() + " " + this.comparative.toString();
  }
}
