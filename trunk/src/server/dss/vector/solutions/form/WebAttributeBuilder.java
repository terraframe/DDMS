/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebPrimitive;

public abstract class WebAttributeBuilder extends WebFieldBuilder implements Reloadable
{
  private MdClass mdClass;

  public WebAttributeBuilder()
  {
    super();
  }

  public WebAttributeBuilder(MdWebAttribute mdWebAttribute)
  {
    super(mdWebAttribute);
  }

  @Override
  public MdWebAttribute getMdField()
  {
    return (MdWebAttribute) super.getMdField();
  }

  public MdClass getMdClass()
  {
    return mdClass;
  }

  public void setMdClass(MdClass mdClass)
  {
    this.mdClass = mdClass;
  }

  @Override
  public void setMdWebForm(MdWebForm mdWebForm)
  {
    super.setMdWebForm(mdWebForm);

    this.setMdClass(mdWebForm.getFormMdClass());
  }

  /**
   * Creates the MdAttribute that maps directly to the MdField.
   * 
   * @param webForm
   * 
   * @return
   */
  @AbortIfProblem
  protected void updateMdAttribute(MdAttributeConcrete mdAttributeConcrete)
  {
    MdWebAttribute mdWebAttribute = this.getMdField();

    if (mdAttributeConcrete.isNew())
    {
      mdAttributeConcrete.setDefiningMdClass(mdClass);
      mdAttributeConcrete.setAttributeName(mdWebAttribute.getFieldName());
    }

    mdAttributeConcrete.setRequired(mdWebAttribute.getRequired());

    String displayLabel = mdWebAttribute.getDisplayLabel().getValue();
    mdAttributeConcrete.getDisplayLabel().setValue(displayLabel);

    String description = mdWebAttribute.getDescription().getValue();
    mdAttributeConcrete.getDescription().setValue(description);
  }

  @Override
  protected void create()
  {
    this.setupAndValidateMdField();
    
    MdAttributeConcrete mdAttribute = this.newMdAttribute();
    this.updateMdAttribute(mdAttribute);
    mdAttribute.apply();

    MdWebAttribute mdWebAttribute = this.getMdField();
    mdWebAttribute.setValue(MdWebPrimitive.DEFININGMDATTRIBUTE, mdAttribute.getId());

    super.create();
  }

  protected abstract MdAttributeConcrete newMdAttribute();

  protected void update()
  {
    MdWebAttribute mdWebAttribute = this.getMdField();
    MdAttributeConcrete mdAttribute = (MdAttributeConcrete) mdWebAttribute.getDefiningMdAttribute();

    mdAttribute.appLock();
    this.setupAndValidateMdField();
    this.updateMdAttribute(mdAttribute);
    mdAttribute.apply();

    super.update();
  }

  @Override
  public void delete()
  {
    super.delete();

    MdWebAttribute mdWebAttribute = this.getMdField();

    MdAttribute mdAttribute = mdWebAttribute.getDefiningMdAttribute();
    mdAttribute.delete();
  }
}