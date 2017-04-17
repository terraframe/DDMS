package dss.vector.solutions.query;

public class QueryInfo extends QueryInfoBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -723660218;
  
  private boolean hasThematicVariable;
  private boolean isThematicNumeric;
  
  public QueryInfo()
  {
    super();
    
    hasThematicVariable = false;
    isThematicNumeric = false;
  }
  
  public void setHasThematicVariable(boolean hasThematicVariable)
  {
    this.hasThematicVariable = hasThematicVariable; 
  }
  
  public void setIsThematicVariable(boolean isThematicVariable)
  {
    this.isThematicNumeric = isThematicVariable;
  }
  
  public boolean hasThematicVariable()
  {
    return this.hasThematicVariable;
  }
  
  public boolean isThematicNumeric()
  {
    return this.isThematicNumeric;
  }
  
}
