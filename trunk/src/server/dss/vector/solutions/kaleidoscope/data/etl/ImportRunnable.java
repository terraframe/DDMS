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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.constants.MdAttributeDecInfo;
import com.runwaysdk.dataaccess.MdAttributeDecDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDecDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.kaleidoscope.data.etl.ImportValidator.DecimalAttribute;
import dss.vector.solutions.kaleidoscope.data.etl.excel.CountSheetHandler;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelDataFormatter;
import dss.vector.solutions.kaleidoscope.data.etl.excel.ExcelSheetReader;
import dss.vector.solutions.kaleidoscope.data.etl.excel.SourceContentHandler;

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

  private ProgressMonitorIF monitor;

  private String            configuration;

  private File              file;

  public ImportRunnable(String configuration, File file, ProgressMonitorIF monitor)
  {
    this.configuration = configuration;
    this.file = file;

    this.monitor = monitor;
  }

  public ImportResponseIF run()
  {
    try
    {
      Disease current = Disease.getCurrent();

      int total = this.getRowNum(this.file);
      monitor.setTotal(total);

      /*
       * First create the data types from the configuration
       */
      DataSetBuilderIF builder = new DataSetBuilder(configuration);
      builder.build();

      /*
       * Create and import the view objects from the configuration
       */
      SourceContextIF sContext = builder.getSourceContext();
      TargetContextIF tContext = builder.getTargetContext();

      /*
       * Before importing the data we must validate that the location text
       * information
       */
      this.validateAndConfigure(sContext, tContext, current);

      /*
       * Import the data
       */
      monitor.setState(DataImportState.DATAIMPORT);

      SuccessResponse summary = this.importData(file, sContext, tContext, current);

      /*
       * Return a JSONArray of the datasets which were created as part of the
       * import. Do not include datasets which have already been created.
       */
      JSONArray datasets = new JSONArray();

      List<TargetDefinitionIF> definitions = tContext.getDefinitions();

      for (TargetDefinitionIF definition : definitions)
      {
        String type = definition.getTargetType();

        MdWebForm mdForm = MdWebForm.getByKey(type);
        MdClass mdClass = mdForm.getFormMdClass();
        MappableClass mClass = MappableClass.getMappableClass(mdClass);

        datasets.put(mClass.toJSON());

        /*
         * Update classifiers value
         */
        List<TargetFieldIF> fields = definition.getFields();

        for (TargetFieldIF field : fields)
        {
          if (field instanceof TargetFieldClassifier)
          {
            String key = field.getKey();

            MdWebAttribute mdWebAttribute = MdWebAttribute.getByKey(key);

            TargetFieldClassifierBindingQuery query = new TargetFieldClassifierBindingQuery(new QueryFactory());
            query.WHERE(query.getTargetAttribute().EQ(mdWebAttribute));

            OIterator<? extends TargetFieldClassifierBinding> it = query.getIterator();

            try
            {
              while (it.hasNext())
              {
                TargetFieldClassifierBinding binding = it.next();
                binding.lock();
                binding.setIsValidate(true);
                binding.apply();
              }
            }
            finally
            {
              it.close();
            }
          }
        }

      }

      monitor.setState(DataImportState.COMPLETE);

      summary.setDatasets(datasets);

      // Return the new data set definition
      return summary;
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

  @Transaction
  private ImportResponseIF validateAndConfigure(SourceContextIF sContext, TargetContextIF tContext, Disease current) throws FileNotFoundException, Exception, IOException
  {
    /*
     * Before importing the data we must validate that the location text
     * information
     */
    monitor.setState(DataImportState.VALIDATION);
    ValidationResult result = this.validateData(file, sContext, tContext, current);

    if (result.getResponse() != null)
    {
      monitor.setState(DataImportState.VALIDATIONFAIL);
      return result.getResponse();
    }

    /*
     * Update any scale or precision which is greater than its current
     * definition
     */
    this.updateScaleAndPrecision(result.getAttributes());

    return null;
  }

  private void updateScaleAndPrecision(Map<String, DecimalAttribute> attributes)
  {
    Set<Entry<String, DecimalAttribute>> entries = attributes.entrySet();

    for (Entry<String, DecimalAttribute> entry : entries)
    {
      String mdAttributeId = entry.getKey();
      DecimalAttribute attribute = entry.getValue();

      int scale = attribute.getScale();
      int precision = attribute.getPrecision();

      MdAttributeDecDAOIF mdAttributeIF = (MdAttributeDecDAOIF) MdAttributeDecDAO.get(mdAttributeId);

      int eLength = new Integer(mdAttributeIF.getLength()).intValue();
      int eScale = new Integer(mdAttributeIF.getDecimal()).intValue();
      int ePrecision = eLength - eScale;

      if (precision > ePrecision || scale > eScale)
      {
        int nLength = Math.max(Math.max(precision + scale, precision + eScale), Math.max(ePrecision + scale, ePrecision + eScale));
        int nScale = Math.max(scale, eScale);

        MdAttributeDecDAO mdAttribute = (MdAttributeDecDAO) mdAttributeIF.getBusinessDAO();
        mdAttribute.setValue(MdAttributeDecInfo.LENGTH, new Integer(nLength).toString());
        mdAttribute.setValue(MdAttributeDecInfo.DECIMAL, new Integer(nScale).toString());
        mdAttribute.apply();
      }
    }
  }

  private SuccessResponse importData(File file, SourceContextIF sContext, TargetContextIF tContext, Disease current) throws FileNotFoundException, IOException, Exception
  {
    Converter converter = new Converter(tContext, current, this.monitor);

    FileInputStream istream = new FileInputStream(file);

    try
    {
      SourceContentHandler handler = new SourceContentHandler(converter, sContext, this.monitor);
      ExcelDataFormatter formatter = new ExcelDataFormatter();

      ExcelSheetReader reader = new ExcelSheetReader(handler, formatter);
      reader.process(istream);

      SuccessResponse summary = new SuccessResponse(sContext, tContext);
      summary.setTotal(this.monitor.getImportCount());
      summary.setFailures(handler.getNumberOfErrors());

      Workbook errors = converter.getErrors();

      if (errors != null)
      {
        try (PipedInputStream pis = new PipedInputStream())
        {
          CopyRunnable runnable = new CopyRunnable(pis, errors);
          Thread t = new Thread(runnable);
          t.setUncaughtExceptionHandler(runnable);
          t.setDaemon(true);
          t.start();

          VaultFile vf2 = VaultFile.createAndApply(file.getName(), pis);

          t.join();

          summary.setFileId(vf2.getId());

          if (runnable.getThrowable() != null)
          {
            throw new ProgrammingErrorException(runnable.getThrowable());
          }
        }
      }

      if (converter.getProblems().size() > 0)
      {
        summary.setProblems(converter.getProblems());
        // ProblemResponse response = new
        // ProblemResponse(converter.getProblems(), sContext, tContext,
        // current);
        // summary.put("problems", response.getProblemsJSON());
      }

      return summary;
    }
    finally
    {
      istream.close();
    }
  }

  private ValidationResult validateData(File file, SourceContextIF sContext, TargetContextIF tContext, Disease current) throws FileNotFoundException, Exception, IOException
  {
    ImportValidator converter = new ImportValidator(tContext);

    FileInputStream istream = new FileInputStream(file);

    try
    {
      SourceContentHandler handler = new SourceContentHandler(converter, sContext, this.monitor);
      ExcelDataFormatter formatter = new ExcelDataFormatter();

      ExcelSheetReader reader = new ExcelSheetReader(handler, formatter);
      reader.process(istream);
    }
    finally
    {
      istream.close();
    }

    ImportResponseIF response = null;

    if (converter.getProblems().size() > 0)
    {
      response = new ProblemResponse(converter.getProblems(), sContext, tContext, current);
    }

    return new ValidationResult(response, converter.getAttributes());
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
