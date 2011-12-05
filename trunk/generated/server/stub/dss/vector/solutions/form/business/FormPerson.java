package dss.vector.solutions.form.business;

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.QueryBuilder;

public class FormPerson extends FormPersonBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -1668815263;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormPerson";

  public FormPerson()
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

  public static ValueQuery getPersonIds(String value)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    FormPersonQuery q = new FormPersonQuery(valueQuery);

    SelectableChar orderBy = q.getOid(FormPerson.OID);
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
