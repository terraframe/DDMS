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
package dss.vector.solutions.ontology;

import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdAttributeReferenceQuery;
import com.runwaysdk.system.metadata.MdAttributeVirtualQuery;
import com.runwaysdk.system.metadata.MdClassQuery;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class BrowserFieldViewQuery extends dss.vector.solutions.ontology.BrowserFieldViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1252959713156L;

  public BrowserFieldViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultBrowserFieldViewBuilder(queryFactory));
  }

  public BrowserFieldViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultBrowserFieldViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    
//    private MdAttributeQuery mdAttributeQuery;
    private MdAttributeReferenceQuery mdRefQuery;
    private MdAttributeReferenceQuery mdVirtualRefQuery;
    private MdAttributeVirtualQuery mdVirtualQuery;
    
    private MdClassQuery mdClassQuery;
    private BrowserFieldQuery fieldQuery;
    private ValueQuery unioned;
    
    private ValueQuery concreteQuery;
    private ValueQuery virtualQuery;
    
    public DefaultBrowserFieldViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);
      
      this.mdRefQuery = new MdAttributeReferenceQuery(queryFactory);
      this.mdVirtualRefQuery = new MdAttributeReferenceQuery(queryFactory);
      this.mdVirtualQuery = new MdAttributeVirtualQuery(queryFactory);
      
      this.mdClassQuery = new MdClassQuery(queryFactory);
      this.fieldQuery = new BrowserFieldQuery(queryFactory);
      
      this.concreteQuery = new ValueQuery(queryFactory);
      this.virtualQuery = new ValueQuery(queryFactory);
      
      this.unioned = new ValueQuery(queryFactory);
    }

    protected BrowserFieldViewQuery getViewQuery()
    {
      return (BrowserFieldViewQuery)super.getViewQuery();
    }

    protected void buildSelectClause()
    {
      BrowserFieldViewQuery query = this.getViewQuery();
      
      // join concrete attribute with display labels
      this.concreteQuery.SELECT(this.mdClassQuery.getId("classId"),
          this.mdClassQuery.getDisplayLabel().localize("classLabel"),
          this.mdRefQuery.getId("attributeId"),
          this.mdRefQuery.getDisplayLabel().localize("attributeLabel"),
          this.mdRefQuery.getDefaultValue("defaultValue"),
          this.mdRefQuery.getDefiningMdClass().getId("definingMdClass"));
      
      
      // join virtual attribute with display labels
      this.virtualQuery.SELECT(this.mdClassQuery.getId("classId"),
          this.mdClassQuery.getDisplayLabel().localize("classLabel"),
          this.mdVirtualQuery.getId("attributeId"),
          this.mdVirtualQuery.getDisplayLabel().localize("attributeLabel"),
          this.mdVirtualRefQuery.getDefaultValue("defaultValue"),
          this.mdVirtualQuery.getDefiningMdView().getId("definingMdClass"));
      
      
      // union the attribute queries
      this.unioned.UNION(this.concreteQuery, this.virtualQuery);
      
      query.map(BrowserFieldView.BROWSERFIELDID, this.fieldQuery.getId());
      
      query.map(BrowserFieldView.MDCLASSID, this.unioned.get("classId"));
      query.map(BrowserFieldView.MDCLASSLABEL, this.unioned.get("classLabel"));

      query.map(BrowserFieldView.MDATTRIBUTEID, this.unioned.get("attributeId"));
      query.map(BrowserFieldView.MDATTRIBUTELABEL, this.unioned.get("attributeLabel"));
      query.map(BrowserFieldView.DEFAULTVALUE, this.unioned.get("defaultValue"));
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      BrowserFieldViewQuery query = this.getViewQuery();
      this.concreteQuery.WHERE(this.mdRefQuery.getDefiningMdClass().EQ(this.mdClassQuery));
      
      this.virtualQuery.WHERE(this.mdVirtualQuery.getDefiningMdView().EQ(this.mdClassQuery));
      this.virtualQuery.AND(this.mdVirtualQuery.getMdAttributeConcrete().EQ(this.mdVirtualRefQuery));
      
      // join the MOField to the MdAttribute
      query.WHERE(this.fieldQuery.getMdAttribute().getId().EQ((SelectableChar) this.unioned.get("attributeId")));
      
      query.ORDER_BY_ASC((SelectablePrimitive) this.unioned.get("classLabel"));
    }

  }
}
