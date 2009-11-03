package dss.vector.solutions.stock;

import dss.vector.solutions.ontology.Term;

/**
 * @author jsmethie
 *
 */
public class StockItem extends StockItemBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257278739419L;
  
  public StockItem()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    // ITN Community Distribution class has no attributes that can form a unique
    // identifier
    return this.getId();
  }
  
  @Override
  public StockItemView getView()
  {
    StockItemView view = new StockItemView();
    view.populateView(this);

    return view;
  }

  @Override
  public StockItemView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public StockItemView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  public void apply()
  {
    validateItemName();
    
    super.apply();
  }
  
  @Override
  public void validateItemName()
  {
    Term item = this.getItemName();

    if(item != null && !item.isLeaf())
    {      
      String msg = "Term [" + item.getOptionName() + "] may not have any children to be a valid item.";

      ItemLeafProblem p = new ItemLeafProblem(msg);
      p.setNotification(this, ITEMNAME);
      p.setItemName(item.getOptionName());
      p.apply();
      
      p.throwIt();
    }
  }

}
