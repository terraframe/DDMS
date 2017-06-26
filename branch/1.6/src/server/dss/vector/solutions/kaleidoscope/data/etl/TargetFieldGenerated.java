package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.util.IDGenerator;

public class TargetFieldGenerated extends TargetField implements TargetFieldIF, Reloadable
{

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    return new FieldValue(IDGenerator.nextID(), true);
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldGeneratedBinding field = new TargetFieldGeneratedBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setColumnLabel(this.getLabel());
    field.apply();
  }

}
