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
