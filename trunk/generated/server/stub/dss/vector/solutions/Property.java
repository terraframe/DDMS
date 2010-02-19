package dss.vector.solutions;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Property extends PropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235777070211L;

  public static final long  SHORT_ID_LENGTH  = 8;
  
  public static final int RESERVED_SHORT_ID_SPACES = 23;

  public static final long  MAX_ID           = (long) Math.pow(30, SHORT_ID_LENGTH);

  public Property()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getDisplayLabel() != null)
    {
      return this.getDisplayLabel();
    }

    return super.toString();
  }

  protected String buildKey()
  {
    return this.getPropertyName();
  }

  public static PropertyQuery getAllByPackage(java.lang.String pkg)
  {
    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().LIKE(pkg + "%"));

    return query;
  }

  public static PropertyQuery getAllEditable()
  {
    PropertyQuery query = new PropertyQuery(new QueryFactory());

    Condition condition = AND.get(query.getPropertyPackage().NE(PropertyInfo.RESISTANCE_PACKAGE), query.getEditable().EQ(true));
    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.GENERAL_PACKAGE));
    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.STANDARDS_PACKAGE));
    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.MONITOR_PACKAGE));

    // FIXME hide the entry for countryGeoId
    condition = AND.get(condition, query.getPropertyName().NE(PropertyInfo.COUNTRY_GEO_ID));
    
    query.WHERE(condition);

    return query;
  }

  public static dss.vector.solutions.Property getByPackageAndName(java.lang.String pkg, java.lang.String name)
  {
    Property prop = null;

    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().EQ(pkg));
    query.AND(query.getPropertyName().EQ(name));

    OIterator<? extends Property> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        prop = iterator.next();
      }
    }
    finally
    {
      iterator.close();
    }

    if (prop == null)
    {
      query = new PropertyQuery(new QueryFactory());

      query.WHERE(query.getPropertyPackage().LIKE(pkg + "%"));
      query.AND(query.getPropertyName().EQ(name));

      iterator = query.getIterator();

      try
      {
        if (iterator.hasNext())
        {
          prop = iterator.next();
        }
      }
      finally
      {
        iterator.close();
      }
    }

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
      String msg = "The property value does not match the property validator regexp.";

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
    // if(this.getPropertyType() == "Date")
    // {

    // }

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
      Property format = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE, PropertyInfo.SYSTEM_DATE_FORMAT);

      return prop.getPropertyDate(format.getPropertyValue());
    }
  }

  @Transaction
  @Authenticate
  public static String getNextId()
  {
    Property currentValue = Property.getByPackageAndName(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_COUNTER);
    currentValue.appLock();

    Long counter = currentValue.getPropertyLong();
    int segments = Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_SEGMENTS);
    int offset = RESERVED_SHORT_ID_SPACES + Property.getInt(PropertyInfo.SYSTEM_PACKAGE, PropertyInfo.SHORT_ID_OFFSET);

    long totalOffset = ( MAX_ID / segments ) * offset;

    counter++;
    currentValue.setPropertyValue(counter.toString());
    currentValue.apply();

    // TODO:perhaps a check that the address space has not been overflowed
    // should be added?

    return Base30.toBase30String(totalOffset + counter, 8);
  }

  @Authenticate
  @Transaction
  public static void setUnitsPerDay(Integer unitsPerDay)
  {
    Property property = Property.getByPackageAndName(PropertyInfo.STANDARDS_PACKAGE, PropertyInfo.DEFAULT_UNITS);

    property.lock();
    property.setPropertyValue(unitsPerDay.toString());
    property.apply();
  }

  public static void setFlag(InputStream stream)
  {
  }

}
