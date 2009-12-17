package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.GeneratedEntityQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.Property;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;
import dss.vector.solutions.intervention.monitor.IndividualIPT;
import dss.vector.solutions.util.QueryUtil;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1894808272;

  public MosquitoCollection()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  @Transaction
  public void delete()
  {
    // Delete all of the sub collections
    MosquitoCollectionView view = this.getView();

    SubCollectionView[] collections = view.getSubCollections();

    for (SubCollectionView collection : collections)
    {
      collection.deleteConcrete();
    }

    // Delete all of the Assays
    List<? extends CollectionAssay> list = this.getAssays();

    for (CollectionAssay assay : list)
    {
      assay.delete();
    }

    // Delete all infection and pooled infection assays
    InfectionAssayView[] infectionAssays = view.getInfectionAssays();

    for (InfectionAssayView assay : infectionAssays)
    {
      assay.deleteConcrete();
    }

    PooledInfectionAssayView[] pooledInfectionAssays = view.getPooledInfectionAssays();

    for (PooledInfectionAssayView assay : pooledInfectionAssays)
    {
      assay.deleteConcrete();
    }

    // DELETE ALL MECHANISM ASSAYS
    BiochemicalAssayView[] biochemicalAssays = view.getBiochemicalAssays();

    for (BiochemicalAssayView assay : biochemicalAssays)
    {
      assay.deleteConcrete();
    }

    MolecularAssayView[] molecularAssays = view.getMolecularAssays();

    for (MolecularAssayView assay : molecularAssays)
    {
      assay.deleteConcrete();
    }

    super.delete();
  }

  private List<? extends CollectionAssay> getAssays()
  {
    CollectionAssayQuery query = new CollectionAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));
    OIterator<? extends CollectionAssay> it = query.getIterator();

    try
    {
      return it.getAll();
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void apply()
  {
    this.populateCollectionId();
    this.populateLifeStageName();
    this.validateCollectionDate();

    super.apply();
  }

  @Override
  public void validateCollectionDate()
  {
    if (this.getCollectionDate() != null && this.getCollectionDate().after(new Date()))
    {
      String msg = "It is impossible to have a test date after the current date";

      CurrentDateProblem p = new CurrentDateProblem(msg);
      p.setGivenDate(this.getCollectionDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, COLLECTIONDATE);
      p.apply();
      p.throwIt();
    }
  }

  private void populateLifeStageName()
  {
    for (LifeStage stage : this.getLifeStage())
    {
      this.setLifeStageName(stage.getEnumName());
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(Property.getNextId());
    }
  }

  @Override
  @Transaction
  public MosquitoCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MosquitoCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MosquitoCollectionView getView()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.populateView(this);

    return view;
  }

  public static MosquitoCollection getByCollectionId(String collectionId)
  {
    MosquitoCollectionQuery query = new MosquitoCollectionQuery(new QueryFactory());
    query.WHERE(query.getCollectionId().EQ(collectionId));
    OIterator<? extends MosquitoCollection> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      MosquitoCollection collection = iterator.next();
      iterator.close();
      return collection;
    }
    else
    {
      throw new DataNotFoundException("No mosquito collection with collection id [" + collectionId + "] found", MdTypeDAO.getMdTypeDAO(CLASS));
    }
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Boolean includeGeometry)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, includeGeometry, IndividualIPT.CLASS, IndividualIPT.FACILITY);

    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    SubCollectionQuery subCollectionQuery = (SubCollectionQuery) queryMap.get(SubCollection.CLASS);

    if (subCollectionQuery != null)
    {
      valueQuery.WHERE(subCollectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, SubCollection.CLASS, subCollectionQuery);

    }


    QueryUtil.joinGeoDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap);

    QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");

    return valueQuery;
  }
}
