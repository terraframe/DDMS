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
package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;

public class IndividualIPTCase extends IndividualIPTCaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254012841931L;

  public IndividualIPTCase()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getPatient() != null)
    {
      Person person = this.getPatient().getPerson();
      String label = person.getFirstName() + " " + person.getLastName();

      return this.getClassDisplayLabel() + ": (" + label + ")";
    }

    return this.buildKey();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();

    // As per ticket #890 we must update the patients residential information
    if (this.getResidentialLocation() != null)
    {
      Person person = this.getPatient().getPerson();

      person.appLock();
      person.setResidentialGeoEntity(this.getResidentialLocation());
      person.apply();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    List<IndividualIPT> instances = this.getInstances();

    for(IndividualIPT instance : instances )
    {
      instance.delete();
    }
    
    super.delete();
  }
  
  public List<IndividualIPT> getInstances()
  {
    List<IndividualIPT> list = new LinkedList<IndividualIPT>();
    
    IndividualIPTQuery query = new IndividualIPTQuery(new QueryFactory());
    query.WHERE(query.getIptCase().EQ(this));
    
    OIterator<? extends IndividualIPT> it = query.getIterator();
    
    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }
    
    return list;
  }

  public static IndividualIPTCaseView getView(String id)
  {
    return IndividualIPTCase.get(id).getView();
  }

  public IndividualIPTCaseView getView()
  {
    IndividualIPTCaseView view = new IndividualIPTCaseView();
    view.populateView(this);

    return view;
  }

  @Override
  public IndividualIPTCaseView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public IndividualIPTCaseView lockView()
  {
    this.lock();

    return this.getView();
  }
}
