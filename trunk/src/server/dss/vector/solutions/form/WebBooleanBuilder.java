/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebBoolean;

public class WebBooleanBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebBooleanBuilder()
  {
    super();
  }

  public WebBooleanBuilder(MdWebBoolean mdWebBoolean)
  {
    super(mdWebBoolean);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebBooleanBuilder();
  }

  @Override
  public MdWebBoolean getMdField()
  {
    return (MdWebBoolean) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdWebBoolean mdField = this.getMdField();
    Boolean defaultValue = mdField.getDefaultValue();
    
    MdAttributeBoolean mdAttributeBoolean = (MdAttributeBoolean) mdAttribute;
    
    if(defaultValue != null)
    {
      mdAttribute.setValue(MdAttributeBooleanInfo.DEFAULT_VALUE, defaultValue.toString());      
    }
    else
    {
      mdAttribute.setValue(MdAttributeBooleanInfo.DEFAULT_VALUE, "");      
    }

    // FIXME allow user defined labels
    if (mdAttributeBoolean.isNew())
    {
      mdAttributeBoolean.getPositiveDisplayLabel().setDefaultValue("true");
      mdAttributeBoolean.getNegativeDisplayLabel().setDefaultValue("false");
    }
    

    super.updateMdAttribute(mdAttributeBoolean);
  }
}