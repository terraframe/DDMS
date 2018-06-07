package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.LocalizationFacade;

public class ODKAttributeRelevancyBasic extends ODKAttributeRelevancy implements Reloadable
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
  public ODKAttributeRelevancyBasic(ODKAttribute definingAttr, ODKAttribute comparitiveAttr, ODKAttributeConditionOperation operation, String value)
  {
    this.definingAttr = definingAttr;
    this.comparitiveAttr = comparitiveAttr;
    this.operation = operation;
    this.value = value;
  }
  
  @Override
  public String getBindRelevant()
  {
    String attrPath = comparitiveAttr.getInstancePath();
    
    if (comparitiveAttr instanceof ODKTermAttribute)
    {
      Term t = Term.get(value);
      value = ODKTermAttribute.sanitizeTermId(t.getTermId());
      
      String selected = "selected(" + attrPath + ", '" + value + "')";
      
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
      String selected = "selected(" + attrPath + ", '" + value + "')";
      
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
    else if (comparitiveAttr.getODKType().equals("date"))
    {
      value = "date('" + value + "')";
    }
    else if (comparitiveAttr.getODKType().equals("string"))
    {
      value = "'" + value + "'";
    }
    
    return attrPath + " " + operation.getOdkRepresentation() + " " + value;
  }
}
