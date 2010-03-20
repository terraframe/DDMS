package dss.vector.solutions.synchronization;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.transaction.TransactionItem;

public class TransactionItemFullView extends TransactionItemFullViewBase implements Reloadable
{
  private static final long serialVersionUID = -2050152056;
  
  public TransactionItemFullView()
  {
    super();
  }
  
  public static TransactionItemFullView getByItemId(String itemID)
  {
    TransactionItem item = TransactionItem.get(itemID);
    TransactionItemFullView view = new TransactionItemFullView();
    view.setRecordId(item.getTransactionRecord().getId());
    view.setActionLabel(item.getItemAction().get(0).getDisplayLabel());
    view.setXmlRecord(item.getXmlRecord());
    view.setComponentSeq(item.getComponentSeq());
    view.setComponentId(item.getComponentId());
    view.setComponentSiteMaster(item.getComponentSiteMaster());
    return view;
  }
}
