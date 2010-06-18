package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.util.FileIO;

public class FileDownloadUtil
{
  public static void writeXLS(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    String type = "application/octet-stream";
    writeFile(resp, filename, "xls", inputStream, type);
  }

  public static void writeCSV(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    String type = "text/plain";
    writeFile(resp, filename, "csv", inputStream, type);
  }
  
  public static void writeTXT(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    String type = "text/plain";
    writeFile(resp, filename, "txt", inputStream, type);
  }
  
  public static void writeZIP(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    String type = "application/zip";
    writeFile(resp, filename, "zip", inputStream, type);
  }
  
  private static void writeFile(HttpServletResponse resp, String filename, String extension, InputStream inputStream, String type) throws IOException
  {
    resp.addHeader("Content-Disposition", "attachment;filename=\""+filename+"."+extension+"\"");
    resp.setContentType(type);
    ServletOutputStream stream = resp.getOutputStream();
    FileIO.write(stream, inputStream);
  }
}
