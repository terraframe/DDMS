package dss.vector.solutions.surveillance;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class EpiDateDTO implements Reloadable
{
  private PeriodTypeDTO periodType;

  private Integer period;

  private Integer year;

  public EpiDateDTO(PeriodTypeDTO periodType, Integer period, Integer year)
  {
    this.periodType = periodType;
    this.period = period;
    this.year = year;
  }

  public PeriodTypeDTO getPeriodType()
  {
    return periodType;
  }

  public Integer getPeriod()
  {
    return period;
  }

  public Integer getYear()
  {
    return year;
  }

  public String getDisplayLabel(ClientRequestIF clientRequest)
  {
    PeriodTypeMasterDTO item = periodType.item(clientRequest);

    return item.getDisplayLabel() + " " + period + " - " + year;
  }
}
