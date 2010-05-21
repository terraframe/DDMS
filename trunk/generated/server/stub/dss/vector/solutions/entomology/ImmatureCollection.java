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
    
    for(CollectionPremise premise : premises)
    {
      premise.deleteAll();
    }
    
    this.delete();
  }
  
  public boolean hasPremises()
  {
    return (this.getPremises().size() > 0);
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

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }
  
  @Override
  public void validateStartDate()
  {
    if(this.getStartDate() != null && this.getEndDate() != null)
    {
      if(this.getStartDate().after(this.getEndDate()))
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

    ImmatureCollectionQuery collectionQuery = ( ImmatureCollectionQuery  ) queryMap.get(ImmatureCollection.CLASS);
    CollectionPremiseQuery collectionPremiseQuery = ( CollectionPremiseQuery  ) queryMap.get(CollectionPremise.CLASS);
    PremiseTaxonQuery premiseTaxonQuery = ( PremiseTaxonQuery  ) queryMap.get(PremiseTaxon.CLASS);
    CollectionContainerQuery collectionContainerQuery = ( CollectionContainerQuery  ) queryMap.get(CollectionContainer.CLASS);
    if(collectionPremiseQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionPremise.CLASS, collectionPremiseQuery );
      valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
    }
    
    if(premiseTaxonQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PremiseTaxon.CLASS, premiseTaxonQuery );
      valueQuery.WHERE(premiseTaxonQuery.getPremise().EQ(collectionPremiseQuery));
    }

    if(collectionContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionContainer.CLASS, collectionContainerQuery );
      valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, ImmatureCollection.CLASS, collectionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    boolean needsJoin = false; 
    
    /*
    numbercontainers integer,
    numberwithwater integer,
    numberdestroyed integer,
    numberwithlarvicide integer,
    numberimmatures integer,
    numberlarvae integer,
    numberpupae integer,
    numberlarvaecollected integer,
    numberpupaecollected integer,
    */
    
    String numberExaminedCol = QueryUtil.getColumnName(collectionPremiseQuery.getMdClassIF(), CollectionPremise.NUMBEREXAMINED);
    
    MdEntityDAOIF collectionContainerMd = collectionContainerQuery.getMdClassIF();
    String numberImmaturesCol = QueryUtil.getColumnName(collectionContainerMd, CollectionContainer.NUMBERIMMATURES);
    String numberLarvaeCol = QueryUtil.getColumnName(collectionContainerMd, CollectionContainer.NUMBERLARVAE);
    String numberPupaeCol = QueryUtil.getColumnName(collectionContainerMd, CollectionContainer.NUMBERPUPAE);
    
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "SUM("+numberExaminedCol+")/SUM("+numberImmaturesCol+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "SUM("+numberExaminedCol+")/SUM("+numberLarvaeCol+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100")|| needsJoin;
    
    QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    
    QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "SUM("+numberExaminedCol+")/SUM("+numberPupaeCol+")*100");
    
    QueryUtil.setSelectabeSQL(valueQuery, "pi", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppr", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "ppha", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppe", "COUNT(*)");
    
    if(needsJoin)
    {
      //String tableName = premiseTaxonQuery.getMdClassIF().getTableName();
      //String tableAlias = premiseTaxonQuery.getTableAlias();
      
      if(collectionContainerQuery == null)
      {
        collectionContainerQuery = new CollectionContainerQuery(queryFactory);   
        valueQuery.WHERE(collectionContainerQuery.hasParent(premiseTaxonQuery));
      }
    }
    
    
    
    
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "COUNT(*)");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "COUNT(*)");
    
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());
    

  }
}
