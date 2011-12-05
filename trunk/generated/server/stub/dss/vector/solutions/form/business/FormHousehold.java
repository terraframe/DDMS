package dss.vector.solutions.form.business;

import java.util.List;

import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.QueryBuilder;

public class FormHousehold extends FormHouseholdBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 786880441;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormHousehold";

  public FormHousehold()
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

  @Override
  public FormBedNet[] getBedNets()
  {
    FormBedNetQuery query = new FormBedNetQuery(new QueryFactory());
    query.WHERE(query.getHousehold().EQ(this));

    OIterator<? extends FormBedNet> it = query.getIterator();

    try
    {
      List<? extends FormBedNet> list = it.getAll();

      return list.toArray(new FormBedNet[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public static FormHousehold getByHouseholdId(String householdId)
  {
    FormHouseholdQuery query = new FormHouseholdQuery(new QueryFactory());
    query.WHERE(query.getOid().EQ(householdId));

    OIterator<? extends FormHousehold> it = query.getIterator();

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

  public static FormHousehold getByHouseholdId(String surveyId, String householdId)
  {
    FormHouseholdQuery query = new FormHouseholdQuery(new QueryFactory());
    query.WHERE(AND.get(query.getSurvey().getOid().EQ(surveyId), query.getOid().EQ(householdId)));

    OIterator<? extends FormHousehold> it = query.getIterator();

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

  public static ValueQuery getHouseIds(String value)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    FormHouseholdQuery q = new FormHouseholdQuery(valueQuery);

    SelectableChar orderBy = q.getOid(FormHousehold.OID);
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
