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
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeasonDateProblem;
import dss.vector.solutions.ontology.Term;
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
    PupalContainerQuery pupalContainerQuery = ( PupalContainerQuery  ) queryMap.get(PupalContainer.CLASS);
    
    if(premiseQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PupalPremise.CLASS, premiseQuery );
      valueQuery.WHERE(premiseQuery.getCollection().EQ(collectionQuery));
    }


    QueryUtil.joinGeoDisplayLabels(valueQuery, PupalCollection.CLASS, collectionQuery );

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
      
    if(pupalContainerQuery == null)
    {
      pupalContainerQuery = new PupalContainerQuery(queryFactory);
    }
    
    if(pupalContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PupalContainer.CLASS, pupalContainerQuery );
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, PupalContainer.CLASS, pupalContainerQuery );
      
      if(premiseQuery == null)
      {
        premiseQuery = new PupalPremiseQuery(valueQuery);
        valueQuery.WHERE(premiseQuery.getCollection().EQ(collectionQuery));
      }
      
      valueQuery.WHERE(pupalContainerQuery.getPremise().EQ(premiseQuery));
    }
    
    boolean needsView = 
      QueryUtil.getSingleAttribteGridSql(valueQuery, pupalContainerQuery.getTableAlias(), 
          RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);

    
       
    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(PupalPremise.CLASS);
    
    String numberExamined = QueryUtil.getColumnName(premiseMd, PupalPremise.NUMBEREXAMINED);
    String numberInhabitants = QueryUtil.getColumnName(premiseMd, PupalPremise.NUMBERINHABITANTS);
    String premiseSize = QueryUtil.getColumnName(premiseMd, PupalPremise.PREMISESIZE);
    
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(PupalContainerAmount.CLASS);
    
    String amount = QueryUtil.getColumnName(ammountMd, PupalContainerAmount.AMOUNT);
    String id = QueryUtil.getIdColumn();
    String child_id = RelationshipDAOIF.CHILD_ID_COLUMN; //QueryUtil.getColumnName(ammountMd, PupalContainerAmount.);
    String parent_id = RelationshipDAOIF.PARENT_ID_COLUMN;//QueryUtil.getColumnName(ammountMd, PupalContainerAmount.ID);
    String taxonAmountsView = "taxonAmmmountsView";      
    String pupalContainerTable = MdBusiness.getMdBusiness(PupalContainer.CLASS).getTableName();
    String pupalContainerAmmountTable = MdBusiness.getMdEntity(PupalContainerAmount.CLASS).getTableName();
    
    boolean needsJoin = false; 
    
    
    String taxonSql = "SELECT pc."+id+" ";
    for (Term taxon : Term.getRootChildren(PupalContainerView.getPupaeAmountMd()))
    {
      String moID = taxon.getTermId().replace(":", "");
      
      String taxonAmmountCol = moID+"_amt";
      
      taxonSql += ",(SELECT "+amount+" FROM "+pupalContainerAmmountTable+" pca WHERE "+child_id+" = '"+taxon.getId()+"'  AND "+parent_id+"  = pc."+id+"  ) AS "+taxonAmmountCol+" ";
           
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM(SUM("+taxonAmmountCol+")) OVER (), 0.0)*100.0") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution_by_type_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM(SUM("+taxonAmmountCol+")) OVER (), 0.0)*100.0") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution_by_lid_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM(SUM("+taxonAmmountCol+")) OVER (), 0.0)*100.0") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution_by_fill_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM(SUM("+taxonAmmountCol+")) OVER (), 0.0)*100.0") || needsView;
      needsView = QueryUtil.setSelectabeSQL(valueQuery, "percent_pupae_contribution_by_frequency_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM(SUM("+taxonAmmountCol+")) OVER (), 0.0)*100.0") || needsView;
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_premise_by_taxon_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM("+numberExamined+"), 0.0)*100.0") || needsJoin;
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_hectare_by_taxon_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF(SUM("+premiseSize+"), 0.0)*100.0") || needsJoin;
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_person_per_taxon_term"+moID, "SUM("+taxonAmmountCol+")/(NULLIF(SUM("+numberInhabitants+"), 0.0))*100.0") || needsJoin;
    }
    taxonSql +=" FROM "+pupalContainerTable+" AS pc";
    
    if(needsJoin || needsView)
    {
      valueQuery.setSqlPrefix("WITH "+taxonAmountsView+" AS (" + taxonSql + ")");
      valueQuery.AND(new InnerJoinEq("id", pupalContainerTable, pupalContainerQuery.getTableAlias(), "id", taxonAmountsView, taxonAmountsView));
    }
//    
//    if(needsJoin && premiseQuery == null)
//    {
//      premiseQuery = new PupalPremiseQuery(valueQuery);
//      valueQuery.WHERE(premiseQuery.getCollection().EQ(collectionQuery));
//    }
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());
  }
  
  static boolean getSelectabeTermRelationSQL(ValueQuery valueQuery, String ref, String sql)
  {
    if (valueQuery.hasSelectableRef(ref))
    {
      Selectable s = valueQuery.getSelectableRef(ref);

      while (s instanceof Function)
      {
        Function f = (Function) s;
        s = f.getSelectable();
      }
      
      ( (SelectableSQL) s ).setSQL(sql);
      return true;
    }
    return false;
  }

}
