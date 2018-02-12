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
package dss.vector.solutions.general;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.ontology.Term;

public class MenuItem extends MenuItemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1188314482;

  public MenuItem()
  {
    super();

  }

  @Override
  public void lock()
  {
    InstallProperties.validateMasterOperation();

    super.lock();
  }

  @Override
  public void unlock()
  {
    InstallProperties.validateMasterOperation();

    super.unlock();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && !this.isAppliedToDB() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    InstallProperties.validateMasterOperation();

    validateTermAsLeaf();

    super.apply();
  }

  /**
   * Checks that a MenuItem Term can only point to a leaf node.
   */
  @AbortIfProblem
  private void validateTermAsLeaf()
  {
    Term term = this.getTerm();
    OIterator<? extends Term> iter = term.getAllChildTerm();

    try
    {
      if (iter.hasNext())
      {
        throw new MenuItemLeafException();
      }
    }
    finally
    {
      iter.close();
    }
  }

  @Override
  public void delete()
  {
    InstallProperties.validateMasterOperation();

    super.delete();
  }

  @Override
  public String toString()
  {
    Term term = this.getTerm();
    if (term != null)
    {
      return term.getTermDisplayLabel().getValue();
    }
    else
    {
      return super.toString();
    }
  }

  public static List<? extends MenuItem> findMenuItems(SystemURL url)
  {
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getUrl().EQ(url));

    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }

  public static MenuItem findMenuItem(SystemURL url, Disease disease)
  {
    MenuItemQuery query = new MenuItemQuery(new QueryFactory());
    query.WHERE(query.getUrl().EQ(url));
    query.WHERE(query.getDisease().EQ(disease));

    OIterator<? extends MenuItem> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
