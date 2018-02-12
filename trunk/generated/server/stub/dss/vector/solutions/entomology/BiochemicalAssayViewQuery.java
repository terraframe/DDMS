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
package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -441276206)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class BiochemicalAssayViewQuery extends dss.vector.solutions.entomology.BiochemicalAssayViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = -441276206;

  public BiochemicalAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultBiochemicalAssayViewBuilder(queryFactory));
  }

  public BiochemicalAssayViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultBiochemicalAssayViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private BiochemicalAssayQuery query;
    
    public DefaultBiochemicalAssayViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      query = new BiochemicalAssayQuery(queryFactory);
    }

    protected BiochemicalAssayViewQuery getViewQuery()
    {
      return (BiochemicalAssayViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      BiochemicalAssayViewQuery vQuery = this.getViewQuery();
      
      vQuery.map(BiochemicalAssayView.UNIQUEASSAYID, query.getUniqueAssayId());
      vQuery.map(BiochemicalAssayView.CONCRETEID, query.getId());
      vQuery.map(BiochemicalAssayView.COLLECTION, query.getCollection());
      vQuery.map(BiochemicalAssayView.IDENTMETHOD, query.getIdentMethod());
      vQuery.map(BiochemicalAssayView.ISOFEMALE, query.getIsofemale());
      vQuery.map(BiochemicalAssayView.MOSQUITOID, query.getMosquitoId());
      vQuery.map(BiochemicalAssayView.NUMBERELEVATED, query.getNumberElevated());
      vQuery.map(BiochemicalAssayView.NUMBERTESTED, query.getNumberTested());
      vQuery.map(BiochemicalAssayView.GENERATION, query.getGeneration());
      vQuery.map(BiochemicalAssayView.SEX, query.getSex());
      vQuery.map(BiochemicalAssayView.SPECIES, query.getSpecies());
      vQuery.map(BiochemicalAssayView.ASSAY, query.getAssay());
    }
    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }
}
