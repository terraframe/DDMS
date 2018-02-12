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
package dss.vector.solutions.kaleidoscope.dashboard.query;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeMomentDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.dashboard.MdAttributeView;
import dss.vector.solutions.util.Predicate;

public class MdAttributeViewPredicate implements Predicate<MdAttributeView>, Reloadable
{
  private MdAttributeDAOIF mdAttribute;

  public MdAttributeViewPredicate(MdAttributeDAOIF mdAttribute)
  {
    this.mdAttribute = mdAttribute;
  }

  @Override
  public boolean evaulate(MdAttributeView _t)
  {
    MdAttributeDAOIF comparisonAttribute = MdAttributeDAO.get(_t.getMdAttributeId());

    if (comparisonAttribute.getMdAttributeConcrete() instanceof MdAttributeMomentDAOIF)
    {
      return true;
    }

    return comparisonAttribute.getId().equals(mdAttribute.getId());
  }

}
