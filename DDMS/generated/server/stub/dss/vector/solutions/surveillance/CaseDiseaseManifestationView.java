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

public class CaseDiseaseManifestationView extends CaseDiseaseManifestationViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 83506452;

  public CaseDiseaseManifestationView()
  {
    super();
  }

  public void populateView(CaseDiseaseManifestation concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
  }

  private void populateConcrete(CaseDiseaseManifestation concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
  }

  private void buildAttributeMap(CaseDiseaseManifestation concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiseaseManifestation.ID, this, CaseDiseaseManifestationView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseDiseaseManifestation.AGGREGATEDCASE, this, CaseDiseaseManifestationView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseDiseaseManifestation.TERM, this, CaseDiseaseManifestationView.TERM);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiseaseManifestation concrete = new CaseDiseaseManifestation();

    if (this.hasConcrete())
    {
      concrete = CaseDiseaseManifestation.get(this.getConcreteId());
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

  public static CaseDiseaseManifestationView[] getDiseaseManifestations(AggregatedCaseView _case)
  {
    Set<CaseDiseaseManifestationView> set = new TreeSet<CaseDiseaseManifestationView>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedCaseView.getCaseDiseaseManifestationMd()))
    {
      CaseDiseaseManifestationView view = new CaseDiseaseManifestationView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseDiseaseManifestationViewQuery query = new CaseDiseaseManifestationViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseDiseaseManifestationView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseDiseaseManifestationView view = it.next();

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

    return set.toArray(new CaseDiseaseManifestationView[set.size()]);
  }

  public static CaseDiseaseManifestationAmountView[][] getAmountsForViews(String[] ids)
  {
    Term[] terms = Term.getSortedRootChildren(CaseDiseaseManifestationView.getDiseaseCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    CaseDiseaseManifestationAmountView[][] data = new CaseDiseaseManifestationAmountView[ids.length][];

    for (int i = 0; i < ids.length; i++)
    {
      String id = ids[i];

      CaseDiseaseManifestationView view = new CaseDiseaseManifestationView();

      if (id != null && id.length() > 0)
      {
        view = CaseDiseaseManifestation.getView(id);
      }

      data[i] = view.getAmounts(terms, comparator);
    }

    return data;
  }

  @Override
  public CaseDiseaseManifestationAmountView[] getAmounts()
  {
    Term[] terms = Term.getSortedRootChildren(CaseDiseaseManifestationView.getDiseaseCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    return this.getAmounts(terms, comparator);
  }

  public CaseDiseaseManifestationAmountView[] getAmounts(Term[] terms, SortedGridComparator comparator)
  {
    List<CaseDiseaseManifestationAmountView> list = new LinkedList<CaseDiseaseManifestationAmountView>();
    Set<CaseDiseaseManifestationAmount> set = new TreeSet<CaseDiseaseManifestationAmount>(comparator);

    for (Term d : terms)
    {
      set.add(new CaseDiseaseManifestationAmount(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      CaseDiseaseManifestation concrete = CaseDiseaseManifestation.get(this.getConcreteId());

      OIterator<? extends CaseDiseaseManifestationAmount> diseases = concrete.getAllDiseasesRel();
      for (CaseDiseaseManifestationAmount d : diseases)
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

    for (CaseDiseaseManifestationAmount container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new CaseDiseaseManifestationAmountView[set.size()]);
  }

  @Transaction
  public void applyAll(CaseDiseaseManifestationAmountView[] views)
  {
    this.apply();

    CaseDiseaseManifestation concrete = CaseDiseaseManifestation.get(this.getConcreteId());

    for (CaseDiseaseManifestationAmountView view : views)
    {
      view.setManifestation(concrete);
      view.apply();
    }
  }
}
