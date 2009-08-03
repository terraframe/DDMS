package dss.vector.solutions.export.entomology;

import java.util.Locale;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.NonSentinelSite;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.mo.CollectionMethod;

public class MosquitoCollectionView extends MosquitoCollectionViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236703946827L;

  private String            concreteId;

  public MosquitoCollectionView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    CollectionMethod method = null;
    GeoEntity entity = getGeoEntity();

    if (this.hasCollectionMethod())
    {
      method = (CollectionMethod) CollectionMethod.validateByDisplayLabel(this.getCollectionMethod());
    }

    MosquitoCollection collection = new MosquitoCollection();
    collection.setGeoEntity(entity);
    collection.setCollectionMethod(method);
    collection.setDateCollected(this.getDateCollected());
    collection.apply();

    this.populateView(collection);
  }

  public MosquitoCollection findMatch()
  {
    CollectionMethod method = null;
    GeoEntity entity = getGeoEntity();

    // I commented out the empty value check on collection method
    // because collection method is a required value on mosquito collections,
    // and if the following query is run when Collection Method is null then
    // a uninformative null pointer exception is thrown.
    //  -Justin Smethie

    // if(this.hasCollectionMethod())
    // {
    method = (CollectionMethod) CollectionMethod.validateByDisplayLabel(this.getCollectionMethod());
    // }

    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(entity));
    query.WHERE(query.getDateCollected().EQ(this.getDateCollected()));
    query.WHERE(query.getCollectionMethod().EQ(method));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    MosquitoCollection match = null;
    if (iterator.hasNext())
    {
      match = iterator.next();
    }
    iterator.close();

    if (match == null)
    {
      String message = "No mosquito collection found with date [" + this.getDateCollected()
          + "], Geo Entity [" + entity.getEntityName() + "], and collection method ["
          + method.getDisplayLabel().getValue(Locale.US) + "]";
      throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(MosquitoCollection.CLASS));
    }

    return match;
  }

  private boolean hasCollectionMethod()
  {
    return this.getCollectionMethod() != null && !this.getCollectionMethod().equals("");
  }

  private void populateView(MosquitoCollection collection)
  {
    this.setConcreteId(collection.getId());
    this.setDateCollected(collection.getDateCollected());

    if (collection.getCollectionMethod() != null)
    {
      this.setCollectionMethod(collection.getCollectionMethod().getTermName());
    }
  }

  public void setConcreteId(String concreteId)
  {
    this.concreteId = concreteId;
  }

  public String getConcreteId()
  {
    return concreteId;
  }

  @Transaction
  public void deleteConcrete()
  {
    if (hasConcreteId())
    {
      MosquitoCollection.get(this.getConcreteId()).delete();
    }
  }

  private boolean hasConcreteId()
  {
    return this.concreteId != null && !this.concreteId.equals("");
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    exporter.addListener(createExcelGeoListener());
  }

  public static void setupImportListener(ExcelImporter importer, String... params)
  {
    importer.addListener(createExcelGeoListener());
  }

  private static DynamicGeoColumnListener createExcelGeoListener()
  {
    GeoHierarchy sentinelSite = GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS);
    GeoHierarchy nonSentinelSite = GeoHierarchy.getGeoHierarchyFromType(NonSentinelSite.CLASS);
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, sentinelSite, nonSentinelSite);
  }
}
