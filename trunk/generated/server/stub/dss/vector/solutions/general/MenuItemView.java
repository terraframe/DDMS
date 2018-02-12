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

import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.MenuItemViewQuery.MenuItemViewBuilder;

public class MenuItemView extends MenuItemViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1731174277;

  public MenuItemView()
  {
    super();
  }

  /**
   * Returns all MenuItemViews for the given disease in the current session.
   * 
   * @return
   */
  public static MenuItemViewQuery getViewsForDisease()
  {
    QueryFactory f = new QueryFactory();
    MenuItemViewQuery q = new MenuItemViewQuery(f);

    return q;
  }

  public static MenuItemViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory factory = new QueryFactory();
    MenuItemViewQuery query = new MenuItemViewQuery(factory, new MenuItemViewBuilder(factory, sortAttribute, isAscending));

    return query;
  }

}
