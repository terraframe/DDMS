package dss.vector.solutions.entomology;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.ResistanceBioassayQB;

public class TimeResponseAssay extends TimeResponseAssayBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1580911879;

  public TimeResponseAssay()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    return new ResistanceBioassayQB(xml, config, layer).construct();
  }
}
