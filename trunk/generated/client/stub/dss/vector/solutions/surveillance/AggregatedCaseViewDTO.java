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
}
