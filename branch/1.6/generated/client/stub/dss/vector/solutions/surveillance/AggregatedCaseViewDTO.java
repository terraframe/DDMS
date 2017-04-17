package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

public class AggregatedCaseViewDTO extends AggregatedCaseViewDTOBase implements Reloadable
{
  private static final long serialVersionUID = 1239135495819L;

  public AggregatedCaseViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  public boolean hasConcreteId()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public AggregatedCaseSearchViewDTO getSearchDTO()
  {
    AggregatedCaseSearchViewDTO search = new AggregatedCaseSearchViewDTO(this.getRequest());

    search.setStartDate(this.getStartDate());
    search.setEndDate(this.getEndDate());
    search.setGeoEntity(this.getGeoEntity());
    search.setAgeGroup(this.getAgeGroup());

    return search;
  }

  public boolean getIsCaseDiagnosticReadable()
  {
    return this.isCaseDiagnosticReadable();
  }

  public boolean getIsCaseTreatmentsReadable()
  {
    return this.isCaseTreatmentsReadable();
  }

  public boolean getIsCaseDiagnosisTypeReadable()
  {
    return this.isCaseDiagnosisTypeReadable();
  }

  public boolean getIsCaseDiseaseManifestationReadable()
  {
    return this.isCaseDiseaseManifestationReadable();
  }

  public boolean getIsCasePatientTypeReadable()
  {
    return this.isCasePatientTypeReadable();
  }

  public boolean getIsCaseReferralsReadable()
  {
    return this.isCaseReferralsReadable();
  }

  public boolean getIsCaseStockReferralReadable()
  {
    return this.isCaseStockReferralReadable();
  }

  public boolean getIsCaseStocksReadable()
  {
    return this.isCaseStocksReadable();
  }

  public boolean getIsCaseTreatmentMethodReadable()
  {
    return this.isCaseTreatmentMethodReadable();
  }
}
