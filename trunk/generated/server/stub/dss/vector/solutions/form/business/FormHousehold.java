package dss.vector.solutions.form.business;

import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

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
    if (this.getOid() != null && this.getOid().length() > 0)
    {
      return this.getOid();
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
}
