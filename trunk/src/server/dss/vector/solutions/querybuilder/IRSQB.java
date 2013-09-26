package dss.vector.solutions.querybuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.COUNT;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MdEntity;
import com.runwaysdk.system.metadata.Metadata;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.AbstractSprayQuery;
import dss.vector.solutions.irs.AreaStandards;
import dss.vector.solutions.irs.GeoTarget;
import dss.vector.solutions.irs.HouseholdSprayStatus;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.TargetUnit;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.irs.ActualJoin;
import dss.vector.solutions.querybuilder.irs.ActualOperatorSprayTarget;
import dss.vector.solutions.querybuilder.irs.ActualTargetUnion;
import dss.vector.solutions.querybuilder.irs.ActualTeamSprayTarget;
import dss.vector.solutions.querybuilder.irs.ActualZoneSprayTarget;
import dss.vector.solutions.querybuilder.irs.Alias;
import dss.vector.solutions.querybuilder.irs.AreaJoin;
import dss.vector.solutions.querybuilder.irs.DiseaseSelectableWrapper;
import dss.vector.solutions.querybuilder.irs.IRSSpoofJoin;
import dss.vector.solutions.querybuilder.irs.IRSUnionIF;
import dss.vector.solutions.querybuilder.irs.OperatorJoin;
import dss.vector.solutions.querybuilder.irs.PlannedAreaTarget;
import dss.vector.solutions.querybuilder.irs.PlannedOperatorTarget;
import dss.vector.solutions.querybuilder.irs.PlannedSprayTeamTarget;
import dss.vector.solutions.querybuilder.irs.PlannedTargetUnion;
import dss.vector.solutions.querybuilder.irs.TargetJoin;
import dss.vector.solutions.querybuilder.irs.TeamJoin;
import dss.vector.solutions.querybuilder.util.QBInterceptor;
import dss.vector.solutions.util.QueryUtil;

public class IRSQB extends AbstractQB implements Reloadable
{
  private static final String               TEAM_TARGET_DIVERGENCE     = "team_target_divergence";

  private static final String               TEAM_PLANNED_COVERAGE      = "team_planned_coverage";

  public static final String                TEAM_PLANNED_TARGET        = "team_planned_target";

  private static final String               TEAM_TARGETED_COVERAGE     = "team_targeted_coverage";

  public static final String                TEAM_ACTUAL_TARGET         = "team_actual_target";

  private static final String               AREA_PLANNED_COVERAGE      = "area_planned_coverage";

  public static final String                AREA_PLANNED_TARGET        = "area_planned_target";

  private static final String               OPERATOR_TARGET_DIVERGENCE = "operator_target_divergence";

  public static final String                OPERATOR_ACTUAL_TARGET     = "operator_actual_target";

  private static final String               OPERATOR_TARGETED_COVERAGE = "operator_targeted_coverage";

  public static final String                OPERATOR_PLANNED_TARGET    = "operator_planned_target";

  private static final String               OPERATOR_PLANNED_COVERAGE  = "operator_planned_coverage";
  
  private static final String UNITAREA = "unitarea";

  // The smallest (most depth) universal selected in the query screen
  private String                            smallestUnivesal;

  // The selectable user alias of the smallest universal in the query screen
  private String                            smallestUniversalSelectable;

  // The union of actual and planned targets must include the minimum set
  // required to make the query work so as to not include superfluous rows.
  private boolean                           needsAreaPlanned;

  private boolean                           needsTeamsPlanned;

  private boolean                           needsOperatorPlanned;

  private Map<String, GeneratedEntityQuery> mainQueryMap;

  private Map<String, GeneratedEntityQuery> insecticideQueryMap;

  private Map<String, GeneratedEntityQuery> abtractSprayQueryMap;

  /**
   * Set of all aliases that are not used with actual spray data or
   * calculations.
   */
  private Set<String>                       nonActuals;

  /**
   * Set to true if any of the planned targets are included in the query.
   */
  private boolean                           hasPlannedTargets;

  private boolean                           hasSprayEnumOrTerm;

  private ValueQuery                        irsVQ;

  private ValueQuery                        sprayVQ;

  private JSONObject                        queryConfig;

  public static final String                RESOURCE_TARGET_VIEW       = "resourceTargetView";

  public static final String                DATE_EXTRAPOLATION         = "dateExtrapolation";
  
  public static final String                SPRAY_SUMMARY              = "spraySummary";

  public static final String                SPRAYED_ROOMS_SUM          = "sprayedRoomsSum";
  
  public static final String                SPRAYED_STRUCTURES_SUM     = "sprayedStructuresSum";
  
  public static final String                SPRAYED_HOUSEHOLDS_SUM     = "sprayedHouseholdsSum";

  public static final String                OPERATOR_SPRAY_ID     = "operatorSprayId";

  public static final String                GEO_TARGET_VIEW            = "geoTargetView";

  public static final String                PLANNED_OPERATOR           = "plannedOperator";

  public static final String                PLANNED_TEAM               = "plannedTeam";

  public static final String                TARGET_ROLLUP              = "targetRollup";

  public static final String                ROLLUP_RESULTS             = "rollupResults";

  public static final String                PLANNED_AREA               = "plannedArea";

  public static final String                ALL_ACTUALS                = "allActuals";

  public static final String                PLANNED_TARGET_DISEASE     = "disease";

  public static final String                ORIGINAL_ID                = "original_id";

  // The final view of all actual and planned targets
  public static final String                SPRAY_VIEW                 = "sprayView";

  public static final String                INSECTICIDE_VIEW           = "insecticideView";

  public static final String                TARGET_WEEK                = "target_week";

  public static final String                WEEKLY_TARGET              = "weekly_target";

  public static final String                MALARIA_SEASON             = "malariaSeasonId";

  private AbstractSprayQuery                abstractSprayQuery;

  private InsecticideBrandQuery             insecticideQuery;

  private InsecticideBrandQuery             outerInsecticideQuery;

  private String                            sprayViewAlias;

  private String                            idCol;
  
  private String keyName;

  private String                            sprayedUnits;

  private ValueQuery                        insecticideVQ;

  private String                            targeter;

  // private String mSeasonCol;

  private String                            geoEntity;

  private String                            periodCol;

  private int                               startDay;

  private String                            diseaseId;

  private boolean                           hasEpiWeek;

  private QueryFactory                      queryFactory;

  private ValueQueryParser                  irsParser;

  private ValueQueryParser                  insecticideParser;

  private ValueQueryParser                  sprayParser;

  public IRSQB(String config, String xml, Layer layer)
  {
    super(xml, config, layer);

    this.smallestUnivesal = null;

    hasEpiWeek = false;

    diseaseId = Disease.getCurrent().getId();

    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    try
    {
      this.queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }
    queryFactory = new QueryFactory();
    this.sprayVQ = new ValueQuery(queryFactory);
    this.irsVQ = new ValueQuery(queryFactory);
    this.insecticideVQ = new ValueQuery(queryFactory);

    this.hasSprayEnumOrTerm = false;

    this.needsAreaPlanned = false;
    this.needsOperatorPlanned = false;
    this.needsTeamsPlanned = false;

    this.hasPlannedTargets = false;

    this.abstractSprayQuery = null;
    this.insecticideQuery = null;

    this.sprayViewAlias = null;

    this.sprayedUnits = null;

    this.idCol = QueryUtil.getIdColumn();
    this.keyName = QueryUtil.getColumnName(Metadata.getKeyNameMd());
    this.targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());
    this.geoEntity = QueryUtil.getColumnName(ZoneSpray.getGeoEntityMd());

    this.periodCol = QueryUtil.getColumnName(EpiWeek.getPeriodMd());

    nonActuals = new HashSet<String>();
    nonActuals.add(TEAM_TARGET_DIVERGENCE);
    nonActuals.add(TEAM_PLANNED_COVERAGE);
    nonActuals.add(TEAM_PLANNED_TARGET);
    nonActuals.add(TEAM_TARGETED_COVERAGE);
    nonActuals.add(TEAM_ACTUAL_TARGET);
    nonActuals.add(AREA_PLANNED_COVERAGE);
    nonActuals.add(AREA_PLANNED_TARGET);
    nonActuals.add(OPERATOR_TARGET_DIVERGENCE);
    nonActuals.add(OPERATOR_ACTUAL_TARGET);
    nonActuals.add(OPERATOR_TARGETED_COVERAGE);
    nonActuals.add(OPERATOR_PLANNED_TARGET);
    nonActuals.add(OPERATOR_PLANNED_COVERAGE);

    nonActuals.add(Alias.SPRAY_LEADER_DEFAULT_LOCALE.getAlias());
    nonActuals.add(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
    nonActuals.add(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());
    nonActuals.add(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE.getAlias());
  }

  @Override
  protected QueryFactory createFactory()
  {
    return this.queryFactory;
  }

  @Override
  protected ValueQuery createValueQuery()
  {
    return this.irsVQ;
  }

  public String getDiseaseId()
  {
    return this.diseaseId;
  }

  public String getGeoEntity()
  {
    return this.geoEntity;
  }

  public String getTargeter()
  {
    return this.targeter;
  }

  public int getStartDay()
  {
    return startDay;
  }

  private void filterSelectables()
  {
    List<String> spraySQLs = Arrays.asList(new String[] { AbstractSpray.SPRAYMETHOD + "_spray",
        AbstractSpray.SURFACETYPE + "_spray" });

    String insecticideTable = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS).getTableName();
    List<String> insecticideSQLs = new LinkedList<String>();

    // insecticide calculations
    insecticideSQLs.addAll(Arrays.asList(new String[] { "nozzle_defaultLocale", "nozzle_ratio",
        "active_ingredient_per_can", "standard_application_rate", "standard_application_rate_mg",
        "units_per_can" }));

    // insecticide terms and enums
    for (String termAttr : Term.getTermAttributes(InsecticideBrand.CLASS))
    {
      insecticideSQLs.add(termAttr + QueryUtil.DISPLAY_LABEL_SUFFIX);
    }

    for (String enumAttr : QueryUtil.getEnumAttributes(InsecticideBrand.CLASS))
    {
      insecticideSQLs.add(enumAttr + QueryUtil.DISPLAY_LABEL_SUFFIX);
    }

    String geoPrefix = ( AbstractSpray.CLASS + "." + AbstractSpray.GEOENTITY ).replaceAll("\\.", "_");

    // remove insecticide selectables from the irsQuery
    List<Selectable> irsSels = new LinkedList<Selectable>();
    List<Selectable> insecticideSels = new LinkedList<Selectable>();
    List<Selectable> spraySels = new LinkedList<Selectable>();
    for (Selectable sel : irsVQ.getSelectableRefs())
    {
      String alias = sel.getUserDefinedAlias();
      String name = sel._getAttributeName();

      if (sel.getDefiningTableName().equals(insecticideTable) || insecticideSQLs.contains(alias)
          || insecticideSQLs.contains(name))
      {
        Selectable iSel = insecticideVQ.getSelectableRef(alias);
        iSel.setColumnAlias(iSel.getColumnAlias() + "_i"); // namespace to
        // avoid
        // a bug in grouping
        insecticideSels.add(iSel);
      }
      else if (spraySQLs.contains(alias))
      {
        Selectable sSel = sprayVQ.getSelectableRef(alias);
        sSel.setColumnAlias(sSel.getColumnAlias() + "_s"); // namespace to
        // avoid
        // a bug in grouping
        spraySels.add(sSel);
      }
      else
      {
        if (alias.startsWith(geoPrefix) || alias.equals(QueryUtil.RATIO)
            || alias.equals(QueryUtil.START_DATE_RANGE) || alias.equals(QueryUtil.END_DATE_RANGE)
            || sel instanceof COUNT)
        {
          nonActuals.add(alias);
        }

        irsSels.add(irsVQ.getSelectableRef(alias));
      }
    }

    irsVQ.clearSelectClause();

    insecticideVQ = new ValueQuery(irsVQ.getQueryFactory());
    sprayVQ = new ValueQuery(irsVQ.getQueryFactory());

    irsVQ.SELECT(irsSels.toArray(new Selectable[irsSels.size()]));
    this.setNumericRestrictions(irsVQ, queryConfig);

    if (insecticideQuery != null)
    {
      insecticideVQ.SELECT(insecticideSels.toArray(new Selectable[insecticideSels.size()]));
      insecticideVQ.SELECT(insecticideQuery.getId(InsecticideBrand.ID));

      for (Selectable sel : insecticideSels)
      {
        irsVQ.SELECT(irsVQ.aSQLCharacter(sel.getColumnAlias(), insecticideQuery.getTableAlias() + "."
            + sel.getColumnAlias(), sel.getUserDefinedAlias()));
      }
    }

    if (spraySels.size() > 0)
    {
      this.hasSprayEnumOrTerm = true;

      sprayVQ.SELECT(spraySels.toArray(new Selectable[spraySels.size()]));
      sprayVQ.SELECT(abstractSprayQuery.getId(AbstractSpray.ID));

      for (Selectable sel : spraySels)
      {
        irsVQ.SELECT(irsVQ.aSQLCharacter(sel.getColumnAlias(), abstractSprayQuery.getTableAlias() + "."
            + sel.getColumnAlias(), sel.getUserDefinedAlias()));
      }
    }
  }

  public boolean hasAreaCalcs()
  {
    return this.irsVQ.hasSelectableRef(AREA_PLANNED_TARGET)
        || this.irsVQ.hasSelectableRef(AREA_PLANNED_COVERAGE);
  }

  public String getSmallestUniversal()
  {
    return this.smallestUnivesal;
  }

  @Override
  protected ValueQueryParser createParser(ValueQuery valueQuery, List<ParseInterceptor> interceptors)
  {
    this.irsParser = super.createParser(this.irsVQ, this.createParseInterceptors(this.irsVQ));
    this.insecticideParser = super.createParser(this.insecticideVQ, this
        .createParseInterceptors(this.insecticideVQ));
    this.sprayParser = super.createParser(this.sprayVQ, this.createParseInterceptors(this.sprayVQ));

    return this.irsParser;
  }

  @Override
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedEntityQuery> queryMap,
      QBInterceptor interceptor)
  {
    QBInterceptor qbInterceptor = this.getQBInterceptor(this.irsParser);
    super.setTermCriteria(irsVQ, this.mainQueryMap, qbInterceptor);

    if (insecticideQuery != null)
    {
      qbInterceptor = this.getQBInterceptor(this.insecticideParser);
      super.setTermCriteria(insecticideVQ, insecticideQueryMap, qbInterceptor);
    }

    if (this.hasSprayEnumOrTerm)
    {
      qbInterceptor = this.getQBInterceptor(this.sprayParser);
      super.setTermCriteria(sprayVQ, abtractSprayQueryMap, qbInterceptor);
    }
  }
  
//  @Override
//  protected void setGeoCriteria(QBInterceptor interceptor, String attributeKey,
//      AllPathsQuery allPathsQuery, List<ValueQuery> leftJoinValueQueries, ValueQuery valueQuery,
//      Map<String, GeneratedEntityQuery> queryMap)
//  {
//
//  }

  @Override
  protected Map<String, GeneratedEntityQuery> joinQueryWithGeoEntities(QueryFactory factory,
      ValueQuery valueQuery, String xml, JSONObject queryConfig, Layer layer, ValueQueryParser parser)
  {
    this.mainQueryMap = super.joinQueryWithGeoEntities(factory, irsVQ, xml, queryConfig, layer,
        this.irsParser);

    this.insecticideQueryMap = super.joinQueryWithGeoEntities(factory, insecticideVQ, xml, queryConfig,
        null, this.insecticideParser);

    this.abtractSprayQueryMap = super.joinQueryWithGeoEntities(factory, sprayVQ, xml, queryConfig, null,
        this.sprayParser);

    return this.mainQueryMap;
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return AbstractSpray.CLASS;
  }

  /**
   * Populates the ValueQuery with the necessary selects, joins, and criteria to
   * make the IRS query work correctly. ORDER IS IMPORTANT. Do not change the
   * calls within this method unless you know what you are doing! There are many
   * boolean flags set within those calls that dictate how the query will
   * behave.
   */
  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedEntityQuery> queryMap, String xml, JSONObject queryConfig)
  {
    queryFactory = this.irsVQ.getQueryFactory();

    this.sprayViewAlias = this.mainQueryMap.get(AbstractSpray.CLASS).getTableAlias();
    this.outerInsecticideQuery = (InsecticideBrandQuery) this.mainQueryMap.get(InsecticideBrand.CLASS);
    this.insecticideQuery = (InsecticideBrandQuery) this.insecticideQueryMap.get(InsecticideBrand.CLASS);
    this.abstractSprayQuery = (AbstractSprayQuery) this.abtractSprayQueryMap.get(AbstractSpray.CLASS);

    filterSelectables();

    swapOutAttributesForAggregates();

//    this.hasEpiWeek = irsVQ.hasSelectableRef(QueryUtil.DATEGROUP_EPIWEEK);

    if (insecticideQuery != null)
    {
      QueryUtil.joinEnumerationDisplayLabels(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      this.setNumericRestrictions(insecticideVQ, queryConfig);
    }

    String sprayDate = QueryUtil.getColumnName(AbstractSpray.getSprayDateMd());

    // Spray Date
    QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + sprayDate);

    // Geo Entity
    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) irsVQ
          .getSelectableRef(AbstractSpray.GEOENTITY);
      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY,
          sprayViewAlias + "." + idCol);
    }

    if (this.hasSprayEnumOrTerm)
    {
      QueryUtil.joinEnumerationDisplayLabels(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.joinTermAllpaths(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery);
    }

    // setAbstractSprayAttributes();

    String availableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    
    String uniqueSprayId = this.getUniqueSprayDetailsId();
    
    
    String unit_operational_coverage = "SUM(" + sprayedUnits + ")::float / nullif(SUM(" + availableUnits
        + "),0)";

    // #2868 - instead of -refills- we now sum on sachets -used-
    String unit_application_rate = "SUM("+Alias.USED.getAlias()+"::FLOAT * " + shareOfCans
        + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * unitarea),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

    
    if(QueryUtil.setSelectabeSQL(irsVQ, "sprayedunits", sprayedUnits))
    {
      QueryUtil.setAttributesAsAggregated(new String[]{"sprayedunits"}, uniqueSprayId, irsVQ, this.sprayViewAlias, true, true);
    }
    
    if(QueryUtil.setSelectabeSQL(irsVQ, "unit_unsprayed", unsprayedUnits))
    {
      QueryUtil.setAttributesAsAggregated(new String[]{"unit_unsprayed"}, uniqueSprayId, irsVQ, null, true, true);
    }
    
    QueryUtil.setSelectabeSQL(irsVQ, "refills * " + shareOfCans, unsprayedUnits);

    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_rate", "(" + unit_application_rate + ")");
    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_rate_mg", "1000.0 *" + "("
        + unit_application_rate + ")");
    
    QueryUtil.setSelectabeSQL(irsVQ, "unit_application_ratio", unit_application_ratio);
    
    QueryUtil
        .setSelectabeSQL(irsVQ, "unit_operational_coverage", unit_operational_coverage + " * 100.0");

    QueryUtil.setSelectabeSQL(irsVQ, "calculated_rooms_sprayed", "(" + unit_operational_coverage
        + ") * SUM(rooms)");
    QueryUtil.setSelectabeSQL(irsVQ, "calculated_structures_sprayed", "(" + unit_operational_coverage
        + ") * SUM(structures)");
    QueryUtil.setSelectabeSQL(irsVQ, "calculated_households_sprayed", "(" + unit_operational_coverage
        + ") * SUM(households)");

    setTargetManagmentCalculations();

    SelectableSQL diseaseSel = irsVQ.aSQLCharacter(Alias.DISEASE.getAlias(), Alias.DISEASE.getAlias());

    DiseaseSelectableWrapper wrapper = new DiseaseSelectableWrapper(diseaseSel, this.sprayViewAlias);

    QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper);

    addPlannedTargetDateCriteria();

    /* DATE POST PROCESSING */
    if (this.hasPlannedTargets)
    {
      // Set the other date groupings to null for the planning rows
      String[] groups = new String[] { QueryUtil.DATEGROUP_EPIWEEK, QueryUtil.DATEGROUP_EPIYEAR,
          QueryUtil.DATEGROUP_MONTH, QueryUtil.DATEGROUP_QUARTER, QueryUtil.DATEGROUP_CALENDARYEAR,
          QueryUtil.DATEGROUP_SEASON };

      for (String group : groups)
      {
        if (irsVQ.hasSelectableRef(group))
        {
          nonActuals.add(group);

          SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(group);
          String original = sel.getSQL();

          String sprayDateCol = sprayViewAlias+"."+Alias.SPRAY_DATE.getAlias();
          String plannedDateCol = sprayViewAlias+"."+Alias.PLANNED_DATE.getAlias();
          
          String sql;
          if(group.equals(QueryUtil.DATEGROUP_SEASON))
          {
            // If there isn't a planned date then don't show the season because that
            // means activity didn't match a target, and it's rather meaningless to
            // show a season value just because a spray date coincidentally fell within
            // the season range.
            String replaced = original.replaceAll(sprayViewAlias+"\\."+Alias.SPRAY_DATE.getAlias(), plannedDateCol);
            sql = "CASE WHEN "+plannedDateCol+" IS NOT NULL THEN "+replaced+" ELSE NULL END";
          }
          else
          {
            String caseStmt = "CASE WHEN " + sprayDateCol
              + " IS NOT NULL THEN " + sprayDateCol + " ELSE "
              + plannedDateCol + " END";
            sql = original.replaceAll(sprayViewAlias+"\\."+Alias.SPRAY_DATE.getAlias(), caseStmt);
          }
          sel.setSQL(sql);
        }
      }
    }

    joinMainQueryTables();

    // Note: setWithQuerySQL() must go last
    // so the target management flags will be set correctly.
    setWithQuerySQL();

    return this.irsVQ;
  }

  /**
   * Some attributes must be swapped out with custom selectables
   */
  private void swapOutAttributesForAggregates()
  {
    // 
    String[] insecticideAliases = new String[] {

      // insecticide usage
      Alias.RECEIVED.getAlias(),
      Alias.USED.getAlias(),
      Alias.REFILLS.getAlias(),
      Alias.RETURNED.getAlias()
    };

    QueryUtil.setAttributesAsAggregated(insecticideAliases, idCol, irsVQ, sprayViewAlias, true);

    String[] sprayDetails = new String[] {

        // spray details (attributes defined by HouseholdSprayStatus)
        Alias.HOUSEHOLDS.getAlias(),
        Alias.SPRAYED_HOUSEHOLDS.getAlias(),
        Alias.STRUCTURES.getAlias(),
        Alias.SPRAYED_STRUCTURES.getAlias(),
        Alias.ROOMS.getAlias(),
        Alias.SPRAYED_ROOMS.getAlias(),
        Alias.LOCKED.getAlias(),
        Alias.REFUSED.getAlias(),
        Alias.OTHER.getAlias(),
        Alias.WRONG_SURFACE.getAlias()
      };
    
    // spray details are unique by the household and structure id
    String detailUniqueId = this.getUniqueSprayDetailsId();
    QueryUtil.setAttributesAsAggregated(sprayDetails, detailUniqueId, irsVQ, sprayViewAlias, true);
  }
  
  /**
   * Household spray details are unique by their household and structure ids.
   * 
   * @return
   */
  private String getUniqueSprayDetailsId()
  {
//    return idCol+" || '_' || "+Alias.UNIQUE_SPRAY_ID.getAlias();
    return Alias.UNIQUE_SPRAY_ID.getAlias();
  }

  private void addPlannedTargetDateCriteria()
  {
    try
    {
      JSONObject dateObj = queryConfig.getJSONObject(QueryUtil.DATE_ATTRIBUTE);

      if (dateObj.has("start") && !dateObj.isNull("start") && !dateObj.getString("start").equals("null"))
      {
        String startValue = dateObj.getString("start");

        Condition or = OR.get(irsVQ.aSQLDate("planned_start_date",
            this.sprayViewAlias + "." + Alias.PLANNED_DATE).GE(
            irsVQ.aSQLDate("planned_start_val", "'" + startValue + "'")), irsVQ.aSQLDate(
            "spray_start_date", this.sprayViewAlias + "." + Alias.SPRAY_DATE).GE(
            irsVQ.aSQLDate("spray_start_val", "'" + startValue + "'")));

        irsVQ.AND(or);
      }
      if (dateObj.has("end") && !dateObj.isNull("end") && !dateObj.getString("end").equals("null"))
      {
        String endValue = dateObj.getString("end");

        Condition or = OR.get(irsVQ.aSQLDate("planned_end_date",
            this.sprayViewAlias + "." + Alias.PLANNED_DATE).LE(
            irsVQ.aSQLDate("planned_end_val", "'" + endValue + "'")), irsVQ.aSQLDate("end_date",
            this.sprayViewAlias + "." + Alias.SPRAY_DATE).LE(
            irsVQ.aSQLDate("end_val", "'" + endValue + "'")));

        irsVQ.AND(or);
      }
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  /**
   * Joins the necessary tables to make the main query work (this also includes
   * setting the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    // FIXME remove this hack! Use SQL pass-through if necessary.
    String abstractSprayTable = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS).getTableName();
    IRSSpoofJoin join = new IRSSpoofJoin(idCol, abstractSprayTable, this.sprayViewAlias, idCol,
        abstractSprayTable, this.sprayViewAlias);
    irsVQ.AND(join);

    StringBuffer str = new StringBuffer();
    String idCol = QueryUtil.getIdColumn();
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    String leftTable = IRSQB.SPRAY_VIEW;
    String leftAlias = this.sprayViewAlias;
    String insecticideView = IRSQB.INSECTICIDE_VIEW;

    // TODO refactor this to use more of the query API instead of string
    // concatenation.
    // Push InsecticideView into a ValueQuery
    // String start = ( str.toString().trim().equals("FROM") ? "" : "," ) +
    // " \n";
    str.append("");
    str.append(leftTable + " " + leftAlias);
    
    if(irsVQ.hasSelectableRef(QueryConstants.AUDIT_IMPORTED_ALIAS))
    {
      str.append(" LEFT JOIN "+IMPORTED_DATETIME+" ON");
      str.append(" "+IMPORTED_DATETIME+"."+IMPORTED_CREATE_DATE);
      str.append(" = "+leftAlias+"."+Alias.CREATE_DATE.getAlias()+" ");
    }

    if (insecticideQuery != null)
    {
      String insecticideId = insecticideVQ.getSelectableRef(InsecticideBrand.ID).getColumnAlias();

      insecticideVQ.FROM(INSECTICIDE_VIEW, INSECTICIDE_VIEW);
      insecticideVQ.AND(insecticideVQ.aSQLCharacter("i_vq",
          insecticideQuery.getTableAlias() + "." + idCol).EQ(
          insecticideVQ.aSQLCharacter("i_view", INSECTICIDE_VIEW + "." + idCol)));

      String joinType = this.hasPlannedTargets ? "LEFT JOIN" : "INNER JOIN";
      str.append(" " + joinType + " (" + insecticideVQ.getSQL() + ") "
          + insecticideQuery.getTableAlias() + " ON " + leftAlias + "." + Alias.BRAND + " = "
          + insecticideQuery.getTableAlias() + "." + insecticideId + " \n");

      // IMPORTANT: Because we are joining three parsed queries, the irsVQ will
      // have some conditions applied
      // to it instead of the insecticideVQ, and that will force the insecticide
      // brand table to be automatically
      // included in the main query. Join on the insecticide brand in the main
      // query with that of the insecticideVQ
      // to make sure everything matches correctly.
      irsVQ.FROM(outerInsecticideQuery.getMdClassIF().getTableName(), outerInsecticideQuery
          .getTableAlias());
      irsVQ
          .WHERE(irsVQ.aSQLCharacter("forceJoin1", outerInsecticideQuery.getId().getDbQualifiedName())
              .EQ(
                  irsVQ.aSQLCharacter("forceJoin2", insecticideQuery.getTableAlias() + "."
                      + insecticideId)));
    }

    if (this.hasSprayEnumOrTerm)
    {
      String sprayId = sprayVQ.getSelectableRef(AbstractSpray.ID).getColumnAlias();

      String joinType = this.hasPlannedTargets ? "LEFT JOIN" : "INNER JOIN";
      str.append(" " + joinType + " (" + sprayVQ.getSQL() + ") " + abstractSprayQuery.getTableAlias()
          + " ON " + leftAlias + "." + Alias.ID + " = " + abstractSprayQuery.getTableAlias() + "."
          + sprayId + " \n");
    }

    // always join on the insecticide view
    str.append(" LEFT JOIN " + insecticideView + " " + insecticideView + " ON " + leftAlias + "."
        + Alias.BRAND + " = " + insecticideView + "." + idCol + " AND \n" + insecticideView + "."
        + diseaseCol + " = " + leftAlias + "." + Alias.DISEASE + "  \n");

    // restrict by dates
    str.append("AND ((" + leftAlias + "." + Alias.SPRAY_DATE + ") >= (" + insecticideView
        + ".start_date) \n");
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView
        + ".end_date)) \n");
    
    /* removed for #2826
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") >= (" + insecticideView
        + ".nozzleStart)) \n");
    str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView
        + ".nozzleEnd)) \n");
    */
    
    // Special case to include rows from the planned targets, which have no
    // dates
    if (this.hasPlannedTargets)
    {
      str.append("OR " + leftAlias + "." + Alias.SPRAY_DATE + " IS NULL \n");
    }

    str.append("\n");

    irsVQ.FROM(str.toString(), "");
  }
  
  @Override
  protected void joinImported(GeneratedEntityQuery q, QueryFactory f, ValueQuery v,
      Selectable importCreateDate)
  {
    // do nothing. Custom join logic is in this.joinMainQueryTables()
  }

  private String createUnion(IRSUnionIF... unions)
  {
    String sql = "";
    int count = 0;
    for (IRSUnionIF union : unions)
    {
      union.setIRSQuery(this);

      if (union instanceof ActualTargetUnion)
      {
        sql += getUnionSQL((ActualTargetUnion) union);
      }
      else
      {
        sql += getUnionSQL((PlannedTargetUnion) union);
      }

      if (count < unions.length - 1)
      {
        sql += "\nUNION\n";
      }

      count++;
    }

    return sql;
  }

  private void setTargetManagmentCalculations()
  {
    calculateAreaPlannedTargets();
    calculateAreaPlannedCoverage();

    calculateTeamActualTargets();
    calculateTeamPlannedTargets();
    calculateTeamPlannedCoverage();
    calculateTeamTargetedCoverage();
    calculateTeamTargetDivergence();

    calculateOperatorActualTargets();
    calculateOperatorPlannedTargets();
    calculateOperatorPlannedCoverage();
    calculateOperatorTargetedCoverage();
    calculateOperatorTargetDivergence();

    this.hasPlannedTargets = this.needsAreaPlanned || this.needsOperatorPlanned
        || this.needsTeamsPlanned;
  }

  String getAbstractSprayAlias()
  {
    return sprayViewAlias;
  }

  private void setWithQuerySQL()
  {
    String resourceTargetViewQuery = this.getTargetResourceView();
    String geoTargetViewQuery = this.getGeoTargetView();
    String insecticideBrandQuery = this.getInsecticideView();
    String dateExtrapolationQuery = this.getDateExtrapolationView();
    String spraySummaryQuery = this.getSpraySummaryView();
    // String targetRollupQuery = this.getTargetRollupView();
    // String rollupResultsQuery = this.getRollupResultsView();

    this.setWITHRecursive(true);

//    sql += " " + DATE_EXTRAPOLATION + " AS \n";
//    sql += "(" + dateExtrapolationQuery + ")\n";
    this.addWITHEntry(new WITHEntry(DATE_EXTRAPOLATION, dateExtrapolationQuery));
    
//    sql += ", " + RESOURCE_TARGET_VIEW + " AS \n";
//    sql += "(" + resourceTargetViewQuery + ")\n";
    this.addWITHEntry(new WITHEntry(RESOURCE_TARGET_VIEW, resourceTargetViewQuery));

//    sql += ", " + GEO_TARGET_VIEW + " AS \n";
//    sql += "(" + geoTargetViewQuery + ")\n";
    this.addWITHEntry(new WITHEntry(GEO_TARGET_VIEW, geoTargetViewQuery));
    
    this.addWITHEntry(new WITHEntry(SPRAY_SUMMARY, spraySummaryQuery));

    // sql += ", " + TARGET_ROLLUP + " AS \n";
    // sql += "(" + targetRollupQuery + ")\n";
    //
    // sql += ", " + ROLLUP_RESULTS + " AS \n";
    // sql += "(" + rollupResultsQuery + ")\n";

//    sql += ", " + INSECTICIDE_VIEW + " AS \n";
//    sql += "(" + insecticideBrandQuery + ")\n";
    this.addWITHEntry(new WITHEntry(INSECTICIDE_VIEW, insecticideBrandQuery));

//    sql += "," + ALL_ACTUALS + " AS \n";
//    sql += "("
//        + createUnion(new ActualOperatorSprayTarget(), new ActualTeamSprayTarget(),
//            new ActualZoneSprayTarget()) + ")\n";
    this.addWITHEntry(new WITHEntry(ALL_ACTUALS, createUnion(new ActualOperatorSprayTarget(), new ActualTeamSprayTarget(),
        new ActualZoneSprayTarget())));

    if (this.needsAreaPlanned)
    {
//      sql += "," + PLANNED_AREA + " AS \n";
//      sql += "(" + createUnion(new PlannedAreaTarget()) + ")\n";
      this.addWITHEntry(new WITHEntry(PLANNED_AREA, createUnion(new PlannedAreaTarget())));
    }

    if (this.needsTeamsPlanned)
    {
//      sql += "," + PLANNED_TEAM + " AS \n";
//      sql += "(" + createUnion(new PlannedSprayTeamTarget()) + ")\n";
      this.addWITHEntry(new WITHEntry(PLANNED_TEAM, createUnion(new PlannedSprayTeamTarget())));
    }

    if (this.needsOperatorPlanned)
    {
//      sql += "," + PLANNED_OPERATOR + " AS \n";
//      sql += "(" + createUnion(new PlannedOperatorTarget()) + ")\n";
      this.addWITHEntry(new WITHEntry(PLANNED_OPERATOR, createUnion(new PlannedOperatorTarget())));
    }

//    sql += "," + SPRAY_VIEW + " AS \n";
//    sql += "(" + createSprayView() + ")\n";
    this.addWITHEntry(new WITHEntry(SPRAY_VIEW, createSprayView()));

//    irsVQ.setSqlPrefix(sql);
  }

  private String getDateExtrapolationView()
  {
    String yearCol = QueryUtil.getColumnName(EpiWeek.getYearOfWeekMd());

    String sql = "";
    sql += "SELECT \n";
    sql += " " + periodCol + " AS " + periodCol + ", \n";
    sql += " (get_epistart(" + yearCol + ", " + startDay + ") + (to_char((" + periodCol
        + ")*7, '999')||' days')::interval)::date AS " + Alias.PLANNED_DATE + " \n";
    sql += "FROM epi_week ";

    return sql;
  }
  
  private String getSpraySummaryView()
  {
    String sprayedRooms = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedRoomsMd());
    String sprayedHouseholds = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedHouseholdsMd());
    String sprayedStructures = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayedStructuresMd());
    String householdSprayStatus = MdEntity.getMdEntity(HouseholdSprayStatus.CLASS).getTableName();
    String operatorSpray = MdEntity.getMdEntity(OperatorSpray.CLASS).getTableName();
//    String spray = QueryUtil.getColumnName(HouseholdSprayStatus.getSprayMd());
    
    String sql = "";
    sql += "SELECT \n";
    sql += " o."+QueryUtil.getIdColumn()+" as "+OPERATOR_SPRAY_ID+", \n";
    sql += " SUM("+sprayedRooms+") as "+SPRAYED_ROOMS_SUM+", \n";
    sql += " SUM("+sprayedHouseholds+") as "+SPRAYED_HOUSEHOLDS_SUM+", \n";
    sql += " SUM("+sprayedStructures+") as "+SPRAYED_STRUCTURES_SUM+" \n";
    sql += "FROM \n";
    sql += " "+householdSprayStatus+" h inner join "+operatorSpray+" o on o."+QueryUtil.getIdColumn()+" = h.spray \n";
    sql += "GROUP BY \n";
    sql += " o.id \n";
    return sql;
  }

  /**
   * Adds the alias to the group by clause of the query.
   * 
   * @param alias
   * @return
   */
  private String forceGrouping(Alias alias)
  {
    String sql = sprayViewAlias + "." + alias.getAlias();
    if (!irsVQ.hasSelectableRef(alias.getAlias()))
    {
      irsVQ.SELECT(irsVQ.aSQLCharacter(alias.getAlias(), sql));
    }

    irsVQ.GROUP_BY((SelectableSingle) irsVQ.getSelectableRef(alias.getAlias()));

    return sql;
  }

  private String sumOperatorActualTargets()
  {
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, OPERATOR_ACTUAL_TARGET);

    // return forceGrouping(Alias.OPERATOR_ACTUAL_TARGET);
  }

  private String sumTeamActualTargets()
  {
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, TEAM_ACTUAL_TARGET);
    // return forceGrouping(Alias.TEAM_ACTUAL_TARGET);
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    if (!this.hasEpiWeek)
    {
      return QueryUtil.sumColumnForId(sprayViewAlias, Alias.TARGET_WEEK.getAlias(), sprayViewAlias,
          OPERATOR_PLANNED_TARGET);
    }
    else
    {
      return forceGrouping(Alias.OPERATOR_PLANNED_TARGET);
    }
  }

  private String sumTeamPlannedTargets()
  {
    this.needsTeamsPlanned = true;

    if (!this.hasEpiWeek)
    {
      return QueryUtil.sumColumnForId(sprayViewAlias, Alias.TARGET_WEEK.getAlias(), sprayViewAlias,
          TEAM_PLANNED_TARGET);
    }
    else
    {
      return forceGrouping(Alias.TEAM_PLANNED_TARGET);
    }
  }

  private String sumAreaPlannedTargets()
  {
    this.needsAreaPlanned = true;

    if (!this.hasEpiWeek)
    {
      String geoEntity = this.sprayViewAlias+"."+Alias.GEO_ENTITY.getAlias();
      String disease = this.sprayViewAlias+"."+Alias.DISEASE.getAlias();
      String season = this.sprayViewAlias+"."+Alias.SPRAY_SEASON.getAlias();
      String func = QueryConstants.SUM_AREA_TARGETS+"("+geoEntity+", to_char("+IRSQB.TARGET_WEEK+"-1, 'FM99'), "
        +disease+", "+season+")";
      
      String check = "(CASE WHEN "+season+" IS NOT NULL AND "+geoEntity+" IS NOT NULL THEN "+func+" ELSE NULL END)";
      String sum = QueryUtil.sumColumnForId(sprayViewAlias, Alias.TARGET_WEEK.getAlias(), null, check);
      
      return sum;
    }
    else
    {
      return forceGrouping(Alias.AREA_PLANNED_TARGET);
    }
  }

  private void calculateOperatorPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_PLANNED_TARGET);
      String sql = sumOperatorPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(TEAM_PLANNED_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_PLANNED_TARGET);
      String sql = sumTeamPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_TARGET_DIVERGENCE);
      String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF(" + sumOperatorPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(TEAM_TARGET_DIVERGENCE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_TARGET_DIVERGENCE);

      String sql = "(" + this.sumTeamActualTargets() + "/NULLIF(" + this.sumTeamPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_TARGETED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamActualTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumOperatorPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_PLANNED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_PLANNED_COVERAGE);
      String sql = "(SUM(" + this.sprayedUnits + ")/NULLIF(" + this.sumTeamPlannedTargets()
          + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(AREA_PLANNED_COVERAGE))
    {
      this.getGeoType();

      if (irsVQ.hasSelectableRef(this.smallestUniversalSelectable))
      {
        String uniqueSprayId = this.getUniqueSprayDetailsId();
        String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);
        
        SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(AREA_PLANNED_COVERAGE);
        String sql = "((" + sum + ")/NULLIF(" + this.sumAreaPlannedTargets()
            + ",0))*100.0";
        calc.setSQL(sql);
      }
      else
      {
        throw new IncidencePopulationException();
      }
    }
  }

  private void calculateOperatorActualTargets()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_ACTUAL_TARGET);
      String sql = this.sumOperatorActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamActualTargets()
  {
    if (irsVQ.hasSelectableRef(TEAM_ACTUAL_TARGET))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_ACTUAL_TARGET);
      String sql = this.sumTeamActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(AREA_PLANNED_TARGET))
    {
      this.getGeoType();

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(AREA_PLANNED_TARGET);

      if (irsVQ.hasSelectableRef(this.smallestUniversalSelectable))
      {
        String sql = this.sumAreaPlannedTargets();
        calc.setSQL(sql);
      }
      else
      {
        throw new IncidencePopulationException();
      }
    }
  }

  private String createSprayView()
  {
    String sql = "";

    List<TargetJoin> joins = new LinkedList<TargetJoin>();

    if (this.hasPlannedTargets)
    {
      if (needsAreaPlanned)
      {
        joins.add(new AreaJoin(true, needsAreaPlanned));
      }

      if (needsTeamsPlanned)
      {
        joins.add(new TeamJoin(true, needsTeamsPlanned));
      }

      if (needsOperatorPlanned)
      {
        joins.add(new OperatorJoin(true, needsOperatorPlanned));
      }
    }
    else
    {
      joins.add(new ActualJoin());
    }

    int count = 0;
    for (TargetJoin join : joins)
    {
      join.setIRSQuery(this);

      sql += "SELECT \n";
      sql += join.setId(Alias.ID) + ", \n";
      sql += join.setUniqueSprayId(Alias.UNIQUE_SPRAY_ID) + ", \n";
      sql += join.setUniquePlannedId(Alias.UNIQUE_PLANNED_ID) + ", \n";
      sql += join.setCreateDate(Alias.CREATE_DATE) + ", \n";
      sql += join.setLastUpdateDate(Alias.LAST_UPDATE_DATE) + ", \n";
      sql += join.setCreatedBy(Alias.CREATED_BY) + ", \n";
      sql += join.setLastUpdatedBy(Alias.LAST_UPDATED_BY) + ", \n";
      sql += join.setAggregationLevel(Alias.AGGREGATION_LEVEL) + ", \n";
      sql += join.setSprayDate(Alias.SPRAY_DATE) + ", \n";
      sql += join.setPlannedDate(Alias.PLANNED_DATE) + ", \n";
      sql += join.setTargetWeek(Alias.TARGET_WEEK) + ", \n";
      sql += join.setGeoEntity(Alias.GEO_ENTITY) + ", \n";
      sql += join.setSprayOperator(Alias.SPRAY_OPERATOR) + ", \n";
      sql += join.setSprayTeam(Alias.SPRAY_TEAM) + ", \n";
      sql += join.setSprayMethod(Alias.SPRAY_METHOD) + ", \n";
      sql += join.setSurfaceType(Alias.SURFACE_TYPE) + ", \n";
      sql += join.setBrand(Alias.BRAND) + ", \n";
      sql += join.setOperatorActualTarget(Alias.OPERATOR_ACTUAL_TARGET) + ", \n";
      sql += join.setTeamActualTarget(Alias.TEAM_ACTUAL_TARGET) + ", \n";
      sql += join.setOperatorPlannedTarget(Alias.OPERATOR_PLANNED_TARGET) + ", \n";
      sql += join.setTeamPlannedTarget(Alias.TEAM_PLANNED_TARGET) + ", \n";
      sql += join.setAreaPlannedTarget(Alias.AREA_PLANNED_TARGET) + ", \n";
      sql += join.setSprayOperatorDefaultLocale(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE) + ", \n";
      sql += join.setSprayTeamDefaultLocale(Alias.SPRAY_TEAM_DEFAULT_LOCALE) + ", \n";
      sql += join.setSprayLeaderDefaultLocale(Alias.SPRAY_LEADER_DEFAULT_LOCALE) + ", \n";
      sql += join.setZoneSuperVisorDefaultLocale(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE) + ", \n";
      sql += join.setStructureId(Alias.STRUCTURE_ID) + ", \n";
      sql += join.setHouseholdId(Alias.HOUSEHOLD_ID) + ", \n";
      sql += join.setSpraySeason(Alias.SPRAY_SEASON) + ", \n";
      sql += join.setRooms(Alias.ROOMS) + ", \n";
      sql += join.setStructures(Alias.STRUCTURES) + ", \n";
      sql += join.setHouseholds(Alias.HOUSEHOLDS) + ", \n";
      sql += join.setSprayedRooms(Alias.SPRAYED_ROOMS) + ", \n";
      sql += join.setSprayedStructures(Alias.SPRAYED_STRUCTURES) + ", \n";
      sql += join.setSprayedHouseholds(Alias.SPRAYED_HOUSEHOLDS) + ", \n";
      sql += join.setPrevSprayedStructures(Alias.PREV_SPRAYED_STRUCTURES) + ", \n";
      sql += join.setPrevSprayedHouseholds(Alias.PREV_SPRAYED_HOUSEHOLDS) + ", \n";
      sql += join.setPeople(Alias.PEOPLE) + ", \n";
      sql += join.setBedNets(Alias.BEDNETS) + ", \n";
      sql += join.setRoomsWithBedNets(Alias.ROOMS_WITH_BED_NETS) + ", \n";
      sql += join.setLocked(Alias.LOCKED) + ", \n";
      sql += join.setRefused(Alias.REFUSED) + ", \n";
      sql += join.setOther(Alias.OTHER) + ", \n";
      sql += join.setWrongSurface(Alias.WRONG_SURFACE) + ", \n";
      sql += join.setDisease(Alias.DISEASE) + ", \n";
      sql += join.setReceived(Alias.RECEIVED) + ", \n";
      sql += join.setUsed(Alias.USED) + ", \n";
      sql += join.setRefills(Alias.REFILLS) + ", \n";
      sql += join.setReturned(Alias.RETURNED) + ", \n";
      sql += join.setRoomUnsprayed(Alias.ROOMS_UNSPRAYED) + ", \n";
      sql += join.setStructureUnsprayed(Alias.STRUCTURES_UNSPRAYED) + ", \n";
      sql += join.setHouseholdUnsprayed(Alias.HOUSEHOLDS_UNSPRAYED) + ", \n";
      sql += join.setSprayedRoomsShare(Alias.SPRAYED_ROOMS_SHARE) + ", \n";
      sql += join.setSprayedStructuresShare(Alias.SPRAYED_STRUCTURES_SHARE) + ", \n";
      sql += join.setSprayedHouseholdsShare(Alias.SPRAYED_HOUSEHOLDS_SHARE) + " \n";

      String from = join.from();
      if (from.length() > 0)
      {
        sql += "FROM \n  " + from + " \n";
      }

      String where = join.where();
      if (where.length() > 0)
      {
        sql += "WHERE \n  " + where;
      }

      if (count < joins.size() - 1)
      {
        sql += "\nUNION\n";
      }

      count++;
    }

    return sql;
  }

  private String getUnionSQL(PlannedTargetUnion union)
  {
    String sql = "SELECT \n";
    sql += union.setId(Alias.ID) + ", \n";
    sql += union.setUniqueSprayId(Alias.UNIQUE_SPRAY_ID) + ", \n";
    sql += union.setUniquePlannedId(Alias.UNIQUE_PLANNED_ID) + ", \n";
    sql += union.setPlannedDate(Alias.PLANNED_DATE) + ", \n";
    sql += union.setTargetWeek(Alias.TARGET_WEEK) + ", \n";
    sql += union.setGeoEntity(Alias.GEO_ENTITY) + ", \n";
    sql += union.setSprayOperator(Alias.SPRAY_OPERATOR) + ", \n";
    sql += union.setSprayTeam(Alias.SPRAY_TEAM) + ", \n";
    sql += union.setOperatorPlannedTarget(Alias.OPERATOR_PLANNED_TARGET) + ", \n";
    sql += union.setTeamPlannedTarget(Alias.TEAM_PLANNED_TARGET) + ", \n";
    sql += union.setAreaPlannedTarget(Alias.AREA_PLANNED_TARGET) + ", \n";
    sql += union.setSprayOperatorDefaultLocale(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE) + ", \n";
    sql += union.setSprayTeamDefaultLocale(Alias.SPRAY_TEAM_DEFAULT_LOCALE) + ", \n";
    sql += union.setSpraySeason(Alias.SPRAY_SEASON) + ", \n";
    sql += union.setDisease(Alias.DISEASE) + " \n";

    String from = union.from();
    if (from.length() > 0)
    {
      sql += "FROM \n  " + from + " \n";
    }

    String where = union.where();
    if (where.length() > 0)
    {
      sql += "WHERE \n  " + where;
    }

    return sql;
  }

  /**
   * Combines the union columns in the correct order and returns the SQL.
   * 
   * @param union
   * @return
   */
  private String getUnionSQL(ActualTargetUnion union)
  {
    String sql = "SELECT \n";
    sql += union.setId(Alias.ID) + ", \n";
    sql += union.setUniqueSprayId(Alias.UNIQUE_SPRAY_ID) + ", \n";
    sql += union.setUniquePlannedId(Alias.UNIQUE_PLANNED_ID) + ", \n";
    sql += union.setCreateDate(Alias.CREATE_DATE) + ", \n";
    sql += union.setLastUpdateDate(Alias.LAST_UPDATE_DATE) + ", \n";
    sql += union.setCreatedBy(Alias.CREATED_BY) + ", \n";
    sql += union.setLastUpdatedBy(Alias.LAST_UPDATED_BY) + ", \n";
    sql += union.setAggregationLevel(Alias.AGGREGATION_LEVEL) + ", \n";
    sql += union.setSprayDate(Alias.SPRAY_DATE) + ", \n";
    sql += union.setTargetWeek(Alias.TARGET_WEEK) + ", \n";
    sql += union.setGeoEntity(Alias.GEO_ENTITY) + ", \n";
    sql += union.setSprayOperator(Alias.SPRAY_OPERATOR) + ", \n";
    sql += union.setSprayTeam(Alias.SPRAY_TEAM) + ", \n";
    sql += union.setSprayMethod(Alias.SPRAY_METHOD) + ", \n";
    sql += union.setSurfaceType(Alias.SURFACE_TYPE) + ", \n";
    sql += union.setBrand(Alias.BRAND) + ", \n";
    sql += union.setOperatorActualTarget(Alias.OPERATOR_ACTUAL_TARGET) + ", \n";
    sql += union.setTeamActualTarget(Alias.TEAM_ACTUAL_TARGET) + ", \n";
    sql += union.setSprayOperatorDefaultLocale(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE) + ", \n";
    sql += union.setSprayTeamDefaultLocale(Alias.SPRAY_TEAM_DEFAULT_LOCALE) + ", \n";
    sql += union.setSprayLeaderDefaultLocale(Alias.SPRAY_LEADER_DEFAULT_LOCALE) + ", \n";
    sql += union.setZoneSuperVisorDefaultLocale(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE) + ", \n";
    sql += union.setStructureId(Alias.STRUCTURE_ID) + ", \n";
    sql += union.setHouseholdId(Alias.HOUSEHOLD_ID) + ", \n";
    sql += union.setSpraySeason(Alias.SPRAY_SEASON) + ", \n";
    sql += union.setRooms(Alias.ROOMS) + ", \n";
    sql += union.setStructures(Alias.STRUCTURES) + ", \n";
    sql += union.setHouseholds(Alias.HOUSEHOLDS) + ", \n";
    sql += union.setSprayedRooms(Alias.SPRAYED_ROOMS) + ", \n";
    sql += union.setSprayedStructures(Alias.SPRAYED_STRUCTURES) + ", \n";
    sql += union.setSprayedHouseholds(Alias.SPRAYED_HOUSEHOLDS) + ", \n";
    sql += union.setPrevSprayedStructures(Alias.PREV_SPRAYED_STRUCTURES) + ", \n";
    sql += union.setPrevSprayedHouseholds(Alias.PREV_SPRAYED_HOUSEHOLDS) + ", \n";
    sql += union.setPeople(Alias.PEOPLE) + ", \n";
    sql += union.setBedNets(Alias.BEDNETS) + ", \n";
    sql += union.setRoomsWithBedNets(Alias.ROOMS_WITH_BED_NETS) + ", \n";
    sql += union.setLocked(Alias.LOCKED) + ", \n";
    sql += union.setRefused(Alias.REFUSED) + ", \n";
    sql += union.setOther(Alias.OTHER) + ", \n";
    sql += union.setWrongSurface(Alias.WRONG_SURFACE) + ", \n";
    sql += union.setDisease(Alias.DISEASE) + ", \n";
    sql += union.setReceived(Alias.RECEIVED) + ", \n";
    sql += union.setUsed(Alias.USED) + ", \n";
    sql += union.setRefills(Alias.REFILLS) + ", \n";
    sql += union.setReturned(Alias.RETURNED) + ", \n";
    sql += union.setRoomUnsprayed(Alias.ROOMS_UNSPRAYED) + ", \n";
    sql += union.setStructureUnsprayed(Alias.STRUCTURES_UNSPRAYED) + ", \n";
    sql += union.setHouseholdUnsprayed(Alias.HOUSEHOLDS_UNSPRAYED) + ", \n";
    sql += union.setSprayedRoomsShare(Alias.SPRAYED_ROOMS_SHARE) + ", \n";
    sql += union.setSprayedStructuresShare(Alias.SPRAYED_STRUCTURES_SHARE) + ", \n";
    sql += union.setSprayedHouseholdsShare(Alias.SPRAYED_HOUSEHOLDS_SHARE) + " \n";

    String from = union.from();
    if (from.length() > 0)
    {
      sql += "FROM \n  " + from + " \n";
    }

    String where = union.where();
    if (where.length() > 0)
    {
      sql += "WHERE \n  " + where;
    }

    return sql;
  }

  private String generateEpiWeekSeriesView(boolean isResource)
  {
    String malariaSeasonTable = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS).getTableName();
    String startDate = QueryUtil.getColumnName(MalariaSeason.getStartDateMd());
    String endDate = QueryUtil.getColumnName(MalariaSeason.getEndDateMd());
    String disease = QueryUtil.getColumnName(MalariaSeason.getDiseaseMd());

    String diseaseCol;
    String seasonCol;
    if (isResource)
    {
      diseaseCol = QueryUtil.getColumnName(ResourceTarget.getDiseaseMd());
      seasonCol = QueryUtil.getColumnName(ResourceTarget.getSeasonMd());
    }
    else
    {
      diseaseCol = QueryUtil.getColumnName(GeoTarget.getDiseaseMd());
      seasonCol = QueryUtil.getColumnName(GeoTarget.getSeasonMd());
    }

    String weeks = "";
    for (int i = 0; i < EpiWeek.NUMBER_OF_WEEKS; i++)
    {
      weeks += "target_" + i + ",";
      if (i % 10 == 0)
        weeks += "\n";
    }
    weeks = weeks.substring(0, weeks.length() - 1);

    String select = "SELECT tar." + idCol + " AS " + idCol + ",\n";
    select += "tar."+keyName+" AS "+keyName+",\n";
    select += "de." + Alias.PLANNED_DATE + " AS " + Alias.PLANNED_DATE + ", \n";
    select += "i AS " + Alias.TARGET_WEEK + ",\n";
    if (isResource)
    {
      select += this.targeter + " AS " + this.targeter + ", \n";
    }
    else
    {
      select += this.geoEntity + " AS " + this.geoEntity + ", \n";
    }

    select += "ms." + idCol + " AS " + MALARIA_SEASON + ", \n";

    select += "tar." + diseaseCol + " AS " + PLANNED_TARGET_DISEASE + ", \n";

    select += "target_array[i] AS " + WEEKLY_TARGET + " \n";

    String from = "FROM ";
    from += "(SELECT id, ";
    from += "key_name, ";
    if (isResource)
    {
      from += this.targeter + ", ";
    }
    else
    {
      from += this.geoEntity + ", ";
    }
    from += diseaseCol + ", ";
    from += seasonCol + ", ";

    String sourceTable;
    if (isResource)
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(ResourceTarget.CLASS).getTableName();
    }
    else
    {
      sourceTable = MdEntityDAO.getMdEntityDAO(GeoTarget.CLASS).getTableName();
    }

    from += "ARRAY[" + weeks + "] AS target_array FROM " + sourceTable + ") AS tar ";
    from += "CROSS JOIN generate_series(1, " + ( EpiWeek.NUMBER_OF_WEEKS ) + ") AS i, "
        + DATE_EXTRAPOLATION + " de, " + malariaSeasonTable + " ms \n";
    from += " WHERE target_array[i] IS NOT NULL \n";
    from += " AND (i-1) = de." + periodCol + " \n"; // substract 1 because arrays in postgres are 1-based
    from += " AND de." + Alias.PLANNED_DATE + " BETWEEN ms." + startDate + " AND ms." + endDate + " \n";
    from += " AND ms." + disease + " = '" + this.diseaseId + "' \n";
    from += " AND ms." + disease + " = tar." + diseaseCol + "\n";
    from += " AND ms." + idCol + " = tar." + seasonCol + "\n";
    return select + from;
  }

  private String getGeoTargetView()
  {
    return generateEpiWeekSeriesView(false);
  }

  private String getTargetResourceView()
  {
    return generateEpiWeekSeriesView(true);
  }

  private String getInsecticideView()
  {
//    MdEntityDAOIF insectNozzleMd = MdEntityDAO.getMdEntityDAO(InsecticideNozzle.CLASS);
//    String insectNozzleTable = insectNozzleMd.getTableName();
//    String configDateCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.CONFIGURATIONDATE);
//    String enabledCol = QueryUtil.getColumnName(insectNozzleMd, InsecticideNozzle.ENABLED);

    MdEntityDAOIF enumMasterMD = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String enumMasterTable = enumMasterMD.getTableName();
    String enumNameCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.ENUMNAME);
    String disLabelCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.DISPLAYLABEL);

    MdEntityDAOIF disLabelMd = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String disLabelTable = disLabelMd.getTableName();
    String defaultLocaleCol = QueryUtil.getColumnName(disLabelMd, MetadataDisplayLabel.DEFAULTLOCALE);

    MdEntityDAOIF insectBrandMd = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS);
    String insectBrandTable = insectBrandMd.getTableName();
    String unitsPerApplicationCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.UNITSPERAPPLICATION);
    String unitQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITQUANTIFIER);
    String concentrationQuantifierCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.CONCENTRATIONQUANTIFIER);
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

//    MdEntityDAOIF nozzleMd = MdEntityDAO.getMdEntityDAO(Nozzle.CLASS);
//    String nozzleTable = nozzleMd.getTableName();
//    String ratioCol = QueryUtil.getColumnName(nozzleMd, Nozzle.RATIO);
//    String nozzleDisLabelCol = QueryUtil.getColumnName(nozzleMd, Nozzle.DISPLAYLABEL);

    MdEntityDAOIF areaStandardsMd = MdEntityDAO.getMdEntityDAO(AreaStandards.CLASS);
    String areaStandardsTable = areaStandardsMd.getTableName();
    String structureAreaCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.STRUCTUREAREA);
    String roomCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.ROOM);
    String householdCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.HOUSEHOLD);
    String unitAreaNozzleCovCol = QueryUtil.getColumnName(areaStandardsMd,
        AreaStandards.UNITNOZZLEAREACOVERAGE);
    String targetUnitCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.TARGETUNIT);

    // id (brand)
    String select = "SELECT " + insectBrandTable + ".id,\n";
    
    // start date
    select += "COALESCE(start_date,'1900-01-01'::date) start_date,\n";
    
    // disease
    select += insectBrandTable + "." + diseaseCol + " disease,\n";
    
    // end date
    select += "COALESCE(end_Date,'2100-01-01'::date) end_date, \n";
    
    /* removed for #2826
    // nozzle start
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE "
        + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i."
        + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i."
        + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol
        + " < i." + configDateCol + " ORDER BY i." + configDateCol
        + " DESC LIMIT 1 ),'1900-01-01'::date) nozzleStart, \n";
    */
    
    /* removed for #2826
    // nozzle end
    select += "COALESCE((SELECT i." + configDateCol + " FROM " + insectNozzleTable + " i WHERE "
        + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i."
        + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";
    select += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i."
        + RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." + configDateCol
        + " > i." + configDateCol + "  ORDER BY i." + configDateCol
        + " ASC LIMIT 1),'2100-01-01'::date) nozzleEnd, \n";
    */
    
    // removed unitsPerApplicationCol * ratio for ticket #2826
    select += "" + unitQuantifierCol + "*"// + unitsPerApplicationCol + "*ratio*"
        + "("+concentrationQuantifierCol + "/100.0) AS active_ingredient_per_can,\n";
    
    /* removed for #2826
    // nozzle ratio
    select += "" + nozzleTable + "." + ratioCol + " AS nozzle_ratio,\n";
    */
    
    /* removed for #2826
    // nozzle default locale
    select += "" + nozzleTable + "." + nozzleDisLabelCol + " AS nozzle_defaultLocale,\n";
    */
    
    /* removed for #2826
    // enabled
    select += "" + insectNozzleTable + "." + enabledCol + ",\n";
    */
    
    // spray unit
    select += "" + enumNameCol + " spray_unit,\n";
    
    // target unit display label
    select += "(SELECT " + defaultLocaleCol + " FROM " + disLabelTable + " md WHERE " + enumMasterTable
        + "." + disLabelCol + " = md.id) targetUnit_displayLabel,\n";

    // unit area
    select += "(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol
        + "  WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol
        + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol
        + " END ) AS "+UNITAREA+",\n";
    
    // standard application rate
    select += "" + unitAreaNozzleCovCol + " " + unitAreaNozzleCovCol + ",\n";
    select += "((" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " )  AS standard_application_rate,\n";
    
    // standard application rate mg
    select += "(1000.0 * (" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " ) AS standard_application_rate_mg,\n";
    
    // units per can (removed ratio per #2826)
    select += /*"ratio * " + */unitAreaNozzleCovCol + "/(CASE WHEN " + enumNameCol + " = '"
        + TargetUnit.ROOM.name() + "' THEN " + roomCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS units_per_can\n";

    String from = "FROM ";
    from += areaStandardsTable + " AS " + areaStandardsTable + ",\n";
    from += insectBrandTable + " AS " + insectBrandTable + ",\n";
    
    /* removed for #2826
    from += nozzleTable + " AS " + nozzleTable + ",\n";
    from += insectNozzleTable + " AS " + insectNozzleTable + "\n,";
    */
    from += "" + enumMasterTable + " " + enumMasterTable + "\n";

    String where = "WHERE \n";
    
    /* removed for #2826
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = "
        + insectBrandTable + ".id \n";
    where += "AND " + insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + nozzleTable
        + ".id \n";
        */
    where += enumMasterTable + ".id = " + targetUnitCol + "_c \n";

    return select + "\n" + from + "\n" + where;
  }

  // FIXME make sure this works with multiple universals
  private void getGeoType()
  {
    if (this.smallestUnivesal != null)
    {
      // calculation already done--do nothing
      return;
    }

    String attrib = AbstractSpray.CLASS + "." + AbstractSpray.GEOENTITY;

    try
    {
      String attributeKey = null;

      String[] selectedUniversals = null;

      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        attributeKey = (String) keys.next();

        JSONArray universals = selectedUniMap.getJSONArray(attributeKey);
        if (universals.length() > 0 && attributeKey.equals(attrib))
        {
          selectedUniversals = new String[universals.length()];
          for (int i = 0; i < universals.length(); i++)
          {
            selectedUniversals[i] = universals.getString(i);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          this.smallestUnivesal = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          this.smallestUniversalSelectable = this.smallestUnivesal.substring(
              this.smallestUnivesal.lastIndexOf('.')).toLowerCase();
          this.smallestUniversalSelectable = attributeKey + '.' + this.smallestUniversalSelectable + '.'
              + GeoEntity.GEOID;
          this.smallestUniversalSelectable = this.smallestUniversalSelectable.replace('.', '_');
        }
      }
    }

    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
}
