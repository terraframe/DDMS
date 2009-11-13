package dss.vector.solutions.intervention.monitor;

import java.util.List;

public class Larvacide extends LarvacideBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372022458L;
  
  public Larvacide()
  {
    super();
  }
  
  @Override
  public LarvacideInstanceView[] getInstanceViews()
  {
    List<? extends LarvacideInstance> instances = this.getAllInstances().getAll();
    LarvacideInstanceView[] views = new LarvacideInstanceView[instances.size()];
    int i=0;
    
    for (LarvacideInstance instance : instances)
    {
      views[i++] = instance.getView();
    }
    
    return views;
  }
}
