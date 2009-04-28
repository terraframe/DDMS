package dss.vector.solutions;


import java.util.regex.Pattern;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.PropertyBase;
import dss.vector.solutions.PropertyQuery;

public class Property extends PropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID                       = 1235777070211L;

  public static final String RESISTANCE_PACKAGE                     = "dss.vector.solutions.entomology.ResistantanceCutOff";

  public static final String ADULT_DDA_RESISTANCE                   = "aDDAR";

  public static final String ADULT_DDA_SUSCEPTIBILE                 = "aDDAS";

  public static final String LARVAE_DDA_RESISTANCE                  = "lDDAR";

  public static final String LARVAE_DDA_SUSCEPTIBILE                = "lDDAS";

  public static final String SHORT_ID_SEGMENTS                      = "SHORT_ID_SEGMENTS";

  public static final String SHORT_ID_OFFSET                        = "SHORT_ID_OFFSET";

  public static final String SHORT_ID_COUNTER                       = "SHORT_ID_COUNTER";

  public static final String DATE_FORMAT                            = "dateFormat";

  public static final String DATE_FORMAT_SHORT                      = "dateFormatShort";

  public static final String EPI_START                              = "epiStart";

  public static final String COUNTRY_GEO_ID                         = "countryGeoId";



  public Property()
  {
    super();
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
      e.setValidValues("0-100");
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

}
