package dss.vector.solutions.querybuilder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.util.QBInterceptor;
import dss.vector.solutions.util.QueryUtil;
import dss.vector.solutions.util.Restriction;

public class MosquitoCollectionQB extends AbstractQB implements Reloadable
{
  private boolean             hasAbundance;

  private boolean             forceUniversal;

  private String              universalClass;

  public static final String  GET_NEXT_TAXON_FUNCTION = "get_next_taxon";

  private static final String ABUNDANCE_VIEW          = "abundance_view";

  private Set<String>         abundanceCols;

  private Selectable          collectionMethod;

  private final String        geoIdColumn;
  
  private static final String ROUND_DISPLAY_LABEL = MosquitoCollection.COLLECTIONROUND+QueryUtil.DISPLAY_LABEL_SUFFIX;
  
  private static final String TYPE_DISPLAY_LABEL = MosquitoCollection.COLLECTIONTYPE+QueryUtil.DISPLAY_LABEL_SUFFIX;
  
  private boolean hasRound;
  
  private boolean hasType;
  
  private Restriction taxonRestriction;

  private static final String GEO_ID_COALESCE_ALIAS   = "geo_id_coalesce_alias";

  public MosquitoCollectionQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);

    this.universalClass = Country.CLASS;
    this.forceUniversal = false;
    this.hasAbundance = this.hasAbundanceCalc(xml);
    this.abundanceCols = new HashSet<String>();
    this.collectionMethod = null;

    this.geoIdColumn = QueryUtil.getColumnName(GeoEntity.getGeoIdMd());
  }

  /**
   * Hack check for abundance calculation within the query.
   * 
   * @return
   */
  private boolean hasAbundanceCalc(String xml)
  {
    return xml.contains("abundance_subcol_1") || xml.contains("abundance_1") || xml.contains("mosquitoCount") || xml.contains("ollectionCount");
  }

  @Override
  protected String getAuditClassAlias()
  {
    return MosquitoCollection.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    this.hasRound = valueQuery.hasSelectableRef("collectionRound");
    this.hasType = valueQuery.hasSelectableRef("collectionType");

    MosquitoCollectionQuery mosquitoCollectionQuery = (MosquitoCollectionQuery) queryMap.get(MosquitoCollection.CLASS);
    SubCollectionQuery subCollectionQuery = (SubCollectionQuery) queryMap.get(SubCollection.CLASS);
    InsecticideBrandQuery insecticideBrandQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
    
    // Taxon is a special case that is selected from the sub collection
    if (subCollectionQuery == null && valueQuery.hasSelectableRef("taxon"))
    {
      subCollectionQuery = new SubCollectionQuery(valueQuery);
      queryMap.put(SubCollection.CLASS, subCollectionQuery);
    }
    
    // Species term restrictions must happen at the end, which we do in setTermCriteria. Remove the restriction
    //  so that in QueryUtil.joinTermAllPaths it doesn't restrict it. (Ticket 3148)
    if (this.getTermRestrictions().containsKey("taxon"))
    {
      Map<String, Restriction> restrictions = this.getTermRestrictions();
      taxonRestriction = restrictions.get("taxon");
      restrictions.remove("taxon");
    }

    if (subCollectionQuery != null)
    {
      valueQuery.WHERE(subCollectionQuery.getCollection().EQ(mosquitoCollectionQuery.getId()));

      QueryUtil.joinTermAllpaths(valueQuery, SubCollection.CLASS, subCollectionQuery, this.getTermRestrictions());
    }
    
    if(insecticideBrandQuery != null)
    {
      valueQuery.WHERE(mosquitoCollectionQuery.getInsecticideBrand().EQ(insecticideBrandQuery));
      
      QueryUtil.joinEnumerationDisplayLabels(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery);
      QueryUtil.joinTermAllpaths(valueQuery, InsecticideBrand.CLASS, insecticideBrandQuery, this.getTermRestrictions());
    }

    this.addGeoDisplayLabelQuery(mosquitoCollectionQuery);

    QueryUtil.joinTermAllpaths(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery, this.getTermRestrictions());

    QueryUtil.joinEnumerationDisplayLabels(valueQuery, MosquitoCollection.CLASS, mosquitoCollectionQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    QueryUtil.setQueryDates(xml, valueQuery, queryConfig, queryMap, mosquitoCollectionQuery.getDisease());

    if (valueQuery.hasSelectableRef("mosquitoCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("mosquitoCount");
      calc.setSQL("NULL::integer");
    }
    if (valueQuery.hasSelectableRef("collectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("collectionCount");
      calc.setSQL("NULL::integer");
    }
    if (valueQuery.hasSelectableRef("subCollectionCount"))
    {
      SelectableSQLInteger calc = (SelectableSQLInteger) valueQuery.getSelectableRef("subCollectionCount");
      calc.setSQL("NULL::integer");
    }

    if (valueQuery.hasSelectableRef(QueryConstants.PERCENT_PAROUS))
    {
      Selectable sel = valueQuery.getSelectableRef(QueryConstants.PERCENT_PAROUS);
      SelectableSQLDouble overall = (SelectableSQLDouble) sel;

      String parous = subCollectionQuery.getTableAlias() + "." + QueryUtil.getColumnName(SubCollection.getParousMd());
      String disected = subCollectionQuery.getTableAlias() + "." + QueryUtil.getColumnName(SubCollection.getDisectedMd());

      String sql = "(SUM(" + parous + ") / NULLIF(SUM(" + disected + "),0)::double precision * 100)";

      overall.setSQL(sql);
    }

    if (this.hasAbundance)
    {
      setAbundance(valueQuery, 1, "1");
      setAbundance(valueQuery, 10, "10");
      setAbundance(valueQuery, 100, "100");
      setAbundance(valueQuery, 1000, "1000");

//      this.collectionMethod = mosquitoCollectionQuery.getCollectionMethod(MosquitoCollection.COLLECTIONMETHOD);
//      valueQuery.SELECT(collectionMethod);

      valueQuery.WHERE(mosquitoCollectionQuery.getAbundance().EQ(true));

      super.joinGeoDisplayLabels(valueQuery);
      
      setWithQuerySQL(ABUNDANCE_VIEW, valueQuery);

      ValueQuery overrideQuery = new ValueQuery(queryFactory);

      boolean hasSubCol = false;
      boolean hasCol = false;
      
      for (Selectable s : valueQuery.getSelectableRefs())
      {
        String attributeName = s.getDbColumnName();
        String columnAlias = s.getColumnAlias();
        String columnName = s.getColumnAlias();

        if (attributeName.equals("mosquitoCount"))
        {
          columnName = "CAST(final_abundance AS int)";
        }
        if (attributeName.equals("collectionCount"))
        {
          columnName = "array_length(allCollectionIds,1)";
          hasCol = true;
        }
        if (attributeName.equals("subCollectionCount"))
        {
          columnName = "coalesce(array_length(allSubCollectionIds,1))";
          hasSubCol = true;
        }

        if (attributeName.equals("abundance_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allCollectionIds,1))";
          hasCol = true;
        }
        if (attributeName.equals("abundance_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allCollectionIds,1))";
          hasCol = true;
        }
        if (attributeName.equals("abundance_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allCollectionIds,1))";
          hasCol = true;
        }
        if (attributeName.equals("abundance_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allCollectionIds,1))";
          hasCol = true;
        }

        if (attributeName.equals("abundance_subcol_1"))
        {
          columnName = "1.0*(final_abundance/array_length(allSubCollectionIds,1))";
          hasSubCol = true;
        }
        if (attributeName.equals("abundance_subcol_10"))
        {
          columnName = "10.0*(final_abundance/array_length(allSubCollectionIds,1))";
          hasSubCol = true;
        }
        if (attributeName.equals("abundance_subcol_100"))
        {
          columnName = "100.0*(final_abundance/array_length(allSubCollectionIds,1))";
          hasSubCol = true;
        }
        if (attributeName.equals("abundance_subcol_1000"))
        {
          columnName = "1000.0*(final_abundance/array_length(allSubCollectionIds,1))";
          hasSubCol = true;
        }
        
        SelectableSingle sel = null;
        if (s instanceof SelectableSQLFloat)
        {
          sel = overrideQuery.aSQLFloat(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
        }
        else if (s instanceof SelectableSQLInteger)
        {
          sel = overrideQuery.aSQLInteger(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
        }
        else if (s instanceof AttributeMoment)
        {
          sel = overrideQuery.aSQLDate(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
        }
        else if (s instanceof SelectableSQLCharacter && (((SelectableSQLCharacter)s).getResultAttributeName().matches("dss_vector_solutions_.*_geoEntity_.*_geoId") || ((SelectableSQLCharacter)s).getResultAttributeName().matches("dss_vector_solutions_.*_geoEntity_.*_entityLabel") ) )
        {
          sel = overrideQuery.aSQLCharacter(s._getAttributeName(), columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
          ((SelectableSQLCharacter)sel).generateColumnAlias();
        }
        else if (!s.getUserDefinedAlias().equals(GEO_ID_COALESCE_ALIAS))
        {
          sel = overrideQuery.aSQLText(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
        }
        
        if (sel != null)
        {
          overrideQuery.SELECT(sel);
//          overrideQuery.GROUP_BY(sel);
        }
      }
      
//      overrideQuery.GROUP_BY(overrideQuery.aSQLDecimal("final_abundance", "final_abundance"));
      if (hasCol)
      {
//        overrideQuery.GROUP_BY(overrideQuery.aSQLDecimal("allCollectionIds", "allCollectionIds"));
      }
      if (hasSubCol)
      {
//        overrideQuery.GROUP_BY(overrideQuery.aSQLDecimal("allSubCollectionIds", "allSubCollectionIds"));
      }
      
      overrideQuery.FROM(ABUNDANCE_VIEW, ABUNDANCE_VIEW);
      
      return overrideQuery;
    }

    return valueQuery;
  }
  
  protected void joinGeoDisplayLabels(ValueQuery valueQuery)
  {
    if (this.hasAbundance)
    {
      return;
    }
    super.joinGeoDisplayLabels(valueQuery);
  }

  private void setAbundance(ValueQuery valueQuery, Integer multiplier, String sql)
  {
    String selectableName = "abundance_" + multiplier;
    if (valueQuery.hasSelectableRef(selectableName))
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }

    selectableName = "abundance_subcol_" + multiplier;
    if (valueQuery.hasSelectableRef(selectableName))
    {
      SelectableSQLFloat calc = (SelectableSQLFloat) valueQuery.getSelectableRef(selectableName);
      calc.setSQL(sql);
    }

  }

  @Override
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap, QBInterceptor interceptor)
  {
    if (interceptor == null)
    {
      return;
    }

    if (this.hasAbundance)
    {
      for (String entityAlias : queryMap.keySet())
      {
        ClassAttributePair pair = this.extractClassAndAttributeFromAlias(entityAlias);
        if (pair != null)
        {
          if (pair.getAttribute().equals(MosquitoCollection.COLLECTIONMETHOD))
          {
            continue;
          }
          else if (pair.getAttribute().equals("taxon_displayLabel"))
          {
            // #3002 - Apply filtering to the final query
            if(taxonRestriction != null)
            {
              SelectableChar taxon = valueQuery.aSQLCharacter(ABUNDANCE_VIEW+".taxon", ABUNDANCE_VIEW+".taxon");
              List<String> idsList = taxonRestriction.getRestrictions();
              
              valueQuery.WHERE(taxon.IN(idsList.toArray(new String[idsList.size()])));
            }
          }
        }
      }
    }
    else
    {
      super.setTermCriteria(valueQuery, queryMap, interceptor);
    }
  }

  private void setWithQuerySQL(String viewName, ValueQuery valueQuery)
  {

    String joinMainQuery = "";

    String areaGroup = "";

    List<String> geoIdAliases = new LinkedList<String>();

    for (Selectable s : valueQuery.getSelectableRefs())
    {
      this.abundanceCols.add(s.getColumnAlias());

      // This list of aliases is the list that defines what "The Group" is. The Group contains all restrictive terms/criteria that the number of collections is grouped by and it EXCLUDES species/taxon.
      // If a new term is added to this QueryBuilder you'll need to add it to this list.
      if ( s.getDbColumnName().startsWith("collectionRound") || s.getDbColumnName().startsWith("collectionType") ||
          s.getDbColumnName().startsWith("geoId_") || s.getDbColumnName().startsWith("collectionMethod") ||
          s.getDbColumnName().startsWith("subCollectionId") || s.getDbColumnName().startsWith("DATEGROUP") ||
          s.getDbColumnName().equals("geoEntity_displayLabel") || s.getDbColumnName().startsWith("wallType")
        )
      {
        joinMainQuery += "\n AND ss." + s.getColumnAlias() + " = mainQuery." + s.getColumnAlias() + " AND ss." + GEO_ID_COALESCE_ALIAS + " = mainQuery." + GEO_ID_COALESCE_ALIAS;
        areaGroup += "||  mainQuery." + s.getColumnAlias() + "  ";
      }

      String definedColumn = s.getMdAttributeIF().getColumnName();
      if (this.geoIdColumn.equals(definedColumn) && s.getMdAttributeIF().getMdAttributeConcrete() != null && GeoEntity.CLASS.equals(s.getMdAttributeIF().getMdAttributeConcrete().definedByClass().definesType()))
      {
        geoIdAliases.add("(CASE WHEN " + s.getDbQualifiedName() + " IS NULL THEN '' ELSE " + s.getDbQualifiedName() + " END)");
      }
    }

    areaGroup = areaGroup.substring(2);

    // to ensure proper joining and avoiding cross-products, create a string of
    // all geo ids that will be
    // used in the sub-selects.
    Selectable geoCoalesce = null;
    if (geoIdAliases.size() > 0)
    {
      String ids = StringUtils.join(geoIdAliases.toArray(new String[geoIdAliases.size()]), "||");
      geoCoalesce = valueQuery.aSQLCharacter(GEO_ID_COALESCE_ALIAS, "(" + ids + ")::varchar", GEO_ID_COALESCE_ALIAS);
    }
    else
    {
      geoCoalesce = valueQuery.aSQLCharacter(GEO_ID_COALESCE_ALIAS, "''", GEO_ID_COALESCE_ALIAS);
    }
    this.abundanceCols.add(geoCoalesce.getColumnAlias());
    valueQuery.SELECT(geoCoalesce);

    String origQuery = valueQuery.getSQL();

    String collectionIdCol = QueryUtil.getColumnName(MosquitoCollection.getCollectionIdMd());
    String subCollectionId = QueryUtil.getColumnName(SubCollection.getSubCollectionIdMd());
    String taxonCol = QueryUtil.getColumnName(SubCollection.getTaxonMd());
    String totalCol = QueryUtil.getColumnName(SubCollection.getFemalesTotalMd());
    String parentTermCol = QueryUtil.getColumnName(AllPaths.getParentTermMd());
    String childTermCol = QueryUtil.getColumnName(AllPaths.getChildTermMd());

    String selectAddtions = taxonCol + ",\n SUM(" + totalCol + ") as abundance_sum, \n array_agg(" + collectionIdCol + ") as collectionIds, \n array_agg(coalesce(" + collectionIdCol + " || " + subCollectionId + ", " + collectionIdCol + ")) as subCollectionIds  \n,";

    origQuery = origQuery.replaceFirst("SELECT", "SELECT " + selectAddtions).replaceFirst("GROUP BY", "GROUP BY " + taxonCol + ",");

    this.setWITHRecursive(true);
    this.addWITHEntry(new WITHEntry("mainQuery", origQuery));

    // taxonCountQuery is where each node gets the total of its child species
    String taxonCountQuery = "SELECT mainQuery.* ,";
    taxonCountQuery += "(SELECT SUM(ss.abundance_sum) FROM mainQuery as ss, allpaths_ontology ap ";
    // used to calcuate ratio
    taxonCountQuery += "WHERE ss." + taxonCol + " = " + childTermCol + " AND " + parentTermCol + " = mainQuery." + taxonCol + " AND ss." + taxonCol + " != mainQuery." + taxonCol + " " + joinMainQuery + " ) as total_of_children, \n";
    // list of collection ids in this group
    taxonCountQuery += "ARRAY(SELECT distinct unnest(collectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allCollectionIds, \n";
    // list of sub collection ids in this group
    taxonCountQuery += "ARRAY(SELECT distinct unnest(subCollectionIDs)FROM mainQuery as ss WHERE 1 = 1 " + joinMainQuery + " )::text[] allSubCollectionIds, \n";
    // used to order the recursive decent
    taxonCountQuery += "(SELECT COUNT(*) FROM mainQuery as ss, allpaths_ontology ap WHERE ss." + taxonCol + " = " + parentTermCol + "  AND " + childTermCol + " = mainQuery." + taxonCol + " AND ss.taxon != mainQuery." + taxonCol + " " + joinMainQuery + " )as depth, ";
    // the parent specie of this row in this group, may skip levels
    // taxonCountQuery += "(SELECT ss." + taxonCol +
    // " as depth FROM mainQuery as ss, allpaths_ontology ap WHERE ss." +
    // taxonCol + " = " + parentTermCol + "  AND " + childTermCol +
    // " = mainQuery." + taxonCol + " AND ss." + taxonCol + " != mainQuery." +
    // taxonCol + " " + joinMainQuery;
    // taxonCountQuery += " GROUP BY ss." + taxonCol +
    // " ORDER BY COUNT(*) DESC LIMIT 1 )as parent,\n";
    // JN fix
    taxonCountQuery += "(SELECT nt.parent FROM " + GET_NEXT_TAXON_FUNCTION + "(mainQuery.taxon) nt, mainQuery ss \n";
    taxonCountQuery += " where nt.parent = ss.taxon AND mainQuery.taxon != nt.parent " + joinMainQuery + " \n";
    taxonCountQuery += " ORDER BY nt.depth ASC LIMIT 1) as parent, \n";

    taxonCountQuery += areaGroup + " AS areaGroup\n";
    taxonCountQuery += " FROM mainQuery";
    this.addWITHEntry(new WITHEntry("taxonCountQuery", taxonCountQuery));

    joinMainQuery = joinMainQuery.replace("mainQuery", "taxonCountQuery");

    String percentViewSQL = "SELECT taxonCountQuery.*,";
    // -- ((me+my_children)/sum(everyone_at_my_level+their children))
    percentViewSQL += "((abundance_sum + coalesce(total_of_children,0)) / \n ";
    percentViewSQL += "NULLIF((SELECT SUM(coalesce(ss.total_of_children,0) + ss.abundance_sum) FROM taxonCountQuery AS ss WHERE ss.parent = taxonCountQuery.parent" + joinMainQuery + "   ), 0))  as my_share \n";
    percentViewSQL += "FROM taxonCountQuery \n";

    // This is a terrible hack but we've already broken out of the ValueQuery
    // and done manual SQL building, so this GROUP BY
    // must be done manually
    this.abundanceCols.add(taxonCol);
    this.abundanceCols.add("collectionIDs");
    this.abundanceCols.add("subCollectionIDs");
    this.abundanceCols.add("abundance_sum");
    this.abundanceCols.add("total_of_children");
    this.abundanceCols.add("allCollectionIds");
    this.abundanceCols.add("allSubCollectionIds");
    this.abundanceCols.add("depth");
    this.abundanceCols.add("parent");
    this.abundanceCols.add("areaGroup");

    String pctGB = StringUtils.join(this.abundanceCols, ',');

    percentViewSQL += "GROUP BY " + pctGB + " \n";

    this.addWITHEntry(new WITHEntry("percent_view", percentViewSQL));

    String rollupView = " SELECT *, abundance_sum + coalesce(total_of_children,0) as final_abundance\n";
    rollupView += "     FROM percent_view\n";
    rollupView += "     WHERE depth = 0\n";
    rollupView += " UNION ALL\n";
    rollupView += " SELECT child_v.*, parent_v.final_abundance * child_v.my_share \n";
    rollupView += " FROM " + viewName + " parent_v, percent_view child_v WHERE parent_v.taxon = child_v.parent AND parent_v.areagroup = child_v.areagroup AND child_v." 
      + GEO_ID_COALESCE_ALIAS + " = parent_v." + GEO_ID_COALESCE_ALIAS;
    
    if(this.hasRound)
    {
      rollupView += " AND child_v."+ROUND_DISPLAY_LABEL+" = parent_v."+ROUND_DISPLAY_LABEL;
    }
    
    if(this.hasType)
    {
      rollupView += " AND child_v."+TYPE_DISPLAY_LABEL+" = parent_v."+TYPE_DISPLAY_LABEL;
    }
    
    rollupView += " \n";
    this.addWITHEntry(new WITHEntry(viewName, rollupView));
  }

}
