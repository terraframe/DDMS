package dss.vector.solutions.surveillance;

import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ontology.Term;

public class CaseTreatmentView extends CaseTreatmentViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -863758480;
  
  public CaseTreatmentView()
  {
    super();
  }
  
  public void populateView(CaseTreatment concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseTreatment concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseTreatment concrete)
  {
    new AttributeNotificationMap(concrete, CaseTreatment.ID, this, CaseTreatmentView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseTreatment.AGGREGATEDCASE, this, CaseTreatmentView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseTreatment.TERM, this, CaseTreatmentView.TERM);
    new AttributeNotificationMap(concrete, CaseTreatment.AMOUNT, this, CaseTreatmentView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseTreatment concrete = new CaseTreatment();

    if (this.hasConcrete())
    {
      concrete = CaseTreatment.get(this.getConcreteId());
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

  public static CaseTreatmentView[] getTreatments(AggregatedCaseView _case)
  {
    Set<CaseTreatmentView> set = new TreeSet<CaseTreatmentView>(new GridComparator());

    for (Term d : Term.getRootChildren(AggregatedCaseView.getCaseTreatmentsMd()))
    {
      CaseTreatmentView view = new CaseTreatmentView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseTreatmentViewQuery query = new CaseTreatmentViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseTreatmentView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseTreatmentView view = it.next();

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

    return set.toArray(new CaseTreatmentView[set.size()]);
  }

}
