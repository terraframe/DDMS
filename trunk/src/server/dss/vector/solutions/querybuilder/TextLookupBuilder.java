package dss.vector.solutions.querybuilder;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributePrimitive;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.Join;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

public class TextLookupBuilder implements Reloadable
{
  private static final long     WEIGHT = 256;

  private ValueQuery            valueQuery;

  private QueryFactory          factory;

  private String[]              tokenArray;

  private SelectablePrimitive[] searchableArray;

  private SelectablePrimitive[] selectableArray;

  private Condition[]           conditionArray;

  private Join[]                joins;

  public TextLookupBuilder(ValueQuery valueQuery, QueryFactory factory, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray, Join[] joins)
  {
    this.valueQuery = valueQuery;
    this.factory = factory;
    this.tokenArray = tokenArray;
    this.searchableArray = searchableArray;
    this.selectableArray = selectableArray;
    this.conditionArray = conditionArray;
    this.joins = joins;
  }

  public TextLookupBuilder(ValueQuery valueQuery, QueryFactory factory, String[] tokenArray, SelectablePrimitive[] searchableArray, SelectablePrimitive[] selectableArray, Condition[] conditionArray)
  {
    this(valueQuery, factory, tokenArray, searchableArray, selectableArray, conditionArray, new Join[] {});
  }

  public void buildQuery()
  {
    ValueQuery uQ = factory.valueQuery();

    ValueQuery[] valueQueryArray = new ValueQuery[tokenArray.length];

    if (tokenArray.length > 1)
    {
      for (int i = 0; i < tokenArray.length; i++)
      {
        String token = tokenArray[i].toLowerCase();
        valueQueryArray[i] = this.buildQueryForToken(token, WEIGHT, i);
      }
      uQ.UNION(valueQueryArray);
    }
    else
    {
      uQ = this.buildQueryForToken(tokenArray[0].toLowerCase(), WEIGHT, 0);
    }

    // Build outermost select clause. This would be cleaner if the API supported
    // incrementally adding to the select clause. One day that will be
    // supported.
    Selectable[] selectClauseArray = new Selectable[selectableArray.length + 2];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = uQ.get(selectableArray[k].getResultAttributeName());
    }

    selectClauseArray[selectableArray.length] = F.COUNT(uQ.get("weight"), "weight");
    selectClauseArray[selectableArray.length + 1] = F.SUM(uQ.get("weight"), "sum");

    valueQuery.SELECT(selectClauseArray);
    valueQuery.ORDER_BY_DESC(F.COUNT(uQ.get("weight"), "weight"));
    valueQuery.ORDER_BY_DESC(F.SUM(uQ.get("weight"), "sum"));

    for (SelectablePrimitive selectable : selectableArray)
    {
      valueQuery.ORDER_BY_ASC((AttributePrimitive) uQ.get(selectable.getResultAttributeName()));
    }

    valueQuery.HAVING(F.COUNT(uQ.get("weight")).EQ(tokenArray.length));
  }

  private ValueQuery buildQueryForToken(String token, long weight, int i)
  {
    ValueQuery vQ = factory.valueQuery();

    token = token.replace("%", "!%");

    // Build select clause. This would be cleaner if the API supported
    // incrementally adding
    // to the select clause. One day that will be supported.
    SelectablePrimitive[] selectClauseArray = new SelectablePrimitive[selectableArray.length + 1];
    for (int k = 0; k < selectableArray.length; k++)
    {
      selectClauseArray[k] = selectableArray[k];
    }

    String sql = Database.instance().escapeSQLCharacters(token);

    selectClauseArray[selectableArray.length] = vQ.aSQLDouble("weight", "1.0 / (" + Math.pow(weight, i) + " * NULLIF(STRPOS(" + this.concatenate() + ", ' " + sql + "'),0))");
    vQ.SELECT_DISTINCT(selectClauseArray);
    vQ.WHERE(vQ.aSQLCharacter("fields", this.concatenate()).LIKE("% " + token + "%"));

    for (Condition condition : conditionArray)
    {
      vQ.AND(condition);
    }

    for (Join join : joins)
    {
      vQ.AND(join);
    }

    return vQ;
  }

  private String concatenate()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("LOWER(' ' || ");

    for (int i = 0; i < searchableArray.length; i++)
    {
      if (i > 0)
      {
        sb.append(" || ' ' || ");
      }

      // IMPORTANT: The selectable may not be required, as such we must COALESCE
      // the selectable with the empty string in order for rows with NULL values
      // to work.
      sb.append("COALESCE(" + searchableArray[i].getDbQualifiedName() + ",'')");
    }

    sb.append(")");
    return sb.toString();
  }

}
