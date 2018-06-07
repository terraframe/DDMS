package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class ODKAttributeConstraintBasic extends ODKAttributeConstraint implements Reloadable
{
  private ODKAttribute definingAttr;
  
  private ODKAttributeConditionOperation operation;
  
  private String value;
  
  /**
   * 
   * @param definingAttr The attribute who contains this condition.
   * @param comparitiveAttr The attribute to use for condition logic.
   * @param operation
   * @param value
   * @param odkForm
   */
  public ODKAttributeConstraintBasic(ODKAttribute definingAttr, ODKAttributeConditionOperation operation, String value, String localizedMsg)
  {
    super(localizedMsg);
    
    this.definingAttr = definingAttr;
    this.operation = operation;
    this.value = value;
  }
  
  @Override
  public String getBindConstraint()
  {
    if (definingAttr instanceof ODKTermAttribute)
    {
      Term t = Term.get(value);
      value = ODKTermAttribute.sanitizeTermId(t.getTermId());
    }
    // TODO other attr types
    
    return ". " + operation.getOdkRepresentation() + " " + value;
  }
}
