package dss.vector.solutions.util;

import java.util.HashMap;

import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;

public class SelectableSQLKey extends SelectableSQLCharacter
{
  public SelectableSQLKey(boolean isAggregate, ValueQuery rootQuery, String attributeName, String sql)
  {
    super(isAggregate, rootQuery, attributeName, sql);
  }

  public SelectableSQLKey(boolean isAggregate, ValueQuery rootQuery, String attributeName, String sql, MdAttributeReferenceDAOIF mdAttribute)
  {
    super(isAggregate, rootQuery, attributeName, sql);

    HashMap<String, Object> data = new HashMap<String, Object>();
    data.put(MetadataInfo.CLASS, mdAttribute);

    this.setData(data);
  }
}
