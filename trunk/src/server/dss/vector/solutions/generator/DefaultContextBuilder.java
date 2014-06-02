package dss.vector.solutions.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.RunwayExceptionIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilder;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;

public class DefaultContextBuilder extends ContextBuilder implements ContextBuilderIF, Reloadable
{
  private String   methodName;

  private String[] params;

  public DefaultContextBuilder()
  {
    this("setupImportListener", new String[] {});
  }

  public DefaultContextBuilder(String methodName, String[] params)
  {
    this.methodName = methodName;
    this.params = params;
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    ImportContext context = super.createContext(sheet, sheetName, errorWorkbook, type);

    try
    {
      String definesType = context.getMdClass().definesType();
      // Load the type which is being exported
      Class<?> c = LoaderDecorator.load(definesType);

      // Get the listener method
      Method method = c.getMethod(this.methodName, ImportContext.class, String[].class);

      // Invoke the method
      method.invoke(null, context, (Object) this.params);
    }
    catch (NoSuchMethodException e)
    {
      // If the method doesn't exist then do nothing
    }
    catch (InvocationTargetException e)
    {
      Throwable targetException = e.getTargetException();
      if (targetException instanceof RunwayExceptionIF)
      {
        throw (RuntimeException) targetException;
      }
      else
      {
        throw new ProgrammingErrorException(e);
      }
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }

    return context;
  }
}
