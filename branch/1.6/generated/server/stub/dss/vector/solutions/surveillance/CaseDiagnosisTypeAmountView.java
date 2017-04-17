package dss.vector.solutions.surveillance;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class CaseDiagnosisTypeAmountView extends CaseDiagnosisTypeAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1002346837;
  
  public CaseDiagnosisTypeAmountView()
  {
    super();
  }
  
  public void populateView(CaseDiagnosisTypeAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setDiagnosis(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseDiagnosisTypeAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseDiagnosisTypeAmount concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiagnosisTypeAmount.AMOUNT, this, CaseDiagnosisTypeAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiagnosisTypeAmount concrete = null;

    CaseDiagnosisType _visit = this.getDiagnosis();
    
    concrete = _visit.getCategoriesRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new CaseDiagnosisTypeAmount(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

}
