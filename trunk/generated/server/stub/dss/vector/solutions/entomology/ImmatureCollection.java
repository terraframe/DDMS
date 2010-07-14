package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
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

      if (collectionPremiseQuery == null)
      {
        collectionPremiseQuery = new CollectionPremiseQuery(valueQuery);
        valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
      }

      QueryUtil.joinTermAllpaths(valueQuery, PremiseTaxon.CLASS, premiseTaxonQuery);
      valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
    }

    if (collectionContainerQuery != null)
    {
      if (collectionPremiseQuery == null)
      {
        collectionPremiseQuery = new CollectionPremiseQuery(valueQuery);
        valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
      }

      if (premiseTaxonQuery == null)
      {
        premiseTaxonQuery = new PremiseTaxonQuery(valueQuery);
        valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
      }

      QueryUtil.joinTermAllpaths(valueQuery, CollectionContainer.CLASS, collectionContainerQuery);
      valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, ImmatureCollection.CLASS, collectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    boolean needsJoin = false;

    MdEntityDAOIF containerMd = MdEntityDAO.getMdEntityDAO(CollectionContainer.CLASS);
    String numberContainers = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERCONTAINERS);
    needsJoin = valueQuery.hasSelectableRef(numberContainers) || needsJoin;

    String numberdestroyed = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERDESTROYED);
    needsJoin = valueQuery.hasSelectableRef(numberdestroyed) || needsJoin;

    String numberwithlarvicide = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERWITHLARVICIDE);
    needsJoin = valueQuery.hasSelectableRef(numberwithlarvicide) || needsJoin;

    String numberwithwater = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERWITHWATER);
    needsJoin = valueQuery.hasSelectableRef(numberwithwater) || needsJoin;

    String numberimmatures = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERIMMATURES);
    needsJoin = valueQuery.hasSelectableRef(numberimmatures) || needsJoin;

    String numberlarvae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERLARVAE);
    needsJoin = valueQuery.hasSelectableRef(numberlarvae) || needsJoin;

    String numberpupae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERPUPAE);
    needsJoin = valueQuery.hasSelectableRef(numberpupae) || needsJoin;

    String numberlarvaecollected = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERLARVAECOLLECTED);
    needsJoin = valueQuery.hasSelectableRef(numberlarvaecollected) || needsJoin;

    String numberpupaecollected = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERPUPAECOLLECTED);
    needsJoin = valueQuery.hasSelectableRef(numberpupaecollected) || needsJoin;

    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(CollectionPremise.CLASS);
    String numberInhabitants = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERINHABITANTS);
    String numberWithImmatures = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHIMMATURES);
    String numberExamined = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBEREXAMINED);
    String numberWithLarvae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHLARVAE);
    String numberWithPupae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHPUPAE);
    String premiseSize = QueryUtil.getColumnName(premiseMd, CollectionPremise.PREMISESIZE);
    String id = QueryUtil.getColumnName(premiseMd, CollectionPremise.ID);

    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "SUM(" + numberWithImmatures + ")/NULLIF(SUM(" + numberExamined + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "SUM(" + numberWithLarvae + ")/NULLIF(SUM(" + numberExamined + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "SUM(" + numberWithPupae + ")/NULLIF(SUM(" + numberExamined + "), 0.0)*100.0") || needsJoin;

    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "SUM(" + numberimmatures + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "SUM(" + numberlarvae + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "SUM(" + numberpupae + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;

    needsJoin = valueQuery.hasSelectableRef("bi_lp") || needsJoin;
    needsJoin = valueQuery.hasSelectableRef("bi_l") || needsJoin;
    needsJoin = valueQuery.hasSelectableRef("bi_p") || needsJoin;

    needsJoin = valueQuery.hasSelectableRef("pi") || needsJoin;
    needsJoin = valueQuery.hasSelectableRef("pppr") || needsJoin;
    needsJoin = valueQuery.hasSelectableRef("ppha") || needsJoin;
    needsJoin = valueQuery.hasSelectableRef("pppe") || needsJoin;

//    if (valueQuery.hasSelectableRef("container_term"))
//    {
//      needsJoin = true;
//    }

    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "SUM(" + numberimmatures + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;
    // Percentage of water-holding containers with larvae by container type:
    // Number with larvae/Number with water*100

    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "SUM(" + numberlarvae + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "SUM(" + numberpupae + ")/NULLIF(SUM(" + numberwithwater + "), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", "(SUM(" + numberlarvaecollected + ")+SUM(" + numberpupaecollected + "))/" +
    		"NULLIF((SUM(SUM(" + numberlarvaecollected + ")) OVER ()) + (SUM(SUM(" + numberpupaecollected + ")) OVER ()), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "SUM(" + numberlarvaecollected + ")/NULLIF(SUM(SUM(" + numberlarvaecollected + ")) OVER (), 0.0)*100.0") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "SUM(" + numberpupaecollected + ")/NULLIF(SUM(SUM(" + numberpupaecollected + ")) OVER (), 0.0)*100.0") || needsJoin;

    if (needsJoin)
    {
      if (collectionPremiseQuery == null)
      {
        collectionPremiseQuery = new CollectionPremiseQuery(valueQuery);
        valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
      }

      if (premiseTaxonQuery == null)
      {
        premiseTaxonQuery = new PremiseTaxonQuery(valueQuery);
        valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
      }

      if (collectionContainerQuery == null)
      {
        collectionContainerQuery = new CollectionContainerQuery(valueQuery);
        valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
      }
      
      
      // we need to deuplicate the sum so the same collection is not counted
      // twice
      String numberExaminedSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT " + collectionPremiseQuery.getTableAlias() + "." + id + " || '~' || " + numberExamined + "))";
      String numberSizeSum =     "sum_stringified_id_int_pairs(array_agg(DISTINCT " + collectionPremiseQuery.getTableAlias() + "." + id + " || '~' || " + premiseSize + "))";
      String numberInhabitantsSum = "sum_stringified_id_int_pairs(array_agg(DISTINCT " + collectionPremiseQuery.getTableAlias() + "." + id + " || '~' || " + numberInhabitants + "))";


      QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "SUM(" + numberimmatures + ")/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
      QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "SUM(" + numberlarvae + ")/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
      QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "SUM(" + numberpupae + ")/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");

      QueryUtil.setSelectabeSQL(valueQuery, "pi", "SUM(" + numberpupaecollected + ")/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
      QueryUtil.setSelectabeSQL(valueQuery, "ppha", "SUM(" + numberpupaecollected + ")/NULLIF(" + numberSizeSum + ", 0.0)");
      QueryUtil.setSelectabeSQL(valueQuery, "pppr", "SUM(" + numberpupaecollected + ")/NULLIF(" + numberExaminedSum + ", 0.0)");
      
      // this calculation only valid for premises with data for inhabitants
      if (QueryUtil.setSelectabeSQL(valueQuery, "pppe", "SUM(" + numberpupaecollected + ")/NULLIF(" + numberInhabitantsSum + ", 0.0)"))
      {
        valueQuery.WHERE(collectionPremiseQuery.getNumberInhabitants().NE(""));
      }
    }
//
//    if (valueQuery.hasSelectableRef("container_term"))
//    {
//      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
//      String idCol = QueryUtil.getIdColumn();
//      String sql = "SELECT " + Term.NAME + " as " + "container_term" + "_displayLabel FROM " + termTable + " tt WHERE tt." + idCol + " = " + collectionContainerQuery.getTableAlias() + "." + RelationshipDAOIF.CHILD_ID_COLUMN;
//      QueryUtil.setSelectabeSQL(valueQuery, "container_term", sql);
//    }

    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());
  }
}
