package dss.vector.solutions.export.entomology;

import java.util.Date;
import java.util.Locale;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.io.ExcelExporter;
import com.terraframe.mojo.dataaccess.io.ExcelImporter;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.RequiredAttributeException;
import dss.vector.solutions.entomology.MorphologicalSpecieGroup;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.export.DynamicGeoColumnListener;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SentinelSite;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.HierarchyBuilder;

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
    MosquitoCollection collection = null;
    GeoEntity entity = getGeoEntity();
    Date collectionDate = this.getDateCollected();
    Term method = null;

    if (this.hasCollectionMethod())
    {
      method = Term.validateByDisplayLabel(this.getCollectionMethod(), MosquitoCollection.getCollectionMethodMd());
      collection = MosquitoCollection.searchByGeoEntityAndDateAndCollectionMethod(entity, collectionDate, method);
    }
    else
    {
      collection = MosquitoCollection.searchByGeoEntityAndDate(entity, collectionDate);
    }

    if (collection == null)
    {
      collection = new MosquitoCollection();
      collection.setGeoEntity(entity);
      collection.setCollectionMethod(method);
      collection.setDateCollected(collectionDate);
      collection.apply();
    }
    
    if (this.hasMorphologicalSpecieGroup())
    {
      MorphologicalSpecieGroup msg = new MorphologicalSpecieGroup();
      msg.setCollection(collection);
      msg.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), MorphologicalSpecieGroup.getSpecieMd()));
      msg.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(), MorphologicalSpecieGroup.getIdentificationMethodMd()));
      msg.setQuantity(this.getQuantity());
      msg.setQuantityMale(this.getQuantityMale());
      msg.setQuantityFemale(this.getQuantityFemale());
      msg.apply();
    }

    this.populateView(collection);
  }

  public MosquitoCollection findMatch()
  {
    GeoEntity entity = getGeoEntity();

    if (!this.hasCollectionMethod())
    {
      String msg = "Collection Method is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(MosquitoCollectionView.getCollectionMethodMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }

    if (entity == null)
    {
      String msg = "Geo Entity is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(MosquitoCollectionView.getGeoEntityMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }

    if (this.getDateCollected() == null)
    {
      String msg = "Date Collected is a required value";
      RequiredAttributeException e = new RequiredAttributeException(msg);
      e.setAttributeLabel(MosquitoCollectionView.getDateCollectedMd().getDisplayLabel(Session.getCurrentLocale()));
      e.apply();
      
      throw e;
    }

    /* FIXME MO REFACTOR
    CollectionMethod method = (CollectionMethod) CollectionMethod.validateByDisplayLabel(this
        .getCollectionMethod(), MosquitoCollection.getCollectionMethodMd());

    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(entity));
    query.WHERE(query.getDateCollected().EQ(this.getDateCollected()));
    query.WHERE(query.getCollectionMethod().EQ(method));

    OIterator<? extends MosquitoCollection> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        String message = "No mosquito collection found with date [" + this.getDateCollected()
            + "], Geo Entity [" + entity.getEntityName() + "], and collection method ["
            + method.getDisplayLabel().getValue(Locale.US) + "]";
        throw new DataNotFoundException(message, MdTypeDAO.getMdTypeDAO(MosquitoCollection.CLASS));
      }
    }
    finally
    {
      iterator.close();
    }*/
    return null; // FIXME MO REFACTOR (to make compile)
  }

  private boolean hasCollectionMethod()
  {
    return this.getCollectionMethod() != null && !this.getCollectionMethod().equals("");
  }

  private boolean hasMorphologicalSpecieGroup()
  {
    return this.getSpecie() != null && !this.getSpecie().equals("") && this.getIdentificationMethod() != null && !this.getIdentificationMethod().equals("") && this.getQuantity() != null;
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
    HierarchyBuilder builder = new HierarchyBuilder();
    builder.add(GeoHierarchy.getGeoHierarchyFromType(SentinelSite.CLASS));
    return new DynamicGeoColumnListener(CLASS, GEOENTITY, builder);
  }
}
