package csu.mrc.ivcc.mdss;


import java.util.regex.Pattern;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.PropertyBase;
import csu.mrc.ivcc.mdss.PropertyQuery;

public class Property extends PropertyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID                       = 1235777070211L;

  public static final String RESISTANCE_PACKAGE                     = "csu.mrc.ivcc.mdss.entomology.ResistantanceCutOff";

  public static final String ADULT_DDA_RESISTANCE                   = "aDDAR";

  public static final String ADULT_DDA_SUSCEPTIBILE                 = "aDDAS";

  public static final String LARVAE_DDA_RESISTANCE                  = "lDDAR";

  public static final String LARVAE_DDA_SUSCEPTIBILE                = "lDDAS";

  public static final String ADULT_KNOCK_DOWN_RESISTANCE            = "aAKnockDownR";

  public static final String ADULT_KNOCK_DOWN_POTENTIAL_RESISTANCE  = "aAKnockDownPR";

  public static final String LARVAE_KNOCK_DOWN_RESISTANCE           = "lAKnockDownR";

  public static final String LARVAE_KNOCK_DOWN_POTENTIAL_RESISTANCE = "lAKnockDownPR";

  public Property()
  {
    super();
  }

  public static csu.mrc.ivcc.mdss.Property getByPackageAndName(java.lang.String pkg,
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