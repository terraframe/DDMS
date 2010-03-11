package dss.vector.solutions.synchronization;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;
import com.terraframe.mojo.system.transaction.TransactionRecord;
import com.terraframe.mojo.system.transaction.TransactionRecordQuery;

public class TransactionItemView extends TransactionItemViewBase implements Reloadable
{
  private static final long serialVersionUID = -424846230;

  public TransactionItemView()
  {
    super();
  }

  public static TransactionItemViewQuery getQuery(String recordID, String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    return new TransactionItemViewQuery(new QueryFactory(), recordID, sortAttribute, isAscending, pageSize, pageNumber);
  }

  public static TransactionRecordQuery getRecordQuery(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    TransactionRecordQuery query = new TransactionRecordQuery(new QueryFactory());

    sortAttribute = (sortAttribute == null ? TransactionRecord.EXPORTSEQUENCE : sortAttribute);
    isAscending = isAscending == null ? true : isAscending;
    SortOrder sortOrder = isAscending ? SortOrder.DESC : SortOrder.ASC;

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
