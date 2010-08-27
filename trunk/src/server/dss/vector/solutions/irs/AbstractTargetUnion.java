package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractTargetUnion implements IRSUnionIF, Reloadable
{
  public static final String EMPTY = "''";

  public static final String AS    = "AS";

  public static final String NULL  = "NULL";

  public static final String ZERO  = "0";

  protected String           idCol;

  protected String           personTable;

  protected String           lastNameCol;

  protected String           firstNameCol;

  protected String           teamMemberTable;

  protected String           memberIdCol;

  protected String           personCol;

  protected String           teamIdCol;

  protected String           sprayTeamTable;

  protected String           abstractSprayTable;

  protected String           geoEntityCol;

  protected String           sprayDateCol;
  
  protected String           sprayMethodCol;

  protected String           surfaceTypeCol;

  protected String           brandCol;

  protected String           malariaSeasonTable;

  protected String           startDateCol;

  protected String           endDateCol;

  /**
   * The owning IRSQuery instance of this union. The variable is protected for
   * easy reading by the subclasses.
   */
  protected IRSQuery         q;

  protected int              startDay;

  protected String           diseaseId;

  public AbstractTargetUnion()
  {
    q = null;
    
    diseaseId = Disease.getCurrent().getId();
    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
    this.personTable = personMd.getTableName();
    this.firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
    this.lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);

    MdEntityDAOIF teamMemberMd = MdEntityDAO.getMdEntityDAO(TeamMember.CLASS);
    this.teamMemberTable = teamMemberMd.getTableName();
    this.memberIdCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.MEMBERID);
    this.personCol = QueryUtil.getColumnName(teamMemberMd, TeamMember.PERSON);

    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    this.teamIdCol = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
    this.sprayTeamTable = sprayTeamMd.getTableName();

    MdEntityDAOIF abstractSprayMd = MdEntityDAO.getMdEntityDAO(AbstractSpray.CLASS);
    this.abstractSprayTable = abstractSprayMd.getTableName();
    this.geoEntityCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.GEOENTITY);
    this.sprayDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYDATE);
    this.sprayMethodCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SPRAYMETHOD);
    this.surfaceTypeCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.SURFACETYPE);
    this.brandCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.BRAND);

    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    this.malariaSeasonTable = malariaSeasonMd.getTableName();
    this.startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    this.endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);
    
    this.idCol = QueryUtil.getIdColumn();
  }

  public final void setIRSQuery(IRSQuery irsQuery)
  {
    this.q = irsQuery;
  }

  public String set(String table, String column, Alias alias)
  {
    return set(table + "." + column, alias);
  }

  public String set(String value, Alias alias)
  {
    return value + " " + AS + " " + alias;
  }

  public String setEmpty(Alias alias)
  {
    return set(EMPTY, alias);
  }

  public String setNULL(Alias alias)
  {
    return set(NULL, alias);
  }

  public String setZero(Alias alias)
  {
    return set(ZERO, alias);
  }

  public String setAggregationLevel(Alias alias)
  {

    return setNULL(alias);
  }

  public String setGeoEntity(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSprayMethod(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setBrand(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setSurfaceType(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setAreaPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setBedNets(Alias alias)
  {

    return setNULL(alias);
  }

  public String setDisease(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdId(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholdUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setId(Alias alias)
  {

    return setNULL(alias);
  }

  public String setLocked(Alias alias)
  {

    return setNULL(alias);
  }

  public String setOperatorActualTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setOperatorPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setOther(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPeople(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setPrevSprayedStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setReceived(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRefills(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRefused(Alias alias)
  {

    return setNULL(alias);
  }

  public String setReturned(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRoomUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRooms(Alias alias)
  {

    return setNULL(alias);
  }

  public String setRoomsWithBedNets(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayDate(Alias alias)
  {

    return setNULL(alias);
  }

  // public String setSprayLeader(Alias alias)
  // {
  //
  // return setNULL(alias);
  // }

  public String setSprayLeaderDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  // public String setSprayOperator(Alias alias)
  // {
  //
  // return setNULL(alias);
  // }

  public String setSprayOperatorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSpraySeason(Alias alias)
  {

    return setNULL(alias);
  }

  // public String setSprayTeam(Alias alias)
  // {
  //
  // return setNULL(alias);
  // }

  public String setSprayTeamDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholds(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedHouseholdsShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRooms(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedRoomsShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setSprayedStructuresShare(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructureId(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructureUnsprayed(Alias alias)
  {

    return setNULL(alias);
  }

  public String setStructures(Alias alias)
  {

    return setNULL(alias);
  }

  public String setTeamActualTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setTeamPlannedTarget(Alias alias)
  {

    return setNULL(alias);
  }

  public String setUsed(Alias alias)
  {

    return setNULL(alias);
  }

  // public String setZoneSuperVisor(Alias alias)
  // {
  //
  // return setNULL(alias);
  // }

  public String setZoneSuperVisorDefaultLocale(Alias alias)
  {

    return setNULL(alias);
  }

  public String from()
  {
    return EMPTY;
  }

  public String where()
  {
    return EMPTY;
  }
}
