package dss.vector.solutions.kaleidoscope.report;

import java.util.Collection;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeBooleanDAOIF;
import com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDecimalDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDoubleDAOIF;
import com.runwaysdk.dataaccess.MdAttributeFloatDAOIF;
import com.runwaysdk.dataaccess.MdAttributeIntegerDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLongDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTextDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTimeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.sql.MdAttributePrimitive_SQL;
import com.runwaysdk.session.Session;

public class RemoteQueryBuilder implements Reloadable
{
  private ValueQuery vQuery;

  private boolean    includeResults;

  public RemoteQueryBuilder(ValueQuery vQuery, boolean includeResults)
  {
    this.vQuery = vQuery;
    this.includeResults = includeResults;
  }

  public RemoteQuery build()
  {
    RemoteQuery query = new RemoteQuery();

    List<Selectable> selectableList = this.vQuery.getSelectableRefs();

    for (Selectable selectable : selectableList)
    {
      MdAttributeConcreteDAOIF mdAttributeIF = selectable.getMdAttributeIF();

      AttributeMetadataIF attributeMetadata = RemoteQueryBuilder.buildAttributeMetadata(mdAttributeIF);

      query.addAttribute(attributeMetadata);
    }

    if (this.includeResults)
    {
      OIterator<ValueObject> iterator = this.vQuery.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          ValueObject object = iterator.next();

          RemoteResult result = new RemoteResult();

          Collection<String> names = query.getAttributeNames();

          for (String name : names)
          {
            String value = object.getValue(name);

            result.setValue(name, value);
          }

          query.addResult(result);
        }
      }
      finally
      {
        iterator.close();
      }
    }

    return query;
  }

  public static AttributeMetadataIF buildAttributeMetadata(MdAttributeConcreteDAOIF mdAttributeIF)
  {
    String name = mdAttributeIF.definesAttribute();
    String label = mdAttributeIF.getDisplayLabel(Session.getCurrentLocale());
    boolean required = mdAttributeIF.isRequired();

    if (mdAttributeIF instanceof MdAttributeBooleanDAOIF)
    {
      return new AttributeBooleanMetadata(name, label, required);
    }
    else if (mdAttributeIF instanceof MdAttributeCharacterDAOIF)
    {
      if (! ( mdAttributeIF instanceof MdAttributePrimitive_SQL ))
      {
        Integer size = new Integer( ( (MdAttributeCharacterDAOIF) mdAttributeIF ).getSize());

        return new AttributeCharacterMetadata(name, label, required, size);
      }
      else
      {
        return new AttributeCharacterMetadata(name, label, required, 0);
      }
    }
    else if (mdAttributeIF instanceof MdAttributeTextDAOIF)
    {
      return new AttributeTextMetadata(name, label, required);
    }
    else if (mdAttributeIF instanceof MdAttributeIntegerDAOIF || mdAttributeIF instanceof MdAttributeLongDAOIF)
    {
      return new AttributeLongMetadata(name, label, required);
    }
    else if (mdAttributeIF instanceof MdAttributeFloatDAOIF || mdAttributeIF instanceof MdAttributeDoubleDAOIF)
    {
      if (! ( mdAttributeIF instanceof MdAttributePrimitive_SQL ))
      {
        Integer precision = new Integer( ( (MdAttributeDecDAOIF) mdAttributeIF ).getLength());
        Integer scale = new Integer( ( (MdAttributeDecDAOIF) mdAttributeIF ).getDecimal());

        return new AttributeDoubleMetadata(name, label, required, precision, scale);
      }

      return new AttributeDoubleMetadata(name, label, required, 1, 0);
    }
    else if (mdAttributeIF instanceof MdAttributeDecimalDAOIF)
    {
      if (! ( mdAttributeIF instanceof MdAttributePrimitive_SQL ))
      {
        Integer precision = new Integer( ( (MdAttributeDecDAOIF) mdAttributeIF ).getLength());
        Integer scale = new Integer( ( (MdAttributeDecDAOIF) mdAttributeIF ).getDecimal());

        return new AttributeDecimalMetadata(name, label, required, precision, scale);
      }

      return new AttributeDecimalMetadata(name, label, required, 1, 0);
    }
    else if (mdAttributeIF instanceof MdAttributeDateDAOIF)
    {
      return new AttributeDateMetadata(name, label, required);
    }
    else if (mdAttributeIF instanceof MdAttributeTimeDAOIF)
    {
      return new AttributeTimeMetadata(name, label, required);
    }
    else if (mdAttributeIF instanceof MdAttributeDateTimeDAOIF)
    {
      return new AttributeDateTimeMetadata(name, label, required);
    }

    throw new ProgrammingErrorException("Unsupported attribute type [" + mdAttributeIF.getClass().getName() + "]");
  }
}
