/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedTableClassQuery;
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
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ImmatureContainerCollectionQB extends AbstractQB implements Reloadable
{

  public ImmatureContainerCollectionQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);
  }

  @Override
  protected String getAuditClassAlias()
  {
    return ImmatureCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    ImmatureCollectionQuery collectionQuery = (ImmatureCollectionQuery) queryMap.get(ImmatureCollection.CLASS);
    CollectionPremiseQuery collectionPremiseQuery = (CollectionPremiseQuery) queryMap.get(CollectionPremise.CLASS);
    PremiseTaxonQuery premiseTaxonQuery = (PremiseTaxonQuery) queryMap.get(PremiseTaxon.CLASS);
    CollectionContainerQuery collectionContainerQuery = (CollectionContainerQuery) queryMap.get(CollectionContainer.CLASS);

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
      QueryUtil.joinTermAllpaths(valueQuery, CollectionPremise.CLASS, collectionPremiseQuery, this.getTermRestrictions(), this.getLayer());
      valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
    }

    if (premiseTaxonQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PremiseTaxon.CLASS, premiseTaxonQuery, this.getTermRestrictions(), this.getLayer());
      valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
    }

    if (collectionContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionContainer.CLASS, collectionContainerQuery, this.getTermRestrictions(), this.getLayer());
      valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
    }

    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(CollectionPremise.CLASS);
    String idCol = QueryUtil.getIdColumn();

    String numberExamined = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBEREXAMINED);
    String numberInhabitants = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERINHABITANTS);
    String premiseSize = QueryUtil.getColumnName(premiseMd, CollectionPremise.PREMISESIZE);
    String numberWithImmatures = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHIMMATURES);
    String numberWithLarvae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHLARVAE);
    String numberWithPupae = QueryUtil.getColumnName(premiseMd, CollectionPremise.NUMBERWITHPUPAE);

    // The aliases are the same as the column name
    String[] aliases = { numberExamined, numberWithImmatures, numberWithLarvae, numberWithPupae, numberInhabitants, premiseSize };
    this.setAttributesAsAggregated(aliases, idCol, valueQuery, collectionPremiseQuery.getTableAlias(), false);

    this.addGeoDisplayLabelQuery(collectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    MdEntityDAOIF containerMd = MdEntityDAO.getMdEntityDAO(CollectionContainer.CLASS);

    String numberwithwater = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERWITHWATER);
    String numberwithwaterSum = this.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberwithwater);

    String numberimmatures = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERIMMATURES);
    String numberimmaturesSum = this.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberimmatures);

    String numberlarvae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERLARVAE);
    String numberlarvaeSum = this.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberlarvae);

    String numberpupae = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERPUPAE);
    String numberpupaeSum = this.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberpupae);

    String numberlarvaecollected = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERLARVAECOLLECTED);

    String numberpupaecollected = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERPUPAECOLLECTED);
    String numberpupaecollectedSum = this.sumColumnForId(collectionContainerQuery.getTableAlias(), idCol, null, numberpupaecollected);

    String numberWithImmaturesSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, numberWithImmatures);

    String numberWithLarvaeSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, numberWithLarvae);

    String numberWithPupaeSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, numberWithPupae);

    String numberExaminedSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, numberExamined);
    String numberSizeSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, premiseSize);
    String numberInhabitantsSum = this.sumColumnForId(collectionPremiseQuery.getTableAlias(), idCol, null, numberInhabitants);

    QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "" + numberWithImmaturesSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "" + numberWithLarvaeSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "" + numberWithPupaeSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "" + numberimmaturesSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "" + numberlarvaeSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "" + numberpupaeSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "" + numberimmaturesSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "" + numberlarvaeSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "" + numberpupaeSum + "::float/NULLIF(" + numberwithwaterSum + ", 0.0)*100.0");

    String s = "(SUM(coalesce(" + numberlarvaecollected + ",0.0))\n" + "+\n" + "SUM(coalesce(" + numberpupaecollected + ",0.0)))\n" + "/\n" + "NULLIF(" + "SUM(SUM(coalesce(" + numberlarvaecollected + ",0.0))) OVER ()\n" + "+ " + "SUM(SUM(coalesce(" + numberpupaecollected + ",0.0))) OVER ()\n" + ",0.0)*100.0\n";
    QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", s);

    QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "SUM(" + numberlarvaecollected + ")/NULLIF(SUM(SUM(" + numberlarvaecollected + ")) OVER (), 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "SUM(" + numberpupaecollected + ")/NULLIF(SUM(SUM(" + numberpupaecollected + ")) OVER (), 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "" + numberimmaturesSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "" + numberlarvaeSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "" + numberpupaeSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");

    QueryUtil.setSelectabeSQL(valueQuery, "pi", "" + numberpupaecollectedSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)*100.0");
    QueryUtil.setSelectabeSQL(valueQuery, "ppha", "" + numberpupaecollectedSum + "::float/NULLIF(" + numberSizeSum + ", 0.0)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppr", "" + numberpupaecollectedSum + "::float/NULLIF(" + numberExaminedSum + ", 0.0)");

    // this calculation only valid for premises with data for inhabitants
    if (QueryUtil.setSelectabeSQL(valueQuery, "pppe", "" + numberpupaecollectedSum + "::float/NULLIF(" + numberInhabitantsSum + ", 0.0)"))
    {
      valueQuery.WHERE(collectionPremiseQuery.getNumberInhabitants().NE(""));
    }

    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate(), collectionQuery.getDisease());
  }

}
