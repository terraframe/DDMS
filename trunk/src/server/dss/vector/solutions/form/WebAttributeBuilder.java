/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.AttributeIF;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.attributes.ImmutableAttributeProblem;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MetadataCannotBeDeletedException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebPrimitive;

import dss.vector.solutions.generator.MdFormUtil;

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

    if (mdAttribute.getAttributeName().equals(MdFormUtil.OID))
    {
      if (mdWebAttribute.isModified(MdWebAttribute.REQUIRED))
      {
        MdClassDAOIF mdClass = mdWebAttribute.getMdClass();
        MdAttributeDAOIF mdAttributeIF = MdWebAttribute.getRequiredMd();
        EntityDAOIF mdWebAttributeDAO = BusinessFacade.getEntityDAO(mdWebAttribute);
        AttributeIF attribute = mdWebAttributeDAO.getAttributeIF(MdWebAttribute.REQUIRED);
        String msg = "The [" + MdWebAttribute.REQUIRED + "] flag on [" + MdFormUtil.OID + "] can not be changed.";

        ImmutableAttributeProblem problem = new ImmutableAttributeProblem(mdWebAttribute.getId(), mdClass, mdAttributeIF, msg, attribute);
        problem.throwIt();
      }
    }

    mdAttribute.appLock();
    this.setupAndValidateMdField();
    this.updateMdAttribute(mdAttribute);
    mdAttribute.apply();

    super.update();
  }

  @Override
  public void delete()
  {
    MdWebAttribute mdWebAttribute = this.getMdField();

    MdAttributeConcrete mdAttribute = (MdAttributeConcrete) mdWebAttribute.getDefiningMdAttribute();

    // IMPORTANT: We cannot delete the OID field regardless of the status of the
    // removable flag. This is due to the fact that the excel import/export
    // funcationality has a lot of hard-coded dependencies on the OID field.
    if (mdAttribute.getAttributeName().equals(MdFormUtil.OID))
    {
      MdAttributeDAO mdAttributeDAO = (MdAttributeDAO) MdAttributeDAO.get(mdAttribute.getId()).getBusinessDAO();
      String msg = "The hard-coded field [" + MdFormUtil.OID + "] cannot be deleted";

      throw new MetadataCannotBeDeletedException(msg, mdAttributeDAO);
    }

    super.delete();

    mdAttribute.delete();
  }
}