package csu.mrc.ivcc.mdss.entomology;


import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.OrderBy.SortOrder;

import csu.mrc.ivcc.mdss.entomology.CompositeMosquitoCollectionBase;
import csu.mrc.ivcc.mdss.entomology.MosquitoCollectionPointQuery;

public class CompositeMosquitoCollection extends CompositeMosquitoCollectionBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236104215387L;

  public CompositeMosquitoCollection()
  {
    super();
  }

  @Override
  public MosquitoCollectionPointView[] getCollections()
  {
    List<MosquitoCollectionPointView> list = new LinkedList<MosquitoCollectionPointView>();
    MosquitoCollectionPointQuery query = new MosquitoCollectionPointQuery(new QueryFactory());
    query.WHERE(query.getCompositeCollection().getId().EQ(this.getId()));
    query.ORDER_BY(query.getCreateDate(), SortOrder.ASC);

    OIterator<? extends MosquitoCollectionPoint> iterator = query.getIterator();

    while (iterator.hasNext())
    {
      list.addAll(iterator.next().getViews());
    }

    iterator.close();

    return list.toArray(new MosquitoCollectionPointView[list.size()]);
  }
}
