package dss.vector.solutions.surveillance;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class CaseTreatmentStockView extends CaseTreatmentStockViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -1792406419;
  
  public CaseTreatmentStockView()
  {
    super();
  }
  
  public void populateView(CaseTreatmentStock concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setOutOfStock(concrete.getOutOfStock());
  }

  private void populateConcrete(CaseTreatmentStock concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setOutOfStock(this.getOutOfStock());
  }

  private void buildAttributeMap(CaseTreatmentStock concrete)
  {
    new AttributeNotificationMap(concrete, CaseTreatmentStock.ID, this, CaseTreatmentStockView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseTreatmentStock.AGGREGATEDCASE, this, CaseTreatmentStockView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseTreatmentStock.TERM, this, CaseTreatmentStockView.TERM);
    new AttributeNotificationMap(concrete, CaseTreatmentStock.OUTOFSTOCK, this, CaseTreatmentStockView.OUTOFSTOCK);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseTreatmentStock concrete = new CaseTreatmentStock();

    if (this.hasConcrete())
    {
      concrete = CaseTreatmentStock.get(this.getConcreteId());
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

  public static CaseTreatmentStockView[] getTreatmentStocks(AggregatedCaseView _case)
  {
    Set<CaseTreatmentStockView> set = new TreeSet<CaseTreatmentStockView>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedCaseView.getCaseStocksMd()))
    {
      CaseTreatmentStockView view = new CaseTreatmentStockView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseTreatmentStockViewQuery query = new CaseTreatmentStockViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseTreatmentStockView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseTreatmentStockView view = it.next();

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

    return set.toArray(new CaseTreatmentStockView[set.size()]);
  }

}
