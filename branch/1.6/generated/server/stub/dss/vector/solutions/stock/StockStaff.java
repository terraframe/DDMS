package dss.vector.solutions.stock;

import java.util.List;

import com.runwaysdk.query.QueryFactory;

public class StockStaff extends StockStaffBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257286441301L;
  
  public StockStaff()
  {
    super();
  }
  
  public static StockStaff[] getAll()
  {
    StockStaffQuery query = new StockStaffQuery(new QueryFactory());
   
    List<? extends StockStaff> list = query.getIterator().getAll();
    
    return list.toArray(new StockStaff[list.size()]);
  }
}
