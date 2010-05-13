package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class PersonInterventionMethodView extends PersonInterventionMethodViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 211163142;
  
  public PersonInterventionMethodView()
  {
    super();
  }
 
  public void populateView(PersonInterventionMethod concrete)
  {
    if (!concrete.isNew())
    {
      this.setPerson(concrete.getParent());
    }

    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(PersonInterventionMethod concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(PersonInterventionMethod concrete)
  {
    new AttributeNotificationMap(concrete, PersonInterventionMethod.AMOUNT, this, PersonInterventionMethodView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    PersonInterventionMethod concrete = null;

    PersonIntervention _person = this.getPerson();

    concrete = _person.getInterventionMethodsRel(this.getTerm());

    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new PersonInterventionMethod(_person, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
