package dss.vector.solutions.odk;

public enum ODKAttributeConditionOperation
{
  AND("and"),
  OR("or"),
  EQUALS("="),
  NOT_EQUALS("!="),
  LESS_THAN("&lt;"),
  LESS_THAN_EQUALS("&lt;="),
  GREATER_THAN("&gt;"),
  GREATER_THAN_EQUALS("&gt;=");
  
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
}
