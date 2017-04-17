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

public class CaseTreatmentMethodView extends CaseTreatmentMethodViewBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -2125372367;
  
  public CaseTreatmentMethodView()
  {
    super();
  }
  
  public void populateView(CaseTreatmentMethod concrete)
  {
    if (!this.getConcreteId().equals(concrete.getId()))
    {
      this.setConcreteId(concrete.getId());
    }
    this.setAggregatedCase(concrete.getAggregatedCase());
    this.setTerm(concrete.getTerm());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseTreatmentMethod concrete)
  {
    concrete.setAggregatedCase(this.getAggregatedCase());
    concrete.setTerm(this.getTerm());
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseTreatmentMethod concrete)
  {
    new AttributeNotificationMap(concrete, CaseTreatmentMethod.ID, this, CaseTreatmentMethodView.CONCRETEID);
    new AttributeNotificationMap(concrete, CaseTreatmentMethod.AGGREGATEDCASE, this, CaseTreatmentMethodView.AGGREGATEDCASE);
    new AttributeNotificationMap(concrete, CaseTreatmentMethod.TERM, this, CaseTreatmentMethodView.TERM);
    new AttributeNotificationMap(concrete, CaseTreatmentMethod.AMOUNT, this, CaseTreatmentMethodView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseTreatmentMethod concrete = new CaseTreatmentMethod();

    if (this.hasConcrete())
    {
      concrete = CaseTreatmentMethod.get(this.getConcreteId());
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

  public static CaseTreatmentMethodView[] getTreatmentMethods(AggregatedCaseView _case)
  {
    Set<CaseTreatmentMethodView> set = new TreeSet<CaseTreatmentMethodView>(new GridComparator());

    for (Term d : TermRootCache.getRoots(AggregatedCaseView.getCaseTreatmentMethodMd()))
    {
      CaseTreatmentMethodView view = new CaseTreatmentMethodView();
      view.setTerm(d);

      set.add(view);
    }

    if (_case.hasConcrete())
    {
      AggregatedCase concrete = _case.getConcrete();

      CaseTreatmentMethodViewQuery query = new CaseTreatmentMethodViewQuery(new QueryFactory());
      query.WHERE(query.getAggregatedCase().EQ(concrete));

      OIterator<? extends CaseTreatmentMethodView> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          CaseTreatmentMethodView view = it.next();

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

    return set.toArray(new CaseTreatmentMethodView[set.size()]);
  }

}
