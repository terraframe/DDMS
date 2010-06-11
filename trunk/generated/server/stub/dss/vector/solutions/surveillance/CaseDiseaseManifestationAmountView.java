package dss.vector.solutions.surveillance;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;

public class CaseDiseaseManifestationAmountView extends CaseDiseaseManifestationAmountViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -807688551;
  
  public CaseDiseaseManifestationAmountView()
  {
    super();
  }
  
  
  public void populateView(CaseDiseaseManifestationAmount concrete)
  {
    if(!concrete.isNew())
    {
      this.setManifestation(concrete.getParent());
    }
    
    this.setTerm(concrete.getChild());
    this.setAmount(concrete.getAmount());
  }

  private void populateConcrete(CaseDiseaseManifestationAmount concrete)
  {
    concrete.setAmount(this.getAmount());
  }

  private void buildAttributeMap(CaseDiseaseManifestationAmount concrete)
  {
    new AttributeNotificationMap(concrete, CaseDiseaseManifestationAmount.AMOUNT, this, CaseDiseaseManifestationAmountView.AMOUNT);
  }

  @Override
  @Transaction
  public void apply()
  {
    CaseDiseaseManifestationAmount concrete = null;

    CaseDiseaseManifestation _visit = this.getManifestation();
    
    concrete = _visit.getDiseasesRel(this.getTerm());
    
    if (concrete != null)
    {
      concrete.lock();
    }
    else
    {
      concrete = new CaseDiseaseManifestationAmount(_visit, this.getTerm());
    }

    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }


}
