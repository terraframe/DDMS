package dss.vector.solutions.util;

import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.generation.loader.Reloadable;

/**
 * Container class holding a Dimension-Locale pair that represents a column in the Localization spreadsheet.
 *
 * @author Eric
 */
public class LocaleDimension implements Reloadable
{
  private String locale;
  private MdDimensionDAOIF dimension;
  
  public LocaleDimension(String locale)
  {
    this(locale, null);
  }
  
  public LocaleDimension(String locale, MdDimensionDAOIF dimension)
  {
    this.locale = locale;
    this.dimension = dimension;
  }
  
  public static LocaleDimension parseColumnHeader(String column)
  {
    String[] split = column.split(" ");
    if (split.length==1)
    {
      return new LocaleDimension(column);
    }
    else
    {
      MdDimensionDAOIF dim = MdDimensionDAO.getByName(split[0]);
      return new LocaleDimension(split[1], dim);
    }
  }
  
  public String getAttributeName()
  {
    if (dimension!=null)
    {
      return dimension.getLocaleAttributeName(locale);
    }
    return locale;
  }
  
  public String getColumnName()
  {
    if (dimension!=null)
    {
      return dimension.getName() + " " + locale;
    }
    return locale;
  }
  
  public String getPropertyFileName(String bundle)
  {
    String filename = bundle;
    if (dimension!=null)
    {
      filename += "-" + dimension.getName();
    }
    if (!locale.equals(MdAttributeLocalInfo.DEFAULT_LOCALE))
    {
      filename += "_" + locale.toString();
    }
    filename += ".properties";
    return filename;
  }
  
  public String getLocaleString()
  {
    return locale;
  }
  
  public boolean hasDimension()
  {
    return dimension!=null;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (!(obj instanceof LocaleDimension))
    {
      return false;
    }
    
    LocaleDimension other = (LocaleDimension) obj;
    if (this.hasDimension() && !other.hasDimension() ||
        !this.hasDimension() && other.hasDimension())
    {
      return false;
    }
    
    if (this.hasDimension())
    {
      if (!this.dimension.getName().equals(other.dimension.getName()))
      {
        return false;
      }
    }
    
    if (!this.locale.equals(other.locale))
    {
      return false;
    }
    
    return true;
  }
  
  /**
   * True IFF this object has a dimension, the parent has no dimension, and the locales are equal.
   * 
   * @param parent
   * @return
   */
  public boolean isDimensionChildOf(LocaleDimension parent)
  {
    if (parent.hasDimension() || !this.hasDimension())
    {
      return false;
    }
    
    return this.locale.equals(parent.locale);
  }
}
