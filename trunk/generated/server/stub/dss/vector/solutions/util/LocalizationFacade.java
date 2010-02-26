package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import com.terraframe.mojo.business.rbac.Authenticate;
import com.terraframe.mojo.dataaccess.cache.ObjectCache;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.generation.loader.LockHolder;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.system.metadata.SupportedLocale;
import com.terraframe.mojo.system.metadata.SupportedLocaleQuery;

public abstract class LocalizationFacade extends LocalizationFacadeBase implements Reloadable
{
  private static final long serialVersionUID = 1002885644;
  
  public LocalizationFacade()
  {
    super();
  }
  
  @Authenticate
  public static SupportedLocaleQuery getInstalledLocales()
  {
    SupportedLocaleQuery query = new SupportedLocaleQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getLocaleLabel());
    return query;
  }
  
  @Authenticate
  public static void installLocale(String localeString)
  {
    install(localeString);
    
    LockHolder.lock(LoaderDecorator.instance());
    ObjectCache.refreshCache();
    LockHolder.unlock();
  }

  @Transaction
  private static void install(String localeString)
  {
    Locale l = getLocaleFromString(localeString);
    String displayName = l.getDisplayName(Locale.ENGLISH);
    
    SupportedLocale sl = new SupportedLocale();
    sl.setEnumName(localeString);
    sl.setLocaleLabel(displayName);
    sl.getDisplayLabel().setDefaultValue(displayName);
    sl.apply();
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
  
  @Authenticate
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
  
  @Authenticate
  public static void importFile(InputStream file)
  {
    MdssLocalizationImporter mli = new MdssLocalizationImporter();
    mli.read(file);
    
    LockHolder.lock(LoaderDecorator.instance());
    ObjectCache.refreshCache();
    LockHolder.unlock();
  }
}
