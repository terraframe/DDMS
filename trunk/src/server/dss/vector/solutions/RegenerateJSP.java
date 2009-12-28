package dss.vector.solutions;

import java.util.Collection;
import java.util.LinkedList;

import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.business.generation.ProviderFactory;
import com.terraframe.mojo.constants.MdEntityInfo;
import com.terraframe.mojo.dataaccess.BusinessDAOIF;
import com.terraframe.mojo.dataaccess.MdEntityDAOIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.BusinessDAOQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;

public class RegenerateJSP implements Reloadable
{
  @StartSession
  public static void main(String[] args)
  {
    Collection<MdEntityDAOIF> mdEntities = new LinkedList<MdEntityDAOIF>();

    QueryFactory qFactory = new QueryFactory();
    BusinessDAOQuery mdTypeQ = qFactory.businessDAOQuery(MdEntityInfo.CLASS);
    OIterator<BusinessDAOIF> it = mdTypeQ.getIterator();
    
    while (it.hasNext())
    { 
      MdEntityDAOIF mdTypeIF = (MdEntityDAOIF)it.next();
      mdEntities.add(mdTypeIF);
    }
    
    for (MdEntityDAOIF mdEntity : mdEntities)
    {
      if (GenerationUtil.isReservedType(mdEntity))
      {
        continue;
      }
      
      if (mdEntity.hasMdController())
      {
         new ProviderFactory().getProvider(mdEntity).generateContent();
      }
    }    
  }
}
