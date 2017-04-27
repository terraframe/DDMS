package dss.vector.solutions.util;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;

public class SelectableSQLKey extends SelectableSQLCharacter
{
  private MdAttributeReferenceDAOIF mdAttribute;

  public SelectableSQLKey(boolean isAggregate, ValueQuery rootQuery, String attributeName, String sql, MdAttributeReferenceDAOIF mdAttribute)
  {
    super(isAggregate, rootQuery, attributeName, sql);

    this.mdAttribute = mdAttribute;
  }

  public MdAttributeReferenceDAOIF getMdAttribute()
  {
    return mdAttribute;
  }
}
