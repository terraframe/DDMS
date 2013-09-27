package dss.vector.solutions.surveillance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.AggregatedCaseQB;

public class AggregatedCase extends AggregatedCaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693161773L;

  public AggregatedCase()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return "(" + this.buildKey() + ")";
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null && this.getAgeGroup() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      String period = format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());
      String ageRange = this.getAgeGroup().getKey();

      return this.getGeoEntity().getGeoId() + ", " + period + ", " + ageRange;
    }
    return this.getId();
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    validateStartDate();
    validateEndDate();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public void delete()
  {
    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();

    for (CaseTreatmentMethod method : methods)
    {
      method.delete();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.delete();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.delete();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.delete();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.delete();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.delete();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.delete();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.delete();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.delete();
    }

    super.delete();
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();

    for (CaseTreatmentMethod method : methods)
    {
      method.lock();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.lock();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.lock();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.lock();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.lock();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.lock();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.lock();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.lock();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    List<CaseTreatmentMethod> methods = this.getTreatmentMethods();

    for (CaseTreatmentMethod method : methods)
    {
      method.unlock();
    }

    List<CaseTreatment> treatments = this.getTreatments();

    for (CaseTreatment treatment : treatments)
    {
      treatment.unlock();
    }

    List<CaseTreatmentStock> stocks = this.getTreatmentStocks();

    for (CaseTreatmentStock stock : stocks)
    {
      stock.unlock();
    }

    List<CaseDiagnostic> diagnostics = this.getDiagnosticMethods();

    for (CaseDiagnostic method : diagnostics)
    {
      method.unlock();
    }

    List<CaseReferral> referrals = this.getReferrals();

    for (CaseReferral referral : referrals)
    {
      referral.unlock();
    }

    List<CaseStockReferral> stockReferrals = this.getStockReferrals();

    for (CaseStockReferral referral : stockReferrals)
    {
      referral.unlock();
    }

    List<CaseDiagnosisType> types = this.getDiagnosisTypes();

    for (CaseDiagnosisType type : types)
    {
      type.unlock();
    }

    List<CaseDiseaseManifestation> manifestations = this.getDiseaseManifestations();

    for (CaseDiseaseManifestation manifestation : manifestations)
    {
      manifestation.unlock();
    }

    List<CasePatientType> patientTypes = this.getPatientTypes();

    for (CasePatientType patientType : patientTypes)
    {
      patientType.unlock();
    }
  }

  public List<CaseTreatmentMethod> getTreatmentMethods()
  {
    List<CaseTreatmentMethod> list = new LinkedList<CaseTreatmentMethod>();

    CaseTreatmentMethodQuery query = new CaseTreatmentMethodQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseTreatmentMethod> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseTreatment> getTreatments()
  {
    List<CaseTreatment> list = new LinkedList<CaseTreatment>();

    CaseTreatmentQuery query = new CaseTreatmentQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseTreatment> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseStockReferral> getStockReferrals()
  {
    List<CaseStockReferral> list = new LinkedList<CaseStockReferral>();

    CaseStockReferralQuery query = new CaseStockReferralQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseStockReferral> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseDiagnostic> getDiagnosticMethods()
  {
    List<CaseDiagnostic> list = new LinkedList<CaseDiagnostic>();

    CaseDiagnosticQuery query = new CaseDiagnosticQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseDiagnostic> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseReferral> getReferrals()
  {
    List<CaseReferral> list = new LinkedList<CaseReferral>();

    CaseReferralQuery query = new CaseReferralQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseReferral> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseTreatmentStock> getTreatmentStocks()
  {
    List<CaseTreatmentStock> list = new LinkedList<CaseTreatmentStock>();

    CaseTreatmentStockQuery query = new CaseTreatmentStockQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseTreatmentStock> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseDiagnosisType> getDiagnosisTypes()
  {
    List<CaseDiagnosisType> list = new LinkedList<CaseDiagnosisType>();

    CaseDiagnosisTypeQuery query = new CaseDiagnosisTypeQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseDiagnosisType> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CaseDiseaseManifestation> getDiseaseManifestations()
  {
    List<CaseDiseaseManifestation> list = new LinkedList<CaseDiseaseManifestation>();

    CaseDiseaseManifestationQuery query = new CaseDiseaseManifestationQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CaseDiseaseManifestation> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public List<CasePatientType> getPatientTypes()
  {
    List<CasePatientType> list = new LinkedList<CasePatientType>();

    CasePatientTypeQuery query = new CasePatientTypeQuery(new QueryFactory());
    query.WHERE(query.getAggregatedCase().EQ(this));

    OIterator<? extends CasePatientType> it = query.getIterator();

    try
    {
      list.addAll(it.getAll());
    }
    finally
    {
      it.close();
    }

    return list;
  }

  public AggregatedCaseView getView()
  {
    AggregatedCaseView view = new AggregatedCaseView();

    view.populateView(this);

    return view;
  }

  @Override
  public AggregatedCaseView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public AggregatedCaseView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    AggregatedCaseQB query = new AggregatedCaseQB(xml, config, layer, pageSize, pageSize);
    return query.construct();
  }

}
