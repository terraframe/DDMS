package dss.vector.solutions.surveillance;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class CasePatientTypeAmountView extends CasePatientTypeAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1710865329;
  
  public CasePatientTypeAmountView()
  {
    super();
  }
  
  public void populateView(CasePatientTypeAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setPatient(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CasePatientTypeAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CasePatientTypeAmount concrete)
  {
    new AttributeNotificationMap(concrete, CasePatientTypeAmount.AMOUNT, this, CasePatientTypeAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CasePatientTypeAmount concrete = null;

    CasePatientType _visit = this.getPatient();
    
    concrete = _visit.getPatientsRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new CasePatientTypeAmount(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
