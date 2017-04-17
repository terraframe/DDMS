package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.List;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;

public class Converter implements ConverterIF
{
  private TargetContextIF context;

  public Converter(TargetContextIF context)
  {
    this.context = context;
  }

  @Override
  public void create(Transient source)
  {
    try
    {
      Mutable business = this.context.newMutable(source.getType());
      boolean hasValues = false;

      List<TargetFieldIF> fields = this.context.getFields(source.getType());

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
}
