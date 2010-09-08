package dss.vector.solutions.entomology;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AVG;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.MIN;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class ImmatureCollection extends ImmatureCollectionBase implements
    com.runwaysdk.generation.loader.Reloadable
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
    this.validateEndDate();
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  @Override
  public void validateStartDate()
  {
    Date start = this.getStartDate();

    if (start != null && start.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(start);
      p.setCurrentDate(new Date());
      p.setNotification(this, STARTDATE);
      p.apply();
      p.throwIt();
    }

    Date end = this.getEndDate();
    if (start != null && end != null)
    {
      if (start.after(this.getEndDate()))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  public void validateEndDate()
  {
    Date end = this.getEndDate();

    if (end != null && end.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(end);
      p.setCurrentDate(new Date());
      p.setNotification(this, ENDDATE);
      p.apply();
      p.throwIt();
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
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory,
        valueQuery, xml, queryConfig, layer);

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
    setCollectionAttribsAsCalculations(aliases, idCol, valueQuery, collectionPremiseQuery);    

    QueryUtil.joinGeoDisplayLabels(valueQuery, ImmatureCollection.CLASS, collectionQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);


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
        collectionQuery.getEndDate());
  }
  
  public static void setCollectionAttribsAsCalculations(String[] aliases, String id, ValueQuery valueQuery, CollectionPremiseQuery premiseQuery)
  {
    Map<String, Selectable> override = new HashMap<String, Selectable>();
    
    for(String alias : aliases)
    {
      if(valueQuery.hasSelectableRef(alias))
      {
        Selectable sel = valueQuery.getSelectableRef(alias);
        String sql;
        if(sel instanceof SUM)
        {
          sql = QueryUtil.sumColumnForId(premiseQuery.getTableAlias(), id, null, alias);
        }
        if(sel instanceof AVG)
        {
          sql = QueryUtil.avgColumnForId(premiseQuery.getTableAlias(), id, null, alias);
        }
        else if(sel instanceof MIN)
        {
          sql = QueryUtil.minColumnForId(premiseQuery.getTableAlias(), id, null, alias);
        }
        else if(sel instanceof MAX)
        {
          sql = QueryUtil.maxColumnForId(premiseQuery.getTableAlias(), id, null, alias);
        }
        else
        {
          // We have to SUM by default to avoid a cross-product
          sql = QueryUtil.sumColumnForId(premiseQuery.getTableAlias(), id, null, alias);
        }
        
        SelectableSQL newSel = valueQuery.aSQLAggregateFloat(alias, sql, alias);
        override.put(alias, newSel);
      }
    }
    
    // Reset the ValueQuery selectables since it is not possible to reset only one at a time
    if(override.size() > 0)
    {
      List<Selectable> all = valueQuery.getSelectableRefs();
      List<Selectable> reAdd = new LinkedList<Selectable>();
      for(Selectable sel : all)
      {
        if(override.containsKey(sel.getUserDefinedAlias()))
        {
          reAdd.add(override.get(sel.getUserDefinedAlias()));
        }
        else
        {
          reAdd.add(sel);
        }
      }
      
      valueQuery.clearSelectClause();
      valueQuery.SELECT(reAdd.toArray(new Selectable[reAdd.size()]));
    }
  }
}
