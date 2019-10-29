/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
