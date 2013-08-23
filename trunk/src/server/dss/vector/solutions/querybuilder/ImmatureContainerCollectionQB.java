package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.CollectionContainer;
import dss.vector.solutions.entomology.CollectionContainerQuery;
import dss.vector.solutions.entomology.CollectionPremise;
import dss.vector.solutions.entomology.CollectionPremiseQuery;
import dss.vector.solutions.entomology.ImmatureCollection;
import dss.vector.solutions.entomology.ImmatureCollectionQuery;
import dss.vector.solutions.entomology.PremiseTaxon;
import dss.vector.solutions.entomology.PremiseTaxonQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ImmatureContainerCollectionQB extends AbstractQB implements Reloadable
{

  public ImmatureContainerCollectionQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return ImmatureCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ImmatureCollectionQuery collectionQuery = (ImmatureCollectionQuery) queryMap
        .get(ImmatureCollection.CLASS);
    CollectionPremiseQuery collectionPremiseQuery = (CollectionPremiseQuery) queryMap
        .get(CollectionPremise.CLASS);
    PremiseTaxonQuery premiseTaxonQuery = (PremiseTaxonQuery) queryMap.get(PremiseTaxon.CLASS);
    CollectionContainerQuery collectionContainerQuery = (CollectionContainerQuery) queryMap
        .get(CollectionContainer.CLASS);

    if (collectionPremiseQuery == null)
    {
      collectionPremiseQuery = new CollectionPremiseQuery(valueQuery);
    }

    if (premiseTaxonQuery == null)
    {
      premiseTaxonQuery = new PremiseTaxonQuery(valueQuery);
    }

    if (collectionContainerQuery == null)
    {
      collectionContainerQuery = new CollectionContainerQuery(valueQuery);
    }

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
    
    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(CollectionPremise.CLASS);
    String idCol = QueryUtil.getIdColumn();

    String numberExamined = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBEREXAMINED);
    String numberInhabitants = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERINHABITANTS);
    String premiseSize = QueryUtil.getColumnName(premiseMd, CollectionPremise.PREMISESIZE);    
    String numberWithImmatures = QueryUtil.getColumnName(premiseMd,
        CollectionPremise.NUMBERWITHIMMATURES);
    String numberWithLarvae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHLARVAE);
    String numberWithPupae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHPUPAE);
    
    // The aliases are the same as the column name
    String[] aliases = {numberExamined, numberWithImmatures, numberWithLarvae, numberWithPupae, numberInhabitants, premiseSize};
    QueryUtil.setAttributesAsAggregated(aliases, idCol, valueQuery, collectionPremiseQuery.getTableAlias(), false);    

    this.addGeoDisplayLabelQuery(collectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);


    MdEntityDAOIF containerMd = MdEntityDAO.getMdEntityDAO(CollectionContainer.CLASS);

    String numberwithwater = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERWITHWATER);
    String numberwithwaterSum = QueryUtil.sumColumnForId(collectionContainerQuery.getTableAlias(),
        idCol, null, numberwithwater);

    String numberimmatures = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERIMMATURES);
    String numberimmaturesSum =QueryUtil.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberimmatures);

    String numberlarvae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERLARVAE);
    String numberlarvaeSum = QueryUtil.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberlarvae);

    String numberpupae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERPUPAE);
    String numberpupaeSum = QueryUtil.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberpupae);

    String numberlarvaecollected = QueryUtil.getColumnName(containerMd,
        CollectionContainer.NUMBERLARVAECOLLECTED);

    String numberpupaecollected = QueryUtil.getColumnName(containerMd,
        CollectionContainer.NUMBERPUPAECOLLECTED);
    String numberpupaecollectedSum = QueryUtil.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null,
        numberpupaecollected);


    String numberWithImmaturesSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(),
        idCol, null, numberWithImmatures);


    String numberWithLarvaeSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol,
        null, numberWithLarvae);

    String numberWithPupaeSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol,
        null, numberWithPupae);


    String numberExaminedSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol,
        null, numberExamined);
    String numberSizeSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null,
        premiseSize);
    String numberInhabitantsSum = QueryUtil.sumColumnForId(collectionPremiseQuery.getTableAlias(),
        idCol, null, numberInhabitants);

    QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "" + numberWithImmaturesSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "" + numberWithLarvaeSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "" + numberWithPupaeSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "" + numberimmaturesSum + "::float/NULLIF("
        + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "" + numberlarvaeSum + "::float/NULLIF("
        + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "" + numberpupaeSum + "::float/NULLIF("
        + numberwithwaterSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "" + numberimmaturesSum
        + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "" + numberlarvaeSum
        + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "" + numberpupaeSum
        + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");

    String s = "(SUM(coalesce(" + numberlarvaecollected + ",0.0))\n" + "+\n" + "SUM(coalesce("
        + numberpupaecollected + ",0.0)))\n" + "/\n" + "NULLIF(" + "SUM(SUM(coalesce("
        + numberlarvaecollected + ",0.0))) OVER ()\n" + "+ " + "SUM(SUM(coalesce("
        + numberpupaecollected + ",0.0))) OVER ()\n" + ",0.0)*100.0\n";
    QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", s);

    QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "SUM(" + numberlarvaecollected
        + ")/NULLIF(SUM(SUM(" + numberlarvaecollected + ")) OVER (), 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "SUM(" + numberpupaecollected
        + ")/NULLIF(SUM(SUM(" + numberpupaecollected + ")) OVER (), 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "" + numberimmaturesSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "" + numberlarvaeSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "" + numberpupaeSum + "::float/NULLIF(" + numberExaminedSum
        + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "pi", "" + numberpupaecollectedSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ppha", "" + numberpupaecollectedSum + "::float/NULLIF("
        + numberSizeSum + ", 0.0)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppr", "" + numberpupaecollectedSum + "::float/NULLIF("
        + numberExaminedSum + ", 0.0)");

    // this calculation only valid for premises with data for inhabitants
    if (QueryUtil.setSelectabeSQL(valueQuery, "pppe", "" + numberpupaecollectedSum + "::float/NULLIF("
        + numberInhabitantsSum + ", 0.0)"))
    {
      valueQuery.WHERE(collectionPremiseQuery.getNumberInhabitants().NE(""));
    }

    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(),
        collectionQuery.getEndDate(), collectionQuery.getDisease());
  }

}
