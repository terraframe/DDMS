package dss.vector.solutions.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.TeeOutputStream;

import com.runwaysdk.constants.DeployProperties;
import com.runwaysdk.logging.LogLevel;
import com.runwaysdk.logging.RunwayLogUtil;
import com.runwaysdk.util.FileIO;

public class ExcelUtil
{
  public static void respondError(BufferedInputStream errorStream, String filename, HttpServletResponse res, String managerId, Boolean hasSynonyms)
  {
    Integer statusCode = null;
    
    if (managerId != null)
    {
      // We're making up our own status codes here because we can
      statusCode = 701; // Request completed but with errors
      if (hasSynonyms)
      {
        statusCode = 702; // Request completed with errors and synonyms
      }
    }
    
    TeeOutputStream tee = null;
    ServletOutputStream streamToBrowser = null;
    BufferedOutputStream streamToXlsLogFile = null;

    // #3211
    String errorDir = DeployProperties.getDeployRoot() + "/../import errors";
    try
    {
      try
      {
        byte[] errorBytes = IOUtils.toByteArray(errorStream);
        errorStream.close();
        errorStream = new BufferedInputStream(new ByteArrayInputStream(errorBytes));

        File fDir = new File(errorDir);

        if (!fDir.exists())
        {
          fDir.mkdirs();
        }

        File errorFile = new File(fDir, filename);
        if (errorFile.exists())
        {
          FileIO.deleteFile(errorFile);
        }

        FileOutputStream fos = new FileOutputStream(errorFile);
        streamToXlsLogFile = new BufferedOutputStream(fos);

        res.setContentType("application/xls");
        res.addHeader("Content-Disposition", "attachment;filename=\"errors.xls\"");
        if (managerId != null)
        {
          res.addHeader("ExcelImportManagerId", managerId);
        }
        if (statusCode != null)
        {
          res.setStatus(statusCode);
        }

        streamToBrowser = res.getOutputStream();

        tee = new TeeOutputStream(streamToXlsLogFile, streamToBrowser); // The
                                                                        // tee
                                                                        // allows
                                                                        // us to
                                                                        // write
                                                                        // to 2
                                                                        // different
                                                                        // places
                                                                        // at
                                                                        // once.
        try
        {
          FileIO.write(tee, errorStream);
        }
        catch (IOException e)
        {
          // If the client closed their browser tab we'll get an IOEx. We still
          // want to make sure that log file gets written (#3462)
          streamToXlsLogFile.close();
          errorStream.close();

          if (errorFile.exists())
          {
            FileIO.deleteFile(errorFile);
          }

          errorStream = new BufferedInputStream(new ByteArrayInputStream(errorBytes));
          streamToXlsLogFile = new BufferedOutputStream(new FileOutputStream(errorFile));

          FileIO.write(streamToXlsLogFile, errorStream);
        }
      }
      finally
      {
        if (streamToBrowser != null)
        {
          streamToBrowser.close();
        }
        if (errorStream != null)
        {
          errorStream.close();
        }
        if (streamToXlsLogFile != null)
        {
          streamToXlsLogFile.close();
        }
        if (tee != null)
        {
          tee.close();
        }
      }
    }
    catch (Exception e)
    {
      RunwayLogUtil.logToLevel(LogLevel.ERROR, "Exception thrown while attempting to write excel error file to directory [" + errorDir + "].", e);
    }
  }
}
