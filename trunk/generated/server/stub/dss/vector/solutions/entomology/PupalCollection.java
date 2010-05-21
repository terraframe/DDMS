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

public class PupalCollection extends PupalCollectionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1932493411;
  
  public PupalCollection()
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
    // DELETE ALL PupalPremise
    List<PupalPremise> premises = this.getPremises();
    
    for(PupalPremise premise : premises)
    {
      premise.delete();
    }
    
    this.delete();
  }
  
  public boolean hasPremises()
  {
    return (this.getPremises().size() > 0);
  }

  private List<PupalPremise> getPremises()
  {
    PupalPremiseQuery query = new PupalPremiseQuery(new QueryFactory());    
    query.WHERE(query.getCollection().EQ(this));
    
    OIterator<? extends PupalPremise> it = query.getIterator();
    
    try
    {
      List<? extends PupalPremise> premises = it.getAll();
      
      return new LinkedList<PupalPremise>(premises);
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
    if (this.getCollectionId() == null || this.getCollectionId().trim().length() == 0)
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }  
  
  public PupalCollectionView getView()
  {
    PupalCollectionView view = new PupalCollectionView();
    
    view.populateView(this, null);
    
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

    PupalCollectionQuery collectionQuery = ( PupalCollectionQuery  ) queryMap.get(PupalCollection.CLASS);
    PupalPremiseQuery premiseQuery = ( PupalPremiseQuery  ) queryMap.get(PupalPremise.CLASS);
    PupalContainerQuery collectionContainerQuery = ( PupalContainerQuery  ) queryMap.get(CollectionContainer.CLASS);
    if(premiseQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PupalPremise.CLASS, premiseQuery );
      valueQuery.WHERE(premiseQuery.getCollection().EQ(collectionQuery));
    }

    if(collectionContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, CollectionContainer.CLASS, collectionContainerQuery );
     //valueQuery.WHERE(collectionContainerQuery.hasParent(premiseQuery));
    }

    QueryUtil.joinGeoDisplayLabels(valueQuery, PupalCollection.CLASS, collectionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
        
    boolean needsJoin = false; 
    
    if(collectionContainerQuery == null)
    {
      collectionContainerQuery = new PupalContainerQuery(queryFactory);   
    }
    
    MdEntityDAOIF md = collectionContainerQuery.getMdClassIF();
    
    String numberContainers = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberwithwater = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberdestroyed = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberwithlarvicide  = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberimmatures = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberlarvae = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberpupae = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberlarvaecollected = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    String numberpupaecollected = QueryUtil.getColumnName(md, CollectionContainer.NUMBERCONTAINERS);
    
    
    
    String numberExamined = QueryUtil.getColumnName(md, CollectionPremise.NUMBEREXAMINED);
    String numberInhabitants = QueryUtil.getColumnName(md, CollectionPremise.NUMBERINHABITANTS);
    String numberWithImmatures = QueryUtil.getColumnName(md, CollectionPremise.NUMBERWITHIMMATURES);
    String numberWithLarvae = QueryUtil.getColumnName(md, CollectionPremise.NUMBERWITHLARVAE);
    String numberWithPupae = QueryUtil.getColumnName(md, CollectionPremise.NUMBERWITHPUPAE);
    String premiseSize = QueryUtil.getColumnName(md, CollectionPremise.PREMISESIZE);
    String premiseType = QueryUtil.getColumnName(md, CollectionPremise.PREMISETYPE);
    
    
    
    
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_lp", "SUM("+numberimmatures+")/SUM("+numberWithImmatures+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_l", "SUM("+numberlarvae+")/SUM("+numberWithImmatures+")*100") || needsJoin;
    needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "hi_p", "SUM("+numberpupae+")/SUM("+numberWithImmatures+")*100")|| needsJoin;
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, ImmatureCollection.STARTDATE, ImmatureCollection.ENDDATE);
    

  }

}
