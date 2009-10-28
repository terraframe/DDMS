package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.util.FileIO;

public class FileDownloadUtil
{
  public static void writeXLS(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    writeFile(resp, filename, "xls", inputStream);
  }

  public static void writeCSV(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    writeFile(resp, filename, "csv", inputStream);
  }
  
  public static void writeTXT(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    writeFile(resp, filename, "txt", inputStream);
  }

  private static void writeFile(HttpServletResponse resp, String filename, String extension, InputStream inputStream) throws IOException
  {
    resp.addHeader("Content-Disposition", "attachment;filename=\""+filename+"."+extension+"\"");
    ServletOutputStream stream = resp.getOutputStream();
    FileIO.write(stream, inputStream);
  }
}
