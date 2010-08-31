package com.runwaysdk.query;

import java.util.Map;
import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.Alias;
import dss.vector.solutions.irs.IRSQuery;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.util.QueryUtil;

public class IRSValueQuery extends ValueQuery implements Reloadable
{
  private GeoProxyValueQuery gpVQ;
  private IRSQuery query;
  
  public IRSValueQuery(QueryFactory queryFactory)
  {
    super(queryFactory);
    
    this.gpVQ = null;
    this.query = null;
    
  }
  
  public void LEFT_JOIN(GeoProxyValueQuery vq, IRSQuery query)
  {
    this.gpVQ = vq;
    this.query = query;    
  }
  
  protected StringBuffer buildFromClause(BuildSQLVisitor visitor, Set<Join> tableJoinSet, Map<String, String> fromTableMap)
  {
    StringBuffer str = super.buildFromClause(visitor, tableJoinSet, fromTableMap);
    
    if(this.gpVQ != null)
    {
     
      String idCol = QueryUtil.getIdColumn();
      String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());
      
      String leftTable = IRSQuery.SPRAY_VIEW;
      String leftAlias = query.getSprayViewAlias();
      String insecticideView = IRSQuery.INSECTICIDE_VIEW;
      
      // TODO refactor this to use more of the query API instead of string concatenation.
      // Push InsecticideView into a ValueQuery
      String start = (str.toString().trim().equals("FROM") ? "" : ",")+" \n";
      str.append(start);
      str.append(this.getFromTableMapInfoForQuery().size() > 0 ? ", " : "");
      str.append(leftTable + " " + leftAlias + " LEFT JOIN " +insecticideView 
          + " " + insecticideView + " ON " +leftAlias+"."+Alias.BRAND+" = "+leftAlias+"."+idCol + " AND \n"+
          insecticideView+"."+diseaseCol+" = "+leftAlias+"."+Alias.DISEASE + " AND \n");
     
      // restrict by dates
      str.append("(((("+leftAlias+"."+Alias.SPRAY_DATE+") >= ("+insecticideView+".start_date) \n");
      str.append("AND ("+leftAlias+"."+Alias.SPRAY_DATE+") <= ("+insecticideView+".end_date)) \n");
      str.append("AND ("+leftAlias+"."+Alias.SPRAY_DATE+") >= ("+insecticideView+".nozzleStart)) \n");
      str.append("AND ("+leftAlias+"."+Alias.SPRAY_DATE+") <= ("+insecticideView+".nozzleEnd)) \n");
      
      
      // Special case to include rows from the planned targets, which have no dates
      if (query.hasPlannedTargets())
      {
        str.append("OR "+leftAlias+"."+Alias.SPRAY_DATE+" IS NULL \n");
      }
      
      str.append("\n");
    }
    
    return str;
  }

}
