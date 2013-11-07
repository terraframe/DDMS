package dss.vector.solutions.querybuilder.irs;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.Person;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.util.QueryUtil;

/**
 *
 */
public abstract class AbstractTargetUnion extends AbstractSprayProvider implements Reloadable
{

  protected String              idCol;

  protected String              personTable;

  protected String              lastNameCol;

  protected String              firstNameCol;

  protected String              teamMemberTable;

  protected String              memberIdCol;

  protected String              personCol;

  protected String              teamIdCol;

  protected String              sprayTeamTable;

  protected String              abstractSprayTable;

  protected String              geoEntityCol;

  protected String              sprayDateCol;

  protected String              sprayMethodCol;

  protected String              surfaceTypeCol;

  protected String              brandCol;

  protected String              malariaSeasonTable;

  protected String              startDateCol;

  protected String              endDateCol;

  protected String              createDateCol;

  protected String              lastUpdateDateCol;

  protected String              createdByCol;

  protected String              lastUpdatedByCol;

  protected String              targeter;

  protected String              identifierCol;

  protected String              sexCol;

  protected String              birthdateCol;

  /**
   * There are 3 joins to the Person table. This is the unique alias for the
   * spray operator.
   */
  protected static final String OPERATOR_PERSON   = "operator_person";

  /**
   * There are 3 joins to the Person table. This is the unique alias for the
   * spray leader.
   */
  protected static final String LEADER_PERSON     = "leader_person";

  /**
   * There are 3 joins to the Person table. This is the unique alias for the
   * zone supervisor.
   */
  protected static final String SUPERVISOR_PERSON = "supervisor_person";

  /**
   * Alias of the team member table used to describe the spray leader.
   */
  protected static final String LEADER_MEMBER     = "leader_member";

  /**
   * Alias of the team member table used to describe the spray operator.
   */
  protected static final String OPERATOR_MEMBER   = "operator_member";

  /**
   * Alias of the team member table used to describe the spray supervisor.
   */
  protected static final String SUPERVISOR_MEMBER = "supervisor_member";

  protected int                 startDay;

  public AbstractTargetUnion(IRSQB irsQB)
  {
    super(irsQB);

    startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);

    this.targeter = QueryUtil.getColumnName(ResourceTarget.getTargeterMd());

    MdEntityDAOIF personMd = MdEntityDAO.getMdEntityDAO(Person.CLASS);
    this.personTable = personMd.getTableName();
    this.firstNameCol = QueryUtil.getColumnName(personMd, Person.FIRSTNAME);
    this.lastNameCol = QueryUtil.getColumnName(personMd, Person.LASTNAME);
    this.identifierCol = QueryUtil.getColumnName(personMd, Person.IDENTIFIER);
    this.sexCol = QueryUtil.getColumnName(personMd, Person.SEX);
    this.birthdateCol = QueryUtil.getColumnName(personMd, Person.DATEOFBIRTH);

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
    this.createDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.CREATEDATE);
    this.lastUpdateDateCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.LASTUPDATEDATE);
    this.createdByCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.CREATEDBY);
    this.lastUpdatedByCol = QueryUtil.getColumnName(abstractSprayMd, AbstractSpray.LASTUPDATEDBY);

    MdEntityDAOIF malariaSeasonMd = MdEntityDAO.getMdEntityDAO(MalariaSeason.CLASS);
    this.malariaSeasonTable = malariaSeasonMd.getTableName();
    this.startDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.STARTDATE);
    this.endDateCol = QueryUtil.getColumnName(malariaSeasonMd, MalariaSeason.ENDDATE);

    this.idCol = QueryUtil.getIdColumn();
  }

}
