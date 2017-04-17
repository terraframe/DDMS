/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdWebDate;

public class WebDateBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDateBuilder()
  {
    super();
  }

  public WebDateBuilder(MdWebDate mdWebDate)
  {
    super(mdWebDate);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDateBuilder();
  }

  @Override
  public MdWebDate getMdField()
  {
    return (MdWebDate) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdAttributeDate mdAttributeDate = (MdAttributeDate) mdAttribute;

    MdWebDate mdWebDate = this.getMdField();

    mdAttributeDate.setStartDate(mdWebDate.getStartDate());
    mdAttributeDate.setEndDate(mdWebDate.getEndDate());
    mdAttributeDate.setAfterTodayExclusive(mdWebDate.getAfterTodayExclusive());
    mdAttributeDate.setAfterTodayInclusive(mdWebDate.getAfterTodayInclusive());
    mdAttributeDate.setBeforeTodayExclusive(mdWebDate.getBeforeTodayExclusive());
    mdAttributeDate.setBeforeTodayInclusive(mdWebDate.getBeforeTodayInclusive());

    super.updateMdAttribute(mdAttributeDate);
  }
}