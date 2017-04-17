package dss.vector.solutions.entomology.assay;


public class AdultAgeRange extends AdultAgeRangeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543767489L;
  
  public AdultAgeRange()
  {
    super();
  }
  
  public AdultAgeRange(com.runwaysdk.business.MutableWithStructs entity, String structName)
  {
    super(entity, structName);
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  public void validateStartPoint()
  {
    super.validateStartPoint();
    
    if(this.getStartPoint() < 0 || this.getStartPoint() > 20)
    {
      String msg = "The start age for an adult assay age range must be between 0 and 20 days";
      
      InvalidAgeProblem p = new InvalidAgeProblem(msg);
      p.setAge(this.getStartPoint());
      p.setNotification(this, STARTPOINT);
      p.apply();
      p.throwIt();      
    }
  }
  
  @Override
  public void validateEndPoint()
  {
    super.validateEndPoint();
    
    if(this.getEndPoint() < 0 || this.getEndPoint() > 20)
    {
      String msg = "The end age for an adult assay age range must be between 0 and 20 days";

      InvalidAgeProblem p = new InvalidAgeProblem(msg);
      p.setAge(this.getEndPoint());
      p.setNotification(this, ENDPOINT);
      p.apply();
      p.throwIt();      
    }
  }
  
  private void validateRange()
  {
    if(this.getStartPoint() > this.getEndPoint())
    {
      String msg = "The start point of a adult assay age range must be less or equal to the end point";

      InvalidAgeRangeProblem p = new InvalidAgeRangeProblem(msg);
      p.setStartPoint(this.getStartPoint());
      p.setEndPoint(this.getEndPoint());
      p.setNotification(this, STARTPOINT);
      p.apply();
      p.throwIt();      
    }    
  }
  
  public void validate()
  {
    validateStartPoint();
    validateEndPoint();
    validateRange();
  }
  
  @Override
  public void apply()
  {
    validate();
        
    super.apply();
  }

}
