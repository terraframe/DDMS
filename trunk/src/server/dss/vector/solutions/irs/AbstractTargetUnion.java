package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
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
  
  protected String targeter;

  /**
   * The owning IRSQuery instance of this union. The variable is protected for
   * easy reading by the subclasses.
   */
  protected IRSQuery         q;

  protected int              startDay;

  public AbstractTargetUnion()
  {
    q = null;
    
    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    this.targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());
    
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
  public String set(String table, Alias column, Alias alias)
  {
    return set(table, column.getAlias(), alias);
  }

  public String set(String table, String column, Alias alias)
  {
    return set(table + "." + column, alias);
  }

  public String set(String value, Alias alias)
  {
    String type = alias.getType();
    return value + " " + (type != null ? "::"+type+" ":"") + AS + " " + alias;
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
  
  public String where()
  {
    return "";
  }
  
  public abstract String from();
}
