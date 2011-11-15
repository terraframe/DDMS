package dss.vector.solutions.form.business;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

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
    if (this.getOid() != null && this.getOid().length() > 0)
    {
      return this.getOid();
    }

    return super.buildKey();
  }

  public static FormBedNet getByBedNetId(String netId)
  {
    FormBedNetQuery query = new FormBedNetQuery(new QueryFactory());
    query.WHERE(query.getOid().EQ(netId));

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

}
