/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.util.FileIO;

public class FileDownloadUtil
{
  public static void writeXLS(HttpServletResponse resp, String filename, InputStream inputStream)
      throws IOException
  {
    String type = "application/octet-stream";
    writeFile(resp, filename, "xlsx", inputStream, type);
  }

  public static void writeCSV(HttpServletResponse resp, String filename, InputStream inputStream)
      throws IOException
  {
    String type = "text/plain";
    writeFile(resp, filename, "csv", inputStream, type);
  }

  public static void writeTXT(HttpServletResponse resp, String filename, InputStream inputStream)
      throws IOException
  {
    String type = "text/plain";
    writeFile(resp, filename, "txt", inputStream, type);
  }

  public static void writeZIP(HttpServletResponse resp, String filename, InputStream inputStream)
      throws IOException
  {
    String type = "application/zip";
    writeFile(resp, filename, "zip", inputStream, type);
  }

  public static void writeFile(HttpServletResponse resp, String filename, String extension, InputStream inputStream, String type) throws IOException
  {
    Cookie cookie = new Cookie("downloadToken", "true");
    // 10 minute cookie expiration
    cookie.setMaxAge(10*60);
    resp.addCookie(cookie);
    
    resp.addHeader("Content-Disposition", "attachment;filename=\""+filename+"."+extension+"\"");
    resp.setContentType(type);
    ServletOutputStream stream = resp.getOutputStream();
    FileIO.write(stream, inputStream);
  }
}
