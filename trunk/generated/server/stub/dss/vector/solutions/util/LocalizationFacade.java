package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import com.terraframe.mojo.generation.loader.Reloadable;

public abstract class LocalizationFacade extends LocalizationFacadeBase implements Reloadable
{
  private static final long serialVersionUID = 1002885644;
  
  public LocalizationFacade()
  {
    super();
  }
  
  public static Locale getLocaleFromString(String localeString)
  {
    String[] split = localeString.split("_", 3);
    if (split.length==1)
    {
      return new Locale(split[0]);
    }
    else if (split.length==2)
    {
      return new Locale(split[0], split[1]);
    }
    else
    {
      return new Locale(split[0], split[1], split[2]);
    }
  }
  
  public static InputStream exportFile(String[] locales)
  {
    MdssLocalizationExporter mdssLocalizer = new MdssLocalizationExporter();
    
    for (String s : locales)
    {
      mdssLocalizer.addLocale(getLocaleFromString(s));
    }
    
    mdssLocalizer.export();
    return new ByteArrayInputStream(mdssLocalizer.write());
  }
  
  public static void importFile(InputStream file)
  {
    MdssLocalizationImporter mli = new MdssLocalizationImporter();
    mli.read(file);
  }
}
