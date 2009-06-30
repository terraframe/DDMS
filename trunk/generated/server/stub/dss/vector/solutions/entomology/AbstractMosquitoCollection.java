package dss.vector.solutions.entomology;


import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class AbstractMosquitoCollection extends AbstractMosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288138118L;
  
  public AbstractMosquitoCollection()
  {
    super();
  }    
  
  @Override
  public MosquitoView[] getMosquitos()
  {
    List<MosquitoView> list = new LinkedList<MosquitoView>();

    OIterator<? extends Mosquito> it = this.getAllMosquitos();
    
    try
    {
      while(it.hasNext())
      {
        list.add(it.next().getView());
      }
      
      return list.toArray(new MosquitoView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }
  
  public dss.vector.solutions.entomology.UninterestingSpecieGroupView[] getUninterestingSpecieGroups()
  {
    List<UninterestingSpecieGroupView> list = new LinkedList<UninterestingSpecieGroupView>();

    UninterestingSpecieGroupQuery query = new UninterestingSpecieGroupQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);
    
    OIterator<? extends UninterestingSpecieGroup> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      list.add(iterator.next().getView());
    }
    
    iterator.close();
    
    return list.toArray(new UninterestingSpecieGroupView[list.size()]);
  }
  
  @Override
  public void delete()
  {
    //An abstract collection has aggregation with
    //MorphologicalSpeiceGroups, UninterestingSpecieGroup, and Mosquitos thus
    //they also need to be deleted when deleting a collection
        
    //Delete all Mosquitos
    for(MosquitoView view : this.getMosquitos())
    {
      view.delete();
    }
    
    for(UninterestingSpecieGroupView view : this.getUninterestingSpecieGroups())
    {
      view.delete();
    }
        
    super.delete();
  }
  
  public static ValueQuery searchByCollectionId(String collectionId, String type)
  {
    QueryFactory f = new QueryFactory();

    ConcreteMosquitoCollectionQuery q = new ConcreteMosquitoCollectionQuery(f);
    ValueQuery valueQuery = new ValueQuery(f);
        
    Selectable[] selectables = new Selectable[] { q.getId(), q.getCollectionId(), q.getGeoEntity().getGeoId(), q.getGeoEntity().getEntityName(), q.getDateCollected(), q.getType()};
    valueQuery.SELECT(selectables);

    String statement = collectionId + "%";
        
    Condition or = OR.get(q.getCollectionId().LIKE(statement), q.getGeoEntity().getGeoId().LIKE(statement), q.getGeoEntity().getEntityName().LIKE(statement));

    if (type != null && type.length() > 0)
    {
      valueQuery.WHERE(AND.get(or, q.getType().EQ(type)));
    }
    else
    {
      valueQuery.WHERE(or);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }
}
