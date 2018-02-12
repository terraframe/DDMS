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

@com.runwaysdk.business.ClassSignature(hash = 288714795)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SurveyedPersonViewQuery extends dss.vector.solutions.intervention.monitor.SurveyedPersonViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 288714795;

  public SurveyedPersonViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSurveyedPersonViewBuilder(queryFactory));
  }

  public SurveyedPersonViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSurveyedPersonViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultSurveyedPersonViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected SurveyedPersonViewQuery getViewQuery()
    {
      return (SurveyedPersonViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      String errMsg = "buildSelectClause() method in class DefaultSurveyedPersonViewBuilder needs to be overwritten.";
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
