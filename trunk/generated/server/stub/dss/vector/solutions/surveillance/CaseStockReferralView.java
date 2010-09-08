package dss.vector.solutions.surveillance;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;

public class CaseStockReferralView extends CaseStockReferralViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -2103009432;
  
  public CaseStockReferralView()
  {
    super();
  }
  
  public void populateView(CaseStockReferral concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseStockReferral concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseStockReferral concrete)
  {
    new AttributeNotificationMap(concrete, CaseStockReferral.ID, this, CaseStockReferralView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseStockReferral.AGGREGATEDCASE, this, CaseStockReferralView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseStockReferral.TERM, this, CaseStockReferralView.TERM);
    new AttributeNotificationMap(concrete, CaseStockReferral.AMOUNT, this, CaseStockReferralView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseStockReferral concrete = new CaseStockReferral();

    if (this.hasConcrete())
    {
      concrete = CaseStockReferral.get(this.getConcreteId());
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

  public static CaseStockReferralView[] getReferrals(AggregatedCaseView _case)
  {
    Set<CaseStockReferralView> set = new TreeSet<CaseStockReferralView>(new GridComparator());

    for (Term d : Term.getRootChildren(AggregatedCaseView.getCaseStockReferralMd()))
    {
      CaseStockReferralView view = new CaseStockReferralView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseStockReferralViewQuery query = new CaseStockReferralViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseStockReferralView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseStockReferralView view = it.next();

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

    return set.toArray(new CaseStockReferralView[set.size()]);
  }

}
