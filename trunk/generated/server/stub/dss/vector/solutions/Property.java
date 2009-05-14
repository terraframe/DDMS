package dss.vector.solutions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Property extends PropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235777070211L;

  public static final long SHORT_ID_LENGTH = 8;
  public static final long MAX_ID = (long) Math.pow(30,SHORT_ID_LENGTH);

  public Property()
  {
    super();
  }

  public static PropertyQuery getAllByPackage(java.lang.String pkg)
  {
    Property prop = null;

    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().LIKE(pkg+"%"));

    return query;
  }

  public static dss.vector.solutions.Property getByPackageAndName(java.lang.String pkg,
      java.lang.String name)
  {
    Property prop = null;

    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().EQ(pkg));
    query.AND(query.getPropertyName().EQ(name));

    OIterator<? extends Property> iterator = query.getIterator();

    if (iterator.hasNext())
    {
      prop = iterator.next();
    }
    else
    {
      query = new PropertyQuery(new QueryFactory());

      query.WHERE(query.getPropertyPackage().LIKE(pkg+"%"));
      query.AND(query.getPropertyName().EQ(name));

      iterator = query.getIterator();
      if (iterator.hasNext())
      {
        prop = iterator.next();
      }

    }


    iterator.close();

    return prop;
  }

  @Override
  public void apply()
  {
    validateProperty();
    super.apply();
  }

  public void validateProperty()
  {
    if (!Pattern.matches(this.getPropertyValidator(), this.getPropertyValue()))
    {
      String msg = "The proprtey value does not match the property validator regexp.";

      PropertyValidationFailedException e = new PropertyValidationFailedException(msg);
      if (this.getValidValues() == null)
      {
        e.setValidValues("");
      }
      else
      {
        e.setValidValues(this.getValidValues());
      }
      e.apply();

      throw e;
    }
  }

  public Integer getPropertyInteger()
  {
    String value = this.getPropertyValue();

    if (value == null)
    {
      return null;
    }
    else
    {
      return new Integer(value);
    }
  }

  public Long getPropertyLong()
  {
    String value = this.getPropertyValue();

    if (value == null)
    {
      return null;
    }
    else
    {
      return new Long(value);
    }
  }


  public Date getPropertyDate(String format)
  {
    SimpleDateFormat formatter = new SimpleDateFormat(format);

    try
    {
      return formatter.parse(this.getPropertyValue());
    }
    catch (ParseException e)
    {
      String msg = "Property [" + this.getPropertyName() + "] does not conform to the date format.";
      InvalidEpiStartFormat ex = new InvalidEpiStartFormat(msg);
      ex.apply();

      throw ex;
    }
  }

  public static java.lang.String getStr(java.lang.String pkg, java.lang.String name)
  {
    Property prop = getByPackageAndName(pkg, name);

    if (prop == null)
    {
      return null;
    }
    else
    {
      return prop.getPropertyValue();
    }
  }

  public static java.lang.Integer getInt(java.lang.String pkg, java.lang.String name)
  {
    Property prop = getByPackageAndName(pkg, name);

    if (prop == null)
    {
      return null;
    }
    else
    {
      return prop.getPropertyInteger();
    }
  }


  public static java.lang.Long getLong(java.lang.String pkg, java.lang.String name)
  {
    Property prop = getByPackageAndName(pkg, name);

    if (prop == null)
    {
      return null;
    }
    else
    {
      return prop.getPropertyLong();
    }
  }

  public static Date getDate(String packageName, String propertyName)
  {
    Property prop = getByPackageAndName(packageName, propertyName);

    if (prop == null)
    {
      return null;
    }
    else
    {
      Property format = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE,
          PropertyInfo.DATE_FORMAT);

      return prop.getPropertyDate(format.getPropertyValue());
    }
  }

  @Transaction
  public static String getNextId()
  {
    Property currentValue = Property.getByPackageAndName(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_COUNTER);
    currentValue.lock();
    Long counter = currentValue.getPropertyLong();
    int segments = Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_SEGMENTS);
    int offset = Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_OFFSET);

    long totalOffset = (MAX_ID/segments)*offset;

    counter++;
    currentValue.setPropertyValue(counter.toString());
    currentValue.apply();

    //TODO:perhaps a check that the address space has not been overflowed should be added?

    return Base30.toBase30String(totalOffset+counter,8);
  }

}
