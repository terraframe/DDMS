package dss.vector.solutions.querybuilder;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.generator.MdFormUtil;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class TypeQB extends AbstractQB implements Reloadable
{

  public TypeQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    Iterator<GeneratedEntityQuery> it = queryMap.values().iterator();

    while (it.hasNext())
    {
      GeneratedEntityQuery query = it.next();

      if (! ( query instanceof AllPathsQuery ))
      {
        this.addGeoDisplayLabelQuery(query);

        this.setNumericRestrictions(valueQuery, queryConfig);

        QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, query.get(MdFormUtil.DISEASE));

        QueryUtil.joinTermAllpaths(valueQuery, query.getClassType(), query);

        QueryUtil.getSingleAttribteGridSql(valueQuery, query.getTableAlias());

        valueQuery.FROM(query.getMdClassIF().getTableName(), query.getTableAlias());

        return valueQuery;
      }
    }

    throw new RuntimeException("NULL Query");
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    return new TypeQB(xml, config, layer).construct();
  }
}
