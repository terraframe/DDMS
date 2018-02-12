/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
