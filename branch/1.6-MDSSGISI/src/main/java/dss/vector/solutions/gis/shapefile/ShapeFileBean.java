package dss.vector.solutions.gis.shapefile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

public class ShapeFileBean implements Reloadable
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
   * Optional attribute with the column name which specifies the type attribute
   */
  private String                type;

  /**
   * Optional attribute with the column name which specifies the sub-type
   * attribute
   */
  private String                subtype;

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
  private String                parent;

  /**
   * Optional column for the located in sub-type.
   */
  private String                parentType;

  /**
   * List of attributes
   */
  private List<String>          attributes;

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
    propertyChangeSupport.firePropertyChange("universal", this.universal, this.universal = universal);
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    propertyChangeSupport.firePropertyChange("type", this.type, this.type = type);
  }

  public String getSubtype()
  {
    return subtype;
  }

  public void setSubtype(String subtype)
  {
    propertyChangeSupport.firePropertyChange("subtype", this.subtype, this.subtype = subtype);
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

  public String getParent()
  {
    return parent;
  }

  public void setParent(String parentIn)
  {
    propertyChangeSupport.firePropertyChange("parent", this.parent, this.parent = parentIn);
  }

  public String getParentType()
  {
    return parentType;
  }

  public void setParentType(String parentType)
  {
    propertyChangeSupport.firePropertyChange("parentType", this.parentType, this.parentType = parentType);
  }

  public List<String> getAttributes()
  {
    return attributes;
  }

  public void setAttributes(List<String> attributes)
  {
    this.attributes = attributes;
  }

  public boolean hasRequiredAttributes()
  {
    return ( ( this.getUniversal() != null || this.getType() != null ) && this.getName() != null );
  }

  @Override
  public String toString()
  {
    return "Universal: " + universal + ", Type: " + type + " Name: " + name + ", Id: " + id + ", Located In: " + parent + ", Located In Type:" + parentType;
  }
}
