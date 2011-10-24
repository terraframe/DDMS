package dss.vector.solutions.form.business;

import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class FormHousehold extends FormHouseholdBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 786880441;

  public FormHousehold()
  {
    super();
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

}
