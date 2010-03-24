package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.PropertyInfo;


public class ResistanceProperty extends ResistancePropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 637641852;

  public ResistanceProperty()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if (this.getPropertyName() != null)
    {
      return this.getPropertyName();
    }
    return super.buildKey();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
  
  @Override
  @Transaction
  public void apply()
  {
    validatePropertyValue();
    
    super.apply();
  }
  
  @Override
  public void validatePropertyValue()
  {
    String name = this.getPropertyName();
    Integer value = this.getPropertyValue();
    
    if(name != null && value != null)
    {
      if(name.equals(PropertyInfo.ADULT_DDA_RESISTANCE))
      {     
        ResistanceProperty.validateValue(this, ResistanceProperty.getProperty(PropertyInfo.ADULT_DDA_SUSCEPTIBILE));        
      }
      else if(name.equals(PropertyInfo.ADULT_DDA_SUSCEPTIBILE))
      {     
        ResistanceProperty.validateValue(ResistanceProperty.getProperty(PropertyInfo.ADULT_DDA_RESISTANCE), this);        
      }
      else if(name.equals(PropertyInfo.LARVAE_DDA_RESISTANCE))
      {     
        ResistanceProperty.validateValue(this, ResistanceProperty.getProperty(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE));        
      }
      else if(name.equals(PropertyInfo.LARVAE_DDA_SUSCEPTIBILE))
      {     
        ResistanceProperty.validateValue(ResistanceProperty.getProperty(PropertyInfo.LARVAE_DDA_RESISTANCE), this);        
      }
    }
  }

  private static void validateValue(ResistanceProperty lowerProperty, ResistanceProperty upperProperty)
  {
    Integer lowerValue = lowerProperty.getPropertyValue();
    Integer upperValue = upperProperty.getPropertyValue();
    
    if(!(lowerValue < upperValue))
    {
      ResistancePropertyException e = new ResistancePropertyException();
      e.setLowerLabel(lowerProperty.getDisplayLabel().getValue());
      e.setLowerValue(lowerProperty.getPropertyValue());
      e.setUpperLabel(upperProperty.getDisplayLabel().getValue());
      e.setUpperValue(upperProperty.getPropertyValue());
      e.apply();
      
      throw e;
    }
  }

  public static Integer getPropertyValue(String propertyName)
  {
    ResistanceProperty property = getProperty(propertyName);
    
    return property.getPropertyValue();
  }

  private static ResistanceProperty getProperty(String propertyName)
  {
    ResistanceProperty property = (ResistanceProperty) ResistanceProperty.get(ResistanceProperty.CLASS, propertyName);
    return property;
  }

}
