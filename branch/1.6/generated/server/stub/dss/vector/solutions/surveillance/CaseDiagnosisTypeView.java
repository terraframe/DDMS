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

public class CaseDiagnosisTypeView extends CaseDiagnosisTypeViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1380454472;
  
  public CaseDiagnosisTypeView()
  {
    super();
  }
  
  public void populateView(CaseDiagnosisType concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
  }

  private void populateConcrete(CaseDiagnosisType concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
  }

  private void buildAttributeMap(CaseDiagnosisType concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiagnosisType.ID, this, CaseDiagnosisTypeView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseDiagnosisType.AGGREGATEDCASE, this, CaseDiagnosisTypeView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseDiagnosisType.TERM, this, CaseDiagnosisTypeView.TERM);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiagnosisType concrete = new CaseDiagnosisType();

    if (this.hasConcrete())
    {
      concrete = CaseDiagnosisType.get(this.getConcreteId());
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

  public static CaseDiagnosisTypeView[] getDiagnosisTypes(AggregatedCaseView _case)
  {
    Set<CaseDiagnosisTypeView> set = new TreeSet<CaseDiagnosisTypeView>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedCaseView.getCaseDiagnosisTypeMd()))
    {
      CaseDiagnosisTypeView view = new CaseDiagnosisTypeView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseDiagnosisTypeViewQuery query = new CaseDiagnosisTypeViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseDiagnosisTypeView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseDiagnosisTypeView view = it.next();

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

    return set.toArray(new CaseDiagnosisTypeView[set.size()]);
  }

  public static CaseDiagnosisTypeAmountView[][] getAmountsForViews(String[] ids)
  {
    Term[] terms = Term.getSortedRootChildren(CaseDiagnosisTypeView.getDiagnosisCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);

    CaseDiagnosisTypeAmountView[][] data = new CaseDiagnosisTypeAmountView[ids.length][];
    
    for(int i = 0; i < ids.length; i++)
    {
      String id = ids[i];
      
      CaseDiagnosisTypeView view = new CaseDiagnosisTypeView();
      
      if(id != null && id.length() > 0)
      {
        view = CaseDiagnosisType.getView(id);
      }

      data[i] = view.getAmounts(terms, comparator);
    }
    
    return data;
  }

  @Override
  public CaseDiagnosisTypeAmountView[] getAmounts()
  {
    Term[] terms = Term.getSortedRootChildren(CaseDiagnosisTypeView.getDiagnosisCategoryMd());
    SortedGridComparator comparator = new SortedGridComparator(terms);
    
    return this.getAmounts(terms, comparator);
  }
  
  public CaseDiagnosisTypeAmountView[] getAmounts(Term[] terms, SortedGridComparator comparator)
  {
    List<CaseDiagnosisTypeAmountView> list = new LinkedList<CaseDiagnosisTypeAmountView>();
    Set<CaseDiagnosisTypeAmount> set = new TreeSet<CaseDiagnosisTypeAmount>(comparator);

    for (Term d : terms)
    {
      set.add(new CaseDiagnosisTypeAmount(this.getId(), d.getId()));
    }

    if (this.hasConcrete())
    {
      CaseDiagnosisType c = CaseDiagnosisType.get(this.getConcreteId());

      OIterator<? extends CaseDiagnosisTypeAmount> categories = c.getAllCategoriesRel();
      
      for (CaseDiagnosisTypeAmount d : categories)
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

    for (CaseDiagnosisTypeAmount container : set)
    {
      list.add(container.getView());
    }

    return list.toArray(new CaseDiagnosisTypeAmountView[set.size()]);
  }

  @Transaction
  public void applyAll(CaseDiagnosisTypeAmountView[] views)
  {
    this.apply();

    CaseDiagnosisType concrete = CaseDiagnosisType.get(this.getConcreteId());

    for (CaseDiagnosisTypeAmountView view : views)
    {
      view.setDiagnosis(concrete);
      view.apply();
    }
  }


}
