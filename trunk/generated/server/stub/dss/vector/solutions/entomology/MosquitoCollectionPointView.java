package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class MosquitoCollectionPointView extends MosquitoCollectionPointViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242677987151L;

  public MosquitoCollectionPointView()
  {
    super();
  }

  public void populateView(MorphologicalSpecieGroup group)
  {
    this.setDateCollected(group.getCollection().getDateCollected());
    this.setGeoEntity(group.getCollection().getGeoEntity());
    this.setTotal(group.getCollection().getMosquitoTotal());
    this.setCollection(group.getCollection());

    super.populateView(group);
  }

  @Transaction
  public void apply()
  {
    Date collectionDate = this.getDateCollected();

    if (this.getCollection() == null)
    {
      MosquitoCollectionPoint collection = MosquitoCollectionPoint.findOrCreate(this.getGeoEntity(), collectionDate);

      this.setCollection(collection);
    }
    else if( this.getCollection() instanceof MosquitoCollectionPoint)
    {
      ConcreteMosquitoCollection collection = this.getCollection();

      if (collectionDate != null && !collection.getDateCollected().equals(collectionDate))
      {
        collection.lock();
        collection.setDateCollected(collectionDate);
        collection.apply();
      }
    }

    super.apply();
  }

  public static MosquitoCollectionPointView[] searchByGeoEntityAndDate(String geoId, Date startDate, Date endDate)
  {
    List<MosquitoCollectionPointView> list = new LinkedList<MosquitoCollectionPointView>();

    QueryFactory factory = new QueryFactory();
    MosquitoCollectionPointQuery collectionQuery = new MosquitoCollectionPointQuery(factory);
    MorphologicalSpecieGroupQuery specieQuery = new MorphologicalSpecieGroupQuery(factory);

    collectionQuery.WHERE(collectionQuery.getGeoEntity().getGeoId().EQ(geoId));
    collectionQuery.AND(collectionQuery.getDateCollected().GE(startDate));
    collectionQuery.AND(collectionQuery.getDateCollected().LE(endDate));

    specieQuery.WHERE(specieQuery.getCollection().EQ(collectionQuery));

    OIterator<? extends MorphologicalSpecieGroup> iterator = specieQuery.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        MorphologicalSpecieGroup group = iterator.next();
        MosquitoCollectionPointView view = new MosquitoCollectionPointView();

        view.populateView(group);

        list.add( view);
      }
    }
    finally
    {
      iterator.close();
    }

    return list.toArray(new MosquitoCollectionPointView[list.size()]);
  }
}
