/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.intervention.monitor;

/**
 *
 * @author Autogenerated by RunwaySDK
 */
public class PersonInterventionMethodViewQuery extends dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 354153519;

  public PersonInterventionMethodViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultPersonInterventionMethodViewBuilder(queryFactory));
  }

  public PersonInterventionMethodViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultPersonInterventionMethodViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultPersonInterventionMethodViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected PersonInterventionMethodViewQuery getViewQuery()
    {
      return (PersonInterventionMethodViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultPersonInterventionMethodViewBuilder needs to be overwritten.";
      throw new com.runwaysdk.query.QueryException(errMsg);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
