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

import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.query.QueryBuilder;

public class Physician extends PhysicianBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -800749037;
  
  public Physician()
  {
    super();
  }
  
  @Override
  public PersonView getView()
  {
    return this.getPerson().getView();
  }
  
  public static ValueQuery searchForPhysician(String search)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    PhysicianQuery leaderQuery = new PhysicianQuery(valueQuery);

    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(PersonView.ID), personQuery.getFirstName(PersonView.FIRSTNAME), personQuery.getLastName(PersonView.LASTNAME) };

    Condition[] conditions = new Condition[] {personQuery.getPhysicianDelegate().EQ(leaderQuery) };

    if (search != null && !search.equals(""))
    {
      String[] array = search.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, personQuery.getFirstName(PersonView.FIRSTNAME), selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }
  
}
