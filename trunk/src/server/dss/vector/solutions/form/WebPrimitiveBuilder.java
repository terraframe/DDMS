/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;

public abstract class WebPrimitiveBuilder extends WebAttributeBuilder implements Reloadable
{
  private MdWebSingleTermGrid mdWebSingleTermGrid;

  public WebPrimitiveBuilder()
  {
    super();
  }

  public WebPrimitiveBuilder(MdWebPrimitive mdWebPrimitive)
  {
    super(mdWebPrimitive);
  }

  @Override
  public MdWebPrimitive getMdField()
  {
    return (MdWebPrimitive) super.getMdField();
  }

  @Override
  protected MdAttributeConcrete newMdAttribute()
  {
    String mdAttributeType = this.getMdField().getExpectedMdAttributeType();

    return (MdAttributeConcrete) BusinessFacade.newBusiness(mdAttributeType);

  }

  public void setMdWebSingleTermGrid(MdWebSingleTermGrid mdWebSingleTermGrid)
  {
    this.mdWebSingleTermGrid = mdWebSingleTermGrid;
  }

  public MdWebSingleTermGrid getMdWebSingleTermGrid()
  {
    return mdWebSingleTermGrid;
  }

  @Override
  protected void create()
  {
    super.create();

    if (this.getMdWebForm() == null && this.mdWebSingleTermGrid != null)
    {
      mdWebSingleTermGrid.addMdFields(this.getMdField()).apply();
    }
  }
}