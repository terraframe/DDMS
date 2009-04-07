package dss.vector.solutions.surveillance;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;


public class AggregatedCase extends AggregatedCaseBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
  {
    super();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    //    Lock the grid relationship also, this must be in the same transaction
    for(CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.lock();
    }

    for(CaseReferral referral : this.getAllReferralRel())
    {
      referral.lock();
    }

    for(CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.lock();
    }

    for(CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.lock();
    }

    for(CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    //    Unlock the grid relationship also, this must be in the same transaction
    for(CaseDiagnostic diagnostic : this.getAllDiagnosticMethodRel())
    {
      diagnostic.unlock();
    }

    for(CaseReferral referral : this.getAllReferralRel())
    {
      referral.unlock();
    }

    for(CaseTreatment treatment : this.getAllTreatmentRel())
    {
      treatment.unlock();
    }

    for(CaseTreatmentMethod method : this.getAllTreatmentMethodRel())
    {
      method.unlock();
    }

    for(CaseTreatmentStock stock : this.getAllTreatmentStockRel())
    {
      stock.unlock();
    }

    super.unlock();
  }

  @Override
  @Transaction
  public void applyAll(CaseTreatment[] treatments, CaseTreatmentMethod[] treatmentMethods, CaseTreatmentStock[] stock, CaseDiagnostic[] diagnosticMethods, CaseReferral[] referrals)
  {
    boolean newCase = this.isNew();

    this.apply();

    if(newCase)
    {
      for(int i = 0; i < diagnosticMethods.length; i++)
      {
        diagnosticMethods[i] = diagnosticMethods[i].clone(this);
      }

      for(int i = 0; i < referrals.length; i++)
      {
        referrals[i] = referrals[i].clone(this);
      }

      for(int i = 0; i < treatments.length; i++)
      {
        treatments[i] = treatments[i].clone(this);
      }

      for(int i = 0; i < treatmentMethods.length; i++)
      {
        treatmentMethods[i] = treatmentMethods[i].clone(this);
      }

      for(int i = 0; i < stock.length; i++)
      {
        stock[i] = stock[i].clone(this);
      }
    }

    for(CaseDiagnostic method : diagnosticMethods)
    {
      method.apply();
    }

    for(CaseReferral referral : referrals)
    {
      referral.apply();
    }

    for(CaseTreatment treatment : treatments)
    {
      treatment.apply();
    }

    for(CaseTreatmentMethod method : treatmentMethods)
    {
      method.apply();
    }

    for(CaseTreatmentStock s : stock)
    {
      s.apply();
    }

  }

  public static AggregatedCase searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCaseQuery query = new AggregatedCaseQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));
    query.AND(query.getStartAge().EQ(ageGroup.getStartAge()));
    query.AND(query.getEndAge().EQ(ageGroup.getEndAge()));

    OIterator<? extends AggregatedCase> it = query.getIterator();

    try
    {
      if(it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }


}
