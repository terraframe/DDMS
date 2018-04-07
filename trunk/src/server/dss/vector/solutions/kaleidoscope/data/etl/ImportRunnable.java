/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.data.etl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.kaleidoscope.data.etl.ImportValidator.DecimalAttribute;
import dss.vector.solutions.kaleidoscope.data.etl.excel.CountSheetHandler;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelDataFormatter;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelSheetReader;
import dss.vector.solutions.kaleidoscope.data.etl.excel.JobHistoryProgressMonitor;

public class ImportRunnable implements Reloadable
{
  static class CopyRunnable implements Runnable, Reloadable, UncaughtExceptionHandler
  {
    private PipedInputStream istream;

    private Workbook         errors;

    private Throwable        throwable;

    public CopyRunnable(PipedInputStream istream, Workbook errors)
    {
      this.istream = istream;
      this.errors = errors;
      this.throwable = null;
    }

    @Override
    public void run()
    {
      try (PipedOutputStream pos = new PipedOutputStream(this.istream))
      {
        errors.write(pos);
      }
      catch (IOException e)
      {
        this.throwable = e;
      }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
      this.throwable = e;
    }

    public Throwable getThrowable()
    {
      return throwable;
    }
  }

  static class ValidationResult implements Reloadable
  {
    private ImportResponseIF              response;

    private Map<String, DecimalAttribute> attributes;

    public ValidationResult(ImportResponseIF response, Map<String, DecimalAttribute> attributes)
    {
      this.response = response;
      this.attributes = attributes;
    }

    public Map<String, DecimalAttribute> getAttributes()
    {
      return attributes;
    }

    public ImportResponseIF getResponse()
    {
      return response;
    }
  }

  private JobHistoryProgressMonitor monitor;

  private String                    configuration;

  private File                      file;

  private String                    filename;

  public ImportRunnable(String filename, String configuration, File file, JobHistoryProgressMonitor monitor)
  {
    this.filename = filename;
    this.configuration = configuration;
    this.file = file;

    this.monitor = monitor;
  }

  public void run()
  {
    try
    {
      int total = this.getRowNum(this.file);
      monitor.setTotal(total);

      /*
       * First create the data types from the configuration: This causes a
       * reload
       */
      Class<?> builderClazz = LoaderDecorator.load("dss.vector.solutions.kaleidoscope.data.etl.DataSetBuilder");

      Object builder = builderClazz.getConstructor(String.class).newInstance(this.configuration);
      builderClazz.getMethod("build").invoke(builder);

      String sheetName = (String) builderClazz.getMethod("getSheetName").invoke(builder);
      String id = (String) builderClazz.getMethod("getId").invoke(builder);

      Class<?> runnableClazz = LoaderDecorator.load("dss.vector.solutions.kaleidoscope.data.etl.DataImportRunnable");

      // public DataImportRunnable(String filename, File file, ProgressMonitorIF
      // monitor)

      Object runnable = runnableClazz.getConstructor(String.class, String.class, File.class, String.class) //
          .newInstance(this.configuration, this.filename, this.file, this.monitor.getHistory().getId());

      runnableClazz.getMethod("run", String.class, String.class).invoke(runnable, id, sheetName);
    }
    catch (RunwayException | SmartException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private int getRowNum(File file) throws FileNotFoundException, IOException, Exception
  {
    FileInputStream istream = new FileInputStream(file);

    try
    {
      CountSheetHandler handler = new CountSheetHandler();
      ExcelDataFormatter formatter = new ExcelDataFormatter();

      ExcelSheetReader reader = new ExcelSheetReader(handler, formatter);
      reader.process(istream);

      return handler.getRowNum();
    }
    finally
    {
      istream.close();
    }
  }
}
