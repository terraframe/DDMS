package dss.vector.solutions.entomology;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Disease;

public class DiagnosticAssay extends DiagnosticAssayBase implements
    com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -469781453;

  public DiagnosticAssay()
  {
    super();
  }

  @Override
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  @Override
  public String toString()
  {
    if (this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else
    {
      return this.getId();
    }
  }

}
