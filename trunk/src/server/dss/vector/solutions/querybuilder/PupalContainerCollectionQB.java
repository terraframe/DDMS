package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.InnerJoinEq;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.entomology.PupalCollection;
import dss.vector.solutions.entomology.PupalCollectionQuery;
import dss.vector.solutions.entomology.PupalContainer;
import dss.vector.solutions.entomology.PupalContainerAmount;
import dss.vector.solutions.entomology.PupalContainerQuery;
import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.entomology.PupalPremise;
import dss.vector.solutions.entomology.PupalPremiseQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class PupalContainerCollectionQB extends AbstractQB implements Reloadable
{

  public PupalContainerCollectionQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageNumber, pageNumber);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return PupalCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    PupalCollectionQuery collectionQuery = ( PupalCollectionQuery  ) queryMap.get(PupalCollection.CLASS);
    PupalPremiseQuery premiseQuery = ( PupalPremiseQuery  ) queryMap.get(PupalPremise.CLASS);
    PupalContainerQuery pupalContainerQuery = ( PupalContainerQuery  ) queryMap.get(PupalContainer.CLASS);
 
    MdEntityDAOIF premiseMd = MdEntityDAO.getMdEntityDAO(PupalPremise.CLASS);
    MdEntityDAOIF ammountMd = MdEntityDAO.getMdEntityDAO(PupalContainerAmount.CLASS);
    
    if(premiseQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PupalPremise.CLASS, premiseQuery, this.getTermRestrictions() );
      valueQuery.WHERE(premiseQuery.getCollection().EQ(collectionQuery));
    }
    
    this.addGeoDisplayLabelQuery(collectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);
      
    if(pupalContainerQuery == null)
    {
      pupalContainerQuery = new PupalContainerQuery(valueQuery);
    }
    
    if(pupalContainerQuery != null)
    {
      QueryUtil.joinTermAllpaths(valueQuery, PupalContainer.CLASS, pupalContainerQuery, this.getTermRestrictions() );
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
    this.setAttributesAsAggregated(aliases, id, valueQuery, premiseQuery.getTableAlias(), false);
    
    boolean needsJoin = false; 
    boolean needsView = 
      QueryUtil.getSingleAttribteGridSql(valueQuery, pupalContainerQuery.getTableAlias());

    String amount = QueryUtil.getColumnName(ammountMd, PupalContainerAmount.AMOUNT);
    String child_id = RelationshipDAOIF.CHILD_ID_COLUMN; //QueryUtil.getColumnName(ammountMd, PupalContainerAmount.);
    String parent_id = RelationshipDAOIF.PARENT_ID_COLUMN;//QueryUtil.getColumnName(ammountMd, PupalContainerAmount.ID);
    String taxonAmountsView = "taxonAmmmountsView";      
    String pupalContainerTable = MdBusiness.getMdBusiness(PupalContainer.CLASS).getTableName();
    String pupalContainerAmmountTable = MdBusiness.getMdEntity(PupalContainerAmount.CLASS).getTableName();
    
    String numberExaminedSum = this.sumColumnForId(premiseQuery.getTableAlias(), id, null, numberExamined);
    String numberSizeSum =     this.sumColumnForId(premiseQuery.getTableAlias(), id, null, premiseSize);
    String numberInhabitantsSum = this.sumColumnForId(premiseQuery.getTableAlias(), id, null, numberInhabitants);  
    
    String taxonSql = "SELECT pc."+id+" ";
    for (Term taxon : TermRootCache.getRoots(PupalContainerView.getPupaeAmountMd()))
    {
//      String moID = taxon.getTermId().replace(":", "");
      String moID = QueryUtil.aliasTerms(taxon);
      
      String taxonAmmountCol = "_"+moID+"_amt";
      
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
//      valueQuery.setSqlPrefix("WITH "+taxonAmountsView+" AS (" + taxonSql + ")");
      this.addWITHEntry(new WITHEntry(taxonAmountsView, taxonSql));
      valueQuery.AND(new InnerJoinEq("id", pupalContainerTable, pupalContainerQuery.getTableAlias(), "id", taxonAmountsView, taxonAmountsView));
    }
    
//    valueQuery.HAVING( F.COUNT(collectionQuery.getId()).GT(0));
    
    return QueryUtil.setQueryDates(xml, valueQuery, collectionQuery, collectionQuery.getStartDate(), collectionQuery.getEndDate(), collectionQuery.getDisease());
  }

}
