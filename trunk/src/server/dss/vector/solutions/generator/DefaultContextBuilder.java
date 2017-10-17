package dss.vector.solutions.generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.RunwayExceptionIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.UnexpectedTypeException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilder;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdViewDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManager;

public class DefaultContextBuilder extends ContextBuilder implements ContextBuilderIF, Reloadable
{
  private static final String setupImportMethod = "setupImportListener";

  private String              methodName;

  private String[]            params;

  private ExcelImportManager  importer;

  public DefaultContextBuilder()
  {
    this(setupImportMethod, new String[] {});
  }

  public DefaultContextBuilder(String[] params, ExcelImportManager importer)
  {
    this(setupImportMethod, params);
    this.importer = importer;
  }

  public DefaultContextBuilder(String methodName, String[] params)
  {
    this.methodName = methodName;
    this.params = params;
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    MdClassDAOIF mdClass = MdClassDAO.getMdClassDAO(type);
    if (! ( mdClass instanceof MdViewDAO ) && ! ( mdClass instanceof MdBusinessDAO ))
    {
      throw new UnexpectedTypeException("Excel Importer does not support type [" + mdClass.definesType() + "]");
    }

    Sheet error = errorWorkbook.createSheet(sheetName);
    ImportContext context = new ManagedImportContext(sheet, sheetName, error, mdClass, this.importer);

    try
    {
      String definesType = context.getMdClass().definesType();
      // Load the type which is being exported
      Class<?> c = LoaderDecorator.load(definesType);

      // Get the listener method
      Method method = c.getMethod(this.methodName, ImportContext.class, String[].class, ExcelImportManager.class);

      // Invoke the method
      method.invoke(null, context, (Object) this.params, importer);
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
