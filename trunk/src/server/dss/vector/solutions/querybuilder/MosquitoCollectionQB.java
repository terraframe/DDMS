package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class MosquitoCollectionQB extends AbstractQB implements Reloadable
{
  public MosquitoCollectionQB(String xml, String config, Layer layer)
  {
    super(xml, config, layer);
  }
  
  @Override
  public ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    SubCollectionQuery subCollectionQuery = (SubCollectionQuery) queryMap.get(SubCollection.CLASS);

    if (subCollectionQuery != null)
    {
      valueQuery.WHERE(subCollectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, SubCollection.CLASS, subCollectionQuery);
    }

    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    
    
    if (valueQuery.hasSelectableRef("mosquitoCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("mosquitoCount");
      calc.setSQL("NULL");
    }
    if (valueQuery.hasSelectableRef("collectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("collectionCount");
      calc.setSQL("NULL");
    }
    if (valueQuery.hasSelectableRef("subCollectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("subCollectionCount");
      calc.setSQL("NULL");
    }
    
    
    
    if (xml.contains("abundance_subcol_1") || xml.contains("abundance_1") || xml.contains("mosquitoCount") || xml.contains("ollectionCount"))
    {

      String viewName = "abundance_view";

      setAbundance(valueQuery, 1, "1");
      setAbundance(valueQuery, 10, "10");
      setAbundance(valueQuery, 100, "100");
      setAbundance(valueQuery, 1000, "1000");

      valueQuery.WHERE(mosquitoCollectionQuery.getAbundance().EQ(true));

      String withQuery = getWithQuerySQL(viewName, valueQuery);

      ValueQuery overrideQuery = new ValueQuery(queryFactory);

      for (Selectable s : valueQuery.getSelectableRefs())
      {
        String attributeName = s.getDbColumnName();
        String columnAlias = s.getColumnAlias();
        String columnName = s.getColumnAlias();

        if (attributeName.equals("mosquitoCount"))
        {
          columnName = "abundance_sum";
        }
        if (attributeName.equals("collectionCount"))
        {
          columnName = "array_length(allCollectionIds,1)";
        }       
        if (attributeName.equals("subCollectionCount"))
        {
          columnName = "coalesce(array_length(allSubCollectionIds,1))";
        }

        
        if (attributeName.equals("abundance_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        if (attributeName.equals("abundance_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allCollectionIds,1))";
        }
        
 
        if (attributeName.equals("abundance_subcol_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }
        if (attributeName.equals("abundance_subcol_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allSubCollectionIds,1))";
        }

        if (s instanceof SelectableSQLFloat)
        {
          overrideQuery.SELECT(overrideQuery.aSQLFloat(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else if (s instanceof SelectableSQLInteger)
        {
          overrideQuery.SELECT(overrideQuery.aSQLInteger(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else if (s instanceof AttributeMoment)
        {
          overrideQuery.SELECT(overrideQuery.aSQLDate(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }
        else
        {
          overrideQuery.SELECT(overrideQuery.aSQLText(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }

      }

      overrideQuery.setSqlPrefix(withQuery);
      overrideQuery.FROM(viewName, viewName);
      return overrideQuery;

    }

    return valueQuery;
  }
  
  public void setAbundance(ValueQuery valueQuery, Integer multiplier, String sql)
  {
    String selectableName = "abundance_" + multiplier;
    if(valueQuery.hasSelectableRef(selectableName)) {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }
    
    selectableName = "abundance_subcol_" + multiplier;
    if(valueQuery.hasSelectableRef(selectableName)) {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }

  }

  
  public String getWithQuerySQL(String viewName, ValueQuery valueQuery)
  {

    String joinMainQuery = "";
    
    String areaGroup = "";
    
    for (Selectable s : valueQuery.getSelectableRefs())
    {
      if (s.getDbColumnName().startsWith("geoId_") || s.getDbColumnName().startsWith("collectionMethod") || s.getDbColumnName().startsWith("subCollectionId") || s.getDbColumnName().startsWith("DATEGROUP"))
      {
        joinMainQuery += "\n AND ss." + s.getColumnAlias() + " = mainQuery." + s.getColumnAlias() + " ";
        areaGroup += "||  mainQuery." + s.getColumnAlias() + " ";
      }

    }
    areaGroup = areaGroup.substring(2);

    String origQuery = valueQuery.getSQL();
    
    String collectionIdCol = QueryUtil.getColumnName(MosquitoCollection.getCollectionIdMd());
    String subCollectionId = QueryUtil.getColumnName(SubCollection.getSubCollectionIdMd());
    String taxonCol = QueryUtil.getColumnName(SubCollection.getTaxonMd());
    String totalCol = QueryUtil.getColumnName(SubCollection.getTotalMd());
    String parentTermCol = QueryUtil.getColumnName(AllPaths.getParentTermMd());
    String childTermCol = QueryUtil.getColumnName(AllPaths.getChildTermMd());
    
    String selectAddtions = taxonCol+",\n SUM("+totalCol+") as abundance_sum, \n array_agg("+collectionIdCol+") as collectionIds, \n array_agg(coalesce("+collectionIdCol+" || "+subCollectionId+", "+collectionIdCol+")) as subCollectionIds  \n,";

    origQuery = origQuery.replaceFirst("SELECT", "SELECT "+ selectAddtions).replaceFirst("GROUP BY", "GROUP BY "+taxonCol+",");

    String sql = "WITH RECURSIVE mainQuery AS \n";
    sql += "(" + origQuery + "),\n";
    
    //taxonCountQuery is where each node gets the total of its child species
    sql += "taxonCountQuery AS (\n";
    sql += "SELECT mainQuery.* ,";
    sql += "(SELECT SUM(ss.abundance_sum) FROM mainQuery as ss, allpaths_ontology ap ";
    //used to calcuate ratio
    sql += "WHERE ss."+taxonCol+" = "+childTermCol+" AND "+parentTermCol+" = mainQuery."+taxonCol+" AND ss."+taxonCol+" != mainQuery."+taxonCol+" " + joinMainQuery + " ) as total_of_children, \n";
    //list of collection ids in this group
    sql += "ARRAY(SELECT distinct unnest(collectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allCollectionIds, \n";
    //list of sub collection ids in this group
    sql += "ARRAY(SELECT distinct unnest(subCollectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allSubCollectionIds, \n";
    //used to order the recursive decent
    sql += "(SELECT COUNT(*) FROM mainQuery as ss, allpaths_ontology ap WHERE ss."+taxonCol+" = "+parentTermCol+"  AND "+childTermCol+" = mainQuery."+taxonCol+" AND ss.taxon != mainQuery."+taxonCol+" "+ joinMainQuery + " )as depth, ";
    //the parent specie of this row in this group, may skip levels
    sql += "(SELECT ss."+taxonCol+" as depth FROM mainQuery as ss, allpaths_ontology ap WHERE ss."+taxonCol+" = "+parentTermCol+"  AND "+childTermCol+" = mainQuery."+taxonCol+" AND ss."+taxonCol+" != mainQuery."+taxonCol+" " + joinMainQuery; 
    sql += " GROUP BY ss."+taxonCol+" ORDER BY COUNT(*) DESC LIMIT 1 )as parent,\n";
    
    sql += areaGroup + " AS areaGroup\n";
    sql += " FROM mainQuery),\n";
    sql += " \n";

    joinMainQuery = joinMainQuery.replace("mainQuery","taxonCountQuery");
    

    sql += " percent_view AS ( ";
    sql += "SELECT taxonCountQuery.*,";
    //  -- ((me+my_children)/sum(everyone_at_my_level+their children))
    sql += "((abundance_sum + coalesce(total_of_children,0)) / \n ";
    sql += "(SELECT SUM(coalesce(ss.total_of_children,0) + ss.abundance_sum) FROM taxonCountQuery AS ss WHERE ss.parent = taxonCountQuery.parent" + joinMainQuery + "   ))  as my_share \n";
    sql += "FROM taxonCountQuery \n";
    sql += "),";
    
    
    sql += " rollup_view AS ( \n";
    sql += " SELECT areagroup, "+taxonCol+", parent, depth , my_share , abundance_sum + coalesce(total_of_children,0) as final_abundance\n";
    sql += "     FROM percent_view\n";
    sql += "     WHERE depth = 0\n";
    sql += " UNION\n";
    sql += " SELECT child_v.areagroup, child_v."+taxonCol+", child_v.parent, child_v.depth ,child_v.my_share, \n";
    sql += "  parent_v.final_abundance * child_v.my_share \n";
    sql += " FROM rollup_view parent_v, percent_view child_v WHERE parent_v.taxon = child_v.parent AND parent_v.areagroup = child_v.areagroup \n";
    sql += " ),\n";
    sql += "\n";

    
    sql += " "+viewName+" AS (\n";
    sql += "SELECT pv.*, final_abundance\n";
    sql += "FROM percent_view pv join  rollup_view  rv on rv.areagroup = pv.areagroup AND  rv."+taxonCol+" = pv."+taxonCol+" \n";
    sql += " )\n";

    sql += " \n";
    

    return sql;
  }
}
