/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.constants.IndexTypes;
import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.constants.MdAttributeCharacterInfo;
import com.runwaysdk.constants.MdWebCharacterInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebCharacter;

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