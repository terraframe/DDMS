package dss.vector.solutions.form;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.attributes.EmptyValueProblem;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.GeoHierarchy;

/**
 * Builder class to construct a WebField from an MdWebFieldDTO.
 * 
 */
public abstract class WebFieldBuilder implements Reloadable
{
  private MdWebField mdField;

  private MdWebForm  mdWebForm;

  public WebFieldBuilder()
  {
    this.mdField = null;
  }

  public WebFieldBuilder(MdWebField mdField)
  {
    this.mdField = mdField;
  }

  public abstract WebFieldBuilder copy();

  public MdWebField getMdField()
  {
    return this.mdField;
  }

  public void setMdField(MdWebField mdField)
  {
    this.mdField = mdField;
  }

  public MdWebForm getMdWebForm()
  {
    return mdWebForm;
  }

  public void setMdWebForm(MdWebForm mdWebForm)
  {
    this.mdWebForm = mdWebForm;
  }

  /**
   * Validates an MdField and sets the field name.
   * 
   * @return
   */
  protected void setupAndValidateMdField()
  {
    // FIXME how to validate non-new display labels? The old one is just being
    // used

    if (mdField.isNew())
    {
      // the display label is required because it is used to generate the
      // field
      // name, and it is the only UI label available to the user.
      String display = mdField.getDisplayLabel().getValue();
      if (display == null || display.trim().length() == 0)
      {
        String msg = "";
        MdAttributeDAOIF md = MdField.getDisplayLabelMd();
        EntityDAOIF field = BusinessFacade.getEntityDAO(mdField);
        EmptyValueProblem p = new EmptyValueProblem(mdField.getId(), mdField.getMdClass(), md, msg, field.getAttributeIF(MdField.DISPLAYLABEL));
        p.throwIt();
      }

      convertDisplayLabelToFieldName(mdField);
    }
  }

  @AbortIfProblem
  private void convertDisplayLabelToFieldName(MdField mdField)
  {
    String displayLabel = mdField.getDisplayLabel().getValue();
    String fieldName = GeoHierarchy.getSystemName(displayLabel, "Attr", false);
    mdField.setFieldName(fieldName);
  }

  /**
   * Builds a new MdAttributeDAO based on the MdField type. Subclasses should
   * override and call this method to set attribute specific information.
   * 
   * @param mdClassId
   * 
   * @return
   */
  protected void create()
  {
    // update the field order to one greater than the last field (to simply
    // append it)

    this.setupAndValidateMdField();

    Integer order = this.getFieldOrder();

    if (mdWebForm != null)
    {
      mdField.setDefiningMdForm(mdWebForm);
    }

    mdField.setFieldOrder(order);
    mdField.apply();
  }

  protected Integer getFieldOrder()
  {
    if (mdWebForm != null)
    {
      return MdFormUtil.getHighestOrder(mdWebForm);
    }

    return 0;
  }

  protected void update()
  {
    mdField.apply();
  }

  public void delete()
  {
    mdField.delete();
  }
}