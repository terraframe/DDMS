package dss.vector.solutions.odk;

import dss.vector.solutions.util.LocalizationFacade;

public enum ODKAttributeConditionOperation
{
  AND("and"),
  OR("or"),
  EQUALS("="),
  NOT_EQUALS("!="),
  LESS_THAN("<"),
  LESS_THAN_EQUALS("<="),
  GREATER_THAN(">"),
  GREATER_THAN_EQUALS(">=");
  
  private String odkRepresentation;
  
  private ODKAttributeConditionOperation(String odkRepresentation)
  {
    this.odkRepresentation = odkRepresentation;
  }
  
  public String getOdkRepresentation() {
    return odkRepresentation;
  }

  /**
   * The string is expected to be one found on BasicConditionDAO.operation
   * 
   * @param opStr
   * @return
   */
  public static ODKAttributeConditionOperation factory(String opStr)
  {
    ODKAttributeConditionOperation operation;
    
    if (opStr.equals("EQ"))
    {
      operation = ODKAttributeConditionOperation.EQUALS;
    }
    else if (opStr.equals("NEQ"))
    {
      operation = ODKAttributeConditionOperation.NOT_EQUALS;
    }
    else if (opStr.equals("LT"))
    {
      operation = ODKAttributeConditionOperation.LESS_THAN;
    }
    else if (opStr.equals("LTE"))
    {
      operation = ODKAttributeConditionOperation.LESS_THAN_EQUALS;
    }
    else if (opStr.equals("GT"))
    {
      operation = ODKAttributeConditionOperation.GREATER_THAN;
    }
    else if (opStr.equals("GTE"))
    {
      operation = ODKAttributeConditionOperation.GREATER_THAN_EQUALS;
    }
    else
    {
      throw new UnsupportedOperationException("Unknown operation [" + opStr + "].");
    }
    
    return operation;
  }

  public String getDisplayLabel()
  {
    String key;
    
    if (this.equals(AND))
    {
      key = "odk_condition_operation_and";
    }
    else if (this.equals(OR))
    {
      key = "odk_condition_operation_or";
    }
    else if (this.equals(EQUALS))
    {
      key = "odk_condition_operation_eq";
    }
    else if (this.equals(NOT_EQUALS))
    {
      key = "odk_condition_operation_ne";
    }
    else if (this.equals(LESS_THAN))
    {
      key = "odk_condition_operation_lt";
    }
    else if (this.equals(LESS_THAN_EQUALS))
    {
      key = "odk_condition_operation_lte";
    }
    else if (this.equals(GREATER_THAN))
    {
      key = "odk_condition_operation_gt";
    }
    else if (this.equals(GREATER_THAN_EQUALS))
    {
      key = "odk_condition_operation_gte";
    }
    else
    {
      throw new UnsupportedOperationException();
    }
    
    return LocalizationFacade.getFromBundles(key);
  }
}
