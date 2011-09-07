package dss.vector.solutions.synchronization;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.system.transaction.TransactionRecord;
import com.runwaysdk.system.transaction.TransactionRecordQuery;

public class TransactionItemView extends TransactionItemViewBase implements Reloadable
{
  private static final long serialVersionUID = -424846230;

  public TransactionItemView()
  {
    super();
  }

  public static TransactionItemViewQuery getQuery(String recordId, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    QueryFactory factory = new QueryFactory();
    TransactionItemViewQuery query = new TransactionItemViewQuery(factory, recordId);

    isAscending = ( isAscending == null ? false : isAscending );
    SortOrder sortOrder = ( isAscending ? SortOrder.ASC : SortOrder.DESC );
    
    if (sortAttribute != null)
    {
      if (sortAttribute.equals(TransactionItemView.COMPONENTID))
      {
        query.ORDER_BY(query.getComponentId(), sortOrder);
      }
      if (sortAttribute.equals(TransactionItemView.ACTIONLABEL))
      {
        query.ORDER_BY(query.getActionLabel(), sortOrder);
      }
      if (sortAttribute.equals(TransactionItemView.COMPONENTSEQ))
      {
        query.ORDER_BY(query.getComponentSeq(), sortOrder);
      }
    }

    if (pageSize == null || pageNumber == null)
    {
      pageSize = 20;
      pageNumber = 1;
    }
    
    query.restrictRows(pageSize, pageNumber);

    return query;
  }

  public static TransactionRecordQuery getRecordQuery(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    TransactionRecordQuery query = new TransactionRecordQuery(new QueryFactory());

    sortAttribute = ( sortAttribute == null ? TransactionRecord.EXPORTSEQUENCE : sortAttribute );
    isAscending = ( isAscending == null ? false : isAscending );
    SortOrder sortOrder = ( isAscending ? SortOrder.ASC : SortOrder.DESC );

    if (sortAttribute != null)
    {
      if (sortAttribute.equals(TransactionRecord.CREATEDATE))
      {
        query.ORDER_BY(query.getCreateDate(), sortOrder);
      }
      if (sortAttribute.equals(TransactionRecord.SEQ))
      {
        query.ORDER_BY(query.getSeq(), sortOrder);
      }
      if (sortAttribute.equals(TransactionRecord.EXPORTSEQUENCE))
      {
        query.ORDER_BY(query.getExportSequence(), sortOrder);
      }
      if (sortAttribute.equals(TransactionRecord.SITEMASTER))
      {
        query.ORDER_BY(query.getSiteMaster(), sortOrder);
      }
    }

    if (pageSize != null && pageNumber != null)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
}
