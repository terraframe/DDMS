package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.LocalizationFacade;

public class ODKAttributeRelevancyBasic extends ODKAttributeRelevancy implements Reloadable
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
  public ODKAttributeRelevancyBasic(ODKAttribute definingAttr, ODKAttribute comparitiveAttr, ODKAttributeConditionOperation operation, ODKConditionComparative comparative)
  {
    this.definingAttr = definingAttr;
    this.comparitiveAttr = comparitiveAttr;
    this.operation = operation;
    this.comparative = comparative;
  }
  
  @Override
  public String getBindRelevant()
  {
    String attrPath = comparitiveAttr.getInstancePath();
    
    if (comparitiveAttr.getODKType().equals("select1"))
    {
      String selected = "selected(" + attrPath + ", '" + comparative.toString() + "')";
      
      if (operation == ODKAttributeConditionOperation.EQUALS)
      {
        return selected;
      }
      else if (operation == ODKAttributeConditionOperation.NOT_EQUALS)
      {
        return "not(" + selected + ")";
      }
      else
      {
        throw new UnsupportedOperationException("Unsupported operation for term condition [" + operation.name() + "].");
      }
    }
    else if (comparitiveAttr instanceof ODKAttributeBoolean)
    {
      String selected = "selected(" + attrPath + ", '" + comparative.toString() + "')";
      
      if (operation == ODKAttributeConditionOperation.EQUALS)
      {
        return selected;
      }
      else if (operation == ODKAttributeConditionOperation.NOT_EQUALS)
      {
        return "not(" + selected + ")";
      }
      else
      {
        throw new UnsupportedOperationException("Unsupported operation for term condition [" + operation.name() + "].");
      }
    }
    
    return attrPath + " " + operation.getOdkRepresentation() + " " + comparative.toString();
  }
}
