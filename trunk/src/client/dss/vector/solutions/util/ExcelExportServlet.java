package dss.vector.solutions.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdClassQueryDTO;

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
    String fileName = split[split.length-1];
    InputStream inputStream = clientRequest.exportExcelFile(type, "setupExportListener", new String[]{});

    FileDownloadUtil.writeXLS(res, fileName, inputStream);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    ClientRequestIF clientRequest = (ClientRequestIF)req.getAttribute(ClientConstants.CLIENTREQUEST);
    MdClassQueryDTO query = FacadeDTO.getMDSSClasses(clientRequest);
    req.setAttribute("classes", query.getResultSet());

    req.getRequestDispatcher("/WEB-INF/excelExport.jsp").forward(req, resp);
  }

}
