package dss.vector.solutions.form.business;

import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.QueryBuilder;

public class FormBedNet extends FormBedNetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -1917753186;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormBedNet";

  public FormBedNet()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getOid() != null && this.getOid().length() > 0 && this.getSurvey() != null)
    {
      return this.getSurvey().getKey() + " " + this.getOid();
    }

    return super.buildKey();
  }

  public static FormBedNet getByBedNetId(String suveryId, String netId)
  {
    FormBedNetQuery query = new FormBedNetQuery(new QueryFactory());
    query.WHERE(AND.get(query.getSurvey().getOid().EQ(suveryId), query.getOid().EQ(netId)));

    OIterator<? extends FormBedNet> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public static ValueQuery getNetIds(String value)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    FormBedNetQuery q = new FormBedNetQuery(valueQuery);

    SelectableChar orderBy = q.getOid(FormBedNet.OID);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { orderBy };

    Condition[] conditions = new Condition[] {};
    LeftJoinEq[] joins = new LeftJoinEq[] {};

    if (value != null && value.length() > 0)
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { orderBy };

      QueryBuilder.textLookup(valueQuery, f, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.distinctOrderedLookup(valueQuery, f, orderBy, selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;

  }
}
