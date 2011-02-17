package dss.vector.solutions.admin.shapefile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Bean which holds all data required for rebuilding the located in table;
 * 
 * @author Justin Smethie
 */
public class LocatedInBean
{
  enum Option {
    DELETE_EXISTING, PRESERVE_EXISTING, BUILD_ORPHANED
  }

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  private Option                option;

  public LocatedInBean()
  {
    this.option = Option.DELETE_EXISTING;
    this.propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public Option getOption()
  {
    return option;
  }

  public void setOption(Option option)
  {
    propertyChangeSupport.firePropertyChange("option", this.option, this.option = option);
  }

}
