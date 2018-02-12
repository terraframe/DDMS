/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;

public class Property extends PropertyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID         = 1235777070211L;

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
      return this.getDisplayLabel().getValue();
    }

    return this.getClassDisplayLabel();
  }

  protected String buildKey()
  {
    return this.getPropertyName();
  }

  public static PropertyQuery getAllByPackage(java.lang.String pkg)
  {
    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().LIKE(pkg + "%"));
    query.AND(OR.get(query.getDisease().EQ(Disease.getCurrent()), query.getDisease().EQ("NULL")));

    return query;
  }

  public static PropertyQuery getAllEditable()
  {
    PropertyQuery query = new PropertyQuery(new QueryFactory());

//    Condition condition = AND.get(query.getPropertyPackage().NE(PropertyInfo.RESISTANCE_PACKAGE), query.getEditable().EQ(true));
//    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.GENERAL_PACKAGE));
//    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.STANDARDS_PACKAGE));
//    condition = AND.get(condition, query.getPropertyPackage().NE(PropertyInfo.MONITOR_PACKAGE));
//
//    // hide the entry for countryGeoId because that is set via a different process.
//    condition = AND.get(condition, query.getPropertyName().NE(PropertyInfo.COUNTRY_GEO_ID));
//
//    query.WHERE(condition);

    return query;
  }
  
  public static dss.vector.solutions.Property getByPackageAndName(java.lang.String pkg, java.lang.String name, Disease disease)
  {
    Property prop = null;

    PropertyQuery query = new PropertyQuery(new QueryFactory());

    query.WHERE(query.getPropertyPackage().EQ(pkg));
    query.AND(query.getPropertyName().EQ(name));
    
    Disease currentDisease = Disease.getCurrent();
    if (currentDisease == null) {
        query.AND(query.getDisease().EQ("NULL"));
    } else {
        query.AND(OR.get(query.getDisease().EQ(disease), query.getDisease().EQ("NULL")));
    }
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
      query.AND(OR.get(query.getDisease().EQ(disease), query.getDisease().EQ("NULL")));
      
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

  public static dss.vector.solutions.Property getByPackageAndName(java.lang.String pkg, java.lang.String name)
  {
    return getByPackageAndName(pkg, name, Disease.getCurrent());
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
  public static void setUnitsPerDay(Integer unitsPerDay)
  {
    Property property = Property.getByPackageAndName(PropertyInfo.STANDARDS_PACKAGE, PropertyInfo.DEFAULT_UNITS);

    property.appLock();
    property.setPropertyValue(unitsPerDay.toString());
    property.apply();
  }

  public static void setFlag(InputStream stream)
  {
  }

}
