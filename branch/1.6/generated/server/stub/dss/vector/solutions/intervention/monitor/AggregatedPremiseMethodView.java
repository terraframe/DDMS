package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class AggregatedPremiseMethodView extends AggregatedPremiseMethodViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 425423779;
  
  public AggregatedPremiseMethodView()
  {
    super();
  }
  
  public void populateView(AggregatedPremiseMethod concrete)
  {
    if (!concrete.isNew())
    {
      this.setVisit(concrete.getParent());
    }

    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(AggregatedPremiseMethod concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(AggregatedPremiseMethod concrete)
  {
    new AttributeNotificationMap(concrete, AggregatedPremiseMethod.AMOUNT, this, AggregatedPremiseMethodView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    AggregatedPremiseMethod concrete = null;

    AggregatedPremiseVisit _visit = this.getVisit();

    concrete = _visit.getInterventionMethodsRel(this.getTerm());

    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new AggregatedPremiseMethod(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
