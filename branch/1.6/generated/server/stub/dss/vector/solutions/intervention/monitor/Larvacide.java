package dss.vector.solutions.intervention.monitor;

import java.util.List;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.LarvacideQB;

public class Larvacide extends LarvacideBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372022458L;

  public Larvacide()
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

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }

    return this.getClassDisplayLabel();
  }

  @Override
  public LarvacideInstanceView[] getInstanceViews()
  {
    List<? extends LarvacideInstance> instances = this.getAllInstances().getAll();
    LarvacideInstanceView[] views = new LarvacideInstanceView[instances.size()];
    int i = 0;

    for (LarvacideInstance instance : instances)
    {
      views[i++] = instance.getView();
    }

    return views;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    return new LarvacideQB(xml, config, layer, pageSize, pageSize, disease).construct();
  }
}
