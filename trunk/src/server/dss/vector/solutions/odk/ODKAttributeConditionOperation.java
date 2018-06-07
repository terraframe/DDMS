package dss.vector.solutions.odk;

import com.runwaysdk.generation.loader.Reloadable;

public enum ODKAttributeConditionOperation implements Reloadable
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
    //TODO Localization messages must be pulled directly from ServerExceptionMessageLocalizer, and they must be specific to each 
    //  constraint violation. You can't just change the word "and" to another language and expect it to translate properly
    //  because the entire sentence structure may be different.
    
    
    if (this.equals(AND))
    {
      return "and";
    }
    else if (this.equals(OR))
    {
      return "or";
    }
    else if (this.equals(EQUALS))
    {
      return "equal to";
    }
    else if (this.equals(NOT_EQUALS))
    {
      return "not equal to";
    }
    else if (this.equals(LESS_THAN))
    {
      return "less than";
    }
    else if (this.equals(LESS_THAN_EQUALS))
    {
      return "less than or equal to";
    }
    else if (this.equals(GREATER_THAN))
    {
      return "greater than or equal to";
    }
    else if (this.equals(GREATER_THAN_EQUALS))
    {
      return "greater than or equal to";
    }
    else
    {
      throw new UnsupportedOperationException();
    }
  }
}
