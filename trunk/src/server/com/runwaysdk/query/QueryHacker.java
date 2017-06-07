package com.runwaysdk.query;
import java.util.Map;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdTableClassIF;

/**
 * This class exists as a way to hack around protected method permissions in the query API.
 */
public class QueryHacker
{
  public static TableClassQuery getTableClassQuery(GeneratedTableClassQuery gtcq)
  {
    return gtcq.getComponentQuery();
  }

  public static Map<String, ? extends MdAttributeDAOIF> getAttributeMap(TableClassQuery tcq)
  {
    return tcq.mdAttributeMap;
  }
  
  public static Map<String, ColumnInfo> getColumnInfoMap(TableClassQuery tcq)
  {
    return tcq.columnInfoMap;
  }
  
  public static Attribute attributeFactory(ComponentQuery rootComponentQuery, Selectable selectable, MdTableClassIF definingTableClassIF, String definingTableName, String definingTableAlias, MdAttributeConcreteDAOIF mdAttributeIF, String userDefinedAlias, String userDefinedDisplayLabel)
  {
    return ValueQuery.attributeFactory(rootComponentQuery, selectable, definingTableClassIF, definingTableName, definingTableAlias, mdAttributeIF, userDefinedAlias, userDefinedDisplayLabel);
  }
}