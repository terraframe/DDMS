package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.generator.MdFormUtil;

public class Converter implements ConverterIF, Reloadable
{
  private TargetContextIF context;

  private Disease         disease;

  public Converter(TargetContextIF context, Disease disease)
  {
    this.context = context;
    this.disease = disease;
  }

  @Override
  public void create(Transient source)
  {
    try
    {
      List<TargetFieldIF> fields = this.context.getFields(source.getType());
      String oid = this.getOid(source, fields);

      BusinessDAO business = this.context.getOrCreateMutable(source.getType(), oid);
      boolean hasValues = false;

      for (TargetFieldIF field : fields)
      {
        String attributeName = field.getName();

        MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) business.getMdAttributeDAO(attributeName);

        // get value can intentionally fail if attempting to get the value of a location that is on the
        // location exclusion list. Note the custom effor after this TRY statement.
        FieldValue fValue = field.getValue(mdAttribute, source);
        Object value = fValue.getValue();

        if (value != null)
        {
          business.setValue(attributeName, value);
        }

        hasValues = hasValues || !fValue.isBlank();
      }

      business.setValue(MdFormUtil.DISEASE, this.disease.getId());

      if (oid == null)
      {
        business.setValue(MdFormUtil.OID, LocalProperty.getNextId());
      }

      /*
       * Before apply ensure that at least one source field was not blank
       */
      if (hasValues)
      {
        TargetDefinitionIF definition = this.context.getDefinition(source.getType());
        PersistenceStrategyIF strategy = definition.getStrategy();

        strategy.handle(business);
      }
    }
    catch (ExclusionException e)
    {
      // Do nothing. It's likely that a source value was not found because of location exclusions
    }
  }

  private String getOid(Transient source, List<TargetFieldIF> fields)
  {
    for (TargetFieldIF field : fields)
    {
      if (field.getName().equals(MdFormUtil.OID))
      {
        MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) MdAttributeConcreteDAO.getByKey(field.getKey());

        FieldValue fValue = field.getValue(mdAttribute, source);
        return fValue.getValue().toString();
      }
    }
    return null;
  }
}
