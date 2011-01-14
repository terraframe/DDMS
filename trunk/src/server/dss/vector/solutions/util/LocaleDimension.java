package dss.vector.solutions.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.runwaysdk.constants.MdAttributeLocalInfo;
import com.runwaysdk.constants.ProfileManager;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.io.FileReadException;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.util.FileIO;

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
  
  public Map<String, String> getPropertiesFromFile(String bundle)
  {
    Map<String, String> props = new TreeMap<String, String>();
    
    File profileRootDir = ProfileManager.getProfileRootDir();
    
    File file = new File(profileRootDir, getPropertyFileName(bundle));
    
    if (!file.exists())
    {
      return props;
    }
    
    List<String> lines;
    try
    {
      lines = FileIO.readLines(file);
    }
    catch (IOException e)
    {
      throw new FileReadException(file, e);
    }
    
    for (String line : lines)
    {
      // Comments aren't properties
      if (line.startsWith("#"))
        continue;
      
      // Blank lines are also boring
      if (line.trim().length()==0)
        continue;
      
      String[] split = line.split("=", 2);
      //    if (split.length!=2)
      //      throw an error;
      
      String key = split[0].trim();
      String value = split[1].trim();
      
      props.put(key, value);
    }
    return props;
  }
  
  public String getLocaleString()
  {
    return locale;
  }
  
  public boolean hasDimension()
  {
    return dimension!=null;
  }
  
  /**
   * Returns the parent dimension of this LocaleDimension. Dimension is
   * unchanged; only the locale becomes more generic. If this instance already
   * represents the default locale, then null is returned.
   * 
   * @return The parent dimension of this LocaleDimension
   */
  public LocaleDimension getParent()
  {
    if (locale.equals(MdAttributeLocalInfo.DEFAULT_LOCALE) || locale.length()==0)
    {
      return null;
    }
    
    int index = locale.lastIndexOf("_");
    if (index==-1)
    {
      return new LocaleDimension(MdAttributeLocalInfo.DEFAULT_LOCALE, this.dimension);
    }
    else
    {
      return new LocaleDimension(locale.substring(0, index), this.dimension);
    }
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
  
  @Override
  public String toString()
  {
    String string = new String();
    if (dimension!=null)
    {
      string += dimension.getName() + " ";
    }
    string += locale;
    return string;
  }
}
