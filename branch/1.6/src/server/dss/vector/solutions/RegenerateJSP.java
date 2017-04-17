package dss.vector.solutions;

import java.util.Collection;
import java.util.LinkedList;

import com.runwaysdk.business.generation.GenerationUtil;
import com.runwaysdk.business.generation.ProviderFactory;
import com.runwaysdk.constants.MdEntityInfo;
import com.runwaysdk.dataaccess.BusinessDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.BusinessDAOQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;

public class RegenerateJSP implements Reloadable
{
  @Request
  public static void main(String[] args)
  {
    boolean onlyGenerated= false;
    
    if (args.length > 0)
    {
      if (args[0].equals("generated"))
        onlyGenerated = true;
    }
    
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
      if (GenerationUtil.isReservedAndHardcoded(mdEntity))
      {
        continue;
      }

      if (mdEntity.hasMdController())
      {
        if (!onlyGenerated || mdEntity.getPackage().contains("generated"))
          new ProviderFactory().getProvider(mdEntity).generateContent();
      }
    }
    System.exit(0);
  }
}
