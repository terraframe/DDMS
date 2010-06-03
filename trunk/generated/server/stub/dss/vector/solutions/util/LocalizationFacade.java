package dss.vector.solutions.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import com.runwaysdk.business.rbac.Authenticate;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.SupportedLocale;
import com.runwaysdk.system.metadata.SupportedLocaleQuery;

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
  }
}
