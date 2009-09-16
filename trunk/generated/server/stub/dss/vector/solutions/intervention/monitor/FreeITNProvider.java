package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class FreeITNProvider extends FreeITNProviderBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1252970226791L;
  
  public FreeITNProvider()
  {
    super();
  }
 
  @Override
  protected String buildKey()
  {
    return this.getProviderName();
  }
  
  public static FreeITNProvider[] getAll()
  {
    FreeITNProviderQuery query = new FreeITNProviderQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getProviderName());

    return FreeITNProvider.convertQueryToArray(query);
  }
  
  public static FreeITNProvider[] getAllActive()
  {
    FreeITNProviderQuery query = new FreeITNProviderQuery(new QueryFactory());
    query.WHERE(query.getEnabled().EQ(true));
    query.ORDER_BY_ASC(query.getProviderName());

    return FreeITNProvider.convertQueryToArray(query);
  }

  private static FreeITNProvider[] convertQueryToArray(FreeITNProviderQuery query)
  {
    List<FreeITNProvider> FreeITNProviders = new LinkedList<FreeITNProvider>();
    OIterator<? extends FreeITNProvider> it = query.getIterator();

    try
    {
      while(it.hasNext())
      {
        FreeITNProviders.add(it.next());
      }

      return FreeITNProviders.toArray(new FreeITNProvider[FreeITNProviders.size()]);
    }
    finally
    {
      it.close();
    }
  }

}
