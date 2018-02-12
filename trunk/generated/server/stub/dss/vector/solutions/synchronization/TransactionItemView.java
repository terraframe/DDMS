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
package dss.vector.solutions.synchronization;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
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
