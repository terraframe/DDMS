package dss.vector.solutions.odk;

import dss.vector.solutions.ontology.Term;

public class ODKAttributeConditionBasic extends ODKAttributeCondition
{
  private ODKAttribute definingAttr;
  
  private ODKAttribute comparitiveAttr;
  
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
  public ODKAttributeConditionBasic(ODKAttribute definingAttr, ODKAttribute comparitiveAttr, ODKAttributeConditionOperation operation, String value)
  {
    this.definingAttr = definingAttr;
    this.comparitiveAttr = comparitiveAttr;
    this.operation = operation;
    this.value = value;
  }
  
  @Override
  public String getBindConstraint()
  {
    String attrStr = comparitiveAttr.getAttributeName();
    
    if (definingAttr.equals(comparitiveAttr))
    {
      attrStr = ".";
    }
    
    if (comparitiveAttr instanceof ODKTermAttribute)
    {
      Term t = Term.get(value);
      value = t.getTermId();
    }
    
    return attrStr + " " + operation.getOdkRepresentation() + " " + value;
  }
}
