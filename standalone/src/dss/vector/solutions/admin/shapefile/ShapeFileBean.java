package dss.vector.solutions.admin.shapefile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class ShapeFileBean
{
  /**
   * Shape file
   */
  private File                  shapeFile;

  /**
   * Type of universal being imported
   */
  private String                universal;

  /**
   * Column of the name attribute
   */
  private String                name;

  /**
   * Column of the id attribute. Option to auto-generate an id.
   */
  private String                id;

  /**
   * Optional column for the located in.
   */
  private String                locatedIn;

  /**
   * Optional column for the located in sub-type.
   */
  private String                locatedInType;

  /**
   * List of attributes
   */
  private String[]              attributes;

  /**
   * PropertyChangeSupport
   */
  private PropertyChangeSupport propertyChangeSupport;

  public ShapeFileBean()
  {
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
  {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  public File getShapeFile()
  {
    return shapeFile;
  }

  public void setShapeFile(File shapeFile)
  {
    this.shapeFile = shapeFile;
  }

  public String getUniversal()
  {
    return universal;
  }

  public void setUniversal(String universal)
  {
    propertyChangeSupport.firePropertyChange("un", this.universal, this.universal = universal);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    propertyChangeSupport.firePropertyChange("name", this.name, this.name = name);
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    propertyChangeSupport.firePropertyChange("id", this.id, this.id = id);
  }

  public String getLocatedIn()
  {
    return locatedIn;
  }

  public void setLocatedIn(String locatedIn)
  {
    propertyChangeSupport.firePropertyChange("locatedIn", this.locatedIn, this.locatedIn = locatedIn);
  }

  public String getLocatedInType()
  {
    return locatedInType;
  }

  public void setLocatedInType(String locatedInType)
  {
    propertyChangeSupport.firePropertyChange("locatedInType", this.locatedInType, this.locatedInType = locatedInType);
  }

  public String[] getAttributes()
  {
    return attributes;
  }

  public void setAttributes(String[] attributes)
  {
    this.attributes = attributes;
  }

  public boolean hasRequiredAttributes()
  {
    return ( this.getUniversal() != null && this.getName() != null );
  }
  
  @Override
  public String toString()
  {
    return "Universal: " + universal + ", Name: " + name + ", Id: " + id + ", Located In: " + locatedIn + ", Located In Type:" + locatedInType;
  }
}
