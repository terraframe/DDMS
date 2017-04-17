package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ontology.Term;

public class PupalContainerAmountView extends PupalContainerAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1412152919;
  
  public PupalContainerAmountView()
  {
    super();
  }

  public void populateView(PupalContainerAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setContainer(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(PupalContainerAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(PupalContainerAmount concrete)
  {
    new AttributeNotificationMap(concrete, PupalContainerAmount.AMOUNT, this, PupalContainerAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    PupalContainerAmount concrete = null;

    PupalContainer _container = this.getContainer();
    Term _term = this.getTerm();
    
    concrete = _container.getPupalRel(_term);
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new PupalContainerAmount(_container, _term);
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }
}
