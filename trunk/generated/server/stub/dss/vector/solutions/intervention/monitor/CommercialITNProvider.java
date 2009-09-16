package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class CommercialITNProvider extends CommercialITNProviderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970180182L;
  
  public CommercialITNProvider()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getProviderName();
  }
  
  public static CommercialITNProvider[] getAll()
  {
    CommercialITNProviderQuery query = new CommercialITNProviderQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getProviderName());

    return CommercialITNProvider.convertQueryToArray(query);
  }
  
  public static CommercialITNProvider[] getAllActive()
  {
    CommercialITNProviderQuery query = new CommercialITNProviderQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getProviderName());

    return CommercialITNProvider.convertQueryToArray(query);
  }

  private static CommercialITNProvider[] convertQueryToArray(CommercialITNProviderQuery query)
  {
    List<CommercialITNProvider> CommericalITNProviders = new LinkedList<CommercialITNProvider>();
    OIterator<? extends CommercialITNProvider> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        CommericalITNProviders.add(it.next());
      }

      return CommericalITNProviders.toArray(new CommercialITNProvider[CommericalITNProviders.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
