package dss.vector.solutions.querybuilder;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.AttributeMoment;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDouble;
import com.runwaysdk.query.SelectableSQLFloat;
import com.runwaysdk.query.SelectableSQLInteger;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;
import dss.vector.solutions.entomology.SubCollection;
import dss.vector.solutions.entomology.SubCollectionQuery;
import dss.vector.solutions.entomology.assay.AbstractAssay;
import dss.vector.solutions.entomology.assay.AbstractAssayQuery;
import dss.vector.solutions.entomology.assay.EfficacyAssay;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoEntityView;
import dss.vector.solutions.geo.generated.Country;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.util.QBInterceptor;
import dss.vector.solutions.util.QueryUtil;

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

  private static final String GEO_ID_COALESCE_ALIAS   = "geo_id_coalesce_alias";

  public MosquitoCollectionQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageNumber, pageNumber);

    this.universalClass = Country.CLASS;
    this.forceUniversal = false;
    this.hasAbundance = this.hasAbundanceCalc(xml);
    this.abundanceCols = new HashSet<String>();
    this.collectionMethod = null;

    this.geoIdColumn = QueryUtil.getColumnName(GeoEntity.getGeoIdMd());
  }

  /**
   * Custom JSON config method to add the the highest universal in the system if
   * this query is for abundance calculation and if no universal columns have
   * been added.
   */
  @Override
  protected JSONObject constructQueryConfig(String config)
  {
    JSONObject json = super.constructQueryConfig(config);

    if (this.hasAbundance)
    {
      JSONObject selectedUniMap;
      try
      {
        selectedUniMap = json.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
        Iterator<String> keys = selectedUniMap.keys();
        if (keys.hasNext())
        {
          // Will be
          // "dss.vector.solutions.entomology.MosquitoCollection.geoEntity" as
          // mosquito collection
          // only has one GeoEntity attribute
          String key = keys.next();

          JSONArray universals = selectedUniMap.getJSONArray(key);
          if (universals.length() == 0)
          {
            // no universals even though we're doing an abundance calculation
            // so add the highest level universal automatically
            universals.put(this.universalClass);
            this.forceUniversal = true;
          }
        }
      }
      catch (JSONException e)
      {
        // we should never reach this
        throw new ProgrammingErrorException(e);
      }
    }

    return json;
  }

  @Override
  protected void setGeoCriteria(QBInterceptor interceptor, String attributeKey, AllPathsQuery allPathsQuery, List<ValueQuery> leftJoinValueQueries, ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap)
  {
    super.setGeoCriteria(interceptor, attributeKey, allPathsQuery, leftJoinValueQueries, valueQuery, queryMap);

    if (this.forceUniversal)
    {
      // We know there is only one left join universal (Earth)
      ValueQuery earthVQ = leftJoinValueQueries.get(0);

      String prepend = attributeKey.replaceAll("\\.", "_") + "__";
      String universalName = MdEntity.getMdEntity(this.universalClass).getTypeName();
      String entityNameAlias = prepend + universalName.toLowerCase() + "_" + GeoEntityView.ENTITYLABEL;
      String geoIdAlias = prepend + universalName.toLowerCase() + "_" + GeoEntityView.GEOID;

      Selectable name = earthVQ.aCharacter(entityNameAlias);
      Selectable geoId = earthVQ.aCharacter(geoIdAlias);

      // Due to an unfortunate hack, taxon is forced as the first column but the
      // query only
      // works correctly if the geo columns are after taxon but before
      // everything else. This
      // needs to be more fully tested. (See setWithQuerySQL() for the source of
      // this evil).
      List<Selectable> selectables = valueQuery.getSelectableRefs();
      selectables.add(0, name);
      selectables.add(1, geoId);

      valueQuery.clearSelectClause();
      valueQuery.SELECT(selectables.toArray(new Selectable[selectables.size()]));
    }
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
    // protect the user if they're doing abundance calcs without enough columns
    // (the collection method and species columns are not enough)
    if (!this.hasAbundance && ( valueQuery.hasSelectableRef("taxon") || valueQuery.hasSelectableRef("collectionMethod_ab") ))
    {
      throw new AbundanceColumnException("Abundance and/or aggregate calculations must be selected when in the Abundance section.");
    }

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
      SelectableSQLDouble overall;
      if (sel.isAggregateFunction())
      {
        overall = (SelectableSQLDouble) ( (AggregateFunction) sel ).getSelectable();
      }
      else
      {
        overall = (SelectableSQLDouble) sel;
      }

      String parous = subCollectionQuery.getTableAlias() + "." + QueryUtil.getColumnName(SubCollection.getParousMd());
      String disected = subCollectionQuery.getTableAlias() + "." + QueryUtil.getColumnName(SubCollection.getDisectedMd());

      String sql = "(" + parous + " / NULLIF(" + disected + ",0)::double precision * 100)";

      overall.setSQL(sql);
    }

    if (this.hasAbundance)
    {
      setAbundance(valueQuery, 1, "1");
      setAbundance(valueQuery, 10, "10");
      setAbundance(valueQuery, 100, "100");
      setAbundance(valueQuery, 1000, "1000");

      this.collectionMethod = mosquitoCollectionQuery.getCollectionMethod(MosquitoCollection.COLLECTIONMETHOD);
      valueQuery.SELECT(collectionMethod);

      valueQuery.WHERE(mosquitoCollectionQuery.getAbundance().EQ(true));

      setWithQuerySQL(ABUNDANCE_VIEW, valueQuery);

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
        else if (!s.getUserDefinedAlias().equals(GEO_ID_COALESCE_ALIAS))
        {
          overrideQuery.SELECT(overrideQuery.aSQLText(columnAlias, columnName, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel()));
        }

      }

      overrideQuery.FROM(ABUNDANCE_VIEW, ABUNDANCE_VIEW);
      return overrideQuery;

    }

    return valueQuery;
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
            ValueQuery termVQ = interceptor.getTermValueQuery(entityAlias);
            dss.vector.solutions.ontology.AllPathsQuery allpathsQ = (dss.vector.solutions.ontology.AllPathsQuery) queryMap.get(entityAlias);
            termVQ.SELECT(termVQ.aSQLInteger("existsConstant", "1"));
            termVQ.AND(allpathsQ.getChildTerm().EQ(termVQ.aSQLCharacter(this.collectionMethod.getAttributeNameSpace(), ABUNDANCE_VIEW + "." + this.collectionMethod.getColumnAlias())));

            // There is no condition passthrough so we have to hack in the
            // EXISTS
            // operator
            valueQuery.AND(termVQ.aSQLCharacter("termExistsSQL", "EXISTS (" + termVQ.getSQL() + ") AND true").EQ(termVQ.aSQLCharacter("termExistsSpoof", "true")));
          }
          else if (pair.getAttribute().equals("taxon_displayLabel"))
          {
            // Filtering on species during abundance calculation has to be done
            // at the end so it doesn't disrupt the results
            // of the recursive rollup. Even though the conditions are on the
            // ontology allpaths table, we strictly match on the
            // terms and do not include children.

            ValueQuery termVQ = interceptor.getTermValueQuery(entityAlias);
            dss.vector.solutions.ontology.AllPathsQuery allpathsQ = (dss.vector.solutions.ontology.AllPathsQuery) queryMap.get(entityAlias);

            String taxonCol = ABUNDANCE_VIEW + "." + QueryUtil.getColumnName(SubCollection.getTaxonMd());
            SelectableSQLCharacter taxon = termVQ.aSQLCharacter("taxon_term_criteria", taxonCol);

            termVQ.SELECT(allpathsQ.getParentTerm());
            termVQ.AND(allpathsQ.getChildTerm().EQ(taxon));

            // There is no condition passthrough so we have to hack in the
            // EXISTS
            // operator
            valueQuery.AND(termVQ.aSQLCharacter("termExistsSQL", taxonCol + " IN ((" + termVQ.getSQL() + ")) AND true").EQ(termVQ.aSQLCharacter("termExistsSpoof", "true")));
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

      if (s.getDbColumnName().startsWith("geoId_") || s.getDbColumnName().startsWith("collectionMethod") || s.getDbColumnName().startsWith("subCollectionId") || s.getDbColumnName().startsWith("DATEGROUP"))
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
    String totalCol = QueryUtil.getColumnName(SubCollection.getTotalMd());
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

    String rollupView = " SELECT " + GEO_ID_COALESCE_ALIAS + ", areagroup, " + taxonCol + ", parent, depth , my_share , abundance_sum + coalesce(total_of_children,0) as final_abundance\n";
    rollupView += "     FROM percent_view\n";
    rollupView += "     WHERE depth = 0\n";
    rollupView += " UNION\n";
    rollupView += " SELECT child_v." + GEO_ID_COALESCE_ALIAS + ", child_v.areagroup, child_v." + taxonCol + ", child_v.parent, child_v.depth ,child_v.my_share, \n";
    rollupView += "  parent_v.final_abundance * child_v.my_share \n";
    rollupView += " FROM rollup_view parent_v, percent_view child_v WHERE parent_v.taxon = child_v.parent AND parent_v.areagroup = child_v.areagroup AND child_v." + GEO_ID_COALESCE_ALIAS + " = parent_v." + GEO_ID_COALESCE_ALIAS + " \n";
    this.addWITHEntry(new WITHEntry("rollup_view", rollupView));

    String viewSQL = "SELECT pv.*, final_abundance\n";
    viewSQL += "FROM percent_view pv join  rollup_view  rv on rv.areagroup = pv.areagroup AND  rv." + taxonCol + " = pv." + taxonCol + " AND rv." + GEO_ID_COALESCE_ALIAS + " = pv." + GEO_ID_COALESCE_ALIAS + " \n";
    this.addWITHEntry(new WITHEntry(viewName, viewSQL));
  }

}
