package csu.mrc.ivcc.mdss.entomology;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

import csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionBase;
import csu.mrc.ivcc.mdss.entomology.MosquitoQuery;
import csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupQuery;

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
  
  public csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupView[] getUninterestingSpecieGroups()
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
}