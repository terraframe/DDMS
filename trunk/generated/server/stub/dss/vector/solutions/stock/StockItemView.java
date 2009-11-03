package dss.vector.solutions.stock;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.query.AttributeEnumeration;
import com.terraframe.mojo.query.AttributeLocal;
import com.terraframe.mojo.query.AttributeReference;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.SelectablePrimitive;

import dss.vector.solutions.ontology.Term;

public class StockItemView extends StockItemViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257278739978L;
  
  public StockItemView()
  {
    super();
  }
 
  public void populateView(StockItem concrete)
  {
    this.setConcreteId(concrete.getId());

    this.setItemName(concrete.getItemName());
    this.setQuantity(concrete.getQuantity());
    this.setUnit(concrete.getUnit());
  }

  private void populateConcrete(StockItem concrete)
  {
    concrete.setItemName(this.getItemName());
    concrete.setQuantity(this.getQuantity());
    concrete.setUnit(this.getUnit());
  }

  private void buildAttributeMap(StockItem concrete)
  {
    new AttributeNotificationMap(concrete, StockItem.ITEMNAME, this, StockItemView.ITEMNAME);
    new AttributeNotificationMap(concrete, StockItem.QUANTITY, this, StockItemView.QUANTITY);
    new AttributeNotificationMap(concrete, StockItem.UNIT, this, StockItemView.UNIT);
  }

  @Override
  public void apply()
  {
    StockItem concrete = new StockItem();

    if (this.hasConcrete())
    {
      concrete = StockItem.get(this.getConcreteId());
    }

    // Build the attribute map between StockItem and
    // StockItemView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      StockItem.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public static StockItemViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    StockItemViewQuery query = new StockItemViewQuery(new QueryFactory());

    if (sortAttribute == null)
    {
      sortAttribute = StockItemView.CONCRETEID;
    }

    Selectable attribute = query.getComponentQuery().getSelectable(sortAttribute);

    if (attribute instanceof AttributeEnumeration)
    {
      attribute = ( (AttributeEnumeration) attribute ).aCharacter("enumName");
    }
    else if (attribute instanceof AttributeReference)
    {
      attribute = ((AttributeReference) attribute).aAttribute(Term.TERMNAME);
    }
    else if (attribute instanceof AttributeLocal)
    {
      attribute = ( (AttributeLocal) attribute ).currentLocale();
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }  
}
