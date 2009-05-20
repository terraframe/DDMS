package dss.vector.solutions.intervention;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class FeverTreatment extends FeverTreatmentBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242842233525L;
  
  public FeverTreatment()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getTreatmentName();
  }
  
  public static FeverTreatment[] getAll()
  {
    FeverTreatmentQuery query = new FeverTreatmentQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getTreatmentName());

    return FeverTreatment.convertQueryToArray(query);
  }
  
  public static FeverTreatment[] getAllActive()
  {
    FeverTreatmentQuery query = new FeverTreatmentQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getTreatmentName());

    return FeverTreatment.convertQueryToArray(query);
  }

  private static FeverTreatment[] convertQueryToArray(FeverTreatmentQuery query)
  {
    List<FeverTreatment> FeverTreatments = new LinkedList<FeverTreatment>();
    OIterator<? extends FeverTreatment> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        FeverTreatments.add(it.next());
      }

      return FeverTreatments.toArray(new FeverTreatment[FeverTreatments.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
