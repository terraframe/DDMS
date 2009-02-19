package org.obofoundry;

public class Component extends ComponentBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public Component()
  {
    super();
  }
  
  public String toString()
  {
    return this.getClassDisplayLabel()+": "+this.getComponentName();
  }
  
}