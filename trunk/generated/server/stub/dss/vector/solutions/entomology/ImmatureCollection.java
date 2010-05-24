package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ImmatureCollection extends ImmatureCollectionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 746053127;

  public ImmatureCollection()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getCollectionId() != null)
    {
      return this.getCollectionId();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getCollectionId();
  }

  @Transaction
  public void deleteAll()
  {
    // DELETE ALL CollectionPremise
    List<CollectionPremise> premises = this.getPremises();

    for (CollectionPremise premise : premises)
    {
      premise.deleteAll();
    }

    this.delete();
  }

  public boolean hasPremises()
  {
    return ( this.getPremises().size() > 0 );
  }

  private List<CollectionPremise> getPremises()
  {
    CollectionPremiseQuery query = new CollectionPremiseQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(this));

    OIterator<? extends CollectionPremise> it = query.getIterator();

    try
    {
      List<? extends CollectionPremise> premises = it.getAll();

      return new LinkedList<CollectionPremise>(premises);
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
    this.validateStartDate();

    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }

    super.apply();
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null && this.getEndDate() != null)
    {
      if (this.getStartDate().after(this.getEndDate()))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();

        p.throwIt();
      }
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }

  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();

    view.populateView(this, null, null);

    return view;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    ImmatureCollectionQuery collectionQuery = (ImmatureCollectionQuery) queryMap.get(ImmatureCollection.CLASS);
    CollectionPremiseQuery collectionPremiseQuery = (CollectionPremiseQuery) queryMap.get(CollectionPremise.CLASS);
    PremiseTaxonQuery premiseTaxonQuery = (PremiseTaxonQuery) queryMap.get(PremiseTaxon.CLASS);
    CollectionContainerQuery collectionContainerQuery = (CollectionContainerQuery) queryMap.get(CollectionContainer.CLASS);
    if (collectionPremiseQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionPremise.CLASS, collectionPremiseQuery);
      valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
    }

    if (premiseTaxonQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PremiseTaxon.CLASS, premiseTaxonQuery);
      valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
    }

    if (collectionContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionContainer.CLASS, collectionContainerQuery);
      valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, ImmatureCollection.CLASS, collectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    boolean needsJoin = false;

    if (collectionContainerQuery == null)
    {
      collectionContainerQuery = new CollectionContainerQuery(queryFactory);
    }

    MdEntityDAOIF md = collectionContainerQuery.getMdClassIF();
    String numberwithwater = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberimmatures = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberlarvae = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberpupae = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberInhabitants = QueryUtil.getColumnName(md, CollectionPremise.NUMBERINHABITANTS);
    String numberWithImmatures = QueryUtil.getColumnName(md, CollectionPremise.NUMBERWITHIMMATURES);
    String numberExamined = QueryUtil.getColumnName(md, CollectionPremise.NUMBEREXAMINED);
    String premiseSize = QueryUtil.getColumnName(md, CollectionPremise.PREMISESIZE);

    /*
     * String numberContainers = QueryUtil.getColumnName(md,
     * CollectionContainer.NUMBERCONTAINERS); String numberdestroyed =
     * QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS); String
     * numberwithlarvicide = QueryUtil.getColumnName(md,
     * CollectionContainer.NUMBERCONTAINERS); String numberlarvaecollected =
     * QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS); String
     * numberpupaecollected = QueryUtil.getColumnName(md,
     * CollectionContainer.NUMBERCONTAINERS);
     * 
     * String numberImmaturesCol =
     * QueryUtil.getColumnName(CollectionContainer.getNumberImmaturesMd());
     * String numberLarvaeCol =
     * QueryUtil.getColumnName(CollectionContainer.getNumberLarvaeMd()); String
     * numberPupaeCol =
     * QueryUtil.getColumnName(CollectionContainer.getNumberPupaeMd()); String
     * numberWithLarvae = QueryUtil.getColumnName(md,
     * CollectionPremise.NUMBERWITHLARVAE); String numberWithPupae =
     * QueryUtil.getColumnName(md, CollectionPremise.NUMBERWITHPUPAE); String
     * premiseType = QueryUtil.getColumnName(md, CollectionPremise.PREMISETYPE);
     */

    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "SUM(" + numberimmatures + ")/SUM(" + numberWithImmatures + ")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "SUM(" + numberlarvae + ")/SUM(" + numberWithImmatures + ")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "SUM(" + numberpupae + ")/SUM(" + numberWithImmatures + ")*100") || needsJoin;

    QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "SUM(" + numberlarvae + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "SUM(" + numberpupae + ")/SUM(" + numberwithwater + ")*100");

    QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "SUM(" + numberimmatures + ")/SUM(" + numberExamined + "*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "SUM(" + numberlarvae + ")/SUM(" + numberExamined + "*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "SUM(" + numberpupae + ")/SUM(" + numberExamined + "*100)");

    QueryUtil.setSelectabeSQL(valueQuery, "pi", "SUM(" + numberpupae + ")/SUM(" + numberExamined + "*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppr", "SUM(" + numberpupae + ")/SUM(" + numberExamined + ")");
    QueryUtil.setSelectabeSQL(valueQuery, "ppha", "SUM(" + numberpupae + ")/SUM(" + premiseSize + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "pppe", "SUM(" + numberpupae + ")/SUM(" + numberInhabitants + ")*100");

    if (needsJoin)
    {
      // String tableName = premiseTaxonQuery.getMdClassIF().getTableName();
      // String tableAlias = premiseTaxonQuery.getTableAlias();

      if (collectionContainerQuery == null)
      {
        collectionContainerQuery = new CollectionContainerQuery(queryFactory);
        valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
      }
    }

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");
    // Percentage of water-holding containers with larvae by container type:
    // Number with larvae/Number with water*100

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "SUM(" + numberpupae + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "SUM(" + numberimmatures + ")/SUM(" + numberwithwater + ")*100");

    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());

  }
}
