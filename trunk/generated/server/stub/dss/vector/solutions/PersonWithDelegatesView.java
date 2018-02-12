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

import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

public class PersonWithDelegatesView extends PersonWithDelegatesViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -2036129620;

  public PersonWithDelegatesView()
  {
    super();
  }
  
  public static PersonWithDelegatesView getView(String id)
  {
    return PersonWithDelegatesView.getView(Person.get(id));
  }
  
  public static PersonWithDelegatesView getView(Person concrete)
  {
    PersonWithDelegatesView view = new PersonWithDelegatesView();
    
    view.populateView(concrete);
    
    return view;
  }
  
  @Override
  public void populateView(Person concrete)
  {
    super.populateView(concrete);
   
    this.setPatientDelegate(concrete.getPatientDelegate());
    this.setIptRecipientDelegate(concrete.getIptRecipientDelegate());
    this.setItnRecipientDelegate(concrete.getItnRecipientDelegate());
    this.setUserDelegate(concrete.getUserDelegate());
    this.setPatientDelegate(concrete.getPatientDelegate());
    this.setPhysicianDelegate(concrete.getPhysicianDelegate());
    this.setTeamMemberDelegate(concrete.getTeamMemberDelegate());
    this.setStockStaffDelegate(concrete.getStockStaffDelegate());
    this.setSupervisorDelegate(concrete.getSupervisorDelegate());
  }
  
  /**
   * MdMethod (invoked from PersonController)
   */
  public static PersonWithDelegatesViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    PersonWithDelegatesViewQuery query = new PersonWithDelegatesViewQuery(new QueryFactory());
    
    return getPage(query, sortAttribute, isAscending, pageSize, pageNumber);
  }
  
  public static PersonWithDelegatesViewQuery getPage(PersonWithDelegatesViewQuery query, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    if (sortAttribute == null)
    {
      sortAttribute = FIRSTNAME;
    }
    
    Selectable selectable = query.getComponentQuery().getSelectableRef(sortAttribute);
    
    if(sortAttribute.equalsIgnoreCase(SEX))
    {
      selectable = query.getSex().getTermDisplayLabel().localize(Term.TERMDISPLAYLABEL);
//      selectable = ((AttributeReference) selectable.getAttribute()).get(Term.DISPLAY);
    }
    
    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) selectable, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) selectable, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }
    
    return query;
  }

}
