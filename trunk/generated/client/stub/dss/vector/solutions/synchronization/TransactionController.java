package dss.vector.solutions.synchronization;

import java.io.IOException;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.system.transaction.TransactionRecordDTO;
import com.terraframe.mojo.system.transaction.TransactionRecordQueryDTO;

public class TransactionController extends TransactionControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/synchronization/Transaction/";

  public static final String LAYOUT           = "/layout.jsp";
  
  private static final long serialVersionUID = -738113886;
  
  public TransactionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void viewRecordPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    if (pageSize==null || pageNumber==null)
    {
      pageSize = 20;
      pageNumber = 1;
    }
    renderRecordPage(TransactionItemViewDTO.getRecordQuery(getClientRequest(), sortAttribute, isAscending, pageSize, pageNumber));
  }

  private void renderRecordPage(TransactionRecordQueryDTO query) throws IOException, ServletException
  {
    req.setAttribute("query", query);
    render("viewAllRecords.jsp");
  }
  
  @Override
  public void viewItemPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String recordID) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    TransactionItemViewQueryDTO query = TransactionItemViewDTO.getQuery(clientRequest, recordID, sortAttribute, isAscending, pageSize, pageNumber);
    TransactionRecordDTO record = TransactionRecordDTO.get(clientRequest, recordID);
    renderItemPage(query, record);
  }

  private void renderItemPage(TransactionItemViewQueryDTO query, TransactionRecordDTO record) throws IOException, ServletException
  {
    req.setAttribute("query", query);
    req.setAttribute("record", record);
    render("viewRecord.jsp");
  }
  
  @Override
  public void viewItem(String id) throws IOException, ServletException
  {
    renderViewItem(TransactionItemFullViewDTO.getByItemId(getClientRequest(), id));
  }

  private void renderViewItem(TransactionItemFullViewDTO item) throws IOException, ServletException
  {
    req.setAttribute("item", item);
    render("viewItem.jsp");
  }
}
