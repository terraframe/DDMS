package dss.vector.solutions.entomology;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.ResistanceBioassayQB;

public class TimeResponseAssay extends TimeResponseAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = -1580911879;

  public TimeResponseAssay()
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

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new ResistanceBioassayQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }
}
