package dss.vector.solutions.surveillance;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;

public class CaseDiagnosticView extends CaseDiagnosticViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -1999778020;

  public CaseDiagnosticView()
  {
    super();
  }

  public void populateView(CaseDiagnostic concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setAmount(concrete.getAmount());
    this.setAmountPositive(concrete.getAmountPositive());
  }

  private void populateConcrete(CaseDiagnostic concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setAmount(this.getAmount());
    concrete.setAmountPositive(this.getAmountPositive());
  }

  private void buildAttributeMap(CaseDiagnostic concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiagnostic.ID, this, CaseDiagnosticView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseDiagnostic.AGGREGATEDCASE, this, CaseDiagnosticView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseDiagnostic.TERM, this, CaseDiagnosticView.TERM);
    new AttributeNotificationMap(concrete, CaseDiagnostic.AMOUNT, this, CaseDiagnosticView.AMOUNT);
    new AttributeNotificationMap(concrete, CaseDiagnostic.AMOUNTPOSITIVE, this, CaseDiagnosticView.AMOUNTPOSITIVE);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiagnostic concrete = new CaseDiagnostic();

    if (this.hasConcrete())
    {
      concrete = CaseDiagnostic.get(this.getConcreteId());
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

  public static CaseDiagnosticView[] getDiagnosticMethods(AggregatedCaseView _case)
  {
    Set<CaseDiagnosticView> set = new TreeSet<CaseDiagnosticView>(new GridComparator());

    for (Term d : Term.getRootChildren(AggregatedCaseView.getCaseDiagnosticMd()))
    {
      CaseDiagnosticView view = new CaseDiagnosticView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseDiagnosticViewQuery query = new CaseDiagnosticViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseDiagnosticView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseDiagnosticView view = it.next();

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

    return set.toArray(new CaseDiagnosticView[set.size()]);
  }

}
