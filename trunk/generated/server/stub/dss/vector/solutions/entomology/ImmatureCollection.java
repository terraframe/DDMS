package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstance;
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

//    valueQuery.WHERE(larvacideAssQuery.parentId().EQ(larvacideQuery.getId()));


    QueryUtil.joinGeoDisplayLabels(valueQuery, Larvacide.CLASS, collectionQuery );

    QueryUtil.joinTermAllpaths(valueQuery, LarvacideInstance.CLASS, collectionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    SelectableMoment dateAttribute = collectionQuery.getStartDate();

    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, dateAttribute);

  }
}
