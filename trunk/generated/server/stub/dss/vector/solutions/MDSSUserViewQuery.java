package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ViewQueryBuilder;

/**
 * 
 * @author Autogenerated by TerraFrame
 */
public class MDSSUserViewQuery extends MDSSUserViewQueryBase implements Reloadable
{
  private static final long serialVersionUID = 1242427875693L;

  public MDSSUserViewQuery(QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultMDSSUserViewBuilder(queryFactory));
  }

  public MDSSUserViewQuery(QueryFactory queryFactory, ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultMDSSUserViewBuilder extends ViewQueryBuilder implements Reloadable
  {
    private PersonQuery   personQuery;

    private MDSSUserQuery userQuery;

    public DefaultMDSSUserViewBuilder(QueryFactory queryFactory)
    {
      super(queryFactory);
      personQuery = new PersonQuery(queryFactory);
      userQuery = new MDSSUserQuery(queryFactory);
    }

    protected MDSSUserViewQuery getViewQuery()
    {
      return (MDSSUserViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      MDSSUserViewQuery vQuery = this.getViewQuery();

      vQuery.map(MDSSUserView.FIRSTNAME, personQuery.getFirstName());
      vQuery.map(MDSSUserView.LASTNAME, personQuery.getLastName());
      vQuery.map(MDSSUserView.PERSONID, personQuery.getId());
      vQuery.map(MDSSUserView.USERNAME, userQuery.getUsername());
      vQuery.map(MDSSUserView.USERID, userQuery.getId());

      // IMPORTANT: The roles attribute is a marker to assign permissions and
      // ontology roots. However it is a required value, and as such it needs
      // a value to work with the query api. Therefore, fill in a random value
      // knowing it will never be used.
      vQuery.map(MDSSUserView.ROLES, userQuery.getId());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      MDSSUserViewQuery vQuery = this.getViewQuery();

      vQuery.WHERE(userQuery.getPerson().EQ(personQuery));
    }

  }
}
