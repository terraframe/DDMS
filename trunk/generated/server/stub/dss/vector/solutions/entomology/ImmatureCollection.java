package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedBusinessQuery;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.ontology.Term;
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
      
      if(collectionPremiseQuery == null)
      {
        collectionPremiseQuery = new CollectionPremiseQuery(queryFactory);
        valueQuery.WHERE(collectionPremiseQuery.getCollection().EQ(collectionQuery));
      }
      
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
    number_containers=Containers
    number_destroyed=Destroyed
    number_immatures=Immatures
    number_larvae=Larvae
    number_larvae_collected=Larvae Collected
    number_pupae=Pupae
    number_pupae_collected=Pupae Collected
    number_with_larvicide=Larvacide
    number_with_water=Water
    container_type=Container Type
    */
    
    
    MdEntityDAOIF containerMd = MdEntityDAO.getMdEntityDAO(CollectionContainer.CLASS);
    String numberContainers = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERCONTAINERS);
    needsJoin = valueQuery.hasSelectableRef(numberContainers) || needsJoin;
    
    String numberdestroyed = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERDESTROYED);
    needsJoin = valueQuery.hasSelectableRef(numberdestroyed) || needsJoin;
    
    String numberwithlarvicide  = QueryUtil.getColumnName(containerMd, CollectionContainer.NUMBERWITHLARVICIDE);
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
    String premiseSize = QueryUtil.getColumnName(premiseMd, CollectionPremise.PREMISESIZE);
    
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "SUM("+numberimmatures+")/SUM("+numberWithImmatures+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "SUM("+numberlarvae+")/SUM("+numberWithImmatures+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "SUM("+numberpupae+")/SUM("+numberWithImmatures+")*100")|| needsJoin;
    
    QueryUtil.setSelectabeSQL(valueQuery, "ci_lp", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_l", "SUM("+numberlarvae+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "ci_p", "SUM("+numberpupae+")/SUM("+numberwithwater+")*100");
    
    QueryUtil.setSelectabeSQL(valueQuery, "bi_lp", "SUM("+numberimmatures+")/SUM("+numberExamined+"*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_l", "SUM("+numberlarvae+")/SUM("+numberExamined+"*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "bi_p", "SUM("+numberpupae+")/SUM("+numberExamined+"*100)");
    
    QueryUtil.setSelectabeSQL(valueQuery, "pi", "SUM("+numberpupae+")/SUM("+numberExamined+"*100)");
    QueryUtil.setSelectabeSQL(valueQuery, "pppr", "SUM("+numberpupae+")/SUM("+numberExamined+")");
    QueryUtil.setSelectabeSQL(valueQuery, "ppha", "SUM("+numberpupae+")/SUM("+premiseSize+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "pppe", "SUM("+numberpupae+")/SUM("+numberInhabitants+")*100");
    
    
    if(valueQuery.hasSelectableRef("container_term"))
    {
       needsJoin = true;
    }
    
    
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
    
    if(valueQuery.hasSelectableRef("container_term"))
    {
      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
      String idCol = QueryUtil.getIdColumn();
      String sql = "SELECT "+ Term.NAME + " as " + "container_term" + "_displayLabel FROM " + termTable + " tt WHERE tt."+idCol+" = " +collectionContainerQuery.getTableAlias()+"."+RelationshipDAOIF.CHILD_ID_COLUMN;
      //QueryUtil.leftJoinTermDisplayLabels(valueQuery, CollectionContainer.CLASS, collectionContainerQuery, collectionContainerQuery.childId().getColumnAlias());
      QueryUtil.setSelectabeSQL(valueQuery, "container_term", sql);
    }
    
    
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_immatures", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    //Percentage of water-holding containers with larvae by container type:
      //Number with larvae/Number with water*100

    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_larvae", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_water_holding_pupae", "SUM("+numberpupae+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_immature_contribution", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_larve_contribution", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution", "SUM("+numberimmatures+")/SUM("+numberwithwater+")*100");
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());

  }
}
