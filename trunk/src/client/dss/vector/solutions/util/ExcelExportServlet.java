package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdClassQueryDTO;
import com.terraframe.mojo.util.FileIO;

public class ExcelExportServlet extends HttpServlet implements Reloadable
{
  /**
   *
   */
  private static final long serialVersionUID = -4211492402501853068L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    String type = req.getParameter("type");

    String[] split = type.split("\\.");
    String fileName = split[split.length-1] + ".xls";
    InputStream inputStream = clientRequest.exportExcelFile(type);

    writeExcelFile(res, fileName, inputStream);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    MdClassQueryDTO query = FacadeDTO.getMDSSClasses(clientRequest);
    req.setAttribute("classes", query.getResultSet());

    req.getRequestDispatcher("/WEB-INF/excelExport.jsp").forward(req, resp);
  }

  public static void writeExcelFile(HttpServletResponse resp, String filename, InputStream inputStream) throws IOException
  {
    resp.addHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
    ServletOutputStream stream = resp.getOutputStream();
    FileIO.write(stream, inputStream);
  }
}
