package dss.vector.solutions.querybuilder;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.EntityDAOIF;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeLocalCharacterDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.LeftJoin;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableAggregate;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.SelectableSQL;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.SelectableSQLLong;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ValueQueryParser;
import com.runwaysdk.query.ValueQueryParser.ParseInterceptor;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdEntity;
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
import dss.vector.solutions.ontology.TermTermDisplayLabel;
import dss.vector.solutions.query.IncidencePopulationException;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.query.QueryConstants;
import dss.vector.solutions.querybuilder.irs.ActivityUnion;
import dss.vector.solutions.querybuilder.irs.Alias;
import dss.vector.solutions.querybuilder.irs.AliasLookup;
import dss.vector.solutions.querybuilder.irs.CriteriaInterceptor;
import dss.vector.solutions.querybuilder.irs.DateExtrapolationView;
import dss.vector.solutions.querybuilder.irs.DateGroups;
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
  private static final String                   CLASS                  = "dss.vector.solutions.querybuilder.IRSQB";

  private Map<String, String>                   dategroups;

  // The smallest (most depth) universal selected in the query screen
  private String                                smallestUnivesal;

  public static final String                    AREA_PREFIX            = "area_";

  public static final String                    IMPORTED_AREA_DATETIME = "imported_area_datetime";

  // The selectable user alias of the smallest universal in the query screen
  private String                                smallestUniversalSelectable;

  // The union of actual and planned targets must include the minimum set
  // required to make the query work so as to not include superfluous rows.
  private boolean                               needsAreaPlanned;

  private boolean                               needsTeamsPlanned;

  private boolean                               needsOperatorPlanned;

  private Map<String, GeneratedTableClassQuery> mainQueryMap;

  // private Map<String, GeneratedEntityQuery> insecticideQueryMap;

  // private Map<String, GeneratedEntityQuery> abtractSprayQueryMap;

  /**
   * Set to true if any of the planned targets are included in the query.
   */
  private boolean                               hasPlannedTargets;

  private ValueQuery                            irsVQ;

  // private ValueQuery sprayVQ;

  private JSONObject                            queryConfig;

  public static final String                    SPRAYED_ROOMS_SUM      = "sprayedRoomsSum";

  public static final String                    SPRAYED_STRUCTURES_SUM = "sprayedStructuresSum";

  public static final String                    SPRAYED_HOUSEHOLDS_SUM = "sprayedHouseholdsSum";

  public static final String                    OPERATOR_SPRAY_ID      = "operatorSprayId";

  public static final String                    PLANNED_TARGET_DISEASE = "disease";

  public static final String                    ORIGINAL_ID            = "original_id";

  public static final String                    TARGET_WEEK            = "target_week";

  public static final String                    WEEKLY_TARGET          = "weekly_target";

  public static final String                    MALARIA_SEASON         = "malariaSeasonId";

  private AbstractSprayQuery                    abstractSprayQuery;

  private InsecticideBrandQuery                 insecticideQuery;

  private InsecticideBrandQuery                 outerInsecticideQuery;

  private CriteriaInterceptor                   criteriaInterceptor;

  private String                                sprayViewAlias;

  private boolean                               joinSprayView          = true;

  private String                                idCol;

  private String                                keyName;

  private String                                sprayedUnits;

  // private ValueQuery insecticideVQ;

  private String                                targeter;

  // private String mSeasonCol;

  private String                                geoEntity;

  private String                                periodCol;

  private int                                   startDay;

  private QueryFactory                          queryFactory;

  private ValueQueryParser                      irsParser;

  private ValueQueryParser                      insecticideParser;

  private ValueQueryParser                      sprayParser;

  private DateCriteria                          dateCriteria;

  private Set<String>                           universalAliases;

  /**
   * A Set of all the select aliases that are used in the query.
   */
  private Set<Alias>                            selectAliases;

  private Map<View, Set<Alias>>                 requiredAliases;

  /**
   * Columns on the parent side of a relationship.
   */
  private Set<Alias>                            parentAggregates;

  /**
   * Columns on the child side of a relationship.
   */
  private Set<Alias>                            childAggregates;

  /**
   * The audit columns.
   */
  private Set<Alias>                            audits;

  private boolean                               hasActivity;

  /**
   * Views required for area planned targets/coverage that will be on the outside of the main query.
   */
  // JN change
  // private List<Pair<String, SQLProvider>> areaPairs;

  // private boolean needUniqueSprayId;

  /**
   * True if any part of the query required spray_unit, thus the insecticide view.
   */
  private boolean                               needsSprayedUnits;

  /**
   * Specifies which type of date criteria was added (and optionally selected).
   */
  private enum DateCriteria implements Reloadable {
    PERSON_BIRTHDATE, SPRAY_DATE, NONE
  }

  /**
   * All views in the query. PRESERVE THE ORDER as values() returns them as declared, which is helpful for keeping the order deterministic.
   */
  public enum View implements Reloadable {

    DATE_EXTRAPOLATION_VIEW("dateExtrapolationView", DateExtrapolationView.CLASS), RESOURCE_TARGET_VIEW("resourceTargetView", ResourceTargetView.CLASS), GEO_TARGET_VIEW("geoTargetView", GeoTargetView.CLASS), SPRAY_SUMMARY_VIEW("spraySummaryView", SpraySummaryView.CLASS), INSECTICIDE_VIEW("insecticideView", InsecticideView.CLASS), PLANNED_OPERATOR("plannedOperator",
        PlannedOperatorTarget.CLASS), PLANNED_TEAM("plannedTeam",
            PlannedSprayTeamTarget.CLASS), PLANNED_TEAM_ROLLUP("plannedTeamRollup", PlannedSprayTeamRollup.CLASS), PLANNED_TEAM_RESULTS("plannedTeamResults", PlannedSprayTeamResults.CLASS), PLANNED_AREA("plannedArea", PlannedAreaTarget.CLASS), DATE_GROUPS("dateGroups", DateGroups.CLASS), ALL_ACTUALS("allActuals", ActivityUnion.CLASS), SPRAY_VIEW("sprayView", SprayView.CLASS);

    private String view;

    private String CLASS;

    private View(String view, String _CLASS)
    {
      this.view = view;
      this.CLASS = _CLASS;
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

    public SQLProvider newInstance(IRSQB irs)
    {
      @SuppressWarnings("unchecked")
      Class<? extends SQLProvider> klass = (Class<? extends SQLProvider>) LoaderDecorator.load(this.CLASS);
      Class<?> irsqbKlass = LoaderDecorator.load(IRSQB.CLASS);

      try
      {
        Constructor<? extends SQLProvider> c = klass.getConstructor(irsqbKlass);
        SQLProvider p = c.newInstance(irs);

        return p;
      }
      catch (Throwable t)
      {
        String msg = "IRS QB: Unable to invoke SQLProvider [" + this + "] with class [" + klass + "].";
        throw new ProgrammingErrorException(msg, t);
      }
    }
  }

  public class Universal implements Reloadable
  {
    /**
     * The universal name.
     */
    private String name;

    /**
     * The universal's id (of the MdBusiness)
     */
    private String id;

    /**
     * The user defined alias of the entity name in the value query.
     */
    private String entityNameAlias;

    /**
     * The user defined alias of the geo id in the value query.
     */
    private String geoIdAlias;

    //
    // /**
    // * The auto generated of the entity name in the value query.
    // */
    // private String entityNameGenerated;
    //
    // /**
    // * The auto generated of the geo id in the value query.
    // */
    // private String geoIdGenerated;

    private Universal(String name, String id, String entityNameAlias, String geoIdAlias)
    {
      this.name = name;
      this.id = id;
      this.entityNameAlias = entityNameAlias;
      this.geoIdAlias = geoIdAlias;
    }

    public String getId()
    {
      return this.id;
    }

    public String getName()
    {
      return name;
    }

    public String getEntityNameAlias()
    {
      return entityNameAlias;
    }

    public String getGeoIdAlias()
    {
      return geoIdAlias;
    }

    // public void setEntityNameGenerated(String entityNameGenerated)
    // {
    // this.entityNameGenerated = entityNameGenerated;
    // }
    //
    // public String getEntityNameGenerated()
    // {
    // return entityNameGenerated;
    // }
    //
    // public void setGeoIdGenerated(String geoIdGenerated)
    // {
    // this.geoIdGenerated = geoIdGenerated;
    // }
    //
    // public String getGeoIdGenerated()
    // {
    // return geoIdGenerated;
    // }

    public String getEntityNamePlanned()
    {
      return this.name.toLowerCase() + "_entityName";
    }

    public String getGeoIdPlanned()
    {
      return this.name.toLowerCase() + "_geoid";
    }
  }

  private Map<String, Universal> universals;

  private Set<View>              requiredViews;

  private AggregationQueryType   aggType;

  /**
   * Name of the sub-query that aggregates activity values ahead of time to avoid cross-products when joining on relationships.
   */
  private static final String    PRE_AGGREGATION = "preAggregation";

  /**
   * Denotes what type of aggregation this query will be used for (eg, as a query in a WITH clause to aggregate area planned targets).
   */
  public enum AggregationQueryType implements Reloadable {
    AREA, OPERATOR, TEAM
  }

  public IRSQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, AggregationQueryType aggType, Disease disease)
  {
    super(xml, config, layer, pageSize, pageSize, disease);

    this.aggType = aggType;

    this.universalAliases = new HashSet<String>();

    this.setWITHRecursive(true);

    this.hasActivity = false;

    this.universals = new HashMap<String, Universal>();

    this.requiredAliases = new HashMap<View, Set<Alias>>();

    // manually put sets with different Views. Note that some views share the
    // same
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

    this.requiredAliases.put(View.DATE_GROUPS, new HashSet<Alias>());

    this.requiredAliases.put(View.ALL_ACTUALS, new HashSet<Alias>());

    this.requiredAliases.put(View.SPRAY_VIEW, new HashSet<Alias>());

    this.dategroups = new HashMap<String, String>();

    // The sprayView is always required
    this.requiredViews = new HashSet<View>();
    this.requiredViews.add(View.SPRAY_VIEW);
    // this.requiredViews.add(View.ALL_ACTUALS);

    this.selectAliases = new HashSet<Alias>();

    this.smallestUnivesal = null;

    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    this.parentAggregates = new HashSet<Alias>();
    this.childAggregates = new HashSet<Alias>();

    this.audits = new HashSet<Alias>();
    this.audits.add(Alias.AUDIT_CREATE_DATE);
    this.audits.add(Alias.AUDIT_CREATED_BY);
    this.audits.add(Alias.AUDIT_IMPORTED);
    this.audits.add(Alias.AUDIT_LAST_UPDATE_DATE);
    this.audits.add(Alias.AUDIT_LAST_UPDATED_BY);

    try
    {
      this.queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    this.dateCriteria = DateCriteria.NONE;

    // JN change
    // this.areaPairs = new LinkedList<Pair<String, SQLProvider>>();

    queryFactory = new QueryFactory();
    // this.sprayVQ = new ValueQuery(queryFactory);
    this.irsVQ = new ValueQuery(queryFactory);
    // this.insecticideVQ = new ValueQuery(queryFactory);

    // this.needUniqueSprayId = false;

    this.needsAreaPlanned = false;
    this.needsOperatorPlanned = false;
    this.needsTeamsPlanned = false;

    this.hasPlannedTargets = false;

    this.abstractSprayQuery = null;
    this.insecticideQuery = null;

    this.sprayViewAlias = null;

    this.sprayedUnits = null;
    this.needsSprayedUnits = false;

    this.idCol = QueryUtil.getIdColumn();
    this.keyName = QueryUtil.getColumnName(Metadata.getKeyNameMd());
    this.targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());
    this.geoEntity = QueryUtil.getColumnName(ZoneSpray.getGeoEntityMd());

    this.periodCol = QueryUtil.getColumnName(EpiWeek.getPeriodMd());

    this.criteriaInterceptor = new CriteriaInterceptor(this.irsVQ);
  }

  public IRSQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    this(xml, config, layer, pageNumber, pageSize, null, disease);

  }

  public AggregationQueryType getAggregationType()
  {
    return this.aggType;
  }

  public String getSprayViewAlias()
  {
    return sprayViewAlias;
  }

  /**
   * Activity is assumed if there are no planned targets (what else would the user select?) or if activity has been explicitly requested. Activity
   * MUST be false if this query is used for aggregation (WITH clause in a larger query).
   * 
   * @return
   */
  public boolean hasActivity()
  {
    // Add actuals (spray activity) if it has explicitly been specified or if
    // COUNT or RATIO exists
    return this.requiredViews.contains(View.ALL_ACTUALS) || this.hasActivity && this.aggType == null || ( this.selectAliases.contains(Alias.COUNT) || this.selectAliases.contains(Alias.RATIO) );
  }

  public boolean hasPlanned()
  {
    return this.hasPlannedTargets;
  }

  public void addRequiredView(View view)
  {
    this.requiredViews.add(view);
  }

  /**
   * Adds the aliases to all planned targets.
   */
  public void addPlannedAlias(Alias... alias)
  {
    // Since planned area shares the same Set
    // instance with operator and team planned targets
    // we only need to reference it to set the values
    // for all planned targets.
    this.addRequiredAlias(View.PLANNED_AREA, alias);
  }

  public void addRequiredAlias(View view, Alias... alias)
  {
    for (Alias a : alias)
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

  public void resetSelectAliases(Set<Alias> reset)
  {
    this.selectAliases = reset;
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

  public Set<String> getDategroups()
  {
    return dategroups.keySet();
  }

  /**
   * These aggregation levels refer to whether or not the query includes data from this level. If the user does not specify any levels, then all
   * levels are included.
   * 
   * @return
   */
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
    String operatorSex = this.createAllPathsEntityAlias(Alias.SPRAY_OPERATOR_SEX.getAlias(), Person.CLASS, Alias.SPRAY_OPERATOR_SEX.getAlias());
    AliasPair operatorSexPair = new AliasPair(this.sprayViewAlias, Alias.SPRAY_OPERATOR_SEX.getAlias());
    pairs.put(operatorSex, operatorSexPair);

    String leaderSex = this.createAllPathsEntityAlias(Alias.SPRAY_LEADER_SEX.getAlias(), Person.CLASS, Alias.SPRAY_LEADER_SEX.getAlias());
    AliasPair leaderSexPair = new AliasPair(this.sprayViewAlias, Alias.SPRAY_LEADER_SEX.getAlias());
    pairs.put(leaderSex, leaderSexPair);

    String supervisorSex = this.createAllPathsEntityAlias(Alias.ZONE_SUPERVISOR_SEX.getAlias(), Person.CLASS, Alias.ZONE_SUPERVISOR_SEX.getAlias());
    AliasPair supervisorSexPair = new AliasPair(this.sprayViewAlias, Alias.ZONE_SUPERVISOR_SEX.getAlias());
    pairs.put(supervisorSex, supervisorSexPair);

    return pairs;
  }

  public boolean hasAreaCalcs()
  {
    return this.irsVQ.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias()) || this.irsVQ.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
  }

  public QueryFactory getQueryFactory()
  {
    return queryFactory;
  }

  public ValueQuery getValueQuery()
  {
    return this.irsVQ;
  }

  public String getSmallestUniversal()
  {
    return this.smallestUnivesal;
  }

  @Override
  protected ValueQueryParser createParser(ValueQuery valueQuery, List<ParseInterceptor> interceptors)
  {
    this.irsParser = super.createParser(this.irsVQ, this.createParseInterceptors(this.irsVQ));
    // this.insecticideParser = super.createParser(this.insecticideVQ,
    // this.createParseInterceptors(this.insecticideVQ));
    // this.sprayParser = super.createParser(this.sprayVQ, this.createParseInterceptors(this.sprayVQ));

    return this.irsParser;
  }

  /**
   * Adds a custom Interceptor to the this.irsVQ to look for aggregation level restrictions.
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
  protected void setTermCriteria(ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, QBInterceptor interceptor)
  {
    QBInterceptor qbInterceptor = this.getQBInterceptor(this.irsParser);
    super.setTermCriteria(irsVQ, this.mainQueryMap, qbInterceptor);

    // if (insecticideQuery != null)
    // {
    // qbInterceptor = this.getQBInterceptor(this.insecticideParser);
    // super.setTermCriteria(insecticideVQ, insecticideQueryMap, qbInterceptor);
    // }
    //
    // if (this.hasSprayEnumOrTerm)
    // {
    // qbInterceptor = this.getQBInterceptor(this.sprayParser);
    // super.setTermCriteria(sprayVQ, abtractSprayQueryMap, qbInterceptor);
    // }
  }

  @Override
  protected Map<String, GeneratedTableClassQuery> joinQueryWithGeoEntities(QueryFactory factory, ValueQuery valueQuery, String xml, JSONObject queryConfig, Layer layer, ValueQueryParser parser)
  {
    this.mainQueryMap = super.joinQueryWithGeoEntities(factory, irsVQ, xml, queryConfig, layer, this.irsParser);

    // this.insecticideQueryMap = super.joinQueryWithGeoEntities(factory, insecticideVQ, xml, queryConfig,
    // null, this.insecticideParser);
    //
    // this.abtractSprayQueryMap = super.joinQueryWithGeoEntities(factory, sprayVQ, xml, queryConfig, null,
    // this.sprayParser);

    return this.mainQueryMap;
  }

  @Override
  protected String getAuditClassAlias()
  {
    return AbstractSpray.CLASS;
  }

  /**
   * Populates the ValueQuery with the necessary selects, joins, and criteria to make the IRS query work correctly. ORDER IS IMPORTANT. Do not change
   * the calls within this method unless you know what you are doing! There are many boolean flags set within those calls that dictate how the query
   * will behave.
   */
  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery, Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    queryFactory = this.irsVQ.getQueryFactory();

    this.sprayViewAlias = this.mainQueryMap.get(AbstractSpray.CLASS).getTableAlias();
    this.abstractSprayQuery = (AbstractSprayQuery) this.mainQueryMap.get(AbstractSpray.CLASS);
    // this.abstractSprayQuery = (AbstractSprayQuery) this.abtractSprayQueryMap.get(AbstractSpray.CLASS);
    this.insecticideQuery = (InsecticideBrandQuery) this.mainQueryMap.get(InsecticideBrand.CLASS);
    this.outerInsecticideQuery = (InsecticideBrandQuery) this.mainQueryMap.get(InsecticideBrand.CLASS);
    // this.insecticideQuery = (InsecticideBrandQuery) this.insecticideQueryMap.get(InsecticideBrand.CLASS);

    // Two of the insecticide selectables are not on the InsecticideBrand class,
    // so
    // manually include the proper objects to make everything else work
    if ( ( valueQuery.hasSelectableRef(Alias.ACTIVE_INGREDIENT_PER_CAN.getAlias()) || valueQuery.hasSelectableRef(Alias.UNITS_PER_CAN.getAlias()) ) && this.insecticideQuery == null)
    {
      this.outerInsecticideQuery = new InsecticideBrandQuery(this.irsVQ);
      this.insecticideQuery = new InsecticideBrandQuery(this.irsVQ);
    }

    if (this.insecticideQuery != null)
    {
      this.addRequiredView(View.INSECTICIDE_VIEW);
    }

    // The LeftOuterJoins created by the ValueQueryParser contain a reference to abstract_spray. Because we're IRS and we think we're special,
    // we need to rewire that to reference SprayView instead.
    // First implemented as part of ticket 3459
    Set<LeftJoin> leftJoins = this.irsVQ.getLeftOuterJoins();
    for (LeftJoin lj : leftJoins)
    {
      if (lj.getTableName1().equals("abstract_spray"))
      {
        lj.setTableName1("sprayView");
      }
    }

    processUniversals();

    discoverDateGroups();

    joinSexAttributes();

    // Add the filters (spray operator id = 123) to the WHERE criteria.
    String tableName = null;
    if (insecticideQuery != null)
    {
      tableName = insecticideQuery.getTableAlias();
    }
    // else if (valueQuery.hasSelectableRef(Alias.STRUCTURE_TYPE.getAlias()) || valueQuery.hasSelectableRef(Alias.REASON_NOT_SPRAYED.getAlias()))
    // {
    // tableName = "allActuals";
    // }

    if (tableName != null)
    {
      QueryUtil.joinEnumerationDisplayLabels(irsVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(irsVQ, InsecticideBrand.CLASS, insecticideQuery, this.getTermRestrictions(), this.getLayer());
      this.setNumericRestrictions(irsVQ, queryConfig);
    }
    else
    {
      setNumericRestrictions(irsVQ, queryConfig);
    }

    // This must happen after setNumericRestrictions, otherwise the selectables are aggregated in the WHERE clause (syntax error!) in the case of an
    // aggregated range restriction.
    swapOutAttributesForAggregates();

    // Spray Date
    QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + Alias.SPRAY_DATE);

    // Geo Entity
    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) irsVQ.getSelectableRef(AbstractSpray.GEOENTITY);
      subGeo.setSQL(QueryUtil.GEO_DISPLAY_LABEL + "." + QueryUtil.LABEL_COLUMN);

      // The subselect was incredibly slow
      // QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS,
      // AbstractSpray.GEOENTITY,
      // sprayViewAlias + "." + idCol);
    }

    // NOTE: for backwards compatibility use the XMLAlias() instead of alias()
    if (irsVQ.hasSelectableRef(Alias.SPRAY_METHOD.getXmlAlias()) || irsVQ.hasSelectableRef(Alias.SURFACE_TYPE.getXmlAlias()))
    {
      QueryUtil.joinEnumerationDisplayLabels(irsVQ, AbstractSpray.CLASS, abstractSprayQuery, View.SPRAY_VIEW.getView(), this.sprayViewAlias);
      QueryUtil.joinTermAllpaths(irsVQ, AbstractSpray.CLASS, View.SPRAY_VIEW.getView(), this.sprayViewAlias, this.getTermRestrictions(), this.getLayer());
    }

    sprayedUnits = "(CASE WHEN " + Alias.SPRAY_UNIT + " = 'ROOM' THEN " + Alias.SPRAYED_ROOMS + "  WHEN " + Alias.SPRAY_UNIT + " = 'STRUCTURE' THEN " + Alias.SPRAYED_STRUCTURES + " WHEN " + Alias.SPRAY_UNIT + " = 'HOUSEHOLD' THEN " + Alias.SPRAYED_HOUSEHOLDS + " END )";

    // make sure if we have a calculation that the right columns are included
    Alias[] calcs = new Alias[] { Alias.SPRAYED_UNITS, Alias.UNITS_UNSPRAYED, Alias.UNIT_APPLICATION_RATE, Alias.UNIT_APPLICATION_RATE_MG, Alias.UNIT_APPLICATION_RATIO, Alias.UNIT_OPERATIONAL_COVERAGE, Alias.CALCULATED_ROOMS_SPRAYED, Alias.CALCULATED_STRUCTURES_SPRAYED, Alias.CALCULATED_HOUSEHOLDS_SPRAYED };

    for (Alias calc : calcs)
    {
      if (this.irsVQ.hasSelectableRef(calc.getAlias()))
      {
        this.needsSprayedUnits = true;
      }
    }

    setTargetManagmentCalculations();

    // Modify the Date sql for both SELECT and WHERE
    // ---- START DATE CRITERIA ----
    Condition dateCond = addDateCriteria(this.sprayViewAlias, this.hasPlannedTargets, Alias.SPRAY_OPERATOR_BIRTHDATE.getAlias(), Alias.SPRAY_LEADER_BIRTHDATE.getAlias(), Alias.ZONE_SUPERVISOR_BIRTHDATE.getAlias());
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

      if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
      {
        // Throw in a SelectableMoment override that points to Alias.SPRAY_DATE
        // because setQueryDates()
        // will try to use the selected date from the QB, PERSON_BIRTHDATE in
        // this case, and that doesn't
        // make sense.
        SelectableMoment dateSel = irsVQ.aSQLDate("birthdate_override", this.sprayViewAlias + "." + Alias.SPRAY_DATE.getAlias());
        QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper, dateSel);
      }
      else
      {
        String attrName = QueryUtil.getDateAttributeFromConfig(queryConfig);
        // This IF statement is a fix for IRS ticket 2979.
        if (xml.contains(attrName))
        {
          QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper);
        }
      }
    }
    // ---- END DATE CRITERIA ----

    /* DATE POST PROCESSING */
    /*
     * if (this.hasPlannedTargets) { // Set the other date groupings to null for the planning rows for (String group : this.dategroups.keySet()) { if
     * (irsVQ.hasSelectableRef(group)) { SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(group); String original = sel.getSQL();
     * 
     * String sprayDateCol = sprayViewAlias + "." + Alias.SPRAY_DATE.getAlias(); String plannedDateCol = sprayViewAlias + "." +
     * Alias.PLANNED_DATE.getAlias();
     * 
     * this.addRequiredAlias(View.PLANNED_AREA, Alias.PLANNED_DATE); this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
     * 
     * 
     * String sql; if (group.equals(QueryUtil.DATEGROUP_SEASON)) { // show the season matching for either the planned target or spray // activity
     * String plannedReplace = original.replaceAll( sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), plannedDateCol); String sprayReplace =
     * original.replaceAll( sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), sprayDateCol);
     * 
     * sql = "CASE WHEN " + plannedDateCol + " IS NOT NULL THEN " + plannedReplace + " WHEN " + sprayDateCol + " IS NOT NULL THEN " + sprayReplace +
     * " ELSE NULL END"; } else { String caseStmt = "CASE WHEN " + sprayDateCol + " IS NOT NULL THEN " + sprayDateCol + " ELSE " + plannedDateCol +
     * " END"; sql = original.replaceAll(sprayViewAlias + "\\." + Alias.SPRAY_DATE.getAlias(), caseStmt); }
     * 
     * 
     * sel.setSQL(sql); } }
     * 
     * }
     */

    // Note: This must go last but before joinMainQueryTables()
    setWithQuerySQL();

    setCalculations();

    joinMainQueryTables();

    coalesceTeamGeoEntity();

    return this.irsVQ;
  }

  /*
   * Ticket 2925 Since team planned targets don't contain geo entities less than spray area, spray zone could potentially end up empty. We're doing a
   * coalesce here just in case.
   */
  private void coalesceTeamGeoEntity()
  {
    if ( ( this.needsTeamsPlanned || this.needsOperatorPlanned ) && smallestUniversalSelectable != null && this.getValueQuery().hasSelectableRef(this.smallestUniversalSelectable))
    {
      /*
       * We'll be pulling the data from this default_geos temp table (which really just grabs the GeoEntity from the SprayView, which in turn comes
       * from SprayTeam)
       */
      // ValueQuery vq = new ValueQuery(this.queryFactory);
      // SelectableSQL geoIdSel = vq.aSQLCharacter(GeoEntity.GEOID, "ge.geo_id", GeoEntity.GEOID);
      // SelectableSQL labelSel = vq.aSQLCharacter(GeoEntity.ENTITYLABEL, "gdl.label", GeoEntity.ENTITYLABEL);
      // vq.SELECT(labelSel, geoIdSel);
      // vq.FROM("sprayView", "sv INNER JOIN geo_entity ge ON sv.geo_entity=ge.id\n" +
      // " INNER JOIN geo_displayLabel gdl on gdl.geo_id=ge.geo_id");
      // vq.GROUP_BY(labelSel, geoIdSel);
      // this.getValueQuery().FROM("(" + vq.getSQL() + ")", "default_geos");

      /*
       * COALESCE the geoId
       */
      // Selectable geoId = this.getValueQuery().getSelectableRef(this.smallestUniversalSelectable);
      // this.getValueQuery().removeSelectable(geoId);
      //
      // SelectableSQLCharacter selectGeoIdFromDefault = this.getValueQuery().aSQLCharacter(GeoEntity.GEOID, "default_geos." + GeoEntity.GEOID);
      // Coalesce geoIdCoal = F.COALESCE(geoId.getUserDefinedAlias(), geoId.getUserDefinedDisplayLabel(), (SelectableSingle) geoId, (SelectableSingle)
      // selectGeoIdFromDefault);
      // geoIdCoal.setColumnAlias(geoId.getColumnAlias());
      //
      // this.getValueQuery().SELECT(geoIdCoal);
      // this.getValueQuery().GROUP_BY(selectGeoIdFromDefault);

      /*
       * COALESCE the entity label (geo display label)
       */
      // String attrName = this.smallestUniversalSelectable.replace(GeoEntity.GEOID, GeoEntity.ENTITYLABEL);
      // Selectable entityLabel = this.getValueQuery().getSelectableRef(attrName);
      // this.getValueQuery().removeSelectable(entityLabel);
      //
      // SelectableSQLCharacter selectLabelFromDefault = this.getValueQuery().aSQLCharacter(GeoEntity.ENTITYLABEL, "default_geos." +
      // GeoEntity.ENTITYLABEL);
      // Coalesce entityLabelCoal = F.COALESCE(entityLabel.getUserDefinedAlias(), entityLabel.getUserDefinedDisplayLabel(), (SelectableSingle)
      // entityLabel, (SelectableSingle) selectLabelFromDefault);
      // entityLabelCoal.setColumnAlias(entityLabel.getColumnAlias());
      //
      // this.getValueQuery().SELECT(entityLabelCoal);
      // this.getValueQuery().GROUP_BY(selectLabelFromDefault);
    }
  }

  private void setCalculations()
  {

    String unsprayedRooms = "SUM(" + Alias.ROOMS + ") - SUM(" + Alias.SPRAYED_ROOMS + ")";
    String unsprayedHouseholds = "SUM(" + Alias.HOUSEHOLDS + ") - SUM(" + Alias.SPRAYED_HOUSEHOLDS + ")";
    String unsprayedStructures = "SUM(" + Alias.STRUCTURES + ") - SUM(" + Alias.SPRAYED_STRUCTURES + ")";

    if (irsVQ.hasSelectableRef(Alias.ROOMS_UNSPRAYED.getAlias()))
    {
      Selectable s = irsVQ.getSelectableRef(Alias.ROOMS_UNSPRAYED.getAlias());
      Selectable r = irsVQ.aSQLAggregateInteger(s._getAttributeName(), unsprayedRooms, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
      r.setColumnAlias(Alias.ROOMS_UNSPRAYED.getAlias());
      
      this.copyData(s, r);

      irsVQ.replaceSelectable(r);
    }

    if (irsVQ.hasSelectableRef(Alias.HOUSEHOLDS_UNSPRAYED.getAlias()))
    {
      Selectable s = irsVQ.getSelectableRef(Alias.HOUSEHOLDS_UNSPRAYED.getAlias());
      Selectable r = irsVQ.aSQLAggregateInteger(s._getAttributeName(), unsprayedHouseholds, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
      r.setColumnAlias(Alias.HOUSEHOLDS_UNSPRAYED.getAlias());

      this.copyData(s, r);

      irsVQ.replaceSelectable(r);
    }

    if (irsVQ.hasSelectableRef(Alias.STRUCTURES_UNSPRAYED.getAlias()))
    {
      Selectable s = irsVQ.getSelectableRef(Alias.STRUCTURES_UNSPRAYED.getAlias());
      Selectable r = irsVQ.aSQLAggregateInteger(s._getAttributeName(), unsprayedStructures, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
      r.setColumnAlias(Alias.STRUCTURES_UNSPRAYED.getAlias());

      this.copyData(s, r);

      irsVQ.replaceSelectable(r);
    }

    String availableUnits = "(CASE WHEN " + Alias.SPRAY_UNIT + " = 'ROOM' THEN rooms  WHEN " + Alias.SPRAY_UNIT + " = 'STRUCTURE' THEN structures WHEN " + Alias.SPRAY_UNIT + " = 'HOUSEHOLD' THEN households END )";
    String unsprayedUnits = "(CASE WHEN " + Alias.SPRAY_UNIT + " = 'ROOM' THEN (" + unsprayedRooms + ")  WHEN " + Alias.SPRAY_UNIT + " = 'STRUCTURE' THEN (" + unsprayedStructures + ") WHEN " + Alias.SPRAY_UNIT + " = 'HOUSEHOLD' THEN (" + unsprayedHouseholds + ")  END )";
    String shareOfCans = "(CASE WHEN " + Alias.SPRAY_UNIT + " = 'ROOM' THEN (sprayedrooms_share)  WHEN " + Alias.SPRAY_UNIT + " = 'STRUCTURE' THEN (sprayedstructures_share) WHEN " + Alias.SPRAY_UNIT + " = 'HOUSEHOLD' THEN (sprayedhouseholds_share)  END )";

    String uniqueSprayId = this.getUniqueSprayDetailsId();

    String unit_operational_coverage = "SUM(" + sprayedUnits + ")::float / nullif(SUM(" + availableUnits + "),0)";

    // #2868 - instead of -refills- we now sum on sachets -used-
    // FIXME SUM FIX
    String used = Alias.USED.getAlias();
    // String used = this.needsPreAggregation() ? PRE_AGGREGATION+"."+Alias.USED : Alias.USED.getAlias();
    String unit_application_rate = "SUM(" + used + "::FLOAT * " + shareOfCans + " * " + Alias.ACTIVE_INGREDIENT_PER_CAN + ") / nullif(SUM(" + sprayedUnits + " * " + Alias.UNIT_AREA + "),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG(" + Alias.STANDARD_APPLICATION_RATE + "))";

    if (QueryUtil.setSelectabeSQL(irsVQ, Alias.SPRAYED_UNITS.getAlias(), sprayedUnits))
    {
      setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() }, uniqueSprayId, irsVQ, this.sprayViewAlias, true, true);
    }

    if (irsVQ.hasSelectableRef(Alias.UNITS_UNSPRAYED.getAlias()))
    {
      Selectable s = irsVQ.getSelectableRef(Alias.UNITS_UNSPRAYED.getAlias());
      SelectableSQL r = irsVQ.aSQLAggregateInteger(s._getAttributeName(), unsprayedUnits, s.getUserDefinedAlias(), s.getUserDefinedDisplayLabel());
      r.setColumnAlias(Alias.UNITS_UNSPRAYED.getAlias());
      
      this.copyData(s, r);

      irsVQ.replaceSelectable(r);

      SelectableSQL gb = irsVQ.aSQLCharacter(Alias.SPRAY_UNIT.getAlias(), Alias.SPRAY_UNIT.getAlias());
      gb.setAttributeNameSpace("insecticideView");
      
      irsVQ.GROUP_BY(gb);
    }

    if (QueryUtil.setSelectabeSQL(irsVQ, Alias.REFILLS + " * " + shareOfCans, unsprayedUnits))
    {
      SelectableSQL gb = irsVQ.aSQLCharacter(Alias.SPRAY_UNIT.getAlias(), Alias.SPRAY_UNIT.getAlias());
      gb.setAttributeNameSpace("insecticideView");
      irsVQ.GROUP_BY(gb);
    }

    QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATE.getAlias(), "(" + unit_application_rate + ")");

    QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATE_MG.getAlias(), "1000.0 *" + "(" + unit_application_rate + ")");

    QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATIO.getAlias(), unit_application_ratio);

    QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_OPERATIONAL_COVERAGE.getAlias(), unit_operational_coverage + " * 100.0");

    QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_ROOMS_SPRAYED.getAlias(), "(" + unit_operational_coverage + ") * SUM(" + Alias.ROOMS.getAlias() + ")");

    QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_STRUCTURES_SPRAYED.getAlias(), "(" + unit_operational_coverage + ") * SUM(" + Alias.STRUCTURES.getAlias() + ")");

    QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_HOUSEHOLDS_SPRAYED.getAlias(), "(" + unit_operational_coverage + ") * SUM(" + Alias.HOUSEHOLDS.getAlias() + ")");
  }

  @SuppressWarnings("unchecked")
  private void copyData(Selectable source, Selectable target)
  {
    Map<String, Object> data = (Map<String, Object>) source.getData();

    if (data == null)
    {
      data = new HashMap<String, Object>();
    }

    if (!data.containsKey(MetadataInfo.CLASS))
    {
      data.put(MetadataInfo.CLASS, source.getMdAttributeIF());
    }
    
    if(!data.containsKey(SelectableAggregate.class.getName()))
    {
      data.put(SelectableAggregate.class.getName(), source.isAggregateFunction());
    }

    target.setData(data);
  }

  /**
   * Locates all the date group fields within the query.
   */
  private void discoverDateGroups()
  {
    String[] groups = new String[] { QueryUtil.DATEGROUP_EPIWEEK, QueryUtil.DATEGROUP_MONTH, QueryUtil.DATEGROUP_QUARTER, QueryUtil.DATEGROUP_EPIYEAR, QueryUtil.DATEGROUP_CALENDARYEAR, QueryUtil.DATEGROUP_SEASON };

    for (String group : groups)
    {
      if (this.irsVQ.hasSelectableRef(group))
      {
        this.dategroups.put(group, group);
      }
    }
  }

  /**
   * The sex attributes on operator, leader, and supervisor in the team details requires custom logic.
   * 
   * FIXME push these into a join instead of subselect
   */
  @SuppressWarnings("unchecked")
  private void joinSexAttributes()
  {
    if (irsVQ.hasSelectableRef(Alias.SPRAY_OPERATOR_SEX.getAlias()))
    {
      this.localizeSex(Alias.SPRAY_OPERATOR_SEX.getAlias(), Alias.SPRAY_OPERATOR_PERSON.getAlias());
    }

    if (irsVQ.hasSelectableRef(Alias.SPRAY_LEADER_SEX.getAlias()))
    {
      this.localizeSex(Alias.SPRAY_LEADER_SEX.getAlias(), Alias.SPRAY_LEADER_PERSON.getAlias());
    }

    if (irsVQ.hasSelectableRef(Alias.ZONE_SUPERVISOR_SEX.getAlias()))
    {
      this.localizeSex(Alias.ZONE_SUPERVISOR_SEX.getAlias(), Alias.ZONE_SUPERVISOR_PERSON.getAlias());
    }

  }

  private void localizeSex(String sexAlias, String personAlias)
  {
    MdEntityDAOIF termLabelMdEntityDAOIF = MdEntityDAO.getMdEntityDAO(TermTermDisplayLabel.CLASS);
    MdAttributeLocalCharacterDAOIF mdAttributeTermDisplayLabel = Term.getTermDisplayLabelMd();

    Selectable selectable = irsVQ.getSelectableRef(sexAlias);
    if (selectable instanceof SelectableSQL)
    {
      SelectableSQL s = (SelectableSQL) selectable;
      PersonQuery personQuery = new PersonQuery(irsVQ);
      MdEntityDAOIF personMdEntity = personQuery.getMdClassIF();

      String sprayOpColumn = personAlias;
      String sprayOpTableName = View.SPRAY_VIEW.getView(); // ActualTeamSprayTarget.OPERATOR_PERSON;
      String sprayOpTableAlias = this.sprayViewAlias;

      // Used for calculating new table aliases for the query
      String personNamespace = sprayOpTableAlias + "." + sexAlias;
      String newPersonTableAlias = irsVQ.getQueryFactory().getTableAlias(personNamespace, personMdEntity.getTableName());

      LeftJoinEq leftJoinEq1 = new LeftJoinEq(sprayOpColumn, sprayOpTableName, sprayOpTableAlias, EntityDAOIF.ID_COLUMN, personMdEntity.getTableName(), newPersonTableAlias);

      String termNamespace = personNamespace + "." + Person.SEX;
      String termTable = MdBusiness.getMdBusiness(Term.CLASS).getTableName();
      String termTableAlias = irsVQ.getQueryFactory().getTableAlias(termNamespace, termTable);
      MdAttributeConcreteDAOIF sexMdAttribute = personMdEntity.definesAttribute(Person.SEX);

      LeftJoinEq leftJoinEq2 = new LeftJoinEq(sexMdAttribute.getColumnName(), personMdEntity.getTableName(), newPersonTableAlias, EntityDAOIF.ID_COLUMN, termTable, termTableAlias);
      leftJoinEq1.nest(leftJoinEq2);

      // create the display label table alias for the term
      String displayLabelNamespace = termNamespace + "." + TermTermDisplayLabel.DEFAULTLOCALE;
      String newDisplayLabelTableAlias = irsVQ.getQueryFactory().getTableAlias(displayLabelNamespace, termLabelMdEntityDAOIF.getTableName());

      LeftJoinEq leftJoinEq3 = new LeftJoinEq(mdAttributeTermDisplayLabel.getColumnName(), termTable, termTableAlias, EntityDAOIF.ID_COLUMN, termLabelMdEntityDAOIF.getTableName(), newDisplayLabelTableAlias);
      leftJoinEq2.nest(leftJoinEq3);

      // Create the coalesce function for the term display label
      String coalesceFunction = QueryUtil.getLocaleCoalesce(newDisplayLabelTableAlias + ".");
      // Set the selectable to be the coalesce function.
      s.setSQL(coalesceFunction);

      irsVQ.AND(leftJoinEq1);
      joinSprayView = false;
    }
  }

  private String[] getGeoIncludes()
  {
    if (geoIncludesCondition != null)
    {
      ArrayList<String> geoIncludes = new ArrayList<String>();

      String haystack = geoIncludesCondition.getSQL();

      String needle = "allpaths_geo.*\\.parent_geo_entity.*=.*'(.*)'";

      Pattern pattern = Pattern.compile(needle);
      Matcher matcher = pattern.matcher(haystack);
      while (matcher.find())
      {
        geoIncludes.add(matcher.group(1));
      }

      return geoIncludes.toArray(new String[geoIncludes.size()]);
    }

    return null;
  }

  private String geoGeoIncludesSQL()
  {
    String[] geoIncludes = getGeoIncludes();

    if (geoIncludes == null)
    {
      return null;
    }

    return "{" + StringUtils.join(geoIncludes, ", ") + "}";
  }

  /**
   * If the original query contains planned targets (operator planned target, team planned target, or area planned target), then we join the planned
   * target aggregation results with the original query. This is done by creating a new IRSQB, running it, and then printing it at the top of our SQL
   * output in a 'with' clause ( i.e. with teamAggregation as ( ... ) ). We then create another value query (finalVQ) and select from originalVQ FULL
   * OUTER JOIN teamAggregation. originalVQ is the (this) value query and it queries the actuals.
   */
  @Override
  protected ValueQuery postProcess(ValueQuery originalVQ)
  {
    // skip this processing if this query is for aggregation and
    // by doing so we'll avoid infinite recursion.
    if (this.aggType != null)
    {
      return super.postProcess(originalVQ);
    }

    List<WITHEntry> entries = new LinkedList<WITHEntry>();
    ValueQuery finalVQ = null; // This value query is the one that gets returned from this function
    ValueQuery aggVQ = null; // The aggregate value query gets printed at the top in a 'with' statement

    String originalAlias = "original_vq";
    String aggregateAlias = null;
    String FROM = originalAlias;

    // Push the original query into the FROM clause of the outer query
    // and join on the aggregation. Make sure to include every selectable that
    // is required for the join
    SelectableSQL seasonJoin = originalVQ.aSQLCharacter(this.sprayViewAlias + "_" + Alias.SPRAY_SEASON.getAlias(), this.sprayViewAlias + "." + Alias.SPRAY_SEASON.getAlias(), Alias.SPRAY_SEASON.getAlias());
    seasonJoin.setColumnAlias(this.sprayViewAlias + "_" + Alias.SPRAY_SEASON.getAlias());

    Selectable diseaseJoin = originalVQ.aSQLCharacter(this.sprayViewAlias + "_" + Alias.DISEASE.getAlias(), this.sprayViewAlias + "." + Alias.DISEASE.getAlias(), Alias.DISEASE.getAlias());
    diseaseJoin.setColumnAlias(this.sprayViewAlias + "_" + Alias.DISEASE.getAlias());

    List<String> partitionBy = new LinkedList<String>();
    for (String group : this.dategroups.values())
    {
      partitionBy.add(group);
    }
    partitionBy.add(seasonJoin.getColumnAlias());
    partitionBy.add(seasonJoin.getColumnAlias());

    if (this.needsAreaPlanned)
    {
      Set<String> toCoalesce = new HashSet<String>();

      String areaAggregation = "areaAggregation";
      aggregateAlias = areaAggregation;

      if (finalVQ == null)
      {
        finalVQ = new ValueQuery(originalVQ.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(originalVQ, originalVQ.getSelectableRefs(), originalAlias, false, null);
        finalVQ.SELECT(copies);
      }

      // We need to aggregate targets based on the lowest universal. In the original query we need
      // to set the parent geo entity to the id of the universal it was joined with
      // Grab the id column of the smallest universal (used for aggreation later on). This is tricky,
      // so grab an existing known selectable that references the same ValueQuery and select from there.
      // Because the aggregation query is a clone of the original query we can use the same selectable/namespace
      Selectable smallestUniversal = originalVQ.getSelectableRef(this.smallestUniversalSelectable);
      ValueQuery universalVQ = (ValueQuery) smallestUniversal.getRootQuery();
      String parentUniversalId = universalVQ.getSelectableRef(PARENT_UNIVERSAL_ID).getDbQualifiedName();

      // create a new IRS Query that aggregates the area targets for the
      // universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps
      // the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.AREA, this.getDisease());

      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();

      // Get the alias of the value query (this.getSprayViewAlias() will return
      // null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();

      ValueQuery areaAggVQ = qb.getValueQuery();

      HashMap<String, Selectable> toAdd = new HashMap<String, Selectable>();
      Selectable smallestUni = areaAggVQ.getSelectableRef(this.smallestUniversalSelectable);
      Selectable apt = areaAggVQ.aSQLAggregateInteger(Alias.AREA_PLANNED_TARGET.getAlias(), Alias.AREA_PLANNED_TARGET.getAlias());
      Selectable season = areaAggVQ.aSQLCharacter(aggAlias + "_" + Alias.SPRAY_SEASON.getAlias(), aggAlias + "." + Alias.SPRAY_SEASON.getAlias());
      Selectable disease = areaAggVQ.aSQLCharacter(aggAlias + "_" + Alias.DISEASE.getAlias(), aggAlias + "." + Alias.DISEASE.getAlias());

      Selectable parentGeo = areaAggVQ.aSQLCharacter(Alias.PARENT_GEO_ENTITY.getAlias(), parentUniversalId, Alias.PARENT_GEO_ENTITY.getAlias());
      parentGeo.setColumnAlias(Alias.PARENT_GEO_ENTITY.getAlias());

      for (Universal u : this.universals.values())
      {
        String geoId = u.getGeoIdAlias();
        String entityName = u.getEntityNameAlias();

        Selectable geoIdSel = originalVQ.getSelectableRef(geoId);
        Selectable entityNameSel = originalVQ.getSelectableRef(entityName);

        if (!geoId.equals(smallestUni.getUserDefinedAlias()))
        {
          Selectable s = areaAggVQ.aSQLCharacter(geoIdSel._getAttributeName(), geoIdSel.getSQL(), geoIdSel.getUserDefinedAlias());
          s.setColumnAlias(originalVQ.getSelectableRef(geoId).getColumnAlias());
          this.copyData(geoIdSel, s);

          toAdd.put(s.getResultAttributeName(), s);
        }

        Selectable s = areaAggVQ.aSQLCharacter(entityNameSel._getAttributeName(), entityNameSel.getSQL(), entityNameSel.getUserDefinedAlias());
        s.setColumnAlias(originalVQ.getSelectableRef(entityName).getColumnAlias());
        this.copyData(entityNameSel, s);

        toAdd.put(s.getResultAttributeName(), s);

        toCoalesce.add(geoId);
        toCoalesce.add(entityName);
      }

      toAdd.put(parentGeo.getResultAttributeName(), parentGeo);
      toAdd.put(smallestUni.getResultAttributeName(), smallestUni);
      toAdd.put(apt.getResultAttributeName(), apt);
      toAdd.put(season.getResultAttributeName(), season);
      toAdd.put(disease.getResultAttributeName(), disease);

      for (String group : this.dategroups.keySet())
      {
        Selectable sel = areaAggVQ.aSQLCharacter(group, group);
        toAdd.put(sel.getResultAttributeName(), sel);
        toCoalesce.add(group);
      }

      // Group by the universal column that is the parent geo entity
      // Create a new selectable to group by the universal id
      SelectableSQL universalGroup = universalVQ.aSQLInteger(PARENT_UNIVERSAL_ID, parentUniversalId, PARENT_UNIVERSAL_ID);
      universalGroup.setColumnAlias(universalGroup.getSQL());
      areaAggVQ.GROUP_BY(universalGroup);

      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.values().iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while (iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());

        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if (reset != null)
        {
          resetAliases.add(reset);
        }
      }

      qb.resetSelectAliases(resetAliases);

      // Make sure we preserve any relevant planning selectables from the original query.
      List<Selectable> sels = qb.getValueQuery().getSelectableRefs();
      for (Selectable sel : sels)
      {
        Alias alias = AliasLookup.get(sel.getResultAttributeName());
        if (alias != null && ( alias.hasView(View.PLANNED_AREA) || alias.hasView(View.RESOURCE_TARGET_VIEW) ))
        {
          toAdd.put(sel.getResultAttributeName(), sel);
        }
      }

      // replace the selectables
      areaAggVQ.clearSelectClause();
      areaAggVQ.SELECT(toAdd.values().toArray(new Selectable[toAdd.size()]));

      // finish construction of the query
      qb.construct(qb.getQueryFactory(), areaAggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();

      // (Ticket 3122) If the aggregate VQ has a date attribute then we'll get an error because it says it isn't grouped. Just use the epiweek we have
      // defined in previous WITH tables.
      // The reason this happens is because spray date doesn't exist on the aggregated tables, but there's special date logic that forces the epiweek
      // to be calculated from it.
      if (originalVQ.hasSelectableRef(AbstractSpray.SPRAYDATE) && areaAggVQ.hasSelectableRef(Alias.DATEGROUP_EPIWEEK.getAlias()))
      {
        ( (SelectableSQL) areaAggVQ.getSelectableRef(Alias.DATEGROUP_EPIWEEK.getAlias()) ).setSQL("dategroup_epiweek ::integer");
        ;
      }

      // Create a new selectable to group by the universal
      if (originalVQ.isGrouping())
      {
        SelectableSQL targetGroup = universalVQ.aSQLInteger(PARENT_UNIVERSAL_ID, parentUniversalId, PARENT_UNIVERSAL_ID);
        targetGroup.setColumnAlias(targetGroup.getSQL());

        irsVQ.GROUP_BY(targetGroup);
      }

      parentGeo = originalVQ.aSQLCharacter(Alias.PARENT_GEO_ENTITY.getAlias(), parentUniversalId, Alias.PARENT_GEO_ENTITY.getAlias());
      parentGeo.setColumnAlias(Alias.PARENT_GEO_ENTITY.getAlias());
      originalVQ.SELECT(parentGeo);

      // boolean hasAPC = valueQuery.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
      // boolean hasAPT = valueQuery.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());

      // Coverage requires activity and APT, meaning unless the user also selects
      // APT there will be empty rows for every APT result for which there's no activity.
      String joinType = "FULL OUTER JOIN";

      FROM += " " + joinType + " " + areaAggregation + " ON " + originalAlias + "." + seasonJoin.getColumnAlias() + " = " + areaAggregation + "." + season._getAttributeName() + " \n" + "AND " + originalAlias + "." + diseaseJoin.getColumnAlias() + " = " + areaAggregation + "." + disease._getAttributeName() + " \n" + "AND " + originalAlias + "." + Alias.PARENT_GEO_ENTITY + " = " + areaAggregation
          + "." + Alias.PARENT_GEO_ENTITY + " \n";
      for (String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND " + originalAlias + "." + dateGroup + " = " + areaAggregation + "." + dateGroup + " \n";
      }

      // The aggregation query needs to sum the area planned targets
      String geoIncludes = geoGeoIncludesSQL();
      if (geoIncludes == null)
      {
        SelectableSQL aptSel = (SelectableSQL) areaAggVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
        String func = QueryConstants.SUM_AREA_TARGETS + "(" + parentUniversalId + ", to_char(" + aggAlias + "." + Alias.TARGET_WEEK + "-1, 'FM99'), " + aggAlias + "." + Alias.DISEASE + ", " + aggAlias + "." + Alias.SPRAY_SEASON + ")";
        aptSel.setSQL(func);
      }
      else
      {
        SelectableSQL aptSel = (SelectableSQL) areaAggVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
        String func = QueryConstants.SUM_AREA_TARGETS_WITH_GEOINCLUDES + "(" + parentUniversalId + ", to_char(" + aggAlias + "." + Alias.TARGET_WEEK + "-1, 'FM99'), " + aggAlias + "." + Alias.DISEASE + ", " + aggAlias + "." + Alias.SPRAY_SEASON + ", '" + geoIncludes + "')";
        aptSel.setSQL(func);
      }

      String targetGroupName = "grouping_" + Alias.TARGET_WEEK;
      SelectableSQL targetGroup = areaAggVQ.aSQLInteger(Alias.TARGET_WEEK.toString(), aggAlias + "." + Alias.TARGET_WEEK, targetGroupName);
      targetGroup.setAttributeNameSpace(aggAlias);
      targetGroup.setColumnAlias(targetGroup.getSQL());
      areaAggVQ.GROUP_BY(targetGroup);

      // Now that everything is joined, grab the area planned target value from
      // the aggregation query
      if (finalVQ.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(areaAggregation + "." + Alias.AREA_PLANNED_TARGET);
      }

      if (finalVQ.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required
        // for coverage, add it
        // so the outer query can reference it
        if (!originalVQ.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String sumSprayedUnits = "SUM(" + this.sprayedUnits + ")";
          SelectableSQL sprayedUnits = originalVQ.aSQLAggregateInteger(Alias.SPRAYED_UNITS.getAlias(), sumSprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          originalVQ.SELECT(sprayedUnits);
        }

        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias() + "/NULLIF(" + areaAggregation + "." + Alias.AREA_PLANNED_TARGET + ",0)*100.0");
      }

      // The final aggregate value query (since we have 2) is a unique sum aggregate.
      aggVQ = queryFactory.valueQuery();

      Selectable[] originals = this.copyAll(areaAggVQ, areaAggVQ.getSelectableRefs(), null, false, null);
      List<SelectablePrimitive> distincts = new LinkedList<SelectablePrimitive>();
      for (Selectable original : originals)
      {
        // Target week is required for the APT calculation but unless it's also
        // selected as a field don't include it to avoid splitting the rows.
        distincts.add((SelectablePrimitive) original);
      }
      aggVQ.SELECT_DISTINCT(distincts.toArray(new SelectablePrimitive[distincts.size()]));

      partitionBy.add(smallestUniversal.getColumnAlias());
      String overWindow = StringUtils.join(partitionBy, ",");

      SelectableSQL aptOver = (SelectableSQL) aggVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
      String over = "SUM(" + Alias.AREA_PLANNED_TARGET + ") OVER(PARTITION BY " + overWindow + ")";
      aptOver.setSQL(over);

      aggVQ.FROM("(" + areaAggVQ.getSQL() + ")", "uniqueSum");

      for (Selectable sel : finalVQ.getSelectableRefs())
      {
        String alias = sel.getUserDefinedAlias();
        if (toCoalesce.contains(alias))
        {
          String col = sel.getColumnAlias();
          SelectableSQL selSQL = (SelectableSQL) sel;
          selSQL.setSQL("COALESCE(" + areaAggregation + "." + col + ", " + originalAlias + "." + col + ")");
        }
      }
    }

    // Aggregate the operator targets into the WITH clause of a new query
    if (this.needsOperatorPlanned)
    {
      Set<String> toCoalesce = new HashSet<String>();

      if (finalVQ == null)
      {
        finalVQ = new ValueQuery(originalVQ.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(originalVQ, originalVQ.getSelectableRefs(), originalAlias, false, null);
        finalVQ.SELECT(copies);
      }

      String operatorAggregation = "operatorAggregation";
      aggregateAlias = operatorAggregation;

      // create a new IRS Query that aggregates the area targets for the
      // universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps
      // the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.OPERATOR, this.getDisease());

      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();

      // Get the alias of the value query (this.getSprayViewAlias() will return
      // null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();

      aggVQ = qb.getValueQuery();

      HashMap<String, Selectable> toAdd = new HashMap<String, Selectable>(); // We're using a HashMap here so as to guarantee no duplicates

      // Add the selectables required for joining our inner IRSQB table with the outer.
      Selectable opt = aggVQ.aSQLAggregateInteger(Alias.OPERATOR_PLANNED_TARGET.getAlias(), Alias.OPERATOR_PLANNED_TARGET.getAlias());
      Selectable season = aggVQ.aSQLCharacter(aggAlias + "_" + Alias.SPRAY_SEASON.getAlias(), aggAlias + "." + Alias.SPRAY_SEASON.getAlias());
      Selectable disease = aggVQ.aSQLCharacter(aggAlias + "_" + Alias.DISEASE.getAlias(), aggAlias + "." + Alias.DISEASE.getAlias());

      Selectable parentGeo = aggVQ.aSQLCharacter(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
      parentGeo.setColumnAlias(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
      toAdd.put(parentGeo.getResultAttributeName(), parentGeo);
      toAdd.put(opt.getResultAttributeName(), opt);
      toAdd.put(season.getResultAttributeName(), season);
      toAdd.put(disease.getResultAttributeName(), disease);

      for (String group : this.dategroups.keySet())
      {
        Selectable sel = aggVQ.aSQLCharacter(group, group);
        toAdd.put(sel.getResultAttributeName(), sel);
        toCoalesce.add(group);
      }

      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.values().iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while (iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());

        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if (reset != null)
        {
          resetAliases.add(reset);
        }
      }

      // Make sure we preserve any relevant planning selectables from the original query.
      List<Selectable> sels = qb.getValueQuery().getSelectableRefs();
      for (Selectable sel : sels)
      {
        Alias alias = AliasLookup.get(sel.getResultAttributeName());
        if (alias != null && ( alias.hasView(View.PLANNED_OPERATOR) || alias.hasView(View.RESOURCE_TARGET_VIEW) ))
        {
          toAdd.put(sel.getResultAttributeName(), sel);
        }
      }

      qb.resetSelectAliases(resetAliases);

      // replace the selectables
      aggVQ.clearSelectClause();
      aggVQ.SELECT(toAdd.values().toArray(new Selectable[toAdd.size()]));

      // finish construction of the query
      qb.construct(qb.getQueryFactory(), aggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();

      // Push the original query into the FROM clause of the outer query
      // and join on the aggregation. Make sure to include every selectable that
      // is required for the join
      FROM += " FULL OUTER JOIN " + operatorAggregation + " ON " + originalAlias + "." + seasonJoin.getColumnAlias() + " = " + operatorAggregation + "." + season._getAttributeName() + " \n" + "AND " + originalAlias + "." + diseaseJoin.getColumnAlias() + " = " + operatorAggregation + "." + disease._getAttributeName() + " \n" + "AND " + originalAlias + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE
          + " = " + operatorAggregation + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " \n";
      for (String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND " + originalAlias + "." + dateGroup + " = " + operatorAggregation + "." + dateGroup + " \n";
      }

      // The aggregation query needs to sum the operator planned targets
      SelectableSQL aptSel = (SelectableSQL) aggVQ.getSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias());
      String sum = sumColumnForId(sprayViewAlias, Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias, Alias.OPERATOR_PLANNED_TARGET.getAlias());
      aptSel.setSQL(sum);

      // Now that everything is joined, grab the area planned target value from
      // the aggregation query
      if (finalVQ.hasSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(operatorAggregation + "." + Alias.OPERATOR_PLANNED_TARGET);
      }

      if (finalVQ.hasSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required
        // for coverage, add it
        // so the outer query can reference it
        if (!originalVQ.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String sumSprayedUnits = "SUM(" + this.sprayedUnits + ")";
          SelectableSQL sprayedUnits = originalVQ.aSQLAggregateInteger(Alias.SPRAYED_UNITS.getAlias(), sumSprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          originalVQ.SELECT(sprayedUnits);
        }

        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias() + "/NULLIF(" + operatorAggregation + "." + Alias.OPERATOR_PLANNED_TARGET + ",0)*100.0");
      }

      if (finalVQ.hasSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias()))
      {
        if (!originalVQ.hasSelectableRef(Alias.OPERATOR_ACTUAL_TARGET.getAlias()))
        {
          String sql = this.sumOperatorActualTargets();
          SelectableSQL actual = originalVQ.aSQLInteger(Alias.OPERATOR_ACTUAL_TARGET.getAlias(), sql, Alias.OPERATOR_ACTUAL_TARGET.getAlias());
          originalVQ.SELECT(actual);
        }

        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias());
        String sql = "(" + Alias.OPERATOR_ACTUAL_TARGET + "/NULLIF(" + operatorAggregation + "." + Alias.OPERATOR_PLANNED_TARGET + ",0))*100.0";
        aptReplace.setSQL(sql);
      }

      // Because we're doing a right join, which makes the planned operator targets more important,
      // just grab the default locale from operatorAggregation as it will always have a value
      for (Selectable aggSel : aggVQ.getSelectableRefs())
      {
        String alias = aggSel.getUserDefinedAlias();
        if (originalVQ.hasSelectableRef(alias) && finalVQ.hasSelectableRef(alias))
        {
          SelectableSQL swap = (SelectableSQL) finalVQ.getSelectableRef(alias);
          String col = swap.getColumnAlias();
          String c = "COALESCE(" + operatorAggregation + "." + swap.getColumnAlias() + ", " + originalAlias + "." + col + ")";
          swap.setSQL(c);
        }
      }

      for (Selectable sel : finalVQ.getSelectableRefs())
      {
        String alias = sel.getUserDefinedAlias();
        if (toCoalesce.contains(alias))
        {
          String col = sel.getColumnAlias();
          SelectableSQL selSQL = (SelectableSQL) sel;
          selSQL.setSQL("COALESCE(" + operatorAggregation + "." + col + ", " + originalAlias + "." + col + ")");
        }
      }
    }

    if (this.needsTeamsPlanned)
    {
      Set<String> toCoalesce = new HashSet<String>();

      if (finalVQ == null)
      {
        finalVQ = new ValueQuery(originalVQ.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(originalVQ, originalVQ.getSelectableRefs(), originalAlias, false, null);
        finalVQ.SELECT(copies);
      }

      String teamAggregation = "teamAggregation";
      aggregateAlias = teamAggregation;

      // create a new IRS Query that aggregates the area targets for the
      // universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps
      // the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.TEAM, this.getDisease());

      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();

      // Get the alias of the value query (this.getSprayViewAlias() will return
      // null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();

      aggVQ = qb.getValueQuery();

      HashMap<String, Selectable> toAdd = new HashMap<String, Selectable>();
      Selectable opt = aggVQ.aSQLAggregateInteger(Alias.TEAM_PLANNED_TARGET.getAlias(), Alias.TEAM_PLANNED_TARGET.getAlias());
      Selectable season = aggVQ.aSQLCharacter(aggAlias + "_" + Alias.SPRAY_SEASON.getAlias(), aggAlias + "." + Alias.SPRAY_SEASON.getAlias());
      Selectable disease = aggVQ.aSQLCharacter(aggAlias + "_" + Alias.DISEASE.getAlias(), aggAlias + "." + Alias.DISEASE.getAlias());

      Selectable parentGeo = aggVQ.aSQLCharacter(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());
      parentGeo.setColumnAlias(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());

      toAdd.put(parentGeo.getResultAttributeName(), parentGeo);
      toAdd.put(opt.getResultAttributeName(), opt);
      toAdd.put(season.getResultAttributeName(), season);
      toAdd.put(disease.getResultAttributeName(), disease);

      List<Selectable> groupsToAdd = new LinkedList<Selectable>();
      for (String group : this.dategroups.keySet())
      {
        Selectable sel = aggVQ.aSQLCharacter(group, group);
        toAdd.put(sel.getResultAttributeName(), sel);
        toCoalesce.add(group);
      }

      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.values().iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while (iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());

        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if (reset != null)
        {
          resetAliases.add(reset);
        }
      }

      qb.resetSelectAliases(resetAliases);

      // Make sure we preserve any relevant planning selectables from the original query.
      List<Selectable> sels = qb.getValueQuery().getSelectableRefs();
      for (Selectable sel : sels)
      {
        Alias alias = AliasLookup.get(sel.getResultAttributeName());
        if (alias != null && ( alias.hasView(View.PLANNED_TEAM) || alias.hasView(View.RESOURCE_TARGET_VIEW) ))
        {
          toAdd.put(sel.getResultAttributeName(), sel);
        }
      }

      // replace the selectables
      aggVQ.clearSelectClause();
      aggVQ.SELECT(toAdd.values().toArray(new Selectable[toAdd.size()]));

      // finish construction of the query
      qb.construct(qb.getQueryFactory(), aggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();

      // Push the original query into the FROM clause of the outer query
      // and join on the aggregation. Make sure to include every selectable that
      // is required for the join
      FROM += " FULL OUTER JOIN " + teamAggregation + " ON " + originalAlias + "." + seasonJoin.getColumnAlias() + " = " + teamAggregation + "." + season._getAttributeName() + " \n" + "AND " + originalAlias + "." + diseaseJoin.getColumnAlias() + " = " + teamAggregation + "." + disease._getAttributeName() + " \n" + "AND " + originalAlias + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " = "
          + teamAggregation + "." + Alias.SPRAY_TEAM_DEFAULT_LOCALE + " \n";
      for (String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND " + originalAlias + "." + dateGroup + " = " + teamAggregation + "." + dateGroup + " \n";
      }

      // The aggregation query needs to sum the team planned targets
      SelectableSQL aptSel = (SelectableSQL) aggVQ.getSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias());
      String sum = this.sumColumnForId(sprayViewAlias, Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias, Alias.TEAM_PLANNED_TARGET.getAlias());
      aptSel.setSQL(sum);

      // Now that everything is joined, grab the area planned target value from
      // the aggregation query
      if (finalVQ.hasSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(teamAggregation + "." + Alias.TEAM_PLANNED_TARGET);
      }

      if (finalVQ.hasSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required
        // for coverage, add it
        // so the outer query can reference it
        if (!originalVQ.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String sumSprayedUnits = "SUM(" + this.sprayedUnits + ")";
          SelectableSQL sprayedUnits = originalVQ.aSQLAggregateInteger(Alias.SPRAYED_UNITS.getAlias(), sumSprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          originalVQ.SELECT(sprayedUnits);
        }

        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias() + "/NULLIF(" + teamAggregation + "." + Alias.TEAM_PLANNED_TARGET + ",0)*100.0");
      }

      if (finalVQ.hasSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias()))
      {
        if (!originalVQ.hasSelectableRef(Alias.TEAM_ACTUAL_TARGET.getAlias()))
        {
          String sql = this.sumTeamActualTargets();
          SelectableSQL actual = originalVQ.aSQLInteger(Alias.TEAM_ACTUAL_TARGET.getAlias(), sql, Alias.TEAM_ACTUAL_TARGET.getAlias());
          originalVQ.SELECT(actual);
        }

        SelectableSQL aptReplace = (SelectableSQL) finalVQ.getSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias());
        String sql = "(" + Alias.TEAM_ACTUAL_TARGET + "/NULLIF(" + teamAggregation + "." + Alias.TEAM_PLANNED_TARGET + ",0))*100.0";
        aptReplace.setSQL(sql);
      }
    }

    if (finalVQ != null)
    {
      // Set the window count (the total result set).
      String windowCount = "count(*) over()";
      SelectableSQLLong c = finalVQ.isGrouping() ? finalVQ.aSQLAggregateLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS) : finalVQ.aSQLLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS);

      finalVQ.SELECT(c);
      finalVQ.setCountSelectable(c);

      // If we're joining planned and activity make sure we join the queries correctly
      originalVQ.SELECT(seasonJoin, diseaseJoin);

      // (Ticket 3122) If the aggregate VQ has a date attribute then we'll get an error because it says it isn't grouped. Just use the epiweek we have
      // defined in previous WITH tables.
      // The reason this happens is because spray date doesn't exist on the aggregated tables, but there's special date logic that forces the epiweek
      // to be calculated from it.
      if (originalVQ.hasSelectableRef(AbstractSpray.SPRAYDATE) && aggVQ.hasSelectableRef(Alias.DATEGROUP_EPIWEEK.getAlias()))
      {
        ( (SelectableSQL) aggVQ.getSelectableRef(Alias.DATEGROUP_EPIWEEK.getAlias()) ).setSQL("dategroup_epiweek ::integer");
        ;
      }

      entries.add(new WITHEntry(aggregateAlias, aggVQ.getSQL()));
      this.setWITHClause(entries, false, finalVQ);

      finalVQ.FROM("(" + originalVQ.getSQL() + ")", FROM);

      coalesceSelects(finalVQ, aggVQ, originalAlias, aggregateAlias);
    }
    else
    {
      finalVQ = super.postProcess(originalVQ);
    }

    return finalVQ;
  }

  protected String sumDistinctColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "SUM(DISTINCT " + col + ")";
  }

  protected String sumColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "SUM(" + col + ")";
    // return QueryUtil.SUM_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable !=
    // null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table !=
    // null ? table + "." : "" ) + column + "))";
  }

  protected String minColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "MIN(" + col + ")";
    // return QueryUtil.MIN_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable !=
    // null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table !=
    // null ? table + "." : "" ) + column + "))";
  }

  protected String maxColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "MAX(" + col + ")";
    // return QueryUtil.MAX_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable !=
    // null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table !=
    // null ? table + "." : "" ) + column + "))";
  }

  protected String avgColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "AVG(" + col + ")";
    // return QueryUtil.AVG_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable !=
    // null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table !=
    // null ? table + "." : "" ) + column + "))";
  }

  /**
   * Some attributes must be swapped out with custom selectables
   */
  private void swapOutAttributesForAggregates()
  {
    //
    String[] insecticideAliases = new String[] {

        // insecticide usage
        Alias.RECEIVED.getAlias(), Alias.USED.getAlias(), Alias.REFILLS.getAlias(), Alias.RETURNED.getAlias() };

    if (this.setAttributesAsAggregated(insecticideAliases, idCol, irsVQ, sprayViewAlias, true) > 0)
    {
      // JN change: might not need sprayed units based on the above aliases
      // this.needsSprayedUnits = true;
      // needUniqueSprayId = true;
    }

    String[] sprayDetails = new String[] {

        // spray details (attributes defined by HouseholdSprayStatus)
        Alias.HOUSEHOLDS.getAlias(), Alias.SPRAYED_HOUSEHOLDS.getAlias(), Alias.STRUCTURES.getAlias(), Alias.SPRAYED_STRUCTURES.getAlias(), Alias.ROOMS.getAlias(), Alias.SPRAYED_ROOMS.getAlias(), Alias.LOCKED.getAlias(), Alias.REFUSED.getAlias(), Alias.OTHER.getAlias(), Alias.WRONG_SURFACE.getAlias(), Alias.PEOPLE.getAlias() };

    // spray details are unique by the household and structure id
    String detailUniqueId = this.getUniqueSprayDetailsId();
    if (this.setAttributesAsAggregated(sprayDetails, detailUniqueId, irsVQ, sprayViewAlias, true) > 0)
    {
      this.needsSprayedUnits = true;
      // needUniqueSprayId = true;
    }
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
  public Condition addDateCriteria(String tableAlias, boolean addPlanned, String operatorDOB, String leaderDOB, String supervisorDOB)
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
          throw new ProgrammingErrorException("The class [" + klass + "] is not supported as IRS date criteria.");
        }

        Condition cond1 = null;
        if (dateObj.has(QueryUtil.DATE_START) && !dateObj.isNull(QueryUtil.DATE_START) && !dateObj.getString(QueryUtil.DATE_START).equals("null"))
        {
          String startValue = dateObj.getString(QueryUtil.DATE_START);
          SelectableMoment startCrit = v.aSQLDate("start_val", "'" + startValue + "'");

          if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
          {

            if (operatorDOB == null)
            {
              // FIXME this is a hack for Level 3, which doesn't have operators.
              // Refactor with selectable dependencies
              cond1 = AND.get(v.aSQLDate("leader_birthdate_start", prepend + leaderDOB).GE(startCrit), v.aSQLDate("supervisor_birthdate_start", prepend + supervisorDOB).GE(startCrit));

            }
            else
            {
              cond1 = AND.get(v.aSQLDate("operator_birthdate_start", prepend + operatorDOB).GE(startCrit), v.aSQLDate("leader_birthdate_start", prepend + leaderDOB).GE(startCrit), v.aSQLDate("supervisor_birthdate_start", prepend + supervisorDOB).GE(startCrit));
            }

            Alias[] dates = new Alias[] { Alias.SPRAY_OPERATOR_BIRTHDATE, Alias.SPRAY_LEADER_BIRTHDATE, Alias.ZONE_SUPERVISOR_BIRTHDATE };
            this.addPlannedAlias(dates);
            this.addRequiredAlias(View.ALL_ACTUALS, dates);
          }
          else
          {
            if (addPlanned)
            {
              cond1 = OR.get(v.aSQLDate("planned_start_date", prepend + Alias.PLANNED_DATE).GE(startCrit), v.aSQLDate("spray_start_date", prepend + Alias.SPRAY_DATE).GE(startCrit));
              this.addPlannedAlias(Alias.PLANNED_DATE);
              this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
            }
            else
            {
              cond1 = v.aSQLDate("spray_start_date", prepend + Alias.SPRAY_DATE).GE(startCrit);
              this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
            }
          }

          cond = cond1;
        }

        Condition cond2 = null;
        if (dateObj.has(QueryUtil.DATE_END) && !dateObj.isNull(QueryUtil.DATE_END) && !dateObj.getString(QueryUtil.DATE_END).equals("null"))
        {
          String endValue = dateObj.getString(QueryUtil.DATE_END);
          SelectableMoment endCrit = irsVQ.aSQLDate("start_val", "'" + endValue + "'");

          if (this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
          {
            if (operatorDOB == null)
            {
              // FIXME this is a hack for Level 3, which doesn't have operators.
              // Refactor with selectable dependencies
              cond2 = AND.get(v.aSQLDate("leader_birthdate_end", prepend + leaderDOB).LE(endCrit), v.aSQLDate("supervisor_birthdate_end", prepend + supervisorDOB).LE(endCrit));
            }
            else
            {
              cond2 = AND.get(v.aSQLDate("operator_birthdate_end", prepend + operatorDOB).LE(endCrit), v.aSQLDate("leader_birthdate_end", prepend + leaderDOB).LE(endCrit), v.aSQLDate("supervisor_birthdate_end", prepend + supervisorDOB).LE(endCrit));
            }

            Alias[] dates = new Alias[] { Alias.SPRAY_OPERATOR_BIRTHDATE, Alias.SPRAY_LEADER_BIRTHDATE, Alias.ZONE_SUPERVISOR_BIRTHDATE };
            this.addPlannedAlias(dates);
            this.addRequiredAlias(View.ALL_ACTUALS, dates);
          }
          else
          {
            if (addPlanned)
            {
              cond2 = OR.get(v.aSQLDate("planned_end_date", prepend + Alias.PLANNED_DATE).LE(endCrit), v.aSQLDate("spray_end_date", prepend + Alias.SPRAY_DATE).LE(endCrit));
              this.addPlannedAlias(Alias.PLANNED_DATE);
              this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
            }
            else
            {
              cond2 = v.aSQLDate("spray_end_date", prepend + Alias.SPRAY_DATE).LE(endCrit);
              this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
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

  public void addParentAggregate(Alias alias)
  {
    this.parentAggregates.add(alias);
  }

  public boolean hasParentAggregate(Alias alias)
  {
    return this.parentAggregates.contains(alias);
  }

  public void addChildAggregate(Alias alias)
  {
    this.childAggregates.add(alias);
  }

  public boolean hasChildAggregate(Alias alias)
  {
    return this.childAggregates.contains(alias);
  }

  public boolean isAggregate(Alias alias)
  {
    if (this.irsVQ.hasSelectableRef(alias.getAlias()))
    {
      return this.irsVQ.getSelectableRef(alias.toString()).isAggregateFunction();
    }
    else
    {
      return false;
    }
  }

  public Selectable get(Alias alias)
  {
    if (this.irsVQ.hasSelectableRef(alias.getAlias()))
    {
      return this.irsVQ.getSelectableRef(alias.getAlias());
    }
    else
    {
      return null;
    }
  }

  /**
   * Joins the necessary tables to make the main query work (this also includes setting the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    String idCol = QueryUtil.getIdColumn();
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    String leftTable = View.SPRAY_VIEW.getView();
    String leftAlias = this.sprayViewAlias;
    String insecticideView = View.INSECTICIDE_VIEW.getView();

    if (irsVQ.hasSelectableRef(QueryConstants.AUDIT_IMPORTED_ALIAS))
    {
      irsVQ.AND(new LeftJoinEq(Alias.CREATE_DATE.getAlias(), leftTable, leftAlias, Alias.CREATE_DATE.getAlias(), IMPORTED_DATETIME, IMPORTED_DATETIME));
      joinSprayView = false;
    }

    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      irsVQ.AND(new LeftJoinEq(Alias.GEO_ENTITY.getAlias(), leftTable, leftAlias, idCol, QueryUtil.GEO_DISPLAY_LABEL, QueryUtil.GEO_DISPLAY_LABEL));
      joinSprayView = false;
    }

    // Don't add anything regarding insecticide if the query is used for
    // aggregation
    // as aggregation has nothing to do with insecticide
    // if (this.aggType == null)
    // {
    if (insecticideQuery != null)
    {
      SelectableSQLCharacter brand = irsVQ.aSQLCharacter("brand_join", this.sprayViewAlias + "." + Alias.BRAND.getAlias());
      irsVQ.WHERE(this.insecticideQuery.getId().EQ(brand));
      joinSprayView = false;
    }

    if (this.requiredViews.contains(View.INSECTICIDE_VIEW))
    {
      irsVQ.AND(new LeftJoinEq(Alias.BRAND.getAlias(), leftTable, leftAlias, idCol, insecticideView, insecticideView));

      SelectableSQLCharacter insectDisease = irsVQ.aSQLCharacter(diseaseCol, insecticideView + "." + diseaseCol, diseaseCol);
      SelectableSQLCharacter sprayDisease = irsVQ.aSQLCharacter(Alias.DISEASE.getAlias(), leftAlias + "." + Alias.DISEASE, Alias.DISEASE.getAlias());
      irsVQ.AND(insectDisease.EQ(sprayDisease));

      SelectableSQLDate leftSel = irsVQ.aSQLDate(Alias.SPRAY_DATE.getAlias(), leftAlias + "." + Alias.SPRAY_DATE, Alias.SPRAY_DATE.getAlias());

      SelectableSQLDate rightSel = irsVQ.aSQLDate("start_date", insecticideView + "." + "start_date", "start_date");
      irsVQ.AND(leftSel.GE(rightSel));

      rightSel = irsVQ.aSQLDate("end_date", insecticideView + "." + "end_date", "end_date");
      irsVQ.AND(leftSel.LE(rightSel));
      joinSprayView = false;
    }
    // }

    if (joinSprayView)
    {
      irsVQ.FROM(View.SPRAY_VIEW.getView(), this.sprayViewAlias);
    }
  }

  @Override
  protected void joinImported(GeneratedTableClassQuery q, QueryFactory f, ValueQuery v, Selectable importCreateDate)
  {
    // do nothing. Custom join logic is in this.joinMainQueryTables()
  }

  public boolean needsSprayedUnits()
  {
    return this.needsSprayedUnits;
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

    this.hasPlannedTargets = this.needsAreaPlanned || this.needsOperatorPlanned || this.needsTeamsPlanned;

    if (this.needsOperatorPlanned)
    {
      this.addRequiredView(View.PLANNED_OPERATOR);
    }

    if (this.needsAreaPlanned)
    {
      this.addRequiredView(View.PLANNED_AREA);
    }

    if (this.needsTeamsPlanned)
    {
      this.addRequiredView(View.PLANNED_TEAM);
      this.addRequiredView(View.PLANNED_TEAM_ROLLUP);
      this.addRequiredView(View.PLANNED_TEAM_RESULTS);
    }
  }

  @Override
  protected ArrayList<SelectableSQL> getTerms(ValueQuery valueQuery)
  {
    ArrayList<SelectableSQL> sels = new ArrayList<SelectableSQL>();

    if (valueQuery.hasSelectableRef(Alias.STRUCTURE_TYPE.getAlias()))
    {
      sels.add((SelectableSQL) valueQuery.getSelectableRef(Alias.STRUCTURE_TYPE.getAlias()));
    }
    if (valueQuery.hasSelectableRef(Alias.REASON_NOT_SPRAYED.getAlias()))
    {
      sels.add((SelectableSQL) valueQuery.getSelectableRef(Alias.REASON_NOT_SPRAYED.getAlias()));
    }

    return sels;
  }

  String getAbstractSprayAlias()
  {
    return sprayViewAlias;
  }

  // public boolean needUniqueSprayId()
  // {
  // return needUniqueSprayId;
  // }

  @Override
  protected void addCountSelectable(ValueQuery v)
  {
    if (!this.hasPlannedTargets && !v.hasCountSelectable())
    {
      String windowCount = "count(*) over()";
      SelectableSQLLong c = v.isGrouping() ? v.aSQLAggregateLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS) : v.aSQLLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS);

      v.SELECT(c);
      v.setCountSelectable(c);
    }
  }

  // JN change
  // public void setAreaJoin(AreaJoin areaJoin)
  // {
  // this.areaJoin = areaJoin;
  // }

  // JN change
  // @Override
  // protected void setGeoDisplayLabelSQL()
  // {
  // // do nothing
  // }
  //
  // private void setWITHGeoDisplayLabelSQL()
  // {
  // String sql = QueryUtil.getGeoDisplayLabelSQL(false);
  // this.addWITHEntry(new WITHEntry(QueryUtil.GEO_DISPLAY_LABEL, sql));
  // }

  /**
   * Sets the entire contents of the WITH clause for the query. This method MUST EXECUTE LAST so that all the flags and column knowledge will be known
   * to do dependencies.
   */
  private void setWithQuerySQL()
  {
    // a list of all known planned aliases
    Set<String> planned = new HashSet<String>();
    planned.add(Alias.AREA_PLANNED_TARGET.getAlias());
    planned.add(Alias.AREA_PLANNED_COVERAGE.getAlias());
    planned.add(Alias.TEAM_PLANNED_TARGET.getAlias());
    planned.add(Alias.TEAM_PLANNED_COVERAGE.getAlias());
    planned.add(Alias.OPERATOR_PLANNED_TARGET.getAlias());
    planned.add(Alias.OPERATOR_PLANNED_COVERAGE.getAlias());

    Set<String> neutral = new HashSet<String>();
    neutral.add(Alias.COUNT.getAlias());
    neutral.add(Alias.RATIO.getAlias());
    for (String dateGroup : this.dategroups.keySet())
    {
      neutral.add(dateGroup);
    }
    neutral.add(Alias.AUDIT_CREATE_DATE.getAlias());
    neutral.add(Alias.AUDIT_LAST_UPDATE_DATE.getAlias());
    neutral.add(Alias.AUDIT_CREATED_BY.getAlias());
    neutral.add(Alias.AUDIT_LAST_UPDATED_BY.getAlias());
    neutral.add(Alias.AUDIT_IMPORTED.getAlias());

    neutral.add(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());

    neutral.add(Alias.SPRAY_OPERATOR.getAlias());
    neutral.add(Alias.SPRAY_OPERATOR_BIRTHDATE.getAlias());
    neutral.add(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
    neutral.add(Alias.SPRAY_OPERATOR_PERSON.getAlias());
    neutral.add(Alias.SPRAY_OPERATOR_PERSON_ID.getAlias());
    neutral.add(Alias.SPRAY_OPERATOR_SEX.getAlias());

    neutral.add(Alias.SPRAY_LEADER_BIRTHDATE.getAlias());
    neutral.add(Alias.SPRAY_LEADER_DEFAULT_LOCALE.getAlias());
    neutral.add(Alias.SPRAY_LEADER_PERSON.getAlias());
    neutral.add(Alias.SPRAY_LEADER_PERSON_ID.getAlias());
    neutral.add(Alias.SPRAY_LEADER_SEX.getAlias());

    neutral.add(Alias.ZONE_SUPERVISOR_BIRTHDATE.getAlias());
    neutral.add(Alias.ZONE_SUPERVISOR_DEFAULT_LOCALE.getAlias());
    neutral.add(Alias.ZONE_SUPERVISOR_PERSON.getAlias());
    neutral.add(Alias.ZONE_SUPERVISOR_PERSON_ID.getAlias());
    neutral.add(Alias.ZONE_SUPERVISOR_SEX.getAlias());

    // add all universals to the neutral aliases
    for (Universal u : this.universals.values())
    {
      neutral.add(u.getEntityNameAlias());
      neutral.add(u.getGeoIdAlias());
    }

    // any thing that needs sprayed units requires all activity levels
    if (this.needsSprayedUnits)
    {
      this.addRequiredView(View.ALL_ACTUALS);
    }

    // JN change
    // if(!this.needsAreaPlanned)
    // {
    // this.setWITHGeoDisplayLabelSQL();
    // }

    QBInterceptor interceptor = this.getQBInterceptor(this.irsParser);
    if (interceptor != null && interceptor.getGeoCriteriaProcessed() > 0)
    {
      this.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
    }

    if (this.requiredViews.contains(View.PLANNED_OPERATOR) && this.hasUniversal())
    {
      this.addRequiredView(View.ALL_ACTUALS);
      this.hasActivity = true;
    }

    try
    {
      JSONObject selectedUniMap = queryConfig.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);

      Iterator<?> keys = selectedUniMap.keys();
      while (keys.hasNext())
      {
        JSONArray universals = selectedUniMap.getJSONArray((String) keys.next());
        if (universals.length() > 0)
        {
          this.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
          break;
        }
      }
    }
    catch (JSONException e)
    {
      String msg = "IRS QB: Unable to determine if a universal was included as a query column.";
      throw new ProgrammingErrorException(msg, e);
    }

    // Loop through and collect all selectables aliases. Note that
    // other places might set aliases as well, but this is to make
    // sure the obvious Selectables are accounted for.
    this.hasActivity = this.needsSprayedUnits; // all sprayed units require
                                               // activity
    for (Selectable s : this.irsVQ.getSelectableRefs())
    {
      String userDefinedAlias = s.getUserDefinedAlias(); // alias from incoming
                                                         // XML

      if (!neutral.contains(userDefinedAlias) && !planned.contains(userDefinedAlias) && !this.universalAliases.contains(userDefinedAlias))
      {
        this.hasActivity = true;
      }

      Alias alias = AliasLookup.get(userDefinedAlias);
      if (alias != null)
      {
        if (ArrayUtils.contains(alias.getViews(), View.ALL_ACTUALS))
        {
          this.hasActivity = true;
        }

        this.selectAliases.add(alias);
      }
    }

    if (this.hasActivity())
    {
      this.addRequiredView(View.ALL_ACTUALS);
    }

    if (this.dategroups.size() > 0)
    {
      this.addRequiredView(View.DATE_GROUPS);
    }

    // Additional check for any required aliases that may have slipped through the cracks. We won't need this once we have a better dependency
    // management system in place.
    boolean hasPlanned = this.hasPlannedTargets();
    for (Alias alias : selectAliases)
    {
      if (alias.hasView(View.SPRAY_VIEW) && !hasPlanned)
      {
        this.addRequiredAlias(View.ALL_ACTUALS, alias);
        this.addRequiredView(View.ALL_ACTUALS);
      }
    }

    // Invoke each View and load the dependencies.
    View[] views = View.values();
    List<Pair<String, SQLProvider>> viewPairs = new LinkedList<Pair<String, SQLProvider>>();

    // The trick is to load the dependencies in reverse order of the query.
    // This ensures the the later queries load their earlier dependencies.
    SQLProvider sprayView = null;
    for (int i = views.length - 1; i >= 0; i--)
    {
      View view = views[i];
      if (this.requiredViews.contains(view))
      {
        SQLProvider p = view.newInstance(this);

        // load dependencies except for the SprayView (that's done later).
        if (view == View.SPRAY_VIEW)
        {
          sprayView = p;
        }
        else
        {
          p.loadDependencies();
        }

        Pair<String, SQLProvider> pair = new Pair<String, SQLProvider>(view.getView(), p);

        // JN change
        // if(this.needsAreaPlanned && (view == View.PLANNED_AREA
        // || view == View.GEO_TARGET_VIEW
        // || view == View.DATE_EXTRAPOLATION_VIEW))
        // {
        // this.areaPairs.add(pair);
        // }
        // else
        // {
        viewPairs.add(pair);
        // }
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
    // return PRE_AGGREGATION + "." + Alias.OPERATOR_ACTUAL_TARGET;
    return "SUM(" + Alias.OPERATOR_ACTUAL_TARGET + ")";
    // return this.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias,
    // Alias.OPERATOR_ACTUAL_TARGET.getAlias());
  }

  private String sumTeamActualTargets()
  {
    return "SUM(" + Alias.TEAM_ACTUAL_TARGET + ")";
    // return PRE_AGGREGATION + "." + Alias.TEAM_ACTUAL_TARGET;
    // return this.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias,
    // Alias.TEAM_ACTUAL_TARGET.getAlias());
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    return "NULL::" + Alias.OPERATOR_PLANNED_TARGET.getType();
  }

  private String sumTeamPlannedTargets()
  {
    this.needsTeamsPlanned = true;

    // return QueryUtil.sumColumnForId(sprayViewAlias,
    // Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias,
    // Alias.TEAM_PLANNED_TARGET.getAlias());
    return "NULL::" + Alias.TEAM_PLANNED_TARGET.getType();
  }

  private String sumAreaPlannedTargets()
  {
    this.needsAreaPlanned = true;
    return "NULL::" + Alias.AREA_PLANNED_TARGET.getType();
  }

  private void calculateOperatorPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias()))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias());
      String sql = sumOperatorPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias()))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias());
      String sql = sumTeamPlannedTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias()))
    {
      this.addRequiredView(View.ALL_ACTUALS);
      this.addRequiredView(View.PLANNED_OPERATOR);

      this.needsOperatorPlanned = true;

      // JN change
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias());
      // String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF(" +
      // sumOperatorPlannedTargets()
      // + ",0))*100.0";
      calc.setSQL("NULL::" + Alias.OPERATOR_TARGET_DIVERGENCE.getType());
    }
  }

  private void calculateTeamTargetDivergence()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias()))
    {
      this.addRequiredView(View.ALL_ACTUALS);
      this.addRequiredView(View.PLANNED_TEAM);

      this.needsTeamsPlanned = true;

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias());

      // JN change
      // String sql = "(" + this.sumTeamActualTargets() + "/NULLIF(" +
      // this.sumTeamPlannedTargets()
      // + ",0))*100.0";
      calc.setSQL("NULL::" + Alias.TEAM_TARGET_DIVERGENCE.getType());
    }
  }

  private void calculateOperatorTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_TARGETED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
      // this.needUniqueSprayId = true;

      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = this.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_TARGETED_COVERAGE.getAlias());
      String sql = "((" + sum + ")/NULLIF(" + this.sumOperatorActualTargets() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateTeamTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_TARGETED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
      // this.needUniqueSprayId = true;

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_TARGETED_COVERAGE.getAlias());

      String uniqueSprayId = this.getUniqueSprayDetailsId();
      String sum = this.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      String sql = "((" + sum + ")/NULLIF(" + this.sumTeamActualTargets() + ",0))*100.0";
      calc.setSQL(sql);
    }
  }

  private void calculateOperatorPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
      this.needsOperatorPlanned = true;

      String uniqueSprayId = this.getUniqueSprayDetailsId();
      // String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId,
      // null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias());
      // String sql = "((" + sum + ")/NULLIF(" +
      // this.sumOperatorPlannedTargets() + ",0))*100.0";
      // calc.setSQL(sql);
      calc.setSQL("NULL::" + Alias.TEAM_PLANNED_COVERAGE.getType());
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
      this.needsTeamsPlanned = true;

      String uniqueSprayId = this.getUniqueSprayDetailsId();
      // String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId,
      // null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias());
      // String sql = "((" + sum + ")/NULLIF(" + this.sumTeamPlannedTargets() +
      // ",0))*100.0";
      // calc.setSQL(sql);
      calc.setSQL("NULL::" + Alias.TEAM_PLANNED_COVERAGE.getType());
    }
  }

  private void calculateAreaPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias()))
    {
      if (irsVQ.hasSelectableRef(this.smallestUniversalSelectable))
      {
        this.needsAreaPlanned = true;
        this.needsSprayedUnits = true;

        SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
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
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_ACTUAL_TARGET.getAlias()))
    {
      this.addRequiredView(View.ALL_ACTUALS);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_ACTUAL_TARGET.getAlias());
      String sql = this.sumOperatorActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateTeamActualTargets()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_ACTUAL_TARGET.getAlias()))
    {
      this.addRequiredView(View.ALL_ACTUALS);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_ACTUAL_TARGET.getAlias());
      String sql = this.sumTeamActualTargets();
      calc.setSQL(sql);
    }
  }

  private void calculateAreaPlannedTargets()
  {
    if (irsVQ.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias()))
    {
      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());

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

  private void processUniversals()
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
            String universalType = universals.getString(i);
            selectedUniversals[i] = universalType;

            MdEntity uni = MdEntity.getMdEntity(universalType);
            String id = uni.getId();
            String name = uni.getTypeName();

            String entityNameAlias = this.getUniversalEntityName(name, attributeKey);
            String geoIdAlias = this.getUniversalGeoId(name, attributeKey);
            String idAlias = this.getUniversalId(name, attributeKey);

            this.universals.put(universalType, new Universal(name, id, entityNameAlias, geoIdAlias));
            this.universalAliases.add(entityNameAlias);
            this.universalAliases.add(geoIdAlias);
            this.universalAliases.add(idAlias);
          }
          // dss_vector_solutions_intervention_monitor_IndividualCase_probableSource__district_geoId
          this.smallestUnivesal = GeoHierarchy.getMostChildishUniversialType(selectedUniversals);
          this.smallestUniversalSelectable = this.smallestUnivesal.substring(this.smallestUnivesal.lastIndexOf('.')).toLowerCase();
          this.smallestUniversalSelectable = attributeKey + '.' + this.smallestUniversalSelectable + '.' + GeoEntity.GEOID;
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
