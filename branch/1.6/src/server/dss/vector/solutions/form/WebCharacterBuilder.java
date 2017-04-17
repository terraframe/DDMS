/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.constants.IndexTypes;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdWebCharacterInfo;
import com.runwaysdk.dataaccess.AttributeIF;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.attributes.ImmutableAttributeProblem;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebCharacter;

import dss.vector.solutions.generator.MdFormUtil;

public class WebCharacterBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebCharacterBuilder()
  {
    super();
  }

  public WebCharacterBuilder(MdWebCharacter mdWebCharacter)
  {
    super(mdWebCharacter);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebCharacterBuilder();
  }

  @Override
  public MdWebCharacter getMdField()
  {
    return (MdWebCharacter) super.getMdField();
  }

  @Override
  protected void update()
  {
    MdWebCharacter mdWebAttribute = this.getMdField();
    MdAttributeConcrete mdAttribute = (MdAttributeConcrete) mdWebAttribute.getDefiningMdAttribute();

    if (mdAttribute.getAttributeName().equals(MdFormUtil.OID))
    {
      if (mdWebAttribute.isModified(MdWebCharacter.UNIQUE))
      {
        MdClassDAOIF mdClass = mdWebAttribute.getMdClass();
        MdAttributeDAOIF mdAttributeIF = MdWebCharacter.getUniqueMd(); 
        EntityDAOIF mdWebAttributeDAO = BusinessFacade.getEntityDAO(mdWebAttribute);
        AttributeIF attribute = mdWebAttributeDAO.getAttributeIF(MdWebCharacter.UNIQUE);
        String msg = "The [" + MdWebCharacter.UNIQUE + "] flag on [" + MdFormUtil.OID + "] can not be changed.";

        ImmutableAttributeProblem problem = new ImmutableAttributeProblem(mdWebAttribute.getId(), mdClass, mdAttributeIF, msg, attribute);
        problem.throwIt();
      }
    }

    super.update();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdAttributeCharacter mdAttributeCharacter = (MdAttributeCharacter) mdAttribute;
    MdWebCharacter mdWebCharacter = this.getMdField();

    mdAttributeCharacter.setDatabaseSize(mdWebCharacter.getMaxLength());

    String unique = mdWebCharacter.getValue(MdWebCharacterInfo.UNIQUE);

    if (unique != null && unique.equalsIgnoreCase(MdAttributeBooleanInfo.TRUE))
    {
      mdAttributeCharacter.setValue(MdAttributeCharacterInfo.INDEX_TYPE, IndexTypes.UNIQUE_INDEX.getId());
    }
    else
    {
      mdAttributeCharacter.setValue(MdAttributeCharacterInfo.INDEX_TYPE, IndexTypes.NO_INDEX.getId());
    }

    super.updateMdAttribute(mdAttributeCharacter);
  }
}