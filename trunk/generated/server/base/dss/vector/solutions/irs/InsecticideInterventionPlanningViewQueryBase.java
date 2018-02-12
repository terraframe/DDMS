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
package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = -698791300)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InsecticideInterventionPlanningView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public  abstract  class InsecticideInterventionPlanningViewQueryBase extends dss.vector.solutions.irs.InterventionPlanningViewQuery
 implements com.runwaysdk.generation.loader.Reloadable
{

  public InsecticideInterventionPlanningViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory)
  {
    super(componentQueryFactory);
  }

  public InsecticideInterventionPlanningViewQueryBase(com.runwaysdk.query.QueryFactory componentQueryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(componentQueryFactory, viewQueryBuilder);
  }
  public String getClassType()
  {
    return dss.vector.solutions.irs.InsecticideInterventionPlanningView.CLASS;
  }
  public com.runwaysdk.query.SelectableDouble getRequiredInsecticide()
  {
    return getRequiredInsecticide(null);

  }
 
  public com.runwaysdk.query.SelectableDouble getRequiredInsecticide(String alias)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.irs.InsecticideInterventionPlanningView.REQUIREDINSECTICIDE, alias, null);

  }
 
  public com.runwaysdk.query.SelectableDouble getRequiredInsecticide(String alias, String displayLabel)
  {
    return (com.runwaysdk.query.SelectableDouble)this.getSelectable(dss.vector.solutions.irs.InsecticideInterventionPlanningView.REQUIREDINSECTICIDE, alias, displayLabel);

  }
 
  /**  
   * Returns an iterator of Business objects that match the query criteria specified
   * on this query object. 
   * @return iterator of Business objects that match the query criteria specified
   * on this query object.
   */
  @java.lang.SuppressWarnings("unchecked")
  public com.runwaysdk.query.OIterator<? extends InsecticideInterventionPlanningView> getIterator()
  {
    com.runwaysdk.query.ValueIterator valueIterator;
    if (_pageSize != null && _pageNumber != null)
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator(_pageSize, _pageNumber);
    }
    else
    {
      valueIterator = (com.runwaysdk.query.ValueIterator<com.runwaysdk.dataaccess.ValueObject>)this.getComponentQuery().getIterator();
    }
    return new com.runwaysdk.query.ViewIterator<InsecticideInterventionPlanningView>(this.getMdClassIF(), valueIterator);
  }

}
