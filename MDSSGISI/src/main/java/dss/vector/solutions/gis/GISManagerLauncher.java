package dss.vector.solutions.gis;

import java.util.Locale;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;

public class GISManagerLauncher
{
  public static void main(String[] args)
  {
    Locale locale = Locale.getDefault();

    if (args.length > 0)
    {
      String[] localeInfo = args[0].split("_");
      switch (localeInfo.length)
      {
        case 1:
          locale = new Locale(localeInfo[0]);
          break;
        case 2:
          locale = new Locale(localeInfo[0], localeInfo[1]);
          break;
        case 3:
          locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
          break;
      }
    }

    Localizer.setInstance("localization", locale);

    final Display display = Display.getDefault();

    class WindowRunner implements Runnable, Reloadable
    {
      public void run()
      {
        try
        {
          Class<?> clazz = LoaderDecorator.load("dss.vector.solutions.gis.GISManagerWindow");
          Object instance = clazz.newInstance();
          clazz.getMethod("run").invoke(instance);
        }
        catch (RuntimeException e)
        {
          throw e;
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
      }
    }

    Realm.runWithDefault(SWTObservables.getRealm(display), new WindowRunner());
  }
}
