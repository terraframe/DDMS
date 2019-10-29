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

import com.runwaysdk.business.generation.ProviderBuilder;
import com.runwaysdk.business.generation.ProviderBuilderIF;
import com.runwaysdk.business.generation.facade.ControllerStubGeneratorIF;
import com.runwaysdk.business.generation.view.ContentProvider;
import com.runwaysdk.business.generation.view.ContentProviderIF;
import com.runwaysdk.business.generation.view.NewRelationshipComponentListener;
import com.runwaysdk.dataaccess.MdControllerDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

public class MDSSProviderBuilder extends ProviderBuilder implements ProviderBuilderIF, Reloadable
{
  public MDSSProviderBuilder()
  {
    // Must implement a default constructor
  }

  @Override
  public ControllerStubGeneratorIF getControllerStubGenerator(MdControllerDAOIF mdController)
  {
    return new MDSSControllerStubGenerator(mdController);
  }
  
  @Override
  public ContentProviderIF getProvider(MdEntityDAOIF mdEntity)
  {
    ContentProviderIF provider = new ContentProvider();

    provider.registerContentListener(new MDSSViewAllComponentListener(mdEntity));
    provider.registerContentListener(new MDSSViewComponentListener(mdEntity));
    provider.registerContentListener(new MDSSFormListener(mdEntity));
    provider.registerContentListener(new MDSSCreateComponentListener(mdEntity));
    provider.registerContentListener(new MDSSUpdateComponentListener(mdEntity));

    if (mdEntity instanceof MdRelationshipDAOIF)
    {
      provider.registerContentListener(new NewRelationshipComponentListener((MdRelationshipDAOIF) mdEntity));
    }

    return provider;
  }
}
