package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewQueryBuilder;

import dss.vector.solutions.PersonQuery;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class TeamMemberViewQuery extends TeamMemberViewQueryBase  implements Reloadable
{
private static final long serialVersionUID = 1241483300997L;

  public TeamMemberViewQuery(QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultTeamMemberViewBuilder(queryFactory));
  }

  public TeamMemberViewQuery(QueryFactory queryFactory, ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultTeamMemberViewBuilder extends ViewQueryBuilder implements Reloadable
  {
    private PersonQuery personQuery;
    private TeamMemberQuery memberQuery;

    public DefaultTeamMemberViewBuilder(QueryFactory queryFactory)
    {
      super(queryFactory);
      personQuery = new PersonQuery(queryFactory);
      memberQuery = new TeamMemberQuery(queryFactory);
    }

    protected TeamMemberViewQuery getViewQuery()
    {
      return (TeamMemberViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      TeamMemberViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(TeamMemberView.FIRSTNAME, personQuery.getFirstName());
      vQuery.map(TeamMemberView.LASTNAME, personQuery.getLastName());
      vQuery.map(TeamMemberView.MEMBERID, memberQuery.getId());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      TeamMemberViewQuery vQuery = this.getViewQuery();
      
      vQuery.WHERE(memberQuery.getPerson().EQ(personQuery));
    }

  }
}
