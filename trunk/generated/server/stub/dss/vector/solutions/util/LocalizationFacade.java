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
  
  public static InputStream exportFile(String[] locales)
  {
    MdssLocalizationExporter mdssLocalizer = new MdssLocalizationExporter();
    
    for (String s : locales)
    {
      String[] split = s.split("_", 3);
      if (split.length==1)
      {
        mdssLocalizer.addLocale(new Locale(split[0]));
      }
      else if (split.length==2)
      {
        mdssLocalizer.addLocale(new Locale(split[0], split[1]));
      }
      else if (split.length==3)
      {
        mdssLocalizer.addLocale(new Locale(split[0], split[1], split[2]));
      }
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
