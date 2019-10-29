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
package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class CasePatientTypeView extends CasePatientTypeViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 600170140;
  
  public CasePatientTypeView()
  {
    super();
  }
  
  
  public void populateView(CasePatientType concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
  }

  private void populateConcrete(CasePatientType concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
  }

  private void buildAttributeMap(CasePatientType concrete)
  {
    new AttributeNotificationMap(concrete, CasePatientType.ID, this, CasePatientTypeView.CONCRETEID);
    new AttributeNotificationMap(concrete, CasePatientType.AGGREGATEDCASE, this, CasePatientTypeView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CasePatientType.TERM, this, CasePatientTypeView.TERM);
  }

  @Override
  @Transaction
  public void apply()
  {
    CasePatientType concrete = new CasePatientType();

    if (this.hasConcrete())
    {
      concrete = CasePatientType.get(this.getConcreteId());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }
  
  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public OptionIF getChild()
  {
    return this.getTerm();
  }

  public static CasePatientTypeView[] getPatientTypes(AggregatedCaseView _case)
  {
    Set<CasePatientTypeView> set = new TreeSet<CasePatientTypeView>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedCaseView.getCasePatientTypeMd()))
    {
      CasePatientTypeView view = new CasePatientTypeView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CasePatientTypeViewQuery query = new CasePatientTypeViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CasePatientTypeView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CasePatientTypeView view = it.next();

          // We will only want grid options methods which are active
          // All active methods are already in the set. Thus, if
          // the set already contains an entry for the Grid Option
          // replace the default relationship with the actaul
          // relationship
          if (set.contains(view))
          {
            set.remove(view);
            set.add(view);
          }
        }
      }
      finally
      {
        it.close();
      }
    }

    return set.toArray(new CasePatientTypeView[set.size()]);
  }

  public static CasePatientTypeAmountView[][] getAmountsForViews(String[] ids)
  {
    Term[] terms = Term.getSortedRootChildren(CasePatientTypeView.getPatientCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    CasePatientTypeAmountView[][] data = new CasePatientTypeAmountView[ids.length][];
    
    for(int i = 0; i < ids.length; i++)
    {
      String id = ids[i];
      
      CasePatientTypeView view = new CasePatientTypeView();
      
      if(id != null && id.length() > 0)
      {
        view = CasePatientType.getView(id);
      }

      data[i] = view.getAmounts(terms, comparator);
    }
    
    return data;
  }
  
  @Override
  public CasePatientTypeAmountView[] getAmounts()
  {
    Term[] terms = Term.getSortedRootChildren(CasePatientTypeView.getPatientCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);
    
    return this.getAmounts(terms, comparator);    
  }
  
  public CasePatientTypeAmountView[] getAmounts(Term[] terms, SortedGridComparator comparator)
  {
    List<CasePatientTypeAmountView> list = new LinkedList<CasePatientTypeAmountView>();
    Set<CasePatientTypeAmount> set = new TreeSet<CasePatientTypeAmount>(comparator);
    
    for (Term d : terms)
    {
      set.add(new CasePatientTypeAmount(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      CasePatientType concrete = CasePatientType.get(this.getConcreteId());

      OIterator<? extends CasePatientTypeAmount> patients = concrete.getAllPatientsRel();
      
      for (CasePatientTypeAmount d : patients)
      {
        // We will only want grid options methods which are active
        // All active methods are already in the set. Thus, if
        // the set already contains an entry for the Grid Option
        // replace the default relationship with the actaul
        // relationship
        if (set.contains(d))
        {
          set.remove(d);
          set.add(d);
        }
      }
    }

    for (CasePatientTypeAmount container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new CasePatientTypeAmountView[set.size()]);
  }

  @Transaction
  public void applyAll(CasePatientTypeAmountView[] views)
  {
    this.apply();

    CasePatientType concrete = CasePatientType.get(this.getConcreteId());

    for (CasePatientTypeAmountView view : views)
    {
      view.setPatient(concrete);
      view.apply();
    }
  }

}
