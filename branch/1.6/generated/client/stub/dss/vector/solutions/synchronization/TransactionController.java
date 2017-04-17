package dss.vector.solutions.synchronization;

import java.io.IOException;

import javax.servlet.ServletException;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.system.transaction.TransactionRecordDTO;
import com.runwaysdk.system.transaction.TransactionRecordQueryDTO;

import dss.vector.solutions.util.ErrorUtility;

public class TransactionController extends TransactionControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/synchronization/Transaction/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = -738113886;

  public TransactionController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void viewRecordPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    if (pageSize == null || pageNumber == null)
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
  public void viewItemPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber, String recordId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      TransactionItemViewQueryDTO query = TransactionItemViewDTO.getQuery(clientRequest, recordId, sortAttribute, isAscending, pageSize, pageNumber);
      TransactionRecordDTO record = TransactionRecordDTO.get(clientRequest, recordId);
      renderItemPage(query, record);
    }
    catch (Throwable t)
    {
      boolean redirect = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirect)
      {
        this.failViewItemPage(sortAttribute, isAscending.toString(), pageSize.toString(), pageNumber.toString(), recordId);
      }
    }
  }
  
  @Override
  public void failViewItemPage(String sortAttribute, String isAscending, String pageSize, String pageNumber, String recordId) throws IOException, ServletException
  {
    this.viewRecordPage(TransactionRecordDTO.EXPORTSEQUENCE, false, 20, 1);
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
