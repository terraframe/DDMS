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
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AVG;
import com.runwaysdk.query.Function;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.MAX;
import com.runwaysdk.query.MIN;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.CurrentDateProblem;
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
  @Transaction
  public void apply()
  {
    this.populateCollectionId();
    this.validateStartDate();
    this.validateEndDate();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }
  
  @Override
  public void validateEndDate()
  {
    Date end = this.getEndDate();
    
    if (end!=null && end.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(end);
      p.setCurrentDate(new Date());
      p.setNotification(this, ENDDATE);
      p.apply();
      p.throwIt();
    }
  }
  
  @Override
  public void validateStartDate()
  {
    Date start = this.getStartDate();
    
    if (start!=null && start.after(new Date()))
    {
      CurrentDateProblem p = new CurrentDateProblem();
      p.setGivenDate(start);
      p.setCurrentDate(new Date());
      p.setNotification(this, STARTDATE);
      p.apply();
      p.throwIt();
    }

    Date end = this.getEndDate();
    if(start != null && end != null)
    {
      if(start.after(end))
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
 
    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(PupalPremise.CLASS);
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(PupalContainerAmount.CLASS);
    
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
      pupalContainerQuery = new PupalContainerQuery(valueQuery);
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
    
    String id = QueryUtil.getIdColumn();
    
    String numberExamined = QueryUtil.getColumnName(premiseMd, PupalPremise.NUMBEREXAMINED);
    String numberInhabitants = QueryUtil.getColumnName(premiseMd, PupalPremise.NUMBERINHABITANTS);
    String premiseSize = QueryUtil.getColumnName(premiseMd, PupalPremise.PREMISESIZE);    
    
    // The aliases are the same as the column name
    String[] aliases = {numberExamined, numberInhabitants, premiseSize};
    setCollectionAttribsAsCalculations(aliases, id, valueQuery, premiseQuery);
    
    boolean needsJoin = false; 
    boolean needsView = 
      QueryUtil.getSingleAttribteGridSql(valueQuery, pupalContainerQuery.getTableAlias(), 
          RelationshipDAOIF.CHILD_ID_COLUMN, RelationshipDAOIF.PARENT_ID_COLUMN);

    String amount = QueryUtil.getColumnName(ammountMd, PupalContainerAmount.AMOUNT);
    String child_id = RelationshipDAOIF.CHILD_ID_COLUMN; //QueryUtil.getColumnName(ammountMd, PupalContainerAmount.);
    String parent_id = RelationshipDAOIF.PARENT_ID_COLUMN;//QueryUtil.getColumnName(ammountMd, PupalContainerAmount.ID);
    String taxonAmountsView = "taxonAmmmountsView";      
    String pupalContainerTable = MdBusiness.getMdBusiness(PupalContainer.CLASS).getTableName();
    String pupalContainerAmmountTable = MdBusiness.getMdEntity(PupalContainerAmount.CLASS).getTableName();
    
    String numberExaminedSum = QueryUtil.sumColumnForId(premiseQuery.getTableAlias(), id, null, numberExamined);
    String numberSizeSum =     QueryUtil.sumColumnForId(premiseQuery.getTableAlias(), id, null, premiseSize);
    String numberInhabitantsSum = QueryUtil.sumColumnForId(premiseQuery.getTableAlias(), id, null, numberInhabitants);  
    
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
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_premise_by_taxon_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF("+numberExaminedSum+", 0.0)") || needsJoin;
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_hectare_by_taxon_term"+moID, "SUM("+taxonAmmountCol+")/NULLIF("+numberSizeSum+", 0.0)") || needsJoin;
      needsJoin = QueryUtil.setSelectabeSQL(valueQuery, "pupae_per_person_per_taxon_term"+moID, "SUM("+taxonAmmountCol+")/(NULLIF("+numberInhabitantsSum+", 0.0))") || needsJoin;
    }
    taxonSql +=" FROM "+pupalContainerTable+" AS pc";
    
    if(needsJoin || needsView)
    {
      valueQuery.setSqlPrefix("WITH "+taxonAmountsView+" AS (" + taxonSql + ")");
      valueQuery.AND(new InnerJoinEq("id", pupalContainerTable, pupalContainerQuery.getTableAlias(), "id", taxonAmountsView, taxonAmountsView));
    }
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate());
  }
  
  private static void setCollectionAttribsAsCalculations(String[] aliases, String id, ValueQuery valueQuery, PupalPremiseQuery premiseQuery)
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
