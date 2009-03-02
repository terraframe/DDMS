package mdss.ivcc.mrc.csu.entomology;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

public abstract class AbstractMosquitoCollection extends AbstractMosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288138118L;
  
  public AbstractMosquitoCollection()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO The date format needs to be localizable
    DateFormat format = DateFormat.getDateInstance();
    return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
  }  
  
  @Override
  public String toString()
  {
    return this.buildKey();
  }
  
  @Override
  public MorphologicalSpecieGroupView[] getMorphologicalSpecieGroups()
  {
    List<MorphologicalSpecieGroupView> list = new LinkedList<MorphologicalSpecieGroupView>();

    MorphologicalSpecieGroupQuery query = new MorphologicalSpecieGroupQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);
    
    OIterator<? extends MorphologicalSpecieGroup> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      list.add(iterator.next().getView());
    }
    
    iterator.close();
    
    return list.toArray(new MorphologicalSpecieGroupView[list.size()]);
  }
  
  @Override
  public MosquitoView[] getMosquitos()
  {
    List<MosquitoView> list = new LinkedList<MosquitoView>();

    MosquitoQuery query = new MosquitoQuery(new QueryFactory());
    query.WHERE(query.getCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);
    
    OIterator<? extends Mosquito> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      list.add(iterator.next().getView());
    }
    
    iterator.close();
    
    return list.toArray(new MosquitoView[list.size()]);
  }
  
  public mdss.ivcc.mrc.csu.entomology.UninterestingSpecieGroupView[] getUninterestingSpecieGroups()
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
    
    for(MorphologicalSpecieGroupView view : this.getMorphologicalSpecieGroups())
    {
      view.delete();
    }
    
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
}
