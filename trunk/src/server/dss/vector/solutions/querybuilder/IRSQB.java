package dss.vector.solutions.querybuilder;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.AttributeDate;
import com.runwaysdk.query.AttributeDateTime;
import com.runwaysdk.query.AttributeTime;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableDecimal;
import com.runwaysdk.query.SelectableDouble;
import com.runwaysdk.query.SelectableFloat;
import com.runwaysdk.query.SelectableInteger;
import com.runwaysdk.query.SelectableLong;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.SelectableSQLDateTime;
import com.runwaysdk.query.SelectableSQLLong;
import com.runwaysdk.query.SelectableSQLTime;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.Metadata;
import com.sun.tools.javac.util.Pair;

import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.AbstractSprayQuery;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.InsecticideBrandQuery;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.irs.ActivityUnion;
import dss.vector.solutions.querybuilder.irs.Alias;
import dss.vector.solutions.querybuilder.irs.ColumnDependency;
import dss.vector.solutions.querybuilder.irs.CriteriaInterceptor;
import dss.vector.solutions.querybuilder.irs.DateExtrapolationView;
import dss.vector.solutions.querybuilder.irs.DiseaseSelectableWrapper;
import dss.vector.solutions.querybuilder.irs.GeoTargetView;
import dss.vector.solutions.querybuilder.irs.InsecticideView;
import dss.vector.solutions.querybuilder.irs.PlannedAreaTarget;
import dss.vector.solutions.querybuilder.irs.PlannedOperatorTarget;
import dss.vector.solutions.querybuilder.irs.PlannedSprayTeamResults;
import dss.vector.solutions.querybuilder.irs.PlannedSprayTeamRollup;
import dss.vector.solutions.querybuilder.irs.PlannedSprayTeamTarget;
import dss.vector.solutions.querybuilder.irs.ResourceTargetView;
import dss.vector.solutions.querybuilder.irs.SQLProvider;
import dss.vector.solutions.querybuilder.irs.SpraySummaryView;
import dss.vector.solutions.querybuilder.irs.SprayView;
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
   * Set to true if any of the planned targets are included in the query.
   */
  private boolean                           hasPlannedTargets;

  private boolean                           hasSprayEnumOrTerm;

  private ValueQuery                        irsVQ;

  private ValueQuery                        sprayVQ;

  private JSONObject                        queryConfig;

  public static final String                SPRAYED_ROOMS_SUM          = "sprayedRoomsSum";

  public static final String                SPRAYED_STRUCTURES_SUM     = "sprayedStructuresSum";

  public static final String                SPRAYED_HOUSEHOLDS_SUM     = "sprayedHouseholdsSum";

  public static final String                OPERATOR_SPRAY_ID          = "operatorSprayId";

  public static final String                PLANNED_TARGET_DISEASE     = "disease";

  public static final String                ORIGINAL_ID                = "original_id";

  public static final String                TARGET_WEEK                = "target_week";

  public static final String                WEEKLY_TARGET              = "weekly_target";

  public static final String                MALARIA_SEASON             = "malariaSeasonId";

  private AbstractSprayQuery                abstractSprayQuery;

  private InsecticideBrandQuery             insecticideQuery;

  private InsecticideBrandQuery             outerInsecticideQuery;

  private CriteriaInterceptor               criteriaInterceptor;

  private String                            sprayViewAlias;

  private String                            idCol;

  private String                            keyName;

  private String                            sprayedUnits;

  private ValueQuery                        insecticideVQ;

  private String                            targeter;

  // private String mSeasonCol;

  private String                            geoEntity;

  private String                            periodCol;

  private int                               startDay;

  private String                            diseaseId;

  private QueryFactory                      queryFactory;

  private ValueQueryParser                  irsParser;

  private ValueQueryParser                  insecticideParser;

  private ValueQueryParser                  sprayParser;

  private DateCriteria                      dateCriteria;

  /**
   * A Set of all the select aliases that are used in the query.
   */
  private Set<Alias>                        selectAliases;

  /**
   * A set of all dependencies
   */
  private Map<String, ColumnDependency> dependenciesByKey;
  
  private Map<String, List<ColumnDependency>> dependenciesByProvider;
  
  private Map<View, Set<Alias>> requiredAliases;

  /**
   * Specifies which type of date criteria was added (and optionally selected).
   */
  private enum DateCriteria implements Reloadable {
    PERSON_BIRTHDATE, SPRAY_DATE, NONE
  }

  /**
   * All views in the query. PRESERVE THE ORDER as values() returns them as
   * declared, which is helpful for keeping the order deterministic.
   */
  public enum View implements Reloadable {

    DATE_EXTRAPOLATION_VIEW("dateExtrapolationView", DateExtrapolationView.class),
    RESOURCE_TARGET_VIEW("resourceTargetView", ResourceTargetView.class),
    GEO_TARGET_VIEW("geoTargetView",GeoTargetView.class),
    SPRAY_SUMMARY_VIEW("spraySummaryView", SpraySummaryView.class),
    INSECTICIDE_VIEW("insecticideView", InsecticideView.class), 
    PLANNED_OPERATOR("plannedOperator", PlannedOperatorTarget.class), 
    PLANNED_TEAM("plannedTeam", PlannedSprayTeamTarget.class), 
    PLANNED_TEAM_ROLLUP("plannedTeamRollup", PlannedSprayTeamRollup.class),
    PLANNED_TEAM_RESULTS("plannedTeamResults",PlannedSprayTeamResults.class), 
    PLANNED_AREA("plannedArea", PlannedAreaTarget.class), 
    ALL_ACTUALS("allActuals", ActivityUnion.class), 
    SPRAY_VIEW("sprayView", SprayView.class);

    private String                       view;

    private Class<? extends SQLProvider> klass;
    
    private View(String view, Class<? extends SQLProvider> klass)
    {
      this.view = view;
      this.klass = klass;
    }

    @Override
    public String toString()
    {
      return this.getView();
    }

    public String getView()
    {
      return view;
    }

    public Class<? extends SQLProvider> getSQLProviderClass()
    {
      return klass;
    }
  }

  private Set<View> requiredViews;

  public IRSQB(String config, String xml, Layer layer, Integer pageNumber, Integer pageSize)
  {
    super(xml, config, layer, pageSize, pageSize);

    this.setWITHRecursive(true);
    
    this.requiredAliases = new HashMap<View, Set<Alias>>();

    /*
         DATE_EXTRAPOLATION_VIEW("dateExtrapolationView", DateExtrapolationView.class),
    RESOURCE_TARGET_VIEW("resourceTargetView", ResourceTargetView.class),
    GEO_TARGET_VIEW("geoTargetView",GeoTargetView.class),
    SPRAY_SUMMARY_VIEW("spraySummaryView", SpraySummaryView.class),
    INSECTICIDE_VIEW("insecticideView", InsecticideView.class), 
    PLANNED_OPERATOR("plannedOperator", PlannedOperatorTarget.class), 
    PLANNED_TEAM("plannedTeam", PlannedSprayTeamTarget.class), 
    PLANNED_TEAM_ROLLUP("plannedTeamRollup", PlannedSprayTeamRollup.class),
    PLANNED_TEAM_RESULTS("plannedTeamResults",PlannedSprayTeamResults.class), 
    PLANNED_AREA("plannedArea", PlannedAreaTarget.class), 
    ALL_ACTUALS("allActuals", ActivityUnion.class), 
    SPRAY_VIEW("sprayView", SprayView.class); 
     */
    
    // manually put sets with different Views. Note that some views share the same
    // sets (to make the unions work by having the same columns).
    this.requiredAliases.put(View.DATE_EXTRAPOLATION_VIEW, new HashSet<Alias>());
    this.requiredAliases.put(View.RESOURCE_TARGET_VIEW, new HashSet<Alias>());
    this.requiredAliases.put(View.GEO_TARGET_VIEW, new HashSet<Alias>());
    this.requiredAliases.put(View.SPRAY_SUMMARY_VIEW, new HashSet<Alias>());
    this.requiredAliases.put(View.INSECTICIDE_VIEW, new HashSet<Alias>());
    
    // planned targets share the same set so they can be unioned.
    Set<Alias> plannedSet = new HashSet<Alias>();
    this.requiredAliases.put(View.PLANNED_OPERATOR, plannedSet);
    this.requiredAliases.put(View.PLANNED_TEAM, plannedSet);
    this.requiredAliases.put(View.PLANNED_TEAM_ROLLUP, plannedSet);
    this.requiredAliases.put(View.PLANNED_TEAM_RESULTS, plannedSet);
    this.requiredAliases.put(View.PLANNED_AREA, plannedSet);
    
    this.requiredAliases.put(View.ALL_ACTUALS, new HashSet<Alias>());

    this.requiredAliases.put(View.SPRAY_VIEW, new HashSet<Alias>());

    
    // The sprayView is always required
    this.requiredViews = new HashSet<View>();
    this.requiredViews.add(View.SPRAY_VIEW);
    // FIXME default to having activity included (what if they're only querying planned)?
    this.requiredViews.add(View.ALL_ACTUALS);

    
    this.selectAliases = new HashSet<Alias>();
    
    this.dependenciesByKey = new LinkedHashMap<String, ColumnDependency>();
    this.dependenciesByProvider = new LinkedHashMap<String, List<ColumnDependency>>();

    this.smallestUnivesal = null;

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

    this.dateCriteria = DateCriteria.NONE;

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

    this.criteriaInterceptor = new CriteriaInterceptor(this.irsVQ);
  }

  /**
   * Add the count selectable if it's not added. This is a looser restriction
   * than other QBs because we always want the count selectable hack.
   */
//  protected boolean enableCountSelectable(ValueQuery v)
//  {
//    return !v.hasSelectableRef(WINDOW_COUNT_ALIAS);
//  }
  
  public void addRequiredView(View view)
  {
    this.requiredViews.add(view);
  }
  
  public void addDependency(ColumnDependency dependency)
  {
    this.dependenciesByKey.put(dependency.getKey(), dependency);

    String provider = dependency.getProvider();
    if(!this.dependenciesByProvider.containsKey(provider))
    {
      this.dependenciesByProvider.put(provider, new LinkedList<ColumnDependency>());
    }
    
    this.dependenciesByProvider.get(provider).add(dependency);
  }

  public void addRequiredAlias(View view, Alias ... alias)
  {
    for(Alias a : alias)
    {
      this.requiredAliases.get(view).add(a);
    }
  }
  
  public Set<Alias> getRequiredAlias(View view)
  {
    return this.requiredAliases.get(view);
  }

  /**
   * Returns a list of all aliases used in the final SELECT clause.
   * 
   * @return
   */
  public Set<Alias> getSelectAliases()
  {
    return this.selectAliases;
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

  public String getPeriodCol()
  {
    return periodCol;
  }

  public int getStartDay()
  {
    return startDay;
  }

  public String getIdCol()
  {
    return idCol;
  }

  public String getKeyName()
  {
    return keyName;
  }

  public boolean hasLevel1()
  {
    return this.criteriaInterceptor.hasLevel1();
  }

  public boolean hasLevel2()
  {
    return this.criteriaInterceptor.hasLevel2();
  }

  public boolean hasLevel3()
  {
    return this.criteriaInterceptor.hasLevel3();
  }

  public boolean needsAreaPlanned()
  {
    return this.needsAreaPlanned;
  }

  public boolean needsTeamsPlanned()
  {
    return this.needsTeamsPlanned;
  }

  public boolean needsOperatorPlanned()
  {
    return this.needsOperatorPlanned;
  }

  public boolean hasPlannedTargets()
  {
    return this.hasPlannedTargets;
  }

  @Override
  protected Map<String, AliasPair> getAliasPairs()
  {
    Map<String, AliasPair> pairs = super.getAliasPairs();

    // The sex attribute across supervisor, leader, and operator require custom
    // overrides
    String operatorSex = this.createAllPathsEntityAlias(Alias.SPRAY_OPERATOR_SEX.getAlias(),
        Person.CLASS, Alias.SPRAY_OPERATOR_SEX.getAlias());
    AliasPair operatorSexPair = new AliasPair(this.sprayViewAlias, Alias.SPRAY_OPERATOR_SEX.getAlias());
    pairs.put(operatorSex, operatorSexPair);

    String leaderSex = this.createAllPathsEntityAlias(Alias.SPRAY_LEADER_SEX.getAlias(), Person.CLASS,
        Alias.SPRAY_LEADER_SEX.getAlias());
    AliasPair leaderSexPair = new AliasPair(this.sprayViewAlias, Alias.SPRAY_LEADER_SEX.getAlias());
    pairs.put(leaderSex, leaderSexPair);

    String supervisorSex = this.createAllPathsEntityAlias(Alias.ZONE_SUPERVISOR_SEX.getAlias(),
        Person.CLASS, Alias.ZONE_SUPERVISOR_SEX.getAlias());
    AliasPair supervisorSexPair = new AliasPair(this.sprayViewAlias,
        Alias.ZONE_SUPERVISOR_SEX.getAlias());
    pairs.put(supervisorSex, supervisorSexPair);

    return pairs;
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

    // String geoPrefix = ( AbstractSpray.CLASS + "." + AbstractSpray.GEOENTITY
    // ).replaceAll("\\.", "_");

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
    this.insecticideParser = super.createParser(this.insecticideVQ,
        this.createParseInterceptors(this.insecticideVQ));
    this.sprayParser = super.createParser(this.sprayVQ, this.createParseInterceptors(this.sprayVQ));

    return this.irsParser;
  }

  /**
   * Adds a custom Interceptor to the this.irsVQ to look for aggregation level
   * restrictions.
   */
  @Override
  protected List<ParseInterceptor> createParseInterceptors(ValueQuery valueQuery)
  {
    List<ParseInterceptor> interceptors = super.createParseInterceptors(valueQuery);

    if (valueQuery == this.irsVQ)
    {
      interceptors.add(this.criteriaInterceptor);
    }

    return interceptors;
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

    joinSexAttributes();

    // ---- START DATE CRITERIA ----
    Condition dateCond = addDateCriteria(this.sprayViewAlias, true,
        Alias.SPRAY_OPERATOR_BIRTHDATE.getAlias(), Alias.SPRAY_LEADER_BIRTHDATE.getAlias(),
        Alias.ZONE_SUPERVISOR_BIRTHDATE.getAlias());
    this.irsVQ.AND(dateCond);

    if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
    {
      // include a Person EntityQuery into the map to make the
      // QueryUtil.setQueryDate() work.
      this.mainQueryMap.put(Person.CLASS, new PersonQuery(this.queryFactory));
    }

    if (this.dateCriteria != DateCriteria.NONE)
    {
      SelectableSQL diseaseSel = irsVQ.aSQLCharacter(Alias.DISEASE.getAlias(), Alias.DISEASE.getAlias());
      DiseaseSelectableWrapper wrapper = new DiseaseSelectableWrapper(diseaseSel, this.sprayViewAlias);
      QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper);
    }
    // ---- END DATE CRITERIA ----

    if (insecticideQuery != null)
    {
      QueryUtil.joinEnumerationDisplayLabels(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      this.setNumericRestrictions(insecticideVQ, queryConfig);
    }

    // Spray Date
    QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + Alias.SPRAY_DATE);

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

    String availableUnits = "(CASE WHEN spray_unit = 'ROOM' THEN rooms  WHEN spray_unit = 'STRUCTURE' THEN structures WHEN spray_unit = 'HOUSEHOLD' THEN households END )";
    sprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN sprayedRooms  WHEN spray_unit = 'STRUCTURE' THEN sprayedStructures WHEN spray_unit = 'HOUSEHOLD' THEN sprayedHouseholds END )";
    String unsprayedUnits = "(CASE WHEN spray_unit = 'ROOM' THEN (room_unsprayed)  WHEN spray_unit = 'STRUCTURE' THEN (structure_unsprayed) WHEN spray_unit = 'HOUSEHOLD' THEN (household_unsprayed)  END )";
    String shareOfCans = "(CASE WHEN spray_unit = 'ROOM' THEN (sprayedrooms_share)  WHEN spray_unit = 'STRUCTURE' THEN (sprayedstructures_share) WHEN spray_unit = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    String uniqueSprayId = this.getUniqueSprayDetailsId();

    String unit_operational_coverage = "SUM(" + sprayedUnits + ")::float / nullif(SUM(" + availableUnits
        + "),0)";

    // #2868 - instead of -refills- we now sum on sachets -used-
    String unit_application_rate = "SUM(" + Alias.USED + "::FLOAT * " + shareOfCans
        + " * active_ingredient_per_can) / nullif(SUM(" + sprayedUnits + " * " + Alias.UNIT_AREA
        + "),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(standard_application_rate))";

    if (QueryUtil.setSelectabeSQL(irsVQ, Alias.SPRAYED_UNITS.getAlias(), sprayedUnits))
    {
      QueryUtil.setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() },
          uniqueSprayId, irsVQ, this.sprayViewAlias, true, true);
    }

    if (QueryUtil.setSelectabeSQL(irsVQ, "unit_unsprayed", unsprayedUnits))
    {
      QueryUtil.setAttributesAsAggregated(new String[] { "unit_unsprayed" }, uniqueSprayId, irsVQ, null,
          true, true);
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
          SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(group);
          String original = sel.getSQL();

          String sprayDateCol = sprayViewAlias + "." + Alias.SPRAY_DATE.getAlias();
          String plannedDateCol = sprayViewAlias + "." + Alias.PLANNED_DATE.getAlias();

          String sql;
          if (group.equals(QueryUtil.DATEGROUP_SEASON))
          {
            // show the season matching for either the planned target or spray
            // activity
            String plannedReplace = original.replaceAll(
                sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), plannedDateCol);
            String sprayReplace = original.replaceAll(
                sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), sprayDateCol);

            sql = "CASE WHEN " + plannedDateCol + " IS NOT NULL THEN " + plannedReplace + " WHEN "
                + sprayDateCol + " IS NOT NULL THEN " + sprayReplace + " ELSE NULL END";
          }
          else
          {
            String caseStmt = "CASE WHEN " + sprayDateCol + " IS NOT NULL THEN " + sprayDateCol
                + " ELSE " + plannedDateCol + " END";
            sql = original.replaceAll(sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), caseStmt);
          }
          sel.setSQL(sql);
        }
      }
    }

    // Note: This must go last but before joinMainQueryTables()
    setWithQuerySQL();

    joinMainQueryTables();

    return this.irsVQ;
  }

  /**
   * The sex attributes on operator, leader, and supervisor in the team details
   * requires custom logic.
   * 
   * FIXME push these into a join instead of subselect
   */
  private void joinSexAttributes()
  {
    if (irsVQ.hasSelectableRef(Alias.SPRAY_OPERATOR_SEX.getAlias()))
    {
      PersonQuery p = new PersonQuery(irsVQ);
      SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(Alias.SPRAY_OPERATOR_SEX.getAlias());
      this.leftJoinSexDisplayLabels(sel, p, Alias.SPRAY_OPERATOR_PERSON.getAlias());
    }

    if (irsVQ.hasSelectableRef(Alias.SPRAY_LEADER_SEX.getAlias()))
    {
      PersonQuery p = new PersonQuery(irsVQ);
      SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(Alias.SPRAY_LEADER_SEX.getAlias());
      this.leftJoinSexDisplayLabels(sel, p, Alias.SPRAY_LEADER_PERSON.getAlias());
    }

    if (irsVQ.hasSelectableRef(Alias.ZONE_SUPERVISOR_SEX.getAlias()))
    {
      PersonQuery p = new PersonQuery(irsVQ);
      SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(Alias.ZONE_SUPERVISOR_SEX.getAlias());
      this.leftJoinSexDisplayLabels(sel, p, Alias.ZONE_SUPERVISOR_PERSON.getAlias());
    }
  }

  /**
   * Custom deviation from QueryUtil.leftJoinTermDisplayLabels() to accommodate
   * IRS.
   * 
   * @param sexSel
   * @param p
   * @param joinAlias
   */
  public void leftJoinSexDisplayLabels(SelectableSQL sexSel, PersonQuery p, String joinAlias)
  {
    String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
    MdEntityDAOIF md = p.getMdClassIF();
    String tableName = md.getTableName();
    String sexCol = QueryUtil.getColumnName(md, Person.SEX);

    String sql = "SELECT term." + Term.NAME + "  FROM " + tableName + " as t";
    sql += " LEFT JOIN " + termTable + " as term  on t." + sexCol + " = term." + idCol;
    sql += " WHERE t." + this.idCol + " = " + joinAlias + "";

    sexSel.setSQL(sql);
  }

  @Override
  protected ValueQuery postProcess(ValueQuery valueQuery)
  {
    // incoming will be this.irsQV

    if (this.needsAreaPlanned)
    {

      // we create a new ValueQuery that wraps the original to calculate the
      // Area Targets
      ValueQuery outer = new ValueQuery(valueQuery.getQueryFactory());

      // SELECT parent_geo_entity, target_week, spray_season, disease because
      // those are
      // required for the outer Area Target calculation

      // add a reference to each selectable from the inner query to the outer
      // query
      SelectableSQL newSel;
      for (Selectable sel : valueQuery.getSelectableRefs())
      {
        // create a new Selectable based off the original type (because they are
        // custom formatted later on)
        if (sel instanceof SelectableInteger)
        {
          newSel = outer.aSQLInteger(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof SelectableLong)
        {
          newSel = outer.aSQLLong(sel.getColumnAlias(), sel.getColumnAlias(), sel.getUserDefinedAlias(),
              sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof SelectableFloat)
        {
          newSel = outer.aSQLFloat(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof SelectableDecimal)
        {
          newSel = outer.aSQLDecimal(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof SelectableDouble)
        {
          newSel = outer.aSQLDouble(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof AttributeDate || sel instanceof SelectableSQLDate)
        {
          newSel = outer.aSQLDate(sel.getColumnAlias(), sel.getColumnAlias(), sel.getUserDefinedAlias(),
              sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof AttributeDateTime || sel instanceof SelectableSQLDateTime)
        {
          newSel = outer.aSQLDateTime(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else if (sel instanceof AttributeTime || sel instanceof SelectableSQLTime)
        {
          newSel = outer.aSQLTime(sel.getColumnAlias(), sel.getColumnAlias(), sel.getUserDefinedAlias(),
              sel.getUserDefinedDisplayLabel());
        }
        else
        {
          // use character as the final default because it's flexible
          newSel = outer.aSQLCharacter(sel.getColumnAlias(), sel.getColumnAlias(),
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }

        newSel.setColumnAlias(sel.getColumnAlias());

        outer.SELECT(newSel);
        outer.GROUP_BY(newSel);
      }

      String aliasPrefix = "area_";

      String pgeAlias = aliasPrefix + Alias.PARENT_GEO_ENTITY.getAlias();
      SelectableSQL parentGeoEntity = valueQuery.aSQLCharacter(Alias.PARENT_GEO_ENTITY.getAlias(),
          this.sprayViewAlias + "." + Alias.PARENT_GEO_ENTITY.getAlias(), pgeAlias);
      parentGeoEntity.setColumnAlias(pgeAlias);

      String twAlias = aliasPrefix + Alias.TARGET_WEEK.getAlias();
      SelectableSQL targetWeek = valueQuery.aSQLCharacter(Alias.TARGET_WEEK.getAlias(),
          this.sprayViewAlias + "." + Alias.TARGET_WEEK.getAlias(), twAlias);
      targetWeek.setColumnAlias(twAlias);

      String ssAlias = aliasPrefix + Alias.SPRAY_SEASON.getAlias();
      SelectableSQL spraySeason = valueQuery.aSQLCharacter(Alias.SPRAY_SEASON.getAlias(),
          this.sprayViewAlias + "." + Alias.SPRAY_SEASON.getAlias(), ssAlias);
      spraySeason.setColumnAlias(ssAlias);

      String dAlias = aliasPrefix + Alias.DISEASE.getAlias();
      SelectableSQL disease = valueQuery.aSQLCharacter(Alias.DISEASE.getAlias(), this.sprayViewAlias
          + "." + Alias.DISEASE.getAlias(), dAlias);
      disease.setColumnAlias(dAlias);

      valueQuery.SELECT(parentGeoEntity, targetWeek, spraySeason, disease);

      // replace the Area Planned Target and Area Planned Coverage
      String func = QueryConstants.SUM_AREA_TARGETS + "(" + pgeAlias + ", to_char(" + twAlias
          + "-1, 'FM99'), " + dAlias + ", " + ssAlias + ")";

      // Do a sum to force aggregation and avoid grouping by epi-week, which
      // will break up the results
      String check = "SUM(CASE WHEN " + ssAlias + " IS NOT NULL AND " + pgeAlias + " IS NOT NULL THEN "
          + func + " ELSE NULL END)";

      if (outer.hasSelectableRef(AREA_PLANNED_TARGET))
      {
        SelectableSQL sel = (SelectableSQL) outer.getSelectableRef(AREA_PLANNED_TARGET);
        sel.setSQL(check);
      }

      if (outer.hasSelectableRef(AREA_PLANNED_COVERAGE))
      {
        if (!valueQuery.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String uniqueSprayId = this.getUniqueSprayDetailsId();
          String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

          SelectableSQL sel = valueQuery.aSQLAggregateInteger(Alias.SPRAYED_UNITS.getAlias(), sum,
              Alias.SPRAYED_UNITS.getAlias());
          valueQuery.SELECT(sel);

          SelectableSQL outerSel = outer.aSQLInteger(Alias.SPRAYED_UNITS.getAlias(),
              Alias.SPRAYED_UNITS.getAlias());
          outer.SELECT(outerSel);
          outer.GROUP_BY(outerSel);
        }

        SelectableSQL calc = (SelectableSQL) outer.getSelectableRef(AREA_PLANNED_COVERAGE);
        String sql = "((" + Alias.SPRAYED_UNITS.getAlias() + ")/NULLIF(" + check + ",0))*100.0";
        calc.setSQL(sql);
      }

      if (valueQuery.hasCountSelectable())
      {
        Selectable count = valueQuery.getCountSelectable();
        valueQuery.SELECT(count);

        String countAlias = count.getColumnAlias();
        SelectableSQLLong c = valueQuery.isGrouping() ? valueQuery.aSQLAggregateLong(WINDOW_COUNT_ALIAS,
            countAlias, WINDOW_COUNT_ALIAS) : valueQuery.aSQLLong(WINDOW_COUNT_ALIAS, countAlias,
            WINDOW_COUNT_ALIAS);

        valueQuery.setCountSelectable(null);
        outer.SELECT(c);
      }

      // restrict the inner query by the page number and size
      // so the outer query has only the results to perform
      // costly calculations
      Integer pageNumber = this.getPageNumber();
      Integer pageSize = this.getPageSize();
      if (pageNumber != null && pageSize != null)
      {
        valueQuery.restrictRows(pageSize, pageNumber);
      }

      outer.FROM("(" + valueQuery.getSQL() + ")", "original_IRS");

      return outer;
    }
    else
    {
      return valueQuery;
    }
  }

  /**
   * Some attributes must be swapped out with custom selectables
   */
  private void swapOutAttributesForAggregates()
  {
    //
    String[] insecticideAliases = new String[] {

        // insecticide usage
        Alias.RECEIVED.getAlias(), Alias.USED.getAlias(), Alias.REFILLS.getAlias(),
        Alias.RETURNED.getAlias() };

    QueryUtil.setAttributesAsAggregated(insecticideAliases, idCol, irsVQ, sprayViewAlias, true);

    String[] sprayDetails = new String[] {

        // spray details (attributes defined by HouseholdSprayStatus)
        Alias.HOUSEHOLDS.getAlias(), Alias.SPRAYED_HOUSEHOLDS.getAlias(), Alias.STRUCTURES.getAlias(),
        Alias.SPRAYED_STRUCTURES.getAlias(), Alias.ROOMS.getAlias(), Alias.SPRAYED_ROOMS.getAlias(),
        Alias.LOCKED.getAlias(), Alias.REFUSED.getAlias(), Alias.OTHER.getAlias(),
        Alias.WRONG_SURFACE.getAlias() };

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
    // return idCol+" || '_' || "+Alias.UNIQUE_SPRAY_ID.getAlias();
    return Alias.UNIQUE_SPRAY_ID.getAlias();
  }

  /**
   * Adds the custom date criteria.
   */
  public Condition addDateCriteria(String tableAlias, boolean addPlanned, String operatorDOB,
      String leaderDOB, String supervisorDOB)
  {
    try
    {
      // Use a new ValueQuery to create the Selectables to ensure there's no
      // consequences
      // on the query itself (this should not be a problem, but might as well be
      // careful).
      ValueQuery v = new ValueQuery(this.irsVQ.getQueryFactory());

      Condition cond = null;

      String prepend = tableAlias != null ? tableAlias + "." : "";

      JSONObject dateObj = queryConfig.getJSONObject(QueryUtil.DATE_ATTRIBUTE);

      if (dateObj.has(QueryUtil.DATE_CLASS) && !dateObj.isNull(QueryUtil.DATE_CLASS))
      {
        String klass = dateObj.getString(QueryUtil.DATE_CLASS);
        if (OperatorSpray.CLASS.equals(klass))
        {
          // represents spray date on all 3 levels
          this.dateCriteria = DateCriteria.SPRAY_DATE;
        }
        else if (Person.CLASS.equals(klass))
        {
          // represents birth date on operator, leader, and supervisor
          this.dateCriteria = DateCriteria.PERSON_BIRTHDATE;
        }
        else
        {
          throw new ProgrammingErrorException("The class [" + klass
              + "] is not supported as IRS date criteria.");
        }

        Condition cond1 = null;
        if (dateObj.has(QueryUtil.DATE_START) && !dateObj.isNull(QueryUtil.DATE_START)
            && !dateObj.getString(QueryUtil.DATE_START).equals("null"))
        {
          String startValue = dateObj.getString(QueryUtil.DATE_START);
          SelectableMoment startCrit = v.aSQLDate("start_val", "'" + startValue + "'");

          if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
          {
            if (operatorDOB == null)
            {
              // FIXME this is a hack for Level 3, which doesn't have operators.
              // Refactor with selectable dependencies
              cond1 = AND.get(v.aSQLDate("leader_birthdate_start", prepend + leaderDOB).GE(startCrit), v
                  .aSQLDate("supervisor_birthdate_start", prepend + supervisorDOB).GE(startCrit));
            }
            else
            {
              cond1 = AND.get(v.aSQLDate("operator_birthdate_start", prepend + operatorDOB)
                  .GE(startCrit), v.aSQLDate("leader_birthdate_start", prepend + leaderDOB)
                  .GE(startCrit),
                  v.aSQLDate("supervisor_birthdate_start", prepend + supervisorDOB).GE(startCrit));
            }
          }
          else
          {
            if (addPlanned)
            {
              cond1 = OR.get(v.aSQLDate("planned_start_date", prepend + Alias.PLANNED_DATE)
                  .GE(startCrit),
                  v.aSQLDate("spray_start_date", prepend + Alias.SPRAY_DATE).GE(startCrit));
            }
            else
            {
              cond1 = v.aSQLDate("spray_start_date", prepend + Alias.SPRAY_DATE).GE(startCrit);
            }
          }

          cond = cond1;
        }

        Condition cond2 = null;
        if (dateObj.has(QueryUtil.DATE_END) && !dateObj.isNull(QueryUtil.DATE_END)
            && !dateObj.getString(QueryUtil.DATE_END).equals("null"))
        {
          String endValue = dateObj.getString(QueryUtil.DATE_END);
          SelectableMoment endCrit = irsVQ.aSQLDate("start_val", "'" + endValue + "'");

          if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
          {
            if (operatorDOB == null)
            {
              // FIXME this is a hack for Level 3, which doesn't have operators.
              // Refactor with selectable dependencies
              cond2 = AND.get(v.aSQLDate("leader_birthdate_end", prepend + leaderDOB).LE(endCrit), v
                  .aSQLDate("supervisor_birthdate_end", prepend + supervisorDOB).LE(endCrit));
            }
            else
            {
              cond2 = AND.get(v.aSQLDate("operator_birthdate_end", prepend + operatorDOB).LE(endCrit), v
                  .aSQLDate("leader_birthdate_end", prepend + leaderDOB).LE(endCrit),
                  v.aSQLDate("supervisor_birthdate_end", prepend + supervisorDOB).LE(endCrit));
            }
          }
          else
          {
            if (addPlanned)
            {
              cond2 = OR.get(v.aSQLDate("planned_end_date", prepend + Alias.PLANNED_DATE).LE(endCrit), v
                  .aSQLDate("spray_end_date", prepend + Alias.SPRAY_DATE).LE(endCrit));
            }
            else
            {
              cond2 = v.aSQLDate("spray_end_date", prepend + Alias.SPRAY_DATE).LE(endCrit);
            }
          }

          cond = cond1 != null ? AND.get(cond1, cond2) : cond2;
        }

      }

      return cond;
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
    // String abstractSprayTable =
    // MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS).getTableName();
    // IRSSpoofJoin join = new IRSSpoofJoin(idCol, abstractSprayTable,
    // this.sprayViewAlias, idCol,
    // abstractSprayTable, this.sprayViewAlias);
    // irsVQ.AND(join);
    // irsVQ.fro

    StringBuffer str = new StringBuffer();
    String idCol = QueryUtil.getIdColumn();
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    String leftTable = View.SPRAY_VIEW.getView();
    String leftAlias = this.sprayViewAlias;
    String insecticideView = View.INSECTICIDE_VIEW.getView();

    str.append("");
    str.append(leftTable + " " + leftAlias);

    // join and restrict for the sex attribute on the spray team details

    if (irsVQ.hasSelectableRef(QueryConstants.AUDIT_IMPORTED_ALIAS))
    {
      str.append(" LEFT JOIN " + IMPORTED_DATETIME + " ON");
      str.append(" " + IMPORTED_DATETIME + "." + IMPORTED_CREATE_DATE);
      str.append(" = " + leftAlias + "." + Alias.CREATE_DATE.getAlias() + " ");
    }

    if (insecticideQuery != null)
    {
      String insecticideId = insecticideVQ.getSelectableRef(InsecticideBrand.ID).getColumnAlias();

      insecticideVQ.FROM(insecticideView, insecticideView);
      insecticideVQ.AND(insecticideVQ.aSQLCharacter("i_vq",
          insecticideQuery.getTableAlias() + "." + idCol).EQ(
          insecticideVQ.aSQLCharacter("i_view", insecticideView + "." + idCol)));

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
      irsVQ.FROM(outerInsecticideQuery.getMdClassIF().getTableName(),
          outerInsecticideQuery.getTableAlias());
      irsVQ
          .WHERE(irsVQ.aSQLCharacter("forceJoin1", outerInsecticideQuery.getId().getDbQualifiedName())
              .EQ(irsVQ.aSQLCharacter("forceJoin2", insecticideQuery.getTableAlias() + "."
                  + insecticideId)));
    }

    if (this.requiredViews.contains(View.INSECTICIDE_VIEW))
    {
      // always join on the insecticide view
      str.append(" LEFT JOIN " + insecticideView + " " + insecticideView + " ON " + leftAlias + "."
          + Alias.BRAND + " = " + insecticideView + "." + idCol + " AND \n" + insecticideView + "."
          + diseaseCol + " = " + leftAlias + "." + Alias.DISEASE + "  \n");

      // restrict by dates
      str.append("AND ((" + leftAlias + "." + Alias.SPRAY_DATE + ") >= (" + insecticideView
          + ".start_date) \n");
      str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView
          + ".end_date)) \n");
    }

    // FIXME trigger this...is it necessary? Maybe push more columns into
    // all-actuals
    if (this.hasSprayEnumOrTerm)
    {
      String sprayId = sprayVQ.getSelectableRef(AbstractSpray.ID).getColumnAlias();

      String joinType = this.hasPlannedTargets ? "LEFT JOIN" : "INNER JOIN";
      str.append(" " + joinType + " (" + sprayVQ.getSQL() + ") " + abstractSprayQuery.getTableAlias()
          + " ON " + leftAlias + "." + Alias.ID + " = " + abstractSprayQuery.getTableAlias() + "."
          + sprayId + " \n");
    }

    /*
     * removed for #2826 str.append("AND (" + leftAlias + "." + Alias.SPRAY_DATE
     * + ") >= (" + insecticideView + ".nozzleStart)) \n"); str.append("AND (" +
     * leftAlias + "." + Alias.SPRAY_DATE + ") <= (" + insecticideView +
     * ".nozzleEnd)) \n");
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

  /**
   * Sets the entire contents of the WITH clause for the query. This method MUST
   * EXECUTE LAST so that all the flags and column knowledge will be known to do
   * dependencies.
   */
  private void setWithQuerySQL()
  {
    // Loop through and collect all selectables aliases. Note that
    // other places might set aliases as well, but this is to make
    // sure the obvious Selectables are accounted for.
    List<String> notFound = new LinkedList<String>();
    for (Selectable s : this.irsVQ.getSelectableRefs())
    {
      String userDefinedAlias = s.getUserDefinedAlias(); // alias from incoming
                                                         // XML
      Alias alias = Alias.get(userDefinedAlias);
      if (alias != null)
      {
        this.selectAliases.add(alias);
      }
      else
      {
        notFound.add(userDefinedAlias);
      }
    }

    // Make sure no columns were left out
    if (notFound.size() > 0)
    {
      String msg = "The user defined aliases [" + StringUtils.join(notFound, ",")
          + "] were not found as Alias objects in the IRS QB.";
      throw new ProgrammingErrorException(msg);
    }

    // Invoke each View and load the dependencies.
    View[] views = View.values();
    List<Pair<String, SQLProvider>> viewPairs = new LinkedList<Pair<String, SQLProvider>>();
    
    // The trick is to load the dependencies in reverse order of the query.
    // This ensures the the later queries load their earlier dependencies.
    SQLProvider sprayView = null;
    for(int i=views.length-1; i>0; i--)
    {
      View view = views[i];
      if(this.requiredViews.contains(view))
      {
        Class<? extends SQLProvider> klass = view.getSQLProviderClass();

        try
        {
          Constructor<? extends SQLProvider> c = klass.getConstructor(IRSQB.class);
          SQLProvider p = c.newInstance(this);
          
          // load dependencies except for the SprayView (that's done later).
          if(view == View.SPRAY_VIEW)
          {
            sprayView = p;
          }
          else
          {
            p.loadDependencies();
          }
          
          viewPairs.add(new Pair<String, SQLProvider>(view.getView(), p));
        }
        catch (Throwable t)
        {
          String msg = "IRS QB: Unable to invoke SQLProvider [" + view + "] with class [" + klass + "].";
          throw new ProgrammingErrorException(msg, t);
        }
      }
    }
    
    // Load the sprayView dependencies last since it's dependent on 
    // every other view to be completed
    sprayView.loadDependencies();
    
    
    // Now go in the correct order of the views and print their sql
    Collections.reverse(viewPairs);
    for (Pair<String, SQLProvider> view : viewPairs)
    {
      this.addWITHEntry(new WITHEntry(view.fst, view.snd.getSQL()));
    }
  }

  private String sumOperatorActualTargets()
  {
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, OPERATOR_ACTUAL_TARGET);
  }

  private String sumTeamActualTargets()
  {
    return QueryUtil.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, TEAM_ACTUAL_TARGET);
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, Alias.TARGET_WEEK.getAlias(), sprayViewAlias,
        OPERATOR_PLANNED_TARGET);
  }

  private String sumTeamPlannedTargets()
  {
    this.needsTeamsPlanned = true;

    return QueryUtil.sumColumnForId(sprayViewAlias, Alias.TARGET_WEEK.getAlias(), sprayViewAlias,
        TEAM_PLANNED_TARGET);
  }

  private String sumAreaPlannedTargets()
  {
    this.needsAreaPlanned = true;
    return "NULL::" + Alias.AREA_PLANNED_TARGET.getType();
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
      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_TARGETED_COVERAGE);
      String sql = "((" + sum + ")/NULLIF(" + this.sumOperatorActualTargets() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_TARGETED_COVERAGE))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_TARGETED_COVERAGE);

      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      String sql = "((" + sum + ")/NULLIF(" + this.sumTeamActualTargets() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(OPERATOR_PLANNED_COVERAGE))
    {
      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(OPERATOR_PLANNED_COVERAGE);
      String sql = "((" + sum + ")/NULLIF(" + this.sumOperatorPlannedTargets() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(TEAM_PLANNED_COVERAGE))
    {
      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(TEAM_PLANNED_COVERAGE);
      String sql = "((" + sum + ")/NULLIF(" + this.sumTeamPlannedTargets() + ",0))*100.0";
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
        this.needsAreaPlanned = true;

        SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(AREA_PLANNED_COVERAGE);
        calc.setSQL("NULL::INTEGER");
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
