package dss.vector.solutions.surveillance;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;

public class CaseReferralView extends CaseReferralViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -1441626087;

  public CaseReferralView()
  {
    super();
  }

  public void populateView(CaseReferral concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseReferral concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseReferral concrete)
  {
    new AttributeNotificationMap(concrete, CaseReferral.ID, this, CaseReferralView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseReferral.AGGREGATEDCASE, this, CaseReferralView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseReferral.TERM, this, CaseReferralView.TERM);
    new AttributeNotificationMap(concrete, CaseReferral.AMOUNT, this, CaseReferralView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseReferral concrete = new CaseReferral();

    if (this.hasConcrete())
    {
      concrete = CaseReferral.get(this.getConcreteId());
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

  public static CaseReferralView[] getReferrals(AggregatedCaseView _case)
  {
    Set<CaseReferralView> set = new TreeSet<CaseReferralView>(new GridComparator());

    for (Term d : Term.getRootChildren(AggregatedCaseView.getCaseReferralsMd()))
    {
      CaseReferralView view = new CaseReferralView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseReferralViewQuery query = new CaseReferralViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseReferralView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseReferralView view = it.next();

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

    return set.toArray(new CaseReferralView[set.size()]);
  }
}
