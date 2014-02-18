package dss.vector.solutions.querybuilder;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
import dss.vector.solutions.querybuilder.irs.IRSSpoofJoin;
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

  private Map<String, String> dategroups;
  
  // The smallest (most depth) universal selected in the query screen
  private String                            smallestUnivesal;
  
  public static final String AREA_PREFIX = "area_";
  
  public static final String IMPORTED_AREA_DATETIME = "imported_area_datetime";
  
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
  
  private Set<String> universalAliases;
  
  /**
   * A Set of all the select aliases that are used in the query.
   */
  private Set<Alias>                        selectAliases;

  private Map<View, Set<Alias>> requiredAliases;
  
  private Set<Alias> preAggregateAliases;
  
  /**
   * The audit columns.
   */
  private Set<Alias> audits;
  
  private boolean hasActivity;
  
  /**
   * Views required for area planned targets/coverage that will be
   * on the outside of the main query.
   */
  // JN change
//  private List<Pair<String, SQLProvider>> areaPairs; 
  
//  private boolean needUniqueSprayId;
  
  /**
   * True if any part of the query required spray_unit, thus the insecticide view.
   */
  private boolean needsSprayedUnits;

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
    DATE_GROUPS("dateGroups", DateGroups.class), 
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
//    /**
//     * The auto generated of the entity name in the value query.
//     */
//    private String entityNameGenerated;
//    
//    /**
//     * The auto generated of the geo id in the value query.
//     */
//    private String geoIdGenerated;
    
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
    
//    public void setEntityNameGenerated(String entityNameGenerated)
//    {
//      this.entityNameGenerated = entityNameGenerated;
//    }
//    
//    public String getEntityNameGenerated()
//    {
//      return entityNameGenerated;
//    }
//    
//    public void setGeoIdGenerated(String geoIdGenerated)
//    {
//      this.geoIdGenerated = geoIdGenerated;
//    }
//    
//    public String getGeoIdGenerated()
//    {
//      return geoIdGenerated;
//    }

    public String getEntityNamePlanned()
    {
      return this.name.toLowerCase()+"_entityName";
    }
    
    public String getGeoIdPlanned()
    {
      return this.name.toLowerCase()+"_geoid";
    }
  }
  
  private Map<String, Universal> universals;

  private Set<View> requiredViews;
  
  private AggregationQueryType aggType;
  
  /**
   * Name of the sub-query that aggregates activity values ahead of time
   * to avoid cross-products when joining on relationships.
   */
  private static final String PRE_AGGREGATION = "preAggregation";
  
  /**
   * Denotes what type of aggregation this query will be used for (eg, as a query in
   * a WITH clause to aggregate area planned targets).
   */
  private enum AggregationQueryType implements Reloadable
  {
    AREA,
    OPERATOR,
    TEAM
  }

  public IRSQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, AggregationQueryType aggType)
  {
    super(xml, config, layer, pageSize, pageSize);

    this.aggType = aggType;
    
    this.universalAliases = new HashSet<String>();
    
    this.setWITHRecursive(true);
    
    this.hasActivity = false;
    
    this.universals = new HashMap<String, Universal>();
    
    this.requiredAliases = new HashMap<View, Set<Alias>>();

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
    
    this.requiredAliases.put(View.DATE_GROUPS, new HashSet<Alias>());
    
    this.requiredAliases.put(View.ALL_ACTUALS, new HashSet<Alias>());

    this.requiredAliases.put(View.SPRAY_VIEW, new HashSet<Alias>());

    this.dategroups = new HashMap<String, String>();
    
    // The sprayView is always required
    this.requiredViews = new HashSet<View>();
    this.requiredViews.add(View.SPRAY_VIEW);
//    this.requiredViews.add(View.ALL_ACTUALS);

    
    this.selectAliases = new HashSet<Alias>();
    
    this.smallestUnivesal = null;

    diseaseId = Disease.getCurrent().getId();

    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    this.preAggregateAliases = new HashSet<Alias>();
    
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
//    this.areaPairs = new LinkedList<Pair<String, SQLProvider>>();

    queryFactory = new QueryFactory();
    this.sprayVQ = new ValueQuery(queryFactory);
    this.irsVQ = new ValueQuery(queryFactory);
    this.insecticideVQ = new ValueQuery(queryFactory);

    this.hasSprayEnumOrTerm = false;

//    this.needUniqueSprayId = false;
    
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
  
  public IRSQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    this(xml, config, layer, pageNumber, pageSize, null);

  }
  
  public String getSprayViewAlias()
  {
    return sprayViewAlias;
  }

  /**
   * Activity is assumed if there are no planned targets (what else would the user select?)
   * or if activity has been explicitly requested. Activity MUST be false if this query is used
   * for aggregation (WITH clause in a larger query).
   * @return
   */
  public boolean hasActivity()
  {
    // Add actuals (spray activity) if it has explicitly been specified or if COUNT or RATIO exists
    return this.requiredViews.contains(View.ALL_ACTUALS) || this.hasActivity && this.aggType == null || (this.selectAliases.contains(Alias.COUNT) || this.selectAliases.contains(Alias.RATIO));
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
  public void addPlannedAlias(Alias ...alias)
  {
    // Since planned area shares the same Set
    // instance with operator and team planned targets
    // we only need to reference it to set the values
    // for all planned targets.
    this.addRequiredAlias(View.PLANNED_AREA, alias);
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
  
  public Set<String> getDategroups()
  {
    return dategroups.keySet();
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
    insecticideSQLs.addAll(Arrays.asList(new String[] { //"nozzle_defaultLocale", "nozzle_ratio",
        Alias.ACTIVE_INGREDIENT_PER_CAN.getAlias(),
        Alias.STANDARD_APPLICATION_RATE.getAlias(),
        Alias.STANDARD_APPLICATION_RAGE_MG.getAlias(),
        Alias.UNITS_PER_CAN.getAlias() }));

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
    return this.irsVQ.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias())
        || this.irsVQ.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
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

    
    // Two of the insecticide selectables are not on the InsecticideBrand class, so
    // manually include the proper objects to make everything else work
    if((valueQuery.hasSelectableRef(Alias.ACTIVE_INGREDIENT_PER_CAN.getAlias()) ||
        valueQuery.hasSelectableRef(Alias.UNITS_PER_CAN.getAlias())
        ) && this.insecticideQuery == null)
    {
      this.outerInsecticideQuery = new InsecticideBrandQuery(this.irsVQ);
      this.insecticideQuery = new InsecticideBrandQuery(this.irsVQ);
    }

    
    if(this.insecticideQuery != null)
    {
      this.addRequiredView(View.INSECTICIDE_VIEW);
    }
    
    processUniversals();
    
    discoverDateGroups();
    
    filterSelectables();

    swapOutAttributesForAggregates();

    joinSexAttributes();

    
    if (insecticideQuery != null)
    {
      QueryUtil.joinEnumerationDisplayLabels(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery);
      QueryUtil.joinTermAllpaths(insecticideVQ, InsecticideBrand.CLASS, insecticideQuery, this.getTermRestrictions());
      this.setNumericRestrictions(insecticideVQ, queryConfig);
    }

    // Spray Date
    QueryUtil.setSelectabeSQL(irsVQ, AbstractSpray.SPRAYDATE, sprayViewAlias + "." + Alias.SPRAY_DATE);

    // Geo Entity
    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      SelectableSQLCharacter subGeo = (SelectableSQLCharacter) irsVQ
          .getSelectableRef(AbstractSpray.GEOENTITY);
      subGeo.setSQL(QueryUtil.GEO_DISPLAY_LABEL+"."+QueryUtil.LABEL_COLUMN);
      
      // The subselect was incredibly slow
//      QueryUtil.subselectGeoDisplayLabels(subGeo, AbstractSpray.CLASS, AbstractSpray.GEOENTITY,
//          sprayViewAlias + "." + idCol);
    }

    if (this.hasSprayEnumOrTerm)
    {
      QueryUtil.joinEnumerationDisplayLabels(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery);
      QueryUtil.joinTermAllpaths(sprayVQ, AbstractSpray.CLASS, abstractSprayQuery, this.getTermRestrictions());
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
        + " * "+Alias.ACTIVE_INGREDIENT_PER_CAN+") / nullif(SUM(" + sprayedUnits + " * " + Alias.UNIT_AREA
        + "),0)";
    String unit_application_ratio = "((" + unit_application_rate + ") / AVG("+Alias.STANDARD_APPLICATION_RATE+"))";

    if (QueryUtil.setSelectabeSQL(irsVQ, Alias.SPRAYED_UNITS.getAlias(), sprayedUnits))
    {
      setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() },
          uniqueSprayId, irsVQ, this.sprayViewAlias, true, true);
      this.needsSprayedUnits = true;
    }

    if (QueryUtil.setSelectabeSQL(irsVQ, Alias.UNITS_UNSPRAYED.getAlias(), unsprayedUnits))
    {
      setAttributesAsAggregated(new String[] { Alias.UNITS_UNSPRAYED.getAlias() }, uniqueSprayId, irsVQ, null,
          true, true);
      this.needsSprayedUnits = true;
    }

    QueryUtil.setSelectabeSQL(irsVQ, Alias.REFILLS+" * " + shareOfCans, unsprayedUnits);

    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATE.getAlias(), "(" + unit_application_rate + ")"))
    {
      this.needsSprayedUnits = true;
    }
    
    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATE_MG.getAlias(), "1000.0 *" + "("
        + unit_application_rate + ")"))
    {
      this.needsSprayedUnits = true;
    }

    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.UNIT_APPLICATION_RATIO.getAlias(), unit_application_ratio))
    {
      this.needsSprayedUnits = true;
    }

    if(QueryUtil
        .setSelectabeSQL(irsVQ, Alias.UNIT_OPERATIONAL_COVERAGE.getAlias(), unit_operational_coverage + " * 100.0"))
    {
      this.needsSprayedUnits = true;
    }

    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_ROOMS_SPRAYED.getAlias(), "(" + unit_operational_coverage
        + ") * SUM("+Alias.ROOMS.getAlias()+")"))
    {
      this.needsSprayedUnits = true;
    }
    
    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_STRUCTURES_SPRAYED.getAlias(), "(" + unit_operational_coverage
        + ") * SUM("+Alias.STRUCTURES.getAlias()+")"))
    {
      this.needsSprayedUnits = true;
    }
    
    if(QueryUtil.setSelectabeSQL(irsVQ, Alias.CALCULATED_HOUSEHOLDS_SPRAYED.getAlias(), "(" + unit_operational_coverage
        + ") * SUM("+Alias.HOUSEHOLDS.getAlias()+")"))
    {
      this.needsSprayedUnits = true;
    }

    setTargetManagmentCalculations();

    
    // ---- START DATE CRITERIA ----
    Condition dateCond = addDateCriteria(this.sprayViewAlias, this.hasPlannedTargets,
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
      
      if(this.dateCriteria == DateCriteria.PERSON_BIRTHDATE)
      {
        // Throw in a SelectableMoment override that points to Alias.SPRAY_DATE because setQueryDates()
        // will try to use the selected date from the QB, PERSON_BIRTHDATE in this case, and that doesn't
        // make sense.
        SelectableMoment dateSel = irsVQ.aSQLDate("birthdate_override", this.sprayViewAlias+"."+Alias.SPRAY_DATE.getAlias());
//        QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper, dateSel);
      }
      else
      {
//        QueryUtil.setQueryDates(xml, irsVQ, queryConfig, this.mainQueryMap, true, wrapper);
      }
    }
    // ---- END DATE CRITERIA ----
    
    
    
    /* DATE POST PROCESSING */
    if (this.hasPlannedTargets)
    {
      // Set the other date groupings to null for the planning rows
      for (String group : this.dategroups.keySet())
      {
        if (irsVQ.hasSelectableRef(group))
        {
          SelectableSQL sel = (SelectableSQL) irsVQ.getSelectableRef(group);
          String original = sel.getSQL();

          String sprayDateCol = sprayViewAlias + "." + Alias.SPRAY_DATE.getAlias();
          String plannedDateCol = sprayViewAlias + "." + Alias.PLANNED_DATE.getAlias();
          
          this.addRequiredAlias(View.PLANNED_AREA, Alias.PLANNED_DATE);
          this.addRequiredAlias(View.ALL_ACTUALS, Alias.SPRAY_DATE);
          

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
   * Locates all the date group fields within the query.
   */
  private void discoverDateGroups()
  {
    String[] groups = new String[] { QueryUtil.DATEGROUP_EPIWEEK, QueryUtil.DATEGROUP_MONTH,
        QueryUtil.DATEGROUP_QUARTER, QueryUtil.DATEGROUP_EPIYEAR, QueryUtil.DATEGROUP_CALENDARYEAR, 
        QueryUtil.DATEGROUP_SEASON };
    
    for(String group : groups)
    {
      if(this.irsVQ.hasSelectableRef(group))
      {
        this.dategroups.put(group, group);
      }
    }
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
  
  /**
   * Joins the planned target aggregation results with the original query.
   */
  @Override
  protected ValueQuery postProcess(ValueQuery valueQuery)
  {
    
    // skip this processing if this query is for aggregation and
    // by doing so we'll avoid infinite recursion.
    if(this.aggType != null)
    {
      return super.postProcess(valueQuery);
    }
    
    List<WITHEntry> entries = new LinkedList<WITHEntry>();
    ValueQuery toReturn = null;

    String originalVQ = "original_vq";
    String FROM = originalVQ;
    
    // Push the original query into the FROM clause of the outer query
    // and join on the aggregation. Make sure to include every selectable that
    // is required for the join
    SelectableSQL seasonJoin = valueQuery.aSQLCharacter(this.sprayViewAlias+"_"+Alias.SPRAY_SEASON.getAlias(), 
        this.sprayViewAlias+"."+Alias.SPRAY_SEASON.getAlias(), Alias.SPRAY_SEASON.getAlias());
    seasonJoin.setColumnAlias(this.sprayViewAlias+"_"+Alias.SPRAY_SEASON.getAlias());
  
    Selectable diseaseJoin = valueQuery.aSQLCharacter(this.sprayViewAlias+"_"+Alias.DISEASE.getAlias(), 
        this.sprayViewAlias+"."+Alias.DISEASE.getAlias(), Alias.DISEASE.getAlias());
    diseaseJoin.setColumnAlias(this.sprayViewAlias+"_"+Alias.DISEASE.getAlias());
    
    if(this.needsAreaPlanned)
    {
      String areaAggregation = "areaAggregation";
      
      if(toReturn == null)
      {
        toReturn = new ValueQuery(valueQuery.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(valueQuery, valueQuery.getSelectableRefs(), originalVQ, false, null);
        toReturn.SELECT(copies);
      }
      
      // create a new IRS Query that aggregates the area targets for the universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.AREA);
      
      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();
      
      // Get the alias of the value query (this.getSprayViewAlias() will return null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();
      
      ValueQuery aggVQ = qb.getValueQuery();
      
      List<Selectable> toAdd = new LinkedList<Selectable>();
      Selectable smallestUni = aggVQ.getSelectableRef(this.smallestUniversalSelectable);
      Selectable apt = aggVQ.aSQLAggregateInteger(Alias.AREA_PLANNED_TARGET.getAlias(), Alias.AREA_PLANNED_TARGET.getAlias());
      Selectable season = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.SPRAY_SEASON.getAlias(), aggAlias+"."+Alias.SPRAY_SEASON.getAlias());
      Selectable disease = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.DISEASE.getAlias(), aggAlias+"."+Alias.DISEASE.getAlias());
      
      
      Selectable parentGeo = aggVQ.aSQLCharacter(Alias.PARENT_GEO_ENTITY.getAlias(), 
         Alias.PARENT_GEO_ENTITY.getAlias(), Alias.PARENT_GEO_ENTITY.getAlias());
      parentGeo.setColumnAlias(Alias.PARENT_GEO_ENTITY.getAlias());
      toAdd.add(parentGeo);
      
      toAdd.add(smallestUni);
      toAdd.add(apt);
      toAdd.add(season);
      toAdd.add(disease);
      
      List<Selectable> groupsToAdd = new LinkedList<Selectable>();
      for(String group : this.dategroups.keySet())
      {
        groupsToAdd.add(aggVQ.aSQLCharacter(group, group));
      }

      toAdd.addAll(groupsToAdd);
      
      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while(iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());
        
        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if(reset != null)
        {
          resetAliases.add(reset);
        }
      }
      
      qb.resetSelectAliases(resetAliases);
      
      // replace the selectables
      aggVQ.clearSelectClause();
      aggVQ.SELECT(toAdd.toArray(new Selectable[toAdd.size()]));
      
      // finish construction of the query
      qb.construct(qb.getQueryFactory(), aggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();      
      
      parentGeo = aggVQ.aSQLCharacter(Alias.PARENT_GEO_ENTITY.getAlias(), 
          Alias.PARENT_GEO_ENTITY.getAlias(), Alias.PARENT_GEO_ENTITY.getAlias());
       parentGeo.setColumnAlias(Alias.PARENT_GEO_ENTITY.getAlias());
       valueQuery.SELECT(parentGeo);
      
      FROM +=" LEFT JOIN "+areaAggregation+" ON "+
      originalVQ+"."+seasonJoin.getColumnAlias()+" = "+areaAggregation+"."+season._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+diseaseJoin.getColumnAlias()+" = "+areaAggregation+"."+disease._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+Alias.PARENT_GEO_ENTITY+" = "+areaAggregation+"."+Alias.PARENT_GEO_ENTITY+" \n";
      for(String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND "+originalVQ+"."+dateGroup+" = "+areaAggregation+"."+dateGroup+" \n";
      }
      
      // The aggregation query needs to sum the area planned targets
      SelectableSQL aptSel = (SelectableSQL) aggVQ.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
      String func = QueryConstants.SUM_AREA_TARGETS + "(" + aggAlias+"."+Alias.PARENT_GEO_ENTITY + ", to_char(" + aggAlias+"."+Alias.TARGET_WEEK
          + "-1, 'FM99'), " + aggAlias+"."+Alias.DISEASE + ", " + aggAlias+"."+Alias.SPRAY_SEASON + ")";
      String sum = sumColumnForId(null, Alias.TARGET_WEEK.getAlias(), null, "("+func+")");
      aptSel.setSQL(sum);
      
      // Now that everything is joined, grab the area planned target value from the aggregation query
      if(toReturn.hasSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.AREA_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(areaAggregation+"."+Alias.AREA_PLANNED_TARGET);
      }
      
      if(toReturn.hasSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required for coverage, add it
        // so the outer query can reference it
        if(!valueQuery.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String uniqueSprayId = this.getUniqueSprayDetailsId();

          SelectableSQL sprayedUnits = valueQuery.aSQLInteger(Alias.SPRAYED_UNITS.getAlias(), this.sprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          valueQuery.SELECT(sprayedUnits);
          
          setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() },
              uniqueSprayId, valueQuery, this.sprayViewAlias, true, true);
        }
        
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.AREA_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias()+"/NULLIF("+areaAggregation+"."+Alias.AREA_PLANNED_TARGET+",0)*100.0");
      }
      
      // Set the aggregation as a subquery in the WITH clause
      
      entries.add(new WITHEntry(areaAggregation, aggVQ.getSQL()));
      this.setWITHClause(entries, false, toReturn);
    }

    // Aggregate the operator targets into the WITH clause of a new query
    if(this.needsOperatorPlanned)
    {
      if(toReturn == null)
      {
        toReturn = new ValueQuery(valueQuery.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(valueQuery, valueQuery.getSelectableRefs(), originalVQ, false, null);
        toReturn.SELECT(copies);
      }
      
      String operatorAggregation = "operatorAggregation";
      
      // create a new IRS Query that aggregates the area targets for the universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.OPERATOR);
      
      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();
      
      // Get the alias of the value query (this.getSprayViewAlias() will return null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();
      
      ValueQuery aggVQ = qb.getValueQuery();
      
      List<Selectable> toAdd = new LinkedList<Selectable>();
      Selectable opt = aggVQ.aSQLAggregateInteger(Alias.OPERATOR_PLANNED_TARGET.getAlias(), Alias.OPERATOR_PLANNED_TARGET.getAlias());
      Selectable season = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.SPRAY_SEASON.getAlias(), aggAlias+"."+Alias.SPRAY_SEASON.getAlias());
      Selectable disease = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.DISEASE.getAlias(), aggAlias+"."+Alias.DISEASE.getAlias());
      
      
      Selectable parentGeo = aggVQ.aSQLCharacter(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias(), 
         Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
      parentGeo.setColumnAlias(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE.getAlias());
      toAdd.add(parentGeo);

      
      toAdd.add(opt);
      toAdd.add(season);
      toAdd.add(disease);
      
      List<Selectable> groupsToAdd = new LinkedList<Selectable>();
      for(String group : this.dategroups.keySet())
      {
        groupsToAdd.add(aggVQ.aSQLCharacter(group, group));
      }

      toAdd.addAll(groupsToAdd);
      
      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while(iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());
        
        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if(reset != null)
        {
          resetAliases.add(reset);
        }
      }
      
      qb.resetSelectAliases(resetAliases);
      
      // replace the selectables
      aggVQ.clearSelectClause();
      aggVQ.SELECT(toAdd.toArray(new Selectable[toAdd.size()]));
      
      // finish construction of the query
      qb.construct(qb.getQueryFactory(), aggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();      
      

      // Push the original query into the FROM clause of the outer query
      // and join on the aggregation. Make sure to include every selectable that
      // is required for the join
      FROM += " LEFT JOIN "+operatorAggregation+" ON "+
      originalVQ+"."+seasonJoin.getColumnAlias()+" = "+operatorAggregation+"."+season._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+diseaseJoin.getColumnAlias()+" = "+operatorAggregation+"."+disease._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+Alias.SPRAY_OPERATOR_DEFAULT_LOCALE+" = "+operatorAggregation+"."+Alias.SPRAY_OPERATOR_DEFAULT_LOCALE+" \n";
      for(String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND "+originalVQ+"."+dateGroup+" = "+operatorAggregation+"."+dateGroup+" \n";
      }
      
      // The aggregation query needs to sum the operator planned targets
      SelectableSQL aptSel = (SelectableSQL) aggVQ.getSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias());
      String sum = sumColumnForId(sprayViewAlias, Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias,
          Alias.OPERATOR_PLANNED_TARGET.getAlias());
      aptSel.setSQL(sum);
      
      // Now that everything is joined, grab the area planned target value from the aggregation query
      if(toReturn.hasSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.OPERATOR_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(operatorAggregation+"."+Alias.OPERATOR_PLANNED_TARGET);
      }
      
      if(toReturn.hasSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required for coverage, add it
        // so the outer query can reference it
        if(!valueQuery.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String uniqueSprayId = this.getUniqueSprayDetailsId();

          SelectableSQL sprayedUnits = valueQuery.aSQLInteger(Alias.SPRAYED_UNITS.getAlias(), this.sprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          valueQuery.SELECT(sprayedUnits);
          
          setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() },
              uniqueSprayId, valueQuery, this.sprayViewAlias, true, true);
        }
        
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias()+"/NULLIF("+operatorAggregation+"."+Alias.OPERATOR_PLANNED_TARGET+",0)*100.0");
      }
      
      if(toReturn.hasSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias()))
      {
        if(!valueQuery.hasSelectableRef(Alias.OPERATOR_ACTUAL_TARGET.getAlias()))
        {
          String sql = this.sumOperatorActualTargets();
          SelectableSQL actual = valueQuery.aSQLInteger(Alias.OPERATOR_ACTUAL_TARGET.getAlias(), sql, Alias.OPERATOR_ACTUAL_TARGET.getAlias());
          valueQuery.SELECT(actual);
        }
        
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.OPERATOR_TARGET_DIVERGENCE.getAlias());
        String sql = "(" + Alias.OPERATOR_ACTUAL_TARGET + "/NULLIF("+operatorAggregation+"."+Alias.OPERATOR_PLANNED_TARGET+",0))*100.0";
        aptReplace.setSQL(sql);
      }
      
      // Set the aggregation as a subquery in the WITH clause
      entries.add(new WITHEntry(operatorAggregation, aggVQ.getSQL()));
    }
    
    if(this.needsTeamsPlanned)
    {
      if(toReturn == null)
      {
        toReturn = new ValueQuery(valueQuery.getQueryFactory());
        // Select all of the columns from the original
        Selectable[] copies = this.copyAll(valueQuery, valueQuery.getSelectableRefs(), originalVQ, false, null);
        toReturn.SELECT(copies);
      }
      

      String teamAggregation = "teamAggregation";
      
      
      // create a new IRS Query that aggregates the area targets for the universals selected.
      // The new query will be a WITH clause entry on the outer query that wraps the original.
      IRSQB qb = new IRSQB(this.getXml(), this.getConfig(), null, null, null, AggregationQueryType.TEAM);
      
      // modify the value query to remove unwanted Selectables
      qb.initialConstruct();
      
      // Get the alias of the value query (this.getSprayViewAlias() will return null at this point).
      String aggAlias = qb.getQueryMap().get(AbstractSpray.CLASS).getTableAlias();
      
      ValueQuery aggVQ = qb.getValueQuery();
      
      List<Selectable> toAdd = new LinkedList<Selectable>();
      Selectable opt = aggVQ.aSQLAggregateInteger(Alias.TEAM_PLANNED_TARGET.getAlias(), Alias.TEAM_PLANNED_TARGET.getAlias());
      Selectable season = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.SPRAY_SEASON.getAlias(), aggAlias+"."+Alias.SPRAY_SEASON.getAlias());
      Selectable disease = aggVQ.aSQLCharacter(aggAlias+"_"+Alias.DISEASE.getAlias(), aggAlias+"."+Alias.DISEASE.getAlias());
      
      
      Selectable parentGeo = aggVQ.aSQLCharacter(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias(), 
         Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias(), Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());
      parentGeo.setColumnAlias(Alias.SPRAY_TEAM_DEFAULT_LOCALE.getAlias());
      toAdd.add(parentGeo);

      
      toAdd.add(opt);
      toAdd.add(season);
      toAdd.add(disease);
      
      List<Selectable> groupsToAdd = new LinkedList<Selectable>();
      for(String group : this.dategroups.keySet())
      {
        groupsToAdd.add(aggVQ.aSQLCharacter(group, group));
      }

      toAdd.addAll(groupsToAdd);
      
      // set the user defined alias to the attribute name
      Iterator<Selectable> iter = toAdd.iterator();
      Set<Alias> resetAliases = new HashSet<Alias>();
      while(iter.hasNext())
      {
        Selectable sel = iter.next();
        sel.setUserDefinedAlias(sel._getAttributeName());
        
        Alias reset = AliasLookup.get(sel.getUserDefinedAlias());
        if(reset != null)
        {
          resetAliases.add(reset);
        }
      }
      
      qb.resetSelectAliases(resetAliases);
      
      // replace the selectables
      aggVQ.clearSelectClause();
      aggVQ.SELECT(toAdd.toArray(new Selectable[toAdd.size()]));
      
      // finish construction of the query
      qb.construct(qb.getQueryFactory(), aggVQ, qb.getQueryMap(), qb.getXml(), qb.getQueryConfig());
      qb.finishConstruct();      
      

      // Push the original query into the FROM clause of the outer query
      // and join on the aggregation. Make sure to include every selectable that
      // is required for the join
      FROM += " LEFT JOIN "+teamAggregation+" ON "+
      originalVQ+"."+seasonJoin.getColumnAlias()+" = "+teamAggregation+"."+season._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+diseaseJoin.getColumnAlias()+" = "+teamAggregation+"."+disease._getAttributeName()+" \n"+
      "AND "+originalVQ+"."+Alias.SPRAY_TEAM_DEFAULT_LOCALE+" = "+teamAggregation+"."+Alias.SPRAY_TEAM_DEFAULT_LOCALE+" \n";
      for(String dateGroup : this.dategroups.keySet())
      {
        FROM += "AND "+originalVQ+"."+dateGroup+" = "+teamAggregation+"."+dateGroup+" \n";
      }
      
      // The aggregation query needs to sum the team planned targets
      SelectableSQL aptSel = (SelectableSQL) aggVQ.getSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias());
      String sum = this.sumColumnForId(sprayViewAlias, Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias,
          Alias.TEAM_PLANNED_TARGET.getAlias());
      aptSel.setSQL(sum);
      
      // Now that everything is joined, grab the area planned target value from the aggregation query
      if(toReturn.hasSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias()))
      {
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.TEAM_PLANNED_TARGET.getAlias());
        aptReplace.setSQL(teamAggregation+"."+Alias.TEAM_PLANNED_TARGET);
      }
      
      if(toReturn.hasSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias()))
      {
        // If the original query doesn't have sprayed units, which is required for coverage, add it
        // so the outer query can reference it
        if(!valueQuery.hasSelectableRef(Alias.SPRAYED_UNITS.getAlias()))
        {
          String uniqueSprayId = this.getUniqueSprayDetailsId();

          SelectableSQL sprayedUnits = valueQuery.aSQLInteger(Alias.SPRAYED_UNITS.getAlias(), this.sprayedUnits, Alias.SPRAYED_UNITS.getAlias());
          valueQuery.SELECT(sprayedUnits);
          
          this.setAttributesAsAggregated(new String[] { Alias.SPRAYED_UNITS.getAlias() },
              uniqueSprayId, valueQuery, this.sprayViewAlias, true, true);
        }
        
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias());
        aptReplace.setSQL(Alias.SPRAYED_UNITS.getAlias()+"/NULLIF("+teamAggregation+"."+Alias.TEAM_PLANNED_TARGET+",0)*100.0");
      }
      
      if(toReturn.hasSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias()))
      {
        if(!valueQuery.hasSelectableRef(Alias.TEAM_ACTUAL_TARGET.getAlias()))
        {
          String sql = this.sumTeamActualTargets();
          SelectableSQL actual = valueQuery.aSQLInteger(Alias.TEAM_ACTUAL_TARGET.getAlias(), sql, Alias.TEAM_ACTUAL_TARGET.getAlias());
          valueQuery.SELECT(actual);
        }
        
        SelectableSQL aptReplace = (SelectableSQL) toReturn.getSelectableRef(Alias.TEAM_TARGET_DIVERGENCE.getAlias());
        String sql = "(" + Alias.TEAM_ACTUAL_TARGET + "/NULLIF("+teamAggregation+"."+Alias.TEAM_PLANNED_TARGET+",0))*100.0";
        aptReplace.setSQL(sql);
      }
      
      // Set the aggregation as a subquery in the WITH clause
      entries.add(new WITHEntry(teamAggregation, aggVQ.getSQL()));
    }
    
    if(toReturn != null)
    {
      String windowCount = "count(*) over()";
      SelectableSQLLong c = toReturn.isGrouping() ? 
          toReturn.aSQLAggregateLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS) :
            toReturn.aSQLLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS);
        
          toReturn.SELECT(c);
          toReturn.setCountSelectable(c);
          
     valueQuery.SELECT(seasonJoin, diseaseJoin);
     this.setWITHClause(entries, false, toReturn);
     toReturn.FROM("("+valueQuery.getSQL()+")", FROM);
    }
    else
    {
      toReturn = super.postProcess(valueQuery);
    }
    
    return toReturn;
  }

 
  private Selectable[] copyAll(ValueQuery vq, List<Selectable> sels, String prefix, boolean preserveAggregates, ValueQuery original)
  {
    Selectable[] replacements = new Selectable[sels.size()];
    
    SelectableSQL newSel;
    int count = 0;
    for (Selectable sel : sels)
    {
      String alias = sel.getUserDefinedAlias();
      // create a new Selectable based off the original type (because they are
      // custom formatted later on)
      String qualifiedCol = prefix != null ? prefix+"."+sel.getColumnAlias() : sel.getColumnAlias();
      if (sel instanceof SelectableInteger)
      {
        if(preserveAggregates && 
            original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateInteger(sel.getColumnAlias(), qualifiedCol,
            sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLInteger(sel.getColumnAlias(), qualifiedCol,
            sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableLong)
      {
        if(preserveAggregates && 
            original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateLong(sel.getColumnAlias(), qualifiedCol, 
              sel.getUserDefinedAlias(),
              sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLLong(sel.getColumnAlias(), qualifiedCol, 
              sel.getUserDefinedAlias(),
              sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableFloat)
      {
        if(preserveAggregates && 
            original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateFloat(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLFloat(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableDecimal)
      {
        if(preserveAggregates && 
            original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateDecimal(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLDecimal(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof SelectableDouble)
      {
        if(preserveAggregates && 
            original.hasSelectableRef(alias) && original.getSelectableRef(alias).isAggregateFunction())
        {
          newSel = vq.aSQLAggregateDouble(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
        else
        {
          newSel = vq.aSQLDouble(sel.getColumnAlias(), qualifiedCol,
              sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
        }
      }
      else if (sel instanceof AttributeDate || sel instanceof SelectableSQLDate)
      {
        newSel = vq.aSQLDate(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(),
            sel.getUserDefinedDisplayLabel());
      }
      else if (sel instanceof AttributeDateTime || sel instanceof SelectableSQLDateTime)
      {
        newSel = vq.aSQLDateTime(sel.getColumnAlias(), qualifiedCol,
            sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }
      else if (sel instanceof AttributeTime || sel instanceof SelectableSQLTime)
      {
        newSel = vq.aSQLTime(sel.getColumnAlias(), qualifiedCol, sel.getUserDefinedAlias(),
            sel.getUserDefinedDisplayLabel());
      }
      else
      {
        // use character as the final default because it's flexible
        newSel = vq.aSQLCharacter(sel.getColumnAlias(), qualifiedCol,
            sel.getUserDefinedAlias(), sel.getUserDefinedDisplayLabel());
      }

      newSel.setColumnAlias(sel.getColumnAlias());

      replacements[count++] = newSel;
    }
    
    return replacements;
  }
  
  protected String sumColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "SUM("+col+")";
//    return QueryUtil.SUM_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String minColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "MIN("+col+")";
//    return QueryUtil.MIN_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String maxColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "MAX("+col+")";
//    return QueryUtil.MAX_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
  }

  protected String avgColumnForId(String sourceTable, String uniqueId, String table, String column)
  {
    String col = ( table != null ? table + "." : "" ) + column;
    return "AVG("+col+")";
//    return QueryUtil.AVG_FUNCTION + "(array_agg(DISTINCT " + ( sourceTable != null ? sourceTable + "." : "" ) + uniqueId + "|| '~' ||" + ( table != null ? table + "." : "" ) + column + "))";
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

    if(this.setAttributesAsAggregated(insecticideAliases, idCol, irsVQ, sprayViewAlias, true) > 0)
    {
      this.needsSprayedUnits = true;
//      needUniqueSprayId = true;
    }

    String[] sprayDetails = new String[] {

        // spray details (attributes defined by HouseholdSprayStatus)
        Alias.HOUSEHOLDS.getAlias(), Alias.SPRAYED_HOUSEHOLDS.getAlias(), Alias.STRUCTURES.getAlias(),
        Alias.SPRAYED_STRUCTURES.getAlias(), Alias.ROOMS.getAlias(), Alias.SPRAYED_ROOMS.getAlias(),
        Alias.LOCKED.getAlias(), Alias.REFUSED.getAlias(), Alias.OTHER.getAlias(),
        Alias.WRONG_SURFACE.getAlias(), Alias.PEOPLE.getAlias() };

    // spray details are unique by the household and structure id
    String detailUniqueId = this.getUniqueSprayDetailsId();
    if(this.setAttributesAsAggregated(sprayDetails, detailUniqueId, irsVQ, sprayViewAlias, true) > 0)
    {
      this.needsSprayedUnits = true;
//      needUniqueSprayId = true;
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
            
            Alias[] dates = new Alias[]{Alias.SPRAY_OPERATOR_BIRTHDATE, Alias.SPRAY_LEADER_BIRTHDATE, Alias.ZONE_SUPERVISOR_BIRTHDATE};
            this.addPlannedAlias(dates);
            this.addRequiredAlias(View.ALL_ACTUALS, dates);
          }
          else
          {
            if (addPlanned)
            {
              cond1 = OR.get(v.aSQLDate("planned_start_date", prepend + Alias.PLANNED_DATE)
                  .GE(startCrit),
                  v.aSQLDate("spray_start_date", prepend + Alias.SPRAY_DATE).GE(startCrit));
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
            
            Alias[] dates = new Alias[]{Alias.SPRAY_OPERATOR_BIRTHDATE, Alias.SPRAY_LEADER_BIRTHDATE, Alias.ZONE_SUPERVISOR_BIRTHDATE};
            this.addPlannedAlias(dates);
            this.addRequiredAlias(View.ALL_ACTUALS, dates);
          }
          else
          {
            if (addPlanned)
            {
              cond2 = OR.get(v.aSQLDate("planned_end_date", prepend + Alias.PLANNED_DATE).LE(endCrit), v
                  .aSQLDate("spray_end_date", prepend + Alias.SPRAY_DATE).LE(endCrit));
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
  
  public boolean needsPreAggregation()
  {
    return this.preAggregateAliases.size() > 0;
  }

  /**
   * Joins the necessary tables to make the main query work (this also includes
   * setting the proper WHERE criteria on dates).
   */
  private void joinMainQueryTables()
  {
    // FIXME This hack is needed to avoid specifying the abstract_table alias more than
    // once when other joins are added.
     String abstractSprayTable = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS).getTableName();
     IRSSpoofJoin join = new IRSSpoofJoin(idCol, abstractSprayTable, this.sprayViewAlias, idCol, abstractSprayTable, this.sprayViewAlias);
     irsVQ.AND(join);

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
      str.append(" " + IMPORTED_DATETIME + "." + Alias.CREATE_DATE);
      str.append(" = " + leftAlias + "." + Alias.CREATE_DATE.getAlias() + " \n");
    }
    
    if (irsVQ.hasSelectableRef(AbstractSpray.GEOENTITY))
    {
      str.append(" LEFT JOIN "+QueryUtil.GEO_DISPLAY_LABEL+" ON "+
        QueryUtil.GEO_DISPLAY_LABEL+"."+idCol+" = "+this.sprayViewAlias+"."+Alias.GEO_ENTITY+" \n");
    }
    
    /*
     * Create the pre-aggregation subquery if relationships are being joined. If class A is a parent of B
     * in a relationship, duplicates of A can occur, which will happen on activity in this domain.
     */
    if(this.needsPreAggregation())
    {
      Object[] groups = this.dategroups.keySet().toArray();
      String groupsSQL = "";
      if(groups.length > 0)
      {
        groupsSQL = ", " + StringUtils.join(groups, ", ");
      }
      
      str.append(" LEFT JOIN \n");
      str.append("( \n");
      str.append("  SELECT \n");
      
      List<Alias> groupBy = new LinkedList<Alias>();
      
      for(Alias preAgg : this.preAggregateAliases)
      {
        String agg = "";
        if(preAgg == Alias.OPERATOR_ACTUAL_TARGET)
        {
          agg = "SUM("+preAgg+") OVER(PARTITION BY "+Alias.SPRAY_OPERATOR_DEFAULT_LOCALE+", "+Alias.DISEASE+" "+groupsSQL+") "+preAgg+", \n";
          
          groupBy.add(Alias.OPERATOR_ACTUAL_TARGET);
          groupBy.add(Alias.SPRAY_OPERATOR_DEFAULT_LOCALE);
        }
        else if(preAgg == Alias.TEAM_ACTUAL_TARGET)
        {
          agg = "SUM("+preAgg+") OVER(PARTITION BY "+Alias.SPRAY_TEAM_DEFAULT_LOCALE+", "+Alias.DISEASE+" "+groupsSQL+") "+preAgg+", \n";
          
          groupBy.add(Alias.TEAM_ACTUAL_TARGET);
          groupBy.add(Alias.SPRAY_TEAM_DEFAULT_LOCALE);
        }
        
        // Add the Alias to the main query's group by clause since it's no longer an aggregation within that context
        String targetGroupName = "preAggregation_"+preAgg;
        SelectableSQL targetGroup = irsVQ.aSQLInteger(targetGroupName, PRE_AGGREGATION+"."+preAgg, targetGroupName);
        targetGroup.setColumnAlias(targetGroup.getSQL());
        
        irsVQ.GROUP_BY(targetGroup);
        
        str.append(agg);
      }
      
      str.append("  "+Alias.ID+" \n");
      str.append("  FROM "+View.SPRAY_VIEW.getView()+" \n");
      
      String grouping = StringUtils.join(groupBy, ", ");
      str.append("  GROUP BY "+grouping+", "+Alias.DISEASE+", "+Alias.ID+" "+groupsSQL+" \n");
      
      
      str.append(") "+PRE_AGGREGATION+" \n");
      str.append("ON "+PRE_AGGREGATION+"."+Alias.ID+" = "+this.sprayViewAlias+"."+Alias.ID+" \n");
      

    }

    // Don't add anything regarding insecticide if the query is used for aggregation
    // as aggregation has nothing to do with insecticide
    if(this.aggType == null)
    {
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
      // FIXME This now breaks...is it needed?
      //str.append(" OR " + leftAlias + "." + Alias.SPRAY_DATE + " IS NULL \n");
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

    this.hasPlannedTargets = this.needsAreaPlanned || this.needsOperatorPlanned
        || this.needsTeamsPlanned;
    
    if(this.needsOperatorPlanned)
    {
      this.addRequiredView(View.PLANNED_OPERATOR);
    }
    
    if(this.needsAreaPlanned)
    {
      this.addRequiredView(View.PLANNED_AREA);
    }
    
    if(this.needsTeamsPlanned)
    {
      this.addRequiredView(View.PLANNED_TEAM);
      this.addRequiredView(View.PLANNED_TEAM_ROLLUP);
      this.addRequiredView(View.PLANNED_TEAM_RESULTS);
    }
  }

  String getAbstractSprayAlias()
  {
    return sprayViewAlias;
  }
  
//  public boolean needUniqueSprayId()
//  {
//    return needUniqueSprayId;
//  }
  
  @Override
  protected void addCountSelectable(ValueQuery v)
  {
    if(!this.hasPlannedTargets && !v.hasCountSelectable())
    {
      String windowCount = "count(*) over()";
      SelectableSQLLong c = v.isGrouping() ? 
        v.aSQLAggregateLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS) :
          v.aSQLLong(WINDOW_COUNT_ALIAS, windowCount, WINDOW_COUNT_ALIAS);
        
      v.SELECT(c);
      v.setCountSelectable(c);
    }
  }
  
// JN change
//  public void setAreaJoin(AreaJoin areaJoin)
//  {
//    this.areaJoin = areaJoin;
//  }

  // JN change
//  @Override
//  protected void setGeoDisplayLabelSQL()
//  {
//    // do nothing
//  }
//  
//  private void setWITHGeoDisplayLabelSQL()
//  {
//    String sql = QueryUtil.getGeoDisplayLabelSQL(false);
//    this.addWITHEntry(new WITHEntry(QueryUtil.GEO_DISPLAY_LABEL, sql));
//  }
  
  /**
   * Sets the entire contents of the WITH clause for the query. This method MUST
   * EXECUTE LAST so that all the flags and column knowledge will be known to do
   * dependencies.
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
    for(String dateGroup : this.dategroups.keySet())
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
    for(Universal u : this.universals.values())
    {
      neutral.add(u.getEntityNameAlias());
      neutral.add(u.getGeoIdAlias());
    }

    // any thing that needs sprayed units requires all activity levels
    if(this.needsSprayedUnits)
    {
      this.addRequiredView(View.ALL_ACTUALS);
    }
    
    // JN change
//    if(!this.needsAreaPlanned)
//    {
//      this.setWITHGeoDisplayLabelSQL();
//    }
    
    
    QBInterceptor interceptor = this.getQBInterceptor(this.irsParser);
    if(interceptor != null && interceptor.getGeoCriteriaProcessed() > 0)
    {
      this.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
    }
    
    if(this.requiredViews.contains(View.PLANNED_OPERATOR) && this.hasUniversal())
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
        if(universals.length() > 0)
        {
          this.addRequiredAlias(View.ALL_ACTUALS, Alias.GEO_ENTITY);
          break;
        }
      }
    }
    catch(JSONException e)
    {
      String msg = "IRS QB: Unable to determine if a universal was included as a query column.";
      throw new ProgrammingErrorException(msg, e);
    }
    
    // Loop through and collect all selectables aliases. Note that
    // other places might set aliases as well, but this is to make
    // sure the obvious Selectables are accounted for.
    this.hasActivity = this.needsSprayedUnits; // all sprayed units require activity
    for (Selectable s : this.irsVQ.getSelectableRefs())
    {
      String userDefinedAlias = s.getUserDefinedAlias(); // alias from incoming
                                                         // XML
      
      if(!neutral.contains(userDefinedAlias) && !planned.contains(userDefinedAlias) && !this.universalAliases.contains(userDefinedAlias))
      {
        this.hasActivity = true;
      }
      
      Alias alias = AliasLookup.get(userDefinedAlias);
      if (alias != null)
      {
        this.selectAliases.add(alias);
      }
    }    

    if(this.hasActivity())
    {
      this.addRequiredView(View.ALL_ACTUALS);
    }
    
    if(this.hasActivity() && this.dategroups.size() > 0)
    {
      this.addRequiredView(View.DATE_GROUPS);
    }

    // Invoke each View and load the dependencies.
    View[] views = View.values();
    List<Pair<String, SQLProvider>> viewPairs = new LinkedList<Pair<String, SQLProvider>>();
    
    // The trick is to load the dependencies in reverse order of the query.
    // This ensures the the later queries load their earlier dependencies.
    SQLProvider sprayView = null;
    for(int i=views.length-1; i>=0; i--)
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
          
          Pair<String, SQLProvider> pair = new Pair<String, SQLProvider>(view.getView(), p);
          
          // JN change
//          if(this.needsAreaPlanned && (view == View.PLANNED_AREA 
//              || view == View.GEO_TARGET_VIEW 
//              || view == View.DATE_EXTRAPOLATION_VIEW))
//          {
//            this.areaPairs.add(pair);
//          }
//          else
//          {
            viewPairs.add(pair);
//          }
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
    this.preAggregateAliases.add(Alias.OPERATOR_ACTUAL_TARGET);
    return PRE_AGGREGATION+"."+Alias.OPERATOR_ACTUAL_TARGET;
//    return this.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, Alias.OPERATOR_ACTUAL_TARGET.getAlias());
  }

  private String sumTeamActualTargets()
  {
    this.preAggregateAliases.add(Alias.TEAM_ACTUAL_TARGET);
    return PRE_AGGREGATION+"."+Alias.TEAM_ACTUAL_TARGET;
//    return this.sumColumnForId(sprayViewAlias, idCol, sprayViewAlias, Alias.TEAM_ACTUAL_TARGET.getAlias());
  }

  private String sumOperatorPlannedTargets()
  {
    this.needsOperatorPlanned = true;

    return "NULL::"+Alias.OPERATOR_PLANNED_TARGET.getType();
  }

  private String sumTeamPlannedTargets()
  {
    this.needsTeamsPlanned = true;

//    return QueryUtil.sumColumnForId(sprayViewAlias, Alias.UNIQUE_PLANNED_ID.getAlias(), sprayViewAlias,
//        Alias.TEAM_PLANNED_TARGET.getAlias());
    return "NULL::"+Alias.TEAM_PLANNED_TARGET.getType();
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
//      String sql = "(" + this.sumOperatorActualTargets() + "/NULLIF(" + sumOperatorPlannedTargets()
//          + ",0))*100.0";
      calc.setSQL("NULL::"+Alias.OPERATOR_TARGET_DIVERGENCE.getType());
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
//      String sql = "(" + this.sumTeamActualTargets() + "/NULLIF(" + this.sumTeamPlannedTargets()
//          + ",0))*100.0";
      calc.setSQL("NULL::"+Alias.TEAM_TARGET_DIVERGENCE.getType());
    }
  }

  private void calculateOperatorTargetedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.OPERATOR_TARGETED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
//      this.needUniqueSprayId = true;
      
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
//      this.needUniqueSprayId = true;
      
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
//      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.OPERATOR_PLANNED_COVERAGE.getAlias());
//      String sql = "((" + sum + ")/NULLIF(" + this.sumOperatorPlannedTargets() + ",0))*100.0";
//      calc.setSQL(sql);
      calc.setSQL("NULL::"+Alias.TEAM_PLANNED_COVERAGE.getType());
    }
  }

  private void calculateTeamPlannedCoverage()
  {
    if (irsVQ.hasSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias()))
    {
      this.needsSprayedUnits = true;
      this.needsTeamsPlanned = true;
      
      String uniqueSprayId = this.getUniqueSprayDetailsId();
//      String sum = QueryUtil.sumColumnForId(sprayViewAlias, uniqueSprayId, null, this.sprayedUnits);

      SelectableSQL calc = (SelectableSQL) irsVQ.getSelectableRef(Alias.TEAM_PLANNED_COVERAGE.getAlias());
//      String sql = "((" + sum + ")/NULLIF(" + this.sumTeamPlannedTargets() + ",0))*100.0";
//      calc.setSQL(sql);
      calc.setSQL("NULL::"+Alias.TEAM_PLANNED_COVERAGE.getType());
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
            
            this.universals.put(universalType, new Universal(name, id, entityNameAlias, geoIdAlias));
            this.universalAliases.add(entityNameAlias);
            this.universalAliases.add(geoIdAlias);
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
